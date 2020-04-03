package fr.marcwrobel.jbanking.calendar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    assertEquals(FRIDAY, STRATEGY.shift(FRIDAY));
  }

  @Test
  void fridayUnshift() {
    assertArrayEquals(new LocalDate[] {FRIDAY}, STRATEGY.unshift(FRIDAY));
  }

  @Test
  void saturdayShift() {
    assertEquals(MONDAY, STRATEGY.shift(SATURDAY));
  }

  @Test
  void saturdayUnshift() {
    assertArrayEquals(new LocalDate[] {SATURDAY}, STRATEGY.unshift(SATURDAY));
  }

  @Test
  void sundayShift() {
    assertEquals(TUESDAY, STRATEGY.shift(SUNDAY));
  }

  @Test
  void sundayUnshift() {
    assertArrayEquals(new LocalDate[] {SUNDAY}, STRATEGY.unshift(SUNDAY));
  }

  @Test
  void mondayShift() {
    assertEquals(MONDAY, STRATEGY.shift(MONDAY));
  }

  @Test
  void mondayUnshift() {
    assertArrayEquals(new LocalDate[] {MONDAY, SATURDAY}, STRATEGY.unshift(MONDAY));
  }

  @Test
  void tuesdayShift() {
    assertEquals(TUESDAY, STRATEGY.shift(TUESDAY));
  }

  @Test
  void tuesdayUnshift() {
    assertArrayEquals(new LocalDate[] {TUESDAY, SUNDAY}, STRATEGY.unshift(TUESDAY));
  }
}
