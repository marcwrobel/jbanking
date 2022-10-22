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
  private static final long serialVersionUID = 0;

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
  private final String normalizedCi;

  /**
   * Create a new Creditor Identifier from the given string.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param creditorIdentifier A non-null String.
   * @throws IllegalArgumentException if the given string is {@code null}
   * @throws CreditorIdentifierFormatException if the given string does not match {@value #REGEX} or if its country code
   *         is not known in {@link fr.marcwrobel.jbanking.IsoCountry} or if its check digit is wrong
   */
  public CreditorIdentifier(String creditorIdentifier) {
    if (creditorIdentifier == null) {
      throw new IllegalArgumentException("the creditor identifier argument cannot be null");
    }

    String normalizedCreditorIdentifier = trimUpperCase(creditorIdentifier);
    if (!isWellFormatted(normalizedCreditorIdentifier)) {
      throw CreditorIdentifierFormatException.forNotProperlyFormattedInput(normalizedCreditorIdentifier);
    }

    Optional<IsoCountry> country = findCountryFor(normalizedCreditorIdentifier);
    if (!country.isPresent()) {
      throw CreditorIdentifierFormatException.forUnknownCountry(creditorIdentifier);
    }

    String normalizedCreditorIdWithoutBusinessCode = removeBusinessCode(normalizedCreditorIdentifier);
    if (!IbanCheckDigit.INSTANCE.validate(normalizedCreditorIdWithoutBusinessCode)) {
      throw CreditorIdentifierFormatException.forIncorrectCheckDigits(creditorIdentifier);
    }

    this.normalizedCi = normalizedCreditorIdentifier;
  }

  /**
   * Create a new Creditor Identifier from the given country code, the creditor business code and the creditor national
   * identifier.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param country A non-null IsoCountry.
   * @param businessCode A non-null String.
   * @param creditorNationalId A non-null String.
   * @throws IllegalArgumentException if either of the given strings is null
   * @throws CreditorIdentifierFormatException if the resulting creditor identifier does not match {@value #REGEX}.
   */
  public CreditorIdentifier(IsoCountry country, String businessCode, String creditorNationalId) {
    if (country == null) {
      throw new IllegalArgumentException("the country argument cannot be null");
    }

    if (businessCode == null) {
      throw new IllegalArgumentException("the business code argument cannot be null");
    }

    if (creditorNationalId == null) {
      throw new IllegalArgumentException("the creditorNationalId argument cannot be null");
    }

    String normalizedBusinessCode = trimUpperCase(businessCode);
    String normalizedNationalId = trimUpperCase(creditorNationalId);
    String normalizedCreditorId = country.getAlpha2Code() + "00" + normalizedNationalId;
    if (!isWellFormatted(normalizedCreditorId)) {
      throw CreditorIdentifierFormatException.forNotProperlyFormattedInput(creditorNationalId);
    }

    String checkDigits = IbanCheckDigit.INSTANCE.calculate(normalizedCreditorId);

    this.normalizedCi = country.getAlpha2Code() + checkDigits + normalizedBusinessCode + normalizedNationalId;
  }

  private static boolean isWellFormatted(String s) {
    int length = s.length();
    if (length < MIN_LENGTH) {
      return false;
    }

    for (int i = COUNTRY_CODE_INDEX; i < COUNTRY_CODE_LENGTH; i++) {
      if (!AsciiCharacters.isAlphabetic(s.charAt(i))) {
        return false;
      }
    }

    for (int i = CHECK_DIGITS_INDEX; i < CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH; i++) {
      if (!AsciiCharacters.isNumeric(s.charAt(i))) {
        return false;
      }
    }

    for (int i = CREDITOR_BUSINESS_CODE_INDEX; i < length; i++) {
      if (!AsciiCharacters.isAlphanumeric(s.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  private static Optional<IsoCountry> findCountryFor(String creditorIdentifier) {
    return IsoCountry.fromAlpha2Code(
        creditorIdentifier.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH));
  }

  private static String removeBusinessCode(String creditorIdentifier) {
    return creditorIdentifier.substring(COUNTRY_CODE_INDEX, CREDITOR_BUSINESS_CODE_INDEX)
        + creditorIdentifier.substring(CREDITOR_NATIONAL_ID_INDEX);
  }

  /**
   * Check whether the given string is a valid creditor identifier.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param creditorIdentifier a String.
   * @return {@code true} if the given String is a valid Creditor Identifier, {@code false} otherwise.
   */
  public static boolean isValid(String creditorIdentifier) {
    if (creditorIdentifier == null) {
      return false;
    }

    String normalizedCreditorId = trimUpperCase(creditorIdentifier);
    if (!isWellFormatted(normalizedCreditorId)) {
      return false;
    }

    Optional<IsoCountry> country = findCountryFor(normalizedCreditorId);
    if (!country.isPresent()) {
      return false;
    }

    String normalizedCreditorIdWithoutBusinessCode = removeBusinessCode(normalizedCreditorId);

    return IbanCheckDigit.INSTANCE.validate(normalizedCreditorIdWithoutBusinessCode);
  }

  /**
   * Extract the ISO 3166-1-alpha-2 country code from this Creditor Identifier.
   *
   * @return A non-null string representing this Creditor Identifier ISO 3166-1-alpha-2 country code.
   */
  public String getCountryCode() {
    return getCountry().getAlpha2Code();
  }

  /**
   * Gets this Creditor Identifier {@link IsoCountry}.
   *
   * @return A non-null {@link IsoCountry}.
   */
  public IsoCountry getCountry() {
    return findCountryFor(normalizedCi).orElseThrow(() -> new IllegalStateException("a valid CI should have a country code"));
  }

  /**
   * Extract the check digit from this Creditor Identifier.
   *
   * @return A non-null string representing this Creditor Identifier check digit.
   */
  public String getCheckDigit() {
    return normalizedCi.substring(CHECK_DIGITS_INDEX, CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH);
  }

  /**
   * Extract the business code from this Creditor Identifier.
   *
   * @return A non-null string representing this Creditor Identifier business code.
   */
  public String getBusinessCode() {
    return normalizedCi.substring(CREDITOR_BUSINESS_CODE_INDEX,
        CREDITOR_BUSINESS_CODE_INDEX + CREDITOR_BUSINESS_CODE_LENGTH);
  }

  /**
   * Extract the creditor national identifier from this Creditor Identifier.
   *
   * @return A non-null string representing this Creditor Identifier National ID.
   */
  public String getNationalIdentifier() {
    return normalizedCi.substring(CREDITOR_NATIONAL_ID_INDEX);
  }

  @Override
  public String toString() {
    return normalizedCi;
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

    return normalizedCi.equals(other.normalizedCi);
  }

  @Override
  public int hashCode() {
    return normalizedCi.hashCode();
  }
}
