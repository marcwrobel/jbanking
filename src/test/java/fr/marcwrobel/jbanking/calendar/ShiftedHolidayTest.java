package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.ShiftingStrategy.CLOSEST_WEEKDAY;
import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ShiftedHolidayTest {

  private static final Month MONTH = Month.JULY;
  private static final int DAY = 4;
  private static final ShiftingStrategy STRATEGY = CLOSEST_WEEKDAY;

  private static final MonthDayHoliday BASE = new MonthDayHoliday(MonthDay.of(MONTH, DAY));
  private static final ShiftedHoliday HOLIDAY = new ShiftedHoliday(BASE, STRATEGY);

  @Test
  void baseCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new RelativeHoliday(null, 0));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(strings = { "2017-07-04", "2018-07-04", "2019-07-04", "2020-07-03", "2021-07-04", "2022-07-04",
      "2023-07-04", "2024-07-04", "2025-07-04", "2026-07-03", "2027-07-05", "2028-07-04", "2029-07-04" })
  void holidayCheckSucceed(String date) {
    assertThat(HOLIDAY.check(LocalDate.parse(date))).isTrue();
  }

  @Test
  void equalsAndHashCodeAndToString() {
    testEquality(new ShiftedHoliday(BASE, STRATEGY), new ShiftedHoliday(BASE, STRATEGY));
  }
}
