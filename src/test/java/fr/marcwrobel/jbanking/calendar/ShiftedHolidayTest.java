package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.ShiftingStrategy.CLOSEST_WEEKDAY;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    assertThrows(NullPointerException.class, () -> new RelativeHoliday(null, 0));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThrows(NullPointerException.class, () -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(strings = { "2017-07-04", "2018-07-04", "2019-07-04", "2020-07-03", "2021-07-04", "2022-07-04",
      "2023-07-04", "2024-07-04", "2025-07-04", "2026-07-03", "2027-07-05", "2028-07-04", "2029-07-04" })
  void holidayCheckSucceed(String date) {
    assertTrue(HOLIDAY.check(LocalDate.parse(date)));
  }

  @Test
  void equalsAndHashCodeAndToString() {
    Holiday holiday1 = new ShiftedHoliday(BASE, STRATEGY);
    Holiday holiday2 = new ShiftedHoliday(BASE, STRATEGY);
    Holiday holiday3 = new ShiftedHoliday(new MonthDayHoliday(MonthDay.of(MONTH, DAY + 1)), STRATEGY);

    assertEquals(holiday1, holiday2);
    assertEquals(holiday2, holiday1);
    assertEquals(holiday1, holiday1);
    assertEquals(holiday1.hashCode(), holiday2.hashCode());
    assertEquals(holiday1.toString(), holiday2.toString());

    assertNotEquals(holiday1, holiday3);
    assertNotEquals(holiday3, holiday1);
    assertNotEquals(holiday1.hashCode(), holiday3.hashCode());
    assertNotEquals(holiday1.toString(), holiday3.toString());

    // do not modify - bullshit tests to improve coverage and have a better visibility in sonar
    assertFalse(holiday1.equals(null));
    assertFalse(holiday1.equals(new Object()));
  }
}
