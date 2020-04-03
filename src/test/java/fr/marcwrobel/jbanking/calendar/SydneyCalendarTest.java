package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.OCTOBER;
import static java.time.MonthDay.of;

import org.junit.jupiter.api.Test;

class SydneyCalendarTest extends CalendarTestSupport {

  protected SydneyCalendarTest() {
    super(FinancialCalendars.SYDNEY);
  }

  // using strata-basics helps us keeping the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata("AUSY");
  }

  // https://www.timeanddate.com/holidays/australia/2018
  @Test
  void year2018() {
    check(
        2018,
        of(JANUARY, 1),
        of(JANUARY, 26),
        of(MARCH, 30),
        of(APRIL, 2),
        of(APRIL, 25),
        of(JUNE, 11),
        of(AUGUST, 6),
        of(OCTOBER, 1),
        of(DECEMBER, 25),
        of(DECEMBER, 26));
  }

  // https://www.timeanddate.com/holidays/australia/2019
  @Test
  void year2019() {
    check(
        2019,
        of(JANUARY, 1),
        of(JANUARY, 28),
        of(APRIL, 19),
        of(APRIL, 22),
        of(APRIL, 25),
        of(JUNE, 10),
        of(AUGUST, 5),
        of(OCTOBER, 7),
        of(DECEMBER, 25),
        of(DECEMBER, 26));
  }

  // https://www.timeanddate.com/holidays/australia/2020
  @Test
  void year2020() {
    check(
        2020,
        of(JANUARY, 1),
        of(JANUARY, 27),
        of(APRIL, 10),
        of(APRIL, 13),
        of(APRIL, 25),
        of(JUNE, 8),
        of(AUGUST, 3),
        of(OCTOBER, 5),
        of(DECEMBER, 25),
        of(DECEMBER, 28));
  }

  // https://www.timeanddate.com/holidays/australia/2021
  @Test
  void year2021() {
    check(
        2021,
        of(JANUARY, 1),
        of(JANUARY, 26),
        of(APRIL, 2),
        of(APRIL, 5),
        of(APRIL, 25),
        of(JUNE, 14),
        of(AUGUST, 2),
        of(OCTOBER, 4),
        of(DECEMBER, 27),
        of(DECEMBER, 28));
  }

  // https://www.timeanddate.com/holidays/australia/2022
  @Test
  void year2022() {
    check(
        2022,
        of(JANUARY, 3),
        of(JANUARY, 26),
        of(APRIL, 15),
        of(APRIL, 18),
        of(APRIL, 25),
        of(JUNE, 13),
        of(AUGUST, 1),
        of(OCTOBER, 3),
        of(DECEMBER, 26),
        of(DECEMBER, 27));
  }
}
