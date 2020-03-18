package fr.marcwrobel.jbanking.swift;

/**
 * Thrown to indicate a syntax error in a SWIFT expression pattern.
 *
 * @author Marc Wrobel
 * @see SwiftPattern
 * @since 1.0
 */
public class SwiftPatternSyntaxException extends RuntimeException {

  private final String expression;

  /**
   * Constructs a {@code SwiftPatternSyntaxException} with the expression that caused the error and
   * the given detail message.
   *
   * @param expression a string
   */
  SwiftPatternSyntaxException(String expression) {
    super(String.format("the expression syntax is invalid in '%s'", expression));
    this.expression = expression;
  }

  /**
   * Returns the input expression that caused this exception to be raised.
   *
   * @return a string
   */
  public String getExpression() {
    return expression;
  }
}
