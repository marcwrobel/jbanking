package fr.marcwrobel.jbanking.swift;

import static java.util.Objects.requireNonNull;

import fr.marcwrobel.jbanking.internal.AsciiCharacters;
import java.util.Optional;

/**
 * The character representations in a {@link SwiftPattern}.
 *
 * @since 4.0.0
 */
public enum SwiftPatternCharacterRepresentation {
  /**
   * Digits (numeric characters 0 to 9 only).
   */
  DIGITS('n', "[0-9]") {
    @Override
    public boolean has(char c) {
      return AsciiCharacters.isNumeric(c);
    }
  },

  /**
   * Uppercase letters (alphabetic characters A-Z only).
   */
  UPPER_CASE_LETTERS('a', "[A-Z]") {
    @Override
    public boolean has(char c) {
      return AsciiCharacters.isUpperAlphabetic(c);
    }
  },

  /**
   * Uppercase and lowercase case alphanumeric characters (A-Z, a-z and 0-9).
   */
  UPPER_AND_LOWER_CASE_ALPHANUMERICS('c', "[a-zA-Z0-9]") {
    @Override
    public boolean has(char c) {
      return AsciiCharacters.isAlphanumeric(c);
    }
  },

  /**
   * Blank space.
   */
  SPACES('e', "[ ]") {
    @Override
    public boolean has(char c) {
      return c == ' ';
    }
  };

  public static Optional<SwiftPatternCharacterRepresentation> from(char qualifier) {
    for (SwiftPatternCharacterRepresentation characters : values()) {
      if (characters.qualifier == qualifier) {
        return Optional.of(characters);
      }
    }

    return Optional.empty();
  }

  private final char qualifier;
  private final String regex;

  SwiftPatternCharacterRepresentation(char qualifier, String regex) {
    this.qualifier = qualifier;
    this.regex = requireNonNull(regex);
  }

  /**
   * Returns this character representation qualifier.
   *
   * @return a single character
   */
  public char qualifier() {
    return qualifier;
  }

  /**
   * Returns this character representation regular expression.
   *
   * @return a non-null String
   */
  public String regex() {
    return regex;
  }

  /**
   * Checks whether the given character belongs to this character representation.
   *
   * @return {@code true} if the given character belongs to this character representation, {@code false} otherwise
   */
  public abstract boolean has(char c);
}
