package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.NO_HOLIDAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class NoHolidayCalendarTest {

  private static final LocalDate NOW = LocalDate.now();
  private static final LocalDate FROM = LocalDate.of(NOW.getYear() - 10, 1, 1);
  private static final LocalDate TO = LocalDate.of(NOW.getYear() + 10, 12, 31);

  @Test
  void everyPastAndFutureDaysAreBusinessDays() {
    LocalDate current = FROM;

    while (!current.equals(TO)) {
      assertTrue(NO_HOLIDAYS.isBusinessDay(current));
      assertFalse(NO_HOLIDAYS.isHoliday(current));
      assertTrue(NO_HOLIDAYS.getHolidaysFor(current).isEmpty());
      current = current.plusDays(1);
    }

    assertTrue(NO_HOLIDAYS.holidaysWithin(FROM, TO).isEmpty());
    assertFalse(NO_HOLIDAYS.businessDaysWithin(FROM, TO).isEmpty());
  }

  @Test
  void previousAndNextDaysAreBusinessDays() {
    assertEquals(NOW.minusDays(1), NO_HOLIDAYS.previousBusinessDay(NOW));
    assertEquals(NOW.plusDays(1), NO_HOLIDAYS.nextBusinessDay(NOW));
  }
}
