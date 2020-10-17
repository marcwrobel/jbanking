package fr.marcwrobel.jbanking.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Support class for various calendar computation algorithms. */
public abstract class CalendarSupport implements Calendar {

  /** Maximum number of iteration for date calculations before giving up. */
  private static final int MAX_ITERATIONS = 3650;

  /** @see Calendar#isBusinessDay(LocalDate) */
  @Override
  public boolean isBusinessDay(LocalDate date) {
    return !isHoliday(date);
  }

  /**
   * @see Calendar#previousBusinessDay(LocalDate)
   * @throws DateCalculationException if no business day could be found in the previous {@value
   *     MAX_ITERATIONS} days
   */
  @Override
  public LocalDate previousBusinessDay(LocalDate date) {
    int c = 0;

    LocalDate previous = date.minusDays(1);
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
   * @see Calendar#nextBusinessDay(LocalDate)
   * @throws DateCalculationException if no business day could be found in the next {@value
   *     MAX_ITERATIONS} days
   */
  @Override
  public LocalDate nextBusinessDay(LocalDate date) {
    int c = 0;

    LocalDate next = date.plusDays(1);
    while (isHoliday(next)) {
      next = next.plusDays(1);

      if (++c >= MAX_ITERATIONS) {
        throw new DateCalculationException(
            "no business day found within the next " + MAX_ITERATIONS + " days");
      }
    }

    return next;
  }

  /** @see Calendar#holidaysWithin(LocalDate, LocalDate) */
  @Override
  public List<LocalDate> holidaysWithin(LocalDate from, LocalDate to) {
    ensureFromIsNotAfterTo(from, to);
    List<LocalDate> occurrences = new ArrayList<>(0);

    for (LocalDate date = from; date.isBefore(to.plusDays(1)); date = date.plusDays(1)) {
      if (isHoliday(date)) {
        occurrences.add(date);
      }
    }

    return Collections.unmodifiableList(occurrences);
  }

  /** @see Calendar#businessDaysWithin(LocalDate, LocalDate) */
  @Override
  public List<LocalDate> businessDaysWithin(LocalDate from, LocalDate to) {
    ensureFromIsNotAfterTo(from, to);
    List<LocalDate> occurrences = new ArrayList<>(0);

    for (LocalDate date = from; date.isBefore(to.plusDays(1)); date = date.plusDays(1)) {
      if (isBusinessDay(date)) {
        occurrences.add(date);
      }
    }

    return Collections.unmodifiableList(occurrences);
  }

  private void ensureFromIsNotAfterTo(LocalDate from, LocalDate to) {
    if (from.isAfter(to)) {
      throw new IllegalArgumentException("from is after to");
    }
  }
}
