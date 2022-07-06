package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

/**
 * A {@link Holiday} occurring the same day of the week in a month every year.
 *
 * <p>
 * This class is using {@link java.time.temporal.TemporalAdjusters#dayOfWeekInMonth(int, DayOfWeek)} under the hood.
 *
 * <p>
 * This class is useful for modeling holidays like <a href="https://wikipedia.org/wiki/Martin_Luther_King_Jr._Day">Martin Luther
 * King Jr. Day</a>.
 *
 * @author Marc Wrobel
 * @see TemporalAdjusters#dayOfWeekInMonth(int, DayOfWeek)
 * @since 2.1.0
 */
public final class DayOfWeekInMonthHoliday implements Holiday {

  private final int weekNumber;
  private final DayOfWeek dayOfWeek;
  private final Month month;

  /**
   * Create a new holiday using the given {@link DayOfWeek} and {@link Month}
   *
   * @param weekNumber the week within the month, unbounded but typically from -5 to 5
   * @param dayOfWeek a non-null {@link DayOfWeek}
   * @param month a non-null {@link Month}
   * @throws NullPointerException if the given {@link MonthDay} is {@code null}
   */
  public DayOfWeekInMonthHoliday(int weekNumber, DayOfWeek dayOfWeek, Month month) {
    this.weekNumber = weekNumber;
    this.dayOfWeek = requireNonNull(dayOfWeek);
    this.month = requireNonNull(month);
  }

  /**
   * @see Holiday#check(LocalDate)
   */
  @Override
  public boolean check(LocalDate date) {
    LocalDate dateForYear = dateForYear(date.getYear());
    return dateForYear.equals(date);
  }

  private LocalDate dateForYear(int year) {
    return LocalDate.of(year, month, 1).with(TemporalAdjusters.dayOfWeekInMonth(weekNumber, dayOfWeek));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DayOfWeekInMonthHoliday that = (DayOfWeekInMonthHoliday) o;
    return weekNumber == that.weekNumber && dayOfWeek == that.dayOfWeek && month == that.month;
  }

  @Override
  public int hashCode() {
    return Objects.hash(weekNumber, dayOfWeek, month);
  }

  @Override
  public String toString() {
    return "DayOfWeekInMonthHoliday{" + "weekNumber=" + weekNumber + ", dayOfWeek=" + dayOfWeek + ", month=" + month
        + '}';
  }
}
