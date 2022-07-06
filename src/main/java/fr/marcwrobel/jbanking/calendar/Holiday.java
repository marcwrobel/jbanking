package fr.marcwrobel.jbanking.calendar;

import java.time.LocalDate;

/**
 * Bank holidays are holidays when banks, and many other businesses, are closed for the day. This interface is representing a
 * public holiday.
 *
 * <p>
 * Subclasses of this interface are expected to be thread-safe and immutable.
 *
 * @since 2.1.0
 */
public interface Holiday {

  /**
   * Check whether the given date is an occurrence of this holiday.
   *
   * @param date the date to check.
   * @return {@code true} if the given date is an occurrence of this holiday, {@code false} otherwise.
   * @throws NullPointerException if the given date is {@code null}.
   */
  boolean check(LocalDate date);
}
