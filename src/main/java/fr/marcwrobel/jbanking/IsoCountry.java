package fr.marcwrobel.jbanking;

import static java.util.Objects.requireNonNull;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

/**
 * The countries, dependent territories, and special areas of geographical interest having an <a
 * href="http://wikipedia.org/wiki/ISO_3166-1_alpha-2#Officially_assigned_code_elements">Officially
 * assigned</a> ISO 3166-1 code.
 *
 * <p>One exception has been made for Kosovo. Kosovo has a user-assigned code, XK, that is being
 * used temporarily by the European Commission, the IMF, and SWIFT.
 *
 * <p>Note that enum entries are named after the ISO 3166-1 alpha-2 code. This choice have been made
 * in version 3.0.0 of jbanking in order to :
 *
 * <ul>
 *   <li>reduce breaking changes in future versions (names change more often than codes),
 *   <li>make this enum easier to serialize (to JSON, in database...),
 *   <li>prevent accidental duplicates.
 * </ul>
 *
 * <p>Please be advised that this list is current as of 2020-08-03. An up-to-date list can be found
 * on the <a href="http://www.iso.org/iso/home/standards/country_codes.htm">International
 * Organization for Standardization</a> website.
 *
 * @author Marc Wrobel
 * @see <a href="http://www.iso.org/iso/home/standards/country_codes.htm">ISO 3166 Country Codes</a>
 * @since 1.0
 */
public enum IsoCountry {
  /**
   * Afghanistan (a.k.a. the Islamic Republic of Afghanistan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AF">www.iso.org</a>
   */
  AF("AFG", 4),

  /**
   * Albania (a.k.a. the Republic of Albania).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AL">www.iso.org</a>
   */
  AL("ALB", 8),

  /**
   * Algeria (a.k.a. the People's Democratic Republic of Algeria).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DZ">www.iso.org</a>
   */
  DZ("DZA", 12),

  /**
   * Andorra (a.k.a. the Principality of Andorra).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AD">www.iso.org</a>
   */
  AD("AND", 20),

  /**
   * Angola (a.k.a the Republic of Angola).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AO">www.iso.org</a>
   */
  AO("AGO", 24),

  /**
   * Antarctica.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AQ">www.iso.org</a>
   */
  AQ("ATA", 10, null),

  /**
   * Antigua and Barbuda.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AG">www.iso.org</a>
   */
  AG("ATG", 28),

  /**
   * Argentina (a.k.a the Argentine Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AR">www.iso.org</a>
   */
  AR("ARG", 32),

  /**
   * Armenia (a.k.a the Republic of Armenia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AM">www.iso.org</a>
   */
  AM("ARM", 51),

  /**
   * Australia.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AU">www.iso.org</a>
   */
  AU("AUS", 36),

  /**
   * Christmas Island.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CX">www.iso.org</a>
   */
  CX("CXR", 162, AU),

  /**
   * The Cocos (Keeling) Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CC">www.iso.org</a>
   */
  CC("CCK", 166, AU),

  /**
   * Heard Island and McDonald Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HM">www.iso.org</a>
   */
  HM("HMD", 334, AU),

  /**
   * Norfolk Island.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NF">www.iso.org</a>
   */
  NF("NFK", 574, AU),

  /**
   * Austria (a.k.a the Republic of Austria).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AT">www.iso.org</a>
   */
  AT("AUT", 40),

  /**
   * Azerbaijan (a.k.a the Republic of Azerbaijan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AZ">www.iso.org</a>
   */
  AZ("AZE", 31),

  /**
   * The Bahamas (a.k.a the Commonwealth of the Bahamas).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BS">www.iso.org</a>
   */
  BS("BHS", 44),

  /**
   * Bahrain (a.k.a the Kingdom of Bahrain).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BH">www.iso.org</a>
   */
  BH("BHR", 48),

  /**
   * Bangladesh (a.k.a the People's Republic of Bangladesh).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BD">www.iso.org</a>
   */
  BD("BGD", 50),

  /**
   * Barbados.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BB">www.iso.org</a>
   */
  BB("BRB", 52),

  /**
   * Belarus (a.k.a the Republic of Belarus).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BY">www.iso.org</a>
   */
  BY("BLR", 112),

  /**
   * Belgium (a.k.a the Kingdom of Belgium).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BE">www.iso.org</a>
   */
  BE("BEL", 56),

  /**
   * Belize.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BZ">www.iso.org</a>
   */
  BZ("BLZ", 84),

  /**
   * Benin (a.k.a the Republic of Benin).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BJ">www.iso.org</a>
   */
  BJ("BEN", 204),

  /**
   * Bhutan (a.k.a the Kingdom of Bhutan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BT">www.iso.org</a>
   */
  BT("BTN", 64),

  /**
   * Bolivia (a.k.a the Plurinational State of Bolivia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BO">www.iso.org</a>
   */
  BO("BOL", 68),

  /**
   * Bosnia and Herzegovina.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BA">www.iso.org</a>
   */
  BA("BIH", 70),

  /**
   * Botswana (a.k.a the Republic of Botswana).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BW">www.iso.org</a>
   */
  BW("BWA", 72),

  /**
   * Brazil (a.k.a the Federative Republic of Brazil).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BR">www.iso.org</a>
   */
  BR("BRA", 76),

  /**
   * Brunei Darussalam.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BN">www.iso.org</a>
   */
  BN("BRN", 96),

  /**
   * Bulgaria (a.k.a the Republic of Bulgaria).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BG">www.iso.org</a>
   */
  BG("BGR", 100),

  /**
   * Burkina Faso.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BF">www.iso.org</a>
   */
  BF("BFA", 854),

  /**
   * Burundi (a.k.a the Republic of Burundi).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BI">www.iso.org</a>
   */
  BI("BDI", 108),

  /**
   * Cambodia (a.k.a the Kingdom of Cambodia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KH">www.iso.org</a>
   */
  KH("KHM", 116),

  /**
   * Cameroon (a.k.a the Republic of Cameroon).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CM">www.iso.org</a>
   */
  CM("CMR", 120),

  /**
   * Canada.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CA">www.iso.org</a>
   */
  CA("CAN", 124),

  /**
   * Cabo Verde (a.k.a the Republic of Cabo Verde).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CV">www.iso.org</a>
   */
  CV("CPV", 132),

  /**
   * The Central African Republic.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CF">www.iso.org</a>
   */
  CF("CAF", 140),

  /**
   * Chad (a.k.a the Republic of Chad).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TD">www.iso.org</a>
   */
  TD("TCD", 148),

  /**
   * Chile (a.k.a the Republic of Chile).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CL">www.iso.org</a>
   */
  CL("CHL", 152),

  /**
   * China (a.k.a the People's Republic of China).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CN">www.iso.org</a>
   */
  CN("CHN", 156),

  /**
   * Hong Kong (a.k.a the Hong Kong Special Administrative Region of China).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HK">www.iso.org</a>
   */
  HK("HKG", 344, CN),

  /**
   * Macao (a.k.a Macao Special Administrative Region of China).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MO">www.iso.org</a>
   */
  MO("MAC", 446, CN),

  /**
   * Colombia (a.k.a the Republic of Colombia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CO">www.iso.org</a>
   */
  CO("COL", 170),

  /**
   * The Comoros (a.k.a the Union of the Comoros).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KM">www.iso.org</a>
   */
  KM("COM", 174),

  /**
   * The Congo (a.k.a the Republic of the Congo).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CG">www.iso.org</a>
   */
  CG("COG", 178),

  /**
   * Costa Rica (a.k.a the Republic of Costa Rica).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CR">www.iso.org</a>
   */
  CR("CRI", 188),

  /**
   * Côte d'Ivoire (a.k.a the Republic of Côte d'Ivoire).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CI">www.iso.org</a>
   */
  CI("CIV", 384),

  /**
   * Croatia (a.k.a the Republic of Croatia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HR">www.iso.org</a>
   */
  HR("HRV", 191),

  /**
   * Cuba (a.k.a the Republic of Cuba).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CU">www.iso.org</a>
   */
  CU("CUB", 192),

  /**
   * Cyprus (a.k.a the Republic of Cyprus).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CY">www.iso.org</a>
   */
  CY("CYP", 196),

  /**
   * Czechia (a.k.a the Czech Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CZ">www.iso.org</a>
   */
  CZ("CZE", 203),

  /**
   * Denmark (a.k.a the Kingdom of Denmark).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DK">www.iso.org</a>
   */
  DK("DNK", 208),

  /**
   * The Faroe Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FO">www.iso.org</a>
   */
  FO("FRO", 234, DK),

  /**
   * Greenland.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GL">www.iso.org</a>
   */
  GL("GRL", 304, DK),

  /**
   * Djibouti (a.k.a the Republic of Djibouti).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DJ">www.iso.org</a>
   */
  DJ("DJI", 262),

  /**
   * Dominica (a.k.a the Commonwealth of Dominica).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DM">www.iso.org</a>
   */
  DM("DMA", 212),

  /**
   * The Dominican Republic.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DO">www.iso.org</a>
   */
  DO("DOM", 214),

  /**
   * Ecuador (a.k.a the Republic of Ecuador).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:EC">www.iso.org</a>
   */
  EC("ECU", 218),

  /**
   * Egypt (a.k.a the Arab Republic of Egypt).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:EG">www.iso.org</a>
   */
  EG("EGY", 818),

  /**
   * El Salvador (a.k.a the Republic of El Salvador).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SV">www.iso.org</a>
   */
  SV("SLV", 222),

  /**
   * Equatorial Guinea (a.k.a the Republic of Equatorial Guinea).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GQ">www.iso.org</a>
   */
  GQ("GNQ", 226),

  /**
   * Eritrea (a.k.a the State of Eritrea).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ER">www.iso.org</a>
   */
  ER("ERI", 232),

  /**
   * Estonia (a.k.a the Republic of Estonia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:EE">www.iso.org</a>
   */
  EE("EST", 233),

  /**
   * Ethiopia (a.k.a the Federal Democratic Republic of Ethiopia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ET">www.iso.org</a>
   */
  ET("ETH", 231),

  /**
   * Fiji (a.k.a the Republic of Fiji).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FJ">www.iso.org</a>
   */
  FJ("FJI", 242),

  /**
   * Finland (a.k.a the Republic of Finland).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FI">www.iso.org</a>
   */
  FI("FIN", 246),

  /**
   * Åland Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AX">www.iso.org</a>
   */
  AX("ALA", 248, FI),

  /**
   * France (a.k.a the French Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FR">www.iso.org</a>
   */
  FR("FRA", 250),

  /**
   * French Guiana.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GF">www.iso.org</a>
   */
  GF("GUF", 254, FR),

  /**
   * French Polynesia.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PF">www.iso.org</a>
   */
  PF("PYF", 258, FR),

  /**
   * The French Southern Territories.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TF">www.iso.org</a>
   */
  TF("ATF", 260, FR),

  /**
   * Guadeloupe.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GP">www.iso.org</a>
   */
  GP("GLP", 312, FR),

  /**
   * Martinique.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MQ">www.iso.org</a>
   */
  MQ("MTQ", 474, FR),

  /**
   * Mayotte.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:YT">www.iso.org</a>
   */
  YT("MYT", 175, FR),

  /**
   * New Caledonia.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NC">www.iso.org</a>
   */
  NC("NCL", 540, FR),

  /**
   * Réunion.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RE">www.iso.org</a>
   */
  RE("REU", 638, FR),

  /**
   * Saint Barthélemy.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BL">www.iso.org</a>
   */
  BL("BLM", 652, FR),

  /**
   * Saint Martin (French part).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MF">www.iso.org</a>
   */
  MF("MAF", 663, FR),

  /**
   * Saint Pierre and Miquelon.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PM">www.iso.org</a>
   */
  PM("SPM", 666, FR),

  /**
   * Wallis and Futuna (a.k.a Wallis and Futuna Islands).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:WF">www.iso.org</a>
   */
  WF("WLF", 876, FR),

  /**
   * Gabon (a.k.a the Gabonese Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GA">www.iso.org</a>
   */
  GA("GAB", 266),

  /**
   * The Gambia (a.k.a the Republic of the Gambia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GM">www.iso.org</a>
   */
  GM("GMB", 270),

  /**
   * Georgia.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GE">www.iso.org</a>
   */
  GE("GEO", 268),

  /**
   * Germany (a.k.a the Federal Republic of Germany).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DE">www.iso.org</a>
   */
  DE("DEU", 276),

  /**
   * Ghana (a.k.a the Republic of Ghana).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GH">www.iso.org</a>
   */
  GH("GHA", 288),

  /**
   * Greece (a.k.a the Hellenic Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GR">www.iso.org</a>
   */
  GR("GRC", 300),

  /**
   * Grenada.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GD">www.iso.org</a>
   */
  GD("GRD", 308),

  /**
   * Guatemala (a.k.a the Republic of Guatemala).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GT">www.iso.org</a>
   */
  GT("GTM", 320),

  /**
   * Guinea (a.k.a the Republic of Guinea).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GN">www.iso.org</a>
   */
  GN("GIN", 324),

  /**
   * Guinea-Bissau (a.k.a the Republic of Guinea-Bissau).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GW">www.iso.org</a>
   */
  GW("GNB", 624),

  /**
   * Guyana (a.k.a the Co-operative Republic of Guyana).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GY">www.iso.org</a>
   */
  GY("GUY", 328),

  /**
   * Haiti (a.k.a the Republic of Haiti).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HT">www.iso.org</a>
   */
  HT("HTI", 332),

  /**
   * Honduras (a.k.a the Republic of Honduras).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HN">www.iso.org</a>
   */
  HN("HND", 340),

  /**
   * Hungary.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HU">www.iso.org</a>
   */
  HU("HUN", 348),

  /**
   * Iceland (a.k.a the Republic of Iceland).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IS">www.iso.org</a>
   */
  IS("ISL", 352),

  /**
   * India (a.k.a the Republic of India).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IN">www.iso.org</a>
   */
  IN("IND", 356),

  /**
   * Indonesia (a.k.a the Republic of Indonesia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ID">www.iso.org</a>
   */
  ID("IDN", 360),

  /**
   * The Islamic Republic of Iran.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IR">www.iso.org</a>
   */
  IR("IRN", 364),

  /**
   * Iraq (a.k.a the Republic of Iraq).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IQ">www.iso.org</a>
   */
  IQ("IRQ", 368),

  /**
   * Ireland.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IE">www.iso.org</a>
   */
  IE("IRL", 372),

  /**
   * Israel (a.k.a the State of Israel).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IL">www.iso.org</a>
   */
  IL("ISR", 376),

  /**
   * Italy (a.k.a the Republic of Italy).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IT">www.iso.org</a>
   */
  IT("ITA", 380),

  /**
   * Jamaica.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:JM">www.iso.org</a>
   */
  JM("JAM", 388),

  /**
   * Japan.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:JP">www.iso.org</a>
   */
  JP("JPN", 392),

  /**
   * Jordan (a.k.a the Hashemite Kingdom of Jordan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:JO">www.iso.org</a>
   */
  JO("JOR", 400),

  /**
   * Kazakhstan (a.k.a the Republic of Kazakhstan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KZ">www.iso.org</a>
   */
  KZ("KAZ", 398),

  /**
   * Kenya (a.k.a the Republic of Kenya).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KE">www.iso.org</a>
   */
  KE("KEN", 404),

  /**
   * Kiribati (a.k.a the Republic of Kiribati).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KI">www.iso.org</a>
   */
  KI("KIR", 296),

  /**
   * Kosovo.
   *
   * <p>Temporarily used by the European Commission, the IMF, and SWIFT.
   *
   * @see <a href="https://fr.wikipedia.org/wiki/Kosovo">Wikipedia</a>
   */
  XK("XKX", null),

  /**
   * Kuwait (a.k.a the State of Kuwait).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KW">www.iso.org</a>
   */
  KW("KWT", 414),

  /**
   * Kyrgyzstan (a.k.a the Kyrgyz Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KG">www.iso.org</a>
   */
  KG("KGZ", 417),

  /**
   * The Lao People's Democratic Republic.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LA">www.iso.org</a>
   */
  LA("LAO", 418),

  /**
   * Latvia (a.k.a the Republic of Latvia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LV">www.iso.org</a>
   */
  LV("LVA", 428),

  /**
   * Lebanon (a.k.a the Lebanese Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LB">www.iso.org</a>
   */
  LB("LBN", 422),

  /**
   * Lesotho (a.k.a the Kingdom of Lesotho).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LS">www.iso.org</a>
   */
  LS("LSO", 426),

  /**
   * Liberia (a.k.a the Republic of Liberia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LR">www.iso.org</a>
   */
  LR("LBR", 430),

  /**
   * Libya (a.k.a the State of Libya).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LY">www.iso.org</a>
   */
  LY("LBY", 434),

  /**
   * Liechtenstein (a.k.a the Principality of Liechtenstein).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LI">www.iso.org</a>
   */
  LI("LIE", 438),

  /**
   * Lithuania (a.k.a the Republic of Lithuania).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LT">www.iso.org</a>
   */
  LT("LTU", 440),

  /**
   * Luxembourg (a.k.a the Grand Duchy of Luxembourg).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LU">www.iso.org</a>
   */
  LU("LUX", 442),

  /**
   * North Macedonia (a.k.a the Republic of North Macedonia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MK">www.iso.org</a>
   */
  MK("MKD", 807),

  /**
   * Madagascar (a.k.a the Republic of Madagascar).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MG">www.iso.org</a>
   */
  MG("MDG", 450),

  /**
   * Malawi (a.k.a the Republic of Malawi).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MW">www.iso.org</a>
   */
  MW("MWI", 454),

  /**
   * Malaysia.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MY">www.iso.org</a>
   */
  MY("MYS", 458),

  /**
   * Maldives (a.k.a the Republic of Maldives).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MV">www.iso.org</a>
   */
  MV("MDV", 462),

  /**
   * Mali (a.k.a the Republic of Mali).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ML">www.iso.org</a>
   */
  ML("MLI", 466),

  /**
   * Malta (a.k.a the Republic of Malta).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MT">www.iso.org</a>
   */
  MT("MLT", 470),

  /**
   * The Marshall Islands (a.k.a the Republic of the Marshall Islands).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MH">www.iso.org</a>
   */
  MH("MHL", 584),

  /**
   * Mauritania (a.k.a the Islamic Republic of Mauritania).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MR">www.iso.org</a>
   */
  MR("MRT", 478),

  /**
   * Mauritius (a.k.a the Republic of Mauritius).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MU">www.iso.org</a>
   */
  MU("MUS", 480),

  /**
   * Mexico (a.k.a the United Mexican States).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MX">www.iso.org</a>
   */
  MX("MEX", 484),

  /**
   * The Federated States of Micronesia.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FM">www.iso.org</a>
   */
  FM("FSM", 583),

  /**
   * The Republic of Moldova.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MD">www.iso.org</a>
   */
  MD("MDA", 498),

  /**
   * Monaco (a.k.a the Principality of Monaco).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MC">www.iso.org</a>
   */
  MC("MCO", 492),

  /**
   * Mongolia.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MN">www.iso.org</a>
   */
  MN("MNG", 496),

  /**
   * Montenegro.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ME">www.iso.org</a>
   */
  ME("MNE", 499),

  /**
   * Morocco (a.k.a the Kingdom of Morocco).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MA">www.iso.org</a>
   */
  MA("MAR", 504),

  /**
   * Mozambique (a.k.a the Republic of Mozambique).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MZ">www.iso.org</a>
   */
  MZ("MOZ", 508),

  /**
   * Myanmar (a.k.a the Republic of the Union of Myanmar).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MM">www.iso.org</a>
   */
  MM("MMR", 104),

  /**
   * Namibia (a.k.a the Republic of Namibia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NA">www.iso.org</a>
   */
  NA("NAM", 516),

  /**
   * Nauru (a.k.a the Republic of Nauru).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NR">www.iso.org</a>
   */
  NR("NRU", 520),

  /**
   * Nepal (a.k.a the Federal Democratic Republic of Nepal).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NP">www.iso.org</a>
   */
  NP("NPL", 524),

  /**
   * The Netherlands (a.k.a the Kingdom of the Netherlands).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NL">www.iso.org</a>
   */
  NL("NLD", 528),

  /**
   * Aruba.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AW">www.iso.org</a>
   */
  AW("ABW", 533, NL),

  /**
   * Bonaire, Sint Eustatius and Saba.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BQ">www.iso.org</a>
   */
  BQ("BES", 535, NL),

  /**
   * Curaçao.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CW">www.iso.org</a>
   */
  CW("CUW", 531, NL),

  /**
   * Sint Maarten (Dutch part).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SX">www.iso.org</a>
   */
  SX("SXM", 534, NL),

  /**
   * New Zealand.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NZ">www.iso.org</a>
   */
  NZ("NZL", 554),

  /**
   * The Cook Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CK">www.iso.org</a>
   */
  CK("COK", 184, NZ),

  /**
   * Niue.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NU">www.iso.org</a>
   */
  NU("NIU", 570, NZ),

  /**
   * Tokelau.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TK">www.iso.org</a>
   */
  TK("TKL", 772, NZ),

  /**
   * Nicaragua (a.k.a the Republic of Nicaragua).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NI">www.iso.org</a>
   */
  NI("NIC", 558),

  /**
   * The Niger (a.k.a the Republic of the Niger).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NE">www.iso.org</a>
   */
  NE("NER", 562),

  /**
   * Nigeria (a.k.a the Federal Republic of Nigeria).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NG">www.iso.org</a>
   */
  NG("NGA", 566),

  /**
   * The Democratic People's Republic of Korea (a.k.a North Korea).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KP">www.iso.org</a>
   */
  KP("PRK", 408),

  /**
   * Norway (a.k.a the Kingdom of Norway).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NO">www.iso.org</a>
   */
  NO("NOR", 578),

  /**
   * Bouvet Island.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BV">www.iso.org</a>
   */
  BV("BVT", 74, NO),

  /**
   * Svalbard and Jan Mayen.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SJ">www.iso.org</a>
   */
  SJ("SJM", 744, NO),

  /**
   * Oman (a.k.a the Sultanate of Oman).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:OM">www.iso.org</a>
   */
  OM("OMN", 512),

  /**
   * Pakistan (a.k.a the Islamic Republic of Pakistan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PK">www.iso.org</a>
   */
  PK("PAK", 586),

  /**
   * Palau (a.k.a the Republic of Palau).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PW">www.iso.org</a>
   */
  PW("PLW", 585),

  /**
   * The State of Palestine.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PS">www.iso.org</a>
   */
  PS("PSE", 275, null),

  /**
   * Panama (a.k.a the Republic of Panama).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PA">www.iso.org</a>
   */
  PA("PAN", 591),

  /**
   * Papua New Guinea (a.k.a the Independent State of Papua New Guinea).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PG">www.iso.org</a>
   */
  PG("PNG", 598),

  /**
   * Paraguay (a.k.a the Republic of Paraguay).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PY">www.iso.org</a>
   */
  PY("PRY", 600),

  /**
   * Peru (a.k.a the Republic of Peru).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PE">www.iso.org</a>
   */
  PE("PER", 604),

  /**
   * The Philippines (a.k.a the Republic of the Philippines).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PH">www.iso.org</a>
   */
  PH("PHL", 608),

  /**
   * Poland (a.k.a the Republic of Poland).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PL">www.iso.org</a>
   */
  PL("POL", 616),

  /**
   * Portugal (a.k.a the Portuguese Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PT">www.iso.org</a>
   */
  PT("PRT", 620),

  /**
   * Qatar (a.k.a the State of Qatar).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:QA">www.iso.org</a>
   */
  QA("QAT", 634),

  /**
   * Romania.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RO">www.iso.org</a>
   */
  RO("ROU", 642),

  /**
   * The Russian Federation (a.k.a Russia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RU">www.iso.org</a>
   */
  RU("RUS", 643),

  /**
   * Rwanda (a.k.a the Republic of Rwanda).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RW">www.iso.org</a>
   */
  RW("RWA", 646),

  /**
   * Saint Kitts and Nevis.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KN">www.iso.org</a>
   */
  KN("KNA", 659),

  /**
   * Saint Lucia.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LC">www.iso.org</a>
   */
  LC("LCA", 662),

  /**
   * Saint Vincent and the Grenadines.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VC">www.iso.org</a>
   */
  VC("VCT", 670),

  /**
   * Samoa (a.k.a the Independent State of Samoa).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:WS">www.iso.org</a>
   */
  WS("WSM", 882),

  /**
   * San Marino (a.k.a the Republic of San Marino).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SM">www.iso.org</a>
   */
  SM("SMR", 674),

  /**
   * Sao Tome and Principe (a.k.a the Democratic Republic of Sao Tome and Principe).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ST">www.iso.org</a>
   */
  ST("STP", 678),

  /**
   * Saudi Arabia (a.k.a the Kingdom of Saudi Arabia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SA">www.iso.org</a>
   */
  SA("SAU", 682),

  /**
   * Senegal (a.k.a the Republic of Senegal).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SN">www.iso.org</a>
   */
  SN("SEN", 686),

  /**
   * Serbia (a.k.a the Republic of Serbia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RS">www.iso.org</a>
   */
  RS("SRB", 688),

  /**
   * Seychelles (a.k.a the Republic of Seychelles).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SC">www.iso.org</a>
   */
  SC("SYC", 690),

  /**
   * Sierra Leone (a.k.a the Republic of Sierra Leone).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SL">www.iso.org</a>
   */
  SL("SLE", 694),

  /**
   * Singapore (a.k.a the Republic of Singapore).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SG">www.iso.org</a>
   */
  SG("SGP", 702),

  /**
   * Slovakia (a.k.a the Slovak Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SK">www.iso.org</a>
   */
  SK("SVK", 703),

  /**
   * Slovenia (a.k.a the Republic of Slovenia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SI">www.iso.org</a>
   */
  SI("SVN", 705),

  /**
   * Solomon Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SB">www.iso.org</a>
   */
  SB("SLB", 90),

  /**
   * Somalia (a.k.a the Federal Republic of Somalia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SO">www.iso.org</a>
   */
  SO("SOM", 706),

  /**
   * South Africa (a.k.a the Republic of South Africa).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ZA">www.iso.org</a>
   */
  ZA("ZAF", 710),

  /**
   * The Republic of Korea (a.k.a South Korea).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KR">www.iso.org</a>
   */
  KR("KOR", 410),

  /**
   * South Sudan (a.k.a the Republic of South Sudan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SS">www.iso.org</a>
   */
  SS("SSD", 728),

  /**
   * Spain (a.k.a the Kingdom of Spain).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ES">www.iso.org</a>
   */
  ES("ESP", 724),

  /**
   * Sri Lanka (a.k.a the Democratic Socialist Republic of Sri Lanka).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LK">www.iso.org</a>
   */
  LK("LKA", 144),

  /**
   * The Sudan (a.k.a the Republic of the Sudan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SD">www.iso.org</a>
   */
  SD("SDN", 729),

  /**
   * Suriname (a.k.a the Republic of Suriname).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SR">www.iso.org</a>
   */
  SR("SUR", 740),

  /**
   * Eswatini (a.k.a the Kingdom of Eswatini).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SZ">www.iso.org</a>
   */
  SZ("SWZ", 748),

  /**
   * Sweden (a.k.a the Kingdom of Sweden).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SE">www.iso.org</a>
   */
  SE("SWE", 752),

  /**
   * Switzerland (a.k.a the Swiss Confederation).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CH">www.iso.org</a>
   */
  CH("CHE", 756),

  /**
   * The Syrian Arab Republic.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SY">www.iso.org</a>
   */
  SY("SYR", 760),

  /**
   * Taiwan (Province of China).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TW">www.iso.org</a>
   */
  TW("TWN", 158, null),

  /**
   * Tajikistan (a.k.a the Republic of Tajikistan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TJ">www.iso.org</a>
   */
  TJ("TJK", 762),

  /**
   * The United Republic of Tanzania.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TZ">www.iso.org</a>
   */
  TZ("TZA", 834),

  /**
   * Thailand (a.k.a the Kingdom of Thailand).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TH">www.iso.org</a>
   */
  TH("THA", 764),

  /**
   * The Democratic Republic of the Congo.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CD">www.iso.org</a>
   */
  CD("COD", 180),

  /**
   * Timor-Leste (a.k.a the Democratic Republic of Timor-Leste).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TL">www.iso.org</a>
   */
  TL("TLS", 626),

  /**
   * Togo (a.k.a the Togolese Republic).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TG">www.iso.org</a>
   */
  TG("TGO", 768),

  /**
   * Tonga (a.k.a the Kingdom of Tonga).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TO">www.iso.org</a>
   */
  TO("TON", 776),

  /**
   * Trinidad and Tobago (a.k.a the Republic of Trinidad and Tobago).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TT">www.iso.org</a>
   */
  TT("TTO", 780),

  /**
   * Tunisia (a.k.a the Republic of Tunisia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TN">www.iso.org</a>
   */
  TN("TUN", 788),

  /**
   * Turkey (a.k.a the Republic of Turkey).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TR">www.iso.org</a>
   */
  TR("TUR", 792),

  /**
   * Turkmenistan.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TM">www.iso.org</a>
   */
  TM("TKM", 795),

  /**
   * Tuvalu.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TV">www.iso.org</a>
   */
  TV("TUV", 798),

  /**
   * Uganda (a.k.a the Republic of Uganda).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UG">www.iso.org</a>
   */
  UG("UGA", 800),

  /**
   * Ukraine.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UA">www.iso.org</a>
   */
  UA("UKR", 804),

  /**
   * The United Arab Emirates.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AE">www.iso.org</a>
   */
  AE("ARE", 784),

  /**
   * The United Kingdom of Great Britain and Northern Ireland (a.k.a the United Kingdom or Britain).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GB">www.iso.org</a>
   */
  GB("GBR", 826),

  /**
   * Anguilla.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AI">www.iso.org</a>
   */
  AI("AIA", 660, GB),

  /**
   * Bermuda.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BM">www.iso.org</a>
   */
  BM("BMU", 60, GB),

  /**
   * The British Indian Ocean Territory.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IO">www.iso.org</a>
   */
  IO("IOT", 86, GB),

  /**
   * The British Virgin Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VG">www.iso.org</a>
   */
  VG("VGB", 92, GB),

  /**
   * The Cayman Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KY">www.iso.org</a>
   */
  KY("CYM", 136, GB),

  /**
   * The Falkland Island (Malvinas).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FK">www.iso.org</a>
   */
  FK("FLK", 238, GB),

  /**
   * Gibraltar.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GI">www.iso.org</a>
   */
  GI("GIB", 292, GB),

  /**
   * Guernsey.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GG">www.iso.org</a>
   */
  GG("GGY", 831, GB),

  /**
   * Isle of Man.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IM">www.iso.org</a>
   */
  IM("IMN", 833, GB),

  /**
   * Jersey.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:JE">www.iso.org</a>
   */
  JE("JEY", 832, GB),

  /**
   * Montserrat.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MS">www.iso.org</a>
   */
  MS("MSR", 500, GB),

  /**
   * Pitcairn.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PN">www.iso.org</a>
   */
  PN("PCN", 612, GB),

  /**
   * Saint Helena, Ascension and Tristan da Cunha.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SH">www.iso.org</a>
   */
  SH("SHN", 654, GB),

  /**
   * South Georgia and the South Sandwich Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GS">www.iso.org</a>
   */
  GS("SGS", 239, GB),

  /**
   * The Turks and Caicos Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TC">www.iso.org</a>
   */
  TC("TCA", 796, GB),

  /**
   * The United States of America.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:US">www.iso.org</a>
   */
  US("USA", 840),

  /**
   * American Samoa.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AS">www.iso.org</a>
   */
  AS("ASM", 16, US),

  /**
   * Guam.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GU">www.iso.org</a>
   */
  GU("GUM", 316, US),

  /**
   * The Northern Mariana Islands (a.k.a the Commonwealth of the Northern Mariana Islands).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MP">www.iso.org</a>
   */
  MP("MNP", 580, US),

  /**
   * Puerto Rico.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PR">www.iso.org</a>
   */
  PR("PRI", 630, US),

  /**
   * The United States Minor Outlying Islands.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UM">www.iso.org</a>
   */
  UM("UMI", 581, US),

  /**
   * The U.S. Virgin Islands (a.k.a the Virgin Islands of the United States).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VI">www.iso.org</a>
   */
  VI("VIR", 850, US),

  /**
   * Uruguay (a.k.a the Eastern Republic of Uruguay).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UY">www.iso.org</a>
   */
  UY("URY", 858),

  /**
   * Uzbekistan (a.k.a the Republic of Uzbekistan).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UZ">www.iso.org</a>
   */
  UZ("UZB", 860),

  /**
   * Vanuatu (a.k.a the Republic of Vanuatu).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VU">www.iso.org</a>
   */
  VU("VUT", 548),

  /**
   * The Holy See (a.k.a the Vatican City State).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VA">www.iso.org</a>
   */
  VA("VAT", 336),

  /**
   * The Bolivarian Republic of Venezuela.
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VE">www.iso.org</a>
   */
  VE("VEN", 862),

  /**
   * Viet Nam (a.k.a the Socialist Republic of Viet Nam).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VN">www.iso.org</a>
   */
  VN("VNM", 704),

  /**
   * Western Sahara (provisional name).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:EH">www.iso.org</a>
   */
  EH("ESH", 732, null),

  /**
   * Yemen (a.k.a the Republic of Yemen).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:YE">www.iso.org</a>
   */
  YE("YEM", 887),

  /**
   * Zambia (a.k.a the Republic of Zambia).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ZM">www.iso.org</a>
   */
  ZM("ZMB", 894),

  /**
   * Zimbabwe (a.k.a the Republic of Zimbabwe).
   *
   * @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ZW">www.iso.org</a>
   */
  ZW("ZWE", 716);

  private final String alpha3Code;
  private final Integer numericCode;

  private final boolean independent;
  private final IsoCountry dependsOn;

  /** General purpose constructor. */
  IsoCountry(String alpha3Code, Integer numericCode, boolean independent, IsoCountry dependsOn) {
    this.alpha3Code = requireNonNull(alpha3Code);
    this.numericCode = numericCode;

    this.independent = independent;
    this.dependsOn = dependsOn;
  }

  /** Dependents countries or territories constructor. */
  IsoCountry(String alpha3Code, Integer numericCode, IsoCountry dependsOn) {
    this(alpha3Code, numericCode, false, dependsOn);
  }

  /** Independents countries constructor. */
  IsoCountry(String alpha3Code, Integer numericCode) {
    this(alpha3Code, numericCode, true, null);
  }

  /**
   * Returns this country ISO 3166-1 alpha-2 code.
   *
   * @return a non null and 2 characters length string.
   * @since 2.1.0
   */
  public String getAlpha2Code() {
    return name();
  }

  /**
   * Returns this country ISO 3166-1 alpha-3 code.
   *
   * @return a non null and 3 characters length string.
   * @since 2.1.0
   */
  public String getAlpha3Code() {
    return alpha3Code;
  }

  /**
   * Returns this country ISO 3166-1 numeric code.
   *
   * @return a positive integer, or {@link Optional#empty()} if there is no code for the country
   *     (user-assigned).
   * @since 2.1.0
   */
  public Optional<Integer> getNumericCode() {
    return Optional.ofNullable(numericCode);
  }

  /**
   * Whether or not this country is independent, according to the <a href="https://www.iso.org">
   * International Organization for Standardization (ISO)</a>.
   *
   * @return {@code true} if this country is independent, {@code false} otherwise.
   * @since 2.1.0
   */
  public boolean isIndependent() {
    return independent;
  }

  /**
   * Returns the country on which this country depends.
   *
   * <p>Note that the dependency link between two countries :
   *
   * <ul>
   *   <li>is based on information provided by the <a href="https://www.iso.org">International
   *       Organization for Standardization (ISO)</a>,
   *   <li>may be unspecified, mainly for political reasons ({@link #AQ}, {@link #PS}, {@link #TW},
   *       {@link #EH}),
   *   <li>can take <a href="https://en.wikipedia.org/wiki/List_of_sovereign_states">many forms</a>
   *       : free association, territory, special administrative region... (this is outside the
   *       scope of jbanking)
   * </ul>
   *
   * @return an {@link IsoCountry} encapsulated in an {@link Optional}, or {@link Optional#empty()}
   * @since 2.1.0
   */
  public Optional<IsoCountry> getDependency() {
    return Optional.ofNullable(dependsOn);
  }

  /**
   * Check whether or not this country is participating to the given {@link Agreement}.
   *
   * @param agreement a non-null {@link Agreement}
   * @return {@code true} if this country is participating to the given {@link Agreement}, {@code
   *     false} otherwise
   * @since 2.1.0
   * @throws IllegalArgumentException if agreement is null
   */
  public boolean isParticipatingTo(Agreement agreement) {
    if (agreement == null) {
      throw new IllegalArgumentException("the agreement argument cannot be null");
    }

    return agreement.getParticipants().contains(this);
  }

  public Set<Agreement> participations() {
    Set<Agreement> agreements = EnumSet.noneOf(Agreement.class);

    for (Agreement agreement : Agreement.values()) {
      if (this.isParticipatingTo(agreement)) {
        agreements.add(agreement);
      }
    }

    return agreements;
  }

  /**
   * Translate the given ISO 3166-1 alpha-2 code to an IsoCountry.
   *
   * <p>This method is not case-sensitive.
   *
   * @param code A string ({@code null} accepted).
   * @return the country having the given ISO 3166-1 alpha-2 code, or {@code Optional#empty}.
   */
  public static Optional<IsoCountry> fromAlpha2Code(String code) {
    if (code != null && code.length() == 2) {
      String upperCasedCode = code.toUpperCase();

      for (IsoCountry country : values()) {
        if (country.getAlpha2Code().equals(upperCasedCode)) {
          return Optional.of(country);
        }
      }
    }

    return Optional.empty();
  }

  /**
   * Translate the given ISO 3166-1 alpha-3 code to an IsoCountry.
   *
   * <p>This method is not case-sensitive.
   *
   * @param code A string ({@code null} accepted).
   * @return the country having the given ISO 3166-1 alpha-3 code, or {@code Optional#empty}.
   */
  public static Optional<IsoCountry> fromAlpha3Code(String code) {
    if (code != null && code.length() == 3) {
      String upperCasedCode = code.toUpperCase();

      for (IsoCountry country : values()) {
        if (country.getAlpha3Code().equals(upperCasedCode)) {
          return Optional.of(country);
        }
      }
    }

    return Optional.empty();
  }

  /**
   * Translate the given ISO 3166-1 numeric code to an IsoCountry.
   *
   * @param code A string ({@code null} accepted).
   * @return the country having the given ISO 3166-1 alpha-3 code, or {@code Optional#empty}.
   */
  public static Optional<IsoCountry> fromNumericCode(int code) {
    for (IsoCountry country : values()) {
      Optional<Integer> numericCode = country.getNumericCode();
      if (numericCode.isPresent() && numericCode.get() == code) {
        return Optional.of(country);
      }
    }

    return Optional.empty();
  }
}
