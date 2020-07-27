package fr.marcwrobel.jbanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.neovisionaries.i18n.CurrencyCode;
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
class IsoCurrencyTest {

  @Test
  void fromAlphaCodeAllowsNull() {
    assertNull(IsoCurrency.fromAlphabeticCode(null));
  }

  @Test
  void fromAlphaCodeAllowsUnknownOrInvalidCodes() {
    assertNull(IsoCurrency.fromAlphabeticCode("AA"));
  }

  @Test
  void fromAlphaCodeIsNotCaseSensitive() {
    assertEquals(
        IsoCurrency.EURO,
        IsoCurrency.fromAlphabeticCode(IsoCurrency.EURO.getAlphabeticCode().toLowerCase()));
  }

  @Test
  void fromAlphaCodeWorksWithExistingValues() {
    for (IsoCurrency currency : IsoCurrency.values()) {
      assertEquals(currency, IsoCurrency.fromAlphabeticCode(currency.getAlphabeticCode()));
    }
  }

  @Test
  void fromNumericCodeAllowsNull() {
    assertEquals(IsoCurrency.NO_UNIVERSAL_CURRENCY, IsoCurrency.fromNumericCode(null));
  }

  @Test
  void fromNumericCodeAllowsUnknownOrInvalidCodes() {
    assertNull(IsoCurrency.fromNumericCode(-1));
    assertNull(IsoCurrency.fromNumericCode(1));
    assertNull(IsoCurrency.fromNumericCode(1000));
  }

  @Test
  void ensureCompleteness() {
    Set<CurrencyCode> exclusion =
        EnumSet.of(
            CurrencyCode.UNDEFINED,
            CurrencyCode.BYR, // https://wikipedia.org/wiki/Belarusian_ruble
            CurrencyCode.MRO, // https://wikipedia.org/wiki/Mauritanian_ouguiya
            CurrencyCode.RUR, // https://wikipedia.org/wiki/Russian_ruble
            CurrencyCode
                .STD, // https://wikipedia.org/wiki/S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe_dobra
            CurrencyCode.VEF // https://wikipedia.org/wiki/Venezuelan_bol%C3%ADvar
            );

    List<CurrencyCode> allCurrencies =
        EnumSet.allOf(CurrencyCode.class).stream()
            .filter(c -> !exclusion.contains(c))
            .collect(Collectors.toList());

    List<CurrencyCode> undefinedCurrencies =
        allCurrencies.stream()
            .filter(c -> IsoCurrency.fromAlphabeticCode(c.name()) == null)
            .collect(Collectors.toList());

    assertTrue(undefinedCurrencies.isEmpty(), "Missing currencies : " + undefinedCurrencies);
  }

  @Test
  void ensureNoDeprecated() {
    List<IsoCurrency> deprecated =
        Arrays.stream(IsoCurrency.values())
            .filter(c -> c != IsoCurrency.NO_UNIVERSAL_CURRENCY)
            .filter(
                currency -> {
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
                })
            .collect(Collectors.toList());

    assertTrue(deprecated.isEmpty(), "Deprecated currencies : " + deprecated);
  }
}
