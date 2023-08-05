package fr.marcwrobel.jbanking.calendar;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

class ConfigurableCalendarTest {

  private static final LocalDate MONDAY = LocalDate.of(2020, 3, 30);
  private static final LocalDate TUESDAY = MONDAY.plusDays(1);
  private static final LocalDate WEDNESDAY = TUESDAY.plusDays(1);
  private static final LocalDate THURSDAY = WEDNESDAY.plusDays(1);
  private static final LocalDate FRIDAY = THURSDAY.plusDays(1);
  private static final LocalDate SATURDAY = FRIDAY.plusDays(1);
  private static final LocalDate SUNDAY = SATURDAY.plusDays(1);
  private static final LocalDate NEXT_MONDAY = SUNDAY.plusDays(1);

  private static final Holiday SATURDAY_HOLIDAY = DayOfWeekHoliday.SATURDAY;
  private static final Holiday SUNDAY_HOLIDAY1 = DayOfWeekHoliday.SUNDAY;
  private static final Holiday SUNDAY_HOLIDAY2 = new MonthDayHoliday(MonthDay.from(SUNDAY));
  private static final Calendar WEEKEND_CALENDAR = new ConfigurableCalendar(SATURDAY_HOLIDAY, SUNDAY_HOLIDAY1,
      SUNDAY_HOLIDAY2);

  @Test
  void holidaysMustNotBeNull() {
    assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> new ConfigurableCalendar((Collection<Holiday>) null));
  }

  @Test
  void oneHolidayMustNotBeNull() {
    assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> new ConfigurableCalendar(SUNDAY_HOLIDAY1, null, SUNDAY_HOLIDAY2));
  }

  @Test
  void emptyCalendarDateCalculation() {
    Calendar emptyCalendar = new ConfigurableCalendar();
    LocalDate date = LocalDate.of(2020, 4, 14);

    assertThat(emptyCalendar.isBusinessDay(date)).isTrue();
    assertThat(emptyCalendar.isHoliday(date)).isFalse();
    assertThat(emptyCalendar.nextOrSame(date)).isEqualTo(date);
    assertThat(emptyCalendar.next(date)).isEqualTo(date.plusDays(1));
    assertThat(emptyCalendar.previousOrSame(date)).isEqualTo(date);
    assertThat(emptyCalendar.previous(date)).isEqualTo(date.minusDays(1));
  }

  @Test
  void badCalendarDateCalculation() {
    Calendar fullCalendar = new ConfigurableCalendar(asList(DayOfWeekHoliday.values()));
    LocalDate date = LocalDate.of(2020, 4, 14);

    for (int i = 0; i < 365; i++) {
      assertThat(fullCalendar.isHoliday(date)).isTrue();
      assertThat(fullCalendar.isBusinessDay(date)).isFalse();
    }

    assertThatExceptionOfType(DateCalculationException.class).isThrownBy(() -> fullCalendar.shift(date, 1));
    assertThatExceptionOfType(DateCalculationException.class).isThrownBy(() -> fullCalendar.shift(date, -1));
    assertThatExceptionOfType(DateCalculationException.class).isThrownBy(() -> fullCalendar.next(date));
    assertThatExceptionOfType(DateCalculationException.class).isThrownBy(() -> fullCalendar.nextOrSame(date));
    assertThatExceptionOfType(DateCalculationException.class).isThrownBy(() -> fullCalendar.previous(date));
    assertThatExceptionOfType(DateCalculationException.class).isThrownBy(() -> fullCalendar.previousOrSame(date));
  }

  @Test
  void saturdayCalculation() {
    assertThat(WEEKEND_CALENDAR.isBusinessDay(SATURDAY)).isFalse();
    assertThat(WEEKEND_CALENDAR.isHoliday(SATURDAY)).isTrue();
    assertThat(WEEKEND_CALENDAR.getHolidaysFor(SATURDAY)).isEqualTo(new HashSet<>(singletonList(SATURDAY_HOLIDAY)));
  }

  @Test
  void fridayCalculation() {
    assertThat(WEEKEND_CALENDAR.isBusinessDay(FRIDAY)).isTrue();
    assertThat(WEEKEND_CALENDAR.isHoliday(FRIDAY)).isFalse();
    assertThat(WEEKEND_CALENDAR.getHolidaysFor(FRIDAY)).isEqualTo(emptySet());
    assertThat(WEEKEND_CALENDAR.previous(SATURDAY)).isEqualTo(FRIDAY);
  }

  @Test
  void sundayCalculation() {
    assertThat(WEEKEND_CALENDAR.isBusinessDay(SUNDAY)).isFalse();
    assertThat(WEEKEND_CALENDAR.isHoliday(SUNDAY)).isTrue();
    assertThat(WEEKEND_CALENDAR.isHoliday(SUNDAY)).isTrue();
    assertThat(WEEKEND_CALENDAR.getHolidaysFor(SUNDAY)).isEqualTo(new HashSet<>(asList(SUNDAY_HOLIDAY1, SUNDAY_HOLIDAY2)));
  }

  @Test
  void nextMondayCalculation() {
    assertThat(WEEKEND_CALENDAR.isBusinessDay(NEXT_MONDAY)).isTrue();
    assertThat(WEEKEND_CALENDAR.isHoliday(NEXT_MONDAY)).isFalse();
    assertThat(WEEKEND_CALENDAR.getHolidaysFor(NEXT_MONDAY)).isEqualTo(emptySet());
    assertThat(WEEKEND_CALENDAR.next(SATURDAY)).isEqualTo(NEXT_MONDAY);
  }

  @Test
  void fromCannotBeAfterToForHolidaysComputation() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> WEEKEND_CALENDAR.holidaysWithin(TUESDAY, MONDAY));
  }

  @Test
  void fromCanBeEqualsToForHolidaysComputation() {
    assertThat(WEEKEND_CALENDAR.holidaysWithin(MONDAY, MONDAY)).isEmpty();
    assertThat(WEEKEND_CALENDAR.holidaysWithin(SUNDAY, SUNDAY)).hasSize(1);
  }

  @Test
  void holidaysComputation() {
    List<LocalDate> expected = asList(SATURDAY, SUNDAY);
    List<LocalDate> occurrences = WEEKEND_CALENDAR.holidaysWithin(MONDAY, SUNDAY);
    assertThat(occurrences).isEqualTo(expected);
  }

  @Test
  void fromCannotBeAfterToForBusinessDaysComputation() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> WEEKEND_CALENDAR.holidaysWithin(TUESDAY, MONDAY));
  }

  @Test
  void fromCanBeEqualsToForBusinessDaysComputation() {
    assertThat(WEEKEND_CALENDAR.holidaysWithin(MONDAY, MONDAY)).isEmpty();
    assertThat(WEEKEND_CALENDAR.holidaysWithin(SUNDAY, SUNDAY)).hasSize(1);
  }

  @Test
  void businessDaysComputation() {
    List<LocalDate> expected = asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
    List<LocalDate> occurrences = WEEKEND_CALENDAR.businessDaysWithin(MONDAY, SUNDAY);
    assertThat(occurrences).isEqualTo(expected);
  }
}
