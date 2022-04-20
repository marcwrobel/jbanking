package fr.marcwrobel.jbanking.calendar;

import org.junit.jupiter.api.Test;

class ParisCalendarTest extends CalendarTestSupport {

  protected ParisCalendarTest() {
    super(FinancialCalendars.PARIS);
  }

  // using strata-basics helps us to keep the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata("FRPA");
  }
}
