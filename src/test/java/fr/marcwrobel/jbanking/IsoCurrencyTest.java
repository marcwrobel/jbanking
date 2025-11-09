package fr.marcwrobel.jbanking;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static fr.marcwrobel.jbanking.IsoCurrency.Category.*;
import static fr.marcwrobel.jbanking.IsoCurrency.*;
import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;

class IsoCurrencyTest {

  private static final Set<String> EXCLUDED_CURRENCIES = new HashSet<>(Arrays.asList(
      // Not in nv-i18n
      "UYW", "VED", "SLE", "XAD",
      // Deprecated
      "HRK"));

  @Test
  void fromAlphaCodeAllowsNull() {
    assertThat(fromAlphabeticCode(null)).isEmpty();
  }

  @Test
  void fromAlphaCodeAllowsUnknownOrInvalidCodes() {
    assertThat(fromAlphabeticCode("AA")).isEmpty();
  }

  @Test
  void fromAlphaCodeIsNotCaseSensitive() {
    Optional<IsoCurrency> currency = fromAlphabeticCode(EUR.getAlphabeticCode().toLowerCase());
    assertThat(currency).contains(EUR);
  }

  @Test
  void fromAlphaCodeAllowsLeadingAndTrailingWhitespaces() {
    Optional<IsoCurrency> currency = fromAlphabeticCode(" " + EUR.getAlphabeticCode() + " ");
    assertThat(currency).contains(EUR);
  }

  @Test
  void fromAlphaCodeWorksWithExistingValues() {
    for (IsoCurrency currency : IsoCurrency.values()) {
      Optional<IsoCurrency> found = fromAlphabeticCode(currency.getAlphabeticCode());
      assertThat(found).contains(currency);
    }
  }

  @Test
  void fromNumericCodeAllowsUnknownOrInvalidCodes() {
    assertThat(IsoCurrency.fromNumericCode(-1)).isEmpty();
    assertThat(IsoCurrency.fromNumericCode(1)).isEmpty();
    assertThat(IsoCurrency.fromNumericCode(1000)).isEmpty();
  }

  @Test
  void ensureNumericCodesAreDefinedOnce() {
    Set<Integer> codes = new HashSet<>();

    for (IsoCurrency currency : IsoCurrency.values()) {
      Integer code = currency.getNumericCode();
      assertThat(codes).doesNotContain(code);
      codes.add(code);
    }
  }

  @Test
  void euroIsInNationalCategory() {
    Set<IsoCurrency> currencies = IsoCurrency.allOf(NATIONAL);
    assertThat(currencies).contains(EUR);
    assertThat(EUR.getCategory()).isEqualTo(NATIONAL);
  }

  @Test
  void goldIsInMetalCategory() {
    Set<IsoCurrency> currencies = IsoCurrency.allOf(METAL);
    assertThat(currencies).contains(XAU);
    assertThat(XAU.getCategory()).isEqualTo(METAL);
  }

  @Test
  void usDollarSameDayIsInMetalCategory() {
    Set<IsoCurrency> currencies = IsoCurrency.allOf(FUND);
    assertThat(currencies).contains(USS);
    assertThat(USS.getCategory()).isEqualTo(FUND);
  }

  // using nv-i18n helps keeping the enum up-to-date
  @Test
  @SuppressWarnings("deprecation")
  void ensureCompleteness() {
    Set<CurrencyCode> exclusion = EnumSet.of(CurrencyCode.UNDEFINED, CurrencyCode.BYR, // https://wikipedia.org/wiki/Belarusian_ruble
        CurrencyCode.MRO, // https://wikipedia.org/wiki/Mauritanian_ouguiya
        CurrencyCode.RUR, // https://wikipedia.org/wiki/Russian_ruble
        CurrencyCode.STD, // https://wikipedia.org/wiki/S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe_dobra
        CurrencyCode.VEF, // https://wikipedia.org/wiki/Venezuelan_bol%C3%ADvar
        CurrencyCode.LTL // https://wikipedia.org/wiki/Lithuanian_litas
    );

    List<CurrencyCode> allCurrencies = EnumSet.allOf(CurrencyCode.class).stream()
        .filter(c -> !exclusion.contains(c)).collect(Collectors.toList());

    List<CurrencyCode> undefinedCurrencies = allCurrencies.stream()
        .filter(c -> !fromAlphabeticCode(c.name()).isPresent()).collect(Collectors.toList());

    assertThat(undefinedCurrencies).as("Missing currencies : " + undefinedCurrencies).isEmpty();
  }

  // using nv-i18n helps keeping the enum up-to-date
  @Test
  void ensureCountriesCompleteness() {
    Multimap<IsoCurrency, IsoCountry> missingCountries = HashMultimap.create();
    Set<CountryCode> unknownCountryCode = new HashSet<>();

    stream(IsoCurrency.values()).filter(currency -> !EXCLUDED_CURRENCIES.contains(currency.getAlphabeticCode()))
        .forEach(currency -> {
          CurrencyCode currencyCode = CurrencyCode.getByCode(currency.getAlphabeticCode());

          for (CountryCode countryCode : currencyCode.getCountryList()) {
            if (countryCode != CountryCode.EU) {
              Optional<IsoCountry> country = IsoCountry.fromAlpha2Code(countryCode.getAlpha2());

              if (country.isPresent()) {
                if (!currency.getCountries().contains(country.get())) {
                  missingCountries.put(currency, country.get());
                }
              } else {
                unknownCountryCode.add(countryCode);
              }
            }
          }
        });

    assertThat(missingCountries.isEmpty()).as("Missing countries : " + missingCountries).isTrue();
    assertThat(unknownCountryCode).as("Unknown countries : " + unknownCountryCode).isEmpty();
  }

  // using nv-i18n helps keeping the enum up-to-date
  @Test
  void ensureNoDeprecated() {
    List<IsoCurrency> deprecated = stream(IsoCurrency.values())
        .filter(isoCurrency -> !isoCurrency.getAlphabeticCode().equals("CLF")) // wrong minor unit
        .filter(isoCurrency -> !EXCLUDED_CURRENCIES.contains(isoCurrency.getAlphabeticCode())) // not in nv-i18n
        .filter(currency -> {
          CurrencyCode code = CurrencyCode.getByCode(currency.getAlphabeticCode());
          if (code != null) {
            if (currency.getNumericCode() == code.getNumeric()) {
              if (currency.getMinorUnit() != null) {
                return currency.getMinorUnit() != code.getMinorUnit();
              } else {
                return code.getMinorUnit() != -1;
              }
            } else {
              return true;
            }
          }
          return true;
        }).collect(Collectors.toList());

    assertThat(deprecated).as("Deprecated currencies : " + deprecated).isEmpty();
  }

  @Test
  void ensureIsoCodeIsUsedForEnumEntries() {
    for (IsoCurrency currency : IsoCurrency.values()) {
      assertThat(currency.name()).hasSize(3);
    }
  }
}
