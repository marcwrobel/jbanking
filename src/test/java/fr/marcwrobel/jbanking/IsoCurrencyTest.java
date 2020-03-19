package fr.marcwrobel.jbanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link IsoCountry} class.
 *
 * @author Marc Wrobel
 */
public class IsoCurrencyTest {

  @Test
  public void fromAlphaCodeAllowsNull() {
    assertNull(IsoCurrency.fromAlphabeticCode(null));
  }

  @Test
  public void fromAlphaCodeAllowsUnknownOrInvalidCodes() {
    assertNull(IsoCurrency.fromAlphabeticCode("AA"));
  }

  @Test
  public void fromAlphaCodeIsNotCaseSensitive() {
    assertEquals(
        IsoCurrency.EURO,
        IsoCurrency.fromAlphabeticCode(IsoCurrency.EURO.getAlphabeticCode().toLowerCase()));
  }

  @Test
  public void fromAlphaCodeWorksWithExistingValues() {
    for (IsoCurrency currency : IsoCurrency.values()) {
      assertEquals(currency, IsoCurrency.fromAlphabeticCode(currency.getAlphabeticCode()));
    }
  }

  @Test
  public void fromNumericCodeAllowsNull() {
    assertEquals(IsoCurrency.NO_UNIVERSAL_CURRENCY, IsoCurrency.fromNumericCode(null));
  }

  @Test
  public void fromNumericCodeAllowsUnknownOrInvalidCodes() {
    assertNull(IsoCurrency.fromNumericCode(-1));
    assertNull(IsoCurrency.fromNumericCode(1));
    assertNull(IsoCurrency.fromNumericCode(1000));
  }

  @Test
  public void fromNumericCodeWorksWithExistingValuesExceptForUicFranc() {
    for (IsoCurrency currency : IsoCurrency.values()) {
      if (currency != IsoCurrency.UIC_FRANC) {
        assertEquals(currency, IsoCurrency.fromNumericCode(currency.getNumericCode()));
      }
    }
  }
}
