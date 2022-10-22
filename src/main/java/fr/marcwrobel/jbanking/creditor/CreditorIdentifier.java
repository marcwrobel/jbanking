package fr.marcwrobel.jbanking.creditor;

import static fr.marcwrobel.jbanking.internal.Normalizer.trimUpperCase;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.checkdigit.IbanCheckDigit;
import fr.marcwrobel.jbanking.internal.AsciiCharacters;
import java.io.Serializable;
import java.util.Optional;

/**
 * A Creditor Identifier (CI) code as specified by the
 * <a href="https://www.europeanpaymentscouncil.eu/document-library/guidance-documents/creditor-identifier-overview">
 * EPC</a>.
 *
 * <p>
 * The CI structure is:
 *
 * <ul>
 * <li>Position 1-2 filled with the ISO country code.
 * <li>Position 3-4 filled with the check digit according to ISO 7064 Mod 97-10.
 * <li>Position 5-7 filled with the Creditor Business Code, if not used then filled with ZZZ.
 * <li>Position 8 onwards filled with the country specific part of the identifier being a national identifier of the
 * Creditor, as defined by the National Community.
 * </ul>
 *
 * <p>
 * This class handles validation of the check digit and validation of the Creditor Identifier Structure described above
 * without going into the validation of the national identifier.
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
 * // Validate a creditor identifier
 * Assertions.assertTrue(CreditorIdentifier.isValid(" fr72zzz123456 "));
 *
 * // Get creditor identifier information
 * CreditorIdentifier ci = new CreditorIdentifier(" fr72zzz123456 ");
 * Assertions.assertEquals("FR72ZZZ123456", ci.toString());
 * Assertions.assertEquals("FR", ci.getCountryCode());
 * Assertions.assertEquals("72", ci.getCheckDigit());
 * Assertions.assertEquals("ZZZ", ci.getBusinessCode());
 * Assertions.assertEquals("123456", ci.getNationalIdentifier());
 * </pre>
 *
 * @see <a href="https://www.europeanpaymentscouncil.eu/document-library/guidance-documents/creditor-identifier-overview">
 *      EPC Creditor Identifier Overview</a>
 */
public final class CreditorIdentifier implements Serializable {

  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 1;

  /**
   * A simple regex that validate well-formed Creditor Identifiers.
   *
   * <p>
   * All strings accepted by {@link #isValid(String)} are also accepted by this regex.
   */
  @SuppressWarnings("unused") // kept for documentation purposes
  public static final String REGEX = "\\s*[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{3}[a-zA-Z0-9]+\\s*";

  private static final int MIN_LENGTH = 8;
  private static final int COUNTRY_CODE_INDEX = 0;
  private static final int COUNTRY_CODE_LENGTH = 2;
  private static final int CHECK_DIGITS_INDEX = COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH;
  private static final int CHECK_DIGITS_LENGTH = 2;
  private static final int CREDITOR_BUSINESS_CODE_INDEX = CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH;
  private static final int CREDITOR_BUSINESS_CODE_LENGTH = 3;
  private static final int CREDITOR_NATIONAL_ID_INDEX = CREDITOR_BUSINESS_CODE_INDEX + CREDITOR_BUSINESS_CODE_LENGTH;

  /**
   * The normalized form of this Creditor Identifier.
   */
  private final String value;

  /**
   * Create a new Creditor Identifier from the given string.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param s a non-null string
   * @throws IllegalArgumentException if the given string is {@code null}
   * @throws CreditorIdentifierFormatException if the given string does not match {@value #REGEX} or if its country code
   *         is not known in {@link fr.marcwrobel.jbanking.IsoCountry} or if its check digit is wrong
   */
  public CreditorIdentifier(String s) {
    if (s == null) {
      throw new IllegalArgumentException("s cannot be null");
    }

    String normalized = trimUpperCase(s);
    if (!isWellFormatted(normalized)) {
      throw CreditorIdentifierFormatException.forNotProperlyFormattedInput(s);
    }

    Optional<IsoCountry> country = findCountryFor(normalized);
    if (!country.isPresent()) {
      throw CreditorIdentifierFormatException.forUnknownCountry(s);
    }

    String normalizedWithoutBusinessCode = removeBusinessCode(normalized);
    if (!IbanCheckDigit.INSTANCE.validate(normalizedWithoutBusinessCode)) {
      throw CreditorIdentifierFormatException.forIncorrectCheckDigits(s);
    }

    this.value = normalized;
  }

  /**
   * Create a new Creditor Identifier from the given country code, the creditor business code and the creditor national
   * identifier.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param country a non-null IsoCountry
   * @param businessCode a non-null string
   * @param nationalId a non-null string
   * @throws IllegalArgumentException if either of the given strings is null
   * @throws CreditorIdentifierFormatException if the resulting creditor identifier does not match {@value #REGEX}
   */
  public CreditorIdentifier(IsoCountry country, String businessCode, String nationalId) {
    if (country == null) {
      throw new IllegalArgumentException("country cannot be null");
    }

    if (businessCode == null) {
      throw new IllegalArgumentException("businessCode cannot be null");
    }

    if (nationalId == null) {
      throw new IllegalArgumentException("nationalId cannot be null");
    }

    String normalizedBusinessCode = trimUpperCase(businessCode);
    String normalizedNationalId = trimUpperCase(nationalId);

    String normalized = country.getAlpha2Code() + "00" + normalizedNationalId;
    if (!isWellFormatted(normalized)) {
      throw CreditorIdentifierFormatException.forNotProperlyFormattedInput(nationalId);
    }

    String checkDigit = IbanCheckDigit.INSTANCE.calculate(normalized);

    this.value = country.getAlpha2Code() + checkDigit + normalizedBusinessCode + normalizedNationalId;
  }

  private static boolean isWellFormatted(String ci) {
    int length = ci.length();
    if (length < MIN_LENGTH) {
      return false;
    }

    for (int i = COUNTRY_CODE_INDEX; i < COUNTRY_CODE_LENGTH; i++) {
      if (!AsciiCharacters.isAlphabetic(ci.charAt(i))) {
        return false;
      }
    }

    for (int i = CHECK_DIGITS_INDEX; i < CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH; i++) {
      if (!AsciiCharacters.isNumeric(ci.charAt(i))) {
        return false;
      }
    }

    for (int i = CREDITOR_BUSINESS_CODE_INDEX; i < length; i++) {
      if (!AsciiCharacters.isAlphanumeric(ci.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  private static Optional<IsoCountry> findCountryFor(String ci) {
    return IsoCountry.fromAlpha2Code(
        ci.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH));
  }

  private static String removeBusinessCode(String ci) {
    return ci.substring(COUNTRY_CODE_INDEX, CREDITOR_BUSINESS_CODE_INDEX) + ci.substring(CREDITOR_NATIONAL_ID_INDEX);
  }

  /**
   * Check whether the given string is a valid creditor identifier.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param s a string, may be {@code null}
   * @return {@code true} if the given String is a valid Creditor Identifier, {@code false} otherwise
   */
  public static boolean isValid(String s) {
    if (s == null) {
      return false;
    }

    String normalized = trimUpperCase(s);
    if (!isWellFormatted(normalized)) {
      return false;
    }

    Optional<IsoCountry> country = findCountryFor(normalized);
    if (!country.isPresent()) {
      return false;
    }

    String normalizedCreditorIdWithoutBusinessCode = removeBusinessCode(normalized);

    return IbanCheckDigit.INSTANCE.validate(normalizedCreditorIdWithoutBusinessCode);
  }

  /**
   * Extract the ISO 3166-1-alpha-2 country code from this Creditor Identifier.
   *
   * @return a non-null string representing this Creditor Identifier ISO 3166-1-alpha-2 country code
   */
  public String getCountryCode() {
    return getCountry().getAlpha2Code();
  }

  /**
   * Gets this Creditor Identifier {@link IsoCountry}.
   *
   * @return a non-null {@link IsoCountry}
   */
  public IsoCountry getCountry() {
    return findCountryFor(value).orElseThrow(() -> new IllegalStateException("a valid CI should have a country code"));
  }

  /**
   * Extract the check digit from this Creditor Identifier.
   *
   * @return a non-null string representing this Creditor Identifier check digit
   */
  public String getCheckDigit() {
    return value.substring(CHECK_DIGITS_INDEX, CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH);
  }

  /**
   * Extract the business code from this Creditor Identifier.
   *
   * @return a non-null string representing this Creditor Identifier business code
   */
  public String getBusinessCode() {
    return value.substring(CREDITOR_BUSINESS_CODE_INDEX,
        CREDITOR_BUSINESS_CODE_INDEX + CREDITOR_BUSINESS_CODE_LENGTH);
  }

  /**
   * Extract the creditor national identifier from this Creditor Identifier.
   *
   * @return a non-null string representing this Creditor Identifier National ID
   */
  public String getNationalIdentifier() {
    return value.substring(CREDITOR_NATIONAL_ID_INDEX);
  }

  /**
   * Returns a normalized string representation of this Creditor Identifier.
   *
   * <p>
   * Normalized means the string is:
   *
   * <ul>
   * <li>made of uppercase characters
   * <li>contains no spaces
   * </ul>
   *
   * @return a normalized string representation of this Creditor Identifier
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

    CreditorIdentifier other = (CreditorIdentifier) o;

    return value.equals(other.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
