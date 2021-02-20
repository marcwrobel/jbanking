package fr.marcwrobel.jbanking.calendar;

import org.junit.jupiter.api.Test;

class FrankfurtCalendarTest extends CalendarTestSupport {

  protected FrankfurtCalendarTest() {
    super(FinancialCalendars.FRANKFURT);
  }

  // using strata-basics helps us keeping the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata("DEFR");
  }
}
