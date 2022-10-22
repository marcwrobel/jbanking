package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.IsoCountry.FR;
import static fr.marcwrobel.jbanking.IsoCountry.GB;
import static fr.marcwrobel.jbanking.internal.TestUtils.BLANK;
import static org.junit.jupiter.api.Assertions.*;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.internal.SerializationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class IbanTest {

  @Test
  void nullIsNotValid() {
    assertFalse(Iban.isValid(null));
  }

  @Test
  void cannotCreateWithNull() {
    assertThrows(IllegalArgumentException.class, () -> new Iban(null));
  }

  @Test
  void cannotCreateWithNullCountry() {
    assertThrows(IllegalArgumentException.class, () -> new Iban(null, "123456"));
  }

  @Test
  void cannotCreateWithNullBban() {
    assertThrows(IllegalArgumentException.class, () -> new Iban(FR, null));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      // invalid
      "", BLANK,
      // unknown country
      "ZZ1420041010050500013M02606",
      // unsupported country
      "US1420041010050500013M02606",
      // invalid structure
      "GB72MIDLA0051539024150",
      // invalid check digit
      "FR0920041010050500013M02606", "BY00NBRB3600000000000Z00AB00"
  })
  void invalidInputIsNotValid(String s) {
    assertFalse(Iban.isValid(s));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      // invalid
      "", BLANK
  })
  void cannotCreateWithInvalidInput(String s) {
    assertThrows(IbanFormatException.class, () -> new Iban(s));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      // invalid
      "", BLANK
  })
  void cannotCreateWithInvalidInput2(String s) {
    assertThrows(IbanFormatException.class, () -> new Iban(FR, s));
  }

  @Test
  void cannotCreateWithAnUnknownCountry() {
    IbanFormatException e = assertThrows(IbanFormatException.class, () -> new Iban("ZZ1420041010050500013M02606"));

    assertEquals("ZZ1420041010050500013M02606", e.getInputString());
    assertTrue(e.getMessage().contains("ISO 3166-1-alpha-2 code"));
  }

  @Test
  void cannotCreateWithAnUnsupportedCountry() {
    IbanFormatException e = assertThrows(IbanFormatException.class, () -> new Iban("US1420041010050500013M02606"));

    assertEquals("US1420041010050500013M02606", e.getInputString());
    assertTrue(e.getMessage().contains("support"));
  }

  @Test
  void cannotCreateWithAnUnsupportedCountry2() {
    IbanFormatException e = assertThrows(IbanFormatException.class, () -> new Iban(IsoCountry.US, "20041010050500013M02606"));

    assertEquals("20041010050500013M02606", e.getInputString());
    assertTrue(e.getMessage().contains("support"));
  }

  @Test
  void cannotCreateWithAnInvalidStructure() {
    IbanFormatException e = assertThrows(IbanFormatException.class, () -> new Iban("GB72MIDLA0051539024150"));

    assertEquals("GB72MIDLA0051539024150", e.getInputString());
    assertTrue(e.getMessage().contains("structure"));
  }

  @Test
  void cannotCreateWithAnInvalidStructure2() {
    IbanFormatException e = assertThrows(IbanFormatException.class, () -> new Iban(GB, "MIDLA0051539024150"));

    assertEquals("MIDLA0051539024150", e.getInputString());
    assertTrue(e.getMessage().contains("structure"));
  }

  @ParameterizedTest
  @ValueSource(strings = { "FR0920041010050500013M02606", "BY00NBRB3600000000000Z00AB00", "AD9279714843548170724658" })
  void cannotCreateWithAnInvalidCheckDigit(String iban) {
    IbanFormatException e = assertThrows(IbanFormatException.class, () -> new Iban(iban));

    assertEquals(iban, e.getInputString());
    assertTrue(e.getMessage().contains("check digits"));
  }

  @ParameterizedTest
  @ValueSource(strings = { "AD9179714843548170724658", "AE532299249995935421750", "AL36442788709271283994894168",
      "AT836670643070032585", "AZ73GIGY95609694664952978687", "BA590483797190961278", "BE83158799706920",
      "BG29EFYA04741506522965", "BH45BSND64749329633519", "BR2900994781520950149594104E9",
      "BY13NBRB3600900000002Z00AB00", "CH2669331784946419826", "CR05015202001026284066", "CY75371695368399798483913159",
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
      "VA59001123000012345678", "VG14NDUM4605555206975725", "XK051212012345678906" })
  void canCreateWithValidInput(String iban) {
    Iban iban1 = new Iban(iban);

    IsoCountry country = IsoCountry.fromAlpha2Code(iban.substring(0, 2)).orElseThrow(IllegalArgumentException::new);
    String bban = iban.substring(4);
    Iban iban2 = new Iban(country, bban);

    assertTrue(Iban.isValid(iban));
    assertEquals(iban1, iban2);
  }

  @ParameterizedTest
  @ValueSource(strings = { "FR1420041010050500013M02606", "fr1420041010050500013m02606", " FR1420041010050500013M02606 " })
  void canCreateAndValidateWithValidInput(String s) {
    Iban iban = new Iban(s);

    assertTrue(Iban.isValid(s));
    assertEquals("FR1420041010050500013M02606", iban.toString());
    assertEquals("FR14 2004 1010 0505 0001 3M02 606", iban.toPrintableString());
    assertEquals(FR, iban.getCountry());
    assertEquals("FR", iban.getCountryCode());
    assertEquals("14", iban.getCheckDigit());
    assertEquals("20041010050500013M02606", iban.getBban());
    assertEquals("20041", iban.getBankIdentifier());
    assertTrue(iban.getBranchIdentifier().isPresent());
    assertEquals("01005", iban.getBranchIdentifier().get());
    assertEquals("0500013M026", iban.getAccountNumber());
    assertTrue(iban.getNationalCheckDigit().isPresent());
    assertEquals("06", iban.getNationalCheckDigit().get());
  }

  @ParameterizedTest
  @ValueSource(strings = { "20041010050500013M02606", "20041010050500013m02606", " 20041010050500013M02606 " })
  void canCreateAndValidateWithValidInput2(String s) {
    Iban iban = new Iban(FR, s);

    assertEquals("FR1420041010050500013M02606", iban.toString());
    assertEquals("FR14 2004 1010 0505 0001 3M02 606", iban.toPrintableString());
    assertEquals(FR, iban.getCountry());
    assertEquals("FR", iban.getCountryCode());
    assertEquals("14", iban.getCheckDigit());
    assertEquals("20041010050500013M02606", iban.getBban());
    assertEquals("20041", iban.getBankIdentifier());
    assertTrue(iban.getBranchIdentifier().isPresent());
    assertEquals("01005", iban.getBranchIdentifier().get());
    assertEquals("0500013M026", iban.getAccountNumber());
    assertTrue(iban.getNationalCheckDigit().isPresent());
    assertEquals("06", iban.getNationalCheckDigit().get());
  }

  @ParameterizedTest
  @EnumSource(BbanStructure.class)
  void decompositionForAllCountry(BbanStructure structure) {
    Iban iban = new RandomIban().next(structure);

    assertEquals(structure.getCountry(), iban.getCountry());
    assertEquals(structure.getCountry().name(), iban.getCountryCode());
    assertDoesNotThrow(iban::getCheckDigit);
    assertDoesNotThrow(iban::getBban);
    assertDoesNotThrow(iban::getBankIdentifier);
    assertDoesNotThrow(iban::getBranchIdentifier);
    assertDoesNotThrow(iban::getNationalCheckDigit);
    assertDoesNotThrow(iban::getAccountNumber);
    assertDoesNotThrow(iban::toPrintableString);
    assertDoesNotThrow(iban::toString);
  }

  @Test
  void equalityTest() {
    Iban iban1 = new Iban("FR1420041010050500013M02606");
    Iban iban2 = new Iban("FR1420041010050500013M02606".toLowerCase());

    assertEquals(iban1, iban1);
    assertEquals(iban2, iban2);

    assertEquals(iban1, iban2);
    assertEquals(iban2, iban1);
    assertEquals(iban1.hashCode(), iban2.hashCode());

    assertNotEquals(null, iban1);
    assertNotEquals(new Object(), iban1);
    assertNotEquals(new Iban("AL36442788709271283994894168"), iban1);
  }

  @Test
  void serialization() {
    Iban object = new Iban("FR1420041010050500013M02606");

    byte[] serializedObject = SerializationUtils.serialize(object);
    Iban deserializedObject = SerializationUtils.deserialize(serializedObject);

    assertEquals(object, deserializedObject);
    assertTrue(SerializationUtils.isSerializable(Iban.class));
  }
}
