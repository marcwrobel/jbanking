package fr.marcwrobel.jbanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for the {@link IsoCountry} class.
 *
 * @author Marc Wrobel
 */
public class IsoCurrencyTest {

  private static final String CURRENCY_FILE_URL =
      "http://www.currency-iso.org/dam/downloads/dl_iso_table_a1.xml";

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

  @Test
  @Ignore("The external XML file this test depends on is no longer available")
  public void ensureEnumCompleteness() throws IOException, DocumentException {
    SAXReader reader = new SAXReader();
    Document document = reader.read(new URL(CURRENCY_FILE_URL));

    for (Iterator i = document.getRootElement().elementIterator("ISO_CURRENCY"); i.hasNext(); ) {
      Element element = (Element) i.next();

      IsoCurrency currencyFromAlphabeticCode =
          IsoCurrency.fromAlphabeticCode(element.elementText("ALPHABETIC_CODE"));
      IsoCurrency currencyFromNumericCode =
          IsoCurrency.fromNumericCode(safeParseInt(element.elementText("NUMERIC_CODE")));

      assertNotNull(currencyFromAlphabeticCode);
      assertNotNull(currencyFromNumericCode);

      if (currencyFromAlphabeticCode != IsoCurrency.UIC_FRANC) {
        assertEquals(currencyFromAlphabeticCode, currencyFromNumericCode);
      }

      if (currencyFromAlphabeticCode.getMinorUnit() != null) {
        assertEquals(
            currencyFromAlphabeticCode.getMinorUnit().toString(),
            element.elementText("MINOR_UNIT"));
      }

      assertNotNull(currencyFromAlphabeticCode.getCountries());
    }
  }

  private Integer safeParseInt(String s) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
