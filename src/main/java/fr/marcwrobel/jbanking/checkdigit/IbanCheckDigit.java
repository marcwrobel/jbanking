package fr.marcwrobel.jbanking.checkdigit;

/**
 * Provide ISO 7064 Mod 97,10 IBAN check digit calculation and validation.
 *
 * <p>
 * This class is implementing the singleton pattern by being an enumeration. Algorithm is based on the <a href=
 * "https://git.apache.org/repos/asf?p=commons-validator.git;a=blob;f=src/main/java/org/apache/commons/validator/routines/checkdigit/IBANCheckDigit.java;hb=HEAD">Apache
 * commons-validator's IBANCheckDigit</a>.
 *
 * @see <a href="https://en.wikipedia.org/wiki/ISO/IEC_7064">https://en.wikipedia.org/wiki/ISO/IEC_7064</a>
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/International_Bank_Account_Number">https://en.wikipedia.org/wiki/International_Bank_Account_Number</a>
 * @see <a href=
 *      "https://git.apache.org/repos/asf?p=commons-validator.git;a=blob;f=src/main/java/org/apache/commons/validator/routines/checkdigit/IBANCheckDigit.java;hb=HEAD">https://git.apache.org/repos/asf?p=commons-validator.git;a=blob;f=src/main/java/org/apache/commons/validator/routines/checkdigit/IBANCheckDigit.java;hb=HEAD</a>
 * @since 1.0
 */
public enum IbanCheckDigit implements CheckDigit {
  /**
   * The singleton instance for this class.
   */
  INSTANCE;

  private static final int BBAN_INDEX = 4;

  private static final long CHECK_DIGITS_MAX = 999999999;
  private static final long CHECK_DIGITS_MODULUS = 97;
  private static final int CHECK_DIGITS_REMAINDER = 1;

  /**
   * Validate the given IBAN check digit.
   * <p>
   * The behavior of this method changed in 4.0.0: prior to this version this method raised an exception on invalid
   * check digits. Now it is returning {@code false} instead.
   *
   * @param iban a non-null string
   * @return {@code true} if the given IBAN check digit is valid, {@code false} otherwise
   */
  @Override
  public boolean validate(String iban) {
    if (iban == null || iban.length() <= BBAN_INDEX) {
      return false;
    }

    // It is easier to extract the check digit string and compare it with the invalid check digits, but we want to avoid
    // unnecessary objects creation.
    char firstChar = iban.charAt(BBAN_INDEX - 2);
    char secondChar = iban.charAt(BBAN_INDEX - 1);
    if ((firstChar == '0' && (secondChar == '0' || secondChar == '1')) || (firstChar == '9' && secondChar == '9')) {
      return false;
    }

    return modulus(iban) == CHECK_DIGITS_REMAINDER;
  }

  /**
   * Calculate the given IBAN check digit. For a valid calculation the given IBAN its characters have to be alphanumeric
   * ([a-zA-Z0-9]) and check digit characters have to be set to zero.
   *
   * @param iban a non-null string
   * @return the given IBAN check digit
   */
  @Override
  public String calculate(String iban) {
    if (iban == null) {
      throw new IllegalArgumentException("the iban argument cannot be null");
    }

    if (iban.length() <= BBAN_INDEX) {
      throw new IllegalArgumentException("the iban argument size must be greater than " + BBAN_INDEX);
    }

    int modulusResult = modulus(iban);
    int charValue = (98 - modulusResult);
    String checkDigit = Integer.toString(charValue);

    return (charValue > 9 ? checkDigit : "0" + checkDigit);
  }

  private int modulus(String iban) {
    String reformattedIban = iban.substring(BBAN_INDEX) + iban.substring(0, BBAN_INDEX);

    long total = 0;
    for (int i = 0; i < reformattedIban.length(); i++) {
      int charValue = Character.digit(reformattedIban.charAt(i), 36);
      total = (charValue > 9 ? total * 100 : total * 10) + charValue;
      if (total > CHECK_DIGITS_MAX) {
        total = total % CHECK_DIGITS_MODULUS;
      }
    }

    return (int) (total % CHECK_DIGITS_MODULUS);
  }
}
