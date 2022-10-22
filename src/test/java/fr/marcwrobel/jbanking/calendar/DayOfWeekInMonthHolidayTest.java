package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayOfWeekInMonthHolidayTest {

  private static final int WEEK_NUMBER = 3;
  private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.WEDNESDAY;
  private static final Month MONTH = Month.JANUARY;

  private static final DayOfWeekInMonthHoliday HOLIDAY = new DayOfWeekInMonthHoliday(WEEK_NUMBER, DAY_OF_WEEK, MONTH);

  @Test
  void dayOfWeekCannotBeNull() {
    assertThrows(NullPointerException.class, () -> new DayOfWeekInMonthHoliday(0, null, Month.MARCH));
  }

  @Test
  void monthCannotBeNull() {
    assertThrows(NullPointerException.class, () -> new DayOfWeekInMonthHoliday(0, DayOfWeek.MONDAY, null));
  }

  @Test
  void checkDoesNotAcceptNull() {
    // noinspection ConstantConditions
    assertThrows(NullPointerException.class, () -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(strings = { "2010-01-20", "2011-01-19", "2012-01-18", "2013-01-16", "2014-01-15", "2015-01-21",
      "2016-01-20", "2017-01-18", "2018-01-17", "2019-01-16", "2020-01-15", "2021-01-20" })
  void holidayCheckSucceed(String date) {
    assertTrue(HOLIDAY.check(LocalDate.parse(date)));
  }

  @Test
  void onlyOneHolidayPerYear() {
    LocalDate start = LocalDate.of(2020, JANUARY, 1);
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
    Holiday holiday1 = new DayOfWeekInMonthHoliday(WEEK_NUMBER, DAY_OF_WEEK, MONTH);
    Holiday holiday2 = new DayOfWeekInMonthHoliday(WEEK_NUMBER, DAY_OF_WEEK, MONTH);
    Holiday holiday3 = new DayOfWeekInMonthHoliday(WEEK_NUMBER + 1, DAY_OF_WEEK, MONTH);
    Holiday holiday4 = new DayOfWeekInMonthHoliday(WEEK_NUMBER, DAY_OF_WEEK.plus(1), MONTH);
    Holiday holiday5 = new DayOfWeekInMonthHoliday(WEEK_NUMBER, DAY_OF_WEEK, MONTH.plus(1));

    assertEquals(holiday1, holiday2);
    assertEquals(holiday2, holiday1);
    assertEquals(holiday1, holiday1);
    assertEquals(holiday1.hashCode(), holiday2.hashCode());
    assertEquals(holiday1.toString(), holiday2.toString());

    assertNotEquals(holiday1, holiday3);
    assertNotEquals(holiday1.hashCode(), holiday3.hashCode());
    assertNotEquals(holiday1.toString(), holiday3.toString());

    assertNotEquals(holiday1, holiday4);
    assertNotEquals(holiday1.hashCode(), holiday4.hashCode());
    assertNotEquals(holiday1.toString(), holiday4.toString());

    assertNotEquals(holiday1, holiday5);
    assertNotEquals(holiday1.hashCode(), holiday5.hashCode());
    assertNotEquals(holiday1.toString(), holiday5.toString());

    assertNotEquals(null, holiday1);
    assertNotEquals(new Object(), holiday1);
  }
}
