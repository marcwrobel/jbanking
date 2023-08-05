package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.IsoCountry.AQ;
import static fr.marcwrobel.jbanking.IsoCountry.EH;
import static fr.marcwrobel.jbanking.IsoCountry.FR;
import static fr.marcwrobel.jbanking.IsoCountry.PS;
import static fr.marcwrobel.jbanking.IsoCountry.TW;
import static java.util.EnumSet.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
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
    assertThat(IsoCountry.fromAlpha2Code(null)).isEmpty();
  }

  @Test
  void fromAlpha3CodeAllowsNull() {
    assertThat(IsoCountry.fromAlpha3Code(null)).isEmpty();
  }

  @Test
  void fromAlpha2CodeAllowsUnknownOrInvalidCodes() {
    assertThat(IsoCountry.fromAlpha2Code("XXX")).isEmpty();
  }

  @Test
  void fromAlpha3CodeAllowsUnknownOrInvalidCodes() {
    assertThat(IsoCountry.fromAlpha3Code("XX")).isEmpty();
  }

  @Test
  void fromNumericCodeAllowsUnknownOrInvalidCodes() {
    assertThat(IsoCountry.fromNumericCode(-1)).isEmpty();
  }

  @Test
  void fromAlpha2CodeIsNotCaseSensitive() {
    Optional<IsoCountry> country = IsoCountry.fromAlpha2Code(IsoCountry.FR.getAlpha2Code().toLowerCase());
    assertThat(country).contains(FR);
  }

  @Test
  void fromAlpha3CodeIsNotCaseSensitive() {
    Optional<IsoCountry> country = IsoCountry.fromAlpha3Code(IsoCountry.FR.getAlpha3Code().toLowerCase());
    assertThat(country).contains(FR);
  }

  @Test
  void fromAlpha2CodeAllowsLeadingAndTrailingWhitespaces() {
    Optional<IsoCountry> country = IsoCountry.fromAlpha2Code(" " + IsoCountry.FR.getAlpha2Code().toLowerCase() + " ");
    assertThat(country).contains(FR);
  }

  @Test
  void fromAlpha3CodeAllowsLeadingAndTrailingWhitespaces() {
    Optional<IsoCountry> country = IsoCountry.fromAlpha3Code(" " + IsoCountry.FR.getAlpha3Code() + " ");
    assertThat(country).contains(FR);
  }

  @Test
  void fromAlpha2CodeWorksWithAllExistingValues() {
    for (IsoCountry country : IsoCountry.values()) {
      Optional<IsoCountry> result = IsoCountry.fromAlpha2Code(country.getAlpha2Code());
      assertThat(result).contains(country);
    }
  }

  @Test
  void fromAlpha3CodeWorksWithAllExistingValues() {
    for (IsoCountry country : IsoCountry.values()) {
      Optional<IsoCountry> result = IsoCountry.fromAlpha3Code(country.getAlpha3Code());
      assertThat(result).contains(country);
    }
  }

  @Test
  void fromNumericCodeWorksWithAllExistingValues() {
    for (IsoCountry country : IsoCountry.values()) {
      Optional<Integer> code = country.getNumericCode();
      if (code.isPresent()) {
        Optional<IsoCountry> result = IsoCountry.fromNumericCode(code.get());
        assertThat(result).contains(country);
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

    assertThat(undefinedCountries).as("Missing countries : " + undefinedCountries).isEmpty();
  }

  @Test
  void ensureAlpha3CodesAreDefinedOnce() {
    Set<String> codes = new HashSet<>();

    for (IsoCountry country : IsoCountry.values()) {
      String code = country.getAlpha3Code();
      assertThat(codes).doesNotContain(code);
      codes.add(code);
    }
  }

  @Test
  void ensureNumericCodesAreDefinedOnce() {
    Set<Integer> codes = new HashSet<>();

    for (IsoCountry country : IsoCountry.values()) {
      Optional<Integer> code = country.getNumericCode();
      if (code.isPresent()) {
        assertThat(codes).doesNotContain(code.get());
        codes.add(code.get());
      }
    }
  }

  // using nv-i18n helps us to keep the enum exact
  @Test
  void ensureExactness() {
    for (IsoCountry country : IsoCountry.values()) {
      CountryCode code = CountryCode.getByAlpha2Code(country.getAlpha2Code());
      assertThat(code).isNotNull();
      assertThat(country.getAlpha2Code()).isEqualTo(code.getAlpha2());
      assertThat(country.getAlpha3Code()).isEqualTo(code.getAlpha3());

      if (country.getNumericCode().isPresent()) {
        assertThat(country.getNumericCode()).contains(code.getNumeric());
      }
    }
  }

  // using nv-i18n helps us to keep the enum exact
  @Test
  void ensureNoDeprecated() {
    List<IsoCountry> deprecated = Arrays.stream(IsoCountry.values())
        .filter(c -> CountryCode.getByCode(c.getAlpha2Code()) == null).collect(Collectors.toList());

    assertThat(deprecated).as("Deprecated currencies : " + deprecated).isEmpty();
  }

  @Test
  void nullParticipationThrows() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> FR.isParticipatingTo(null));
  }

  @Test
  void dependentCountriesDependsOnIndependentCountry() {
    Set<IsoCountry> excludedCountries = of(AQ, PS, TW, EH);

    for (IsoCountry country : IsoCountry.values()) {
      if (country.isIndependent()) {
        assertThat(country.getDependency()).as(country::name).isEmpty();

      } else if (excludedCountries.contains(country)) {
        assertThat(country.getDependency()).as(country::name).isEmpty();

      } else {
        assertThat(country.getDependency()).as(country::name).isPresent();
        assertThat(country.getDependency().get().isIndependent()).as(country::name).isTrue();
      }
    }
  }

  @Test
  void ensureIsoAlpha2CodeIsUsedForEnumEntries() {
    for (IsoCountry country : IsoCountry.values()) {
      assertThat(country.name()).hasSize(2);
    }
  }
}
