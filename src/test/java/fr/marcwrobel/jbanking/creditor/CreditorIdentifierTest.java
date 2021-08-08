package fr.marcwrobel.jbanking.creditor;

import static fr.marcwrobel.jbanking.TestUtils.shouldHaveThrown;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.TestUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for the {@link fr.marcwrobel.jbanking.creditor.CreditorIdentifier} class.
 *
 * @author Charles Kayser
 */
class CreditorIdentifierTest {

  private static final List<String> VALID_CREDITOR_IDENTIFIERS =
      Arrays.asList(
          "HR04ZZZ01234567890",
          "SK19ZZZ70000000022",
          "NO38ZZZ123456785",
          "ES59ZZZX1234567L",
          "CY54ZZZ003A",
          "CZ56ZZZ12345",
          "IE84ZZZ123456",
          "FR72ZZZ123456",
          "PL18ZZZ0123456789",
          "DK95ZZZ999912345678",
          "DE51ZZZ12345678901",
          "LT30ZZZ123456789",
          "MC54ZZZ123456",
          "GB23ZZZSDDBARC000000ABCD1234",
          "SE41ZZZ1234567890",
          "ES04ZZZ52840790N",
          "NL42ZZZ123456780001",
          "AT61ZZZ01234567890",
          "LV21ZZZ40003000010",
          "BE68ZZZ0123456789",
          "GR44ZZZ12345",
          "IT66ZZZA1B2C3D4E5F6G7H8",
          "PT73ZZZ123456",
          "SI02ZZZ12345678",
          "HU56ZZZE12345676",
          "BE69ZZZ050D000000008",
          "HU74111A12345676",
          "BG07ZZZ100064095",
          "FI22BBB12345678",
          "SM94ZZZA1B2C3D4E5F6G7H8",
          "ES50ZZZM23456789",
          "LU27ZZZ0000000000123456789",
          "CH1312300000012345",
          "MT31ZZZ123456789X",
          "EE49ZZZEE00012345678");

  private static final String VALID_CI_COUNTRY = "FR";
  private static final String VALID_CI_CHECKDIGIT = "72";
  private static final String VALID_CI_BUSINESS_CODE = "ZZZ";
  private static final String VALID_CI_NATIONAL_ID = "123456";
  private static final String VALID_CI =
      VALID_CI_COUNTRY + VALID_CI_CHECKDIGIT + VALID_CI_BUSINESS_CODE + VALID_CI_NATIONAL_ID;
  private static final String VALID_CI2 = "BE69ZZZ050D000000008";

  private static final String INVALID_CI_NATIONAL_ID = "132!";

  private static final String CI_WITH_INVALID_FORMAT =
      VALID_CI_COUNTRY + VALID_CI_CHECKDIGIT + VALID_CI_BUSINESS_CODE + INVALID_CI_NATIONAL_ID;
  private static final String CI_WITH_UNKNOWN_COUNTRY =
      "ZZ" + VALID_CI_CHECKDIGIT + VALID_CI_BUSINESS_CODE + VALID_CI_NATIONAL_ID;
  private static final String CI_WITH_UNSUPPORTED_COUNTRY =
      "US" + VALID_CI_CHECKDIGIT + VALID_CI_BUSINESS_CODE + VALID_CI_NATIONAL_ID;

  private static final String CI_WITH_INVALID_CHECK_DIGIT =
      VALID_CI_COUNTRY + "09" + VALID_CI_BUSINESS_CODE + VALID_CI_NATIONAL_ID;

  @Test
  void nullIsNotAValidCreditorIdentifier() {
    assertFalse(CreditorIdentifier.isValid(null));
  }

  @Test
  void aCreditorIdentifierCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> new CreditorIdentifier(null));
  }

  @Test
  void aCreditorIdentifierCountryCannotBeNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new CreditorIdentifier(null, VALID_CI_BUSINESS_CODE, "123456"));
  }

  @Test
  void aCreditorIdentifierBusinessCodeCannotBeNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new CreditorIdentifier(IsoCountry.FR, null, "123456"));
  }

  @Test
  void aCreditorNationalIdCannotBeNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new CreditorIdentifier(IsoCountry.FR, VALID_CI_BUSINESS_CODE, null));
  }

  @Test
  void blankIsNotAValidCreditorIdentifier() {
    assertFalse(CreditorIdentifier.isValid(TestUtils.BLANK));
  }

  @Test
  void aCreditorIdentifierCannotBeBlank() {
    assertThrows(
        CreditorIdentifierFormatException.class, () -> new CreditorIdentifier(TestUtils.BLANK));
  }

  @Test
  void aCreditorNationalIdCannotBeBlank() {
    assertThrows(
        CreditorIdentifierFormatException.class,
        () -> new CreditorIdentifier(IsoCountry.FR, VALID_CI_BUSINESS_CODE, TestUtils.BLANK));
  }

  @Test
  void creditorIdWithUnknownCountryIsNotValid() {
    assertFalse(CreditorIdentifier.isValid(CI_WITH_UNKNOWN_COUNTRY));
  }

  @Test
  void aCreditorIdMustBeFromAKnownCountry() {
    CreditorIdentifierFormatException e =
        assertThrows(
            CreditorIdentifierFormatException.class,
            () -> new CreditorIdentifier(CI_WITH_UNKNOWN_COUNTRY));
    assertEquals(CI_WITH_UNKNOWN_COUNTRY, e.getInputString());
    assertTrue(e.getMessage().contains("ISO 3166-1-alpha-2 code"));
  }

  @Test
  void creditorIdWithUnsupportedCountryIsNotValid() {
    assertFalse(CreditorIdentifier.isValid(CI_WITH_UNSUPPORTED_COUNTRY));
  }

  @Test
  void notProperlyFormattedCreditorIdentifierIsNotValid() {
    assertFalse(CreditorIdentifier.isValid(CI_WITH_INVALID_FORMAT));
  }

  @Test
  void aCreditorIdMustBeProperlyFormatted() {
    try {
      new CreditorIdentifier(CI_WITH_INVALID_FORMAT);
      shouldHaveThrown(CreditorIdentifierFormatException.class);
    } catch (CreditorIdentifierFormatException e) {
      assertEquals(CI_WITH_INVALID_FORMAT, e.getInputString());
      assertTrue(e.getMessage().contains("format"));
    }
  }

  @Test
  void aCreditorNationalIdMustBeProperlyStructured() {
    try {
      new CreditorIdentifier(IsoCountry.FR, VALID_CI_BUSINESS_CODE, INVALID_CI_NATIONAL_ID);
      shouldHaveThrown(CreditorIdentifierFormatException.class);
    } catch (CreditorIdentifierFormatException e) {
      assertEquals(INVALID_CI_NATIONAL_ID, e.getInputString());
      assertTrue(e.getMessage().contains("format"));
    }
  }

  @Test
  void aCreditorIdWithInvalidCheckDigitsIsNotValid() {
    assertFalse(CreditorIdentifier.isValid(CI_WITH_INVALID_CHECK_DIGIT));
  }

  @Test
  void aCreditorIdMustHaveCorrectCheckDigit() {
    try {
      new CreditorIdentifier(CI_WITH_INVALID_CHECK_DIGIT);
      shouldHaveThrown(CreditorIdentifierFormatException.class);
    } catch (CreditorIdentifierFormatException e) {
      assertEquals(CI_WITH_INVALID_CHECK_DIGIT, e.getInputString());
      assertTrue(e.getMessage().contains("check digits"));
    }
  }

  @Test
  void validCreditorIdentifierDecomposition() {
    assertTrue(CreditorIdentifier.isValid(VALID_CI));
    CreditorIdentifier creditorId = new CreditorIdentifier(VALID_CI);
    assertEquals(VALID_CI_COUNTRY, creditorId.getCountryCode());
    assertEquals(VALID_CI_CHECKDIGIT, creditorId.getCheckDigit());
    assertEquals(VALID_CI_BUSINESS_CODE, creditorId.getBusinessCode());
    assertEquals(VALID_CI_NATIONAL_ID, creditorId.getNationalIdentifier());
  }

  @ParameterizedTest
  @MethodSource("validCreditorIdentifiers")
  void validCreditorIdentifiersTest(String value) {
    assertTrue(CreditorIdentifier.isValid(value));

    CreditorIdentifier creditorId = new CreditorIdentifier(value);
    String countryCode = value.substring(0, 2);
    String businessCode = value.substring(4, 7);
    String nationalId = value.substring(7);
    assertEquals(
        creditorId,
        new CreditorIdentifier(
            IsoCountry.fromAlpha2Code(countryCode).orElseThrow(IllegalArgumentException::new),
            businessCode,
            nationalId));
  }

  @ParameterizedTest
  @MethodSource("validCreditorIdentifiers")
  void creditorIdValidationIsNotCaseSensitive(String value) {
    String lowerCaseCreditorIdentifier = value.toLowerCase();
    assertTrue(CreditorIdentifier.isValid(lowerCaseCreditorIdentifier));
  }

  @ParameterizedTest
  @MethodSource("validCreditorIdentifiers")
  void creditorIdCreationIsNotCaseSensitive(String value) {
    assertDoesNotThrow(() -> new CreditorIdentifier(value.toLowerCase()));
  }

  @ParameterizedTest
  @MethodSource("validCreditorIdentifiers")
  void creditorIdFromBbanCreationIsNotCaseSensitive(String value) {
    String countryCode = value.substring(0, 2);
    String businessCode = value.substring(4, 7);
    String nationalId = value.substring(7);
    assertEquals(
        value,
        new CreditorIdentifier(
                IsoCountry.fromAlpha2Code(countryCode).orElseThrow(IllegalArgumentException::new),
                businessCode,
                nationalId.toLowerCase())
            .toString());
  }

  @Test
  void printableCreditorIdentifiersAreValid() {
    CreditorIdentifier creditorId = new CreditorIdentifier(VALID_CI);
    String printableCreditorIdentifier = creditorId.toString();

    assertTrue(CreditorIdentifier.isValid(printableCreditorIdentifier));
    assertEquals(creditorId, new CreditorIdentifier(printableCreditorIdentifier));
  }

  @Test
  void equalityTest() {
    CreditorIdentifier creditorId1 = new CreditorIdentifier(VALID_CI);
    CreditorIdentifier creditorId2 = new CreditorIdentifier(creditorId1.toString());
    CreditorIdentifier creditorId3 = new CreditorIdentifier(VALID_CI.toLowerCase());

    assertEquals(creditorId1, creditorId1);
    assertEquals(creditorId2, creditorId2);
    assertEquals(creditorId3, creditorId3);

    assertEquals(creditorId1, creditorId2);
    assertEquals(creditorId2, creditorId1);
    assertEquals(creditorId2, creditorId3);
    assertEquals(creditorId3, creditorId2);
    assertEquals(creditorId1, creditorId3);
    assertEquals(creditorId3, creditorId1);
    assertEquals(creditorId1.hashCode(), creditorId2.hashCode());
    assertEquals(creditorId2.hashCode(), creditorId3.hashCode());

    assertNotNull(creditorId1);
    assertNotEquals(creditorId1, new Object());
    assertNotEquals(creditorId1, new CreditorIdentifier(VALID_CI2));
  }

  private static Stream<Arguments> validCreditorIdentifiers() {
    return VALID_CREDITOR_IDENTIFIERS.stream().map(Arguments::of);
  }
}
