package fr.marcwrobel.jbanking.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class FollowingWeekShiftingStrategyTest {

  private static final LocalDate FRIDAY = LocalDate.of(2020, 4, 3);
  private static final LocalDate SATURDAY = FRIDAY.plusDays(1);
  private static final LocalDate SUNDAY = SATURDAY.plusDays(1);
  private static final LocalDate MONDAY = SUNDAY.plusDays(1);
  private static final LocalDate TUESDAY = MONDAY.plusDays(1);

  private static final ShiftingStrategy STRATEGY = ShiftingStrategy.PLUS_TWO_DAYS;

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
    assertThat(STRATEGY.shift(SATURDAY)).isEqualTo(MONDAY);
  }

  @Test
  void saturdayUnshift() {
    assertThat(STRATEGY.unshift(SATURDAY)).containsExactly(SATURDAY);
  }

  @Test
  void sundayShift() {
    assertThat(STRATEGY.shift(SUNDAY)).isEqualTo(TUESDAY);
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
    assertThat(STRATEGY.unshift(MONDAY)).containsExactly(MONDAY, SATURDAY);
  }

  @Test
  void tuesdayShift() {
    assertThat(STRATEGY.shift(TUESDAY)).isEqualTo(TUESDAY);
  }

  @Test
  void tuesdayUnshift() {
    assertThat(STRATEGY.unshift(TUESDAY)).containsExactly(TUESDAY, SUNDAY);
  }
}
