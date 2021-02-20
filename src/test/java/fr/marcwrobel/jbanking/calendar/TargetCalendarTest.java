package fr.marcwrobel.jbanking.calendar;

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
}
