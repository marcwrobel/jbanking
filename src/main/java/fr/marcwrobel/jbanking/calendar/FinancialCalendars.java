package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.DayOfWeekHoliday.FRIDAY;
import static fr.marcwrobel.jbanking.calendar.DayOfWeekHoliday.SATURDAY;
import static fr.marcwrobel.jbanking.calendar.DayOfWeekHoliday.SUNDAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.ALL_SAINTS_DAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.ARMISTICE_DAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.ASCENSION_DAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.ASSUMPTION_OF_MARY;
import static fr.marcwrobel.jbanking.calendar.Holidays.BOXING_DAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.CHRISTMAS_DAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.CHRISTMAS_EVE;
import static fr.marcwrobel.jbanking.calendar.Holidays.EASTER_MONDAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.FEAST_OF_CORPUS_CHRISTI;
import static fr.marcwrobel.jbanking.calendar.Holidays.GOOD_FRIDAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.INTERNATIONAL_WORKERS_DAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.NEW_YEAR_DAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.NEW_YEAR_EVE;
import static fr.marcwrobel.jbanking.calendar.Holidays.PENTECOST_MONDAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.SAINT_STEPHENS_DAY;
import static fr.marcwrobel.jbanking.calendar.Holidays.VICTORY_IN_EUROPE_DAY;
import static fr.marcwrobel.jbanking.calendar.ShiftingStrategy.CLOSEST_WEEKDAY;
import static fr.marcwrobel.jbanking.calendar.ShiftingStrategy.PLUS_TWO_DAYS;
import static fr.marcwrobel.jbanking.calendar.ShiftingStrategy.SUNDAY_TO_MONDAY;
import static fr.marcwrobel.jbanking.calendar.ShiftingStrategy.WEEKEND_TO_MONDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Set;

/**
 * A non-exhaustive list of {@link Calendar}s for some of the most important <a
 * href="https://wikipedia.org/wiki/List_of_financial_districts">financial districts</a> or
 * financial systems in the world.
 *
 * <p>For the time being only financial districts or financial systems that express their holidays
 * using the <a href="https://wikipedia.org/wiki/Gregorian_calendar">gregorian calendar</a> are
 * supported.
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public enum FinancialCalendars implements Calendar {

  /**
   * A special calendar with no holidays and no weekends. All days are business days. It may be used
   * to indicate that a holiday calendar does not apply.
   */
  NO_HOLIDAYS(new ConfigurableCalendar()),

  /**
   * A special calendar with no holidays but with saturday and sunday as weekends.
   *
   * <p>Note that not all countries use saturday and sunday weekends.
   */
  SATURDAY_SUNDAY(new ConfigurableCalendar(SATURDAY, SUNDAY)),

  /**
   * A special calendar with no holidays but with saturday and sunday as weekends.
   *
   * <p>Note that not all countries use saturday and sunday weekends.
   */
  FRIDAY_SATURDAY(new ConfigurableCalendar(FRIDAY, SATURDAY)),

  /**
   * Frankfurt (Germany) financial district holidays.
   *
   * <p>Note that 2017 was the 500th anniversary of the act by Martin Luther which stated the
   * Reformation. In Germany, the 31st of October was a national holiday that year. More on <a
   * href="https://www.bbc.com/news/world-europe-41817418">this bbc.com article</a>.
   *
   * <p>This calendar is valid from 2000 until further notice. Last verification occurred on
   * 2020-08-09.
   *
   * @see <a
   *     href="https://www.tradinghours.com/exchanges/fsx/market-holidays/2020">tradinghours.com</a>
   * @see <a href="https://www.timeanddate.com/holidays/germany/">timeanddate.com</a>
   */
  FRANKFURT(
      new ConfigurableCalendar(
          SATURDAY,
          SUNDAY,
          NEW_YEAR_DAY,
          GOOD_FRIDAY,
          EASTER_MONDAY,
          INTERNATIONAL_WORKERS_DAY,
          ASCENSION_DAY,
          PENTECOST_MONDAY,
          FEAST_OF_CORPUS_CHRISTI,
          // https://wikipedia.org/wiki/German_Unity_Day
          new MonthDayHoliday(MonthDay.of(OCTOBER, 3)),
          // https://wikipedia.org/wiki/Reformation_Day - was a national holiday only on 2017
          new YearRangeHoliday(
              new MonthDayHoliday(MonthDay.of(OCTOBER, 31)), ValueRange.of(2017, 2017)),
          CHRISTMAS_EVE,
          CHRISTMAS_DAY,
          SAINT_STEPHENS_DAY,
          NEW_YEAR_EVE)),

  /**
   * London (United Kingdom) financial district holidays.
   *
   * <p>This calendar is valid from 2000 until further notice. Last verification occurred on
   * 2020-08-09.
   *
   * @see <a href="https://www.timeanddate.com/holidays/uk/">timeanddate.com</a>
   */
  LONDON(
      new ConfigurableCalendar(
          SATURDAY,
          SUNDAY,
          new ShiftedHoliday(NEW_YEAR_DAY, WEEKEND_TO_MONDAY),
          GOOD_FRIDAY,
          EASTER_MONDAY,
          new MovedHoliday(Holidays.MAY_DAY, LocalDate.of(2020, 5, 4), LocalDate.of(2020, 5, 8)),
          // https://www.timeanddate.com/holidays/uk/spring-bank-holiday - suppression for jubilees
          new SuppressedHoliday(new DayOfWeekInMonthHoliday(-1, MONDAY, MAY), 2002, 2012, 2022),
          // https://www.timeanddate.com/holidays/uk/summer-bank-holiday
          new DayOfWeekInMonthHoliday(-1, MONDAY, AUGUST),
          new ShiftedHoliday(CHRISTMAS_DAY, PLUS_TWO_DAYS),
          new ShiftedHoliday(BOXING_DAY, PLUS_TWO_DAYS),
          // golden jubilee
          new FixedHoliday(LocalDate.of(2002, 6, 3), LocalDate.of(2002, 6, 4)),
          // royal wedding
          new FixedHoliday(LocalDate.of(2011, 4, 29)),
          // golden jubilee
          new FixedHoliday(LocalDate.of(2012, 6, 4), LocalDate.of(2012, 6, 5)),
          // platinum jubilee
          new FixedHoliday(LocalDate.of(2022, 6, 2), LocalDate.of(2022, 6, 3)))),

  /**
   * Federal Reserve Bank of New York holidays.
   *
   * <p>This calendar is valid from 2000 until further notice. Last verification occurred on
   * 2020-08-09.
   *
   * @see <a href="https://www.newyorkfed.org/aboutthefed/holiday_schedule.html">newyorkfed.org</a>
   */
  NEW_YORK_FED(
      new ConfigurableCalendar(
          SATURDAY,
          SUNDAY,
          new ShiftedHoliday(NEW_YEAR_DAY, SUNDAY_TO_MONDAY),
          // https://wikipedia.org/wiki/Martin_Luther_King_Jr._Day
          new DayOfWeekInMonthHoliday(3, MONDAY, JANUARY),
          // https://wikipedia.org/wiki/Washington%27s_Birthday
          new DayOfWeekInMonthHoliday(3, MONDAY, FEBRUARY),
          // https://wikipedia.org/wiki/Memorial_Day
          new DayOfWeekInMonthHoliday(-1, MONDAY, MAY),
          // https://wikipedia.org/wiki/Independence_Day_(United_States)
          new ShiftedHoliday(new MonthDayHoliday(MonthDay.of(JULY, 4)), SUNDAY_TO_MONDAY),
          // https://wikipedia.org/wiki/Labor_Day
          new DayOfWeekInMonthHoliday(1, MONDAY, SEPTEMBER),
          // https://wikipedia.org/wiki/Columbus_Day
          new DayOfWeekInMonthHoliday(2, MONDAY, OCTOBER),
          // https://wikipedia.org/wiki/Veterans_Day
          new ShiftedHoliday(ARMISTICE_DAY, SUNDAY_TO_MONDAY),
          // https://wikipedia.org/wiki/Thanksgiving
          new DayOfWeekInMonthHoliday(4, THURSDAY, NOVEMBER),
          new ShiftedHoliday(CHRISTMAS_DAY, SUNDAY_TO_MONDAY))),

  /**
   * New York Stock Exchange (NYSE) holidays.
   *
   * <p>This calendar is valid from 2000 until further notice. Last verification occurred on
   * 2020-08-09.
   *
   * @see <a href="https://www.nyse.com/markets/hours-calendars">nyse.com</a>
   */
  NEW_YORK_SOCK_EXCHANGE(
      new ConfigurableCalendar(
          SATURDAY,
          SUNDAY,
          new ShiftedHoliday(NEW_YEAR_DAY, SUNDAY_TO_MONDAY),
          // https://wikipedia.org/wiki/Martin_Luther_King_Jr._Day
          new DayOfWeekInMonthHoliday(3, MONDAY, JANUARY),
          // https://wikipedia.org/wiki/Washington%27s_Birthday
          new DayOfWeekInMonthHoliday(3, MONDAY, FEBRUARY),
          GOOD_FRIDAY,
          // https://wikipedia.org/wiki/Memorial_Day
          new DayOfWeekInMonthHoliday(-1, MONDAY, MAY),
          // https://wikipedia.org/wiki/Independence_Day_(United_States)
          new ShiftedHoliday(new MonthDayHoliday(MonthDay.of(JULY, 4)), CLOSEST_WEEKDAY),
          // https://wikipedia.org/wiki/Labor_Day
          new DayOfWeekInMonthHoliday(1, MONDAY, SEPTEMBER),
          // https://wikipedia.org/wiki/Thanksgiving
          new DayOfWeekInMonthHoliday(4, THURSDAY, NOVEMBER),
          new ShiftedHoliday(CHRISTMAS_DAY, CLOSEST_WEEKDAY),
          // https://wikipedia.org/wiki/September_11_attacks
          new FixedHoliday(
              LocalDate.of(2001, 9, 11),
              LocalDate.of(2001, 9, 12),
              LocalDate.of(2001, 9, 13),
              LocalDate.of(2001, 9, 14)),
          // https://wikipedia.org/wiki/Death_and_state_funeral_of_Ronald_Reagan
          new FixedHoliday(LocalDate.of(2004, 6, 11)),
          // https://wikipedia.org/wiki/Death_and_state_funeral_of_Gerald_Ford
          new FixedHoliday(LocalDate.of(2007, 1, 2)),
          // https://wikipedia.org/wiki/Hurricane_Sandy
          new FixedHoliday(LocalDate.of(2012, 10, 30)),
          // https://wikipedia.org/wiki/Death_and_state_funeral_of_George_H._W._Bush
          new FixedHoliday(LocalDate.of(2018, 12, 5)))),

  /**
   * Paris (France) financial district holidays.
   *
   * <p>This calendar is valid from 2000 until further notice. Last verification occurred on
   * 2020-08-09.
   *
   * @see <a
   *     href="http://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000006902611&cidTexte=LEGITEXT000006072050">legifrance.gouv.fr</a>
   * @see <a href="http://jollyday.sourceforge.net/data/fr.html">jollyday.sourceforge.net</a>
   * @see <a href="https://www.timeanddate.com/holidays/france/">timeanddate.com</a>
   */
  PARIS(
      new ConfigurableCalendar(
          SATURDAY,
          SUNDAY,
          NEW_YEAR_DAY,
          EASTER_MONDAY,
          GOOD_FRIDAY,
          INTERNATIONAL_WORKERS_DAY,
          VICTORY_IN_EUROPE_DAY,
          ASCENSION_DAY,
          // see http://jollyday.sourceforge.net/data/fr.html
          new SuppressedHoliday(PENTECOST_MONDAY, 2005, 2006, 2007),
          // https://wikipedia.org/wiki/Bastille_Day
          new MonthDayHoliday(MonthDay.of(JULY, 14)),
          ASSUMPTION_OF_MARY,
          ALL_SAINTS_DAY,
          ARMISTICE_DAY,
          CHRISTMAS_DAY,
          SAINT_STEPHENS_DAY)),

  /**
   * Sydney (Australia) financial district holidays.
   *
   * <p>This calendar is valid from 2000 until further notice. Last verification occurred on
   * 2020-08-09.
   *
   * @see <a href="https://www.timeanddate.com/holidays/australia/">timeanddate.com</a>
   * @see <a href="https://www.rba.gov.au/schedules-events/bank-holidays-2020.html">Reserve Bank of
   *     Australia</a>
   */
  SYDNEY(
      new ConfigurableCalendar(
          SATURDAY,
          SUNDAY,
          new ShiftedHoliday(NEW_YEAR_DAY, WEEKEND_TO_MONDAY),
          // https://wikipedia.org/wiki/Australia_Day
          new ShiftedHoliday(new MonthDayHoliday(MonthDay.of(JANUARY, 26)), WEEKEND_TO_MONDAY),
          GOOD_FRIDAY,
          EASTER_MONDAY,
          // https://wikipedia.org/wiki/Anzac_Day
          new MonthDayHoliday(MonthDay.of(APRIL, 25)),
          // https://wikipedia.org/wiki/Queen%27s_Official_Birthday
          new DayOfWeekInMonthHoliday(2, MONDAY, JUNE),
          // https://wikipedia.org/wiki/Bank_holiday
          new DayOfWeekInMonthHoliday(1, MONDAY, AUGUST),
          // https://wikipedia.org/wiki/Labour_Day
          new DayOfWeekInMonthHoliday(1, MONDAY, OCTOBER),
          new ShiftedHoliday(CHRISTMAS_DAY, PLUS_TWO_DAYS),
          new ShiftedHoliday(BOXING_DAY, PLUS_TWO_DAYS))),

  /**
   * The calendar for <a
   * href="https://www.ecb.europa.eu/paym/target/target2/html/index.en.html">Trans-European
   * Automated Real-time Gross settlement Express Transfer (TARGET)</a> closing days.
   *
   * <p>This calendar is valid from 2000 until further notice. Last verification occurred on
   * 2020-08-09.
   *
   * @see <a
   *     href="https://www.ecb.europa.eu/press/pr/date/2000/html/pr001214_4.en.html">ecb.europa.eu</a>.
   */
  TARGET(
      new ConfigurableCalendar(
          SATURDAY,
          SUNDAY,
          NEW_YEAR_DAY,
          GOOD_FRIDAY,
          EASTER_MONDAY,
          INTERNATIONAL_WORKERS_DAY,
          CHRISTMAS_DAY,
          SAINT_STEPHENS_DAY,
          // https://www.ecb.europa.eu/press/pr/date/2000/html/pr000525_2.en.html
          new FixedHoliday(LocalDate.of(2001, 12, 31))));

  private final ConfigurableCalendar calendar;

  FinancialCalendars(ConfigurableCalendar calendar) {
    this.calendar = calendar;
  }

  /** @see Calendar#isHoliday(LocalDate) */
  @Override
  public boolean isHoliday(LocalDate date) {
    return calendar.isHoliday(date);
  }

  /** @see Calendar#getHolidaysFor(LocalDate) */
  @Override
  public Set<Holiday> getHolidaysFor(LocalDate date) {
    return calendar.getHolidaysFor(date);
  }

  /** @see Calendar#isBusinessDay(LocalDate) */
  @Override
  public boolean isBusinessDay(LocalDate date) {
    return calendar.isBusinessDay(date);
  }

  /** @see Calendar#previous(LocalDate) */
  @Override
  public LocalDate previous(LocalDate date) {
    return calendar.previous(date);
  }

  /** @see Calendar#next(LocalDate) */
  @Override
  public LocalDate next(LocalDate date) {
    return calendar.next(date);
  }

  /** @see Calendar#holidaysWithin(LocalDate, LocalDate) */
  @Override
  public List<LocalDate> holidaysWithin(LocalDate from, LocalDate to) {
    return calendar.holidaysWithin(from, to);
  }

  /** @see Calendar#businessDaysWithin(LocalDate, LocalDate) */
  @Override
  public List<LocalDate> businessDaysWithin(LocalDate from, LocalDate to) {
    return calendar.businessDaysWithin(from, to);
  }
}
