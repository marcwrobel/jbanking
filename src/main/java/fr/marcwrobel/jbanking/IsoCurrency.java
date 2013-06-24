/**
 * Copyright 2013 Marc Wrobel (marc.wrobel@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.marcwrobel.jbanking;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * The currencies having an ISO 4217 code.
 *
 * <p>
 * Please be advised that this list is current as of 2013/05/26. Up-to-date list can be found for free on the <a href="http://www.iso.org/iso/home/standards/currency_codes.htm">
 * International Organization for Standardization website</a>.
 * </p>
 *
 * @author Marc Wrobel
 * @see <a href="http://www.iso.org/iso/home/standards/currency_codes.htm">http://www.iso.org/iso/home/standards/currency_codes.htm</a>
 * @since 1.0
 */
public enum IsoCurrency {

    ADB_UNIT_OF_ACCOUNT("XUA", 965, null),
    AFGHANI("AFN", 971, 2, IsoCountry.AFGHANISTAN),
    ALGERIAN_DINAR("DZD", 12, 2, IsoCountry.ALGERIA),
    ARGENTINE_PESO("ARS", 32, 2, IsoCountry.ARGENTINA),
    ARMENIAN_DRAM("AMD", 51, 2, IsoCountry.ARMENIA),
    ARUBAN_FLORIN("AWG", 533, 2, IsoCountry.ARUBA),
    AUSTRALIAN_DOLLAR("AUD", 36, 2, IsoCountry.HEARD_ISLAND_AND_MCDONALD_ISLANDS, IsoCountry.TUVALU, IsoCountry.KIRIBATI, IsoCountry.AUSTRALIA, IsoCountry.NORFOLK_ISLAND, IsoCountry.NAURU, IsoCountry.CHRISTMAS_ISLAND, IsoCountry.COCOS_ISLANDS),
    AZERBAIJANIAN_MANAT("AZN", 944, 2, IsoCountry.AZERBAIJAN),
    BAHAMIAN_DOLLAR("BSD", 44, 2, IsoCountry.BAHAMAS),
    BAHRAINI_DINAR("BHD", 48, 3, IsoCountry.BAHRAIN),
    BAHT("THB", 764, 2, IsoCountry.THAILAND),
    BALBOA("PAB", 590, 2, IsoCountry.PANAMA),
    BARBADOS_DOLLAR("BBD", 52, 2, IsoCountry.BARBADOS),
    BELARUSSIAN_RUBLE("BYR", 974, 0, IsoCountry.BELARUS),
    BELIZE_DOLLAR("BZD", 84, 2, IsoCountry.BELIZE),
    BERMUDIAN_DOLLAR("BMD", 60, 2, IsoCountry.BERMUDA),
    BOLIVAR("VEF", 937, 2, IsoCountry.VENEZUELA),
    BOLIVIANO("BOB", 68, 2, IsoCountry.BOLIVIA),
    BRAZILIAN_REAL("BRL", 986, 2, IsoCountry.BRAZIL),
    BRUNEI_DOLLAR("BND", 96, 2, IsoCountry.BRUNEI_DARUSSALAM),
    BULGARIAN_LEV("BGN", 975, 2, IsoCountry.BULGARIA),
    BURUNDI_FRANC("BIF", 108, 0, IsoCountry.BURUNDI),
    CANADIAN_DOLLAR("CAD", 124, 2, IsoCountry.CANADA),
    CAPE_VERDE_ESCUDO("CVE", 132, 2, IsoCountry.CAPE_VERDE),
    CAYMAN_ISLANDS_DOLLAR("KYD", 136, 2, IsoCountry.CAYMAN_ISLANDS),
    CFA_FRANC_BCEAO("XOF", 952, 0, IsoCountry.MALI, IsoCountry.NIGER, IsoCountry.BENIN, IsoCountry.TOGO, IsoCountry.BURKINA_FASO, IsoCountry.SENEGAL, IsoCountry.COTE_D_IVOIRE, IsoCountry.GUINEA_BISSAU),
    CFA_FRANC_BEAC("XAF", 950, 0, IsoCountry.CHAD, IsoCountry.GABON, IsoCountry.EQUATORIAL_GUINEA, IsoCountry.CAMEROON, IsoCountry.CENTRAL_AFRICAN_REPUBLIC, IsoCountry.CONGO),
    CFP_FRANC("XPF", 953, 0, IsoCountry.FRENCH_POLYNESIA, IsoCountry.WALLIS_AND_FUTUNA, IsoCountry.NEW_CALEDONIA),
    CHILEAN_PESO("CLP", 152, 0, IsoCountry.CHILE),
    COLOMBIAN_PESO("COP", 170, 2, IsoCountry.COLOMBIA),
    COMORO_FRANC("KMF", 174, 0, IsoCountry.COMOROS),
    CONGOLESE_FRANC("CDF", 976, 2, IsoCountry.CONGO),
    CONVERTIBLE_MARK("BAM", 977, 2, IsoCountry.BOSNIA_AND_HERZEGOVINA),
    CORDOBA_ORO("NIO", 558, 2, IsoCountry.NICARAGUA),
    COSTA_RICAN_COLON("CRC", 188, 2, IsoCountry.COSTA_RICA),
    CROATIAN_KUNA("HRK", 191, 2, IsoCountry.CROATIA),
    CUBAN_PESO("CUP", 192, 2, IsoCountry.CUBA),
    CZECH_KORUNA("CZK", 203, 2, IsoCountry.CZECH_REPUBLIC),
    DALASI("GMD", 270, 2, IsoCountry.GAMBIA),
    DANISH_KRONE("DKK", 208, 2, IsoCountry.DENMARK, IsoCountry.FAROE_ISLANDS, IsoCountry.GREENLAND),
    DENAR("MKD", 807, 2, IsoCountry.MACEDONIA),
    DJIBOUTI_FRANC("DJF", 262, 0, IsoCountry.DJIBOUTI),
    DOBRA("STD", 678, 2, IsoCountry.SAO_TOME_AND_PRINCIPE),
    DOMINICAN_PESO("DOP", 214, 2, IsoCountry.DOMINICAN_REPUBLIC),
    DONG("VND", 704, 0, IsoCountry.VIET_NAM),
    EAST_CARIBBEAN_DOLLAR("XCD", 951, 2, IsoCountry.DOMINICA, IsoCountry.SAINT_VINCENT_AND_THE_GRENADINES, IsoCountry.MONTSERRAT, IsoCountry.ANTIGUA_AND_BARBUDA, IsoCountry.SAINT_LUCIA, IsoCountry.ANGUILLA, IsoCountry.SAINT_KITTS_AND_NEVIS, IsoCountry.GRENADA),
    EGYPTIAN_POUND("EGP", 818, 2, IsoCountry.EGYPT),
    EL_SALVADOR_COLON("SVC", 222, 2, IsoCountry.EL_SALVADOR),
    EMU_6("XBB", 956, null),
    ETHIOPIAN_BIRR("ETB", 230, 2, IsoCountry.ETHIOPIA),
    EUA_17("XBD", 958, null),
    EUA_9("XBC", 957, null),
    EURCO("XBA", 955, null),
    EURO("EUR", 978, 2, IsoCountry.PORTUGAL, IsoCountry.REUNION, IsoCountry.LUXEMBOURG, IsoCountry.ITALY, IsoCountry.SLOVAKIA, IsoCountry.SAINT_MARTIN, IsoCountry.ANDORRA, IsoCountry.MAYOTTE, IsoCountry.SAINT_BARTHELEMY, IsoCountry.ALAND_ISLANDS, IsoCountry.GUADELOUPE, IsoCountry.FRENCH_GUIANA, IsoCountry.SAINT_PIERRE_AND_MIQUELON, IsoCountry.GERMANY, IsoCountry.BELGIUM, IsoCountry.SPAIN, IsoCountry.SAN_MARINO, IsoCountry.FINLAND, IsoCountry.MALTA, IsoCountry.VATICAN_CITY_STATE, IsoCountry.MONACO, IsoCountry.GREECE, IsoCountry.MARTINIQUE, IsoCountry.FRENCH_SOUTHERN_TERRITORIES, IsoCountry.ESTONIA, IsoCountry.IRELAND, IsoCountry.NETHERLANDS, IsoCountry.MONTENEGRO, IsoCountry.SLOVENIA, IsoCountry.FRANCE, IsoCountry.AUSTRIA, IsoCountry.CYPRUS),
    FALKLAND_ISLANDS_POUND("FKP", 238, 2, IsoCountry.FALKLAND_ISLANDS),
    FIJI_DOLLAR("FJD", 242, 2, IsoCountry.FIJI),
    FORINT("HUF", 348, 2, IsoCountry.HUNGARY),
    GHANA_CEDI("GHS", 936, 2, IsoCountry.GHANA),
    GIBRALTAR_POUND("GIP", 292, 2, IsoCountry.GIBRALTAR),
    GOLD("XAU", 959, null),
    GOURDE("HTG", 332, 2, IsoCountry.HAITI),
    GUARANI("PYG", 600, 0, IsoCountry.PARAGUAY),
    GUINEA_FRANC("GNF", 324, 0, IsoCountry.GUINEA),
    GUYANA_DOLLAR("GYD", 328, 2, IsoCountry.GUYANA),
    HONG_KONG_DOLLAR("HKD", 344, 2, IsoCountry.HONG_KONG),
    HRYVNIA("UAH", 980, 2, IsoCountry.UKRAINE),
    ICELAND_KRONA("ISK", 352, 0, IsoCountry.ICELAND),
    INDIAN_RUPEE("INR", 356, 2, IsoCountry.INDIA, IsoCountry.BHUTAN),
    IRANIAN_RIAL("IRR", 364, 2, IsoCountry.IRAN),
    IRAQI_DINAR("IQD", 368, 3, IsoCountry.IRAQ),
    JAMAICAN_DOLLAR("JMD", 388, 2, IsoCountry.JAMAICA),
    JORDANIAN_DINAR("JOD", 400, 3, IsoCountry.JORDAN),
    KENYAN_SHILLING("KES", 404, 2, IsoCountry.KENYA),
    KINA("PGK", 598, 2, IsoCountry.PAPUA_NEW_GUINEA),
    KIP("LAK", 418, 2, IsoCountry.LAO_PEOPLES_DEMOCRATIC_REPUBLIC),
    KUWAITI_DINAR("KWD", 414, 3, IsoCountry.KUWAIT),
    KWACHA("MWK", 454, 2, IsoCountry.MALAWI),
    KWANZA("AOA", 973, 2, IsoCountry.ANGOLA),
    KYAT("MMK", 104, 2, IsoCountry.MYANMAR),
    LARI("GEL", 981, 2, IsoCountry.GEORGIA),
    LATVIAN_LATS("LVL", 428, 2, IsoCountry.LATVIA),
    LEBANESE_POUND("LBP", 422, 2, IsoCountry.LEBANON),
    LEK("ALL", 8, 2, IsoCountry.ALBANIA),
    LEMPIRA("HNL", 340, 2, IsoCountry.HONDURAS),
    LEONE("SLL", 694, 2, IsoCountry.SIERRA_LEONE),
    LIBERIAN_DOLLAR("LRD", 430, 2, IsoCountry.LIBERIA),
    LIBYAN_DINAR("LYD", 434, 3, IsoCountry.LIBYA),
    LILANGENI("SZL", 748, 2, IsoCountry.SWAZILAND),
    LITHUANIAN_LITAS("LTL", 440, 2, IsoCountry.LITHUANIA),
    LOTI("LSL", 426, 2, IsoCountry.LESOTHO),
    MALAGASY_ARIARY("MGA", 969, 2, IsoCountry.MADAGASCAR),
    MALAYSIAN_RINGGIT("MYR", 458, 2, IsoCountry.MALAYSIA),
    MAURITIUS_RUPEE("MUR", 480, 2, IsoCountry.MAURITIUS),
    MEXICAN_PESO("MXN", 484, 2, IsoCountry.MEXICO),
    MEXICAN_UNIDAD_DE_INVERSION("MXV", 979, 2, IsoCountry.MEXICO),
    MOLDOVAN_LEU("MDL", 498, 2, IsoCountry.MOLDOVA),
    MOROCCAN_DIRHAM("MAD", 504, 2, IsoCountry.WESTERN_SAHARA, IsoCountry.MOROCCO),
    MOZAMBIQUE_METICAL("MZN", 943, 2, IsoCountry.MOZAMBIQUE),
    MVDOL("BOV", 984, 2, IsoCountry.BOLIVIA),
    NAIRA("NGN", 566, 2, IsoCountry.NIGERIA),
    NAKFA("ERN", 232, 2, IsoCountry.ERITREA),
    NAMIBIA_DOLLAR("NAD", 516, 2, IsoCountry.NAMIBIA),
    NEPALESE_RUPEE("NPR", 524, 2, IsoCountry.NEPAL),
    NETHERLANDS_ANTILLEAN_GUILDER("ANG", 532, 2, IsoCountry.SINT_MAARTEN, IsoCountry.CURACAO),
    NEW_ISRAELI_SHEQEL("ILS", 376, 2, IsoCountry.ISRAEL),
    NEW_ROMANIAN_LEU("RON", 946, 2, IsoCountry.ROMANIA),
    NEW_TAIWAN_DOLLAR("TWD", 901, 2, IsoCountry.TAIWAN),
    NEW_ZEALAND_DOLLAR("NZD", 554, 2, IsoCountry.TOKELAU, IsoCountry.NEW_ZEALAND, IsoCountry.COOK_ISLANDS, IsoCountry.NIUE, IsoCountry.PITCAIRN),
    NGULTRUM("BTN", 64, 2, IsoCountry.BHUTAN),
    NO_UNIVERSAL_CURRENCY("", null, null, IsoCountry.PALESTINE, IsoCountry.ANTARCTICA, IsoCountry.SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS),
    NORTH_KOREAN_WON("KPW", 408, 2, IsoCountry.NORTH_KOREA),
    NORWEGIAN_KRONE("NOK", 578, 2, IsoCountry.NORWAY, IsoCountry.SVALBARD_AND_JAN_MAYEN, IsoCountry.BOUVET_ISLAND),
    NUEVO_SOL("PEN", 604, 2, IsoCountry.PERU),
    NO_CURRENCY("XXX", 999, null),
    OUGUIYA("MRO", 478, 2, IsoCountry.MAURITANIA),
    PAKISTAN_RUPEE("PKR", 586, 2, IsoCountry.PAKISTAN),
    PALLADIUM("XPD", 964, null),
    PATACA("MOP", 446, 2, IsoCountry.MACAO),
    PAANGA("TOP", 776, 2, IsoCountry.TONGA),
    PESO_CONVERTIBLE("CUC", 931, 2, IsoCountry.CUBA),
    PESO_URUGUAYO("UYU", 858, 2, IsoCountry.URUGUAY),
    PHILIPPINE_PESO("PHP", 608, 2, IsoCountry.PHILIPPINES),
    PLATINUM("XPT", 962, null),
    POUND_STERLING("GBP", 826, 2, IsoCountry.UNITED_KINGDOM, IsoCountry.ISLE_OF_MAN, IsoCountry.JERSEY, IsoCountry.GUERNSEY),
    PULA("BWP", 72, 2, IsoCountry.BOTSWANA),
    QATARI_RIAL("QAR", 634, 2, IsoCountry.QATAR),
    QUETZAL("GTQ", 320, 2, IsoCountry.GUATEMALA),
    RAND("ZAR", 710, 2, IsoCountry.NAMIBIA, IsoCountry.LESOTHO, IsoCountry.SOUTH_AFRICA),
    RIAL_OMANI("OMR", 512, 3, IsoCountry.OMAN),
    RIEL("KHR", 116, 2, IsoCountry.CAMBODIA),
    RUFIYAA("MVR", 462, 2, IsoCountry.MALDIVES),
    RUPIAH("IDR", 360, 2, IsoCountry.INDONESIA),
    RUSSIAN_RUBLE("RUB", 643, 2, IsoCountry.RUSSIAN_FEDERATION),
    RWANDA_FRANC("RWF", 646, 0, IsoCountry.RWANDA),
    SAINT_HELENA_POUND("SHP", 654, 2, IsoCountry.SAINT_HELENA),
    SAUDI_RIYAL("SAR", 682, 2, IsoCountry.SAUDI_ARABIA),
    SDR("XDR", 960, null),
    SERBIAN_DINAR("RSD", 941, 2, IsoCountry.SERBIA),
    SEYCHELLES_RUPEE("SCR", 690, 2, IsoCountry.SEYCHELLES),
    SILVER("XAG", 961, null),
    SINGAPORE_DOLLAR("SGD", 702, 2, IsoCountry.SINGAPORE),
    SOLOMON_ISLANDS_DOLLAR("SBD", 90, 2, IsoCountry.SOLOMON_ISLANDS),
    SOM("KGS", 417, 2, IsoCountry.KYRGYZSTAN),
    SOMALI_SHILLING("SOS", 706, 2, IsoCountry.SOMALIA),
    SOMONI("TJS", 972, 2, IsoCountry.TAJIKISTAN),
    SOUTH_SUDANESE_POUND("SSP", 728, 2, IsoCountry.SOUTH_SUDAN),
    SRI_LANKA_RUPEE("LKR", 144, 2, IsoCountry.SRI_LANKA),
    SUCRE("XSU", 994, null),
    SUDANESE_POUND("SDG", 938, 2, IsoCountry.SUDAN),
    SURINAM_DOLLAR("SRD", 968, 2, IsoCountry.SURINAME),
    SWEDISH_KRONA("SEK", 752, 2, IsoCountry.SWEDEN),
    SWISS_FRANC("CHF", 756, 2, IsoCountry.LIECHTENSTEIN, IsoCountry.SWITZERLAND),
    SYRIAN_POUND("SYP", 760, 2, IsoCountry.SYRIAN_ARAB_REPUBLIC),
    TAKA("BDT", 50, 2, IsoCountry.BANGLADESH),
    TALA("WST", 882, 2, IsoCountry.SAMOA),
    TANZANIAN_SHILLING("TZS", 834, 2, IsoCountry.TANZANIA),
    TENGE("KZT", 398, 2, IsoCountry.KAZAKHSTAN),
    TRINIDAD_AND_TOBAGO_DOLLAR("TTD", 780, 2, IsoCountry.TRINIDAD_AND_TOBAGO),
    TUGRIK("MNT", 496, 2, IsoCountry.MONGOLIA),
    TUNISIAN_DINAR("TND", 788, 3, IsoCountry.TUNISIA),
    TURKISH_LIRA("TRY", 949, 2, IsoCountry.TURKEY),
    TURKMENISTAN_NEW_MANAT("TMT", 934, 2, IsoCountry.TURKMENISTAN),
    TESTING_CODE("XTS", 963, null),
    UAE_DIRHAM("AED", 784, 2, IsoCountry.UNITED_ARAB_EMIRATES),
    UGANDA_SHILLING("UGX", 800, 0, IsoCountry.UGANDA),
    UIC_FRANC("XFU", null, null),
    UNIDAD_DE_VALOR_REAL("COU", 970, 2, IsoCountry.COLOMBIA),
    UNIDADES_DE_FOMENTO("CLF", 990, 0, IsoCountry.CHILE),
    URUGUAY_PESO_EN_UNIDADES_INDEXADAS("UYI", 940, 0, IsoCountry.URUGUAY),
    US_DOLLAR("USD", 840, 2, IsoCountry.BRITISH_VIRGIN_ISLANDS, IsoCountry.TIMOR_LESTE, IsoCountry.NORTHERN_MARIANA_ISLANDS, IsoCountry.MICRONESIA, IsoCountry.PALAU, IsoCountry.GUAM, IsoCountry.HAITI, IsoCountry.ECUADOR, IsoCountry.UNITED_STATES_MINOR_OUTLYING_ISLANDS, IsoCountry.BRITISH_INDIAN_OCEAN_TERRITORY, IsoCountry.US_VIRGIN_ISLANDS, IsoCountry.BONAIRE, IsoCountry.EL_SALVADOR, IsoCountry.UNITED_STATES, IsoCountry.PANAMA, IsoCountry.MARSHALL_ISLANDS, IsoCountry.PUERTO_RICO, IsoCountry.AMERICAN_SAMOA, IsoCountry.TURKS_AND_CAICOS_ISLANDS),
    US_DOLLAR_NEXT_DAY("USN", 997, 2, IsoCountry.UNITED_STATES),
    US_DOLLAR_SAME_DAY("USS", 998, 2, IsoCountry.UNITED_STATES),
    UZBEKISTAN_SUM("UZS", 860, 2, IsoCountry.UZBEKISTAN),
    VATU("VUV", 548, 0, IsoCountry.VANUATU),
    WIR_EURO("CHE", 947, 2, IsoCountry.SWITZERLAND),
    WIR_FRANC("CHW", 948, 2, IsoCountry.SWITZERLAND),
    WON("KRW", 410, 0, IsoCountry.SOUTH_KOREA),
    YEMENI_RIAL("YER", 886, 2, IsoCountry.YEMEN),
    YEN("JPY", 392, 0, IsoCountry.JAPAN),
    YUAN_RENMINBI("CNY", 156, 2, IsoCountry.CHINA),
    ZAMBIAN_KWACHA("ZMW", 967, 2, IsoCountry.ZAMBIA),
    ZIMBABWE_DOLLAR("ZWL", 932, 2, IsoCountry.ZIMBABWE),
    ZLOTY("PLN", 985, 2, IsoCountry.POLAND);

    private static final int MIN_NUMERIC_CODE = 1;
    private static final int MAX_NUMERIC_CODE = 999;

    private final String alphabeticCode;
    private final Integer numericCode;
    private final Integer minorUnit;
    private final Set<IsoCountry> countries;

    private IsoCurrency(String alphabeticCode, Integer numericCode, Integer minorUnit, IsoCountry... countries) {
        this.alphabeticCode = alphabeticCode;
        this.numericCode = numericCode;
        this.minorUnit = minorUnit;
        this.countries = countries.length > 0 ? EnumSet.copyOf(Arrays.asList(countries)) : EnumSet.noneOf(IsoCountry.class);
    }

    /**
     * <p>Returns this currency ISO 4217 alphabetical code.</p>
     *
     * @return a non null and 3 characters length string
     */
    public String getAlphabeticCode() {
        return alphabeticCode;
    }

    /**
     * <p>Returns this currency ISO 4217 numeric code.</p>
     *
     * @return an Integer or null if this country is {@link #UIC_FRANC} or {@link #NO_UNIVERSAL_CURRENCY}
     */
    public Integer getNumericCode() {
        return numericCode;
    }

    /**
     * <p>Returns this currency minor unit, if applicable.</p>
     *
     * @return an Integer or null if this not applicable
     */
    public Integer getMinorUnit() {
        return minorUnit;
    }

    /**
     * <p>Returns the countries that are using this currency.</p>
     *
     * @return a non null but may be empty set of countries
     */
    public Set<IsoCountry> getCountries() {
        return countries;
    }

    /**
     * <p>Translate the given ISO 4217 alphabetical code to an IsoCurrency.</p>
     *
     * <p>This method is not case sensitive not spaces sensitive.</p>
     *
     * @param code A non null String.
     * @return the currency having the given ISO 4217 alphabetical code, or null if it does not exist
     */
    public static IsoCurrency fromAlphabeticCode(String code) {
        String cleanedCode = (code == null ? null : code.toUpperCase());

        for (IsoCurrency currency : values()) {
            if (currency.getAlphabeticCode().equals(cleanedCode)) {
                return currency;
            }
        }

        return null;
    }

    /**
     * <p>Translate the given ISO 4217 numeric code to an IsoCurrency.</p>
     *
     * <p>This method allows null. In this case {@link #NO_UNIVERSAL_CURRENCY} is always returned (and not {@link #UIC_FRANC}).</p>
     *
     * @param code An Integer, null or not.
     * @return the currency having the given ISO 4217 numerical code ({@link #NO_UNIVERSAL_CURRENCY} if the given code is null), or null if it does not exist
     */
    public static IsoCurrency fromNumericCode(Integer code) {
        if (code == null) {
            return NO_UNIVERSAL_CURRENCY;
        }

        if (code < MIN_NUMERIC_CODE || code > MAX_NUMERIC_CODE) {
            return null;
        }

        for (IsoCurrency currency : values()) {
            if (code.equals(currency.getNumericCode())) {
                return currency;
            }
        }

        return null;
    }
}
