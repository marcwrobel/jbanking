package fr.marcwrobel.jbanking.swift;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import java.util.regex.Pattern;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class SwiftPatternCharacterRepresentationTest {

  @ParameterizedTest
  @EnumSource(SwiftPatternCharacterRepresentation.class)
  void from(SwiftPatternCharacterRepresentation expected) {
    Optional<SwiftPatternCharacterRepresentation> actual = SwiftPatternCharacterRepresentation.from(expected.qualifier());
    assertThat(actual).contains(expected);
  }

  @ParameterizedTest
  @EnumSource(SwiftPatternCharacterRepresentation.class)
  void has(SwiftPatternCharacterRepresentation representation) {
    for (char c : representation.alphabet().toCharArray()) {
      assertThat(representation.has(c)).isTrue();
    }
  }

  @ParameterizedTest
  @EnumSource(SwiftPatternCharacterRepresentation.class)
  void matches(SwiftPatternCharacterRepresentation representation) {
    Pattern pattern = Pattern.compile(representation.regex());

    for (char c : representation.alphabet().toCharArray()) {
      assertThat(pattern.matcher("" + c).matches()).isTrue();
    }
  }
}
