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
 * <p>Please be advised that this list is current as of 2020-08-03. An up-to-date list can be found
 * on the <a href="http://www.iso.org/iso/home/standards/country_codes.htm">International
 * Organization for Standardization</a> website.
 *
 * @author Marc Wrobel
 * @see <a href="http://www.iso.org/iso/home/standards/country_codes.htm">ISO 3166 Country Codes</a>
 * @since 1.0
 */
public enum IsoCountry {
  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AF">www.iso.org</a> */
  AFGHANISTAN("AF", "AFG", 4),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AL">www.iso.org</a> */
  ALBANIA("AL", "ALB", 8),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DZ">www.iso.org</a> */
  ALGERIA("DZ", "DZA", 12),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AD">www.iso.org</a> */
  ANDORRA("AD", "AND", 20),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AO">www.iso.org</a> */
  ANGOLA("AO", "AGO", 24),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AQ">www.iso.org</a> */
  ANTARCTICA("AQ", "ATA", 10, null),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AG">www.iso.org</a> */
  ANTIGUA_AND_BARBUDA("AG", "ATG", 28),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AR">www.iso.org</a> */
  ARGENTINA("AR", "ARG", 32),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AM">www.iso.org</a> */
  ARMENIA("AM", "ARM", 51),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AU">www.iso.org</a> */
  AUSTRALIA("AU", "AUS", 36),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CX">www.iso.org</a> */
  CHRISTMAS_ISLAND("CX", "CXR", 162, AUSTRALIA),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CC">www.iso.org</a> */
  COCOS_ISLANDS("CC", "CCK", 166, AUSTRALIA),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HM">www.iso.org</a> */
  HEARD_ISLAND_AND_MCDONALD_ISLANDS("HM", "HMD", 334, AUSTRALIA),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NF">www.iso.org</a> */
  NORFOLK_ISLAND("NF", "NFK", 574, AUSTRALIA),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AT">www.iso.org</a> */
  AUSTRIA("AT", "AUT", 40),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AZ">www.iso.org</a> */
  AZERBAIJAN("AZ", "AZE", 31),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BS">www.iso.org</a> */
  BAHAMAS("BS", "BHS", 44),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BH">www.iso.org</a> */
  BAHRAIN("BH", "BHR", 48),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BD">www.iso.org</a> */
  BANGLADESH("BD", "BGD", 50),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BB">www.iso.org</a> */
  BARBADOS("BB", "BRB", 52),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BY">www.iso.org</a> */
  BELARUS("BY", "BLR", 112),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BE">www.iso.org</a> */
  BELGIUM("BE", "BEL", 56),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BZ">www.iso.org</a> */
  BELIZE("BZ", "BLZ", 84),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BJ">www.iso.org</a> */
  BENIN("BJ", "BEN", 204),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BT">www.iso.org</a> */
  BHUTAN("BT", "BTN", 64),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BO">www.iso.org</a> */
  BOLIVIA("BO", "BOL", 68),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BA">www.iso.org</a> */
  BOSNIA_AND_HERZEGOVINA("BA", "BIH", 70),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BW">www.iso.org</a> */
  BOTSWANA("BW", "BWA", 72),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BR">www.iso.org</a> */
  BRAZIL("BR", "BRA", 76),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BN">www.iso.org</a> */
  BRUNEI_DARUSSALAM("BN", "BRN", 96),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BG">www.iso.org</a> */
  BULGARIA("BG", "BGR", 100),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BF">www.iso.org</a> */
  BURKINA_FASO("BF", "BFA", 854),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BI">www.iso.org</a> */
  BURUNDI("BI", "BDI", 108),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KH">www.iso.org</a> */
  CAMBODIA("KH", "KHM", 116),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CM">www.iso.org</a> */
  CAMEROON("CM", "CMR", 120),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CA">www.iso.org</a> */
  CANADA("CA", "CAN", 124),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CV">www.iso.org</a> */
  CAPE_VERDE("CV", "CPV", 132),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CF">www.iso.org</a> */
  CENTRAL_AFRICAN_REPUBLIC("CF", "CAF", 140),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TD">www.iso.org</a> */
  CHAD("TD", "TCD", 148),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CL">www.iso.org</a> */
  CHILE("CL", "CHL", 152),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CN">www.iso.org</a> */
  CHINA("CN", "CHN", 156),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HK">www.iso.org</a> */
  HONG_KONG("HK", "HKG", 344, CHINA),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MO">www.iso.org</a> */
  MACAO("MO", "MAC", 446, CHINA),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CO">www.iso.org</a> */
  COLOMBIA("CO", "COL", 170),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KM">www.iso.org</a> */
  COMOROS("KM", "COM", 174),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CG">www.iso.org</a> */
  CONGO("CG", "COG", 178),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CR">www.iso.org</a> */
  COSTA_RICA("CR", "CRI", 188),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CI">www.iso.org</a> */
  COTE_D_IVOIRE("CI", "CIV", 384),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HR">www.iso.org</a> */
  CROATIA("HR", "HRV", 191),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CU">www.iso.org</a> */
  CUBA("CU", "CUB", 192),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CY">www.iso.org</a> */
  CYPRUS("CY", "CYP", 196),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CZ">www.iso.org</a> */
  CZECH_REPUBLIC("CZ", "CZE", 203),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DK">www.iso.org</a> */
  DENMARK("DK", "DNK", 208),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FO">www.iso.org</a> */
  FAROE_ISLANDS("FO", "FRO", 234, DENMARK),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GL">www.iso.org</a> */
  GREENLAND("GL", "GRL", 304, DENMARK),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DJ">www.iso.org</a> */
  DJIBOUTI("DJ", "DJI", 262),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DM">www.iso.org</a> */
  DOMINICA("DM", "DMA", 212),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DO">www.iso.org</a> */
  DOMINICAN_REPUBLIC("DO", "DOM", 214),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:EC">www.iso.org</a> */
  ECUADOR("EC", "ECU", 218),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:EG">www.iso.org</a> */
  EGYPT("EG", "EGY", 818),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SV">www.iso.org</a> */
  EL_SALVADOR("SV", "SLV", 222),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GQ">www.iso.org</a> */
  EQUATORIAL_GUINEA("GQ", "GNQ", 226),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ER">www.iso.org</a> */
  ERITREA("ER", "ERI", 232),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:EE">www.iso.org</a> */
  ESTONIA("EE", "EST", 233),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ET">www.iso.org</a> */
  ETHIOPIA("ET", "ETH", 231),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FJ">www.iso.org</a> */
  FIJI("FJ", "FJI", 242),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FI">www.iso.org</a> */
  FINLAND("FI", "FIN", 246),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AX">www.iso.org</a> */
  ALAND_ISLANDS("AX", "ALA", 248, FINLAND),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FR">www.iso.org</a> */
  FRANCE("FR", "FRA", 250),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GF">www.iso.org</a> */
  FRENCH_GUIANA("GF", "GUF", 254, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PF">www.iso.org</a> */
  FRENCH_POLYNESIA("PF", "PYF", 258, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TF">www.iso.org</a> */
  FRENCH_SOUTHERN_TERRITORIES("TF", "ATF", 260, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GP">www.iso.org</a> */
  GUADELOUPE("GP", "GLP", 312, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MQ">www.iso.org</a> */
  MARTINIQUE("MQ", "MTQ", 474, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:YT">www.iso.org</a> */
  MAYOTTE("YT", "MYT", 175, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NC">www.iso.org</a> */
  NEW_CALEDONIA("NC", "NCL", 540, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RE">www.iso.org</a> */
  REUNION("RE", "REU", 638, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BL">www.iso.org</a> */
  SAINT_BARTHELEMY("BL", "BLM", 652, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MF">www.iso.org</a> */
  SAINT_MARTIN("MF", "MAF", 663, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PM">www.iso.org</a> */
  SAINT_PIERRE_AND_MIQUELON("PM", "SPM", 666, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:WF">www.iso.org</a> */
  WALLIS_AND_FUTUNA("WF", "WLF", 876, FRANCE),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GA">www.iso.org</a> */
  GABON("GA", "GAB", 266),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GM">www.iso.org</a> */
  GAMBIA("GM", "GMB", 270),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GE">www.iso.org</a> */
  GEORGIA("GE", "GEO", 268),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:DE">www.iso.org</a> */
  GERMANY("DE", "DEU", 276),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GH">www.iso.org</a> */
  GHANA("GH", "GHA", 288),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GR">www.iso.org</a> */
  GREECE("GR", "GRC", 300),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GD">www.iso.org</a> */
  GRENADA("GD", "GRD", 308),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GT">www.iso.org</a> */
  GUATEMALA("GT", "GTM", 320),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GN">www.iso.org</a> */
  GUINEA("GN", "GIN", 324),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GW">www.iso.org</a> */
  GUINEA_BISSAU("GW", "GNB", 624),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GY">www.iso.org</a> */
  GUYANA("GY", "GUY", 328),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HT">www.iso.org</a> */
  HAITI("HT", "HTI", 332),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HN">www.iso.org</a> */
  HONDURAS("HN", "HND", 340),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:HU">www.iso.org</a> */
  HUNGARY("HU", "HUN", 348),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IS">www.iso.org</a> */
  ICELAND("IS", "ISL", 352),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IN">www.iso.org</a> */
  INDIA("IN", "IND", 356),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ID">www.iso.org</a> */
  INDONESIA("ID", "IDN", 360),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IR">www.iso.org</a> */
  IRAN("IR", "IRN", 364),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IQ">www.iso.org</a> */
  IRAQ("IQ", "IRQ", 368),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IE">www.iso.org</a> */
  IRELAND("IE", "IRL", 372),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IL">www.iso.org</a> */
  ISRAEL("IL", "ISR", 376),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IT">www.iso.org</a> */
  ITALY("IT", "ITA", 380),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:JM">www.iso.org</a> */
  JAMAICA("JM", "JAM", 388),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:JP">www.iso.org</a> */
  JAPAN("JP", "JPN", 392),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:JO">www.iso.org</a> */
  JORDAN("JO", "JOR", 400),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KZ">www.iso.org</a> */
  KAZAKHSTAN("KZ", "KAZ", 398),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KE">www.iso.org</a> */
  KENYA("KE", "KEN", 404),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KI">www.iso.org</a> */
  KIRIBATI("KI", "KIR", 296),

  /** Temporarily used by the European Commission, the IMF, and SWIFT. */
  KOSOVO("XK", "XKX", -1),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KW">www.iso.org</a> */
  KUWAIT("KW", "KWT", 414),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KG">www.iso.org</a> */
  KYRGYZSTAN("KG", "KGZ", 417),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LA">www.iso.org</a> */
  LAO_PEOPLES_DEMOCRATIC_REPUBLIC("LA", "LAO", 418),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LV">www.iso.org</a> */
  LATVIA("LV", "LVA", 428),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LB">www.iso.org</a> */
  LEBANON("LB", "LBN", 422),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LS">www.iso.org</a> */
  LESOTHO("LS", "LSO", 426),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LR">www.iso.org</a> */
  LIBERIA("LR", "LBR", 430),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LY">www.iso.org</a> */
  LIBYA("LY", "LBY", 434),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LI">www.iso.org</a> */
  LIECHTENSTEIN("LI", "LIE", 438),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LT">www.iso.org</a> */
  LITHUANIA("LT", "LTU", 440),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LU">www.iso.org</a> */
  LUXEMBOURG("LU", "LUX", 442),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MK">www.iso.org</a> */
  MACEDONIA("MK", "MKD", 807),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MG">www.iso.org</a> */
  MADAGASCAR("MG", "MDG", 450),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MW">www.iso.org</a> */
  MALAWI("MW", "MWI", 454),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MY">www.iso.org</a> */
  MALAYSIA("MY", "MYS", 458),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MV">www.iso.org</a> */
  MALDIVES("MV", "MDV", 462),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ML">www.iso.org</a> */
  MALI("ML", "MLI", 466),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MT">www.iso.org</a> */
  MALTA("MT", "MLT", 470),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MH">www.iso.org</a> */
  MARSHALL_ISLANDS("MH", "MHL", 584),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MR">www.iso.org</a> */
  MAURITANIA("MR", "MRT", 478),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MU">www.iso.org</a> */
  MAURITIUS("MU", "MUS", 480),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MX">www.iso.org</a> */
  MEXICO("MX", "MEX", 484),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FM">www.iso.org</a> */
  MICRONESIA("FM", "FSM", 583),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MD">www.iso.org</a> */
  MOLDOVA("MD", "MDA", 498),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MC">www.iso.org</a> */
  MONACO("MC", "MCO", 492),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MN">www.iso.org</a> */
  MONGOLIA("MN", "MNG", 496),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ME">www.iso.org</a> */
  MONTENEGRO("ME", "MNE", 499),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MA">www.iso.org</a> */
  MOROCCO("MA", "MAR", 504),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MZ">www.iso.org</a> */
  MOZAMBIQUE("MZ", "MOZ", 508),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MM">www.iso.org</a> */
  MYANMAR("MM", "MMR", 104),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NA">www.iso.org</a> */
  NAMIBIA("NA", "NAM", 516),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NR">www.iso.org</a> */
  NAURU("NR", "NRU", 520),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NP">www.iso.org</a> */
  NEPAL("NP", "NPL", 524),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NL">www.iso.org</a> */
  NETHERLANDS("NL", "NLD", 528),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AW">www.iso.org</a> */
  ARUBA("AW", "ABW", 533, NETHERLANDS),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BQ">www.iso.org</a> */
  BONAIRE("BQ", "BES", 535, NETHERLANDS),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CW">www.iso.org</a> */
  CURACAO("CW", "CUW", 531, NETHERLANDS),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SX">www.iso.org</a> */
  SINT_MAARTEN("SX", "SXM", 534, NETHERLANDS),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NZ">www.iso.org</a> */
  NEW_ZEALAND("NZ", "NZL", 554),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CK">www.iso.org</a> */
  COOK_ISLANDS("CK", "COK", 184, NEW_ZEALAND),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NU">www.iso.org</a> */
  NIUE("NU", "NIU", 570, NEW_ZEALAND),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TK">www.iso.org</a> */
  TOKELAU("TK", "TKL", 772, NEW_ZEALAND),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NI">www.iso.org</a> */
  NICARAGUA("NI", "NIC", 558),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NE">www.iso.org</a> */
  NIGER("NE", "NER", 562),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NG">www.iso.org</a> */
  NIGERIA("NG", "NGA", 566),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KP">www.iso.org</a> */
  NORTH_KOREA("KP", "PRK", 408),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:NO">www.iso.org</a> */
  NORWAY("NO", "NOR", 578),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BV">www.iso.org</a> */
  BOUVET_ISLAND("BV", "BVT", 74, NORWAY),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SJ">www.iso.org</a> */
  SVALBARD_AND_JAN_MAYEN("SJ", "SJM", 744, NORWAY),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:OM">www.iso.org</a> */
  OMAN("OM", "OMN", 512),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PK">www.iso.org</a> */
  PAKISTAN("PK", "PAK", 586),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PW">www.iso.org</a> */
  PALAU("PW", "PLW", 585),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PS">www.iso.org</a> */
  PALESTINE("PS", "PSE", 275, null),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PA">www.iso.org</a> */
  PANAMA("PA", "PAN", 591),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PG">www.iso.org</a> */
  PAPUA_NEW_GUINEA("PG", "PNG", 598),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PY">www.iso.org</a> */
  PARAGUAY("PY", "PRY", 600),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PE">www.iso.org</a> */
  PERU("PE", "PER", 604),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PH">www.iso.org</a> */
  PHILIPPINES("PH", "PHL", 608),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PL">www.iso.org</a> */
  POLAND("PL", "POL", 616),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PT">www.iso.org</a> */
  PORTUGAL("PT", "PRT", 620),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:QA">www.iso.org</a> */
  QATAR("QA", "QAT", 634),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RO">www.iso.org</a> */
  ROMANIA("RO", "ROU", 642),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RU">www.iso.org</a> */
  RUSSIAN_FEDERATION("RU", "RUS", 643),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RW">www.iso.org</a> */
  RWANDA("RW", "RWA", 646),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KN">www.iso.org</a> */
  SAINT_KITTS_AND_NEVIS("KN", "KNA", 659),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LC">www.iso.org</a> */
  SAINT_LUCIA("LC", "LCA", 662),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VC">www.iso.org</a> */
  SAINT_VINCENT_AND_THE_GRENADINES("VC", "VCT", 670),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:WS">www.iso.org</a> */
  SAMOA("WS", "WSM", 882),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SM">www.iso.org</a> */
  SAN_MARINO("SM", "SMR", 674),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ST">www.iso.org</a> */
  SAO_TOME_AND_PRINCIPE("ST", "STP", 678),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SA">www.iso.org</a> */
  SAUDI_ARABIA("SA", "SAU", 682),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SN">www.iso.org</a> */
  SENEGAL("SN", "SEN", 686),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:RS">www.iso.org</a> */
  SERBIA("RS", "SRB", 688),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SC">www.iso.org</a> */
  SEYCHELLES("SC", "SYC", 690),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SL">www.iso.org</a> */
  SIERRA_LEONE("SL", "SLE", 694),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SG">www.iso.org</a> */
  SINGAPORE("SG", "SGP", 702),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SK">www.iso.org</a> */
  SLOVAKIA("SK", "SVK", 703),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SI">www.iso.org</a> */
  SLOVENIA("SI", "SVN", 705),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SB">www.iso.org</a> */
  SOLOMON_ISLANDS("SB", "SLB", 90),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SO">www.iso.org</a> */
  SOMALIA("SO", "SOM", 706),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ZA">www.iso.org</a> */
  SOUTH_AFRICA("ZA", "ZAF", 710),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KR">www.iso.org</a> */
  SOUTH_KOREA("KR", "KOR", 410),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SS">www.iso.org</a> */
  SOUTH_SUDAN("SS", "SSD", 728),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ES">www.iso.org</a> */
  SPAIN("ES", "ESP", 724),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:LK">www.iso.org</a> */
  SRI_LANKA("LK", "LKA", 144),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SD">www.iso.org</a> */
  SUDAN("SD", "SDN", 729),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SR">www.iso.org</a> */
  SURINAME("SR", "SUR", 740),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SZ">www.iso.org</a> */
  SWAZILAND("SZ", "SWZ", 748),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SE">www.iso.org</a> */
  SWEDEN("SE", "SWE", 752),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CH">www.iso.org</a> */
  SWITZERLAND("CH", "CHE", 756),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SY">www.iso.org</a> */
  SYRIAN_ARAB_REPUBLIC("SY", "SYR", 760),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TW">www.iso.org</a> */
  TAIWAN("TW", "TWN", 158, null),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TJ">www.iso.org</a> */
  TAJIKISTAN("TJ", "TJK", 762),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TZ">www.iso.org</a> */
  TANZANIA("TZ", "TZA", 834),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TH">www.iso.org</a> */
  THAILAND("TH", "THA", 764),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:CD">www.iso.org</a> */
  THE_DEMOCRATIC_REPUBLIC_OF_THE_CONGO("CD", "COD", 180),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TL">www.iso.org</a> */
  TIMOR_LESTE("TL", "TLS", 626),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TG">www.iso.org</a> */
  TOGO("TG", "TGO", 768),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TO">www.iso.org</a> */
  TONGA("TO", "TON", 776),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TT">www.iso.org</a> */
  TRINIDAD_AND_TOBAGO("TT", "TTO", 780),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TN">www.iso.org</a> */
  TUNISIA("TN", "TUN", 788),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TR">www.iso.org</a> */
  TURKEY("TR", "TUR", 792),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TM">www.iso.org</a> */
  TURKMENISTAN("TM", "TKM", 795),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TV">www.iso.org</a> */
  TUVALU("TV", "TUV", 798),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UG">www.iso.org</a> */
  UGANDA("UG", "UGA", 800),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UA">www.iso.org</a> */
  UKRAINE("UA", "UKR", 804),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AE">www.iso.org</a> */
  UNITED_ARAB_EMIRATES("AE", "ARE", 784),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GB">www.iso.org</a> */
  UNITED_KINGDOM("GB", "GBR", 826),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AI">www.iso.org</a> */
  ANGUILLA("AI", "AIA", 660, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:BM">www.iso.org</a> */
  BERMUDA("BM", "BMU", 60, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IO">www.iso.org</a> */
  BRITISH_INDIAN_OCEAN_TERRITORY("IO", "IOT", 86, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VG">www.iso.org</a> */
  BRITISH_VIRGIN_ISLANDS("VG", "VGB", 92, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:KY">www.iso.org</a> */
  CAYMAN_ISLANDS("KY", "CYM", 136, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:FK">www.iso.org</a> */
  FALKLAND_ISLANDS("FK", "FLK", 238, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GI">www.iso.org</a> */
  GIBRALTAR("GI", "GIB", 292, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GG">www.iso.org</a> */
  GUERNSEY("GG", "GGY", 831, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:IM">www.iso.org</a> */
  ISLE_OF_MAN("IM", "IMN", 833, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:JE">www.iso.org</a> */
  JERSEY("JE", "JEY", 832, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MS">www.iso.org</a> */
  MONTSERRAT("MS", "MSR", 500, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PN">www.iso.org</a> */
  PITCAIRN("PN", "PCN", 612, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:SH">www.iso.org</a> */
  SAINT_HELENA("SH", "SHN", 654, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GS">www.iso.org</a> */
  SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS("GS", "SGS", 239, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:TC">www.iso.org</a> */
  TURKS_AND_CAICOS_ISLANDS("TC", "TCA", 796, UNITED_KINGDOM),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:US">www.iso.org</a> */
  UNITED_STATES("US", "USA", 840),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:AS">www.iso.org</a> */
  AMERICAN_SAMOA("AS", "ASM", 16, UNITED_STATES),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:GU">www.iso.org</a> */
  GUAM("GU", "GUM", 316, UNITED_STATES),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:MP">www.iso.org</a> */
  NORTHERN_MARIANA_ISLANDS("MP", "MNP", 580, UNITED_STATES),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:PR">www.iso.org</a> */
  PUERTO_RICO("PR", "PRI", 630, UNITED_STATES),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UM">www.iso.org</a> */
  UNITED_STATES_MINOR_OUTLYING_ISLANDS("UM", "UMI", 581, UNITED_STATES),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VI">www.iso.org</a> */
  US_VIRGIN_ISLANDS("VI", "VIR", 850, UNITED_STATES),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UY">www.iso.org</a> */
  URUGUAY("UY", "URY", 858),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:UZ">www.iso.org</a> */
  UZBEKISTAN("UZ", "UZB", 860),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VU">www.iso.org</a> */
  VANUATU("VU", "VUT", 548),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VA">www.iso.org</a> */
  VATICAN_CITY_STATE("VA", "VAT", 336),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VE">www.iso.org</a> */
  VENEZUELA("VE", "VEN", 862),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:VN">www.iso.org</a> */
  VIET_NAM("VN", "VNM", 704),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:EH">www.iso.org</a> */
  WESTERN_SAHARA("EH", "ESH", 732, null),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:YE">www.iso.org</a> */
  YEMEN("YE", "YEM", 887),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ZM">www.iso.org</a> */
  ZAMBIA("ZM", "ZMB", 894),

  /** @see <a href="https://www.iso.org/obp/ui/#iso:code:3166:ZW">www.iso.org</a> */
  ZIMBABWE("ZW", "ZWE", 716);

  public static final int UNSPECIFIED_NUMERIC_CODE = -1;

  private final String alpha2Code;
  private final String alpha3Code;
  private final Integer numericCode;

  private final boolean independent;
  private final IsoCountry dependsOn;

  /** General purpose constructor. */
  IsoCountry(
      String alpha2Code,
      String alpha3Code,
      int numericCode,
      boolean independent,
      IsoCountry dependsOn) {
    this.alpha2Code = requireNonNull(alpha2Code);
    this.alpha3Code = requireNonNull(alpha3Code);
    this.numericCode = numericCode;

    this.independent = independent;
    this.dependsOn = dependsOn;
  }

  /** Dependents countries or territories constructor. */
  IsoCountry(String alpha2Code, String alpha3Code, int numericCode, IsoCountry dependsOn) {
    this(alpha2Code, alpha3Code, numericCode, false, dependsOn);
  }

  /** Independents countries constructor. */
  IsoCountry(String alpha2Code, String alpha3Code, int numericCode) {
    this(alpha2Code, alpha3Code, numericCode, true, null);
  }

  /**
   * Returns this country ISO 3166-1-alpha-2 code.
   *
   * @deprecated use {@link #getAlpha2Code()}, will be removed in the next major version
   * @return a non null and 2 characters length string.
   * @see #getAlpha2Code()
   */
  @Deprecated
  public String getCode() {
    return getAlpha2Code();
  }

  /**
   * Returns this country ISO 3166-1 alpha-2 code.
   *
   * @return a non null and 2 characters length string.
   */
  public String getAlpha2Code() {
    return alpha2Code;
  }

  /**
   * Returns this country ISO 3166-1 alpha-3 code.
   *
   * @return a non null and 3 characters length string.
   */
  public String getAlpha3Code() {
    return alpha3Code;
  }

  /**
   * Returns this country ISO 3166-1 numeric code.
   *
   * @return a positive integer, or {@link #UNSPECIFIED_NUMERIC_CODE} if the alpha-2 code was
   *     user-assigned.
   */
  public int getNumericCode() {
    return numericCode;
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
   *   <li>may be unspecified, mainly for political reasons ({@link #ANTARCTICA}, {@link
   *       #PALESTINE}, {@link #TAIWAN}, {@link #WESTERN_SAHARA}),
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
   * Translate the given ISO 3166-1-alpha-2 code to an IsoCountry.
   *
   * <p>This method is not case-sensitive.
   *
   * @param code A non-null String.
   * @deprecated use {@link #fromAlpha2Code(String)}, will be removed in the next major version
   * @return the country having the given ISO 3166-1-alpha-2 code, or null if it does not exist.
   * @see #fromAlpha2Code(String)
   */
  @Deprecated
  public static IsoCountry fromCode(String code) {
    return fromAlpha2Code(code);
  }

  /**
   * Translate the given ISO 3166-1-alpha-2 code to an IsoCountry.
   *
   * <p>This method is not case-sensitive.
   *
   * @param code A non-null String.
   * @return the country having the given ISO 3166-1-alpha-2 code, or null if it does not exist.
   */
  public static IsoCountry fromAlpha2Code(String code) {
    String cleanedCode = (code == null ? null : code.toUpperCase());

    if (cleanedCode == null || cleanedCode.length() != 2) {
      return null;
    }

    for (IsoCountry country : values()) {
      if (country.getAlpha2Code().equals(cleanedCode)) {
        return country;
      }
    }

    return null;
  }
}
