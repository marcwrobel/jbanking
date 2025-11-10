package fr.marcwrobel.jbanking.bic;

import fr.marcwrobel.jbanking.IsoCountry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static fr.marcwrobel.jbanking.internal.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class BicTest {

  @Test
  void nullIsNotValid() {
    assertThat(Bic.isValid(null)).isFalse();
  }

  @Test
  void cannotCreateWithNull() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Bic(null));
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
    assertThat(Bic.isValid(s)).isFalse();
  }

  @ParameterizedTest
  @ValueSource(strings = { BLANK, "BNPAFR", "BNPAPPXXX", "BNPAF1PPXXX", "AAAAAAA" })
  void cannotCreateWithInvalidInput(String s) {
    assertThatExceptionOfType(BicFormatException.class)
        .isThrownBy(() -> new Bic(s))
        .withMessageContaining("format")
        .extracting(BicFormatException::getInputString)
        .isEqualTo(s);
  }

  @Test
  void cannotCreateWithUnknownCountry() {
    assertThatExceptionOfType(BicFormatException.class)
        .isThrownBy(() -> new Bic("BNPAFGPPXXX"))
        .withMessageContaining("country")
        .extracting(BicFormatException::getInputString)
        .isEqualTo("BNPAFGPPXXX");
  }

  @ParameterizedTest
  @ValueSource(strings = { "BNPAFRPPXXX", "BNPAFRPP", "BNPAfrPPXxx", "bnpafrpp", " BNPAFRPP ", " bnpafrpp " })
  void canCreateAndValidateWithValidInput(String s) {
    assertThat(Bic.isValid(s)).isTrue();

    Bic bic = new Bic(s);
    assertThat(bic.getCountry()).isEqualTo(IsoCountry.FR);
    assertThat(bic.getCountryCode()).isEqualTo("FR");
    assertThat(bic.getInstitutionCode()).isEqualTo("BNPA");
    assertThat(bic.getBranchCode()).isEqualTo("XXX");
    assertThat(bic.getLocationCode()).isEqualTo("PP");
    assertThat(bic).hasToString("BNPAFRPPXXX");
    assertThat(bic.isTestBic()).isFalse();
  }

  @Test
  void allowNumericInInstitutionCode() {
    assertThat(Bic.isValid("WG11US335AB")).isTrue();
  }

  @Test
  void liveBicTest() {
    Bic liveBic = new Bic("BNPAFRPPXXX");
    assertThat(liveBic.isLiveBic()).isTrue();
    assertThat(liveBic.isTestBic()).isFalse();

    Bic testBic = liveBic.asTestBic();
    assertThat(testBic.isTestBic()).isTrue();
    assertThat(testBic).isEqualTo(new Bic("BNPAFRP0XXX"));
  }

  @Test
  void equality() {
    testEquality(new Bic("BNPAFRPP"), new Bic("BNPAFRPPXXX"));
  }

  @Test
  void serialization() {
    testSerialization(new Bic("BNPAFRPPXXX"));
  }
}
