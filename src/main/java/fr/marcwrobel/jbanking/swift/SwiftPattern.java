package fr.marcwrobel.jbanking.swift;

import static java.util.Objects.requireNonNull;

import fr.marcwrobel.jbanking.internal.AsciiCharacters;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A partial support of a SWIFT expression (a kind of regular expression) as used in many SWIFT documents (for instance
 * <a href="https://www.iso13616.org/">the IBAN registry document</a>).
 *
 * <p>
 * This class used to transform SWIFT patterns to {@link java.util.regex.Pattern Java patterns} to perform its task.
 * This was changed in version 4.0.0 to improve performance of the {@link fr.marcwrobel.jbanking.iban.Iban} class.
 *
 * <p>
 * <b>Note that variable length indication is not supported anymore since version 4.0.0</b>.
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
 * <caption>Length indications (variable length is not supported)</caption>
 * <tr>
 * <td>nn!</td>
 * <td>fixed length</td>
 * </tr>
 * </table>
 *
 * <p>
 * Here are some examples of SWIFT expressions that are supported by this SwiftPattern :
 *
 * <ul>
 * <li>{@code 4!n} : four digits
 * <li>{@code 4!c3!a} : four upper or lower case alphanumeric characters followed by three upper case
 * letters
 * <li>{@code 2!e4!a} : two spaces followed by four upper case letters
 * </ul>
 *
 * <p>
 * This class has some limitations in order to prevent stack overflows, as explained in
 * <a href="https://sonarcloud.io/organizations/marcwrobel/rules?open=java%3AS5998&rule_key=java%3AS5998">Regular
 * expressions should not overflow the stack</a>. The maximum supported length of a group is set to 999 and the maximum
 * number of groups (such as {@code 4!n}) is 1000. These limitations are far above anything that can be seen in SWIFT
 * documents and should be sufficient.
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

  private static final String GROUP_REGEX = "\\d{1,3}!?[ance]";
  private static final Pattern SWIFT_FORMAT_PATTERN = Pattern.compile("^(" + GROUP_REGEX + "){1,1000}$");
  private static final Pattern SWIFT_FORMAT_GROUPS_PATTERN = Pattern.compile(GROUP_REGEX);

  /**
   * The SWIFT pattern.
   */
  private final String expression;

  private final List<Group> groups;
  private final int length;

  private SwiftPattern(String expression, List<Group> groups) {
    this.expression = expression;
    this.groups = groups;

    int expectedLength = 0;
    for (Group group : groups) {
      expectedLength += group.length;
    }
    this.length = expectedLength;
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

    return new SwiftPattern(expression, toGroups(expression));
  }

  private static List<Group> toGroups(String expression) {
    Matcher matcher = SWIFT_FORMAT_GROUPS_PATTERN.matcher(expression);
    List<Group> groups = new ArrayList<>();

    int from = 0;
    while (matcher.find()) {
      Group group = transform(expression, matcher.group(), from);
      from = from + group.length;
      groups.add(group);
    }

    return Collections.unmodifiableList(groups);
  }

  private static Group transform(String expression, String groupExpression, int from) {
    int length = groupExpression.length();

    char qualifier = groupExpression.charAt(length - 1);
    Optional<CharacterClass> cClass = CharacterClass.from(qualifier);
    if (!cClass.isPresent()) {
      // should never happen because expression was already tested against SWIFT_FORMAT_GROUPS_PATTERN
      throw new SwiftPatternSyntaxException(expression,
          "illegal qualifier '" + qualifier + "' in group '" + groupExpression + "'");
    }

    boolean strict = groupExpression.charAt(length - 2) == '!';
    if (!strict) {
      // non-fixed length are not supported for the time being
      throw new SwiftPatternSyntaxException(expression, "non-fixed length group '" + groupExpression +
          "' is not supported");
    }

    int maxOccurrences;
    try {
      maxOccurrences = Integer.parseInt(groupExpression.substring(0, length - 2));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("could not extract length from group '" + groupExpression + "'");
    }

    return new Group(from, maxOccurrences, cClass.get());
  }

  /**
   * Checks whether the given string matches this expression.
   *
   * @return {@code true} if the given string matches this expression, {@code false} otherwise.
   */
  public boolean matches(String s) {
    if (s.length() != length) {
      return false;
    }

    for (Group group : groups) {
      if (!group.matches(s)) {
        return false;
      }
    }

    return true;
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

  /**
   * Returns the SWIFT expression from which this pattern was created.
   *
   * @return a non-null string.
   */
  @Override
  public String toString() {
    return expression;
  }

  private enum CharacterClass {
    DIGITS('n') {
      @Override
      boolean isValid(char c) {
        return AsciiCharacters.isNumeric(c);
      }
    },

    UPPER_CASE_LETTERS('a') {
      @Override
      boolean isValid(char c) {
        return AsciiCharacters.isUpperAlphabetic(c);
      }
    },

    UPPER_AND_LOWER_CASE_ALPHANUMERICS('c') {
      @Override
      boolean isValid(char c) {
        return AsciiCharacters.isAlphanumeric(c);
      }
    },

    SPACES('e') {
      @Override
      boolean isValid(char c) {
        return c == ' ';
      }
    };

    private final char qualifier;

    CharacterClass(char qualifier) {
      this.qualifier = qualifier;
    }

    public static Optional<CharacterClass> from(char qualifier) {
      for (CharacterClass cClass : values()) {
        if (cClass.qualifier == qualifier) {
          return Optional.of(cClass);
        }
      }

      return Optional.empty();
    }

    abstract boolean isValid(char c);
  }

  private static class Group implements Serializable {
    final int from;
    final int length;
    final CharacterClass cClass;

    private Group(int from, int length, CharacterClass cClass) {
      this.from = from;
      this.length = length;
      this.cClass = requireNonNull(cClass);
    }

    boolean matches(String s) {
      // length check is done at SwiftPatten level
      for (int i = from; i < from + length; i++) {
        if (!cClass.isValid(s.charAt(i))) {
          return false;
        }
      }

      return true;
    }
  }
}
