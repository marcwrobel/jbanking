package fr.marcwrobel.jbanking.calendar;

import static java.time.LocalDate.MAX;
import static java.time.LocalDate.MIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CalendarTest {

  private static final LocalDate ODD = LocalDate.of(2020, 1, 11);
  private static final LocalDate EVEN = LocalDate.of(2020, 1, 12);

  private static class OddCalendar implements Calendar {

    @Override
    public boolean isHoliday(LocalDate date) {
      return date.getDayOfMonth() % 2 != 0;
    }

    @Override
    public Set<Holiday> getHolidaysFor(LocalDate date) {
      throw new UnsupportedOperationException();
    }
  }

  @Test
  void businessDay() {
    Calendar calendar = new OddCalendar();
    assertThat(calendar.isHoliday(ODD)).isTrue();
    assertThat(calendar.isBusinessDay(ODD)).isFalse();
    assertThat(calendar.isHoliday(EVEN)).isFalse();
    assertThat(calendar.isBusinessDay(EVEN)).isTrue();
  }

  @Test
  void shift() {
    Calendar calendar = new OddCalendar();

    assertThat(calendar.shift(ODD, 0)).isEqualTo(ODD);
    assertThat(calendar.shift(EVEN, 0)).isEqualTo(EVEN);

    assertThat(calendar.shift(ODD, 1)).isEqualTo(ODD.plusDays(1));
    assertThat(calendar.shift(EVEN, 1)).isEqualTo(EVEN.plusDays(2));

    assertThat(calendar.shift(ODD, -1)).isEqualTo(ODD.minusDays(1));
    assertThat(calendar.shift(EVEN, -1)).isEqualTo(EVEN.minusDays(2));
  }

  @Test
  void nextOrSame() {
    Calendar calendar = new OddCalendar();
    assertThat(calendar.nextOrSame(ODD)).isEqualTo(ODD.plusDays(1));
    assertThat(calendar.nextOrSame(EVEN)).isEqualTo(EVEN);
  }

  @Test
  void previousOrSame() {
    Calendar calendar = new OddCalendar();
    assertThat(calendar.previousOrSame(ODD)).isEqualTo(ODD.minusDays(1));
    assertThat(calendar.previousOrSame(EVEN)).isEqualTo(EVEN);
  }

  @Test
  void next() {
    Calendar calendar = new OddCalendar();
    assertThat(calendar.next(ODD)).isEqualTo(ODD.plusDays(1));
    assertThat(calendar.next(EVEN)).isEqualTo(EVEN.plusDays(2));
  }

  @Test
  void previous() {
    Calendar calendar = new OddCalendar();
    assertThat(calendar.previous(ODD)).isEqualTo(ODD.minusDays(1));
    assertThat(calendar.previous(EVEN)).isEqualTo(EVEN.minusDays(2));
  }

  @Test
  void holidaysWithinParameterValidation() {
    Calendar calendar = new OddCalendar();
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> calendar.holidaysWithin(MAX, MIN));
  }

  @Test
  void holidaysWithin() {
    Calendar calendar = new OddCalendar();
    assertThat(calendar.holidaysWithin(ODD.minusDays(2), ODD.plusDays(2)))
        .isEqualTo(Arrays.asList(ODD.minusDays(2), ODD, ODD.plusDays(2)));
  }

  @Test
  void businessDaysWithinParameterValidation() {
    Calendar calendar = new OddCalendar();
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> calendar.businessDaysWithin(MAX, MIN));
  }

  @Test
  void businessDaysWithin() {
    Calendar calendar = new OddCalendar();
    assertThat(calendar.businessDaysWithin(ODD.minusDays(2), ODD.plusDays(2)))
        .isEqualTo(Arrays.asList(ODD.minusDays(1), ODD.plusDays(1)));
  }
}
