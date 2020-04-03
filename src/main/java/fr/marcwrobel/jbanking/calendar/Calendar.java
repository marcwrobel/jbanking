package fr.marcwrobel.jbanking.calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * A calendar that handles date calculations, taking bank {@link Holiday}s into account. Days that
 * are not bank holidays are called business days.
 *
 * <p>Subclasses of this interface are expected to be thread-safe and immutable.
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public interface Calendar {

  /**
   * Check whether the given date is a bank holiday.
   *
   * @param date a non-null date.
   * @return {@code true} if the given date is a holiday, {@code false} otherwise.
   */
  boolean isHoliday(LocalDate date);

  /**
   * Get all the {@link Holiday}s matching with the given day.
   *
   * @param date a non-null and unmodifiable set of {@link Holiday}s.
   * @return {@code true} if the given date is a holiday, {@code false} otherwise.
   */
  Set<Holiday> getHolidaysFor(LocalDate date);

  /**
   * Check whether the given date is a business day.
   *
   * @param date a non-null date.
   * @return {@code true} if the given date is a business day, {@code false} otherwise.
   */
  boolean isBusinessDay(LocalDate date);

  /**
   * Compute the previous business day before the given date (excluded).
   *
   * @param date a non-null date
   * @return a non-null date
   * @throws DateCalculationException if no business day could be found in a reasonable time
   */
  LocalDate previousBusinessDay(LocalDate date);

  /**
   * Compute the next business day after the given date (excluded).
   *
   * @param date a non-null date
   * @return a non-null date
   * @throws DateCalculationException if no business day could be found in a reasonable time
   */
  LocalDate nextBusinessDay(LocalDate date);

  /**
   * Compute the holidays between {@code from} (inclusive) and {@code to} (inclusive).
   *
   * @param from a non-null date
   * @param to a non-null date
   * @return a non-null and unmodifiable ordered list of dates
   * @throws IllegalArgumentException if {@code from} is after {@code to}
   */
  List<LocalDate> holidaysWithin(LocalDate from, LocalDate to);

  /**
   * Compute the business days between {@code from} (inclusive) and {@code to} (inclusive).
   *
   * @param from a non-null date
   * @param to a non-null date
   * @return a non-null and unmodifiable ordered list of dates
   * @throws IllegalArgumentException if {@code from} is after {@code to}
   */
  List<LocalDate> businessDaysWithin(LocalDate from, LocalDate to);
}
