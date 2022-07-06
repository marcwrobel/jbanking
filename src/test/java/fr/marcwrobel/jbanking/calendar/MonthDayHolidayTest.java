package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MonthDayHolidayTest {

  private static final int YEAR = 2020;
  private static final Month MONTH = JUNE;
  private static final int DAY = 15;

  private static final MonthDayHoliday HOLIDAY = new MonthDayHoliday(MonthDay.of(MONTH, DAY));

  @Test
  void monthDayCannotBeNull() {
    assertThrows(NullPointerException.class, () -> new MonthDayHoliday(null));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThrows(NullPointerException.class, () -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 100, 1970, YEAR, 2050, 10000 })
  void holidayCheckSucceed(int year) {
    assertTrue(HOLIDAY.check(LocalDate.of(year, MONTH, DAY)));
  }

  @Test
  void onlyOneHolidayPerYear() {
    LocalDate start = LocalDate.of(YEAR, JANUARY, 1);
    LocalDate end = LocalDate.of(start.getYear(), DECEMBER, 31);

    int count = 0;
    for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
      if (HOLIDAY.check(date)) {
        count++;
      }
    }

    assertEquals(1, count);
  }

  @Test
  void equalsAndHashCodeAndToString() {
    Holiday holiday1 = new MonthDayHoliday(MonthDay.of(MONTH, DAY));
    Holiday holiday2 = new MonthDayHoliday(MonthDay.of(MONTH, DAY));

    assertEquals(holiday1, holiday2);
    assertEquals(holiday2, holiday1);
    assertEquals(holiday1, holiday1);
    assertEquals(holiday1.hashCode(), holiday2.hashCode());
    assertEquals(holiday1.toString(), holiday2.toString());
  }
}
