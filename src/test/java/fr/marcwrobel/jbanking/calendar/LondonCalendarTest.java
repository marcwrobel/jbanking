package fr.marcwrobel.jbanking.calendar;

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
}
