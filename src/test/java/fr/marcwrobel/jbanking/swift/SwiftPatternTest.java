package fr.marcwrobel.jbanking.swift;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import fr.marcwrobel.jbanking.internal.SerializationUtils;
import org.junit.jupiter.api.Test;

class SwiftPatternTest {

  @Test
  void aSwiftPatternCannotBeNull() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> SwiftPattern.compile(null));
  }

  @Test
  void aSwiftPatternMustBeWellFormed() {
    String invalidPattern = "2!n3d";
    assertThatExceptionOfType(SwiftPatternSyntaxException.class)
        .isThrownBy(() -> SwiftPattern.compile(invalidPattern))
        .withMessageContaining("must match")
        .extracting(SwiftPatternSyntaxException::getExpression)
        .isEqualTo(invalidPattern);
  }

  @Test
  void digitsFormatTest() {
    String expression = "10n";
    assertMatches("1", expression);
    assertMatches("12", expression);
    assertNotMatches("01234567890", expression);
    assertNotMatches("1a", expression);
  }

  @Test
  void strictDigitsFormatTest() {
    String expression = "5!n";
    assertMatches("12345", expression);
    assertNotMatches("123", expression);
    assertNotMatches("123456", expression);
    assertNotMatches("1a", expression);
  }

  @Test
  void upperCaseLettersFormatTest() {
    String expression = "2a";
    assertMatches("A", expression);
    assertMatches("AB", expression);
    assertNotMatches("ABC", expression);
    assertNotMatches("1A", expression);
  }

  @Test
  void strictUpperCaseLettersFormatTest() {
    String expression = "5!a";
    assertMatches("ABCDE", expression);
    assertNotMatches("ABC", expression);
    assertNotMatches("ABCDEF", expression);
    assertNotMatches("1A", expression);
  }

  @Test
  void upperAndLowerCaseAlphanumericsFormatTest() {
    String expression = "2c";
    assertMatches("1", expression);
    assertMatches("A", expression);
    assertMatches("Ab", expression);
    assertMatches("12", expression);
    assertMatches("1A", expression);
    assertNotMatches("1!", expression);
    assertNotMatches("ABC", expression);
    assertNotMatches("AB1", expression);
  }

  @Test
  void strictUpperAndLowerCaseAlphanumericsFormatTest() {
    String expression = "5!c";
    assertMatches("Ab1De", expression);
    assertNotMatches("Ab1", expression);
    assertNotMatches("Ab1De3", expression);
    assertNotMatches("1111", expression);
    assertNotMatches("aaaaaa", expression);
  }

  @Test
  void multipleBasicExpressionTest() {
    String expression = "2!c18!c";
    assertMatches("01234567890123456789", expression);
    assertNotMatches("Ab1", expression);
    assertNotMatches("Ab1De3", expression);
    assertNotMatches("1111", expression);
    assertNotMatches("aaaaaa", expression);
  }

  @Test
  void mixedFormatTest() {
    String expression = "2!n3!c1!a2e";
    assertMatches("12a1cC  ", expression);
    assertNotMatches("12", expression);
    assertNotMatches("123", expression);
    assertNotMatches("1a", expression);
    assertNotMatches("12ac2", expression);
    assertNotMatches("12ac2  ", expression);
  }

  private void assertMatches(String value, String expression) {
    SwiftPattern pattern = SwiftPattern.compile(expression);
    assertThat(pattern.matcher(value).matches()).isTrue();
  }

  private void assertNotMatches(String value, String expression) {
    SwiftPattern pattern = SwiftPattern.compile(expression);
    assertThat(pattern.matcher(value).matches()).isFalse();
  }

  @Test
  void transformationTest() {
    String expression = "5!n";
    SwiftPattern pattern = SwiftPattern.compile(expression);
    assertThat(pattern.getExpression()).isEqualTo(expression);
    assertThat(pattern.getEquivalentJavaPattern().pattern()).isEqualTo("^[0-9]{5}$");
  }

  @Test
  void equality() {
    SwiftPattern pattern1 = SwiftPattern.compile("4!n");
    SwiftPattern pattern2 = SwiftPattern.compile("4!n");

    assertThat(pattern1).isEqualTo(pattern1).isEqualTo(pattern2);

    assertThat(pattern2)
        .isEqualTo(pattern1)
        .isEqualTo(pattern2)
        .hasSameHashCodeAs(pattern1);

    SwiftPattern pattern3 = SwiftPattern.compile("3!n");
    assertThat(pattern1).isNotNull();
    assertThat(new Object()).isNotEqualTo(pattern1);
    assertThat(pattern3).isNotEqualTo(pattern1);
  }

  @Test
  void serialization() {
    SwiftPattern object = SwiftPattern.compile("2!n3!c1!a2e3c2n");

    byte[] serializedObject = SerializationUtils.serialize(object);
    SwiftPattern deserializedObject = SerializationUtils.deserialize(serializedObject);

    assertThat(SerializationUtils.isSerializable(SwiftPattern.class)).isTrue();
    assertThat(deserializedObject).isEqualTo(object);
  }
}
