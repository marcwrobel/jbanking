package fr.marcwrobel.jbanking.calendar;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TokyoCalendarTest extends CalendarTestSupport {

  protected TokyoCalendarTest() {
    super(FinancialCalendars.TOKYO);
  }

  // using strata-basics helps us keeping the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata(
        "JPTO",
        // excluded - Olympics dates are wrong in Strata -
        // https://github.com/marcwrobel/jbanking/issues/77
        LocalDate.of(2021, 7, 19),
        LocalDate.of(2021, 7, 22),
        LocalDate.of(2021, 7, 23),
        LocalDate.of(2021, 8, 9),
        LocalDate.of(2021, 8, 11),
        LocalDate.of(2021, 10, 11));
  }
}
