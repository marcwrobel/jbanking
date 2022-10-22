package fr.marcwrobel.jbanking.internal;

/**
 * Operations on ASCII characters.
 *
 * <p>
 * Code from this class is an adaptation of <a href="https://commons.apache.org/proper/commons-lang/">Apache commons-lang</a>
 * {@code CharUtils}.
 */
public class AsciiCharacters {

  private AsciiCharacters() {
    // prevent instantiation
  }

  /**
   * <p>
   * Checks whether the character is ASCII 7 bit alphabetic.
   * </p>
   *
   * <pre>
   *   AsciiCharacters.isAlphabetic('a')  = true
   *   AsciiCharacters.isAlphabetic('A')  = true
   *   AsciiCharacters.isAlphabetic('3')  = false
   *   AsciiCharacters.isAlphabetic('-')  = false
   *   AsciiCharacters.isAlphabetic('\n') = false
   *   AsciiCharacters.isAlphabetic('&copy;') = false
   * </pre>
   *
   * @param c the character to check
   * @return {@code true} if between 65 and 90 or 97 and 122 inclusive, {@code false} otherwise
   */
  public static boolean isAlphabetic(final char c) {
    return isUpperAlphabetic(c) || isLowerAlphabetic(c);
  }

  /**
   * <p>
   * Checks whether the character is ASCII 7 bit alphabetic uppercase.
   * </p>
   *
   * <pre>
   *   AsciiCharacters.isUpperAlphabetic('a')  = false
   *   AsciiCharacters.isUpperAlphabetic('A')  = true
   *   AsciiCharacters.isUpperAlphabetic('3')  = false
   *   AsciiCharacters.isUpperAlphabetic('-')  = false
   *   AsciiCharacters.isUpperAlphabetic('\n') = false
   *   AsciiCharacters.isUpperAlphabetic('&copy;') = false
   * </pre>
   *
   * @param c the character to check
   * @return {@code true} if between 65 and 90 inclusive, {@code false} otherwise
   */
  public static boolean isUpperAlphabetic(final char c) {
    return c >= 'A' && c <= 'Z';
  }

  /**
   * <p>
   * Checks whether the character is ASCII 7 bit alphabetic lowercase.
   * </p>
   *
   * <pre>
   *   AsciiCharacters.isLowerAlphabetic('a')  = true
   *   AsciiCharacters.isLowerAlphabetic('A')  = false
   *   AsciiCharacters.isLowerAlphabetic('3')  = false
   *   AsciiCharacters.isLowerAlphabetic('-')  = false
   *   AsciiCharacters.isLowerAlphabetic('\n') = false
   *   AsciiCharacters.isLowerAlphabetic('&copy;') = false
   * </pre>
   *
   * @param c the character to check
   * @return {@code true} if between 97 and 122 inclusive, {@code false} otherwise
   */
  public static boolean isLowerAlphabetic(final char c) {
    return c >= 'a' && c <= 'z';
  }

  /**
   * <p>
   * Checks whether the character is ASCII 7 bit numeric.
   * </p>
   *
   * <pre>
   *   AsciiCharacters.isNumeric('a')  = false
   *   AsciiCharacters.isNumeric('A')  = false
   *   AsciiCharacters.isNumeric('3')  = true
   *   AsciiCharacters.isNumeric('-')  = false
   *   AsciiCharacters.isNumeric('\n') = false
   *   AsciiCharacters.isNumeric('&copy;') = false
   * </pre>
   *
   * @param c the character to check
   * @return {@code true} if between 48 and 57 inclusive, {@code false} otherwise
   */
  public static boolean isNumeric(final char c) {
    return c >= '0' && c <= '9';
  }

  /**
   * <p>
   * Checks whether the character is ASCII 7 bit alphabetic or numeric.
   * </p>
   *
   * <pre>
   *   AsciiCharacters.isAlphanumeric('a')  = true
   *   AsciiCharacters.isAlphanumeric('A')  = true
   *   AsciiCharacters.isAlphanumeric('3')  = true
   *   AsciiCharacters.isAlphanumeric('-')  = false
   *   AsciiCharacters.isAlphanumeric('\n') = false
   *   AsciiCharacters.isAlphanumeric('&copy;') = false
   * </pre>
   *
   * @param c the character to check
   * @return {@code true} if between 48 and 57 or 65 and 90 or 97 and 122 inclusive, {@code false} otherwise
   */
  public static boolean isAlphanumeric(final char c) {
    return isAlphabetic(c) || isNumeric(c);
  }

  /**
   * <p>
   * Checks whether the character is ASCII 7 bit space.
   * </p>
   *
   * <pre>
   *   AsciiCharacters.isSpace(' ')  = true
   *   AsciiCharacters.isSpace('A')  = false
   *   AsciiCharacters.isSpace('3')  = false
   *   AsciiCharacters.isSpace('-')  = false
   *   AsciiCharacters.isSpace('\n') = false
   *   AsciiCharacters.isSpace('&copy;') = false
   * </pre>
   *
   * @param c the character to check
   * @return {@code true} if equal to 32, {@code false} otherwise
   */
  public static boolean isSpace(final char c) {
    return c == ' ';
  }
}
