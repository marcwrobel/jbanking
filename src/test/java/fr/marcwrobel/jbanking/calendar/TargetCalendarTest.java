package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.MonthDay.of;

import org.junit.jupiter.api.Test;

class TargetCalendarTest extends CalendarTestSupport {

  protected TargetCalendarTest() {
    super(FinancialCalendars.TARGET);
  }

  // using strata-basics helps us keeping the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata("EUTA");
  }

  // https://www.sepaforcorporates.com/single-euro-payments-area/european-sepa-target-closing-days-2017-2018/
  @Test
  void year2018() {
    check(
        2018,
        of(JANUARY, 1),
        of(MARCH, 30),
        of(APRIL, 2),
        of(MAY, 1),
        of(DECEMBER, 25),
        of(DECEMBER, 26));
  }

  // https://www.sepaforcorporates.com/single-euro-payments-area/sepa-target-closing-days-2019-and-2020/
  @Test
  void year2019() {
    check(
        2019,
        of(JANUARY, 1),
        of(APRIL, 19),
        of(APRIL, 22),
        of(MAY, 1),
        of(DECEMBER, 25),
        of(DECEMBER, 26));
  }

  // https://www.sepaforcorporates.com/single-euro-payments-area/sepa-target-closing-days-2019-and-2020/
  @Test
  void year2020() {
    check(
        2020,
        of(JANUARY, 1),
        of(APRIL, 10),
        of(APRIL, 13),
        of(MAY, 1),
        of(DECEMBER, 25),
        of(DECEMBER, 26));
  }
}
