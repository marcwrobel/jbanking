package fr.marcwrobel.jbanking.iban;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import fr.marcwrobel.jbanking.IsoCountry;
import org.junit.Test;

/**
 * Tests for the {@link BbanStructure} enum.
 *
 * @author Marc Wrobel
 */
public class BbanStructureTest {

  @Test
  public void nullReturnsNull() {
    assertNull(BbanStructure.forCountry(null));
  }

  @Test
  public void unsupportedCountryReturnsNull() {
    assertNull(BbanStructure.forCountry(IsoCountry.UNITED_STATES));
  }

  @Test
  public void supportedCountryReturnsCorrespondingBbanDefinition() {
    for (BbanStructure structure : BbanStructure.values()) {
      assertEquals(structure, BbanStructure.forCountry(structure.getCountry()));
    }
  }

  @Test
  public void supportedCountrySubdivisionReturnsCorrespondingBbanDefinition() {
    for (BbanStructure structure : BbanStructure.values()) {
      for (IsoCountry country : structure.getSubdivisions()) {
        assertEquals(structure, BbanStructure.forCountry(country));
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void isBbanValidCannotBeCalledWithNull() {
    BbanStructure.ALBANIA.isBbanValid(null);
  }
}
