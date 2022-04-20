package fr.marcwrobel.jbanking.calendar;

import org.junit.jupiter.api.Test;

class NewYorkStockExchangeCalendarTest extends CalendarTestSupport {

  protected NewYorkStockExchangeCalendarTest() {
    super(FinancialCalendars.NEW_YORK_SOCK_EXCHANGE);
  }

  // using strata-basics helps us to keep the definition up-to-date
  @Test
  void ensureConsistencyWithStrata() {
    this.checkWithStrata("NYSE");
  }
}
