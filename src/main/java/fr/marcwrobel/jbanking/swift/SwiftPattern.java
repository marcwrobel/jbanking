package fr.marcwrobel.jbanking.swift;

import java.io.Serializable;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A partial compiled representation of a SWIFT expression (sort of a regular expression) as used in many SWIFT
 * documents (for instance <a href="https://www.iso13616.org/">the IBAN registry document</a>).
 *
 * <p>
 * This class internally uses a {@link java.util.regex.Pattern} by transforming the SWIFT expression to a traditional
 * regular expression. As a result the SwiftPattern API is similar to the {@link java.util.regex.Pattern Pattern API}.
 *
 * <p>
 * The SwiftPattern class partially supports the SWIFT expression by using the following constructions :
 *
 * <table>
 * <caption>Character representations</caption>
 * <tr>
 * <td>n</td>
 * <td>digits (numeric characters 0 to 9 only)</td>
 * </tr>
 * <tr>
 * <td>a</td>
 * <td>uppercase letters (alphabetic characters A-Z only)</td>
 * </tr>
 * <tr>
 * <td>c</td>
 * <td>uppercase and lowercase case alphanumeric characters (A-Z, a-z and 0-9)</td>
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
 * Here are some examples of SWIFT expressions that are supported by this SwiftPattern :
 *
 * <ul>
 * <li>{@code 4!n} (corresponding regex {@code [0-9]{4}}) : four digits
 * <li>{@code 4!c3a} (corresponding regex {@code [A-Za-z0-9]{4}[A-Z]{1,3}}) : four upper or lowercase alphanumeric
 * characters followed by one to three uppercase letters
 * <li>{@code 2e4!a} (corresponding regex {@code [ ]{1,2}[A-Z]{4}}) : one or two spaces followed by four uppercase
 * letters
 * </ul>
 *
 * <p>
 * This class has some limitations in order to prevent stack overflows, as explained in
 * <a href="https://sonarcloud.io/organizations/marcwrobel/rules?open=java%3AS5998&rule_key=java%3AS5998">Regular
 * expressions should not overflow the stack</a>. The maximum supported length is set to 999 and the maximum number of
 * groups (such as {@code 4!n}) is 1000. these limitations are far above anything that can be seen in SWIFT documents
 * and should be sufficient.
 *
 * <p>
 * Instances of this class are immutable and thread-safe.
 *
 * @see java.util.regex.Pattern
 * @since 1.0
 */
public final class SwiftPattern implements Serializable {

  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 0;

  public static final String GROUP_REGEX = "\\d{1,3}!?[ance]";
  public static final Pattern SWIFT_FORMAT_PATTERN = Pattern.compile("^(" + GROUP_REGEX + "){1,1000}$");
  public static final Pattern SWIFT_FORMAT_GROUPS_PATTERN = Pattern.compile(GROUP_REGEX);

  /**
   * The SWIFT pattern.
   */
  private final String expression;

  /**
   * The equivalent {@link Pattern Java pattern} constructed from the SWIFT pattern.
   */
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
      throw new IllegalArgumentException("the given expression cannot be null");
    }

    if (!SWIFT_FORMAT_PATTERN.matcher(expression).matches()) {
      throw new SwiftPatternSyntaxException(expression, "expression must match " + SWIFT_FORMAT_PATTERN);
    }

    return new SwiftPattern(expression, Pattern.compile(toRegex(expression)));
  }

  private static String toRegex(String expression) {
    Matcher matcher = SWIFT_FORMAT_GROUPS_PATTERN.matcher(expression);

    StringBuilder regex = new StringBuilder("^");
    while (matcher.find()) {
      regex.append(transform(expression, matcher.group()));
    }
    regex.append("$");

    return regex.toString();
  }

  private static String transform(String expression, String groupExpression) {
    int length = groupExpression.length();

    char qualifier = groupExpression.charAt(length - 1);
    Optional<SwiftPatternCharacterRepresentation> cRepresentation = SwiftPatternCharacterRepresentation.from(qualifier);
    if (!cRepresentation.isPresent()) {
      // should never happen because expression was already tested against SWIFT_FORMAT_GROUPS_PATTERN
      throw new SwiftPatternSyntaxException(expression,
          "illegal qualifier '" + qualifier + "' in group '" + groupExpression + "'");
    }

    boolean strict = groupExpression.charAt(groupExpression.length() - 2) == '!';
    String maxOccurrences = groupExpression.substring(0, length - (strict ? 2 : 1));

    return cRepresentation.get().regex() + "{" + (strict ? "" : "1,") + maxOccurrences + "}";
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
