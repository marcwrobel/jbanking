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
   *   CharUtils.isAsciiAlpha('a')  = true
   *   CharUtils.isAsciiAlpha('A')  = true
   *   CharUtils.isAsciiAlpha('3')  = false
   *   CharUtils.isAsciiAlpha('-')  = false
   *   CharUtils.isAsciiAlpha('\n') = false
   *   CharUtils.isAsciiAlpha('&copy;') = false
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
   *   CharUtils.isAsciiAlphaUpper('a')  = false
   *   CharUtils.isAsciiAlphaUpper('A')  = true
   *   CharUtils.isAsciiAlphaUpper('3')  = false
   *   CharUtils.isAsciiAlphaUpper('-')  = false
   *   CharUtils.isAsciiAlphaUpper('\n') = false
   *   CharUtils.isAsciiAlphaUpper('&copy;') = false
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
   *   CharUtils.isAsciiAlphaLower('a')  = true
   *   CharUtils.isAsciiAlphaLower('A')  = false
   *   CharUtils.isAsciiAlphaLower('3')  = false
   *   CharUtils.isAsciiAlphaLower('-')  = false
   *   CharUtils.isAsciiAlphaLower('\n') = false
   *   CharUtils.isAsciiAlphaLower('&copy;') = false
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
   *   CharUtils.isAsciiNumeric('a')  = false
   *   CharUtils.isAsciiNumeric('A')  = false
   *   CharUtils.isAsciiNumeric('3')  = true
   *   CharUtils.isAsciiNumeric('-')  = false
   *   CharUtils.isAsciiNumeric('\n') = false
   *   CharUtils.isAsciiNumeric('&copy;') = false
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
   *   CharUtils.isAsciiAlphanumeric('a')  = true
   *   CharUtils.isAsciiAlphanumeric('A')  = true
   *   CharUtils.isAsciiAlphanumeric('3')  = true
   *   CharUtils.isAsciiAlphanumeric('-')  = false
   *   CharUtils.isAsciiAlphanumeric('\n') = false
   *   CharUtils.isAsciiAlphanumeric('&copy;') = false
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
   *   CharUtils.isAsciiAlphanumeric(' ')  = true
   *   CharUtils.isAsciiAlphanumeric('A')  = false
   *   CharUtils.isAsciiAlphanumeric('3')  = false
   *   CharUtils.isAsciiAlphanumeric('-')  = false
   *   CharUtils.isAsciiAlphanumeric('\n') = false
   *   CharUtils.isAsciiAlphanumeric('&copy;') = false
   * </pre>
   *
   * @param c the character to check
   * @return {@code true} if equal to 32, {@code false} otherwise
   */
  public static boolean isSpace(final char c) {
    return c == ' ';
  }
}
