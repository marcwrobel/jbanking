package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A {@link Holiday} relative to another {@link Holiday} with a fixed day shift.
 *
 * <p>
 * This class is modeling holidays like <a href="https://wikipedia.org/wiki/Independence_Day_%28United_States%29">the
 * Independence Day</a> in the United States.
 *
 * @since 2.1.0
 */
public final class ShiftedHoliday implements Holiday {

  private final Holiday base;
  private final ShiftingStrategy strategy;

  /**
   * Create a new holiday using the given {@link Holiday base holiday} and shifting strategy.
   *
   * @param base a non-null holiday to use as a base
   * @param strategy a non-null strategy to use as for shifting dates
   * @throws NullPointerException if the either {@code base} or {@code strategy} is {@code null}
   */
  public ShiftedHoliday(Holiday base, ShiftingStrategy strategy) {
    this.base = requireNonNull(base);
    this.strategy = requireNonNull(strategy);
  }

  /**
   * @see Holiday#check(LocalDate)
   */
  @Override
  public boolean check(LocalDate date) {
    for (LocalDate candidate : strategy.unshift(date)) {
      if (base.check(candidate)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ShiftedHoliday that = (ShiftedHoliday) o;
    return base.equals(that.base) && strategy == that.strategy;
  }

  @Override
  public int hashCode() {
    return Objects.hash(base, strategy);
  }

  @Override
  public String toString() {
    return "ShiftedHoliday{" + "base=" + base + ", strategy=" + strategy + '}';
  }
}
