package fr.marcwrobel.jbanking.iban;

import fr.marcwrobel.jbanking.IsoCountry;
import java.io.Serializable;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * An International Bank Account Number (IBAN) Code as specified by the <a href="https://www.iso13616.org">ISO 13616:2007
 * standard</a>.
 *
 * <p>
 * An IBAN consists of a two-letter ISO 3166-1 country code, followed by two check digits and up to thirty alphanumeric
 * characters for a BBAN (Basic Bank Account Number) which has a fixed length per country and, included within it, a bank
 * identifier with a fixed position and a fixed length per country. The check digits are calculated based on the scheme defined
 * in ISO/IEC 7064 (MOD97-10). Note that an IBAN is case-insensitive.
 *
 * <p>
 * This class handles validation of the check digit and validation of the {@link BbanStructure BBAN structure}.
 *
 * <p>
 * Instances of this class are immutable and thread-safe.
 *
 * <p>
 * Usage:
 * 
 * <pre>
 * // Validate an IBAN
 * Assertions.assertTrue(Iban.isValid("FR2531682128768051490609537"));
 *
 * // Retrieve IBAN information
 * Iban iban = new Iban("fr2531682128768051490609537");
 * Assertions.assertEquals("FR2531682128768051490609537", iban.toString());
 * Assertions.assertEquals("FR", iban.getCountryCode());
 * Assertions.assertEquals("25", iban.getCheckDigit());
 * Assertions.assertEquals("31682128768051490609537", iban.getBban());
 * Assertions.assertEquals("FR25 3168 2128 7680 5149 0609 537", iban.toPrintableString());
 * </pre>
 *
 * @see BbanStructure
 * @see <a href=
 *      "http://wikipedia.org/wiki/International_Bank_Account_Number">http://wikipedia.org/wiki/International_Bank_Account_Number</a>
 * @see <a href="https://www.iso13616.org">https://www.iso13616.org</a>
 * @since 1.0
 */
public final class Iban implements Serializable {

  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 0;

  private static final String BASIC_REGEX = "[A-Za-z]{2}\\d{2}[A-Za-z0-9]+";
  private static final Pattern BASIC_PATTERN = Pattern.compile(BASIC_REGEX);

  private static final int COUNTRY_CODE_INDEX = 0;
  private static final int COUNTRY_CODE_LENGTH = 2;
  private static final int CHECK_DIGITS_INDEX = COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH;
  private static final int CHECK_DIGITS_LENGTH = 2;
  private static final int BBAN_INDEX = CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH;

  private static final int GROUP_SIZE_FOR_PRINTABLE_IBAN = 4;

  /**
   * The normalized form of this IBAN.
   */
  private final String normalizedIban;

  /**
   * Create a new IBAN from the given country code and BBAN.
   *
   * @param country A non-null IsoCountry.
   * @param bban A non-null String.
   * @throws IllegalArgumentException if either the IsoCountry or BBAN is {@code null}
   * @throws IbanFormatException if a valid IBAN could not be calculated using the given IsoCountry and BBAN.
   */
  public Iban(IsoCountry country, String bban) {
    if (country == null) {
      throw new IllegalArgumentException("the country argument cannot be null");
    }

    if (bban == null) {
      throw new IllegalArgumentException("the bban argument cannot be null");
    }

    String normalizedBban = normalize(bban);
    String normalized = country.getAlpha2Code() + "00" + normalizedBban;

    Optional<BbanStructure> oBbanStructure = BbanStructure.forCountry(country);
    if (!oBbanStructure.isPresent()) {
      throw IbanFormatException.forNotSupportedCountry(bban, country);
    }

    BbanStructure bbanStructure = oBbanStructure.get();
    if (!bbanStructure.isBbanValid(normalizedBban)) {
      throw IbanFormatException.forInvalidBbanStructure(bban, bbanStructure);
    }

    String checkDigits = IbanCheckDigit.INSTANCE.calculate(normalized);

    this.normalizedIban = country.getAlpha2Code() + checkDigits + normalizedBban;
  }

  /**
   * Create a new IBAN from the given string.
   *
   * @param iban A non-null String.
   * @throws IllegalArgumentException if the given string is {@code null}
   * @throws IbanFormatException if the given string is not a valid IBAN.
   */
  public Iban(String iban) {
    if (iban == null) {
      throw new IllegalArgumentException("the iban argument cannot be null");
    }

    String normalized = normalize(iban);

    if (isNotWellFormatted(normalized)) {
      throw IbanFormatException.forNotProperlyFormattedInput(normalized);
    }

    Optional<IsoCountry> country = findCountryFor(normalized);
    if (!country.isPresent()) {
      throw IbanFormatException.forUnknownCountry(iban);
    }

    Optional<BbanStructure> oBbanStructure = BbanStructure.forCountry(country.get());
    if (!oBbanStructure.isPresent()) {
      throw IbanFormatException.forNotSupportedCountry(iban, country.get());
    }

    BbanStructure bbanStructure = oBbanStructure.get();
    if (!bbanStructure.isBbanValid(normalized.substring(BBAN_INDEX))) {
      throw IbanFormatException.forInvalidBbanStructure(iban, bbanStructure);
    }

    if (!IbanCheckDigit.INSTANCE.validate(normalized)) {
      throw IbanFormatException.forIncorrectCheckDigits(iban);
    }

    this.normalizedIban = normalized;
  }

  /**
   * Validates the given IBAN String.
   *
   * @param iban A String.
   * @return {@code true} if the given String is a valid IBAN, {@code false} otherwise.
   */
  public static boolean isValid(String iban) {
    if (iban == null) {
      return false;
    }

    String normalized = normalize(iban);

    if (isNotWellFormatted(normalized)) {
      return false;
    }

    Optional<IsoCountry> country = findCountryFor(normalized);
    if (!country.isPresent()) {
      return false;
    }

    Optional<BbanStructure> oBbanStructure = BbanStructure.forCountry(country.get());
    if (!oBbanStructure.isPresent()) {
      return false;
    }

    BbanStructure bbanStructure = oBbanStructure.get();
    if (!bbanStructure.isBbanValid(normalized.substring(BBAN_INDEX))) {
      return false;
    }

    return IbanCheckDigit.INSTANCE.validate(normalized);
  }

  private static String normalize(String iban) {
    return iban.replaceAll("\\s+", "").toUpperCase();
  }

  private static boolean isNotWellFormatted(String s) {
    return !BASIC_PATTERN.matcher(s).matches();
  }

  private static Optional<IsoCountry> findCountryFor(String s) {
    return IsoCountry.fromAlpha2Code(s.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH));
  }

  /**
   * Extract the ISO 3166-1-alpha-2 country code from this IBAN.
   *
   * @return A non-null string representing this IBAN ISO 3166-1-alpha-2 country code.
   */
  public String getCountryCode() {
    return normalizedIban.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
  }

  /**
   * Extract the check digit from this IBAN.
   *
   * @return A non-null string representing this IBAN check digit.
   */
  public String getCheckDigit() {
    return normalizedIban.substring(CHECK_DIGITS_INDEX, CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH);
  }

  /**
   * Extract the BBAN from this IBAN.
   *
   * @return A non-null string representing this IBAN BBAN.
   */
  public String getBban() {
    return normalizedIban.substring(BBAN_INDEX);
  }

  /**
   * Gets the printable version of this IBAN.
   *
   * <p>
   * When printed on paper, the IBAN is expressed in groups of four characters separated by a single space, the last group being
   * of variable length
   *
   * @return A non-null string representing this IBAN formatted for printing.
   */
  public String toPrintableString() {
    StringBuilder printableIban = new StringBuilder(normalizedIban);
    int length = normalizedIban.length();

    for (int i = 0; i < length / GROUP_SIZE_FOR_PRINTABLE_IBAN; i++) {
      printableIban.insert((i + 1) * GROUP_SIZE_FOR_PRINTABLE_IBAN + i, ' ');
    }

    return printableIban.toString();
  }

  @Override
  public String toString() {
    return normalizedIban;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Iban other = (Iban) o;
    return normalizedIban.equals(other.normalizedIban);
  }

  /**
   * Returns a normalized string representation of this IBAN.
   *
   * <p>
   * Normalized means the string is:
   *
   * <ul>
   * <li>made of uppercase characters
   * <li>contains no spaces
   * </ul>
   *
   * @return a normalized string representation of this IBAN
   */
  @Override
  public int hashCode() {
    return 29 * normalizedIban.hashCode();
  }
}
