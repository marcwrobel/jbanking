package fr.marcwrobel.jbanking;

import static java.util.Objects.requireNonNull;

import java.util.EnumSet;
import java.util.Set;

/**
 * The countries having an <a
 * href="http://wikipedia.org/wiki/ISO_3166-1_alpha-2#Officially_assigned_code_elements">Officially
 * assigned</a>, or a <a
 * href="http://wikipedia.org/wiki/ISO_3166-1_alpha-2#User-assigned_code_elements">User assigned</a>
 * ISO 3166-1 alpha-2, ISO 3166-1 alpha-3 and ISO 3166-1 numeric code.
 *
 * <p>Please be advised that this list is current as of 2020-07-31. Up-to-date list can be found for
 * free on the <a href="http://www.iso.org/iso/home/standards/country_codes.htm">International
 * Organization for Standardization website</a>.
 *
 * @author Marc Wrobel
 * @see <a href="http://www.iso.org/iso/home/standards/country_codes.htm">ISO 3166 Country Codes</a>
 * @since 1.0
 */
public enum IsoCountry {
  AFGHANISTAN("AF", "AFG", 4),
  ALAND_ISLANDS("AX", "ALA", 248),
  ALBANIA("AL", "ALB", 8),
  ALGERIA("DZ", "DZA", 12),
  AMERICAN_SAMOA("AS", "ASM", 16),
  ANDORRA("AD", "AND", 20),
  ANGOLA("AO", "AGO", 24),
  ANGUILLA("AI", "AIA", 660),
  ANTARCTICA("AQ", "ATA", 10),
  ANTIGUA_AND_BARBUDA("AG", "ATG", 28),
  ARGENTINA("AR", "ARG", 32),
  ARMENIA("AM", "ARM", 51),
  ARUBA("AW", "ABW", 533),
  AUSTRALIA("AU", "AUS", 36),
  AUSTRIA("AT", "AUT", 40),
  AZERBAIJAN("AZ", "AZE", 31),
  BAHAMAS("BS", "BHS", 44),
  BAHRAIN("BH", "BHR", 48),
  BANGLADESH("BD", "BGD", 50),
  BARBADOS("BB", "BRB", 52),
  BELARUS("BY", "BLR", 112),
  BELGIUM("BE", "BEL", 56),
  BELIZE("BZ", "BLZ", 84),
  BENIN("BJ", "BEN", 204),
  BERMUDA("BM", "BMU", 60),
  BHUTAN("BT", "BTN", 64),
  BOLIVIA("BO", "BOL", 68),
  BONAIRE("BQ", "BES", 535),
  BOSNIA_AND_HERZEGOVINA("BA", "BIH", 70),
  BOTSWANA("BW", "BWA", 72),
  BOUVET_ISLAND("BV", "BVT", 74),
  BRAZIL("BR", "BRA", 76),
  BRITISH_INDIAN_OCEAN_TERRITORY("IO", "IOT", 86),
  BRITISH_VIRGIN_ISLANDS("VG", "VGB", 92),
  BRUNEI_DARUSSALAM("BN", "BRN", 96),
  BULGARIA("BG", "BGR", 100),
  BURKINA_FASO("BF", "BFA", 854),
  BURUNDI("BI", "BDI", 108),
  CAMBODIA("KH", "KHM", 116),
  CAMEROON("CM", "CMR", 120),
  CANADA("CA", "CAN", 124),
  CAPE_VERDE("CV", "CPV", 132),
  CAYMAN_ISLANDS("KY", "CYM", 136),
  CENTRAL_AFRICAN_REPUBLIC("CF", "CAF", 140),
  CHAD("TD", "TCD", 148),
  CHILE("CL", "CHL", 152),
  CHINA("CN", "CHN", 156),
  CHRISTMAS_ISLAND("CX", "CXR", 162),
  COCOS_ISLANDS("CC", "CCK", 166),
  COLOMBIA("CO", "COL", 170),
  COMOROS("KM", "COM", 174),
  CONGO("CG", "COG", 178),
  COOK_ISLANDS("CK", "COK", 184),
  COSTA_RICA("CR", "CRI", 188),
  COTE_D_IVOIRE("CI", "CIV", 384),
  CROATIA("HR", "HRV", 191),
  CUBA("CU", "CUB", 192),
  CURACAO("CW", "CUW", 531),
  CYPRUS("CY", "CYP", 196),
  CZECH_REPUBLIC("CZ", "CZE", 203),
  DENMARK("DK", "DNK", 208),
  DJIBOUTI("DJ", "DJI", 262),
  DOMINICA("DM", "DMA", 212),
  DOMINICAN_REPUBLIC("DO", "DOM", 214),
  ECUADOR("EC", "ECU", 218),
  EGYPT("EG", "EGY", 818),
  EL_SALVADOR("SV", "SLV", 222),
  EQUATORIAL_GUINEA("GQ", "GNQ", 226),
  ERITREA("ER", "ERI", 232),
  ESTONIA("EE", "EST", 233),
  ETHIOPIA("ET", "ETH", 231),
  FALKLAND_ISLANDS("FK", "FLK", 238),
  FAROE_ISLANDS("FO", "FRO", 234),
  FIJI("FJ", "FJI", 242),
  FINLAND("FI", "FIN", 246),
  FRANCE("FR", "FRA", 250),
  FRENCH_GUIANA("GF", "GUF", 254),
  FRENCH_POLYNESIA("PF", "PYF", 258),
  FRENCH_SOUTHERN_TERRITORIES("TF", "ATF", 260),
  GABON("GA", "GAB", 266),
  GAMBIA("GM", "GMB", 270),
  GEORGIA("GE", "GEO", 268),
  GERMANY("DE", "DEU", 276),
  GHANA("GH", "GHA", 288),
  GIBRALTAR("GI", "GIB", 292),
  GREECE("GR", "GRC", 300),
  GREENLAND("GL", "GRL", 304),
  GRENADA("GD", "GRD", 308),
  GUADELOUPE("GP", "GLP", 312),
  GUAM("GU", "GUM", 316),
  GUATEMALA("GT", "GTM", 320),
  GUERNSEY("GG", "GGY", 831),
  GUINEA("GN", "GIN", 324),
  GUINEA_BISSAU("GW", "GNB", 624),
  GUYANA("GY", "GUY", 328),
  HAITI("HT", "HTI", 332),
  HEARD_ISLAND_AND_MCDONALD_ISLANDS("HM", "HMD", 334),
  HONDURAS("HN", "HND", 340),
  HONG_KONG("HK", "HKG", 344),
  HUNGARY("HU", "HUN", 348),
  ICELAND("IS", "ISL", 352),
  INDIA("IN", "IND", 356),
  INDONESIA("ID", "IDN", 360),
  IRAN("IR", "IRN", 364),
  IRAQ("IQ", "IRQ", 368),
  IRELAND("IE", "IRL", 372),
  ISLE_OF_MAN("IM", "IMN", 833),
  ISRAEL("IL", "ISR", 376),
  ITALY("IT", "ITA", 380),
  JAMAICA("JM", "JAM", 388),
  JAPAN("JP", "JPN", 392),
  JERSEY("JE", "JEY", 832),
  JORDAN("JO", "JOR", 400),
  KAZAKHSTAN("KZ", "KAZ", 398),
  KENYA("KE", "KEN", 404),
  KIRIBATI("KI", "KIR", 296),
  KOSOVO("XK", "XKX", null), // temporary country code used by SWIFT
  KUWAIT("KW", "KWT", 414),
  KYRGYZSTAN("KG", "KGZ", 417),
  LAO_PEOPLES_DEMOCRATIC_REPUBLIC("LA", "LAO", 418),
  LATVIA("LV", "LVA", 428),
  LEBANON("LB", "LBN", 422),
  LESOTHO("LS", "LSO", 426),
  LIBERIA("LR", "LBR", 430),
  LIBYA("LY", "LBY", 434),
  LIECHTENSTEIN("LI", "LIE", 438),
  LITHUANIA("LT", "LTU", 440),
  LUXEMBOURG("LU", "LUX", 442),
  MACAO("MO", "MAC", 446),
  MACEDONIA("MK", "MKD", 807),
  MADAGASCAR("MG", "MDG", 450),
  MALAWI("MW", "MWI", 454),
  MALAYSIA("MY", "MYS", 458),
  MALDIVES("MV", "MDV", 462),
  MALI("ML", "MLI", 466),
  MALTA("MT", "MLT", 470),
  MARSHALL_ISLANDS("MH", "MHL", 584),
  MARTINIQUE("MQ", "MTQ", 474),
  MAURITANIA("MR", "MRT", 478),
  MAURITIUS("MU", "MUS", 480),
  MAYOTTE("YT", "MYT", 175),
  MEXICO("MX", "MEX", 484),
  MICRONESIA("FM", "FSM", 583),
  MOLDOVA("MD", "MDA", 498),
  MONACO("MC", "MCO", 492),
  MONGOLIA("MN", "MNG", 496),
  MONTENEGRO("ME", "MNE", 499),
  MONTSERRAT("MS", "MSR", 500),
  MOROCCO("MA", "MAR", 504),
  MOZAMBIQUE("MZ", "MOZ", 508),
  MYANMAR("MM", "MMR", 104),
  NAMIBIA("NA", "NAM", 516),
  NAURU("NR", "NRU", 520),
  NEPAL("NP", "NPL", 524),
  NETHERLANDS("NL", "NLD", 528),
  NEW_CALEDONIA("NC", "NCL", 540),
  NEW_ZEALAND("NZ", "NZL", 554),
  NICARAGUA("NI", "NIC", 558),
  NIGER("NE", "NER", 562),
  NIGERIA("NG", "NGA", 566),
  NIUE("NU", "NIU", 570),
  NORFOLK_ISLAND("NF", "NFK", 574),
  NORTH_KOREA("KP", "PRK", 408),
  NORTHERN_MARIANA_ISLANDS("MP", "MNP", 580),
  NORWAY("NO", "NOR", 578),
  OMAN("OM", "OMN", 512),
  PAKISTAN("PK", "PAK", 586),
  PALAU("PW", "PLW", 585),
  PALESTINE("PS", "PSE", 275),
  PANAMA("PA", "PAN", 591),
  PAPUA_NEW_GUINEA("PG", "PNG", 598),
  PARAGUAY("PY", "PRY", 600),
  PERU("PE", "PER", 604),
  PHILIPPINES("PH", "PHL", 608),
  PITCAIRN("PN", "PCN", 612),
  POLAND("PL", "POL", 616),
  PORTUGAL("PT", "PRT", 620),
  PUERTO_RICO("PR", "PRI", 630),
  QATAR("QA", "QAT", 634),
  REUNION("RE", "REU", 638),
  ROMANIA("RO", "ROU", 642),
  RUSSIAN_FEDERATION("RU", "RUS", 643),
  RWANDA("RW", "RWA", 646),
  SAINT_BARTHELEMY("BL", "BLM", 652),
  SAINT_HELENA("SH", "SHN", 654),
  SAINT_KITTS_AND_NEVIS("KN", "KNA", 659),
  SAINT_LUCIA("LC", "LCA", 662),
  SAINT_MARTIN("MF", "MAF", 663),
  SAINT_PIERRE_AND_MIQUELON("PM", "SPM", 666),
  SAINT_VINCENT_AND_THE_GRENADINES("VC", "VCT", 670),
  SAMOA("WS", "WSM", 882),
  SAN_MARINO("SM", "SMR", 674),
  SAO_TOME_AND_PRINCIPE("ST", "STP", 678),
  SAUDI_ARABIA("SA", "SAU", 682),
  SENEGAL("SN", "SEN", 686),
  SERBIA("RS", "SRB", 688),
  SEYCHELLES("SC", "SYC", 690),
  SIERRA_LEONE("SL", "SLE", 694),
  SINGAPORE("SG", "SGP", 702),
  SINT_MAARTEN("SX", "SXM", 534),
  SLOVAKIA("SK", "SVK", 703),
  SLOVENIA("SI", "SVN", 705),
  SOLOMON_ISLANDS("SB", "SLB", 90),
  SOMALIA("SO", "SOM", 706),
  SOUTH_AFRICA("ZA", "ZAF", 710),
  SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS("GS", "SGS", 239),
  SOUTH_KOREA("KR", "KOR", 410),
  SOUTH_SUDAN("SS", "SSD", 728),
  SPAIN("ES", "ESP", 724),
  SRI_LANKA("LK", "LKA", 144),
  SUDAN("SD", "SDN", 729),
  SURINAME("SR", "SUR", 740),
  SVALBARD_AND_JAN_MAYEN("SJ", "SJM", 744),
  SWAZILAND("SZ", "SWZ", 748),
  SWEDEN("SE", "SWE", 752),
  SWITZERLAND("CH", "CHE", 756),
  SYRIAN_ARAB_REPUBLIC("SY", "SYR", 760),
  TAIWAN("TW", "TWN", 158),
  TAJIKISTAN("TJ", "TJK", 762),
  TANZANIA("TZ", "TZA", 834),
  THAILAND("TH", "THA", 764),
  THE_DEMOCRATIC_REPUBLIC_OF_THE_CONGO("CD", "COD", 180),
  TIMOR_LESTE("TL", "TLS", 626),
  TOGO("TG", "TGO", 768),
  TOKELAU("TK", "TKL", 772),
  TONGA("TO", "TON", 776),
  TRINIDAD_AND_TOBAGO("TT", "TTO", 780),
  TUNISIA("TN", "TUN", 788),
  TURKEY("TR", "TUR", 792),
  TURKMENISTAN("TM", "TKM", 795),
  TURKS_AND_CAICOS_ISLANDS("TC", "TCA", 796),
  TUVALU("TV", "TUV", 798),
  UGANDA("UG", "UGA", 800),
  UKRAINE("UA", "UKR", 804),
  UNITED_ARAB_EMIRATES("AE", "ARE", 784),
  UNITED_KINGDOM("GB", "GBR", 826),
  UNITED_STATES("US", "USA", 840),
  UNITED_STATES_MINOR_OUTLYING_ISLANDS("UM", "UMI", 581),
  URUGUAY("UY", "URY", 858),
  US_VIRGIN_ISLANDS("VI", "VIR", 850),
  UZBEKISTAN("UZ", "UZB", 860),
  VANUATU("VU", "VUT", 548),
  VATICAN_CITY_STATE("VA", "VAT", 336),
  VENEZUELA("VE", "VEN", 862),
  VIET_NAM("VN", "VNM", 704),
  WALLIS_AND_FUTUNA("WF", "WLF", 876),
  WESTERN_SAHARA("EH", "ESH", 732),
  YEMEN("YE", "YEM", 887),
  ZAMBIA("ZM", "ZMB", 894),
  ZIMBABWE("ZW", "ZWE", 716);

  private final String alpha2Code;
  private final String alpha3Code;
  private final Integer numericCode;

  IsoCountry(String alpha2Code, String alpha3Code, Integer numericCode) {
    this.alpha2Code = requireNonNull(alpha2Code);
    this.alpha3Code = requireNonNull(alpha3Code);
    this.numericCode = numericCode;
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
   * @return a 3 numeric characters length string (may be null).
   */
  public Integer getNumericCode() {
    return numericCode;
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
