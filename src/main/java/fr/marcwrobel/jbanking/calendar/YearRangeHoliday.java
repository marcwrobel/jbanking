package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.temporal.ValueRange;
import java.util.Objects;

/**
 * A {@link Holiday} that is valid only for a given range or years.
 *
 * <p>
 * This class is useful for modeling holidays like the German reformation day that was exceptionally a national holiday in 2017
 * because of 500 anniversary of the religious reformation in Europe.
 *
 * @since 2.1.0
 */
public final class YearRangeHoliday implements Holiday {

  private final Holiday base;
  private final ValueRange validityRange;

  /**
   * Create a new holiday using the given base and years of validity.
   *
   * @param base a non-null holiday to use as a base.
   * @param validityRange a non-null validity range.
   * @throws NullPointerException if either {@code base} or {@code validityRange} is {@code null}
   */
  public YearRangeHoliday(Holiday base, ValueRange validityRange) {
    this.base = requireNonNull(base);
    this.validityRange = requireNonNull(validityRange);
  }

  /**
   * Create a new holiday using the given base and years of validity.
   *
   * @param base a non-null holiday to use as a base.
   * @param from the minimum year of validity (included)
   * @param to the maximum year of validity (included)
   * @throws NullPointerException if {@code base} is {@code null}
   * @throws IllegalArgumentException if {@code from} is greater than {@code to}
   */
  public YearRangeHoliday(Holiday base, long from, long to) {
    this.base = requireNonNull(base);
    this.validityRange = ValueRange.of(from, to);
  }

  /**
   * @see Holiday#check(LocalDate)
   */
  @Override
  public boolean check(LocalDate date) {
    if (!validityRange.isValidValue(date.getYear())) {
      return false;
    }

    return base.check(date);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    YearRangeHoliday that = (YearRangeHoliday) o;
    return base.equals(that.base) && validityRange.equals(that.validityRange);
  }

  @Override
  public int hashCode() {
    return Objects.hash(base, validityRange);
  }

  @Override
  public String toString() {
    return "YearRangeHoliday{" + "base=" + base + ", validityRange=" + validityRange + '}';
  }
}
