package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.IsoCurrency.Category.FUND;
import static fr.marcwrobel.jbanking.IsoCurrency.Category.METAL;
import static fr.marcwrobel.jbanking.IsoCurrency.Category.NATIONAL;
import static fr.marcwrobel.jbanking.IsoCurrency.EUR;
import static fr.marcwrobel.jbanking.IsoCurrency.USS;
import static fr.marcwrobel.jbanking.IsoCurrency.XAU;
import static fr.marcwrobel.jbanking.IsoCurrency.fromAlphabeticCode;
import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link IsoCountry} class.
 */
class IsoCurrencyTest {

  private static final Set<String> NOT_IN_NV_I18N = new HashSet<>(Arrays.asList("UYW", "VED", "SLE"));

  @Test
  void fromAlphaCodeAllowsNull() {
    assertFalse(fromAlphabeticCode(null).isPresent());
  }

  @Test
  void fromAlphaCodeAllowsUnknownOrInvalidCodes() {
    assertFalse(fromAlphabeticCode("AA").isPresent());
  }

  @Test
  void fromAlphaCodeIsNotCaseSensitive() {
    Optional<IsoCurrency> currency = fromAlphabeticCode(EUR.getAlphabeticCode().toLowerCase());
    assertTrue(currency.isPresent());
    assertEquals(EUR, currency.get());
  }

  @Test
  void fromAlphaCodeWorksWithExistingValues() {
    for (IsoCurrency currency : IsoCurrency.values()) {
      Optional<IsoCurrency> found = fromAlphabeticCode(currency.getAlphabeticCode());
      assertTrue(found.isPresent());
      assertEquals(currency, found.get());
    }
  }

  @Test
  void fromNumericCodeAllowsUnknownOrInvalidCodes() {
    assertFalse(IsoCurrency.fromNumericCode(-1).isPresent());
    assertFalse(IsoCurrency.fromNumericCode(1).isPresent());
    assertFalse(IsoCurrency.fromNumericCode(1000).isPresent());
  }

  @Test
  void ensureNumericCodesAreDefinedOnce() {
    Set<Integer> codes = new HashSet<>();

    for (IsoCurrency currency : IsoCurrency.values()) {
      Integer code = currency.getNumericCode();
      assertFalse(codes.contains(code));
      codes.add(code);
    }
  }

  @Test
  void euroIsInNationalCategory() {
    Set<IsoCurrency> currencies = IsoCurrency.allOf(NATIONAL);
    assertTrue(currencies.contains(EUR));
    assertEquals(NATIONAL, EUR.getCategory());
  }

  @Test
  void goldIsInMetalCategory() {
    Set<IsoCurrency> currencies = IsoCurrency.allOf(METAL);
    assertTrue(currencies.contains(XAU));
    assertEquals(METAL, XAU.getCategory());
  }

  @Test
  void usDollarSameDayIsInMetalCategory() {
    Set<IsoCurrency> currencies = IsoCurrency.allOf(FUND);
    assertTrue(currencies.contains(USS));
    assertEquals(FUND, USS.getCategory());
  }

  // using nv-i18n helps us to keep the enum up-to-date
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

    assertTrue(undefinedCurrencies.isEmpty(), "Missing currencies : " + undefinedCurrencies);
  }

  // using nv-i18n helps us to keep the enum up-to-date
  @Test
  void ensureCountriesCompleteness() {
    Multimap<IsoCurrency, IsoCountry> missingCountries = HashMultimap.create();
    Set<CountryCode> unknownCountryCode = new HashSet<>();

    stream(IsoCurrency.values()).filter(isoCurrency -> !NOT_IN_NV_I18N.contains(isoCurrency.getAlphabeticCode())) // not
        // in
        // nv-i18n
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

    assertTrue(missingCountries.isEmpty(), "Missing countries : " + missingCountries);
    assertTrue(unknownCountryCode.isEmpty(), "Unknown countries : " + unknownCountryCode);
  }

  // using nv-i18n helps us to keep the enum up-to-date
  @Test
  void ensureNoDeprecated() {
    List<IsoCurrency> deprecated = stream(IsoCurrency.values())
        .filter(isoCurrency -> !isoCurrency.getAlphabeticCode().equals("CLF")) // wrong minor unit
        .filter(isoCurrency -> !NOT_IN_NV_I18N.contains(isoCurrency.getAlphabeticCode())) // not in nv-i18n
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

    assertTrue(deprecated.isEmpty(), "Deprecated currencies : " + deprecated);
  }

  @Test
  void ensureIsoCodeIsUsedForEnumEntries() {
    for (IsoCurrency currency : IsoCurrency.values()) {
      assertEquals(3, currency.name().length());
    }
  }
}
