package fr.marcwrobel.jbanking.calendar;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A {@link Calendar} that can be programmatically configured.
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public final class ConfigurableCalendar implements Calendar {

  /** Maximum number of iteration for date calculations before giving up. */
  private static final int MAX_ITERATIONS = 3650;

  private final Set<Holiday> holidays;

  /**
   * Creates a new instance using the given bank {@link Holiday}s.
   *
   * @param holidays the {@link Holiday}s that the calendar will be using.
   * @throws NullPointerException if {@code holidays} is {code null} or if one of the holiday in
   *     {@code holidays} is {code null}.
   */
  public ConfigurableCalendar(Collection<Holiday> holidays) {
    Set<Holiday> copy = new HashSet<>(holidays.size());

    for (Holiday holiday : holidays) {
      copy.add(requireNonNull(holiday));
    }

    this.holidays = unmodifiableSet(copy);
  }

  /**
   * Creates a new instance using the given bank {@link Holiday}s.
   *
   * @param holidays the {@link Holiday}s that the calendar will be using.
   * @throws NullPointerException if {@code holidays} is {code null}.
   */
  public ConfigurableCalendar(Holiday... holidays) {
    this(asList(holidays));
  }

  /** @see Calendar#isHoliday(LocalDate) */
  @Override
  public boolean isHoliday(LocalDate date) {
    for (Holiday holiday : holidays) {
      if (holiday.check(date)) {
        return true;
      }
    }

    return false;
  }

  /** @see Calendar#getHolidaysFor(LocalDate) */
  @Override
  public Set<Holiday> getHolidaysFor(LocalDate date) {
    Set<Holiday> matchingHolidays = new HashSet<>(0);

    for (Holiday holiday : holidays) {
      if (holiday.check(date)) {
        matchingHolidays.add(holiday);
      }
    }

    return unmodifiableSet(matchingHolidays);
  }

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
