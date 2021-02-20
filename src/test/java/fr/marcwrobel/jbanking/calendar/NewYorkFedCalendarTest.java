package fr.marcwrobel.jbanking.calendar;

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
}
