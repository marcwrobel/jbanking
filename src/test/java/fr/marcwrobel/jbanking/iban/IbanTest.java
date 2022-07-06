package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.TestUtils.shouldHaveThrown;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Sets;
import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.TestUtils;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for the {@link Iban} class.
 *
 * @author Marc Wrobel
 */
class IbanTest {

  private static final Set<String> VALID_IBANS = Sets.newHashSet("AD9179714843548170724658",
      "AE532299249995935421750", "AL36442788709271283994894168", "AT836670643070032585",
      "AZ73GIGY95609694664952978687", "BA590483797190961278", "BE83158799706920", "BG29EFYA04741506522965",
      "BH45BSND64749329633519", "BR2900994781520950149594104E9", "BY13NBRB3600900000002Z00AB00",
      "CH2669331784946419826", "CR05015202001026284066", "CY75371695368399798483913159",
      "CZ9667197451460125048740", "DE09355072686373974067", "DK2017303660295651", "DO72758351294497410197174992",
      "EE803801541625441184", "ES2837832292261368335005", "FI6346426364591804", "FO5778020271560713",
      "FR2531682128768051490609537", "GB42RHBR54612317078692", "GE20NB4430283848566197",
      "GI44UQCR057428239600992", "GL0502210301477863", "GR7642039964850941669463374",
      "GT46866930583377769763582334", "HR7971488917057910183", "HU91584374853132521152169392",
      "IE27CTJG26368089744633", "IL862354629185675963157", "IQ98NBIQ850123456789012",
      "IS925454646268060577432601", "IT82K7579579031831283849724", "JO94CBJO0010000000000131000302",
      "KW23YOUF8981762754308793114869", "KZ830907614112967961", "LB52372840451692007088329912",
      "LC55HEMM000100010012001200023015", "LI1935676368061346475", "LT192412904756279442", "LU951073196477746242",
      "LV88HAGO9615590045208", "MC3212096543766737867569392", "MD9881991481700015080266",
      "ME36752723963983139414", "MK31948483764450559", "MR5442207381102036325668909",
      "MT84AIWA00813109843252965695890", "MU65OSUR4348845783494200142ABC", "NL64VCYA2607250706",
      "NO2451742753161", "PK13LCJY3078553597017331", "PL64778828144458067930515057",
      "PS38LEKA813957891201287138525", "PT87847144161279936872891", "QA58DOHB00001234567890ABCDEFG",
      "RO17KRBO3212835705756123", "RS80246113647338221492", "SA0979413508515371577583",
      "SC18SSCB11010000000000001497USD", "SE0651297191201320278580", "SI75412441865827872",
      "SK7336568401664473733427", "SM67H0718392993037614346095", "ST32000200010192194210112",
      "SV62CENR00000000000000700025", "TL380080012345678910157", "TN6199977105796904072050",
      "TR330006100519786457841326", "TR888859050625760496700846", "UA213223130000026007233566001",
      "VA59001123000012345678", "VG14NDUM4605555206975725", "XK051212012345678906");

  private static final String VALID_IBAN_COUNTRY = "FR";
  private static final String VALID_IBAN_CHECKDIGIT = "14";
  private static final String VALID_IBAN_BBAN = "20041010050500013M02606";
  private static final String VALID_IBAN = VALID_IBAN_COUNTRY + VALID_IBAN_CHECKDIGIT + VALID_IBAN_BBAN;
  private static final String VALID_IBAN2 = "AL36442788709271283994894168";

  private static final String BBAN_WITH_INVALID_FORMAT = "132!";
  private static final String IBAN_WITH_INVALID_FORMAT = VALID_IBAN_COUNTRY + VALID_IBAN_CHECKDIGIT
      + BBAN_WITH_INVALID_FORMAT;

  private static final String IBAN_WITH_UNKNOWN_COUNTRY = "ZZ" + VALID_IBAN_CHECKDIGIT + VALID_IBAN_BBAN;
  private static final String IBAN_WITH_UNSUPPORTED_COUNTRY = "US" + VALID_IBAN_CHECKDIGIT + VALID_IBAN_BBAN;
  private static final String IBAN_WITH_INVALID_CHECK_DIGIT = VALID_IBAN_COUNTRY + "09" + VALID_IBAN_BBAN;

  private static final String IBAN_WITH_INVALID_BBAN_STRUCTURE = "GB72MIDLA0051539024150";

  @Test
  void nullIsNotAValidIban() {
    assertFalse(Iban.isValid(null));
  }

  @Test
  void anIbanCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> new Iban(null));
  }

  @Test
  void anIbanCountryCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> new Iban(null, "123456"));
  }

  @Test
  void anIbanBbanCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> new Iban(IsoCountry.FR, null));
  }

  @Test
  void blankIsNotAValidIban() {
    assertFalse(Iban.isValid(TestUtils.BLANK));
  }

  @Test
  void anIbanCannotBeBlank() {
    assertThrows(IbanFormatException.class, () -> new Iban(TestUtils.BLANK));
  }

  @Test
  void anIbanBbanCannotBeBlank() {
    assertThrows(IbanFormatException.class, () -> new Iban(IsoCountry.FR, TestUtils.BLANK));
  }

  @Test
  void ibanWithUnknownCountryIsNotValid() {
    assertFalse(Iban.isValid(IBAN_WITH_UNKNOWN_COUNTRY));
  }

  @Test
  void anIbanMustBeFromAKnownCountry() {
    try {
      new Iban(IBAN_WITH_UNKNOWN_COUNTRY);
      shouldHaveThrown(IbanFormatException.class);
    } catch (IbanFormatException e) {
      assertEquals(IBAN_WITH_UNKNOWN_COUNTRY, e.getInputString());
      assertTrue(e.getMessage().contains("ISO 3166-1-alpha-2 code"));
    }
  }

  @Test
  void ibanWithUnsupportedCountryIsNotValid() {
    assertFalse(Iban.isValid(IBAN_WITH_UNSUPPORTED_COUNTRY));
  }

  @Test
  void anIbanMustBeFromASupportedCountry() {
    try {
      new Iban(IBAN_WITH_UNSUPPORTED_COUNTRY);
      shouldHaveThrown(IbanFormatException.class);
    } catch (IbanFormatException e) {
      assertEquals(IBAN_WITH_UNSUPPORTED_COUNTRY, e.getInputString());
      assertTrue(e.getMessage().contains("support"));
    }
  }

  @Test
  void anIbanCountryMustBeSupported() {
    try {
      new Iban(IsoCountry.US, VALID_IBAN_BBAN);
      shouldHaveThrown(IbanFormatException.class);
    } catch (IbanFormatException e) {
      assertEquals(VALID_IBAN_BBAN, e.getInputString());
      assertTrue(e.getMessage().contains("support"));
    }
  }

  @Test
  void notProperlyFormattedIbanIsNotValid() {
    assertFalse(Iban.isValid(IBAN_WITH_INVALID_FORMAT));
  }

  @Test
  void anIbanMustBeProperlyFormatted() {
    try {
      new Iban(IBAN_WITH_INVALID_FORMAT);
      shouldHaveThrown(IbanFormatException.class);
    } catch (IbanFormatException e) {
      assertEquals(IBAN_WITH_INVALID_FORMAT, e.getInputString());
      assertTrue(e.getMessage().contains("format"));
    }
  }

  @Test
  void notProperlyStructuredIbanIsNotValid() {
    assertFalse(Iban.isValid(IBAN_WITH_INVALID_BBAN_STRUCTURE));
  }

  @Test
  void anIbanMustBeProperlyStructured() {
    try {
      new Iban(IBAN_WITH_INVALID_BBAN_STRUCTURE);
      shouldHaveThrown(IbanFormatException.class);
    } catch (IbanFormatException e) {
      assertEquals(IBAN_WITH_INVALID_BBAN_STRUCTURE, e.getInputString());
      assertTrue(e.getMessage().contains("structure"));
    }
  }

  @Test
  void anIbanBbanMustBeProperlyStructured() {
    try {
      new Iban(IsoCountry.FR, BBAN_WITH_INVALID_FORMAT);
      shouldHaveThrown(IbanFormatException.class);
    } catch (IbanFormatException e) {
      assertEquals(BBAN_WITH_INVALID_FORMAT, e.getInputString());
      assertTrue(e.getMessage().contains("structure"));
    }
  }

  @Test
  void anIbanWithInvalidCheckDigitsIsNotValid() {
    assertFalse(Iban.isValid(IBAN_WITH_INVALID_CHECK_DIGIT));
  }

  @Test
  void anIbanMustHaveCorrectCheckDigit() {
    try {
      new Iban(IBAN_WITH_INVALID_CHECK_DIGIT);
      shouldHaveThrown(IbanFormatException.class);
    } catch (IbanFormatException e) {
      assertEquals(IBAN_WITH_INVALID_CHECK_DIGIT, e.getInputString());
      assertTrue(e.getMessage().contains("check digits"));
    }
  }

  @Test
  void validIbanDecomposition() {
    assertTrue(Iban.isValid(VALID_IBAN));
    Iban iban = new Iban(VALID_IBAN);
    assertEquals(VALID_IBAN_COUNTRY, iban.getCountryCode());
    assertEquals(VALID_IBAN_CHECKDIGIT, iban.getCheckDigit());
    assertEquals(VALID_IBAN_BBAN, iban.getBban());
  }

  @ParameterizedTest
  @MethodSource("validIbans")
  void validIbansTest(String ibanString) {
    assertTrue(Iban.isValid(ibanString));

    Iban iban = new Iban(ibanString);
    String countryCode = ibanString.substring(0, 2);
    String bban = ibanString.substring(4);
    assertEquals(iban,
        new Iban(IsoCountry.fromAlpha2Code(countryCode).orElseThrow(IllegalArgumentException::new), bban));
  }

  @ParameterizedTest
  @MethodSource("validIbans")
  void ibanValidationIsNotCaseSensitive(String iban) {
    String lowerCaseIban = iban.toLowerCase();
    assertTrue(Iban.isValid(lowerCaseIban));
  }

  @ParameterizedTest
  @MethodSource("validIbans")
  void ibanCreationIsNotCaseSensitive(String iban) {
    assertDoesNotThrow(() -> new Iban(iban.toLowerCase()));
  }

  @ParameterizedTest
  @MethodSource("validIbans")
  void ibanFromBbanCreationIsNotCaseSensitive(String iban) {
    String countryCode = iban.substring(0, 2);
    String bban = iban.substring(4);
    assertEquals(iban, new Iban(IsoCountry.fromAlpha2Code(countryCode).orElseThrow(IllegalArgumentException::new),
        bban.toLowerCase()).toString());
  }

  @Test
  void printableIbansAreValid() {
    Iban iban = new Iban(VALID_IBAN);
    String printableIban = iban.toPrintableString();

    assertTrue(Iban.isValid(printableIban));
    assertEquals(iban, new Iban(printableIban));
  }

  @Test
  void equalityTest() {
    Iban iban1 = new Iban(VALID_IBAN);
    Iban iban2 = new Iban(iban1.toPrintableString());
    Iban iban3 = new Iban(VALID_IBAN.toLowerCase());

    assertEquals(iban1, iban1);
    assertEquals(iban2, iban2);
    assertEquals(iban3, iban3);

    assertEquals(iban1, iban2);
    assertEquals(iban2, iban1);
    assertEquals(iban2, iban3);
    assertEquals(iban3, iban2);
    assertEquals(iban1, iban3);
    assertEquals(iban3, iban1);
    assertEquals(iban1.hashCode(), iban2.hashCode());
    assertEquals(iban2.hashCode(), iban3.hashCode());

    assertNotEquals(null, iban1);
    assertNotEquals(iban1, new Object());
    assertNotEquals(iban1, new Iban(VALID_IBAN2));
  }

  private static Stream<Arguments> validIbans() {
    return VALID_IBANS.stream().map(Arguments::of);
  }
}
