package fr.marcwrobel.jbanking.creditor;

import static fr.marcwrobel.jbanking.IsoCountry.FR;
import static fr.marcwrobel.jbanking.internal.TestUtils.BLANK;
import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static fr.marcwrobel.jbanking.internal.TestUtils.testSerialization;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import fr.marcwrobel.jbanking.IsoCountry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CreditorIdentifierTest {

  private static final String VALID_CI = "FR72ZZZ123456";
  private static final String VALID_CI2 = "BE69ZZZ050D000000008";

  @Test
  void nullIsNotValid() {
    assertThat(CreditorIdentifier.isValid(null)).isFalse();
  }

  @Test
  void cannotCreateWithNull() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new CreditorIdentifier(null));
  }

  @Test
  void cannotCreateWithNullCountry() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new CreditorIdentifier(null, "ZZZ", "123456"));
  }

  @Test
  void cannotCreateWithNullBusinessCode() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new CreditorIdentifier(FR, null, "123456"));
  }

  @Test
  void cannotCreateWithNullNationalId() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new CreditorIdentifier(FR, "ZZZ", null));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      // invalid
      "", BLANK,
      // malformed
      "FR", "FR72", "FR72ZZZ", "FR72ZZZ12345!", "F!72ZZZ123456", "FR7!ZZZ123456", "FR72ZZ!123456", "FR72ZZZ12345!",
      // unknown country
      "FG72ZZZ123456",
      // invalid check digit
      "FR77ZZZ123456"
  })
  void invalidInputIsNotValid(String s) {
    assertThat(CreditorIdentifier.isValid(s)).isFalse();
  }

  @ParameterizedTest
  @ValueSource(strings = {
      // invalid
      "", BLANK,
      // malformed
      "FR", "FR72", "FR72ZZZ", "FR72ZZZ12345!"
  })
  void cannotCreateWithInvalidInput(String s) {
    assertThatExceptionOfType(CreditorIdentifierFormatException.class)
        .isThrownBy(() -> new CreditorIdentifier(s))
        .withMessageContaining("format")
        .extracting(CreditorIdentifierFormatException::getInputString)
        .isEqualTo(s);
  }

  @Test
  void cannotCreateWithUnknownCountry() {
    assertThatExceptionOfType(CreditorIdentifierFormatException.class)
        .isThrownBy(() -> new CreditorIdentifier("FG72ZZZ123456"))
        .withMessageContaining("ISO 3166-1-alpha-2 code")
        .extracting(CreditorIdentifierFormatException::getInputString)
        .isEqualTo("FG72ZZZ123456");
  }

  @Test
  void cannotCreateWithInvalidCheckDigit() {
    assertThatExceptionOfType(CreditorIdentifierFormatException.class)
        .isThrownBy(() -> new CreditorIdentifier("FR77ZZZ123456"))
        .withMessageContaining("check digits")
        .extracting(CreditorIdentifierFormatException::getInputString)
        .isEqualTo("FR77ZZZ123456");
  }

  @Test
  void cannotCreateWithInvalidNationalId() {
    assertThatExceptionOfType(CreditorIdentifierFormatException.class)
        .isThrownBy(() -> new CreditorIdentifier(""))
        .withMessageContaining("format")
        .extracting(CreditorIdentifierFormatException::getInputString)
        .isEqualTo("");
  }

  @ParameterizedTest
  @ValueSource(strings = { "FR72ZZZ123456", "fr72zzz123456", " FR72ZZZ123456 " })
  void canCreateAndValidateWithValidInput(String s) {
    assertThat(CreditorIdentifier.isValid(s)).isTrue();

    CreditorIdentifier ci = new CreditorIdentifier(s);
    assertThat(ci).hasToString("FR72ZZZ123456");
    assertThat(ci.getCountry()).isEqualTo(FR);
    assertThat(ci.getCountryCode()).isEqualTo("FR");
    assertThat(ci.getCheckDigit()).isEqualTo("72");
    assertThat(ci.getBusinessCode()).isEqualTo("ZZZ");
    assertThat(ci.getNationalIdentifier()).isEqualTo("123456");
  }

  @ParameterizedTest
  @ValueSource(strings = { "HR04ZZZ01234567890",
      "SK19ZZZ70000000022", "NO38ZZZ123456785", "ES59ZZZX1234567L", "CY54ZZZ003A", "CZ56ZZZ12345",
      "IE84ZZZ123456", "FR72ZZZ123456", "PL18ZZZ0123456789", "DK95ZZZ999912345678", "DE51ZZZ12345678901",
      "LT30ZZZ123456789", "MC54ZZZ123456", "GB23ZZZSDDBARC000000ABCD1234", "SE41ZZZ1234567890",
      "ES04ZZZ52840790N", "NL42ZZZ123456780001", "AT61ZZZ01234567890", "LV21ZZZ40003000010", "BE68ZZZ0123456789",
      "GR44ZZZ12345", "IT66ZZZA1B2C3D4E5F6G7H8", "PT73ZZZ123456", "SI02ZZZ12345678", "HU56ZZZE12345676",
      "BE69ZZZ050D000000008", "HU74111A12345676", "BG07ZZZ100064095", "FI22BBB12345678",
      "SM94ZZZA1B2C3D4E5F6G7H8", "ES50ZZZM23456789", "LU27ZZZ0000000000123456789", "CH1312300000012345",
      "MT31ZZZ123456789X", "EE49ZZZEE00012345678" })
  void validCreditorIdentifiersTest(String value) {
    assertThat(CreditorIdentifier.isValid(value)).isTrue();

    CreditorIdentifier ci1 = new CreditorIdentifier(value);

    IsoCountry country = IsoCountry.fromAlpha2Code(value.substring(0, 2)).orElseThrow(IllegalArgumentException::new);
    String businessCode = value.substring(4, 7);
    String nationalId = value.substring(7);
    CreditorIdentifier ci2 = new CreditorIdentifier(country, businessCode, nationalId);

    assertThat(ci2).isEqualTo(ci1);
  }

  @Test
  void equality() {
    testEquality(new CreditorIdentifier(VALID_CI), new CreditorIdentifier(VALID_CI));
  }

  @Test
  void serialization() {
    testSerialization(new CreditorIdentifier(VALID_CI));
  }
}
