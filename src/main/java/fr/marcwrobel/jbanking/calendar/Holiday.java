package fr.marcwrobel.jbanking.calendar;

import java.time.LocalDate;

/**
 * Bank holidays are holidays when banks, and many other businesses, are closed for the day. This
 * interface is representing a bank holiday.
 *
 * <p>Subclasses of this interface are expected to be thread-safe and immutable.
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public interface Holiday {

  /**
   * Check whether the given date is an occurrence of this holiday.
   *
   * @param date the date to check.
   * @return <code>true</code> if the given date is an occurrence of this holiday, <code>false
   *     </code> otherwise.
   * @throws NullPointerException if the given date is {@code null}.
   */
  boolean check(LocalDate date);
}
