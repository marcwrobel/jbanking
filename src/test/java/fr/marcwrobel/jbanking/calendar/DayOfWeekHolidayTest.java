package fr.marcwrobel.jbanking.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DayOfWeekHolidayTest {

  private static final int YEAR = 2020;
  private static final int MONTH = 6;
  private static final int DAY = 16;

  private static final LocalDate CURRENT_WEEK = LocalDate.of(YEAR, MONTH, DAY);

  private static final DayOfWeekHoliday HOLIDAY = DayOfWeekHoliday.TUESDAY;

  @Test
  void checkDoesNotAcceptNull() {
    assertThrows(NullPointerException.class, () -> HOLIDAY.check(null));
  }

  @Test
  void holidayCheckSucceed() {
    assertTrue(HOLIDAY.check(CURRENT_WEEK));
  }

  @Test
  void onlyOneHolidayPerWeek() {
    LocalDate start = LocalDate.of(YEAR, MONTH, DAY);
    LocalDate end = start.with(DayOfWeek.SUNDAY);

    int count = 0;
    for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
      if (HOLIDAY.check(date)) {
        count++;
      }
    }

    assertEquals(1, count);
  }
}
