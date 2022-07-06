package fr.marcwrobel.jbanking.iban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.marcwrobel.jbanking.IsoCountry;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link BbanStructure} enum.
 */
class BbanStructureTest {

  @Test
  void nullReturnsEmpty() {
    assertFalse(BbanStructure.forCountry(null).isPresent());
  }

  @Test
  void unsupportedCountryReturnsEmpty() {
    assertFalse(BbanStructure.forCountry(IsoCountry.US).isPresent());
  }

  @Test
  void supportedCountryReturnsCorrespondingBbanDefinition() {
    for (BbanStructure structure : BbanStructure.values()) {
      Optional<BbanStructure> found = BbanStructure.forCountry(structure.getCountry());
      assertTrue(found.isPresent());
      assertEquals(structure, found.get());
    }
  }

  @Test
  void supportedCountrySubdivisionReturnsCorrespondingBbanDefinition() {
    for (BbanStructure structure : BbanStructure.values()) {
      for (IsoCountry country : structure.getSubdivisions()) {
        Optional<BbanStructure> found = BbanStructure.forCountry(country);
        assertTrue(found.isPresent());
        assertEquals(structure, found.get());
      }
    }
  }

  @Test
  void isBbanValidCannotBeCalledWithNull() {
    assertThrows(IllegalArgumentException.class, () -> BbanStructure.AL.isBbanValid(null));
  }

  @Test
  void ensureCountriesAreDefinedOnce() {
    Set<IsoCountry> countries = EnumSet.noneOf(IsoCountry.class);

    for (BbanStructure structure : BbanStructure.values()) {
      IsoCountry country = structure.getCountry();
      assertFalse(countries.contains(country));
      countries.add(country);

      for (IsoCountry subDivision : structure.getSubdivisions()) {
        assertFalse(countries.contains(subDivision));
        countries.add(subDivision);
      }
    }
  }
}
