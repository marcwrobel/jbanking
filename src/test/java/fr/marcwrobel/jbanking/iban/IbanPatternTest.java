package fr.marcwrobel.jbanking.iban;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import fr.marcwrobel.jbanking.swift.SwiftPatternSyntaxException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class IbanPatternTest {

  @Test
  void patternCannotBeNull() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> IbanPattern.compile(null));
  }

  @Test
  void unknownCharacterClassAreForbidden() {
    String pattern = "2!n3!d";
    assertThatExceptionOfType(SwiftPatternSyntaxException.class)
        .isThrownBy(() -> IbanPattern.compile(pattern))
        .withMessageContaining("must match")
        .extracting(SwiftPatternSyntaxException::getExpression)
        .isEqualTo(pattern);
  }

  @Test
  void nonStrictGroupAreForbidden() {
    String pattern = "2n";
    assertThatExceptionOfType(SwiftPatternSyntaxException.class)
        .isThrownBy(() -> IbanPattern.compile(pattern))
        .withMessageContaining("not supported")
        .extracting(SwiftPatternSyntaxException::getExpression)
        .isEqualTo(pattern);
  }

  @Test
  void strictDigitsFormatValid() {
    assertMatches("12345", "5!n");
  }

  @ParameterizedTest
  @ValueSource(strings = { "", "1234", "123456", "abcde", "     " })
  void strictDigitsFormatInvalid(String s) {
    assertNotMatches(s, "5!n");
  }

  @Test
  void strictUpperCaseLettersFormatValid() {
    assertMatches("ABCDE", "5!a");
  }

  @ParameterizedTest
  @ValueSource(strings = { "", "ABCD", "ABCDEF", "12345", "     " })
  void strictUpperCaseLettersFormatInvalid(String s) {
    assertNotMatches(s, "5!a");
  }

  @ParameterizedTest
  @ValueSource(strings = { "12345", "abcde", "ABCDE", "Ab1De" })
  void strictUpperAndLowerCaseAlphanumericsFormatValid(String s) {
    assertMatches(s, "5!c");
  }

  @ParameterizedTest
  @ValueSource(strings = { "", "1234", "123456", "ABCD", "ABCDEF", "abcd", "abcdef", "Abc123", "Ab12", "     " })
  void strictUpperAndLowerCaseAlphanumericsFormatInvalid(String s) {
    assertNotMatches(s, "5!c");
  }

  @ParameterizedTest
  @ValueSource(strings = { "12abcA  ", "23ABCB  ", "78123C  ", "78xY5D  ", "90a1ZZ  " })
  void multipleStrictExpressionsValid(String s) {
    assertMatches(s, "2!n3!c1!a2!e");
  }

  @ParameterizedTest
  @ValueSource(strings = { "        ", "12345678", "abcdefgh", "ABCDEFGH", "123aBcA  ", "12aBA  " })
  void multipleStrictExpressionsInvalid(String s) {
    assertNotMatches(s, "2!n3!c1!a2!e");
  }

  private void assertMatches(String value, String expression) {
    IbanPattern pattern = IbanPattern.compile(expression);
    assertThat(pattern.matches(value)).isTrue();
  }

  private void assertNotMatches(String value, String expression) {
    IbanPattern pattern = IbanPattern.compile(expression);
    assertThat(pattern.matches(value)).isFalse();
  }

  @Test
  void equality() {
    IbanPattern pattern1 = IbanPattern.compile("4!n");
    IbanPattern pattern2 = IbanPattern.compile("4!n");

    assertThat(pattern1).isEqualTo(pattern1).isEqualTo(pattern2);

    assertThat(pattern2).isEqualTo(pattern1)
        .isEqualTo(pattern2)
        .hasSameHashCodeAs(pattern1);

    IbanPattern pattern3 = IbanPattern.compile("3!n");
    assertThat(pattern1).isNotNull();
    assertThat(new Object()).isNotEqualTo(pattern1);
    assertThat(pattern3).isNotEqualTo(pattern1);
  }
}
