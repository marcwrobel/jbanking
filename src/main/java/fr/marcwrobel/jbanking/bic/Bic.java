package fr.marcwrobel.jbanking.bic;

import static fr.marcwrobel.jbanking.internal.Normalizer.trimUpperCase;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.internal.AsciiCharacters;
import java.io.Serializable;
import java.util.Optional;

/**
 * A Business Identifier Code (also known as BIC, SWIFT-BIC, BIC code, SWIFT ID or SWIFT code, Business Entity
 * Identifier or BEI) as specified by ISO 9362:2009.
 *
 * <p>
 * A BIC is either eight (BIC8) or eleven (BIC11) characters made up of :
 *
 * <ul>
 * <li>4 letters: institution code (or bank code)
 * <li>2 letters: ISO 3166-1 alpha-2 country code
 * <li>2 letters or digits: location code
 * <li>3 letters or digits (optional): branch code
 * </ul>
 *
 * <p>
 * Where an 8-digit code is given, it is assumed that it refers to the primary office. The primary office is always
 * designated by the branch code {@value #PRIMARY_OFFICE_BRANCH_CODE}).
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
 * // Validate a BIC
 * Assertions.assertTrue(Bic.isValid(" psstfrppxxx "));
 *
 * // Get BIC information
 * Bic bic = new Bic(" psstfrppxxx ");
 * Assertions.assertEquals("PSSTFRPPXXX", bic.toString());
 * Assertions.assertEquals("PSST", bic.getInstitutionCode());
 * Assertions.assertEquals("FR", bic.getCountryCode());
 * Assertions.assertEquals("PP", bic.getLocationCode());
 * Assertions.assertEquals("XXX", bic.getBranchCode());
 * Assertions.assertTrue(bic.isLiveBic());
 * </pre>
 *
 * @see <a href="https://wikipedia.org/wiki/Bank_Identifier_Code">https://wikipedia.org/wiki/Bank_Identifier_Code</a>
 * @since 1.0
 */
public final class Bic implements Serializable {

  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 1;

  /**
   * A simple regex that validate well-formed BICs.
   *
   * <p>
   * All strings accepted by {@link #isValid(String)} are also accepted by this regex.
   */
  @SuppressWarnings("unused") // kept for compatibility and documentation purposes
  public static final String REGEX = "\\s*[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?\\s*";

  /**
   * The branch code for primary offices.
   */
  public static final String PRIMARY_OFFICE_BRANCH_CODE = "XXX";

  /**
   * If the last character of the location code in a BIC is this one it means that the BIC is a Test BIC.
   */
  public static final char TEST_BIC_INDICATOR = '0';

  private static final int BIC8_LENGTH = 8;
  private static final int BIC11_LENGTH = 11;
  private static final int INSTITUTION_CODE_INDEX = 0;
  private static final int INSTITUTION_CODE_LENGTH = 4;
  private static final int COUNTRY_CODE_INDEX = INSTITUTION_CODE_INDEX + INSTITUTION_CODE_LENGTH;
  private static final int COUNTRY_CODE_LENGTH = 2;
  private static final int LOCATION_CODE_INDEX = COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH;
  private static final int LOCATION_CODE_LENGTH = 2;
  private static final int BRANCH_CODE_INDEX = LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH;
  private static final int BRANCH_CODE_LENGTH = 3;

  /**
   * The normalized form of this BIC.
   */
  private final String value;

  /**
   * Create a new BIC from the given string.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces. The given string
   * may be a BIC8 or a BIC11.
   *
   * @param s A non-null String.
   * @throws IllegalArgumentException if the given string is {@code null}
   * @throws BicFormatException if the given string does not match {@value #REGEX} or if its country code is not known in
   *         {@link fr.marcwrobel.jbanking.IsoCountry}
   */
  public Bic(final String s) {
    if (s == null) {
      throw new IllegalArgumentException("s cannot be null");
    }

    String normalized = trimUpperCase(s);
    if (!isWellFormatted(normalized)) {
      throw BicFormatException.forNotProperlyFormattedInput(s);
    }

    if (!findCountryFor(normalized).isPresent()) {
      throw BicFormatException.forUnknownCountryCode(s);
    }

    if (normalized.length() == BIC8_LENGTH) {
      normalized += PRIMARY_OFFICE_BRANCH_CODE;
    }

    this.value = normalized;
  }

  /**
   * Check whether the given string is a valid BIC.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces. The given string
   * may be a BIC8 or a BIC11.
   *
   * @param s A String.
   * @return {@code true} if the given string is valid BIC, otherwise {@code false}.
   */
  public static boolean isValid(String s) {
    String normalizedValue = trimUpperCase(s);
    return normalizedValue != null && isWellFormatted(normalizedValue) && findCountryFor(normalizedValue).isPresent();
  }

  private static boolean isWellFormatted(String bic) {
    int length = bic.length();
    if (length != BIC8_LENGTH && length != BIC11_LENGTH) {
      return false;
    }

    for (int i = 0; i < INSTITUTION_CODE_INDEX + INSTITUTION_CODE_LENGTH + COUNTRY_CODE_LENGTH; i++) {
      if (!AsciiCharacters.isAlphabetic(bic.charAt(i))) {
        return false;
      }
    }

    for (int i = LOCATION_CODE_INDEX; i < length; i++) {
      if (!AsciiCharacters.isAlphanumeric(bic.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  private static Optional<IsoCountry> findCountryFor(String bic) {
    return IsoCountry.fromAlpha2Code(bic.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH));
  }

  /**
   * Extract the institution code (or bank code) from this BIC.
   *
   * @return A non-null string representing this BIC institution code.
   */
  public String getInstitutionCode() {
    return value.substring(INSTITUTION_CODE_INDEX, INSTITUTION_CODE_INDEX + INSTITUTION_CODE_LENGTH);
  }

  /**
   * Extract the country code from this BIC.
   *
   * @return A non-null string representing this BIC country code.
   */
  public String getCountryCode() {
    return getCountry().getAlpha2Code();
  }

  /**
   * Gets this BIC {@link IsoCountry}.
   *
   * @return A non-null {@link IsoCountry}.
   */
  public IsoCountry getCountry() {
    return findCountryFor(value).orElseThrow(() -> new IllegalStateException("a valid Bic should have a country code"));
  }

  /**
   * Extract the location code from this BIC.
   *
   * @return A non-null string representing this BIC location code.
   */
  public String getLocationCode() {
    return value.substring(LOCATION_CODE_INDEX, LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH);
  }

  /**
   * Extract the branch code from this BIC.
   *
   * @return A non-null string representing this BIC branch code.
   */
  public String getBranchCode() {
    return value.substring(BRANCH_CODE_INDEX, BRANCH_CODE_INDEX + BRANCH_CODE_LENGTH);
  }

  /**
   * Test whether this BIC is a test bic.
   *
   * <p>
   * A BIC is a test BIC if the last character of the location code is {@value #TEST_BIC_INDICATOR}.
   *
   * @return {@code true} if this BIC is a test BIC, otherwise {@code false}.
   * @see #isLiveBic
   */
  public boolean isTestBic() {
    return value.charAt(LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH - 1) == TEST_BIC_INDICATOR;
  }

  /**
   * Test whether this BIC is a live bic.
   *
   * <p>
   * A BIC is a live BIC if the last character of the location code is not {@value #TEST_BIC_INDICATOR}.
   *
   * @return {@code true} if this BIC is a live BIC, otherwise {@code false}.
   * @see #isTestBic
   */
  public boolean isLiveBic() {
    return !isTestBic();
  }

  /**
   * Transform this BIC to a test BIC.
   *
   * @return this if this BIC is a test BIC, or this BIC corresponding test BIC otherwise.
   */
  public Bic asTestBic() {
    if (isTestBic()) {
      return this;
    }

    StringBuilder testBicBuilder = new StringBuilder(value);
    testBicBuilder.setCharAt(LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH - 1, TEST_BIC_INDICATOR);
    return new Bic(testBicBuilder.toString());
  }

  /**
   * Indicates whether some other object is “equal to” this one.
   *
   * <p>
   * To be equals to this one the other object must be a {@link Bic} and the BICs normalized form (see
   * {@link #toString()}) must be equal.
   *
   * @param o the object with which to compare.
   * @return {@code true} if this object is the same as the obj argument or {@code false} otherwise.
   * @see Object#toString()
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    return value.equals(((Bic) o).value);
  }

  /**
   * @see Object#hashCode()
   */
  @Override
  public int hashCode() {
    return 31 * value.hashCode();
  }

  /**
   * Returns a normalized string representation of this BIC.
   *
   * <p>
   * Normalized means the string is:
   * <ul>
   * <li>made of uppercase characters
   * <li>eleven characters long (BIC11)
   * </ul>
   *
   * @return a normalized string representation of this BIC
   */
  @Override
  public String toString() {
    return value;
  }
}
