package fr.marcwrobel.jbanking.calendar;

import static java.time.DayOfWeek.MONDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

import java.time.LocalDate;
import java.time.MonthDay;

/**
 * A set of {@link Holiday}s that relates to common holidays.
 *
 * <p>This class is for internal use only.
 */
enum Holidays implements Holiday {

  /**
   * @see <a href="https://wikipedia.org/wiki/All_Saints%27_Day">Wikipedia</a>
   */
  ALL_SAINTS_DAY(new MonthDayHoliday(MonthDay.of(NOVEMBER, 1))),

  /**
   * @see <a href="https://wikipedia.org/wiki/Ascension_of_Jesus"Wikipedia</a>
   */
  ASCENSION_DAY(new RelativeHoliday(WesternEaster.INSTANCE, 39)),

  /**
   * @see <a href="https://wikipedia.org/wiki/Assumption_of_Mary">Wikipedia</a>
   */
  ASSUMPTION_OF_MARY(new MonthDayHoliday(MonthDay.of(AUGUST, 15))),

  /**
   * @see <a href="https://wikipedia.org/wiki/Armistice_Day">Wikipedia</a>
   */
  ARMISTICE_DAY(new MonthDayHoliday(MonthDay.of(NOVEMBER, 11))),

  /**
   * @see <a href="https://wikipedia.org/wiki/Boxing_Day">Wikipedia</a>
   */
  BOXING_DAY(new MonthDayHoliday(MonthDay.of(DECEMBER, 26))),

  /**
   * @see <a href="https://wikipedia.org/wiki/Christmas">Wikipedia</a>
   */
  CHRISTMAS_DAY(new MonthDayHoliday(MonthDay.of(DECEMBER, 25))),

  /**
   * @see <a href="https://wikipedia.org/wiki/Christmas_Eve">Wikipedia</a>
   */
  CHRISTMAS_EVE(new MonthDayHoliday(MonthDay.of(DECEMBER, 24))),

  /**
   * @see <a href="https://wikipedia.org/wiki/Easter_Monday">Wikipedia</a>
   */
  EASTER_MONDAY(new RelativeHoliday(WesternEaster.INSTANCE, 1)),

  /**
   * @see <a href="https://wikipedia.org/wiki/Europe_Day">Wikipedia</a>
   */
  EUROPE_DAY(new MonthDayHoliday(MonthDay.of(MAY, 9))),

  /**
   * @see <a href="https://wikipedia.org/wiki/Feast_of_Corpus_Christi">Wikipedia</a>
   */
  FEAST_OF_CORPUS_CHRISTI(new RelativeHoliday(WesternEaster.INSTANCE, 60)),

  /**
   * @see <a href="https://wikipedia.org/wiki/Good_Friday">Wikipedia</a>
   */
  GOOD_FRIDAY(new RelativeHoliday(WesternEaster.INSTANCE, -2)),

  /**
   * @see <a href="https://wikipedia.org/wiki/International_Workers%27_Day">Wikipedia</a>
   */
  INTERNATIONAL_WORKERS_DAY(new MonthDayHoliday(MonthDay.of(MAY, 1))),

  /**
   * @see <a href="https://wikipedia.org/wiki/May_Day">Wikipedia</a>
   */
  MAY_DAY(new DayOfWeekInMonthHoliday(1, MONDAY, MAY)),

  /**
   * @see <a href="https://wikipedia.org/wiki/New_Year%27s_Day">Wikipedia</a>
   */
  NEW_YEAR_DAY(new MonthDayHoliday(MonthDay.of(JANUARY, 1))),

  /**
   * @see <a href="https://wikipedia.org/wiki/New_Year%27s_Eve">Wikipedia</a>
   */
  NEW_YEAR_EVE(new MonthDayHoliday(MonthDay.of(DECEMBER, 31))),

  /**
   * @see <a href="https://wikipedia.org/wiki/Whit_Monday">Wikipedia</a>
   */
  PENTECOST_MONDAY(new RelativeHoliday(WesternEaster.INSTANCE, 50)),

  /**
   * @see <a href="https://wikipedia.org/wiki/Saint_Stephen%27s_Day">Wikipedia</a>
   */
  SAINT_STEPHENS_DAY(new MonthDayHoliday(MonthDay.of(DECEMBER, 26))),

  /**
   * @see <a href="https://wikipedia.org/wiki/Victory_in_Europe_Day">Wikipedia</a>
   */
  VICTORY_IN_EUROPE_DAY(new MonthDayHoliday(MonthDay.of(MAY, 8)));

  @SuppressWarnings(
      "ImmutableEnumChecker") // Holidays is thread-safe (given Holiday is thread-safe).
  private final Holiday holiday;

  Holidays(Holiday holiday) {
    this.holiday = holiday;
  }

  /**
   * @see Holiday#check(LocalDate)
   */
  @Override
  public boolean check(LocalDate date) {
    return holiday.check(date);
  }
}
