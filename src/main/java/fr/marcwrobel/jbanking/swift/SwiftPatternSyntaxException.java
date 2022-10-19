package fr.marcwrobel.jbanking.swift;

/**
 * Thrown to indicate a syntax error in a SWIFT expression pattern.
 *
 * @see SwiftPattern
 * @since 1.0
 */
public class SwiftPatternSyntaxException extends RuntimeException {

  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 0;

  /**
   * The string that caused the error.
   */
  private final String expression;

  /**
   * Constructs a {@code SwiftPatternSyntaxException} with the expression that caused the error and the given detail message.
   *
   * @param expression a string
   */
  public SwiftPatternSyntaxException(String expression, String cause) {
    super(String.format("the expression '%s' is invalid : %s", expression, cause));
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
