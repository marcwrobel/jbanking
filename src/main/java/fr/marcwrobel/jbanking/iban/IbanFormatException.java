package fr.marcwrobel.jbanking.iban;

import fr.marcwrobel.jbanking.IsoCountry;

/**
 * Thrown to indicate that an attempt has been made to convert a string to a {@link Iban}, but that the string does not have the
 * appropriate format.
 *
 * @author Marc Wrobel
 * @see Iban#Iban(String)
 * @since 1.0
 */
public final class IbanFormatException extends RuntimeException {

  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 0;

  private final String inputString;

  /**
   * Constructs a {@code IbanFormatException} with the string that caused the error and the given detail message.
   *
   * @param input a string
   * @param message a string
   */
  private IbanFormatException(String input, String message) {
    super(message);
    this.inputString = input;
  }

  static IbanFormatException forNotProperlyFormattedInput(String input) {
    return new IbanFormatException(input, String.format("'%s' format is not appropriate for an IBAN", input));
  }

  static IbanFormatException forIncorrectCheckDigits(String input) {
    return new IbanFormatException(input, String.format("'%s' check digits are incorrect", input));
  }

  static IbanFormatException forUnknownCountry(String input) {
    return new IbanFormatException(input,
        String.format("'%s' country code is not an ISO 3166-1-alpha-2 code", input));
  }

  static IbanFormatException forNotSupportedCountry(String input, IsoCountry country) {
    return new IbanFormatException(input, String.format("'%s' country does not support IBAN", country));
  }

  static IbanFormatException forInvalidBbanStructure(String input, BbanStructure bbanStructure) {
    return new IbanFormatException(input,
        String.format("'%s' BBAN structure is not valid against BBAN structure used in %s", input,
            bbanStructure.getCountry()));
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
