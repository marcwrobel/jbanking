package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import org.junit.jupiter.api.Test;

class MovedHolidayTest {

  private static final int YEAR = 2020;
  private static final Month MONTH = Month.JUNE;
  private static final int DAY = 15;
  private static final LocalDate FROM = LocalDate.of(YEAR, MONTH, DAY);
  private static final LocalDate TO = LocalDate.of(YEAR, MONTH, DAY + 1);

  private static final Holiday BASE = new MonthDayHoliday(MonthDay.of(MONTH, DAY));
  private static final Holiday HOLIDAY = new MovedHoliday(BASE, FROM, TO);

  @Test
  void baseCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new RelativeHoliday(null, 0));
  }

  @Test
  void holidayIsMoved() {
    assertThat(HOLIDAY.check(FROM.minusYears(1))).isTrue();
    assertThat(HOLIDAY.check(FROM)).isFalse();
    assertThat(HOLIDAY.check(TO)).isTrue();
    assertThat(HOLIDAY.check(FROM.plusYears(1))).isTrue();
  }

  @Test
  void equalsAndHashCodeAndToString() {
    testEquality(new MovedHoliday(BASE, FROM, TO), new MovedHoliday(BASE, FROM, TO));
  }
}
