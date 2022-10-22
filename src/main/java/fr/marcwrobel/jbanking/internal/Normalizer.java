package fr.marcwrobel.jbanking.internal;

import java.util.Locale;

/**
 * Normalization operations on {@link String strings}, used in various jbanking classes.
 */
public final class Normalizer {

  private Normalizer() {
    // prevent instantiation
  }

  /**
   * Normalize the input string by removing all leading and trailing space ({@link String#trim()} and converting
   * lowercase characters to uppercase ({@link String#toUpperCase(Locale)}).
   *
   * <pre>
   *   Normalizer.trimUpperCase(null)    = null
   *   Normalizer.trimUpperCase("")      = ""
   *   Normalizer.trimUpperCase(" \t\n") = ""
   *   Normalizer.trimUpperCase("abc")   = "ABC"
   *   Normalizer.trimUpperCase(" aBc ") = "ABC"
   * </pre>
   *
   * @param s a string
   * @return a string (may be {@code null})
   */
  public static String trimUpperCase(String s) {
    return s == null ? null : s.trim().toUpperCase(Locale.ROOT);
  }
}
