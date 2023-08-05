package fr.marcwrobel.jbanking.internal;

import static fr.marcwrobel.jbanking.internal.AsciiCharacters.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class AsciiCharactersTest {

  @Test
  void isAlphabeticTest() {
    assertThat(isAlphabetic('a')).isTrue();
    assertThat(isAlphabetic('A')).isTrue();
    assertThat(isAlphabetic('3')).isFalse();
    assertThat(isAlphabetic('-')).isFalse();
    assertThat(isAlphabetic('\n')).isFalse();
    assertThat(isAlphabetic('©')).isFalse();
  }

  @Test
  void isUpperAlphabeticTest() {
    assertThat(isUpperAlphabetic('a')).isFalse();
    assertThat(isUpperAlphabetic('A')).isTrue();
    assertThat(isUpperAlphabetic('3')).isFalse();
    assertThat(isUpperAlphabetic('-')).isFalse();
    assertThat(isUpperAlphabetic('\n')).isFalse();
    assertThat(isUpperAlphabetic('©')).isFalse();
  }

  @Test
  void isLowerAlphabeticTest() {
    assertThat(isLowerAlphabetic('a')).isTrue();
    assertThat(isLowerAlphabetic('A')).isFalse();
    assertThat(isLowerAlphabetic('3')).isFalse();
    assertThat(isLowerAlphabetic('-')).isFalse();
    assertThat(isLowerAlphabetic('\n')).isFalse();
    assertThat(isLowerAlphabetic('©')).isFalse();
  }

  @Test
  void isNumericTest() {
    assertThat(isNumeric('a')).isFalse();
    assertThat(isNumeric('A')).isFalse();
    assertThat(isNumeric('3')).isTrue();
    assertThat(isNumeric('-')).isFalse();
    assertThat(isNumeric('\n')).isFalse();
    assertThat(isNumeric('©')).isFalse();
  }

  @Test
  void isAlphanumericTest() {
    assertThat(isAlphanumeric('a')).isTrue();
    assertThat(isAlphanumeric('A')).isTrue();
    assertThat(isAlphanumeric('3')).isTrue();
    assertThat(isAlphanumeric('-')).isFalse();
    assertThat(isAlphanumeric('\n')).isFalse();
    assertThat(isAlphanumeric('©')).isFalse();
  }

  @Test
  void isSpaceTest() {
    assertThat(isSpace(' ')).isTrue();
    assertThat(isSpace('a')).isFalse();
    assertThat(isSpace('A')).isFalse();
    assertThat(isSpace('3')).isFalse();
    assertThat(isSpace('-')).isFalse();
    assertThat(isSpace('\n')).isFalse();
    assertThat(isSpace('©')).isFalse();
  }
}
