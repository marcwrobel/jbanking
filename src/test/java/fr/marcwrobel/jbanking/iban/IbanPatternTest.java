package fr.marcwrobel.jbanking.iban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.marcwrobel.jbanking.iban.IbanPattern;
import fr.marcwrobel.jbanking.swift.SwiftPatternSyntaxException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for the {@link IbanPattern} class.
 */
class IbanPatternTest {

  @Test
  void patternCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> IbanPattern.compile(null));
  }

  @Test
  void unknownCharacterClassAreForbidden() {
    String pattern = "2!n3!d";

    SwiftPatternSyntaxException e = assertThrows(SwiftPatternSyntaxException.class, () -> IbanPattern.compile(pattern));

    assertEquals(pattern, e.getExpression());
    assertTrue(e.getMessage().contains("must match"));
  }

  @Test
  void nonStrictGroupAreForbidden() {
    String pattern = "2n";

    SwiftPatternSyntaxException e = assertThrows(SwiftPatternSyntaxException.class, () -> IbanPattern.compile(pattern));

    assertEquals(pattern, e.getExpression());
    assertTrue(e.getMessage().contains("not supported"));
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
    assertTrue(pattern.matches(value));
  }

  private void assertNotMatches(String value, String expression) {
    IbanPattern pattern = IbanPattern.compile(expression);
    assertFalse(pattern.matches(value));
  }

  @Test
  void equalityTest() {
    IbanPattern pattern1 = IbanPattern.compile("4!n");
    IbanPattern pattern2 = IbanPattern.compile("4!n");

    assertEquals(pattern1, pattern1);
    assertEquals(pattern2, pattern2);

    assertEquals(pattern1, pattern2);
    assertEquals(pattern2, pattern1);
    assertEquals(pattern1.hashCode(), pattern2.hashCode());

    IbanPattern pattern3 = IbanPattern.compile("3!n");
    assertNotNull(pattern1);
    assertNotEquals(pattern1, new Object());
    assertNotEquals(pattern1, pattern3);
  }
}
