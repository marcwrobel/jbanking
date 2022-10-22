package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.internal.Normalizer.trimUpperCase;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.checkdigit.IbanCheckDigit;
import java.io.Serializable;
import java.util.Optional;

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
 * Except the national check digit, which is pretty common, the following information were considered too specific to be
 * part of the jbanking API :
 * <ul>
 * <li>account type (Bulgaria, Brasil, Guatemala),</li>
 * <li>account currency (Guatemala, Mauritius, Seychelles),</li>
 * <li>balance account number (Belarus),</li>
 * <li>identification number (Iceland),</li>
 * <li>owner account number (Brasil),</li>
 * <li>reserved characters (Costa Rica, Mauritius).</li>
 * </ul>
 *
 * <p>
 * This class handles validation of the check digit and validation of the {@link BbanStructure BBAN structure}.
 *
 * <p>
 * Instances of this class are immutable and thread-safe.
 *
 * <p>
 * This class implements {@link Serializable} for convenience, but you are encouraged to use the {@link #toString()
 * normalized string representation} if possible. Note that no validity check is done during deserialization.
 *
 * <p>
 * Usage:
 *
 * <pre>
 * // Validate an IBAN
 * Assertions.assertTrue(Iban.isValid(" fr2531682128768051490609537 "));
 *
 * // Get IBAN information
 * Iban iban = new Iban(" fr2531682128768051490609537 ");
 * Assertions.assertEquals("FR2531682128768051490609537", iban.toString());
 * Assertions.assertEquals("FR25 3168 2128 7680 5149 0609 537", iban.toPrintableString());
 * Assertions.assertEquals("FR", iban.getCountryCode());
 * Assertions.assertEquals("25", iban.getCheckDigit());
 * Assertions.assertEquals("31682128768051490609537", iban.getBban());
 * Assertions.assertEquals("31682", iban.getBankIdentifier());
 * Assertions.assertEquals("12876", iban.getBranchIdentifier().get());
 * Assertions.assertEquals("80514906095", iban.getAccountNumber());
 * Assertions.assertEquals("37", iban.getNationalCheckDigit().get());
 * </pre>
 *
 * @see BbanStructure
 * @see <a href=
 *      "https://wikipedia.org/wiki/International_Bank_Account_Number">https://wikipedia.org/wiki/International_Bank_Account_Number</a>
 * @see <a href="https://www.iso13616.org">https://www.iso13616.org</a>
 * @since 1.0
 */
public final class Iban implements Serializable {

  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 1;

  /**
   * A simple regex that validate well-formed IBANs.
   *
   * <p>
   * All strings accepted by {@link #isValid(String)} are also accepted by this regex.
   */
  @SuppressWarnings("unused") // kept for documentation purposes
  public static final String REGEX = "\\s*[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]+\\s*";

  private static final int MIN_LENGTH = 5;
  private static final int COUNTRY_CODE_INDEX = 0;
  private static final int COUNTRY_CODE_LENGTH = 2;
  private static final int CHECK_DIGITS_INDEX = COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH;
  private static final int CHECK_DIGITS_LENGTH = 2;
  private static final int BBAN_INDEX = CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH;

  private static final int GROUP_SIZE_FOR_PRINTABLE_IBAN = 4;

  /**
   * The normalized form of this IBAN.
   */
  private final String value;

  /**
   * This IBAN BBAN structure.
   */
  private final BbanStructure structure;

  /**
   * Create a new IBAN from the given country code and BBAN.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param country a non-null IsoCountry
   * @param bban a non-null string
   * @throws IllegalArgumentException if either the IsoCountry or BBAN is {@code null}
   * @throws IbanFormatException if a valid IBAN could not be calculated using the given IsoCountry and BBAN
   */
  public Iban(IsoCountry country, String bban) {
    if (country == null) {
      throw new IllegalArgumentException("country cannot be null");
    }

    if (bban == null) {
      throw new IllegalArgumentException("bban cannot be null");
    }

    String normalizedBban = trimUpperCase(bban);
    String normalized = country.getAlpha2Code() + "00" + normalizedBban;

    Optional<BbanStructure> oStructure = BbanStructure.forCountry(country);
    if (!oStructure.isPresent()) {
      throw IbanFormatException.forNotSupportedCountry(bban, country);
    }

    structure = oStructure.get();
    if (!structure.isBbanValid(normalizedBban)) {
      throw IbanFormatException.forInvalidBbanStructure(bban, structure);
    }

    String checkDigit = IbanCheckDigit.INSTANCE.calculate(normalized);

    this.value = country.getAlpha2Code() + checkDigit + normalizedBban;
  }

  /**
   * Create a new IBAN from the given string.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param s a non-null string
   * @throws IllegalArgumentException if the given string is {@code null}
   * @throws IbanFormatException if the given string is not a valid IBAN
   */
  public Iban(String s) {
    if (s == null) {
      throw new IllegalArgumentException("s cannot be null");
    }

    String normalized = trimUpperCase(s);
    if (normalized.length() < MIN_LENGTH) {
      throw IbanFormatException.forInvalidLength(s);
    }

    Optional<IsoCountry> country = findCountryFor(normalized);
    if (!country.isPresent()) {
      throw IbanFormatException.forUnknownCountry(s);
    }

    Optional<BbanStructure> oStructure = BbanStructure.forCountry(country.get());
    if (!oStructure.isPresent()) {
      throw IbanFormatException.forNotSupportedCountry(s, country.get());
    }

    structure = oStructure.get();
    if (!structure.isBbanValid(normalized.substring(BBAN_INDEX))) {
      throw IbanFormatException.forInvalidBbanStructure(s, structure);
    }

    if (!IbanCheckDigit.INSTANCE.validate(normalized)) {
      throw IbanFormatException.forIncorrectCheckDigits(s);
    }

    this.value = normalized;
  }

  /**
   * Validates the given IBAN String.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param s a string, may be {@code null}
   * @return {@code true} if the given String is a valid IBAN, {@code false} otherwise
   */
  public static boolean isValid(String s) {
    if (s == null) {
      return false;
    }

    String normalized = trimUpperCase(s);
    if (normalized.length() < MIN_LENGTH) {
      return false;
    }

    Optional<IsoCountry> country = findCountryFor(normalized);
    if (!country.isPresent()) {
      return false;
    }

    Optional<BbanStructure> oStructure = BbanStructure.forCountry(country.get());
    if (!oStructure.isPresent()) {
      return false;
    }

    BbanStructure structure = oStructure.get();
    if (!structure.isBbanValid(normalized.substring(BBAN_INDEX))) {
      return false;
    }

    return IbanCheckDigit.INSTANCE.validate(normalized);
  }

  private static Optional<IsoCountry> findCountryFor(String iban) {
    return IsoCountry.fromAlpha2Code(iban.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH));
  }

  /**
   * Extract the ISO 3166-1-alpha-2 country code from this IBAN.
   *
   * @return a non-null string representing this IBAN ISO 3166-1-alpha-2 country code
   */
  public String getCountryCode() {
    return getCountry().getAlpha2Code();
  }

  /**
   * Gets this IBAN {@link IsoCountry}.
   *
   * @return a non-null {@link IsoCountry}
   */
  public IsoCountry getCountry() {
    return structure.getCountry();
  }

  /**
   * Extract the check digit from this IBAN.
   *
   * @return a non-null string
   */
  public String getCheckDigit() {
    return value.substring(CHECK_DIGITS_INDEX, CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH);
  }

  /**
   * Extract the BBAN from this IBAN.
   *
   * @return a non-null string
   */
  public String getBban() {
    return value.substring(BBAN_INDEX);
  }

  /**
   * Extract the bank identifier (also known as bank code) from this IBAN.
   *
   * @return a non-null string
   * @since 4.0.0
   */
  public String getBankIdentifier() {
    int from = structure.getBankIdentifierStartIndexInclusive();
    int to = structure.getBankIdentifierEndIndexExclusive();

    return value.substring(BBAN_INDEX + from, BBAN_INDEX + to);
  }

  /**
   * Extract the branch identifier (also known as branch code) from this IBAN, if possible.
   *
   * @return a non-null optional string
   * @since 4.0.0
   */
  public Optional<String> getBranchIdentifier() {
    Optional<Integer> from = structure.getBranchIdentifierStartIndexInclusive();
    Optional<Integer> to = structure.getBranchIdentifierEndIndexExclusive();

    if (from.isPresent() && to.isPresent()) {
      return Optional.of(value.substring(BBAN_INDEX + from.get(), BBAN_INDEX + to.get()));
    }

    return Optional.empty();
  }

  /**
   * Extract the national check digit (also known as check character) from this IBAN, if possible.
   *
   * <p>
   * The type of the check digit and the algorithm used to compute it varies by country (RIB key in France, CIN in
   * Italy...).
   *
   * @return a non-null optional string
   * @since 4.0.0
   */
  public Optional<String> getNationalCheckDigit() {
    Optional<Integer> from = structure.getNationalCheckDigitStartIndexInclusive();
    Optional<Integer> to = structure.getNationalCheckDigitEndIndexExclusive();

    if (from.isPresent() && to.isPresent()) {
      return Optional.of(value.substring(BBAN_INDEX + from.get(), BBAN_INDEX + to.get()));
    }

    return Optional.empty();
  }

  /**
   * Extract the national account number from this IBAN.
   *
   * @return a non-null string representing this IBAN account number
   * @since 4.0.0
   */
  public String getAccountNumber() {
    int from = structure.getAccountNumberStartIndexInclusive();
    int to = structure.getAccountNumberEndIndexExclusive();

    return value.substring(BBAN_INDEX + from, BBAN_INDEX + to);
  }

  /**
   * Gets the printable version of this IBAN.
   *
   * <p>
   * When printed on paper, the IBAN is expressed in groups of four characters separated by a single space, the last group being
   * of variable length
   *
   * @return a non-null string representing this IBAN formatted for printing
   */
  public String toPrintableString() {
    StringBuilder printable = new StringBuilder(value);
    int length = value.length();

    for (int i = 0; i < length / GROUP_SIZE_FOR_PRINTABLE_IBAN; i++) {
      printable.insert((i + 1) * GROUP_SIZE_FOR_PRINTABLE_IBAN + i, ' ');
    }

    return printable.toString();
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
  public String toString() {
    return value;
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
    return value.equals(other.value);
  }

  @Override
  public int hashCode() {
    return 29 * value.hashCode();
  }
}
