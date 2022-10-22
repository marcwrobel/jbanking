package fr.marcwrobel.jbanking.internal;

import static fr.marcwrobel.jbanking.internal.AsciiCharacters.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AsciiCharactersTest {

  @Test
  void isAlphabeticTest() {
    assertTrue(isAlphabetic('a'));
    assertTrue(isAlphabetic('A'));
    assertFalse(isAlphabetic('3'));
    assertFalse(isAlphabetic('-'));
    assertFalse(isAlphabetic('\n'));
    assertFalse(isAlphabetic('©'));
  }

  @Test
  void isUpperAlphabeticTest() {
    assertFalse(isUpperAlphabetic('a'));
    assertTrue(isUpperAlphabetic('A'));
    assertFalse(isUpperAlphabetic('3'));
    assertFalse(isUpperAlphabetic('-'));
    assertFalse(isUpperAlphabetic('\n'));
    assertFalse(isUpperAlphabetic('©'));
  }

  @Test
  void isLowerAlphabeticTest() {
    assertTrue(isLowerAlphabetic('a'));
    assertFalse(isLowerAlphabetic('A'));
    assertFalse(isLowerAlphabetic('3'));
    assertFalse(isLowerAlphabetic('-'));
    assertFalse(isLowerAlphabetic('\n'));
    assertFalse(isLowerAlphabetic('©'));
  }

  @Test
  void isNumericTest() {
    assertFalse(isNumeric('a'));
    assertFalse(isNumeric('A'));
    assertTrue(isNumeric('3'));
    assertFalse(isNumeric('-'));
    assertFalse(isNumeric('\n'));
    assertFalse(isNumeric('©'));
  }

  @Test
  void isAlphanumericTest() {
    assertTrue(isAlphanumeric('a'));
    assertTrue(isAlphanumeric('A'));
    assertTrue(isAlphanumeric('3'));
    assertFalse(isAlphanumeric('-'));
    assertFalse(isAlphanumeric('\n'));
    assertFalse(isAlphanumeric('©'));
  }

  @Test
  void isSpaceTest() {
    assertTrue(isSpace(' '));
    assertFalse(isSpace('a'));
    assertFalse(isSpace('A'));
    assertFalse(isSpace('3'));
    assertFalse(isSpace('-'));
    assertFalse(isSpace('\n'));
    assertFalse(isSpace('©'));
  }
}
