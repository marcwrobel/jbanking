package fr.marcwrobel.jbanking.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SundayToMondayShiftingStrategyTest {

  private static final LocalDate THURSDAY = LocalDate.of(2020, 4, 2);
  private static final LocalDate FRIDAY = THURSDAY.plusDays(1);
  private static final LocalDate SATURDAY = FRIDAY.plusDays(1);
  private static final LocalDate SUNDAY = SATURDAY.plusDays(1);
  private static final LocalDate MONDAY = SUNDAY.plusDays(1);

  private static final ShiftingStrategy STRATEGY = ShiftingStrategy.SUNDAY_TO_MONDAY;

  @Test
  void thursdayShift() {
    assertThat(STRATEGY.shift(THURSDAY)).isEqualTo(THURSDAY);
  }

  @Test
  void thursdayUnshift() {
    assertThat(STRATEGY.unshift(THURSDAY)).containsExactly(THURSDAY);
  }

  @Test
  void fridayShift() {
    assertThat(STRATEGY.shift(FRIDAY)).isEqualTo(FRIDAY);
  }

  @Test
  void fridayUnshift() {
    assertThat(STRATEGY.unshift(FRIDAY)).containsExactly(FRIDAY);
  }

  @Test
  void saturdayShift() {
    assertThat(STRATEGY.shift(SATURDAY)).isEqualTo(SATURDAY);
  }

  @Test
  void saturdayUnshift() {
    assertThat(STRATEGY.unshift(SATURDAY)).containsExactly(SATURDAY);
  }

  @Test
  void sundayShift() {
    assertThat(STRATEGY.shift(SUNDAY)).isEqualTo(MONDAY);
  }

  @Test
  void sundayUnshift() {
    assertThat(STRATEGY.unshift(SUNDAY)).containsExactly(SUNDAY);
  }

  @Test
  void mondayShift() {
    assertThat(STRATEGY.shift(MONDAY)).isEqualTo(MONDAY);
  }

  @Test
  void mondayUnshift() {
    assertThat(STRATEGY.unshift(MONDAY)).containsExactly(MONDAY, SUNDAY);
  }
}
