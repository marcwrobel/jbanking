package fr.marcwrobel.jbanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

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
}
