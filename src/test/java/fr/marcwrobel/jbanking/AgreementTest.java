package fr.marcwrobel.jbanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  @Test
  void sepaComPacifiqueParticipantsAreNotSingleEuroPaymentAreaParticipants() {
    for (IsoCountry participant : Agreement.SEPA_COM_PACIFIQUE.getParticipants()) {
      assertFalse(participant.isParticipatingTo(Agreement.SINGLE_EURO_PAYMENTS_AREA));
    }
  }

  @Test
  void europeanUnionParticipantsAreAlsoSingleEuroPaymentAreaParticipants() {
    for (IsoCountry participant : Agreement.EUROPEAN_UNION.getParticipants()) {
      assertTrue(participant.isParticipatingTo(Agreement.SINGLE_EURO_PAYMENTS_AREA));
    }
  }
}
