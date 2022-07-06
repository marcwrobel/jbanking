package fr.marcwrobel.jbanking.calendar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    assertEquals(THURSDAY, STRATEGY.shift(THURSDAY));
  }

  @Test
  void thursdayUnshift() {
    assertArrayEquals(new LocalDate[] { THURSDAY }, STRATEGY.unshift(THURSDAY));
  }

  @Test
  void fridayShift() {
    assertEquals(FRIDAY, STRATEGY.shift(FRIDAY));
  }

  @Test
  void fridayUnshift() {
    assertArrayEquals(new LocalDate[] { FRIDAY }, STRATEGY.unshift(FRIDAY));
  }

  @Test
  void saturdayShift() {
    assertEquals(SATURDAY, STRATEGY.shift(SATURDAY));
  }

  @Test
  void saturdayUnshift() {
    assertArrayEquals(new LocalDate[] { SATURDAY }, STRATEGY.unshift(SATURDAY));
  }

  @Test
  void sundayShift() {
    assertEquals(MONDAY, STRATEGY.shift(SUNDAY));
  }

  @Test
  void sundayUnshift() {
    assertArrayEquals(new LocalDate[] { SUNDAY }, STRATEGY.unshift(SUNDAY));
  }

  @Test
  void mondayShift() {
    assertEquals(MONDAY, STRATEGY.shift(MONDAY));
  }

  @Test
  void mondayUnshift() {
    assertArrayEquals(new LocalDate[] { MONDAY, SUNDAY }, STRATEGY.unshift(MONDAY));
  }
}
