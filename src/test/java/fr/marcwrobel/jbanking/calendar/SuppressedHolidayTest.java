package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.JUNE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SuppressedHolidayTest {

  private static final int YEAR = 2020;
  private static final Month MONTH = JUNE;
  private static final int DAY = 15;

  private static final MonthDayHoliday BASE = new MonthDayHoliday(MonthDay.of(MONTH, DAY));
  private static final SuppressedHoliday HOLIDAY = new SuppressedHoliday(BASE, YEAR);

  @Test
  void baseCannotBeNull() {
    assertThrows(NullPointerException.class, () -> new SuppressedHoliday(null, YEAR));
  }

  @Test
  void suppressedYearsCannotBeNull() {
    assertThrows(
        NullPointerException.class, () -> new SuppressedHoliday(BASE, (Collection<Integer>) null));
  }

  @Test
  void suppressedYearsCannotBeNull2() {
    assertThrows(NullPointerException.class, () -> new SuppressedHoliday(BASE, (Integer[]) null));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThrows(NullPointerException.class, () -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 100, 1970, 2050, 10000})
  void holidayCheckSucceed(int year) {
    assertTrue(HOLIDAY.check(LocalDate.of(year, MONTH, DAY)));
  }

  @Test
  void holidayIsSuppressed() {
    assertFalse(HOLIDAY.check(LocalDate.of(YEAR, MONTH, DAY)));
  }

  @Test
  void equalsAndHashCodeAndToString() {
    Holiday holiday1 = new SuppressedHoliday(new MonthDayHoliday(MonthDay.of(MONTH, DAY)), YEAR);
    Holiday holiday2 = new SuppressedHoliday(new MonthDayHoliday(MonthDay.of(MONTH, DAY)), YEAR);

    assertEquals(holiday1, holiday2);
    assertEquals(holiday2, holiday1);
    assertEquals(holiday1, holiday1);
    assertEquals(holiday1.hashCode(), holiday2.hashCode());
    assertEquals(holiday1.toString(), holiday2.toString());
  }
}
