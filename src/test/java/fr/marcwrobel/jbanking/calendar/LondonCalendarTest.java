package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.MonthDay.of;

import org.junit.jupiter.api.Test;

class LondonCalendarTest extends CalendarTestSupport {

  protected LondonCalendarTest() {
    super(FinancialCalendars.LONDON);
  }

  // using strata-basics helps us keeping the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata("GBLO");
  }

  // https://www.timeanddate.com/holidays/uk/2018
  @Test
  void year2018() {
    check(
        2018,
        of(JANUARY, 1),
        of(MARCH, 30),
        of(APRIL, 2),
        of(MAY, 7),
        of(MAY, 28),
        of(AUGUST, 27),
        of(DECEMBER, 25),
        of(DECEMBER, 26));
  }

  // https://www.timeanddate.com/holidays/uk/2019
  @Test
  void year2019() {
    check(
        2019,
        of(JANUARY, 1),
        of(APRIL, 19),
        of(APRIL, 22),
        of(MAY, 6),
        of(MAY, 27),
        of(AUGUST, 26),
        of(DECEMBER, 25),
        of(DECEMBER, 26));
  }

  // https://www.timeanddate.com/holidays/uk/2020
  @Test
  void year2020() {
    check(
        2020,
        of(JANUARY, 1),
        of(APRIL, 10),
        of(APRIL, 13),
        of(MAY, 8),
        of(MAY, 25),
        of(AUGUST, 31),
        of(DECEMBER, 25),
        of(DECEMBER, 28));
  }

  // https://www.timeanddate.com/holidays/uk/2021
  @Test
  void year2021() {
    check(
        2021,
        of(JANUARY, 1),
        of(APRIL, 2),
        of(APRIL, 5),
        of(MAY, 3),
        of(MAY, 31),
        of(AUGUST, 30),
        of(DECEMBER, 27),
        of(DECEMBER, 28));
  }

  // https://www.timeanddate.com/holidays/uk/2022
  @Test
  void year2022() {
    check(
        2022,
        of(JANUARY, 3),
        of(APRIL, 15),
        of(APRIL, 18),
        of(MAY, 2),
        of(JUNE, 2),
        of(JUNE, 3),
        of(AUGUST, 29),
        of(DECEMBER, 26),
        of(DECEMBER, 27));
  }
}
