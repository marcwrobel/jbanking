package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Objects;

/**
 * A {@link Holiday} occurring the same day and month every year.
 *
 * <p>This class is useful for modeling holidays like Christmas.
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public final class MonthDayHoliday implements Holiday {

  private final MonthDay monthDay;

  /**
   * Create a new holiday using the given {@link MonthDay}.
   *
   * @param monthDay a non-null {@link MonthDay}
   * @throws NullPointerException if the given {@link MonthDay} is {@code null}
   */
  public MonthDayHoliday(MonthDay monthDay) {
    this.monthDay = requireNonNull(monthDay);
  }

  /**
   * Create a new holiday using the given month and day of month.
   *
   * @param month the month-of-year to represent, not null
   * @param dayOfMonth the day-of-month to represent
   * @throws java.time.DateTimeException if the value of any field is out of range, or if the
   *     day-of-month is invalid for the month
   */
  public MonthDayHoliday(Month month, int dayOfMonth) {
    this.monthDay = MonthDay.of(month, dayOfMonth);
  }

  /** @see Holiday#check(LocalDate) */
  @Override
  public boolean check(LocalDate date) {
    return monthDay.equals(MonthDay.from(date));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MonthDayHoliday that = (MonthDayHoliday) o;
    return monthDay.equals(that.monthDay);
  }

  @Override
  public int hashCode() {
    return Objects.hash(monthDay);
  }

  @Override
  public String toString() {
    return "MonthDayHoliday{" + "monthDay=" + monthDay + '}';
  }
}
