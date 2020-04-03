package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.FRIDAY_SATURDAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.EnumSet;
import org.junit.jupiter.api.Test;

class FridaySaturdayCalendarTest {

  private static final LocalDate NOW = LocalDate.now();
  private static final LocalDate FROM = LocalDate.of(NOW.getYear() - 10, 1, 1);
  private static final LocalDate TO = LocalDate.of(NOW.getYear() + 10, 12, 31);

  @Test
  void onlySaturdayAndSundayAreBusinessDays() {
    LocalDate current = FROM;

    while (!current.equals(TO)) {
      if (EnumSet.of(FRIDAY, SATURDAY).contains(current.getDayOfWeek())) {
        assertFalse(FRIDAY_SATURDAY.isBusinessDay(current));
        assertTrue(FRIDAY_SATURDAY.isHoliday(current));
      } else {
        assertTrue(FRIDAY_SATURDAY.isBusinessDay(current));
        assertFalse(FRIDAY_SATURDAY.isHoliday(current));
      }

      current = current.plusDays(1);
    }
  }
}
