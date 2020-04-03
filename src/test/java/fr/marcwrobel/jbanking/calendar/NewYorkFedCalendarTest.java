package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.time.MonthDay.of;

import org.junit.jupiter.api.Test;

class NewYorkFedCalendarTest extends CalendarTestSupport {

  protected NewYorkFedCalendarTest() {
    super(FinancialCalendars.NEW_YORK_FED);
  }

  // using strata-basics helps us keeping the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata("NYFD");
  }

  @Test
  void year2018() {
    check(
        2018,
        of(JANUARY, 1),
        of(JANUARY, 15),
        of(FEBRUARY, 19),
        of(MAY, 28),
        of(JULY, 4),
        of(SEPTEMBER, 3),
        of(OCTOBER, 8),
        of(NOVEMBER, 12),
        of(NOVEMBER, 22),
        of(DECEMBER, 25));
  }

  @Test
  void year2019() {
    check(
        2019,
        of(JANUARY, 1),
        of(JANUARY, 21),
        of(FEBRUARY, 18),
        of(MAY, 27),
        of(JULY, 4),
        of(SEPTEMBER, 2),
        of(OCTOBER, 14),
        of(NOVEMBER, 11),
        of(NOVEMBER, 28),
        of(DECEMBER, 25));
  }

  @Test
  void year2020() {
    check(
        2020,
        of(JANUARY, 1),
        of(JANUARY, 20),
        of(FEBRUARY, 17),
        of(MAY, 25),
        of(JULY, 4),
        of(SEPTEMBER, 7),
        of(OCTOBER, 12),
        of(NOVEMBER, 11),
        of(NOVEMBER, 26),
        of(DECEMBER, 25));
  }

  @Test
  void year2021() {
    check(
        2021,
        of(JANUARY, 1),
        of(JANUARY, 18),
        of(FEBRUARY, 15),
        of(MAY, 31),
        of(JULY, 5),
        of(SEPTEMBER, 6),
        of(OCTOBER, 11),
        of(NOVEMBER, 11),
        of(NOVEMBER, 25),
        of(DECEMBER, 25));
  }

  @Test
  void year2022() {
    check(
        2022,
        of(JANUARY, 17),
        of(FEBRUARY, 21),
        of(MAY, 30),
        of(JULY, 4),
        of(SEPTEMBER, 5),
        of(OCTOBER, 10),
        of(NOVEMBER, 11),
        of(NOVEMBER, 24),
        of(DECEMBER, 26));
  }
}
