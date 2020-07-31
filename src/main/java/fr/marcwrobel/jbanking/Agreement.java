package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.IsoCountry.ALAND_ISLANDS;
import static fr.marcwrobel.jbanking.IsoCountry.ANDORRA;
import static fr.marcwrobel.jbanking.IsoCountry.AUSTRIA;
import static fr.marcwrobel.jbanking.IsoCountry.BELGIUM;
import static fr.marcwrobel.jbanking.IsoCountry.BULGARIA;
import static fr.marcwrobel.jbanking.IsoCountry.CROATIA;
import static fr.marcwrobel.jbanking.IsoCountry.CYPRUS;
import static fr.marcwrobel.jbanking.IsoCountry.CZECH_REPUBLIC;
import static fr.marcwrobel.jbanking.IsoCountry.DENMARK;
import static fr.marcwrobel.jbanking.IsoCountry.ESTONIA;
import static fr.marcwrobel.jbanking.IsoCountry.FINLAND;
import static fr.marcwrobel.jbanking.IsoCountry.FRANCE;
import static fr.marcwrobel.jbanking.IsoCountry.FRENCH_GUIANA;
import static fr.marcwrobel.jbanking.IsoCountry.FRENCH_POLYNESIA;
import static fr.marcwrobel.jbanking.IsoCountry.GERMANY;
import static fr.marcwrobel.jbanking.IsoCountry.GIBRALTAR;
import static fr.marcwrobel.jbanking.IsoCountry.GREECE;
import static fr.marcwrobel.jbanking.IsoCountry.GUADELOUPE;
import static fr.marcwrobel.jbanking.IsoCountry.GUERNSEY;
import static fr.marcwrobel.jbanking.IsoCountry.HUNGARY;
import static fr.marcwrobel.jbanking.IsoCountry.ICELAND;
import static fr.marcwrobel.jbanking.IsoCountry.IRELAND;
import static fr.marcwrobel.jbanking.IsoCountry.ISLE_OF_MAN;
import static fr.marcwrobel.jbanking.IsoCountry.ITALY;
import static fr.marcwrobel.jbanking.IsoCountry.JERSEY;
import static fr.marcwrobel.jbanking.IsoCountry.LATVIA;
import static fr.marcwrobel.jbanking.IsoCountry.LIECHTENSTEIN;
import static fr.marcwrobel.jbanking.IsoCountry.LITHUANIA;
import static fr.marcwrobel.jbanking.IsoCountry.LUXEMBOURG;
import static fr.marcwrobel.jbanking.IsoCountry.MALTA;
import static fr.marcwrobel.jbanking.IsoCountry.MARTINIQUE;
import static fr.marcwrobel.jbanking.IsoCountry.MAYOTTE;
import static fr.marcwrobel.jbanking.IsoCountry.MONACO;
import static fr.marcwrobel.jbanking.IsoCountry.NETHERLANDS;
import static fr.marcwrobel.jbanking.IsoCountry.NEW_CALEDONIA;
import static fr.marcwrobel.jbanking.IsoCountry.NORWAY;
import static fr.marcwrobel.jbanking.IsoCountry.POLAND;
import static fr.marcwrobel.jbanking.IsoCountry.PORTUGAL;
import static fr.marcwrobel.jbanking.IsoCountry.REUNION;
import static fr.marcwrobel.jbanking.IsoCountry.ROMANIA;
import static fr.marcwrobel.jbanking.IsoCountry.SAINT_BARTHELEMY;
import static fr.marcwrobel.jbanking.IsoCountry.SAINT_MARTIN;
import static fr.marcwrobel.jbanking.IsoCountry.SAINT_PIERRE_AND_MIQUELON;
import static fr.marcwrobel.jbanking.IsoCountry.SAN_MARINO;
import static fr.marcwrobel.jbanking.IsoCountry.SLOVAKIA;
import static fr.marcwrobel.jbanking.IsoCountry.SLOVENIA;
import static fr.marcwrobel.jbanking.IsoCountry.SPAIN;
import static fr.marcwrobel.jbanking.IsoCountry.SWEDEN;
import static fr.marcwrobel.jbanking.IsoCountry.SWITZERLAND;
import static fr.marcwrobel.jbanking.IsoCountry.UNITED_KINGDOM;
import static fr.marcwrobel.jbanking.IsoCountry.VATICAN_CITY_STATE;
import static fr.marcwrobel.jbanking.IsoCountry.WALLIS_AND_FUTUNA;

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
   * The Single Euro Payments Area (SEPA) is a payment-integration initiative of the European Union
   * for simplification of bank transfers denominated in euro. The aim of SEPA is to improve the
   * efficiency of cross-border payments and turn the previously fragmented national markets for
   * euro payments into a single domestic one.
   *
   * <p>This enum was last updated on 2020-07-30 based on information given in <a
   * href="https://www.europeanpaymentscouncil.eu/document-library/other/epc-list-sepa-scheme-countries">
   * EPC409-09 - EPC List of SEPA Countries v2.6</a>.
   *
   * @see <a
   *     href="https://www.europeanpaymentscouncil.eu/document-library/other/epc-list-sepa-scheme-countries">EPC409-09
   *     EPC List of SEPA Countries v2.6 - January 2020</a>
   * @see <a href="https://www.iso13616.org/">IBAN registry release 87</a>
   * @see <a href="https://wikipedia.org/wiki/Single_Euro_Payments_Area">Wikipedia</a>
   * @since 2.1.0
   */
  SINGLE_EURO_PAYMENTS_AREA(
      ANDORRA,
      AUSTRIA,
      BELGIUM,
      BULGARIA,
      CROATIA,
      CYPRUS,
      CZECH_REPUBLIC,
      DENMARK,
      ESTONIA,
      FINLAND,
      ALAND_ISLANDS, // with Finland
      FRANCE,
      FRENCH_GUIANA, // with France
      GUADELOUPE, // with France
      MARTINIQUE, // with France
      MAYOTTE, // with France
      REUNION, // with France
      SAINT_BARTHELEMY, // with France
      SAINT_MARTIN, // with France
      SAINT_PIERRE_AND_MIQUELON, // with France
      GERMANY,
      GREECE,
      HUNGARY,
      ICELAND,
      IRELAND,
      ITALY,
      LATVIA,
      LIECHTENSTEIN,
      LITHUANIA,
      LUXEMBOURG,
      MALTA,
      MONACO,
      NETHERLANDS,
      NORWAY,
      POLAND,
      PORTUGAL,
      ROMANIA,
      SAN_MARINO,
      SLOVAKIA,
      SLOVENIA,
      SPAIN,
      SWEDEN,
      SWITZERLAND,
      UNITED_KINGDOM, // under a transitional period until 31 December 2020
      GIBRALTAR, // with United Kingdom
      GUERNSEY, // with United Kingdom
      JERSEY, // with United Kingdom
      ISLE_OF_MAN, // with United Kingdom
      VATICAN_CITY_STATE),

  /**
   * The French Republic is made up of a "SEPA zone" and a "non-SEPA" zone. In order to ensure the
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
  SEPA_COM_PACIFIQUE(FRENCH_POLYNESIA, NEW_CALEDONIA, WALLIS_AND_FUTUNA);

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
