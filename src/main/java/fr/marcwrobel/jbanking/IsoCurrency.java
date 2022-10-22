package fr.marcwrobel.jbanking;

import static fr.marcwrobel.jbanking.IsoCountry.*;
import static fr.marcwrobel.jbanking.IsoCurrency.Category.*;
import static fr.marcwrobel.jbanking.internal.Normalizer.trimUpperCase;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

import fr.marcwrobel.jbanking.internal.LastVerification;
import java.util.*;

/**
 * The currencies having an ISO 4217 code.
 *
 * <p>
 * Note that enum entries are named after the ISO 4217 alphabetic code. This choice has been made in version 3.0.0 of jbanking
 * in order to :
 *
 * <ul>
 * <li>reduce breaking changes in future versions (names change more often than codes),
 * <li>make this enum easier to serialize (to JSON, in database...),
 * <li>prevent accidental duplicates.
 * </ul>
 *
 * <p>
 * Last verification date of this list can be seen in the {@code @LastVerification} value.
 *
 * <p>
 * Usage:
 *
 * <pre>
 * // Get ISO currency information
 * IsoCurrency currency = IsoCurrency.fromAlphabeticCode("EUR").get();
 * Assertion.assertEquals(978, currency.getNumericCode());
 * Assertion.assertEquals(2, currency.getMinorUnit().get());
 * Assertion.assertEquals(NATIONAL, currency.getCategory());
 * Assertion.assertTrue(currency.getCountries().contains(FR));
 * </pre>
 *
 * @see <a href="https://www.currency-iso.org/en/home/tables/table-a1.html">ISO 4217 - Currency Code Maintenance - Current
 *      Currency &amp; Funds - List One</a>
 * @since 1.0
 */
@LastVerification("2022-10-22")
public enum IsoCurrency {
  /**
   * United Arab Emirates dirham.
   *
   * @see <a href="https://wikipedia.org/wiki/United_Arab_Emirates_dirham">wikipedia.org</a>
   */
  AED(784, 2, AE),

  /**
   * Afghan afghani.
   *
   * @see <a href="https://wikipedia.org/wiki/Afghan_afghani">wikipedia.org</a>
   */
  AFN(971, 2, AF),

  /**
   * Albanian lek.
   *
   * @see <a href="https://wikipedia.org/wiki/Albanian_lek">wikipedia.org</a>
   */
  ALL(8, 2, AL),

  /**
   * Armenian dram.
   *
   * @see <a href="https://wikipedia.org/wiki/Armenian_dram">wikipedia.org</a>
   */
  AMD(51, 2, AM),

  /**
   * Netherlands Antillean guilder.
   *
   * @see <a href="https://wikipedia.org/wiki/Netherlands_Antillean_guilder">wikipedia.org</a>
   */
  ANG(532, 2, SX, CW),

  /**
   * Angolan kwanza.
   *
   * @see <a href="https://wikipedia.org/wiki/Angolan_kwanza">wikipedia.org</a>
   */
  AOA(973, 2, AO),

  /**
   * Argentine peso.
   *
   * @see <a href="https://wikipedia.org/wiki/Argentine_peso">wikipedia.org</a>
   */
  ARS(32, 2, AR),

  /**
   * Australian dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Australian_dollar">wikipedia.org</a>
   */
  AUD(36, 2, HM, TV, KI, AU, NF, NR, CX, CC),

  /**
   * Aruban florin.
   *
   * @see <a href="https://wikipedia.org/wiki/Aruban_florin">wikipedia.org</a>
   */
  AWG(533, 2, AW),

  /**
   * Azerbaijani manat.
   *
   * @see <a href="https://wikipedia.org/wiki/Azerbaijani_manat">wikipedia.org</a>
   */
  AZN(944, 2, AZ),

  /**
   * Bosnia and Herzegovina convertible mark.
   *
   * @see <a href="https://wikipedia.org/wiki/Bosnia_and_Herzegovina_convertible_mark">wikipedia.org</a>
   */
  BAM(977, 2, BA),

  /**
   * Barbadian dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Barbadian_dollar">wikipedia.org</a>
   */
  BBD(52, 2, BB),

  /**
   * Bangladeshi taka.
   *
   * @see <a href="https://wikipedia.org/wiki/Bangladeshi_taka">wikipedia.org</a>
   */
  BDT(50, 2, BD),

  /**
   * Bulgarian lev.
   *
   * @see <a href="https://wikipedia.org/wiki/Bulgarian_lev">wikipedia.org</a>
   */
  BGN(975, 2, BG),

  /**
   * Bahraini dinar.
   *
   * @see <a href="https://wikipedia.org/wiki/Bahraini_dinar">wikipedia.org</a>
   */
  BHD(48, 3, BH),

  /**
   * Burundian franc.
   *
   * @see <a href="https://wikipedia.org/wiki/Burundian_franc">wikipedia.org</a>
   */
  BIF(108, 0, BI),

  /**
   * Bermudian dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Bermudian_dollar">wikipedia.org</a>
   */
  BMD(60, 2, BM),

  /**
   * Brunei dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Brunei_dollar">wikipedia.org</a>
   */
  BND(96, 2, BN),

  /**
   * Bolivian boliviano.
   *
   * @see <a href="https://wikipedia.org/wiki/Bolivian_boliviano">wikipedia.org</a>
   */
  BOB(68, 2, BO),

  /**
   * Bolivian Mvdol (funds code).
   *
   * <p>
   * For indexation purposes and denomination of certain financial instruments (e.g. treasury bills). The Mvdol is set daily by
   * the Central Bank of Bolivia based on the official USD/BOB rate.
   *
   * @see <a href="https://www.tradinghours.com/currencies/bov-bolivian-mvdol">tradinghours.com</a>
   */
  BOV(984, 2, FUND, BO),

  /**
   * Brazilian real.
   *
   * @see <a href="https://wikipedia.org/wiki/Brazilian_real">wikipedia.org</a>
   */
  BRL(986, 2, BR),

  /**
   * Bahamian dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Bahamian_dollar">wikipedia.org</a>
   */
  BSD(44, 2, BS),

  /**
   * Bhutanese ngultrum.
   *
   * @see <a href="https://wikipedia.org/wiki/Bhutanese_ngultrum">wikipedia.org</a>
   */
  BTN(64, 2, BT),

  /**
   * Botswana pula.
   *
   * @see <a href="https://wikipedia.org/wiki/Botswana_pula">wikipedia.org</a>
   */
  BWP(72, 2, BW),

  /**
   * Belarusian ruble.
   *
   * @see <a href="https://wikipedia.org/wiki/Belarusian_ruble">wikipedia.org</a>
   */
  BYN(933, 2, BY),

  /**
   * Belize dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Belize_dollar">wikipedia.org</a>
   */
  BZD(84, 2, BZ),

  /**
   * Canadian dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Canadian_dollar">wikipedia.org</a>
   */
  CAD(124, 2, CA),

  /**
   * Congolese franc.
   *
   * @see <a href="https://wikipedia.org/wiki/Congolese_franc">wikipedia.org</a>
   */
  CDF(976, 2, CD),

  /**
   * WIR euro (complementary currency).
   *
   * <p>
   * WIR Bank for use with the EFTPOS system with their own WIR-card and the Electronic Banking Services.
   *
   * @see <a href="https://wikipedia.org/wiki/ISO_4217">wikipedia.org</a>
   */
  CHE(947, 2, FUND, CH),

  /**
   * Swiss franc.
   *
   * @see <a href="https://wikipedia.org/wiki/Swiss_franc">wikipedia.org</a>
   */
  CHF(756, 2, LI, CH),

  /**
   * WIR franc (complementary currency).
   *
   * <p>
   * WIR Bank for use with the EFTPOS system with their own WIR-card and the Electronic Banking Services.
   *
   * @see <a href="https://wikipedia.org/wiki/ISO_4217">wikipedia.org</a>
   */
  CHW(948, 2, FUND, CH),

  /**
   * Unidad de Fomento (funds code).
   *
   * <p>
   * The CLF is a daily economically-financial unit calculated by the Central Bank of Chile according to inflation (as measured
   * by the Chilean Consumer Price Index of the previous month). The value of the CLF is expressed in terms of Chilean Pesos per
   * CLF. The use of CLF has been widely extended to all types of bank loans, financial investments (time deposits, mortgages
   * and other public or private indexed instruments), contracts and fees in some cases.
   *
   * @see <a href="https://wikipedia.org/wiki/Unidad_de_Fomento">wikipedia.org</a>
   */
  CLF(990, 4, FUND, CL),

  /**
   * Chilean peso.
   *
   * @see <a href="https://wikipedia.org/wiki/Chilean_peso">wikipedia.org</a>
   */
  CLP(152, 0, CL),

  /**
   * Yuan Renminbi.
   *
   * @see <a href="https://wikipedia.org/wiki/Renminbi">wikipedia.org</a>
   */
  CNY(156, 2, CN),

  /**
   * Colombian peso.
   *
   * @see <a href="https://wikipedia.org/wiki/Colombian_peso">wikipedia.org</a>
   */
  COP(170, 2, CO),

  /**
   * Unidad de Valor Real (UVR) (funds code).
   *
   * <p>
   * The UVR is a daily account unit set by the Central Bank of Colombia according to the variation in the Consumer Price Index
   * of Colombia. The value of UVR is expressed in terms of Colombian Pesos per UVR. It is used to denominate and update
   * mortgage loans and some public debt bonds.
   *
   * @see <a href="https://wikipedia.org/wiki/ISO_4217">wikipedia.org</a>
   */
  COU(970, 2, FUND, CO),

  /**
   * Costa Rican colón.
   *
   * @see <a href="https://wikipedia.org/wiki/Costa_Rican_col%C3%B3n">wikipedia.org</a>
   */
  CRC(188, 2, CR),

  /**
   * Cuban convertible peso.
   *
   * @see <a href="https://wikipedia.org/wiki/Cuban_convertible_peso">wikipedia.org</a>
   */
  CUC(931, 2, CU),

  /**
   * Cuban peso.
   *
   * @see <a href="https://wikipedia.org/wiki/Cuban_peso">wikipedia.org</a>
   */
  CUP(192, 2, CU),

  /**
   * Cape Verdean escudo.
   *
   * @see <a href="https://wikipedia.org/wiki/Cape_Verdean_escudo">wikipedia.org</a>
   */
  CVE(132, 2, CV),

  /**
   * Czech koruna.
   *
   * @see <a href="https://wikipedia.org/wiki/Czech_koruna">wikipedia.org</a>
   */
  CZK(203, 2, CZ),

  /**
   * Djiboutian franc.
   *
   * @see <a href="https://wikipedia.org/wiki/Djiboutian_franc">wikipedia.org</a>
   */
  DJF(262, 0, DJ),

  /**
   * Danish krone.
   *
   * @see <a href="https://wikipedia.org/wiki/Danish_krone">wikipedia.org</a>
   */
  DKK(208, 2, DK, FO, GL),

  /**
   * Dominican peso.
   *
   * @see <a href="https://wikipedia.org/wiki/Dominican_peso">wikipedia.org</a>
   */
  DOP(214, 2, DO),

  /**
   * Algerian dinar.
   *
   * @see <a href="https://wikipedia.org/wiki/Algerian_dinar">wikipedia.org</a>
   */
  DZD(12, 2, DZ),

  /**
   * Egyptian pound.
   *
   * @see <a href="https://wikipedia.org/wiki/Egyptian_pound">wikipedia.org</a>
   */
  EGP(818, 2, EG),

  /**
   * Eritrean nakfa.
   *
   * @see <a href="https://wikipedia.org/wiki/Eritrean_nakfa">wikipedia.org</a>
   */
  ERN(232, 2, ER),

  /**
   * Ethiopian birr.
   *
   * @see <a href="https://wikipedia.org/wiki/Ethiopian_birr">wikipedia.org</a>
   */
  ETB(230, 2, ET),

  /**
   * Euro.
   *
   * @see <a href="https://wikipedia.org/wiki/Euro">wikipedia.org</a>
   */
  EUR(978, 2, PT, RE, LU, IT, SK, MF, AD, YT, BL, AX, GP, GF, PM, DE, BE, ES, SM, FI, MT, VA, MC, GR, MQ, TF, EE, IE,
      NL, ME, SI, FR, AT, CY, XK, LV, LT),

  /**
   * Fijian dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Fijian_dollar">wikipedia.org</a>
   */
  FJD(242, 2, FJ),

  /**
   * Falkland Islands pound.
   *
   * @see <a href="https://wikipedia.org/wiki/Falkland_Islands_pound">wikipedia.org</a>
   */
  FKP(238, 2, FK),

  /**
   * Pound sterling.
   *
   * @see <a href="https://wikipedia.org/wiki/Pound_sterling">wikipedia.org</a>
   */
  GBP(826, 2, GB, IM, JE, GG),

  /**
   * Georgian lari.
   *
   * @see <a href="https://wikipedia.org/wiki/Georgian_lari">wikipedia.org</a>
   */
  GEL(981, 2, GE),

  /**
   * Ghanaian cedi.
   *
   * @see <a href="https://wikipedia.org/wiki/Ghanaian_cedi">wikipedia.org</a>
   */
  GHS(936, 2, GH),

  /**
   * Gibraltar pound.
   *
   * @see <a href="https://wikipedia.org/wiki/Gibraltar_pound">wikipedia.org</a>
   */
  GIP(292, 2, GI),

  /**
   * Gambian dalasi.
   *
   * @see <a href="https://wikipedia.org/wiki/Gambian_dalasi">wikipedia.org</a>
   */
  GMD(270, 2, GM),

  /**
   * Guinean franc.
   *
   * @see <a href="https://wikipedia.org/wiki/Guinean_franc">wikipedia.org</a>
   */
  GNF(324, 0, GN),

  /**
   * Guatemalan quetzal.
   *
   * @see <a href="https://wikipedia.org/wiki/Guatemalan_quetzal">wikipedia.org</a>
   */
  GTQ(320, 2, GT),

  /**
   * Guyanese dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Guyanese_dollar">wikipedia.org</a>
   */
  GYD(328, 2, GY),

  /**
   * Hong Kong dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Hong_Kong_dollar">wikipedia.org</a>
   */
  HKD(344, 2, HK),

  /**
   * Honduran lempira.
   *
   * @see <a href="https://wikipedia.org/wiki/Honduran_lempira">wikipedia.org</a>
   */
  HNL(340, 2, HN),

  /**
   * Croatian kuna.
   *
   * @see <a href="https://wikipedia.org/wiki/Croatian_kuna">wikipedia.org</a>
   */
  HRK(191, 2, HR),

  /**
   * Haitian gourde.
   *
   * @see <a href="https://wikipedia.org/wiki/Haitian_gourde">wikipedia.org</a>
   */
  HTG(332, 2, HT),

  /**
   * Hungarian forint.
   *
   * @see <a href="https://wikipedia.org/wiki/Hungarian_forint">wikipedia.org</a>
   */
  HUF(348, 2, HU),

  /**
   * Indonesian rupiah.
   *
   * @see <a href="https://wikipedia.org/wiki/Indonesian_rupiah">wikipedia.org</a>
   */
  IDR(360, 2, ID),

  /**
   * Israeli new shekel.
   *
   * @see <a href="https://wikipedia.org/wiki/Israeli_new_shekel">wikipedia.org</a>
   */
  ILS(376, 2, IL),

  /**
   * Indian rupee.
   *
   * @see <a href="https://wikipedia.org/wiki/Indian_rupee">wikipedia.org</a>
   */
  INR(356, 2, IN, BT),

  /**
   * Iraqi dinar.
   *
   * @see <a href="https://wikipedia.org/wiki/Iraqi_dinar">wikipedia.org</a>
   */
  IQD(368, 3, IQ),

  /**
   * Iranian rial.
   *
   * @see <a href="https://wikipedia.org/wiki/Iranian_rial">wikipedia.org</a>
   */
  IRR(364, 2, IR),

  /**
   * Icelandic króna.
   *
   * @see <a href="https://wikipedia.org/wiki/Icelandic_kr%C3%B3na">wikipedia.org</a>
   */
  ISK(352, 0, IS),

  /**
   * Jamaican dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Jamaican_dollar">wikipedia.org</a>
   */
  JMD(388, 2, JM),

  /**
   * Jordanian dinar.
   *
   * @see <a href="https://wikipedia.org/wiki/Jordanian_dinar">wikipedia.org</a>
   */
  JOD(400, 3, JO),

  /**
   * Japanese yen.
   *
   * @see <a href="https://wikipedia.org/wiki/Japanese_yen">wikipedia.org</a>
   */
  JPY(392, 0, JP),

  /**
   * Kenyan shilling.
   *
   * @see <a href="https://wikipedia.org/wiki/Kenyan_shilling">wikipedia.org</a>
   */
  KES(404, 2, KE),

  /**
   * Kyrgyzstani som.
   *
   * @see <a href="https://wikipedia.org/wiki/Kyrgyzstani_som">wikipedia.org</a>
   */
  KGS(417, 2, KG),

  /**
   * Cambodian riel.
   *
   * @see <a href="https://wikipedia.org/wiki/Cambodian_riel">wikipedia.org</a>
   */
  KHR(116, 2, KH),

  /**
   * Comorian franc.
   *
   * @see <a href="https://wikipedia.org/wiki/Comorian_franc">wikipedia.org</a>
   */
  KMF(174, 0, KM),

  /**
   * North Korean won.
   *
   * @see <a href="https://wikipedia.org/wiki/North_Korean_won">wikipedia.org</a>
   */
  KPW(408, 2, KP),

  /**
   * South Korean won.
   *
   * @see <a href="https://wikipedia.org/wiki/South_Korean_won">wikipedia.org</a>
   */
  KRW(410, 0, KR),

  /**
   * Kuwaiti dinar.
   *
   * @see <a href="https://wikipedia.org/wiki/Kuwaiti_dinar">wikipedia.org</a>
   */
  KWD(414, 3, KW),

  /**
   * Cayman Islands dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Cayman_Islands_dollar">wikipedia.org</a>
   */
  KYD(136, 2, KY),

  /**
   * Kazakhstani tenge.
   *
   * @see <a href="https://wikipedia.org/wiki/Kazakhstani_tenge">wikipedia.org</a>
   */
  KZT(398, 2, KZ),

  /**
   * Lao kip.
   *
   * @see <a href="https://wikipedia.org/wiki/Lao_kip">wikipedia.org</a>
   */
  LAK(418, 2, LA),

  /**
   * Lebanese pound.
   *
   * @see <a href="https://wikipedia.org/wiki/Lebanese_pound">wikipedia.org</a>
   */
  LBP(422, 2, LB),

  /**
   * Sri Lankan rupee.
   *
   * @see <a href="https://wikipedia.org/wiki/Sri_Lankan_rupee">wikipedia.org</a>
   */
  LKR(144, 2, LK),

  /**
   * Liberian dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Liberian_dollar">wikipedia.org</a>
   */
  LRD(430, 2, LR),

  /**
   * Lesotho loti.
   *
   * @see <a href="https://wikipedia.org/wiki/Lesotho_loti">wikipedia.org</a>
   */
  LSL(426, 2, LS),

  /**
   * Libyan dinar.
   *
   * @see <a href="https://wikipedia.org/wiki/Libyan_dinar">wikipedia.org</a>
   */
  LYD(434, 3, LY),

  /**
   * Moroccan dirham.
   *
   * @see <a href="https://wikipedia.org/wiki/Moroccan_dirham">wikipedia.org</a>
   */
  MAD(504, 2, EH, MA),

  /**
   * Moldovan leu.
   *
   * @see <a href="https://wikipedia.org/wiki/Moldovan_leu">wikipedia.org</a>
   */
  MDL(498, 2, MD),

  /**
   * Malagasy ariary.
   *
   * @see <a href="https://wikipedia.org/wiki/Malagasy_ariary">wikipedia.org</a>
   */
  MGA(969, 2, MG),

  /**
   * Macedonian denar.
   *
   * @see <a href="https://wikipedia.org/wiki/Macedonian_denar">wikipedia.org</a>
   */
  MKD(807, 2, MK),

  /**
   * Burmese kyat.
   *
   * @see <a href="https://wikipedia.org/wiki/Burmese_kyat">wikipedia.org</a>
   */
  MMK(104, 2, MM),

  /**
   * Mongolian tögrög (or tugrik).
   *
   * @see <a href="https://wikipedia.org/wiki/Mongolian_t%C3%B6gr%C3%B6g">wikipedia.org</a>
   */
  MNT(496, 2, MN),

  /**
   * Macanese pataca.
   *
   * @see <a href="https://wikipedia.org/wiki/Macanese_pataca">wikipedia.org</a>
   */
  MOP(446, 2, MO),

  /**
   * Mauritanian ouguiya.
   *
   * @see <a href="https://wikipedia.org/wiki/Mauritanian_ouguiya">wikipedia.org</a>
   */
  MRU(929, 2, MR),

  /**
   * Mauritian rupee.
   *
   * @see <a href="https://wikipedia.org/wiki/Mauritian_rupee">wikipedia.org</a>
   */
  MUR(480, 2, MU),

  /**
   * Maldivian rufiyaa.
   *
   * @see <a href="https://wikipedia.org/wiki/Maldivian_rufiyaa">wikipedia.org</a>
   */
  MVR(462, 2, MV),

  /**
   * Malawian kwacha.
   *
   * @see <a href="https://wikipedia.org/wiki/Malawian_kwacha">wikipedia.org</a>
   */
  MWK(454, 2, MW),

  /**
   * Mexican peso.
   *
   * @see <a href="https://wikipedia.org/wiki/Mexican_peso">wikipedia.org</a>
   */
  MXN(484, 2, MX),

  /**
   * Mexican unidad de inversión.
   *
   * <p>
   * The UDI is an inflation adjusted mechanism set by the Central Bank of Mexico according to the variation in the Mexican
   * Consumer Price Index. The value of the UDI is expressed in terms of Mexican Pesos per UDI. It is used to denominate
   * mortgage loans, some bank deposits with maturities of 3 month or more and Government bonds (UDIBONOS).
   *
   * @see <a href="https://wikipedia.org/wiki/Mexican_unidad_de_inversi%C3%B3n">wikipedia.org</a>
   */
  MXV(979, 2, FUND, MX),

  /**
   * Malaysian ringgit.
   *
   * @see <a href="https://wikipedia.org/wiki/Malaysian_ringgit">wikipedia.org</a>
   */
  MYR(458, 2, MY),

  /**
   * Mozambican metical.
   *
   * @see <a href="https://wikipedia.org/wiki/Mozambican_metical">wikipedia.org</a>
   */
  MZN(943, 2, MZ),

  /**
   * Namibian dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Namibian_dollar">wikipedia.org</a>
   */
  NAD(516, 2, NA),

  /**
   * Nigerian naira.
   *
   * @see <a href="https://wikipedia.org/wiki/Nigerian_naira">wikipedia.org</a>
   */
  NGN(566, 2, NG),

  /**
   * Nicaraguan córdoba.
   *
   * @see <a href="https://wikipedia.org/wiki/Nicaraguan_c%C3%B3rdoba">wikipedia.org</a>
   */
  NIO(558, 2, NI),

  /**
   * Norwegian krone.
   *
   * @see <a href="https://wikipedia.org/wiki/Norwegian_krone">wikipedia.org</a>
   */
  NOK(578, 2, NO, SJ, BV),

  /**
   * Nepalese rupee.
   *
   * @see <a href="https://wikipedia.org/wiki/Nepalese_rupee">wikipedia.org</a>
   */
  NPR(524, 2, NP),

  /**
   * New Zealand dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/New_Zealand_dollar">wikipedia.org</a>
   */
  NZD(554, 2, TK, NZ, CK, NU, PN),

  /**
   * Omani rial.
   *
   * @see <a href="https://wikipedia.org/wiki/Omani_rial">wikipedia.org</a>
   */
  OMR(512, 3, OM),

  /**
   * Panamanian balboa.
   *
   * @see <a href="https://wikipedia.org/wiki/Panamanian_balboa">wikipedia.org</a>
   */
  PAB(590, 2, PA),

  /**
   * Peruvian sol.
   *
   * @see <a href="https://wikipedia.org/wiki/Peruvian_sol">wikipedia.org</a>
   */
  PEN(604, 2, PE),

  /**
   * Papua New Guinean kina.
   *
   * @see <a href="https://wikipedia.org/wiki/Papua_New_Guinean_kina">wikipedia.org</a>
   */
  PGK(598, 2, PG),

  /**
   * Philippine peso.
   *
   * @see <a href="https://wikipedia.org/wiki/Philippine_peso">wikipedia.org</a>
   */
  PHP(608, 2, PH),

  /**
   * Pakistani rupee.
   *
   * @see <a href="https://wikipedia.org/wiki/Pakistani_rupee">wikipedia.org</a>
   */
  PKR(586, 2, PK),

  /**
   * Polish złoty.
   *
   * @see <a href="https://wikipedia.org/wiki/Polish_z%C5%82oty">wikipedia.org</a>
   */
  PLN(985, 2, PL),

  /**
   * Paraguayan guaraní.
   *
   * @see <a href="https://wikipedia.org/wiki/Paraguayan_guaran%C3%AD">wikipedia.org</a>
   */
  PYG(600, 0, PY),

  /**
   * Qatari riyal.
   *
   * @see <a href="https://wikipedia.org/wiki/Qatari_riyal">wikipedia.org</a>
   */
  QAR(634, 2, QA),

  /**
   * Romanian leu.
   *
   * @see <a href="https://wikipedia.org/wiki/Romanian_leu">wikipedia.org</a>
   */
  RON(946, 2, RO),

  /**
   * Serbian dinar.
   *
   * @see <a href="https://wikipedia.org/wiki/Serbian_dinar">wikipedia.org</a>
   */
  RSD(941, 2, RS),

  /**
   * Russian ruble.
   *
   * @see <a href="https://wikipedia.org/wiki/Russian_ruble">wikipedia.org</a>
   */
  RUB(643, 2, RU),

  /**
   * Rwandan franc.
   *
   * @see <a href="https://wikipedia.org/wiki/Rwandan_franc">wikipedia.org</a>
   */
  RWF(646, 0, RW),

  /**
   * Saudi riyal.
   *
   * @see <a href="https://wikipedia.org/wiki/Saudi_riyal">wikipedia.org</a>
   */
  SAR(682, 2, SA),

  /**
   * Solomon Islands dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Solomon_Islands_dollar">wikipedia.org</a>
   */
  SBD(90, 2, SB),

  /**
   * Seychellois rupee.
   *
   * @see <a href="https://wikipedia.org/wiki/Seychellois_rupee">wikipedia.org</a>
   */
  SCR(690, 2, SC),

  /**
   * Sudanese pound.
   *
   * @see <a href="https://wikipedia.org/wiki/Sudanese_pound">wikipedia.org</a>
   */
  SDG(938, 2, SD),

  /**
   * Swedish króna.
   *
   * @see <a href="https://wikipedia.org/wiki/Swedish_krona">wikipedia.org</a>
   */
  SEK(752, 2, SE),

  /**
   * Singapore dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Singapore_dollar">wikipedia.org</a>
   */
  SGD(702, 2, SG),

  /**
   * Saint Helena pound.
   *
   * @see <a href="https://wikipedia.org/wiki/Saint_Helena_pound">wikipedia.org</a>
   */
  SHP(654, 2, SH),

  /**
   * Sierra Leonean leone.
   *
   * <p>
   * This currency code was introduced by ISO-4217 amendment number 171 and is effective from 1 April 2022 :
   *
   * <blockquote>
   * <p>
   * The Sierra Leonean LEONE (SLL) is redenominated by removing three (3) zeros from the denominations. A new currency code
   * SLE/925 representing the new valuation (1’000 times old SLL/694) is introduced on 1 st April 2022 for any internal needs
   * during the redenomination process, and is replacing SLL as the official currency code, after the transition period to be
   * determined.
   *
   * <p>
   * During this transition period, both the old Leone and new Leone will be in physical circulation for at least 90 days.
   *
   * <p>
   * The Bank of Sierra Leone will adopt the new code in the local system but SLL/694 shall remain in use until further notice.
   * </blockquote>
   *
   * @see <a href="https://wikipedia.org/wiki/Venezuelan_bol%C3%ADvar">wikipedia.org</a>
   * @see <a href="https://www.currency-iso.org/dam/downloads/dl_currency_iso_amendment_171.pdf">ISO-4217 amendment
   *      number 171</a>
   * @see <a href="https://wikipedia.org/wiki/Sierra_Leonean_leone">wikipedia.org</a>
   * @since 3.2.0
   */
  SLE(925, 2, SL),

  /**
   * Sierra Leonean leone.
   *
   * @see <a href="https://wikipedia.org/wiki/Sierra_Leonean_leone">wikipedia.org</a>
   */
  SLL(694, 2, SL),

  /**
   * Somali shilling.
   *
   * @see <a href="https://wikipedia.org/wiki/Somali_shilling">wikipedia.org</a>
   */
  SOS(706, 2, SO),

  /**
   * Surinamese dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Surinamese_dollar">wikipedia.org</a>
   */
  SRD(968, 2, SR),

  /**
   * South Sudanese pound.
   *
   * @see <a href="https://wikipedia.org/wiki/South_Sudanese_pound">wikipedia.org</a>
   */
  SSP(728, 2, SS),

  /**
   * São Tomé and Príncipe dobra.
   *
   * @see <a href="https://wikipedia.org/wiki/S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe_dobra">wikipedia.org</a>
   */
  STN(930, 2, ST),

  /**
   * Salvadoran colón.
   *
   * @see <a href="https://wikipedia.org/wiki/Salvadoran_col%C3%B3n">wikipedia.org</a>
   */
  SVC(222, 2, SV),

  /**
   * Syrian pound.
   *
   * @see <a href="https://wikipedia.org/wiki/Syrian_pound">wikipedia.org</a>
   */
  SYP(760, 2, SY),

  /**
   * Swazi lilangeni.
   *
   * @see <a href="https://wikipedia.org/wiki/Swazi_lilangeni">wikipedia.org</a>
   */
  SZL(748, 2, SZ),

  /**
   * Thai baht.
   *
   * @see <a href="https://wikipedia.org/wiki/Thai_baht">wikipedia.org</a>
   */
  THB(764, 2, TH),

  /**
   * Tajikistani somoni.
   *
   * @see <a href="https://wikipedia.org/wiki/Tajikistani_somoni">wikipedia.org</a>
   */
  TJS(972, 2, TJ),

  /**
   * Turkmenistan manat.
   *
   * @see <a href="https://wikipedia.org/wiki/Turkmenistan_manat">wikipedia.org</a>
   */
  TMT(934, 2, TM),

  /**
   * Tunisian dinar.
   *
   * @see <a href="https://wikipedia.org/wiki/Tunisian_dinar">wikipedia.org</a>
   */
  TND(788, 3, TN),

  /**
   * Tongan paʻanga.
   *
   * @see <a href="https://wikipedia.org/wiki/Tongan_pa%CA%BBanga">wikipedia.org</a>
   */
  TOP(776, 2, TO),

  /**
   * Turkish lira.
   *
   * @see <a href="https://wikipedia.org/wiki/Turkish_lira">wikipedia.org</a>
   */
  TRY(949, 2, TR),

  /**
   * Trinidad and Tobago dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Trinidad_and_Tobago_dollar">wikipedia.org</a>
   */
  TTD(780, 2, TT),

  /**
   * New Taiwan dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/New_Taiwan_dollar">wikipedia.org</a>
   */
  TWD(901, 2, TW),

  /**
   * Tanzanian shilling.
   *
   * @see <a href="https://wikipedia.org/wiki/Tanzanian_shilling">wikipedia.org</a>
   */
  TZS(834, 2, TZ),

  /**
   * Ukrainian hryvnia.
   *
   * @see <a href="https://wikipedia.org/wiki/Ukrainian_hryvnia">wikipedia.org</a>
   */
  UAH(980, 2, UA),

  /**
   * Ugandan shilling.
   *
   * @see <a href="https://wikipedia.org/wiki/Ugandan_shilling">wikipedia.org</a>
   */
  UGX(800, 0, UG),

  /**
   * United States dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/United_States_dollar">wikipedia.org</a>
   */
  USD(840, 2, VG, TL, MP, FM, PW, GU, HT, EC, UM, IO, VI, BQ, SV, US, PA, MH, PR, AS, TC),

  /**
   * United States dollar (next day) (funds code).
   *
   * <p>
   * "Next day" funds are immediately available for transfer in like funds, and, subject to settlement, available the next
   * business day for same day funds transfer or withdrawal in cash.
   *
   * @see <a href="https://wikipedia.org/wiki/ISO_4217">wikipedia.org</a>
   */
  USN(997, 2, FUND, US),

  /**
   * United States dollar (same day) (funds code).
   *
   * @see <a href="https://wikipedia.org/wiki/ISO_4217">wikipedia.org</a>
   */
  USS(998, 2, FUND, US),

  /**
   * Uruguay Peso en Unidades Indexadas (URUIURUI) (funds code).
   *
   * <p>
   * The UYI (UI) is used for issuance of debt instruments by the Uruguayan government in the international global bond market.
   * It is calculated based on an established methodology using underlying inflationary statistics in the Uruguayan market
   * (introduced in 2002).
   *
   * @see <a href="https://en.wikipedia.org/wiki/UYI">wikipedia.org</a>
   */
  UYI(940, 0, FUND, UY),

  /**
   * Uruguayan peso.
   *
   * @see <a href="https://wikipedia.org/wiki/Uruguayan_peso">wikipedia.org</a>
   */
  UYU(858, 2, UY),

  /**
   * Uruguayan Unidad Previsional (Fund currency).
   *
   * <p>
   * The Unidad Previsional (UP) is a daily accounting unit that tracks changes to the nominal wage index. The value of UP is
   * expressed in terms of Uruguayan Pesos per UP, with the initial value of one peso (UYU 1.00) on 04/30/2018. The institution
   * responsible for the calculation and publication is the Instituto Nacional de Estadística (National Bureau of Statistics)
   * according to Law 19,608.
   *
   * @see <a href="https://www.currency-iso.org/dam/downloads/dl_currency_iso_amendment_169.pdf">ISO 4217 AMENDMENT NUMBER
   *      169</a>
   */
  UYW(927, 4, FUND, UY),

  /**
   * Uzbekistani soʻm.
   *
   * @see <a href="https://wikipedia.org/wiki/Uzbekistani_so%CA%BBm">wikipedia.org</a>
   */
  UZS(860, 2, UZ),

  /**
   * Venezuelan bolívar digital (as designated on <a href="https://en.wikipedia.org/wiki/ISO_4217">Wikipedia</a>).
   *
   * <p>
   * This currency code was introduced by ISO-4217 amendment number 170 and is effective from 1 October 2021 :
   *
   * <blockquote>
   * <p>
   * The Bolívar Soberano (VES) is redenominated by removing six zeros from the denominations. A new currency code VED/926
   * representing the new valuation (1,000,000 times old VES/928) is introduced on 1 October 2021 for any internal needs during
   * the redenomination process, but is not replacing VES as the official currency code. The Central Bank of Venezuela will not
   * adopt the new codes in the local system, VES/928 remains in use.
   *
   * <p>
   * The actual currency code VES/928 remains the valid code after 1 October 2021 to use in any future transactions to indicate
   * the redenominated Bolívar Soberano.
   * </blockquote>
   *
   * @see <a href="https://wikipedia.org/wiki/Venezuelan_bol%C3%ADvar">wikipedia.org</a>
   * @see <a href="https://www.currency-iso.org/dam/downloads/dl_currency_iso_amendment_170.pdf">ISO-4217 amendment number
   *      170</a>
   * @since 3.2.0
   */
  VED(926, 2, VE),

  /**
   * Venezuelan bolívar soberano.
   *
   * @see <a href="https://wikipedia.org/wiki/Venezuelan_bol%C3%ADvar">wikipedia.org</a>
   */
  VES(928, 2, VE),

  /**
   * Vietnamese đồng.
   *
   * @see <a href="https://wikipedia.org/wiki/Vietnamese_%C4%91%E1%BB%93ng">wikipedia.org</a>
   */
  VND(704, 0, VN),

  /**
   * Vanuatu vatu.
   *
   * @see <a href="https://wikipedia.org/wiki/Vanuatu_vatu">wikipedia.org</a>
   */
  VUV(548, 0, VU),

  /**
   * Samoan tālā.
   *
   * @see <a href="https://wikipedia.org/wiki/Samoan_t%C4%81l%C4%81">wikipedia.org</a>
   */
  WST(882, 2, WS),

  /**
   * Central African CFA franc.
   *
   * @see <a href="https://wikipedia.org/wiki/Central_African_CFA_franc">wikipedia.org</a>
   */
  XAF(950, 0, TD, GA, GQ, CM, CF, CG),

  /**
   * Silver.
   *
   * @see <a href="https://wikipedia.org/wiki/Silver">wikipedia.org</a>
   */
  XAG(961, null, METAL),

  /**
   * Gold.
   *
   * @see <a href="https://wikipedia.org/wiki/Gold_as_an_investment">wikipedia.org</a>
   */
  XAU(959, null, METAL),

  /**
   * European Composite Unit (EURCO).
   *
   * @see <a href="https://wikipedia.org/wiki/European_Unit_of_Account">wikipedia.org</a>
   */
  XBA(955, null, BOND),

  /**
   * European Monetary Unit (E.M.U.-6).
   *
   * @see <a href="https://wikipedia.org/wiki/European_Unit_of_Account">wikipedia.org</a>
   */
  XBB(956, null, BOND),

  /**
   * European Unit of Account 9 (E.U.A.-9).
   *
   * @see <a href="https://wikipedia.org/wiki/European_Unit_of_Account">wikipedia.org</a>
   */
  XBC(957, null, BOND),

  /**
   * European Unit of Account 17 (E.U.A.-17).
   *
   * @see <a href="https://wikipedia.org/wiki/European_Unit_of_Account">wikipedia.org</a>
   */
  XBD(958, null, BOND),

  /**
   * Eastern Caribbean dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Eastern_Caribbean_dollar">wikipedia.org</a>
   */
  XCD(951, 2, DM, VC, MS, AG, LC, AI, KN, GD),

  /**
   * Special drawing rights.
   *
   * @see <a href="https://wikipedia.org/wiki/Special_drawing_rights">wikipedia.org</a>
   */
  XDR(960, null, OTHER),

  /**
   * West African CFA franc.
   *
   * @see <a href="https://wikipedia.org/wiki/West_African_CFA_franc">wikipedia.org</a>
   */
  XOF(952, 0, ML, NE, BJ, TG, BF, SN, CI, GW),

  /**
   * Palladium.
   *
   * @see <a href="https://wikipedia.org/wiki/Palladium">wikipedia.org</a>
   */
  XPD(964, null, METAL),

  /**
   * CFP franc.
   *
   * @see <a href="https://wikipedia.org/wiki/CFP_franc">wikipedia.org</a>
   */
  XPF(953, 0, PF, WF, NC),

  /**
   * Platinum.
   *
   * @see <a href="https://wikipedia.org/wiki/Platinum">wikipedia.org</a>
   */
  XPT(962, null, METAL),

  /**
   * The SUCRE (a.k.a Sistema Unitario de Compensación Regional, or Unified System for Regional Compensation).
   *
   * @see <a href="https://wikipedia.org/wiki/SUCRE">wikipedia.org</a>
   */
  XSU(994, null, OTHER),

  /**
   * Code reserved for testing.
   *
   * @see <a href="https://wikipedia.org/wiki/ISO_4217">wikipedia.org</a>
   */
  XTS(963, null, OTHER),

  /**
   * ADB (African Development Bank) Unit of Account.
   *
   * @see <a href="https://wikipedia.org/wiki/XUA">wikipedia.org</a>
   */
  XUA(965, null, OTHER),

  /**
   * No currency.
   *
   * @see <a href="https://wikipedia.org/wiki/ISO_4217">wikipedia.org</a>
   */
  XXX(999, null, OTHER),

  /**
   * Yemeni rial.
   *
   * @see <a href="https://wikipedia.org/wiki/Yemeni_rial">wikipedia.org</a>
   */
  YER(886, 2, YE),

  /**
   * South African rand.
   *
   * @see <a href="https://wikipedia.org/wiki/South_African_rand">wikipedia.org</a>
   */
  ZAR(710, 2, NA, LS, ZA),

  /**
   * Zambian kwacha.
   *
   * @see <a href="https://wikipedia.org/wiki/Zambian_kwacha">wikipedia.org</a>
   */
  ZMW(967, 2, ZM),

  /**
   * Zimbabwean dollar.
   *
   * @see <a href="https://wikipedia.org/wiki/Zimbabwean_dollar">wikipedia.org</a>
   */
  ZWL(932, 2, ZW);

  // Enum.valueOf throws Exception on null or not found
  private static final Map<String, IsoCurrency> byAlphaCode = new HashMap<>();
  private static final Map<Integer, IsoCurrency> byNumericCode = new HashMap<>();

  static {
    for (IsoCurrency currency : values()) {
      byAlphaCode.put(currency.getAlphabeticCode(), currency);
      byNumericCode.put(currency.getNumericCode(), currency);
    }
  }

  private final int numericCode;
  private final Integer minorUnit;
  private final Category category;

  @SuppressWarnings("ImmutableEnumChecker") // initialized with Collections.unmodifiableSet(...).
  private final Set<IsoCountry> countries;

  IsoCurrency(int numericCode, Integer minorUnit, Category category, IsoCountry... countries) {
    this.numericCode = numericCode;
    this.minorUnit = minorUnit;
    this.category = requireNonNull(category);
    this.countries = unmodifiableSet(
        countries.length > 0 ? EnumSet.copyOf(Arrays.asList(countries)) : EnumSet.noneOf(IsoCountry.class));
  }

  IsoCurrency(int numericCode, Integer minorUnit, IsoCountry... countries) {
    this(numericCode, minorUnit, NATIONAL, countries);
  }

  /**
   * Returns this currency ISO 4217 alphabetical code.
   *
   * @return a non-null and 3 characters length string.
   */
  public String getAlphabeticCode() {
    return name();
  }

  /**
   * Returns this currency ISO 4217 numeric code.
   *
   * @return an integer.
   */
  public int getNumericCode() {
    return numericCode;
  }

  /**
   * Returns this currency minor unit, if applicable.
   *
   * @return an Integer or null if this not applicable.
   */
  public Integer getMinorUnit() {
    return minorUnit;
  }

  /**
   * Returns this currency {@link Category category}.
   *
   * @return a non-null {@link Category}.
   */
  public Category getCategory() {
    return category;
  }

  /**
   * Returns the countries that are using this currency.
   *
   * @return a non-null but may be empty set of countries.
   */
  public Set<IsoCountry> getCountries() {
    return countries;
  }

  /**
   * Translate the given ISO 4217 alphabetical code to an IsoCurrency.
   *
   * <p>
   * This method is neither sensitive to the case nor to the presence of leading or trailing spaces.
   *
   * @param code A non-null String.
   * @return the currency having the given ISO 4217 alphabetical code, or null if it does not exist.
   */
  public static Optional<IsoCurrency> fromAlphabeticCode(String code) {
    return Optional.ofNullable(byAlphaCode.get(trimUpperCase(code)));
  }

  /**
   * Translate the given ISO 4217 numeric code to an IsoCurrency.
   *
   * @param code An Integer, null or not.
   * @return the currency having the given ISO 4217 numerical code, or null if it does not exist.
   */
  public static Optional<IsoCurrency> fromNumericCode(int code) {
    return Optional.ofNullable(byNumericCode.get(code));
  }

  /**
   * Get currencies of the given category.
   *
   * @param category a non-null category
   * @return a non-null set of currencies
   * @throws NullPointerException if the given category is {@code null}
   */
  public static Set<IsoCurrency> allOf(Category category) {
    requireNonNull(category);
    EnumSet<IsoCurrency> currencies = EnumSet.noneOf(IsoCurrency.class);

    for (IsoCurrency currency : values()) {
      if (currency.category == category) {
        currencies.add(currency);
      }
    }

    return currencies;
  }

  /**
   * {@link IsoCurrency ISO 4217 currencies} categories.
   */
  public enum Category {

    /**
     * Bond market unit codes.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Bond_market">Bond market</a>
     */
    BOND,

    /**
     * Fund codes.
     *
     * @see <a href="https://www.currency-iso.org/en/home/tables/table-a2.html">Current funds codes list</a>
     */
    FUND,

    /**
     * Precious metals codes.
     *
     * <p>
     * These “currency units” are denominated as <a href="https://en.wikipedia.org/wiki/Troy_weight#Troy_ounce_(oz_t)">one troy
     * ounce</a>.
     */
    METAL,

    /**
     * National or supranational currencies codes.
     */
    NATIONAL,

    /**
     * Other codes (testing codes, special codes...).
     */
    OTHER
  }
}
