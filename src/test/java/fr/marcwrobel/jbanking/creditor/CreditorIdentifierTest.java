package fr.marcwrobel.jbanking.creditor;

import static fr.marcwrobel.jbanking.IsoCountry.FR;
import static fr.marcwrobel.jbanking.internal.TestUtils.BLANK;
import static org.junit.jupiter.api.Assertions.*;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.internal.SerializationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CreditorIdentifierTest {

  private static final String VALID_CI = "FR72ZZZ123456";
  private static final String VALID_CI2 = "BE69ZZZ050D000000008";

  @Test
  void nullIsNotValid() {
    assertFalse(CreditorIdentifier.isValid(null));
  }

  @Test
  void cannotCreateWithNull() {
    assertThrows(IllegalArgumentException.class, () -> new CreditorIdentifier(null));
  }

  @Test
  void cannotCreateWithNullCountry() {
    assertThrows(IllegalArgumentException.class, () -> new CreditorIdentifier(null, "ZZZ", "123456"));
  }

  @Test
  void cannotCreateWithNullBusinessCode() {
    assertThrows(IllegalArgumentException.class, () -> new CreditorIdentifier(FR, null, "123456"));
  }

  @Test
  void cannotCreateWithNullNationalId() {
    assertThrows(IllegalArgumentException.class, () -> new CreditorIdentifier(FR, "ZZZ", null));
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
    assertFalse(CreditorIdentifier.isValid(s));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      // invalid
      "", BLANK,
      // malformed
      "FR", "FR72", "FR72ZZZ", "FR72ZZZ12345!"
  })
  void cannotCreateWithInvalidInput(String s) {
    CreditorIdentifierFormatException e = assertThrows(CreditorIdentifierFormatException.class,
        () -> new CreditorIdentifier(s));
    assertEquals(s, e.getInputString());
    assertTrue(e.getMessage().contains("format"));
  }

  @Test
  void cannotCreateWithUnknownCountry() {
    CreditorIdentifierFormatException e = assertThrows(CreditorIdentifierFormatException.class,
        () -> new CreditorIdentifier("FG72ZZZ123456"));
    assertEquals("FG72ZZZ123456", e.getInputString());
    assertTrue(e.getMessage().contains("ISO 3166-1-alpha-2 code"));
  }

  @Test
  void cannotCreateWithInvalidCheckDigit() {
    CreditorIdentifierFormatException e = assertThrows(CreditorIdentifierFormatException.class,
        () -> new CreditorIdentifier("FR77ZZZ123456"));
    assertEquals("FR77ZZZ123456", e.getInputString());
    assertTrue(e.getMessage().contains("check digits"));
  }

  @Test
  void cannotCreateWithInvalidNationalId() {
    CreditorIdentifierFormatException e = assertThrows(CreditorIdentifierFormatException.class,
        () -> new CreditorIdentifier(""));
    assertEquals("", e.getInputString());
    assertTrue(e.getMessage().contains("format"));
  }

  @ParameterizedTest
  @ValueSource(strings = { "FR72ZZZ123456", "fr72zzz123456", " FR72ZZZ123456 " })
  void canCreateAndValidateWithValidInput(String s) {
    assertTrue(CreditorIdentifier.isValid(s));

    CreditorIdentifier ci = new CreditorIdentifier(s);
    assertEquals("FR72ZZZ123456", ci.toString());
    assertEquals(FR, ci.getCountry());
    assertEquals("FR", ci.getCountryCode());
    assertEquals("72", ci.getCheckDigit());
    assertEquals("ZZZ", ci.getBusinessCode());
    assertEquals("123456", ci.getNationalIdentifier());
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
    assertTrue(CreditorIdentifier.isValid(value));

    CreditorIdentifier ci1 = new CreditorIdentifier(value);

    IsoCountry country = IsoCountry.fromAlpha2Code(value.substring(0, 2)).orElseThrow(IllegalArgumentException::new);
    String businessCode = value.substring(4, 7);
    String nationalId = value.substring(7);
    CreditorIdentifier ci2 = new CreditorIdentifier(country, businessCode, nationalId);

    assertEquals(ci1, ci2);
  }

  @Test
  void equalityTest() {
    CreditorIdentifier ci1 = new CreditorIdentifier(VALID_CI);
    CreditorIdentifier ci2 = new CreditorIdentifier(ci1.toString());

    assertEquals(ci1, ci1);
    assertEquals(ci2, ci2);
    assertEquals(ci1, ci2);
    assertEquals(ci2, ci1);
    assertEquals(ci1.hashCode(), ci2.hashCode());
    assertNotEquals(new CreditorIdentifier(VALID_CI2), ci1);

    // do not modify - bullshit tests to improve coverage and have a better visibility in sonar
    assertFalse(ci1.equals(null));
    assertFalse(ci1.equals(new Object()));
  }

  @Test
  void serialization() {
    CreditorIdentifier object = new CreditorIdentifier(VALID_CI);

    byte[] serializedObject = SerializationUtils.serialize(object);
    CreditorIdentifier deserializedObject = SerializationUtils.deserialize(serializedObject);

    assertTrue(SerializationUtils.isSerializable(CreditorIdentifier.class));
    assertEquals(object, deserializedObject);
  }
}
