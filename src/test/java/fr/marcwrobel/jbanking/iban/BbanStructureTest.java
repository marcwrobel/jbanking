package fr.marcwrobel.jbanking.iban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.marcwrobel.jbanking.IsoCountry;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link BbanStructure} enum.
 *
 * @author Marc Wrobel
 */
class BbanStructureTest {

  @Test
  void nullReturnsNull() {
    assertNull(BbanStructure.forCountry(null));
  }

  @Test
  void unsupportedCountryReturnsNull() {
    assertNull(BbanStructure.forCountry(IsoCountry.US));
  }

  @Test
  void supportedCountryReturnsCorrespondingBbanDefinition() {
    for (BbanStructure structure : BbanStructure.values()) {
      assertEquals(structure, BbanStructure.forCountry(structure.getCountry()));
    }
  }

  @Test
  void supportedCountrySubdivisionReturnsCorrespondingBbanDefinition() {
    for (BbanStructure structure : BbanStructure.values()) {
      for (IsoCountry country : structure.getSubdivisions()) {
        assertEquals(structure, BbanStructure.forCountry(country));
      }
    }
  }

  @Test
  void isBbanValidCannotBeCalledWithNull() {
    assertThrows(IllegalArgumentException.class, () -> BbanStructure.ALBANIA.isBbanValid(null));
  }
}
