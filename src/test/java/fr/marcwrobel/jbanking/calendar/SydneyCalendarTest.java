package fr.marcwrobel.jbanking.calendar;

import org.junit.jupiter.api.Test;

class SydneyCalendarTest extends CalendarTestSupport {

  protected SydneyCalendarTest() {
    super(FinancialCalendars.SYDNEY);
  }

  // using strata-basics helps us to keep the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata("AUSY");
  }
}
