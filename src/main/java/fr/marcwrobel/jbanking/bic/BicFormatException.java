package fr.marcwrobel.jbanking.bic;

/**
 * Thrown to indicate that an attempt has been made to convert a string to a {@link Bic}, but that the string does not have the
 * appropriate format.
 *
 * @see Bic#Bic(String)
 * @since 1.0
 */
public final class BicFormatException extends RuntimeException {

  /** Serialization version. */
  private static final long serialVersionUID = 0;

  private final String inputString;

  /**
   * Constructs a {@code BicFormatException} with the string that caused the error and the given detail message.
   *
   * @param input a string
   * @param message a string
   */
  private BicFormatException(String input, String message) {
    super(message);
    this.inputString = input;
  }

  static BicFormatException forNotProperlyFormattedInput(String input) {
    return new BicFormatException(input, String.format("'%s' format is not appropriate for a BIC", input));
  }

  static BicFormatException forUnknownCountryCode(String input) {
    return new BicFormatException(input, String.format("'%s' country code is unknown", input));
  }

  /**
   * Returns the input String that caused this exception to be raised.
   *
   * @return a string
   */
  public String getInputString() {
    return inputString;
  }
}
