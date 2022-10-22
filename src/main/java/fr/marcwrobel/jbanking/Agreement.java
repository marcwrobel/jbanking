package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.IsoCountry.*;
import static java.util.Collections.unmodifiableSet;

import fr.marcwrobel.jbanking.internal.LastVerification;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * A list of <a href="https://en.wikipedia.org/wiki/Economic_integration">economic agreements</a> between countries or
 * territories that may be useful in banking processes (such as file processing).
 *
 * @since 2.1.0
 */
public enum Agreement {

  /**
   * The European Economic Area (EEA) was established via the Agreement on the European Economic Area, an international
   * agreement which enables the extension of the European Union's single market to member states of the European Free Trade
   * Association.
   *
   * <p>
   * Last verification date of this list can be seen in the {@code @LastVerification} value.
   *
   * @see <a href=
   *      "https://ec.europa.eu/eurostat/statistics-explained/index.php/Glossary%3AEuropean_Economic_Area_%28EEA%29">europa.eu -
   *      European Economic Area (EEA)</a>
   * @see <a href="https://wikipedia.org/wiki/European_Economic_Area">Wikipedia - European Economic Area</a>
   * @since 2.1.0
   */
  @LastVerification("2022-10-22")
  EUROPEAN_ECONOMIC_AREA(AT, BE, BG, HR, CY, CZ, DK, EE, FI, FR, DE, GR, HU, IS, IE, IT, LV, LI, LT, LU, MT, NL, NO,
      PL, PT, RO, SK, SI, ES, SE, CH),

  /**
   * The European Free Trade Association (EFTA) is a regional trade organization and free trade area. The organization operates
   * in parallel with the European Union (EU), and all member states participate in the European Single Market and are part of
   * the Schengen Area. They are not, however, party to the European Union Customs Union.
   *
   * <p>
   * Last verification date of this list can be seen in the {@code @LastVerification} value.
   *
   * @see <a href="https://www.efta.int/about-efta/the-efta-states">efta.int - The EFTA States</a>
   * @see <a href="https://wikipedia.org/wiki/European_Free_Trade_Association">Wikipedia - European Free Trade Association</a>
   * @since 2.1.0
   */
  @LastVerification("2022-10-22")
  EUROPEAN_FREE_TRADE_ASSOCIATION(IS, LI, NO, CH),

  /**
   * The European Union (EU) is a political and economic union of states that are located primarily in Europe. The EU has
   * developed an internal single market through a standardised system of laws that apply in all member states in those matters,
   * and only those matters, where members have agreed to act as one.
   *
   * <p>
   * Last verification date of this list can be seen in the {@code @LastVerification} value.
   *
   * <p>
   * <a href="https://wikipedia.org/wiki/Special_member_state_territories_and_the_European_Union">Special member state
   * territories</a> are not listed in this enum.
   *
   * @see <a href="https://europa.eu/european-union/about-eu/countries_en">europa.eu - Countries</a>
   * @see <a href="https://wikipedia.org/wiki/European_Union">Wikipedia - European Union</a>
   * @see <a href="https://wikipedia.org/wiki/Special_member_state_territories_and_the_European_Union">Wikipedia - Special
   *      member state territories and the European Union</a>
   * @since 2.1.0
   */
  @LastVerification("2022-10-22")
  EUROPEAN_UNION(AT, BE, BG, HR, CY, CZ, DK, EE, FI, FR, DE, GR, HU, IE, IT, LV, LT, LU, MT, NL, PL, PT, RO, SK, SI,
      ES, SE),

  /**
   * The French Republic is made up of a “SEPA zone” and a “non-SEPA” zone. In order to ensure the continuity of exchanges in
   * euros between the part of the French Republic which is in the SEPA zone and the part of the French Republic which is
   * outside the SEPA zone, namely French Polynesia, New Caledonia, Wallis and Futuna, the CFONB has defined a solution called
   * SEPA COM PACIFIQUE.
   *
   * <p>
   * Last verification date of this list can be seen in the {@code @LastVerification} value.
   *
   * @see <a href="https://www.cfonb.org/sepa/leuro-et-les-territoires-du-pacifique">Le SEPA et les territoires du Pacifique</a>
   * @see <a href="https://wikipedia.org/wiki/Single_Euro_Payments_Area">Wikipedia</a>
   * @since 2.1.0
   */
  @LastVerification("2022-10-27")
  SEPA_COM_PACIFIQUE(PF, NC, WF),

  /**
   * The Single Euro Payments Area (SEPA) is a payment-integration initiative of the European Union for simplification of bank
   * transfers denominated in euro. The aim of SEPA is to improve the efficiency of cross-border payments and turn the
   * previously fragmented national markets for euro payments into a single domestic one.
   *
   * <p>
   * Last verification date of this list can be seen in the {@code @LastVerification} value.
   *
   * @see <a href="https://www.europeanpaymentscouncil.eu/document-library/other/epc-list-sepa-scheme-countries">EPC409-09 EPC
   *      List of SEPA Countries v4.0 - February 2021</a>
   * @see <a href="https://www.iso13616.org/">IBAN registry</a>
   * @see <a href="https://wikipedia.org/wiki/Single_Euro_Payments_Area">Wikipedia</a>
   * @since 2.1.0
   */
  @LastVerification("2022-10-22")
  SINGLE_EURO_PAYMENTS_AREA(
      // EU countries
      AT, BE, BG, HR, CY, CZ, DK, EE, FI, FR, DE, GR, HU, IE, IT, LV, LT, LU, MT, NL, PL, PT, RO, SK, SI, ES, SE,
      // + EEA countries
      IS, LI, NO,
      // + Non-EEA Countries
      AD, MC, SM, CH, GB, VA,
      // + through Finland
      AX,
      // + through France
      GF, GP, MQ, YT, RE, BL, MF, PM,
      // + through The United Kingdom
      GI, GG, JE, IM);

  @SuppressWarnings("ImmutableEnumChecker") // initialized with Collections.unmodifiableSet(...).
  private final Set<IsoCountry> participants;

  Agreement(IsoCountry... participants) {
    // emptiness is checked by EnumSet.copyOf, no need to check it here
    this.participants = unmodifiableSet(EnumSet.copyOf(Arrays.asList(participants)));
  }

  /**
   * Get the participants of this agreement.
   *
   * @return a non-null, non-empty and unmodifiable set of {@link IsoCountry}
   */
  public Set<IsoCountry> getParticipants() {
    return participants;
  }
}
