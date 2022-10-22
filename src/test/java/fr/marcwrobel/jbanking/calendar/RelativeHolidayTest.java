package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RelativeHolidayTest {

  private static final int YEAR = 2020;
  private static final Month MONTH = Month.JUNE;
  private static final int DAY = 15;
  private static final int SHIFT = 1;

  private static final Holiday BASE = new MonthDayHoliday(MonthDay.of(MONTH, DAY));
  private static final RelativeHoliday HOLIDAY = new RelativeHoliday(BASE, SHIFT);

  @Test
  void baseCannotBeNull() {
    assertThrows(NullPointerException.class, () -> new RelativeHoliday(null, 0));
  }

  @Test
  void checkDoesNotAcceptNull() {
    // noinspection ConstantConditions
    assertThrows(NullPointerException.class, () -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 100, 1970, YEAR, 2050, 10000 })
  void holidayCheckSucceed(int year) {
    assertTrue(HOLIDAY.check(LocalDate.of(year, MONTH, DAY).plusDays(SHIFT)));
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
    Holiday holiday1 = new RelativeHoliday(BASE, SHIFT);
    Holiday holiday2 = new RelativeHoliday(BASE, SHIFT);
    Holiday holiday3 = new RelativeHoliday(BASE, SHIFT + 1);
    Holiday holiday4 = new RelativeHoliday(new MonthDayHoliday(MonthDay.of(MONTH, DAY + 1)), SHIFT);

    assertEquals(holiday1, holiday2);
    assertEquals(holiday2, holiday1);
    assertEquals(holiday1, holiday1);
    assertEquals(holiday1.hashCode(), holiday2.hashCode());
    assertEquals(holiday1.toString(), holiday2.toString());

    assertNotEquals(holiday1, holiday3);
    assertNotEquals(holiday3, holiday1);
    assertNotEquals(holiday1.hashCode(), holiday3.hashCode());
    assertNotEquals(holiday1.toString(), holiday3.toString());

    assertNotEquals(holiday1, holiday4);
    assertNotEquals(holiday4, holiday1);
    assertNotEquals(holiday1.hashCode(), holiday4.hashCode());
    assertNotEquals(holiday1.toString(), holiday4.toString());

    // do not modify - bullshit tests to improve coverage and have a better visibility in sonar
    assertFalse(holiday1.equals(null));
    assertFalse(holiday1.equals(new Object()));
  }
}
