package fr.marcwrobel.jbanking.internal;

import static fr.marcwrobel.jbanking.internal.Normalizer.trimUpperCase;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class NormalizerTest {

  @Test
  void nullNormalizeToNull() {
    assertThat(trimUpperCase(null)).isNull();
  }

  @Test
  void emptyNormalizeToEmpty() {
    assertThat(trimUpperCase("")).isEmpty();
  }

  @Test
  void blankNormalizeToEmpty() {
    assertThat(trimUpperCase(" \t\n\f\r" + (char) 0x0B)).isEmpty();
  }

  @Test
  void lowerCaseNormalizeToUpperCase() {
    assertThat(trimUpperCase("abc")).isEqualTo("ABC");
  }
}
