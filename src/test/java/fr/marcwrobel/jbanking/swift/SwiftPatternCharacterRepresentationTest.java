package fr.marcwrobel.jbanking.swift;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.regex.Pattern;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class SwiftPatternCharacterRepresentationTest {

  @ParameterizedTest
  @EnumSource(SwiftPatternCharacterRepresentation.class)
  void from(SwiftPatternCharacterRepresentation expected) {
    Optional<SwiftPatternCharacterRepresentation> actual = SwiftPatternCharacterRepresentation.from(expected.qualifier());

    assertTrue(actual.isPresent());
    assertEquals(expected, actual.get());
  }

  @ParameterizedTest
  @EnumSource(SwiftPatternCharacterRepresentation.class)
  void has(SwiftPatternCharacterRepresentation representation) {
    for (char c : representation.alphabet().toCharArray()) {
      assertTrue(representation.has(c));
    }
  }

  @ParameterizedTest
  @EnumSource(SwiftPatternCharacterRepresentation.class)
  void matches(SwiftPatternCharacterRepresentation representation) {
    Pattern pattern = Pattern.compile(representation.regex());

    for (char c : representation.alphabet().toCharArray()) {
      assertTrue(pattern.matcher("" + c).matches());
    }
  }
}
