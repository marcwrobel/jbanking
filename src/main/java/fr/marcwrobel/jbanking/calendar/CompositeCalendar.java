package fr.marcwrobel.jbanking.calendar;

import static java.util.Arrays.asList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A {@link Calendar} that can combine multiple calendars into a single one.
 *
 * @since 3.0.0
 */
public final class CompositeCalendar implements Calendar {

  private final List<Calendar> calendars;

  /**
   * Creates a new instance using the given {@link Calendar}s.
   *
   * @param calendars the {@link Calendar}s to use in this composite calendar
   * @throws NullPointerException if {@code calendars} is {code null}
   * @throws IllegalArgumentException if {@code calendars} is empty
   */
  public CompositeCalendar(Collection<Calendar> calendars) {
    if (calendars.isEmpty()) {
      throw new IllegalArgumentException("calendars must not be empty");
    }

    this.calendars = new ArrayList<>(calendars);
  }

  /**
   * Creates a new instance using the given {@link Calendar}s.
   *
   * @param calendars the {@link Calendar}s to use in this composite calendar
   * @throws NullPointerException if {@code calendars} is {code null}
   * @throws IllegalArgumentException if {@code calendars} is empty
   */
  public CompositeCalendar(Calendar... calendars) {
    this(asList(calendars));
  }

  /**
   * @see Calendar#isHoliday(LocalDate)
   */
  @Override
  public boolean isHoliday(LocalDate date) {
    for (Calendar calendar : calendars) {
      if (calendar.isHoliday(date)) {
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

    for (Calendar calendar : calendars) {
      matchingHolidays.addAll(calendar.getHolidaysFor(date));
    }

    return matchingHolidays;
  }
}
