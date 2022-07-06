package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * A {@link Holiday} occurring the same day every week.
 *
 * <p>
 * This class is useful for modeling <a href="https://wikipedia.org/wiki/Workweek_and_weekend">weekends</a>.
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public enum DayOfWeekHoliday implements Holiday {
  /**
   * Monday is the day of the week between Sunday and Tuesday. It seems it is never a weekend day.
   *
   * @see <a href="https://wikipedia.org/wiki/Monday">Wikipedia</a>
   */
  MONDAY(DayOfWeek.MONDAY),

  /**
   * Tuesday is the day of the week between Monday and Wednesday. It seems it is never a weekend day.
   *
   * @see <a href="https://wikipedia.org/wiki/Tuesday">Wikipedia</a>
   */
  TUESDAY(DayOfWeek.TUESDAY),

  /**
   * Wednesday is the day of the week between Tuesday and Thursday. It seems it is never a weekend day.
   *
   * @see <a href="https://wikipedia.org/wiki/Wednesday">Wikipedia</a>
   */
  WEDNESDAY(DayOfWeek.WEDNESDAY),

  /**
   * Thursday is the day of the week between Wednesday and Friday. It seems is never a weekend day.
   *
   * @see <a href="https://wikipedia.org/wiki/Thursday">Wikipedia</a>
   */
  THURSDAY(DayOfWeek.THURSDAY),

  /**
   * Friday is the day of the week between Thursday and Saturday. Friday is the first day of the weekend in some countries, with
   * Saturday the second.
   *
   * @see <a href="https://wikipedia.org/wiki/Friday">Wikipedia</a>
   */
  FRIDAY(DayOfWeek.FRIDAY),

  /**
   * Saturday is the day of the week between Friday and Sunday. In most countries, Saturday is a weekend day.
   *
   * @see <a href="https://wikipedia.org/wiki/Saturday">Wikipedia</a>
   */
  SATURDAY(DayOfWeek.SATURDAY),

  /**
   * Sunday is the day of the week between Saturday and Monday. Sunday is a day of rest in most Western countries, and a part of
   * the weekend.
   *
   * @see <a href="https://wikipedia.org/wiki/Sunday">Wikipedia</a>
   */
  SUNDAY(DayOfWeek.SUNDAY);

  private final DayOfWeek dayOfWeek;

  DayOfWeekHoliday(DayOfWeek dayOfWeek) {
    this.dayOfWeek = requireNonNull(dayOfWeek);
  }

  /**
   * @see Holiday#check(LocalDate)
   */
  @Override
  public boolean check(LocalDate date) {
    return dayOfWeek.equals(DayOfWeek.from(date));
  }
}
