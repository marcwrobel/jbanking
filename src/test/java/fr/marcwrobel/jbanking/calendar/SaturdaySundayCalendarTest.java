package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.SATURDAY_SUNDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.EnumSet;
import org.junit.jupiter.api.Test;

class SaturdaySundayCalendarTest {

  private static final LocalDate NOW = LocalDate.now();
  private static final LocalDate FROM = LocalDate.of(NOW.getYear() - 10, 1, 1);
  private static final LocalDate TO = LocalDate.of(NOW.getYear() + 10, 12, 31);

  @Test
  void onlySaturdayAndSundayAreBusinessDays() {
    LocalDate current = FROM;

    while (!current.equals(TO)) {
      if (EnumSet.of(SATURDAY, SUNDAY).contains(current.getDayOfWeek())) {
        assertFalse(SATURDAY_SUNDAY.isBusinessDay(current));
        assertTrue(SATURDAY_SUNDAY.isHoliday(current));
      } else {
        assertTrue(SATURDAY_SUNDAY.isBusinessDay(current));
        assertFalse(SATURDAY_SUNDAY.isHoliday(current));
      }

      current = current.plusDays(1);
    }
  }
}
