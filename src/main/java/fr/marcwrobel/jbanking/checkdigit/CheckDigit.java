package fr.marcwrobel.jbanking.checkdigit;

/**
 * A check digit is a form of redundancy check used for error detection on identification numbers, such as bank account
 * numbers, which are used in an application where they will at least sometimes be input manually. It consists of one
 * or more digits (or letters) computed by an algorithm from the other digits (or letters) in the sequence input.
 *
 * <p>
 * With a check digit, one can detect simple errors in the input of a series of characters (usually digits) such as a
 * single mistyped digit or some permutations of two successive digits.
 *
 * <p>
 * A class that implements this interface implements one of the many existing algorithms for calculating and validating
 * check digits, such as one defined in <a href="https://wikipedia.org/wiki/ISO/IEC_7064">ISO/IEC 7064</a>.
 *
 * @see <a href="https://wikipedia.org/wiki/Check_digit">Check digit</a>
 */
public interface CheckDigit {

  /**
   * Calculates the check digit for a given input string.
   *
   * @param input the non-null input string to calculate the check digit for
   * @return a non-null string
   * @throws IllegalArgumentException if the check digit cannot be computed for the given input string (because it is
   *         {@code null}, to short...)
   */
  String calculate(String input);

  /**
   * Validates the check digit for the given input string.
   *
   * <p>
   * Invalid input strings, such as {@code null}, are considered invalid and do not throw any exception.
   *
   * @param input the input string to validate, may be null
   * @return {@code true} if the check digit is verified, {@code false} otherwise
   */
  boolean validate(String input);

}
