package fr.marcwrobel.jbanking.internal;

import static fr.marcwrobel.jbanking.internal.Normalizer.trimUpperCase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NormalizerTest {

  @Test
  void nullNormalizeToNull() {
    assertNull(trimUpperCase(null));
  }

  @Test
  void emptyNormalizeToEmpty() {
    assertEquals("", trimUpperCase(""));
  }

  @Test
  void blankNormalizeToEmpty() {
    assertEquals("", trimUpperCase(" \t\n\f\r" + (char) 0x0B));
  }

  @Test
  void lowerCaseNormalizeToUpperCase() {
    assertEquals("ABC", trimUpperCase("abc"));
  }
}
