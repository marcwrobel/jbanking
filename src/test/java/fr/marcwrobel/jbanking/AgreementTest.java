package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.Agreement.EUROPEAN_ECONOMIC_AREA;
import static fr.marcwrobel.jbanking.Agreement.EUROPEAN_FREE_TRADE_ASSOCIATION;
import static fr.marcwrobel.jbanking.Agreement.EUROPEAN_UNION;
import static fr.marcwrobel.jbanking.Agreement.SEPA_COM_PACIFIQUE;
import static fr.marcwrobel.jbanking.Agreement.SINGLE_EURO_PAYMENTS_AREA;
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
        assertEquals(agreement.getParticipants().contains(country), country.isParticipatingTo(agreement));
        assertEquals(country.isParticipatingTo(agreement), participations.contains(agreement));
      }
    }
  }

  @Test
  void sepaComPacifiqueParticipantsAreNotSingleEuroPaymentAreaParticipants() {
    for (IsoCountry participant : SEPA_COM_PACIFIQUE.getParticipants()) {
      assertFalse(participant.isParticipatingTo(SINGLE_EURO_PAYMENTS_AREA), participant::name);
    }
  }

  @Test
  void europeanUnionParticipantsAreAlsoSingleEuroPaymentAreaParticipants() {
    for (IsoCountry participant : EUROPEAN_UNION.getParticipants()) {
      assertTrue(participant.isParticipatingTo(SINGLE_EURO_PAYMENTS_AREA), participant::name);
    }
  }

  @Test
  void europeanUnionParticipantsAreEuropeanEconomicAreaParticipants() {
    for (IsoCountry participant : EUROPEAN_UNION.getParticipants()) {
      assertTrue(participant.isParticipatingTo(EUROPEAN_ECONOMIC_AREA), participant::name);
    }
  }

  @Test
  void europeanFreeTradeAssociationParticipantsAreEuropeanEconomicAreaParticipants() {
    for (IsoCountry participant : EUROPEAN_FREE_TRADE_ASSOCIATION.getParticipants()) {
      assertTrue(participant.isParticipatingTo(EUROPEAN_ECONOMIC_AREA), participant::name);
    }
  }
}
