package fr.marcwrobel.jbanking.swift;

import static java.util.Objects.requireNonNull;

import fr.marcwrobel.jbanking.internal.AsciiCharacters;
import fr.marcwrobel.jbanking.internal.CharacterPredicate;
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
  DIGITS('n', "[0-9]", "0123456789", AsciiCharacters::isNumeric),

  /**
   * Uppercase letters (alphabetic characters A-Z only).
   */
  UPPER_CASE_LETTERS('a', "[A-Z]", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", AsciiCharacters::isUpperAlphabetic),

  /**
   * Uppercase and lowercase case alphanumeric characters (A-Z, a-z and 0-9).
   */
  UPPER_AND_LOWER_CASE_ALPHANUMERICS('c', "[a-zA-Z0-9]", "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
      AsciiCharacters::isAlphanumeric),

  /**
   * Blank space.
   */
  SPACES('e', "[ ]", " ", AsciiCharacters::isSpace);

  /**
   * Get the corresponding representation for the given qualifier.
   *
   * @param qualifier a char
   * @return a non-null optional SwiftPatternCharacterRepresentation
   */
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
  private final String alphabet;
  private final CharacterPredicate predicate;

  SwiftPatternCharacterRepresentation(char qualifier, String regex, String alphabet, CharacterPredicate predicate) {
    this.qualifier = qualifier;
    this.regex = requireNonNull(regex);
    this.alphabet = requireNonNull(alphabet);
    this.predicate = requireNonNull(predicate);
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
   * Returns this character representation alphabet, i.e. a String composed of all the characters in the representation.
   *
   * @return a non-null string
   */
  public String alphabet() {
    return alphabet;
  }

  /**
   * Returns this character representation regular expression.
   *
   * @return a non-null string
   */
  public String regex() {
    return regex;
  }

  /**
   * Checks whether the given character belongs to this character representation.
   *
   * @param c the char to test
   * @return {@code true} if the given character belongs to this character representation, {@code false} otherwise
   */
  public boolean has(char c) {
    return predicate.test(c);
  }
}
