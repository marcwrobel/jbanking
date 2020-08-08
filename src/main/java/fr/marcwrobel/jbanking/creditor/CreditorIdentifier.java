package fr.marcwrobel.jbanking.creditor;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.iban.IbanCheckDigit;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * A Creditor Identifier (CI) Code as specified by the <a
 * href="http://www.europeanpaymentscouncil.eu/index.cfm/knowledge-bank/epc-documents/creditor-identifier-overview/">EPC</a>.
 *
 * <p>CI structure:
 *
 * <ul>
 *   <li>Position 1-2 filled with the ISO country code.
 *   <li>Position 3-4 filled with the check digit according to ISO 7064 Mod 97-10.
 *   <li>Position 5-7 filled with the Creditor Business Code, if not used then filled with ZZZ.
 *   <li>Position 8 onwards filled with the country specific part of the identifier being a national
 *       identifier of the Creditor, as defined by the National Community.
 * </ul>
 *
 * <p>This class handles validation of the check digit and validation of the Creditor Identifier
 * Structure described above without going into the validation of the national identifier.
 *
 * <p>Instances of this class are immutable and are safe for use by multiple concurrent threads.
 *
 * @author Charles Kayser
 * @see <a
 *     href="http://www.europeanpaymentscouncil.eu/index.cfm/knowledge-bank/epc-documents/creditor-identifier-overview/">EPC
 *     Creditor Identifier Overview</a>
 */
public class CreditorIdentifier {

  private static final String BASIC_REGEX = "[A-Za-z]{2}[0-9]{2}[A-Za-z0-9]{3}[A-Za-z0-9]+";
  private static final Pattern BASIC_PATTERN = Pattern.compile(BASIC_REGEX);

  private static final int COUNTRY_CODE_INDEX = 0;
  private static final int COUNTRY_CODE_LENGTH = 2;
  private static final int CHECK_DIGITS_INDEX = COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH;
  private static final int CHECK_DIGITS_LENGTH = 2;
  private static final int CREDITOR_BUSINESS_CODE_INDEX = CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH;
  private static final int CREDITOR_BUSINESS_CODE_LENGTH = 3;
  private static final int CREDITOR_NATIONAL_ID_INDEX =
      CREDITOR_BUSINESS_CODE_INDEX + CREDITOR_BUSINESS_CODE_LENGTH;

  private final String creditorId;

  /**
   * Create a new Creditor Identifier from the given country code, the creditor business code and
   * the creditor national id.
   *
   * @param country A non null IsoCountry.
   * @param businessCode A non null String.
   * @param creditorNationalId A non null String.
   * @throws IllegalArgumentException if either the IsoCountry or BBAN is null
   * @throws fr.marcwrobel.jbanking.creditor.CreditorIdentifierFormatException if a valid Creditor
   *     Identifier could not be calculated using the given IsoCountry, business code and creditor
   *     national id
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

    String normalizedNationalId = normalize(creditorNationalId);
    String normalizedCreditorId = country.getAlpha2Code() + "00" + normalizedNationalId;

    if (isNotWellFormatted(normalizedCreditorId)) {
      throw CreditorIdentifierFormatException.forNotProperlyFormattedInput(creditorNationalId);
    }

    String checkDigits = IbanCheckDigit.INSTANCE.calculate(normalizedCreditorId);

    this.creditorId = country.getAlpha2Code() + checkDigits + businessCode + normalizedNationalId;
  }

  /**
   * Create a new creditor identifier from the given string.
   *
   * @param creditorId a non null String.
   */
  public CreditorIdentifier(String creditorId) {
    if (creditorId == null) {
      throw new IllegalArgumentException("the creditor identifier argument cannot be null");
    }

    String normalizedCreditorId = normalize(creditorId);

    if (isNotWellFormatted(normalizedCreditorId)) {
      throw CreditorIdentifierFormatException.forNotProperlyFormattedInput(normalizedCreditorId);
    }

    Optional<IsoCountry> country = findCountryFor(normalizedCreditorId);
    if (!country.isPresent()) {
      throw CreditorIdentifierFormatException.forUnknownCountry(creditorId);
    }

    String normalizedCreditorIdWithoutBusinessCode = removeBusinessCode(normalizedCreditorId);
    if (!IbanCheckDigit.INSTANCE.validate(normalizedCreditorIdWithoutBusinessCode)) {
      throw CreditorIdentifierFormatException.forIncorrectCheckDigits(creditorId);
    }

    this.creditorId = normalizedCreditorId;
  }

  /**
   * Returns a normalized string representation of the given Creditor Identifier.
   *
   * <p>
   *
   * <p>Normalized means the string is:
   *
   * <ul>
   *   <li>made of uppercase characters
   *   <li>contains no spaces
   * </ul>
   */
  private static String normalize(String creditorIdentifier) {
    return creditorIdentifier.replaceAll("\\s+", "").toUpperCase();
  }

  /**
   * Check if the given string matches the basic format of a Creditor Identifier.
   *
   * <p>Returns {@code true} if the given strings matches the following pattern:
   *
   * <ul>
   *   <li>Position 1-2 filled with alphabetic values (the ISO country code).
   *   <li>Position 3-4 filled with numeric values (the check digits).
   *   <li>Position 5-7 filled with alpha-numeric values (the Creditor Business Code).
   *   <li>Position 8 onwards filled with alpha-numeric values (a national identifier of the
   *       Creditor).
   * </ul>
   */
  private static boolean isNotWellFormatted(String creditorIdentifier) {
    return !BASIC_PATTERN.matcher(creditorIdentifier).matches();
  }

  /**
   * Returns the {@code Country} reference from the given Creditor Identifier string.
   *
   * <p>Returns null if not found.
   */
  private static Optional<IsoCountry> findCountryFor(String creditorIdentifier) {
    return IsoCountry.fromAlpha2Code(
        creditorIdentifier.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH));
  }

  /** Removes the business code part from the given Creditor Identifier string. */
  private static String removeBusinessCode(String creditorIdentifier) {
    return creditorIdentifier.substring(COUNTRY_CODE_INDEX, CREDITOR_BUSINESS_CODE_INDEX)
        + creditorIdentifier.substring(CREDITOR_NATIONAL_ID_INDEX);
  }

  /**
   * Validates the given Creditor Identifier String.
   *
   * @param creditorIdentifier A String.
   * @return {@code true} if the given String is a valid Creditor Identifier, {@code false}
   *     otherwise.
   */
  public static boolean isValid(String creditorIdentifier) {
    if (creditorIdentifier == null) {
      return false;
    }

    String normalizedCreditorId = normalize(creditorIdentifier);

    if (isNotWellFormatted(normalizedCreditorId)) {
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
   * @return A non null string representing this Creditor Identifier ISO 3166-1-alpha-2 country
   *     code.
   */
  public String getCountryCode() {
    return creditorId.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
  }

  /**
   * Extract the check digit from this Creditor Identifier.
   *
   * @return A non null string representing this Creditor Identifier check digit.
   */
  public String getCheckDigit() {
    return creditorId.substring(CHECK_DIGITS_INDEX, CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH);
  }

  /**
   * Extract the business code from this Creditor Identifier.
   *
   * @return A non null string representing this Creditor Identifier business code.
   */
  public String getBusinessCode() {
    return creditorId.substring(
        CREDITOR_BUSINESS_CODE_INDEX, CREDITOR_BUSINESS_CODE_INDEX + CREDITOR_BUSINESS_CODE_LENGTH);
  }

  /**
   * Extract the creditor national identifier from this Creditor Identifier.
   *
   * @return A non null string representing this Creditor Identifier National Id.
   */
  public String getNationalIdentifier() {
    return creditorId.substring(CREDITOR_NATIONAL_ID_INDEX);
  }

  @Override
  public String toString() {
    return creditorId;
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

    return creditorId.equals(other.creditorId);
  }

  @Override
  public int hashCode() {
    return creditorId.hashCode();
  }
}
