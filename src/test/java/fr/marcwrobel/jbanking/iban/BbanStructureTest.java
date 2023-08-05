package fr.marcwrobel.jbanking.iban;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import fr.marcwrobel.jbanking.IsoCountry;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;

class BbanStructureTest {

  @Test
  void nullReturnsEmpty() {
    assertThat(BbanStructure.forCountry(null)).isEmpty();
  }

  @Test
  void unsupportedCountryReturnsEmpty() {
    assertThat(BbanStructure.forCountry(IsoCountry.US)).isEmpty();
  }

  @Test
  void supportedCountryReturnsCorrespondingBbanDefinition() {
    for (BbanStructure structure : BbanStructure.values()) {
      Optional<BbanStructure> found = BbanStructure.forCountry(structure.getCountry());
      assertThat(found).contains(structure);
    }
  }

  @Test
  void supportedCountrySubdivisionReturnsCorrespondingBbanDefinition() {
    for (BbanStructure structure : BbanStructure.values()) {
      for (IsoCountry country : structure.getSubdivisions()) {
        Optional<BbanStructure> found = BbanStructure.forCountry(country);
        assertThat(found).contains(structure);
      }
    }
  }

  @Test
  void isBbanValidCannotBeCalledWithNull() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> BbanStructure.AL.isBbanValid(null));
  }

  @Test
  void ensureCountriesAreDefinedOnce() {
    Set<IsoCountry> countries = EnumSet.noneOf(IsoCountry.class);

    for (BbanStructure structure : BbanStructure.values()) {
      IsoCountry country = structure.getCountry();
      assertThat(countries).doesNotContain(country);
      countries.add(country);

      for (IsoCountry subDivision : structure.getSubdivisions()) {
        assertThat(countries).doesNotContain(subDivision);
        countries.add(subDivision);
      }
    }
  }
}
