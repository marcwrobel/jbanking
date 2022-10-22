package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.IsoCountry.AQ;
import static fr.marcwrobel.jbanking.IsoCountry.EH;
import static fr.marcwrobel.jbanking.IsoCountry.FR;
import static fr.marcwrobel.jbanking.IsoCountry.PS;
import static fr.marcwrobel.jbanking.IsoCountry.TW;
import static java.util.EnumSet.of;
import static org.junit.jupiter.api.Assertions.*;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CountryCode.Assignment;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class IsoCountryTest {

  @Test
  void fromAlpha2CodeAllowsNull() {
    assertFalse(IsoCountry.fromAlpha2Code(null).isPresent());
  }

  @Test
  void fromAlpha3CodeAllowsNull() {
    assertFalse(IsoCountry.fromAlpha3Code(null).isPresent());
  }

  @Test
  void fromAlpha2CodeAllowsUnknownOrInvalidCodes() {
    assertFalse(IsoCountry.fromAlpha2Code("XXX").isPresent());
  }

  @Test
  void fromAlpha3CodeAllowsUnknownOrInvalidCodes() {
    assertFalse(IsoCountry.fromAlpha3Code("XX").isPresent());
  }

  @Test
  void fromNumericCodeAllowsUnknownOrInvalidCodes() {
    assertFalse(IsoCountry.fromNumericCode(-1).isPresent());
  }

  @Test
  void fromAlpha2CodeIsNotCaseSensitive() {
    Optional<IsoCountry> country = IsoCountry.fromAlpha2Code(IsoCountry.FR.getAlpha2Code().toLowerCase());

    assertTrue(country.isPresent());
    assertEquals(FR, country.get());
  }

  @Test
  void fromAlpha3CodeIsNotCaseSensitive() {
    Optional<IsoCountry> country = IsoCountry.fromAlpha3Code(IsoCountry.FR.getAlpha3Code().toLowerCase());

    assertTrue(country.isPresent());
    assertEquals(FR, country.get());
  }

  @Test
  void fromAlpha2CodeAllowsLeadingAndTrailingWhitespaces() {
    Optional<IsoCountry> country = IsoCountry.fromAlpha2Code(" " + IsoCountry.FR.getAlpha2Code().toLowerCase() + " ");

    assertTrue(country.isPresent());
    assertEquals(FR, country.get());
  }

  @Test
  void fromAlpha3CodeAllowsLeadingAndTrailingWhitespaces() {
    Optional<IsoCountry> country = IsoCountry.fromAlpha3Code(" " + IsoCountry.FR.getAlpha3Code() + " ");

    assertTrue(country.isPresent());
    assertEquals(FR, country.get());
  }

  @Test
  void fromAlpha2CodeWorksWithAllExistingValues() {
    for (IsoCountry country : IsoCountry.values()) {
      Optional<IsoCountry> result = IsoCountry.fromAlpha2Code(country.getAlpha2Code());
      assertTrue(result.isPresent());
      assertEquals(country, result.get());
    }
  }

  @Test
  void fromAlpha3CodeWorksWithAllExistingValues() {
    for (IsoCountry country : IsoCountry.values()) {
      Optional<IsoCountry> result = IsoCountry.fromAlpha3Code(country.getAlpha3Code());
      assertTrue(result.isPresent());
      assertEquals(country, result.get());
    }
  }

  @Test
  void fromNumericCodeWorksWithAllExistingValues() {
    for (IsoCountry country : IsoCountry.values()) {
      Optional<Integer> code = country.getNumericCode();
      if (code.isPresent()) {
        Optional<IsoCountry> result = IsoCountry.fromNumericCode(code.get());
        assertTrue(result.isPresent());
        assertEquals(country, result.get());
      }
    }
  }

  // using nv-i18n helps us to keep the enum up-to-date
  @Test
  void ensureCompleteness() {
    Set<Assignment> assignments = of(Assignment.OFFICIALLY_ASSIGNED, Assignment.USER_ASSIGNED);

    List<CountryCode> allCountries = EnumSet.allOf(CountryCode.class).stream()
        .filter(c -> !EnumSet.of(CountryCode.UNDEFINED,
            // XI and XU are not official ISO 3166 country codes and are mainly for
            // customs declarations (see
            // https://www.uktradeinfo.com/news/changes-to-country-and-commodity-codes-in-2022/). They are
            // not
            // listed in IBAN registry. So they will not be added to the IsoCountry
            // enum.
            CountryCode.XI, CountryCode.XU).contains(c))
        .filter(c -> assignments.contains(c.getAssignment())).collect(Collectors.toList());

    List<CountryCode> undefinedCountries = allCountries.stream()
        .filter(c -> !IsoCountry.fromAlpha2Code(c.getAlpha2()).isPresent()).collect(Collectors.toList());

    assertTrue(undefinedCountries.isEmpty(), "Missing countries : " + undefinedCountries);
  }

  @Test
  void ensureAlpha3CodesAreDefinedOnce() {
    Set<String> codes = new HashSet<>();

    for (IsoCountry country : IsoCountry.values()) {
      String code = country.getAlpha3Code();
      assertFalse(codes.contains(code));
      codes.add(code);
    }
  }

  @Test
  void ensureNumericCodesAreDefinedOnce() {
    Set<Integer> codes = new HashSet<>();

    for (IsoCountry country : IsoCountry.values()) {
      Optional<Integer> code = country.getNumericCode();
      if (code.isPresent()) {
        assertFalse(codes.contains(code.get()));
        codes.add(code.get());
      }
    }
  }

  // using nv-i18n helps us to keep the enum exact
  @Test
  void ensureExactness() {
    for (IsoCountry country : IsoCountry.values()) {
      CountryCode code = CountryCode.getByAlpha2Code(country.getAlpha2Code());
      assertNotNull(code);
      assertEquals(code.getAlpha2(), country.getAlpha2Code());
      assertEquals(code.getAlpha3(), country.getAlpha3Code());

      if (country.getNumericCode().isPresent()) {
        assertEquals(code.getNumeric(), country.getNumericCode().get());
      }
    }
  }

  // using nv-i18n helps us to keep the enum exact
  @Test
  void ensureNoDeprecated() {
    List<IsoCountry> deprecated = Arrays.stream(IsoCountry.values())
        .filter(c -> CountryCode.getByCode(c.getAlpha2Code()) == null).collect(Collectors.toList());

    assertTrue(deprecated.isEmpty(), "Deprecated currencies : " + deprecated);
  }

  @Test
  void nullParticipationThrows() {
    assertThrows(IllegalArgumentException.class, () -> FR.isParticipatingTo(null));
  }

  @Test
  void dependentCountriesDependsOnIndependentCountry() {
    Set<IsoCountry> excludedCountries = of(AQ, PS, TW, EH);

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

  @Test
  void ensureIsoAlpha2CodeIsUsedForEnumEntries() {
    for (IsoCountry country : IsoCountry.values()) {
      assertEquals(2, country.name().length());
    }
  }
}
