package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.Agreement.EUROPEAN_ECONOMIC_AREA;
import static fr.marcwrobel.jbanking.Agreement.EUROPEAN_FREE_TRADE_ASSOCIATION;
import static fr.marcwrobel.jbanking.Agreement.EUROPEAN_UNION;
import static fr.marcwrobel.jbanking.Agreement.SEPA_COM_PACIFIQUE;
import static fr.marcwrobel.jbanking.Agreement.SINGLE_EURO_PAYMENTS_AREA;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AgreementTest {

  @Test
  void ensureConsistency() {
    for (IsoCountry country : IsoCountry.values()) {
      Set<Agreement> participations = country.participations();

      for (Agreement agreement : Agreement.values()) {
        assertThat(country.isParticipatingTo(agreement)).isEqualTo(agreement.getParticipants().contains(country));
        assertThat(participations.contains(agreement)).isEqualTo(country.isParticipatingTo(agreement));
      }
    }
  }

  @Test
  void sepaComPacifiqueParticipantsAreNotSingleEuroPaymentAreaParticipants() {
    for (IsoCountry participant : SEPA_COM_PACIFIQUE.getParticipants()) {
      assertThat(participant.isParticipatingTo(SINGLE_EURO_PAYMENTS_AREA)).as(participant::name).isFalse();
    }
  }

  @Test
  void europeanUnionParticipantsAreAlsoSingleEuroPaymentAreaParticipants() {
    for (IsoCountry participant : EUROPEAN_UNION.getParticipants()) {
      assertThat(participant.isParticipatingTo(SINGLE_EURO_PAYMENTS_AREA)).as(participant::name).isTrue();
    }
  }

  @Test
  void europeanUnionParticipantsAreEuropeanEconomicAreaParticipants() {
    for (IsoCountry participant : EUROPEAN_UNION.getParticipants()) {
      assertThat(participant.isParticipatingTo(EUROPEAN_ECONOMIC_AREA)).as(participant::name).isTrue();
    }
  }

  @Test
  void europeanFreeTradeAssociationParticipantsAreEuropeanEconomicAreaParticipants() {
    for (IsoCountry participant : EUROPEAN_FREE_TRADE_ASSOCIATION.getParticipants()) {
      assertThat(participant.isParticipatingTo(EUROPEAN_ECONOMIC_AREA)).as(participant::name).isTrue();
    }
  }
}
