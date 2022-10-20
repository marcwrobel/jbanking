package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.swift.SwiftPattern.SWIFT_FORMAT_GROUPS_PATTERN;
import static fr.marcwrobel.jbanking.swift.SwiftPattern.SWIFT_FORMAT_PATTERN;

import fr.marcwrobel.jbanking.swift.SwiftPatternCharacterRepresentation;
import fr.marcwrobel.jbanking.swift.SwiftPatternSyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

/**
 * Similar to {@link fr.marcwrobel.jbanking.swift.SwiftPattern}, but specialized for IBANs. Unlike
 * {@link fr.marcwrobel.jbanking.swift.SwiftPattern}, this class:
 * <ul>
 * <li>does not make use of regexes (to improve performance during IBAN creation and validation),</li>
 * <li>does not support <i>variable length indication</i> (never used for IBANs),</li>
 * <li>does not support spaces and uppercase and lowercase alphanumeric character representations (never used for
 * IBANs).</li>
 * </ul>
 *
 * <p>
 * This class is for jbanking internal use only. Instances of this class are immutable and thread-safe.
 *
 * @since 4.0
 */
final class IbanPattern {

  /**
   * The SWIFT pattern.
   */
  final String expression;

  /**
   * The groups that compose this pattern.
   */
  final List<IbanPatternGroup> groups;

  /**
   * The expected length of the corresponding IBANs.
   */
  final int length;

  private IbanPattern(String expression, List<IbanPatternGroup> groups) {
    this.expression = expression;
    this.groups = groups;

    int expectedLength = 0;
    for (IbanPatternGroup group : groups) {
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
  public static IbanPattern compile(String expression) {
    if (expression == null) {
      throw new IllegalArgumentException("the given expression cannot be null");
    }

    if (!SWIFT_FORMAT_PATTERN.matcher(expression).matches()) {
      throw new SwiftPatternSyntaxException(expression, "expression must match " + SWIFT_FORMAT_PATTERN);
    }

    return new IbanPattern(expression, toGroups(expression));
  }

  private static List<IbanPatternGroup> toGroups(String expression) {
    Matcher matcher = SWIFT_FORMAT_GROUPS_PATTERN.matcher(expression);
    List<IbanPatternGroup> groups = new ArrayList<>();

    int from = 0;
    IbanPatternGroup previousGroup = null;
    while (matcher.find()) {
      IbanPatternGroup group = transform(expression, matcher.group(), from);
      from = from + group.length;

      if (previousGroup != null && previousGroup.canBeMergedTo(group)) {
        groups.remove(previousGroup);
        group = previousGroup.merge(group);
      }

      groups.add(group);
      previousGroup = group;
    }

    return Collections.unmodifiableList(groups);
  }

  private static IbanPatternGroup transform(String expression, String groupExpression, int from) {
    int length = groupExpression.length();

    char qualifier = groupExpression.charAt(length - 1);
    Optional<SwiftPatternCharacterRepresentation> cRepresentation = SwiftPatternCharacterRepresentation.from(qualifier);
    if (!cRepresentation.isPresent()) {
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
      throw new SwiftPatternSyntaxException(expression, "could not extract length from '" + groupExpression + "'");
    }

    return new IbanPatternGroup(cRepresentation.get(), from, maxOccurrences);
  }

  /**
   * Checks whether the given string matches this expression.
   *
   * @return {@code true} if the given string matches this expression, {@code false} otherwise.
   */
  boolean matches(String s) {
    if (s.length() != length) {
      return false;
    }

    for (IbanPatternGroup group : groups) {
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

    IbanPattern that = (IbanPattern) o;
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
}
