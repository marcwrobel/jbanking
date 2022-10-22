package fr.marcwrobel.jbanking.swift;

import static org.junit.jupiter.api.Assertions.*;

import fr.marcwrobel.jbanking.internal.SerializationUtils;
import org.junit.jupiter.api.Test;

class SwiftPatternTest {

  @Test
  void aSwiftPatternCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> SwiftPattern.compile(null));
  }

  @Test
  void aSwiftPatternMustBeWellFormed() {
    String invalidPattern = "2!n3d";

    SwiftPatternSyntaxException e = assertThrows(SwiftPatternSyntaxException.class,
        () -> SwiftPattern.compile(invalidPattern));
    assertEquals(invalidPattern, e.getExpression());
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
    assertTrue(pattern.matcher(value).matches());
  }

  private void assertNotMatches(String value, String expression) {
    SwiftPattern pattern = SwiftPattern.compile(expression);
    assertFalse(pattern.matcher(value).matches());
  }

  @Test
  void transformationTest() {
    String expression = "5!n";
    SwiftPattern pattern = SwiftPattern.compile(expression);
    assertEquals(expression, pattern.getExpression());
    assertEquals("^[0-9]{5}$", pattern.getEquivalentJavaPattern().pattern());
  }

  @Test
  void equalityTest() {
    SwiftPattern pattern1 = SwiftPattern.compile("4!n");
    SwiftPattern pattern2 = SwiftPattern.compile("4!n");

    assertEquals(pattern1, pattern1);
    assertEquals(pattern2, pattern2);

    assertEquals(pattern1, pattern2);
    assertEquals(pattern2, pattern1);
    assertEquals(pattern1.hashCode(), pattern2.hashCode());

    SwiftPattern pattern3 = SwiftPattern.compile("3!n");
    assertNotNull(pattern1);
    assertNotEquals(pattern1, new Object());
    assertNotEquals(pattern1, pattern3);
  }

  @Test
  void serialization() {
    SwiftPattern object = SwiftPattern.compile("2!n3!c1!a2e3c2n");

    byte[] serializedObject = SerializationUtils.serialize(object);
    SwiftPattern deserializedObject = SerializationUtils.deserialize(serializedObject);

    assertTrue(SerializationUtils.isSerializable(SwiftPattern.class));
    assertEquals(object, deserializedObject);
  }
}
