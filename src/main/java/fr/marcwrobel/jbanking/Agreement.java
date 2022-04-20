package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.IsoCountry.AD;
import static fr.marcwrobel.jbanking.IsoCountry.AT;
import static fr.marcwrobel.jbanking.IsoCountry.AX;
import static fr.marcwrobel.jbanking.IsoCountry.BE;
import static fr.marcwrobel.jbanking.IsoCountry.BG;
import static fr.marcwrobel.jbanking.IsoCountry.BL;
import static fr.marcwrobel.jbanking.IsoCountry.CH;
import static fr.marcwrobel.jbanking.IsoCountry.CY;
import static fr.marcwrobel.jbanking.IsoCountry.CZ;
import static fr.marcwrobel.jbanking.IsoCountry.DE;
import static fr.marcwrobel.jbanking.IsoCountry.DK;
import static fr.marcwrobel.jbanking.IsoCountry.EE;
import static fr.marcwrobel.jbanking.IsoCountry.ES;
import static fr.marcwrobel.jbanking.IsoCountry.FI;
import static fr.marcwrobel.jbanking.IsoCountry.FR;
import static fr.marcwrobel.jbanking.IsoCountry.GB;
import static fr.marcwrobel.jbanking.IsoCountry.GF;
import static fr.marcwrobel.jbanking.IsoCountry.GG;
import static fr.marcwrobel.jbanking.IsoCountry.GI;
import static fr.marcwrobel.jbanking.IsoCountry.GP;
import static fr.marcwrobel.jbanking.IsoCountry.GR;
import static fr.marcwrobel.jbanking.IsoCountry.HR;
import static fr.marcwrobel.jbanking.IsoCountry.HU;
import static fr.marcwrobel.jbanking.IsoCountry.IE;
import static fr.marcwrobel.jbanking.IsoCountry.IM;
import static fr.marcwrobel.jbanking.IsoCountry.IS;
import static fr.marcwrobel.jbanking.IsoCountry.IT;
import static fr.marcwrobel.jbanking.IsoCountry.JE;
import static fr.marcwrobel.jbanking.IsoCountry.LI;
import static fr.marcwrobel.jbanking.IsoCountry.LT;
import static fr.marcwrobel.jbanking.IsoCountry.LU;
import static fr.marcwrobel.jbanking.IsoCountry.LV;
import static fr.marcwrobel.jbanking.IsoCountry.MC;
import static fr.marcwrobel.jbanking.IsoCountry.MF;
import static fr.marcwrobel.jbanking.IsoCountry.MQ;
import static fr.marcwrobel.jbanking.IsoCountry.MT;
import static fr.marcwrobel.jbanking.IsoCountry.NC;
import static fr.marcwrobel.jbanking.IsoCountry.NL;
import static fr.marcwrobel.jbanking.IsoCountry.NO;
import static fr.marcwrobel.jbanking.IsoCountry.PF;
import static fr.marcwrobel.jbanking.IsoCountry.PL;
import static fr.marcwrobel.jbanking.IsoCountry.PM;
import static fr.marcwrobel.jbanking.IsoCountry.PT;
import static fr.marcwrobel.jbanking.IsoCountry.RE;
import static fr.marcwrobel.jbanking.IsoCountry.RO;
import static fr.marcwrobel.jbanking.IsoCountry.SE;
import static fr.marcwrobel.jbanking.IsoCountry.SI;
import static fr.marcwrobel.jbanking.IsoCountry.SK;
import static fr.marcwrobel.jbanking.IsoCountry.SM;
import static fr.marcwrobel.jbanking.IsoCountry.VA;
import static fr.marcwrobel.jbanking.IsoCountry.WF;
import static fr.marcwrobel.jbanking.IsoCountry.YT;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * A list of <a href="https://en.wikipedia.org/wiki/Economic_integration">economic agreements</a>
 * between countries or territories that may be useful in banking processes (such as file
 * processing).
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public enum Agreement {

  /**
   * The European Economic Area (EEA) was established via the Agreement on the European Economic
   * Area, an international agreement which enables the extension of the European Union's single
   * market to member states of the European Free Trade Association.
   *
   * <p>This enum was last updated on 2020-08-03 based on information given on <a
   * href="https://wikipedia.org/wiki/European_Economic_Area">Wikipedia - European Economic
   * Area</a>.
   *
   * @see <a
   *     href="https://ec.europa.eu/eurostat/statistics-explained/index.php/Glossary:European_Economic_Area_(EEA)">europa.eu
   *     - European Economic Area (EEA)</a>
   * @see <a href="https://wikipedia.org/wiki/European_Economic_Area">Wikipedia - European Economic
   *     Area</a>
   * @since 2.1.0
   */
  EUROPEAN_ECONOMIC_AREA(
      AT, BE, BG, HR, CY, CZ, DK, EE, FI, FR, DE, GR, HU, IS, IE, IT, LV, LI, LT, LU, MT, NL, NO,
      PL, PT, RO, SK, SI, ES, SE, CH),

  /**
   * The European Free Trade Association (EFTA) is a regional trade organization and free trade
   * area. The organization operates in parallel with the European Union (EU), and all member states
   * participate in the European Single Market and are part of the Schengen Area. They are not,
   * however, party to the European Union Customs Union.
   *
   * <p>This enum was last updated on 2020-08-03 based on information given on : <a
   * href="https://www.efta.int/about-efta/the-efta-states">efta.int - The EFTA States</a>.
   *
   * @see <a href="https://www.efta.int/about-efta/the-efta-states">efta.int - The EFTA States</a>
   * @see <a href="https://wikipedia.org/wiki/European_Free_Trade_Association">Wikipedia - European
   *     Free Trade Association</a>
   * @since 2.1.0
   */
  EUROPEAN_FREE_TRADE_ASSOCIATION(IS, LI, NO, CH),

  /**
   * The European Union (EU) is a political and economic union of states that are located primarily
   * in Europe. The EU has developed an internal single market through a standardised system of laws
   * that apply in all member states in those matters, and only those matters, where members have
   * agreed to act as one.
   *
   * <p><a
   * href="https://wikipedia.org/wiki/Special_member_state_territories_and_the_European_Union">Special
   * member state territories</a> are not listed in this enum.
   *
   * <p>This enum was last updated on 2020-07-31 based on information given on <a
   * href="https://europa.eu/european-union/about-eu/countries_en">europa.eu - Countries</a>.
   *
   * @see <a href="https://europa.eu/european-union/about-eu/countries_en">europa.eu - Countries</a>
   * @see <a href="https://wikipedia.org/wiki/European_Union">Wikipedia - European Union</a>
   * @see <a
   *     href="https://wikipedia.org/wiki/Special_member_state_territories_and_the_European_Union">Wikipedia
   *     - Special member state territories and the European Union</a>
   * @since 2.1.0
   */
  EUROPEAN_UNION(
      AT, BE, BG, HR, CY, CZ, DK, EE, FI, FR, DE, GR, HU, IE, IT, LV, LT, LU, MT, NL, PL, PT, RO,
      SK, SI, ES, SE),

  /**
   * The French Republic is made up of a “SEPA zone” and a “non-SEPA” zone. In order to ensure the
   * continuity of exchanges in euros between the part of the French Republic which is in the SEPA
   * zone and the part of the French Republic which is outside the SEPA zone, namely French
   * Polynesia, New Caledonia, Wallis and Futuna, the CFONB has defined a solution called SEPA COM
   * PACIFIQUE.
   *
   * <p>This enum was last updated on 2020-07-31 based on information given in the CFONB article <a
   * href="https://www.cfonb.org/Default.aspx?lid=1&rid=122&rvid=239">L'Euro et les territoires du
   * Pacifique</a>.
   *
   * @see <a href="https://www.cfonb.org/Default.aspx?lid=1&rid=122&rvid=239">L'Euro et les
   *     territoires du Pacifique</a>
   * @see <a href="https://wikipedia.org/wiki/Single_Euro_Payments_Area">Wikipedia</a>
   * @since 2.1.0
   */
  SEPA_COM_PACIFIQUE(PF, NC, WF),

  /**
   * The Single Euro Payments Area (SEPA) is a payment-integration initiative of the European Union
   * for simplification of bank transfers denominated in euro. The aim of SEPA is to improve the
   * efficiency of cross-border payments and turn the previously fragmented national markets for
   * euro payments into a single domestic one.
   *
   * <p>This enum was last updated on 2020-07-30 based on information given in <a
   * href="https://www.europeanpaymentscouncil.eu/document-library/other/epc-list-sepa-scheme-countries">EPC409-09
   * - EPC List of SEPA Countries v2.6</a>.
   *
   * @see <a
   *     href="https://www.europeanpaymentscouncil.eu/document-library/other/epc-list-sepa-scheme-countries">EPC409-09
   *     EPC List of SEPA Countries v2.6 - January 2020</a>
   * @see <a href="https://www.iso13616.org/">IBAN registry release 87</a>
   * @see <a href="https://wikipedia.org/wiki/Single_Euro_Payments_Area">Wikipedia</a>
   * @since 2.1.0
   */
  SINGLE_EURO_PAYMENTS_AREA(
      AD, AT, BE, BG, HR, CY, CZ, DK, EE, FI, AX, // through Finland
      FR, GF, // through France
      GP, // through France
      MQ, // through France
      YT, // through France
      RE, // through France
      BL, // through France
      MF, // through France
      PM, // through France
      DE, GR, HU, IS, IE, IT, LV, LI, LT, LU, MT, MC, NL, NO, PL, PT, RO, SM, SK, SI, ES, SE, CH,
      GB, // under a transitional period until 31 December 2020
      GI, // through United Kingdom
      GG, // through United Kingdom
      JE, // through United Kingdom
      IM, // through United Kingdom
      VA);

  private final Set<IsoCountry> participants;

  Agreement(IsoCountry... participants) {
    // emptiness is checked by EnumSet.copyOf, no need to check it here
    this.participants = Collections.unmodifiableSet(EnumSet.copyOf(Arrays.asList(participants)));
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
