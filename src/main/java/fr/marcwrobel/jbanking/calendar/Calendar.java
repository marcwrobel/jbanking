package fr.marcwrobel.jbanking.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

  /** Maximum number of iteration for date calculations before giving up. */
  int MAX_ITERATIONS = 3650;

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
  default boolean isBusinessDay(LocalDate date) {
    return !isHoliday(date);
  }

  /**
   * Shifts the given date by the specified number of business days. If the given amount is
   *
   * <ul>
   *   <li>zero, the input date is returned,
   *   <li>positive, later business days are chosen
   *   <li>negative, earlier business days are chosen
   * </ul>
   *
   * @param date the date to shift
   * @param numberOfDays the number of business days to adjust by
   * @return a non-null date
   * @throws DateCalculationException if no business day could be found in a reasonable time
   */
  default LocalDate shift(LocalDate date, int numberOfDays) {
    LocalDate shifted = date;

    if (numberOfDays > 0) {
      for (int i = 0; i < numberOfDays; i++) {
        shifted = next(shifted);
      }
    } else if (numberOfDays < 0) {
      for (int i = 0; i > numberOfDays; i--) {
        shifted = previous(shifted);
      }
    }

    return shifted;
  }

  /**
   * Compute the previous business day before the given date (excluded).
   *
   * @param date a non-null date
   * @return a non-null date
   * @throws DateCalculationException if no business day could be found in a reasonable time
   */
  default LocalDate previous(LocalDate date) {
    return previousOrSame(date.minusDays(1));
  }

  /**
   * Compute the previous business day before the given date (included).
   *
   * @param date a non-null date
   * @return a non-null date
   * @throws DateCalculationException if no business day could be found in a reasonable time
   */
  default LocalDate previousOrSame(LocalDate date) {
    int c = 0;

    LocalDate previous = date;
    while (isHoliday(previous)) {
      previous = previous.minusDays(1);

      if (++c >= MAX_ITERATIONS) {
        throw new DateCalculationException(
            "no business day found within the previous " + MAX_ITERATIONS + " days");
      }
    }

    return previous;
  }

  /**
   * Compute the next business day after the given date (excluded).
   *
   * @param date a non-null date
   * @return a non-null date
   * @throws DateCalculationException if no business day could be found in a reasonable time
   */
  default LocalDate next(LocalDate date) {
    return nextOrSame(date.plusDays(1));
  }

  /**
   * Compute the next business day after the given date (included).
   *
   * @param date a non-null date
   * @return a non-null date
   * @throws DateCalculationException if no business day could be found in a reasonable time
   */
  default LocalDate nextOrSame(LocalDate date) {
    int c = 0;

    LocalDate next = date;
    while (isHoliday(next)) {
      next = next.plusDays(1);

      if (++c >= MAX_ITERATIONS) {
        throw new DateCalculationException(
            "no business day found within the next " + MAX_ITERATIONS + " days");
      }
    }

    return next;
  }

  /**
   * Compute the holidays between {@code from} (inclusive) and {@code to} (inclusive).
   *
   * @param from a non-null date
   * @param to a non-null date
   * @return a non-null and unmodifiable ordered list of dates
   * @throws IllegalArgumentException if {@code from} is after {@code to}
   */
  default List<LocalDate> holidaysWithin(LocalDate from, LocalDate to) {
    if (from.isAfter(to)) {
      throw new IllegalArgumentException("from is after to");
    }

    List<LocalDate> occurrences = new ArrayList<>(0);
    for (LocalDate date = from; date.isBefore(to.plusDays(1)); date = date.plusDays(1)) {
      if (isHoliday(date)) {
        occurrences.add(date);
      }
    }

    return Collections.unmodifiableList(occurrences);
  }

  /**
   * Compute the business days between {@code from} (inclusive) and {@code to} (inclusive).
   *
   * @param from a non-null date
   * @param to a non-null date
   * @return a non-null and unmodifiable ordered list of dates
   * @throws IllegalArgumentException if {@code from} is after {@code to}
   */
  default List<LocalDate> businessDaysWithin(LocalDate from, LocalDate to) {
    if (from.isAfter(to)) {
      throw new IllegalArgumentException("from is after to");
    }

    List<LocalDate> occurrences = new ArrayList<>(0);
    for (LocalDate date = from; date.isBefore(to.plusDays(1)); date = date.plusDays(1)) {
      if (isBusinessDay(date)) {
        occurrences.add(date);
      }
    }

    return Collections.unmodifiableList(occurrences);
  }
}
