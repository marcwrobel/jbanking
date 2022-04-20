package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A {@link Holiday} occurring at given dates.
 *
 * <p>This class is useful for modeling exceptional holidays like the golden jubilee in the United
 * Kingdom.
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public final class FixedHoliday implements Holiday {

  private final Set<LocalDate> dates;

  /**
   * Create a new holiday using the given dates.
   *
   * @param dates a collection of {@link LocalDate}s
   * @throws NullPointerException if the given collection is {@code null}
   */
  public FixedHoliday(Collection<LocalDate> dates) {
    this.dates = new HashSet<>(dates);
  }

  /**
   * Create a new holiday using the given dates.
   *
   * @param dates a collection of {@link LocalDate}s
   * @throws NullPointerException if the given collection is {@code null}
   */
  public FixedHoliday(LocalDate... dates) {
    this(Arrays.asList(dates));
  }

  /** @see Holiday#check(LocalDate) */
  @Override
  public boolean check(LocalDate date) {
    return dates.contains(requireNonNull(date));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FixedHoliday that = (FixedHoliday) o;
    return dates.equals(that.dates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dates);
  }

  @Override
  public String toString() {
    return "FixedHoliday{" + "dates=" + dates + '}';
  }
}
