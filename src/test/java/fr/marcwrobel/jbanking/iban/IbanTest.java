package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.IsoCountry.FR;
import static fr.marcwrobel.jbanking.IsoCountry.GB;
import static fr.marcwrobel.jbanking.internal.TestUtils.BLANK;
import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static fr.marcwrobel.jbanking.internal.TestUtils.testSerialization;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import fr.marcwrobel.jbanking.IsoCountry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class IbanTest {

  @Test
  void nullIsNotValid() {
    assertThat(Iban.isValid(null)).isFalse();
  }

  @Test
  void cannotCreateWithNull() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Iban(null));
  }

  @Test
  void cannotCreateWithNullCountry() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Iban(null, "123456"));
  }

  @Test
  void cannotCreateWithNullBban() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Iban(FR, null));
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
    assertThat(Iban.isValid(s)).isFalse();
  }

  @ParameterizedTest
  @ValueSource(strings = {
      // invalid
      "", BLANK
  })
  void cannotCreateWithInvalidInput(String s) {
    assertThatExceptionOfType(IbanFormatException.class).isThrownBy(() -> new Iban(s));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      // invalid
      "", BLANK
  })
  void cannotCreateWithInvalidInput2(String s) {
    assertThatExceptionOfType(IbanFormatException.class).isThrownBy(() -> new Iban(FR, s));
  }

  @Test
  void cannotCreateWithAnUnknownCountry() {
    assertThatExceptionOfType(IbanFormatException.class)
        .isThrownBy(() -> new Iban("ZZ1420041010050500013M02606"))
        .withMessageContaining("ISO 3166-1-alpha-2 code")
        .extracting(IbanFormatException::getInputString)
        .isEqualTo("ZZ1420041010050500013M02606");
  }

  @Test
  void cannotCreateWithAnUnsupportedCountry() {
    assertThatExceptionOfType(IbanFormatException.class)
        .isThrownBy(() -> new Iban("US1420041010050500013M02606"))
        .withMessageContaining("support")
        .extracting(IbanFormatException::getInputString)
        .isEqualTo("US1420041010050500013M02606");
  }

  @Test
  void cannotCreateWithAnUnsupportedCountry2() {
    assertThatExceptionOfType(IbanFormatException.class)
        .isThrownBy(() -> new Iban(IsoCountry.US, "20041010050500013M02606"))
        .withMessageContaining("support")
        .extracting(IbanFormatException::getInputString)
        .isEqualTo("20041010050500013M02606");
  }

  @Test
  void cannotCreateWithAnInvalidStructure() {
    assertThatExceptionOfType(IbanFormatException.class)
        .isThrownBy(() -> new Iban("GB72MIDLA0051539024150"))
        .withMessageContaining("structure")
        .extracting(IbanFormatException::getInputString)
        .isEqualTo("GB72MIDLA0051539024150");
  }

  @Test
  void cannotCreateWithAnInvalidStructure2() {
    assertThatExceptionOfType(IbanFormatException.class)
        .isThrownBy(() -> new Iban(GB, "MIDLA0051539024150"))
        .withMessageContaining("structure")
        .extracting(IbanFormatException::getInputString)
        .isEqualTo("MIDLA0051539024150");
  }

  @ParameterizedTest
  @ValueSource(strings = { "FR0920041010050500013M02606", "BY00NBRB3600000000000Z00AB00", "AD9279714843548170724658" })
  void cannotCreateWithAnInvalidCheckDigit(String iban) {
    assertThatExceptionOfType(IbanFormatException.class)
        .isThrownBy(() -> new Iban(iban))
        .withMessageContaining("check digits")
        .extracting(IbanFormatException::getInputString)
        .isEqualTo(iban);
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

    assertThat(Iban.isValid(iban)).isTrue();
    assertThat(iban2).isEqualTo(iban1);
  }

  @ParameterizedTest
  @ValueSource(strings = { "FR1420041010050500013M02606", "fr1420041010050500013m02606", " FR1420041010050500013M02606 " })
  void canCreateAndValidateWithValidInput(String s) {
    Iban iban = new Iban(s);

    assertThat(Iban.isValid(s)).isTrue();
    assertThat(iban).hasToString("FR1420041010050500013M02606");
    assertThat(iban.toPrintableString()).isEqualTo("FR14 2004 1010 0505 0001 3M02 606");
    assertThat(iban.getCountry()).isEqualTo(FR);
    assertThat(iban.getCountryCode()).isEqualTo("FR");
    assertThat(iban.getCheckDigit()).isEqualTo("14");
    assertThat(iban.getBban()).isEqualTo("20041010050500013M02606");
    assertThat(iban.getBankIdentifier()).isEqualTo("20041");
    assertThat(iban.getBranchIdentifier()).contains("01005");
    assertThat(iban.getAccountNumber()).isEqualTo("0500013M026");
    assertThat(iban.getNationalCheckDigit()).contains("06");
  }

  @ParameterizedTest
  @ValueSource(strings = { "20041010050500013M02606", "20041010050500013m02606", " 20041010050500013M02606 " })
  void canCreateAndValidateWithValidInput2(String s) {
    Iban iban = new Iban(FR, s);

    assertThat(iban).hasToString("FR1420041010050500013M02606");
    assertThat(iban.toPrintableString()).isEqualTo("FR14 2004 1010 0505 0001 3M02 606");
    assertThat(iban.getCountry()).isEqualTo(FR);
    assertThat(iban.getCountryCode()).isEqualTo("FR");
    assertThat(iban.getCheckDigit()).isEqualTo("14");
    assertThat(iban.getBban()).isEqualTo("20041010050500013M02606");
    assertThat(iban.getBankIdentifier()).isEqualTo("20041");
    assertThat(iban.getBranchIdentifier()).contains("01005");
    assertThat(iban.getAccountNumber()).isEqualTo("0500013M026");
    assertThat(iban.getNationalCheckDigit()).contains("06");
  }

  @ParameterizedTest
  @EnumSource(BbanStructure.class)
  void decompositionForAllCountry(BbanStructure structure) {
    Iban iban = new RandomIban().next(structure);

    assertThat(iban.getCountry()).isEqualTo(structure.getCountry());
    assertThat(iban.getCountryCode()).isEqualTo(structure.getCountry().name());

    assertThatNoException().isThrownBy(iban::getCheckDigit);
    assertThatNoException().isThrownBy(iban::getBban);
    assertThatNoException().isThrownBy(iban::getBankIdentifier);
    assertThatNoException().isThrownBy(iban::getBranchIdentifier);
    assertThatNoException().isThrownBy(iban::getNationalCheckDigit);
    assertThatNoException().isThrownBy(iban::getAccountNumber);
    assertThatNoException().isThrownBy(iban::toPrintableString);
    assertThatNoException().isThrownBy(iban::toString);
  }

  @Test
  void equality() {
    testEquality(new Iban("FR1420041010050500013M02606"),
        new Iban("FR1420041010050500013M02606".toLowerCase()));
  }

  @Test
  void serialization() {
    testSerialization(new Iban("FR1420041010050500013M02606"));
  }
}
