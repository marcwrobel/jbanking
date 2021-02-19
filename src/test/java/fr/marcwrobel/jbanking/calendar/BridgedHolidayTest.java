package fr.marcwrobel.jbanking.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.MonthDay;
import org.junit.jupiter.api.Test;

class BridgedHolidayTest {

  private static final int YEAR = 2020;
  private static final int MONTH = 6;
  private static final int DAY = 16;

  private static final LocalDate DATE = LocalDate.of(YEAR, MONTH, DAY);

  private static final BridgedHoliday HOLIDAY =
      new BridgedHoliday(
          new MonthDayHoliday(MonthDay.of(MONTH, DAY - 1)),
          new MonthDayHoliday(MonthDay.of(MONTH, DAY + 1)));

  @Test
  void checkDoesNotAcceptNull1() {
    assertThrows(NullPointerException.class, () -> HOLIDAY.check(null));
  }

  @Test
  void holidayCheckSucceed() {
    assertTrue(HOLIDAY.check(DATE.minusDays(1)));
    assertTrue(HOLIDAY.check(DATE));
    assertTrue(HOLIDAY.check(DATE.plusDays(1)));
  }

  @Test
  void onlyOneHolidayOfEachPerYear() {
    LocalDate start = LocalDate.of(YEAR, 1, 1);
    LocalDate end = LocalDate.of(YEAR, 12, 31);

    int count = 0;
    for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
      if (HOLIDAY.check(date)) {
        count++;
      }
    }

    assertEquals(3, count);
  }
}
