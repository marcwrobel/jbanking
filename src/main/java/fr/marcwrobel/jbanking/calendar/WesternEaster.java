package fr.marcwrobel.jbanking.calendar;

import java.time.LocalDate;

/**
 * This class is modeling the <a href="https://wikipedia.org/wiki/Easter">western easter sunday</a>.
 *
 * <p>The date of Easter Sunday is computed with the Meeus/Jones/Butcher Gregorian algorithm.
 *
 * @see <a
 *     href="http://wikipedia.org/wiki/Computus#Meeus.2FJones.2FButcher_Gregorian_algorithm">Meeus/Jones/Butcher
 *     Gregorian algorithm</a>
 * @author Marc Wrobel
 * @since 2.1.0
 */
public enum WesternEaster implements Holiday {
  INSTANCE;

  /** @see Holiday#check(LocalDate) */
  @Override
  public boolean check(LocalDate date) {
    return meeusJonesButcherComputus(date.getYear()).equals(date);
  }

  private static LocalDate meeusJonesButcherComputus(final int year) {
    final int a = year % 19;
    final int b = year / 100;
    final int c = year % 100;
    final int d = b / 4;
    final int e = b % 4;
    final int f = (b + 8) / 25;
    final int g = (b - f + 1) / 3;
    final int h = (19 * a + b - d - g + 15) % 30;
    final int i = c / 4;
    final int k = c % 4;
    final int l = (32 + 2 * e + 2 * i - h - k) % 7;
    final int m = (a + 11 * h + 22 * l) / 451;
    final int n = h + l - 7 * m + 114;
    final int month = n / 31;
    final int day = (n % 31) + 1;
    return LocalDate.of(year, month, day);
  }
}
