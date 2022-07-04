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
import static fr.marcwrobel.jbanking.calendar.ShiftingStrategy.SUNDAY_TO_TUESDAY;
import static fr.marcwrobel.jbanking.calendar.ShiftingStrategy.SUNDAY_TO_WEDNESDAY;
import static fr.marcwrobel.jbanking.calendar.ShiftingStrategy.WEEKEND_TO_MONDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
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
 * <p>Those calendars are valid from 2000 until further notice.
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
          new MonthDayHoliday(OCTOBER, 3),
          // https://wikipedia.org/wiki/Reformation_Day - was a national holiday only on 2017
          new YearRangeHoliday(new MonthDayHoliday(OCTOBER, 31), 2017, 2017),
          CHRISTMAS_EVE,
          CHRISTMAS_DAY,
          SAINT_STEPHENS_DAY,
          NEW_YEAR_EVE)),

  /**
   * London (United Kingdom) financial district holidays.
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
          new MovedHoliday(Holidays.MAY_DAY, d(2020, 5, 4), d(2020, 5, 8)),
          // https://www.timeanddate.com/holidays/uk/spring-bank-holiday - suppression for jubilees
          new SuppressedHoliday(new DayOfWeekInMonthHoliday(-1, MONDAY, MAY), 2002, 2012, 2022),
          // https://www.timeanddate.com/holidays/uk/summer-bank-holiday
          new DayOfWeekInMonthHoliday(-1, MONDAY, AUGUST),
          new ShiftedHoliday(CHRISTMAS_DAY, PLUS_TWO_DAYS),
          new ShiftedHoliday(BOXING_DAY, PLUS_TWO_DAYS),
          // golden jubilee
          new FixedHoliday(d(2002, 6, 3), d(2002, 6, 4)),
          // royal wedding
          new FixedHoliday(d(2011, 4, 29)),
          // golden jubilee
          new FixedHoliday(d(2012, 6, 4), d(2012, 6, 5)),
          // platinum jubilee
          new FixedHoliday(d(2022, 6, 2), d(2022, 6, 3)))),

  /**
   * Federal Reserve Bank of New York holidays.
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
          // https://wikipedia.org/wiki/Juneteenth
          // https://www.officeholidays.com/holidays/usa/new-york/juneteenth
          // https://www.cnbc.com/2021/06/17/juneteenth-federal-holiday-biden-signs-bill.html
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(JUNE, 19), CLOSEST_WEEKDAY), 2022, 2999),
          // https://wikipedia.org/wiki/Independence_Day_(United_States)
          new ShiftedHoliday(new MonthDayHoliday(JULY, 4), SUNDAY_TO_MONDAY),
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
          // https://wikipedia.org/wiki/Juneteenth
          // https://www.officeholidays.com/holidays/usa/new-york/juneteenth
          // https://www.cnbc.com/2021/06/17/juneteenth-federal-holiday-biden-signs-bill.html
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(JUNE, 19), CLOSEST_WEEKDAY), 2022, 2999),
          // https://wikipedia.org/wiki/Independence_Day_(United_States)
          new ShiftedHoliday(new MonthDayHoliday(JULY, 4), CLOSEST_WEEKDAY),
          // https://wikipedia.org/wiki/Labor_Day
          new DayOfWeekInMonthHoliday(1, MONDAY, SEPTEMBER),
          // https://wikipedia.org/wiki/Thanksgiving
          new DayOfWeekInMonthHoliday(4, THURSDAY, NOVEMBER),
          new ShiftedHoliday(CHRISTMAS_DAY, CLOSEST_WEEKDAY),
          // https://wikipedia.org/wiki/September_11_attacks
          new FixedHoliday(d(2001, 9, 11), d(2001, 9, 12), d(2001, 9, 13), d(2001, 9, 14)),
          // https://wikipedia.org/wiki/Death_and_state_funeral_of_Ronald_Reagan
          new FixedHoliday(d(2004, 6, 11)),
          // https://wikipedia.org/wiki/Death_and_state_funeral_of_Gerald_Ford
          new FixedHoliday(d(2007, 1, 2)),
          // https://wikipedia.org/wiki/Hurricane_Sandy
          new FixedHoliday(d(2012, 10, 30)),
          // https://wikipedia.org/wiki/Death_and_state_funeral_of_George_H._W._Bush
          new FixedHoliday(d(2018, 12, 5)))),

  /**
   * Paris (France) financial district holidays.
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
          new MonthDayHoliday(JULY, 14),
          ASSUMPTION_OF_MARY,
          ALL_SAINTS_DAY,
          ARMISTICE_DAY,
          CHRISTMAS_DAY,
          SAINT_STEPHENS_DAY)),

  /**
   * Sydney (Australia) financial district holidays.
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
          new ShiftedHoliday(new MonthDayHoliday(JANUARY, 26), WEEKEND_TO_MONDAY),
          GOOD_FRIDAY,
          EASTER_MONDAY,
          // https://wikipedia.org/wiki/Anzac_Day
          new MonthDayHoliday(APRIL, 25),
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
   * @see <a
   *     href="https://www.ecb.europa.eu/press/pr/date/2000/html/pr001214_4.en.html">ecb.europa.eu</a>
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
          new FixedHoliday(d(2001, 12, 31)))),

  /**
   * Tokyo (Japan) financial district holidays.
   *
   * <p>This calendar is valid from 2007 until further notice. Last verification occurred on
   * 2021-02-19.
   *
   * @see <a href="https://www.boj.or.jp/en/about/outline/holi.htm/">Holiday Schedule of the Bank of
   *     Japan</a>
   */
  TOKYO(
      new ConfigurableCalendar(
          SATURDAY,
          SUNDAY,
          new ShiftedHoliday(NEW_YEAR_DAY, SUNDAY_TO_MONDAY),
          new MonthDayHoliday(JANUARY, 2),
          new MonthDayHoliday(JANUARY, 3),
          // https://wikipedia.org/wiki/Coming_of_Age_Day
          new DayOfWeekInMonthHoliday(2, MONDAY, JANUARY),
          // https://wikipedia.org/wiki/National_Foundation_Day
          new ShiftedHoliday(new MonthDayHoliday(FEBRUARY, 11), SUNDAY_TO_MONDAY),
          // https://www.timeanddate.com/holidays/japan/spring-equinox
          new ShiftedHoliday(
              new MovedHoliday(
                  MonthDay.of(MARCH, 21),
                  MonthDay.of(MARCH, 20),
                  2000,
                  2001,
                  2004,
                  2005,
                  2008,
                  2009,
                  2012,
                  2013,
                  2016,
                  2017,
                  2020,
                  2021,
                  2024,
                  2025,
                  2026,
                  2028,
                  2029,
                  2030),
              SUNDAY_TO_MONDAY),
          // https://wikipedia.org/wiki/Sh%C5%8Dwa_Day
          new ShiftedHoliday(new MonthDayHoliday(APRIL, 29), SUNDAY_TO_MONDAY),
          // https://wikipedia.org/wiki/Constitution_Memorial_Day
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(MAY, 3), SUNDAY_TO_MONDAY), 1950, 2006),
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(MAY, 3), SUNDAY_TO_WEDNESDAY), 2007, 2999),
          // https://wikipedia.org/wiki/Greenery_Day from 2007, holiday between holidays before
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(MAY, 4), SUNDAY_TO_MONDAY), 1950, 2006),
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(MAY, 4), SUNDAY_TO_TUESDAY), 2007, 2999),
          // https://wikipedia.org/wiki/Children's_Day#Japan
          new ShiftedHoliday(new MonthDayHoliday(MAY, 5), SUNDAY_TO_MONDAY),
          // https://en.wikipedia.org/wiki/Marine_Day
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(JULY, 20), SUNDAY_TO_MONDAY), 1950, 2003),
          new YearRangeHoliday(
              new MovedHoliday(
                  new DayOfWeekInMonthHoliday(3, MONDAY, JULY),
                  d(2020, 7, 20), // The Olympics
                  d(2020, 7, 23),
                  d(2021, 7, 19), // Postponement of the Olympics
                  d(2021, 7, 22)),
              2003,
              2999),
          // https://wikipedia.org/wiki/Mountain_Day
          new YearRangeHoliday(
              new MovedHoliday(
                  new ShiftedHoliday(new MonthDayHoliday(AUGUST, 11), SUNDAY_TO_MONDAY),
                  d(2020, 8, 11), // The Olympics
                  d(2020, 8, 10),
                  d(2021, 8, 11), // Postponement of the Olympics
                  d(2021, 8, 9)),
              2016,
              2999),
          // https://wikipedia.org/wiki/Respect_for_the_Aged_Day - before 2003
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(SEPTEMBER, 15), SUNDAY_TO_MONDAY), 1966, 2002),
          new BridgedHoliday(
              // https://wikipedia.org/wiki/Respect_for_the_Aged_Day - 2003 onward
              new YearRangeHoliday(
                  new DayOfWeekInMonthHoliday(3, MONDAY, SEPTEMBER), ValueRange.of(2003, 2999)),
              // https://www.timeanddate.com/holidays/japan/autumn-equinox
              new ShiftedHoliday(
                  new MovedHoliday(
                      MonthDay.of(SEPTEMBER, 23),
                      MonthDay.of(SEPTEMBER, 22),
                      2012,
                      2016,
                      2020,
                      2024,
                      2028),
                  SUNDAY_TO_MONDAY)),
          // https://wikipedia.org/wiki/Health_and_Sports_Day
          new MovedHoliday(
              new DayOfWeekInMonthHoliday(2, MONDAY, OCTOBER),
              d(2020, 10, 12), // The Olympics
              d(2020, 7, 24),
              d(2021, 10, 11), // Postponement of the Olympics
              d(2021, 7, 23)),
          // https://en.wikipedia.org/wiki/Culture_Day
          new ShiftedHoliday(new MonthDayHoliday(NOVEMBER, 3), SUNDAY_TO_MONDAY),
          // https://www.timeanddate.com/holidays/japan/labor-thanksgiving-day
          new ShiftedHoliday(new MonthDayHoliday(NOVEMBER, 23), SUNDAY_TO_MONDAY),
          // new years' eve - bank of Japan, but not national holiday
          new ShiftedHoliday(new MonthDayHoliday(DECEMBER, 31), SUNDAY_TO_MONDAY),
          // https://en.wikipedia.org/wiki/The_Emperor%27s_Birthday - Akihito
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(DECEMBER, 23), SUNDAY_TO_MONDAY), 1990, 2018),
          // https://www.timeanddate.com/holidays/japan/2019?hol=9
          new FixedHoliday(
              // https://www.timeanddate.com/holidays/japan/coronation-day-holiday-1
              d(2019, 4, 30),
              // https://www.timeanddate.com/holidays/japan/coronation-day
              d(2019, 5, 1),
              // https://www.timeanddate.com/holidays/japan/coronation-day-holiday-2
              d(2019, 5, 2),
              // https://www.timeanddate.com/holidays/japan/enthronement-ceremony-day
              d(2019, 10, 22)),
          // https://en.wikipedia.org/wiki/The_Emperor%27s_Birthday - Naruhito
          new YearRangeHoliday(
              new ShiftedHoliday(new MonthDayHoliday(FEBRUARY, 23), SUNDAY_TO_MONDAY),
              2020,
              2999)));

  @SuppressWarnings(
      "ImmutableEnumChecker") // ConfigurableCalendar is thread-safe (given Holidays are
                              // thread-safe).
  private final ConfigurableCalendar calendar;

  private static LocalDate d(int year, int month, int day) {
    return LocalDate.of(year, month, day);
  }

  FinancialCalendars(ConfigurableCalendar calendar) {
    this.calendar = calendar;
  }

  /**
   * @see Calendar#isHoliday(LocalDate)
   */
  @Override
  public boolean isHoliday(LocalDate date) {
    return calendar.isHoliday(date);
  }

  /**
   * @see Calendar#getHolidaysFor(LocalDate)
   */
  @Override
  public Set<Holiday> getHolidaysFor(LocalDate date) {
    return calendar.getHolidaysFor(date);
  }

  /**
   * @see Calendar#isBusinessDay(LocalDate)
   */
  @Override
  public boolean isBusinessDay(LocalDate date) {
    return calendar.isBusinessDay(date);
  }

  /**
   * @see Calendar#previous(LocalDate)
   */
  @Override
  public LocalDate previous(LocalDate date) {
    return calendar.previous(date);
  }

  /**
   * @see Calendar#next(LocalDate)
   */
  @Override
  public LocalDate next(LocalDate date) {
    return calendar.next(date);
  }

  /**
   * @see Calendar#holidaysWithin(LocalDate, LocalDate)
   */
  @Override
  public List<LocalDate> holidaysWithin(LocalDate from, LocalDate to) {
    return calendar.holidaysWithin(from, to);
  }

  /**
   * @see Calendar#businessDaysWithin(LocalDate, LocalDate)
   */
  @Override
  public List<LocalDate> businessDaysWithin(LocalDate from, LocalDate to) {
    return calendar.businessDaysWithin(from, to);
  }
}
