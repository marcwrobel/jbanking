package fr.marcwrobel.jbanking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import org.junit.jupiter.api.Test;

class AgreementTest {

  @Test
  void ensureConsistency() {
    for (IsoCountry country : IsoCountry.values()) {
      Set<Agreement> participations = country.participations();

      for (Agreement agreement : Agreement.values()) {
        assertEquals(
            agreement.getParticipants().contains(country), country.isParticipatingTo(agreement));
        assertEquals(country.isParticipatingTo(agreement), participations.contains(agreement));
      }
    }
  }
}
