package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

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
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new DayOfWeekInMonthHoliday(0, null, Month.MARCH));
  }

  @Test
  void monthCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> new DayOfWeekInMonthHoliday(0, DayOfWeek.MONDAY, null));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(strings = { "2010-01-20", "2011-01-19", "2012-01-18", "2013-01-16", "2014-01-15", "2015-01-21",
      "2016-01-20", "2017-01-18", "2018-01-17", "2019-01-16", "2020-01-15", "2021-01-20" })
  void holidayCheckSucceed(String date) {
    assertThat(HOLIDAY.check(LocalDate.parse(date))).isTrue();
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

    assertThat(count).isEqualTo(1);
  }

  @Test
  void equalsAndHashCodeAndToString() {
    testEquality(new DayOfWeekInMonthHoliday(WEEK_NUMBER, DAY_OF_WEEK, MONTH),
        new DayOfWeekInMonthHoliday(WEEK_NUMBER, DAY_OF_WEEK, MONTH));
  }
}
