package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WesternEasterTest {

  private static final WesternEaster HOLIDAY = WesternEaster.INSTANCE;

  private static Stream<Arguments> easter500() {
    InputStream easter500 = WesternEasterTest.class.getClassLoader().getResourceAsStream("easter500.txt");
    return new BufferedReader(new InputStreamReader(Objects.requireNonNull(easter500))).lines()
        .map(line -> line.trim().replaceAll("\\s+", " ")).map(line -> line.split(" ")).map(Arguments::of);
  }

  @Test
  void checkDoesNotAcceptNull() {
    // noinspection ConstantConditions
    assertThrows(NullPointerException.class, () -> HOLIDAY.check(null));
  }

  // Data from https://www.census.gov/data/software/x13as/genhol/easter-dates-frequency.html
  @ParameterizedTest
  @MethodSource("easter500")
  void easter500(int month, int dayOfMonth, int year) {
    LocalDate date = LocalDate.of(year, month, dayOfMonth);
    assertTrue(HOLIDAY.check(date));
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

    assertEquals(1, count);
  }
}
