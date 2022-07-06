package fr.marcwrobel.jbanking.swift;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A partial compiled representation of a SWIFT expression (a kind of regular expression) as used in many SWIFT documents (for
 * instance <a href="http://www.swift.com/dsp/resources/documents/IBAN_Registry.pdf">the IBAN registry document</a>).
 *
 * <p>
 * This class internally uses a {@link java.util.regex.Pattern} by transforming the SWIFT expression to a traditional regular
 * expression. As a result the SwiftPattern API is similar to the {@link java.util.regex.Pattern Pattern API}.
 *
 * <p>
 * The SwiftPattern class partially supports the SWIFT expression by using the following constructions :<br>
 *
 * <table>
 * <caption>Character representations</caption>
 * <tr>
 * <td>n</td>
 * <td>digits (numeric characters 0 to 9 only)</td>
 * </tr>
 * <tr>
 * <td>a</td>
 * <td>upper case letters (alphabetic characters A-Z only)</td>
 * </tr>
 * <tr>
 * <td>c</td>
 * <td>upper and lower case alphanumeric characters (A-Z, a-z and 0-9)</td>
 * </tr>
 * <tr>
 * <td>e</td>
 * <td>blank space</td>
 * </tr>
 * </table>
 *
 * <table>
 * <caption>Length indications</caption>
 * <tr>
 * <td>nn!</td>
 * <td>fixed length</td>
 * </tr>
 * <tr>
 * <td>nn</td>
 * <td>maximum length</td>
 * </tr>
 * </table>
 *
 * <p>
 * Here are some examples of SWIFT expressions that are supported by this SwiftPattern :<br>
 *
 * <ul>
 * <li>{@code 4!n} (corresponding regex [0-9]{4}) : four consecutive digits
 * <li>{@code 4!a3c} (corresponding regex [A-Za-z0-9]{4}[A-Z]{1,3}) : four consecutive upper or lower case alphanumeric
 * characters followed by one to three upper case letters
 * <li>{@code 2e4!a} (corresponding regex [ ]{1,2}) : one or two consecutive spaces followed by four consecutive upper
 * case letters
 * </ul>
 *
 * <p>
 * This class has some limitations in order to prevent stack overflows, as explained in
 * <a href="https://sonarcloud.io/organizations/marcwrobel/rules?open=java%3AS5998&rule_key=java%3AS5998">Regular expressions
 * should not overflow the stack</a>. The maximum supported length is set to 999 and the maximum number of groups (such as
 * {@code 4!n}) is 1000. these limitations are far above anything that can be seen in SWIFT documents and should be sufficient.
 *
 * <p>
 * Instances of this class are immutable and are safe for use by multiple concurrent threads.
 *
 * @author Marc Wrobel
 * @see java.util.regex.Pattern
 * @since 1.0
 */
public final class SwiftPattern implements Serializable {

  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 0;

  static final char DIGITS_CHARACTER = 'n';
  static final char UPPER_CASE_LETTERS_CHARACTER = 'a';
  static final char UPPER_AND_LOWER_CASE_ALPHANUMERICS_CHARACTER = 'c';
  static final char SPACES_CHARACTER = 'e';

  private static final String DIGITS_CLASS = "[0-9]";
  private static final String UPPER_CASE_LETTERS_CLASS = "[A-Z]";
  private static final String UPPER_AND_LOWER_CASE_ALPHANUMERICS_CLASS = "[A-Za-z0-9]";
  private static final String SPACES_CLASS = "[ ]";

  private static final String GROUP_REGEX = "\\d{1,3}!?[ance]";
  private static final Pattern SWIFT_FORMAT_PATTERN = Pattern.compile("^(" + GROUP_REGEX + "){1,1000}$");
  private static final Pattern SWIFT_FORMAT_GROUPS_PATTERN = Pattern.compile(GROUP_REGEX);

  private final String expression;
  private final Pattern equivalentJavaPattern;

  private SwiftPattern(String expression, Pattern equivalentJavaPattern) {
    this.expression = expression;
    this.equivalentJavaPattern = equivalentJavaPattern;
  }

  /**
   * Compiles the given SWIFT expression into a SwiftPattern.
   *
   * @param expression The expression to be compiled
   * @return a SwiftPattern representing the given expression
   * @throws SwiftPatternSyntaxException If the expression's syntax is invalid.
   */
  public static SwiftPattern compile(String expression) {
    if (expression == null) {
      throw new IllegalArgumentException("the given parameter expression cannot be null");
    }

    if (!SWIFT_FORMAT_PATTERN.matcher(expression).matches()) {
      throw new SwiftPatternSyntaxException(expression);
    }

    return new SwiftPattern(expression, Pattern.compile(toRegex(expression)));
  }

  private static String toRegex(String expression) {
    Matcher matcher = SWIFT_FORMAT_GROUPS_PATTERN.matcher(expression);

    StringBuilder regex = new StringBuilder("^");
    while (matcher.find()) {
      regex.append(transform(matcher.group()));
    }
    regex.append("$");

    return regex.toString();
  }

  private static String transform(String simpleExpression) {
    int length = simpleExpression.length();

    String charactersRegex;
    char qualifier = simpleExpression.charAt(length - 1);
    switch (qualifier) {
      case DIGITS_CHARACTER:
        charactersRegex = DIGITS_CLASS;
        break;
      case UPPER_CASE_LETTERS_CHARACTER:
        charactersRegex = UPPER_CASE_LETTERS_CLASS;
        break;
      case UPPER_AND_LOWER_CASE_ALPHANUMERICS_CHARACTER:
        charactersRegex = UPPER_AND_LOWER_CASE_ALPHANUMERICS_CLASS;
        break;
      case SPACES_CHARACTER:
        charactersRegex = SPACES_CLASS;
        break;
      default:
        throw new IllegalArgumentException(
            "illegal qualifier '" + qualifier + "' in expression '" + simpleExpression + "'");
    }

    boolean strict = simpleExpression.charAt(simpleExpression.length() - 2) == '!';
    String maxOccurrences = simpleExpression.substring(0, length - (strict ? 2 : 1));

    return charactersRegex + "{" + (strict ? "" : "1,") + maxOccurrences + "}";
  }

  /**
   * Creates a matcher that will match the given input against this pattern.
   *
   * @param input The character sequence to be matched
   * @return A new matcher for this pattern.
   */
  public Matcher matcher(CharSequence input) {
    return equivalentJavaPattern.matcher(input);
  }

  /**
   * Returns the SWIFT expression from which this pattern was compiled.
   *
   * @return a non-null string.
   */
  public String getExpression() {
    return expression;
  }

  /**
   * Returns the {@link java.util.regex.Pattern java Pattern} build using the SWIFT expression.
   *
   * @return a non-null pattern.
   */
  public Pattern getEquivalentJavaPattern() {
    return equivalentJavaPattern;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SwiftPattern that = (SwiftPattern) o;
    return expression.equals(that.expression);
  }

  @Override
  public int hashCode() {
    return 13 + expression.hashCode();
  }

  @Override
  public String toString() {
    return expression + "{regex=" + equivalentJavaPattern + "}";
  }
}
