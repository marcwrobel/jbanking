package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.IsoCountry.ANTARCTICA;
import static fr.marcwrobel.jbanking.IsoCountry.PALESTINE;
import static fr.marcwrobel.jbanking.IsoCountry.TAIWAN;
import static fr.marcwrobel.jbanking.IsoCountry.WESTERN_SAHARA;
import static java.util.EnumSet.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
class IsoCountryTest {

  @Test
  void fromCodeAllowsNull() {
    assertNull(IsoCountry.fromAlpha2Code(null));
  }

  @Test
  void fromCodeAllowsUnknownOrInvalidCodes() {
    assertNull(IsoCountry.fromAlpha2Code("XXX"));
  }

  @Test
  void fromCodeIsNotCaseSensitive() {
    assertEquals(
        IsoCountry.FRANCE,
        IsoCountry.fromAlpha2Code(IsoCountry.FRANCE.getAlpha2Code().toLowerCase()));
  }

  @Test
  void fromCodeWorksWithExistingValues() {
    for (IsoCountry country : IsoCountry.values()) {
      assertEquals(country, IsoCountry.fromAlpha2Code(country.getAlpha2Code()));
    }
  }

  // using nv-i18n helps us keeping the enum up-to-date
  @Test
  void ensureCompleteness() {
    Set<Assignment> assignments = of(Assignment.OFFICIALLY_ASSIGNED, Assignment.USER_ASSIGNED);

    List<CountryCode> allCountries =
        EnumSet.allOf(CountryCode.class).stream()
            .filter(c -> c != CountryCode.UNDEFINED)
            .filter(c -> assignments.contains(c.getAssignment()))
            .collect(Collectors.toList());

    List<CountryCode> undefinedCountries =
        allCountries.stream()
            .filter(c -> IsoCountry.fromAlpha2Code(c.getAlpha2()) == null)
            .collect(Collectors.toList());

    assertTrue(undefinedCountries.isEmpty(), "Missing countries : " + undefinedCountries);
  }

  // using nv-i18n helps us keeping the enum exact
  @Test
  void ensureExactness() {
    for (IsoCountry country : IsoCountry.values()) {
      CountryCode code = CountryCode.getByAlpha2Code(country.getAlpha2Code());
      assertNotNull(code);
      assertEquals(code.getAlpha2(), country.getAlpha2Code());
      assertEquals(code.getAlpha3(), country.getAlpha3Code());
      assertEquals(code.getNumeric(), country.getNumericCode());
    }
  }

  // using nv-i18n helps us keeping the enum exact
  @Test
  void ensureNoDeprecated() {
    List<IsoCountry> deprecated =
        Arrays.stream(IsoCountry.values())
            .filter(c -> CountryCode.getByCode(c.getAlpha2Code()) == null)
            .collect(Collectors.toList());

    assertTrue(deprecated.isEmpty(), "Deprecated currencies : " + deprecated);
  }

  @Test
  void dependentCountriesDependsOnIndependentCountry() {
    Set<IsoCountry> excludedCountries = of(ANTARCTICA, PALESTINE, TAIWAN, WESTERN_SAHARA);

    for (IsoCountry country : IsoCountry.values()) {
      if (country.isIndependent()) {
        assertFalse(country.getDependency().isPresent(), country::name);

      } else if (excludedCountries.contains(country)) {
        assertFalse(country.getDependency().isPresent(), country::name);

      } else {
        assertTrue(country.getDependency().isPresent(), country::name);
        assertTrue(country.getDependency().get().isIndependent(), country::name);
      }
    }
  }
}
