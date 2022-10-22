package fr.marcwrobel.jbanking.bic;

import static fr.marcwrobel.jbanking.internal.TestUtils.BLANK;
import static org.junit.jupiter.api.Assertions.*;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.internal.SerializationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BicTest {

  @Test
  void nullIsNotValid() {
    assertFalse(Bic.isValid(null));
  }

  @Test
  void cannotCreateWithNull() {
    assertThrows(IllegalArgumentException.class, () -> new Bic(null));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      // invalid
      "", BLANK,
      // malformed
      "FR", "BNPA", "BNPAFR", "BNPAPPXXX", "AAAAAAA", "BNP!FRPPXXX", "BNPA!RPPXXX", "BNPAFR!PXXX", "BNPAFRPPXX!",
      // unknown country
      "BNPAFGPPXXX" })
  void invalidInputIsNotValid(String s) {
    assertFalse(Bic.isValid(s));
  }

  @ParameterizedTest
  @ValueSource(strings = { BLANK, "BNPAFR", "BNPAPPXXX", "AAAAAAA" })
  void cannotCreateWithInvalidInput(String s) {
    BicFormatException e = assertThrows(BicFormatException.class, () -> new Bic(s));
    assertEquals(s, e.getInputString());
    assertTrue(e.getMessage().contains("format"));
  }

  @Test
  void cannotCreateWithUnknownCountry() {
    BicFormatException e = assertThrows(BicFormatException.class, () -> new Bic("BNPAFGPPXXX"));
    assertEquals("BNPAFGPPXXX", e.getInputString());
    assertTrue(e.getMessage().contains("country"));
  }

  @ParameterizedTest
  @ValueSource(strings = { "BNPAFRPPXXX", "BNPAFRPP", "bnpafrpp", " BNPAFRPP ", " bnpafrpp " })
  void canCreateAndValidateWithValidInput(String s) {
    assertTrue(Bic.isValid(s));

    Bic bic = new Bic(s);
    assertEquals(IsoCountry.FR, bic.getCountry());
    assertEquals("FR", bic.getCountryCode());
    assertEquals("BNPA", bic.getInstitutionCode());
    assertEquals("XXX", bic.getBranchCode());
    assertEquals("PP", bic.getLocationCode());
    assertEquals("BNPAFRPPXXX", bic.toString());
    assertFalse(bic.isTestBic());
  }

  @Test
  void liveBicTest() {
    Bic liveBic = new Bic("BNPAFRPPXXX");
    assertTrue(liveBic.isLiveBic());
    assertFalse(liveBic.isTestBic());

    Bic testBic = liveBic.asTestBic();
    assertTrue(testBic.isTestBic());
    assertEquals(new Bic("BNPAFRP0XXX"), testBic);
  }

  @Test
  void equalityTest() {
    Bic bic1 = new Bic("BNPAFRPP");
    Bic bic2 = new Bic("BNPAFRPPXXX");

    assertEquals(bic1, bic1);
    assertEquals(bic2, bic2);

    assertEquals(bic1, bic2);
    assertEquals(bic2, bic1);
    assertEquals(bic1.hashCode(), bic2.hashCode());

    // do not modify - bullshit tests to improve coverage and have a better visibility in sonar
    assertFalse(bic1.equals(null));
    assertFalse(bic1.equals(new Object()));
  }

  @Test
  void serialization() {
    Bic object = new Bic("BNPAFRPPXXX");

    byte[] serializedObject = SerializationUtils.serialize(object);
    Bic deserializedObject = SerializationUtils.deserialize(serializedObject);

    assertTrue(SerializationUtils.isSerializable(Bic.class));
    assertEquals(object, deserializedObject);
  }
}
