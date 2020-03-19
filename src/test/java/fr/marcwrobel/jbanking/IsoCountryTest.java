package fr.marcwrobel.jbanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CountryCode.Assignment;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link IsoCountry} class.
 *
 * @author Marc Wrobel
 */
public class IsoCountryTest {

  @Test
  public void fromCodeAllowsNull() {
    assertNull(IsoCountry.fromCode(null));
  }

  @Test
  public void fromCodeAllowsUnknownOrInvalidCodes() {
    assertNull(IsoCountry.fromCode("XXX"));
  }

  @Test
  public void fromCodeIsNotCaseSensitive() {
    assertEquals(IsoCountry.FRANCE, IsoCountry.fromCode(IsoCountry.FRANCE.getCode().toLowerCase()));
  }

  @Test
  public void fromCodeWorksWithExistingValues() {
    for (IsoCountry country : IsoCountry.values()) {
      assertEquals(country, IsoCountry.fromCode(country.getCode()));
    }
  }

  @Test
  public void ensureCompleteness() {
    Set<Assignment> assignments =
        EnumSet.of(Assignment.OFFICIALLY_ASSIGNED, Assignment.USER_ASSIGNED);

    List<CountryCode> allCountries =
        EnumSet.allOf(CountryCode.class).stream()
            .filter(c -> c != CountryCode.UNDEFINED)
            .filter(c -> assignments.contains(c.getAssignment()))
            .collect(Collectors.toList());

    List<CountryCode> undefinedCountries =
        allCountries.stream()
            .filter(c -> IsoCountry.fromCode(c.getAlpha2()) == null)
            .collect(Collectors.toList());

    assertTrue(undefinedCountries.isEmpty(), "Missing countries : " + undefinedCountries);
  }

  @Test
  public void ensureNoDeprecated() {
    List<IsoCountry> deprecated =
        Arrays.stream(IsoCountry.values())
            .filter(c -> CountryCode.getByCode(c.getCode()) == null)
            .collect(Collectors.toList());

    assertTrue(deprecated.isEmpty(), "Deprecated currencies : " + deprecated);
  }
}
