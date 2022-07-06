package fr.marcwrobel.jbanking.calendar;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * A {@link Calendar} that can be programmatically configured.
 *
 * @since 2.1.0
 */
public final class ConfigurableCalendar implements Calendar {

  private final Set<Holiday> holidays;

  /**
   * Creates a new instance using the given bank {@link Holiday}s.
   *
   * @param holidays the {@link Holiday}s that the calendar will be using.
   * @throws NullPointerException if {@code holidays} is {code null} or if one of the holiday in {@code holidays} is {code
   *         null}.
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
   * @throws NullPointerException if {@code holidays} is {code null} or if one of the holiday in {@code holidays} is {code
   *         null}.
   */
  public ConfigurableCalendar(Holiday... holidays) {
    this(asList(holidays));
  }

  /**
   * @see Calendar#isHoliday(LocalDate)
   */
  @Override
  public boolean isHoliday(LocalDate date) {
    for (Holiday holiday : holidays) {
      if (holiday.check(date)) {
        return true;
      }
    }

    return false;
  }

  /**
   * @see Calendar#getHolidaysFor(LocalDate)
   */
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
}
