package fr.marcwrobel.jbanking.calendar;

import org.junit.jupiter.api.Test;

class TokyoCalendarTest extends CalendarTestSupport {

  protected TokyoCalendarTest() {
    super(FinancialCalendars.TOKYO);
  }

  // using strata-basics helps us to keep the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata("JPTO");
  }
}
