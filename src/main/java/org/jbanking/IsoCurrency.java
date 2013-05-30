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
package org.jbanking;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

import static org.jbanking.IsoCountry.*;

/**
 * <p>The currencies having an ISO 4217 code.</p>
 *
 * <p>Please be advised that this list is current as of 2013/05/26. Up-to-date list can be found for free on the <a href="http://www.iso.org/iso/home/standards/currency_codes.htm">International
 * Organization for Standardization website</a>.</p>
 */
public enum IsoCurrency {

    ADB_UNIT_OF_ACCOUNT("XUA", 965, null),
    AFGHANI("AFN", 971, 2, AFGHANISTAN),
    ALGERIAN_DINAR("DZD", 12, 2, ALGERIA),
    ARGENTINE_PESO("ARS", 32, 2, ARGENTINA),
    ARMENIAN_DRAM("AMD", 51, 2, ARMENIA),
    ARUBAN_FLORIN("AWG", 533, 2, ARUBA),
    AUSTRALIAN_DOLLAR("AUD", 36, 2, HEARD_ISLAND_AND_MCDONALD_ISLANDS, TUVALU, KIRIBATI, AUSTRALIA, NORFOLK_ISLAND, NAURU, CHRISTMAS_ISLAND, COCOS_ISLANDS),
    AZERBAIJANIAN_MANAT("AZN", 944, 2, AZERBAIJAN),
    BAHAMIAN_DOLLAR("BSD", 44, 2, BAHAMAS),
    BAHRAINI_DINAR("BHD", 48, 3, BAHRAIN),
    BAHT("THB", 764, 2, THAILAND),
    BALBOA("PAB", 590, 2, PANAMA),
    BARBADOS_DOLLAR("BBD", 52, 2, BARBADOS),
    BELARUSSIAN_RUBLE("BYR", 974, 0, BELARUS),
    BELIZE_DOLLAR("BZD", 84, 2, BELIZE),
    BERMUDIAN_DOLLAR("BMD", 60, 2, BERMUDA),
    BOLIVAR("VEF", 937, 2, VENEZUELA),
    BOLIVIANO("BOB", 68, 2, BOLIVIA),
    BRAZILIAN_REAL("BRL", 986, 2, BRAZIL),
    BRUNEI_DOLLAR("BND", 96, 2, BRUNEI_DARUSSALAM),
    BULGARIAN_LEV("BGN", 975, 2, BULGARIA),
    BURUNDI_FRANC("BIF", 108, 0, BURUNDI),
    CANADIAN_DOLLAR("CAD", 124, 2, CANADA),
    CAPE_VERDE_ESCUDO("CVE", 132, 2, CAPE_VERDE),
    CAYMAN_ISLANDS_DOLLAR("KYD", 136, 2, CAYMAN_ISLANDS),
    CFA_FRANC_BCEAO("XOF", 952, 0, MALI, NIGER, BENIN, TOGO, BURKINA_FASO, SENEGAL, COTE_D_IVOIRE, GUINEA_BISSAU),
    CFA_FRANC_BEAC("XAF", 950, 0, CHAD, GABON, EQUATORIAL_GUINEA, CAMEROON, CENTRAL_AFRICAN_REPUBLIC, CONGO),
    CFP_FRANC("XPF", 953, 0, FRENCH_POLYNESIA, WALLIS_AND_FUTUNA, NEW_CALEDONIA),
    CHILEAN_PESO("CLP", 152, 0, CHILE),
    COLOMBIAN_PESO("COP", 170, 2, COLOMBIA),
    COMORO_FRANC("KMF", 174, 0, COMOROS),
    CONGOLESE_FRANC("CDF", 976, 2, CONGO),
    CONVERTIBLE_MARK("BAM", 977, 2, BOSNIA_AND_HERZEGOVINA),
    CORDOBA_ORO("NIO", 558, 2, NICARAGUA),
    COSTA_RICAN_COLON("CRC", 188, 2, COSTA_RICA),
    CROATIAN_KUNA("HRK", 191, 2, CROATIA),
    CUBAN_PESO("CUP", 192, 2, CUBA),
    CZECH_KORUNA("CZK", 203, 2, CZECH_REPUBLIC),
    DALASI("GMD", 270, 2, GAMBIA),
    DANISH_KRONE("DKK", 208, 2, DENMARK, FAROE_ISLANDS, GREENLAND),
    DENAR("MKD", 807, 2, MACEDONIA),
    DJIBOUTI_FRANC("DJF", 262, 0, DJIBOUTI),
    DOBRA("STD", 678, 2, SAO_TOME_AND_PRINCIPE),
    DOMINICAN_PESO("DOP", 214, 2, DOMINICAN_REPUBLIC),
    DONG("VND", 704, 0, VIET_NAM),
    EAST_CARIBBEAN_DOLLAR("XCD", 951, 2, DOMINICA, SAINT_VINCENT_AND_THE_GRENADINES, MONTSERRAT, ANTIGUA_AND_BARBUDA, SAINT_LUCIA, ANGUILLA, SAINT_KITTS_AND_NEVIS, GRENADA),
    EGYPTIAN_POUND("EGP", 818, 2, EGYPT),
    EL_SALVADOR_COLON("SVC", 222, 2, EL_SALVADOR),
    EMU_6("XBB", 956, null),
    ETHIOPIAN_BIRR("ETB", 230, 2, ETHIOPIA),
    EUA_17("XBD", 958, null),
    EUA_9("XBC", 957, null),
    EURCO("XBA", 955, null),
    EURO("EUR", 978, 2, PORTUGAL, REUNION, LUXEMBOURG, ITALY, SLOVAKIA, SAINT_MARTIN, ANDORRA, MAYOTTE, SAINT_BARTHELEMY, ALAND_ISLANDS, GUADELOUPE, FRENCH_GUIANA, SAINT_PIERRE_AND_MIQUELON, GERMANY, BELGIUM, SPAIN, SAN_MARINO, FINLAND, MALTA, VATICAN_CITY_STATE, MONACO, GREECE, MARTINIQUE, FRENCH_SOUTHERN_TERRITORIES, ESTONIA, IRELAND, NETHERLANDS, MONTENEGRO, SLOVENIA, FRANCE, AUSTRIA, CYPRUS),
    FALKLAND_ISLANDS_POUND("FKP", 238, 2, FALKLAND_ISLANDS),
    FIJI_DOLLAR("FJD", 242, 2, FIJI),
    FORINT("HUF", 348, 2, HUNGARY),
    GHANA_CEDI("GHS", 936, 2, GHANA),
    GIBRALTAR_POUND("GIP", 292, 2, GIBRALTAR),
    GOLD("XAU", 959, null),
    GOURDE("HTG", 332, 2, HAITI),
    GUARANI("PYG", 600, 0, PARAGUAY),
    GUINEA_FRANC("GNF", 324, 0, GUINEA),
    GUYANA_DOLLAR("GYD", 328, 2, GUYANA),
    HONG_KONG_DOLLAR("HKD", 344, 2, HONG_KONG),
    HRYVNIA("UAH", 980, 2, UKRAINE),
    ICELAND_KRONA("ISK", 352, 0, ICELAND),
    INDIAN_RUPEE("INR", 356, 2, INDIA, BHUTAN),
    IRANIAN_RIAL("IRR", 364, 2, IRAN),
    IRAQI_DINAR("IQD", 368, 3, IRAQ),
    JAMAICAN_DOLLAR("JMD", 388, 2, JAMAICA),
    JORDANIAN_DINAR("JOD", 400, 3, JORDAN),
    KENYAN_SHILLING("KES", 404, 2, KENYA),
    KINA("PGK", 598, 2, PAPUA_NEW_GUINEA),
    KIP("LAK", 418, 2, LAO_PEOPLES_DEMOCRATIC_REPUBLIC),
    KUWAITI_DINAR("KWD", 414, 3, KUWAIT),
    KWACHA("MWK", 454, 2, MALAWI),
    KWANZA("AOA", 973, 2, ANGOLA),
    KYAT("MMK", 104, 2, MYANMAR),
    LARI("GEL", 981, 2, GEORGIA),
    LATVIAN_LATS("LVL", 428, 2, LATVIA),
    LEBANESE_POUND("LBP", 422, 2, LEBANON),
    LEK("ALL", 8, 2, ALBANIA),
    LEMPIRA("HNL", 340, 2, HONDURAS),
    LEONE("SLL", 694, 2, SIERRA_LEONE),
    LIBERIAN_DOLLAR("LRD", 430, 2, LIBERIA),
    LIBYAN_DINAR("LYD", 434, 3, LIBYA),
    LILANGENI("SZL", 748, 2, SWAZILAND),
    LITHUANIAN_LITAS("LTL", 440, 2, LITHUANIA),
    LOTI("LSL", 426, 2, LESOTHO),
    MALAGASY_ARIARY("MGA", 969, 2, MADAGASCAR),
    MALAYSIAN_RINGGIT("MYR", 458, 2, MALAYSIA),
    MAURITIUS_RUPEE("MUR", 480, 2, MAURITIUS),
    MEXICAN_PESO("MXN", 484, 2, MEXICO),
    MEXICAN_UNIDAD_DE_INVERSION("MXV", 979, 2, MEXICO),
    MOLDOVAN_LEU("MDL", 498, 2, MOLDOVA),
    MOROCCAN_DIRHAM("MAD", 504, 2, WESTERN_SAHARA, MOROCCO),
    MOZAMBIQUE_METICAL("MZN", 943, 2, MOZAMBIQUE),
    MVDOL("BOV", 984, 2, BOLIVIA),
    NAIRA("NGN", 566, 2, NIGERIA),
    NAKFA("ERN", 232, 2, ERITREA),
    NAMIBIA_DOLLAR("NAD", 516, 2, NAMIBIA),
    NEPALESE_RUPEE("NPR", 524, 2, NEPAL),
    NETHERLANDS_ANTILLEAN_GUILDER("ANG", 532, 2, SINT_MAARTEN, CURACAO),
    NEW_ISRAELI_SHEQEL("ILS", 376, 2, ISRAEL),
    NEW_ROMANIAN_LEU("RON", 946, 2, ROMANIA),
    NEW_TAIWAN_DOLLAR("TWD", 901, 2, TAIWAN),
    NEW_ZEALAND_DOLLAR("NZD", 554, 2, TOKELAU, NEW_ZEALAND, COOK_ISLANDS, NIUE, PITCAIRN),
    NGULTRUM("BTN", 64, 2, BHUTAN),
    NO_UNIVERSAL_CURRENCY("", null, null, PALESTINE, ANTARCTICA, SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS),
    NORTH_KOREAN_WON("KPW", 408, 2, NORTH_KOREA),
    NORWEGIAN_KRONE("NOK", 578, 2, NORWAY, SVALBARD_AND_JAN_MAYEN, BOUVET_ISLAND),
    NUEVO_SOL("PEN", 604, 2, PERU),
    NO_CURRENCY("XXX", 999, null),
    OUGUIYA("MRO", 478, 2, MAURITANIA),
    PAKISTAN_RUPEE("PKR", 586, 2, PAKISTAN),
    PALLADIUM("XPD", 964, null),
    PATACA("MOP", 446, 2, MACAO),
    PAANGA("TOP", 776, 2, TONGA),
    PESO_CONVERTIBLE("CUC", 931, 2, CUBA),
    PESO_URUGUAYO("UYU", 858, 2, URUGUAY),
    PHILIPPINE_PESO("PHP", 608, 2, PHILIPPINES),
    PLATINUM("XPT", 962, null),
    POUND_STERLING("GBP", 826, 2, UNITED_KINGDOM, ISLE_OF_MAN, JERSEY, GUERNSEY),
    PULA("BWP", 72, 2, BOTSWANA),
    QATARI_RIAL("QAR", 634, 2, QATAR),
    QUETZAL("GTQ", 320, 2, GUATEMALA),
    RAND("ZAR", 710, 2, NAMIBIA, LESOTHO, SOUTH_AFRICA),
    RIAL_OMANI("OMR", 512, 3, OMAN),
    RIEL("KHR", 116, 2, CAMBODIA),
    RUFIYAA("MVR", 462, 2, MALDIVES),
    RUPIAH("IDR", 360, 2, INDONESIA),
    RUSSIAN_RUBLE("RUB", 643, 2, RUSSIAN_FEDERATION),
    RWANDA_FRANC("RWF", 646, 0, RWANDA),
    SAINT_HELENA_POUND("SHP", 654, 2, SAINT_HELENA),
    SAUDI_RIYAL("SAR", 682, 2, SAUDI_ARABIA),
    SDR("XDR", 960, null),
    SERBIAN_DINAR("RSD", 941, 2, SERBIA),
    SEYCHELLES_RUPEE("SCR", 690, 2, SEYCHELLES),
    SILVER("XAG", 961, null),
    SINGAPORE_DOLLAR("SGD", 702, 2, SINGAPORE),
    SOLOMON_ISLANDS_DOLLAR("SBD", 90, 2, SOLOMON_ISLANDS),
    SOM("KGS", 417, 2, KYRGYZSTAN),
    SOMALI_SHILLING("SOS", 706, 2, SOMALIA),
    SOMONI("TJS", 972, 2, TAJIKISTAN),
    SOUTH_SUDANESE_POUND("SSP", 728, 2, SOUTH_SUDAN),
    SRI_LANKA_RUPEE("LKR", 144, 2, SRI_LANKA),
    SUCRE("XSU", 994, null),
    SUDANESE_POUND("SDG", 938, 2, SUDAN),
    SURINAM_DOLLAR("SRD", 968, 2, SURINAME),
    SWEDISH_KRONA("SEK", 752, 2, SWEDEN),
    SWISS_FRANC("CHF", 756, 2, LIECHTENSTEIN, SWITZERLAND),
    SYRIAN_POUND("SYP", 760, 2, SYRIAN_ARAB_REPUBLIC),
    TAKA("BDT", 50, 2, BANGLADESH),
    TALA("WST", 882, 2, SAMOA),
    TANZANIAN_SHILLING("TZS", 834, 2, TANZANIA),
    TENGE("KZT", 398, 2, KAZAKHSTAN),
    TRINIDAD_AND_TOBAGO_DOLLAR("TTD", 780, 2, TRINIDAD_AND_TOBAGO),
    TUGRIK("MNT", 496, 2, MONGOLIA),
    TUNISIAN_DINAR("TND", 788, 3, TUNISIA),
    TURKISH_LIRA("TRY", 949, 2, TURKEY),
    TURKMENISTAN_NEW_MANAT("TMT", 934, 2, TURKMENISTAN),
    TESTING_CODE("XTS", 963, null),
    UAE_DIRHAM("AED", 784, 2, UNITED_ARAB_EMIRATES),
    UGANDA_SHILLING("UGX", 800, 0, UGANDA),
    UIC_FRANC("XFU", null, null),
    UNIDAD_DE_VALOR_REAL("COU", 970, 2, COLOMBIA),
    UNIDADES_DE_FOMENTO("CLF", 990, 0, CHILE),
    URUGUAY_PESO_EN_UNIDADES_INDEXADAS("UYI", 940, 0, URUGUAY),
    US_DOLLAR("USD", 840, 2, BRITISH_VIRGIN_ISLANDS, TIMOR_LESTE, NORTHERN_MARIANA_ISLANDS, MICRONESIA, PALAU, GUAM, HAITI, ECUADOR, UNITED_STATES_MINOR_OUTLYING_ISLANDS, BRITISH_INDIAN_OCEAN_TERRITORY, US_VIRGIN_ISLANDS, BONAIRE, EL_SALVADOR, UNITED_STATES, PANAMA, MARSHALL_ISLANDS, PUERTO_RICO, AMERICAN_SAMOA, TURKS_AND_CAICOS_ISLANDS),
    US_DOLLAR_NEXT_DAY("USN", 997, 2, UNITED_STATES),
    US_DOLLAR_SAME_DAY("USS", 998, 2, UNITED_STATES),
    UZBEKISTAN_SUM("UZS", 860, 2, UZBEKISTAN),
    VATU("VUV", 548, 0, VANUATU),
    WIR_EURO("CHE", 947, 2, SWITZERLAND),
    WIR_FRANC("CHW", 948, 2, SWITZERLAND),
    WON("KRW", 410, 0, SOUTH_KOREA),
    YEMENI_RIAL("YER", 886, 2, YEMEN),
    YEN("JPY", 392, 0, JAPAN),
    YUAN_RENMINBI("CNY", 156, 2, CHINA),
    ZAMBIAN_KWACHA("ZMW", 967, 2, ZAMBIA),
    ZIMBABWE_DOLLAR("ZWL", 932, 2, ZIMBABWE),
    ZLOTY("PLN", 985, 2, POLAND);

    private final String alphaCode;
    private final Integer numericCode;
    private final Integer minorUnit;
    private final Set<IsoCountry> countries;

    private IsoCurrency(String alphaCode, Integer numericCode, Integer minorUnit, IsoCountry... countries) {
        this.alphaCode = alphaCode;
        this.numericCode = numericCode;
        this.minorUnit = minorUnit;
        this.countries = countries.length > 0 ? EnumSet.copyOf(Arrays.asList(countries)) : EnumSet.noneOf(IsoCountry.class);
    }

    /**
     * <p>Returns this currency ISO 4217 alphabetical code.</p>
     *
     * @return a non null and 3 characters length string
     */
    public String getAlphaCode() {
        return alphaCode;
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
    public static IsoCurrency fromAlphaCode(String code) {
        String cleanedCode = (code == null ? null : code.toUpperCase().trim());

        for (IsoCurrency currency : values()) {
            if (currency.getAlphaCode().equals(cleanedCode)) {
                return currency;
            }
        }

        return null;
    }

    /**
     * <p>Translate the given ISO 4217 alphabetical code to an IsoCurrency.</p>
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

        if (code < 0 || code > 999) {
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
