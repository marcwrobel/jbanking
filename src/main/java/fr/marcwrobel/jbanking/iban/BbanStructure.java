package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.IsoCountry.*;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.internal.LastVerification;
import java.util.*;

/**
 * Provides BBAN (also known as basic bank account number) structure for each ISO 13616-compliant national IBAN formats.
 *
 * <p>
 * It is based on the document <i>IBAN Registry Release 92</i> issued by SWIFT in May 2022. Last verification date of
 * this list can be seen in the {@code @LastVerification} value.
 *
 * <p>
 * Except the national check digit, which is pretty common, the following information were considered too specific to be
 * part of the jbanking API :
 * <ul>
 * <li>account type (Bulgaria, Brasil, Guatemala),</li>
 * <li>account currency (Guatemala, Mauritius, Seychelles),</li>
 * <li>balance account number (Belarus),</li>
 * <li>identification number (Iceland),</li>
 * <li>owner account number (Brasil),</li>
 * <li>reserved characters (Costa Rica, Mauritius).</li>
 * </ul>
 *
 * <p>
 * Note that IBAN Registry contains errors in the BBAN composition for some countries, such as France or Poland. Each
 * format has been verified using the documentation issued by the national contact indicated in the IBAN Registry (or
 * using @see <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number">International Bank Account
 * Number</a> if no documentation were found on the contact's website).
 *
 * @see <a href="https://www.iso13616.org">https://www.iso13616.org</a>
 * @see <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number">International Bank Account Number</a>
 * @see <a href="https://bank-code.net/iban/country-list">List of Countries using the IBAN standard</a>
 * @since 1.0
 */
@LastVerification("2023-01-07")
@SuppressWarnings("java:S1192") // swift expressions cannot be constants (maintainability).
public enum BbanStructure {
  /**
   * BBAN structure for Andorra as defined by the <a href="https://www.andorranbanking.ad/">Associació de Bancs
   * Andorrans</a> (ABA).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: AD12 0001 2030 2003 5910 0100</li>
   * <li>BBAN: 00012030200359100100</li>
   * <li>Bank identifier: 0001</li>
   * <li>Branch identifier: 2030</li>
   * <li>Account number: 200359100100</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.andorranbanking.ad/en/aba_topics/what-is-iban-format/">What is IBAN format?</a>
   */
  AD(IsoCountry.AD, "4!n4!n12!c", 0, 4, 4, 8, null, null, 8, 20),

  /**
   * BBAN structure for the United Arab Emirates as defined by the <a href="https://www.centralbank.ae">Central Bank of
   * the United Arab Emirates</a>.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: AE07 0331 2345 6789 0123 456</li>
   * <li>BBAN: 0331234567890123456</li>
   * <li>Bank identifier: 033</li>
   * <li>Account number: 1234567890123456</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://centralbank.ae/en/our-operations/payments-and-settlements/regulations-and-standards/iban/">IBAN</a>
   */
  AE(IsoCountry.AE, "3!n16!n", 0, 3, null, null, null, null, 3, 19),

  /**
   * BBAN structure for Albania as defined by the <a href="https://www.bankofalbania.org">Banka e Shqipërisë</a> (Bank
   * of Albania).
   *
   * <p>
   * Note that data in IBAN registry (Release 92 – May 2022) is wrong regarding the BBAN structure.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: AL47 2121 1009 0000 0002 3569 8741</li>
   * <li>BBAN: 212110090000000235698741</li>
   * <li>Bank identifier: 212</li>
   * <li>Branch identifier: 1100</li>
   * <li>National check digit: 9</li>
   * <li>Account number: 0000000235698741</li>
   * </ul>
   *
   * @see <a href="https://www.bankofalbania.org/Press/Press_Releases/IBAN_International_Bank_Account_Number.html">IBAN</a>
   */
  AL(IsoCountry.AL, "8!n16!c", 0, 3, 3, 7, 7, 8, 8, 24),

  /**
   * BBAN structure for Austria as defined by <a href="https://www.stuzza.at/">Stuzza</a>.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: AT61 1904 3002 3457 3201</li>
   * <li>BBAN: 1904300234573201</li>
   * <li>Bank identifier: 19043</li>
   * <li>Account number: 00234573201</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  AT(IsoCountry.AT, "5!n11!n", 0, 5, null, null, null, null, 5, 16),

  /**
   * BBAN structure for Azerbaijan as defined by the <a href="https://www.cbar.az/">Azərbaycan Respublikasının Mərkəzi
   * Bankı</a> (Central Bank of the Republic of Azerbaijan - CBA).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: AZ21 NABZ 0000 0000 1370 1000 1944</li>
   * <li>BBAN: NABZ00000000137010001944</li>
   * <li>Bank identifier: NABZ</li>
   * <li>Account number: 00000000137010001944</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.cbar.az/page-618/iban">IBAN</a>
   */
  AZ(IsoCountry.AZ, "4!a20!c", 0, 4, null, null, null, null, 4, 24),

  /**
   * BBAN structure for Bosnia and Herzegovina as defined by the <a href="https://www.cbbh.ba/?lang=en">Centralna banka
   * I Bosne Hercegovine</a> (Central Bank of Bosnia and Herzegovina - CBBH).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: BA39 1290 0794 0102 8494</li>
   * <li>BBAN: 1290079401028494</li>
   * <li>Bank identifier: 129</li>
   * <li>Branch identifier: 007</li>
   * <li>Account number: 94010284</li>
   * <li>National check digit: 94</li>
   * </ul>
   *
   * @see <a href="https://cbbh.ba/Content/Read/610">The Instruction on the structure and use of International Number of the
   *      Bank Account (IBAN)</a>
   */
  BA(IsoCountry.BA, "3!n3!n8!n2!n", 0, 3, 3, 6, 14, 16, 6, 14),

  /**
   * BBAN structure for Belgium as defined by the <a href="https://www.febelfin.be">Fédération belge du secteur
   * financier</a> (Febelfin).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: BE68 5390 0754 7034</li>
   * <li>BBAN: 539007547034</li>
   * <li>Bank identifier: 539</li>
   * <li>Account number: 0075470</li>
   * <li>National check digit: 34</li>
   * <li>Branch identifier: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.febelfin.be/fr/dossiers/votre-numero-de-compte-iban-et-bic">Votre numéro de compte : IBAN et
   *      BIC</a>
   */
  BE(IsoCountry.BE, "3!n7!n2!n", 0, 3, null, null, 10, 12, 3, 10),

  /**
   * BBAN structure for Bulgaria as defined by the <a href="https://www.bnb.bg/">Balgarska narodna banka</a> (Bulgarian
   * National Bank - BNB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: BG80 BNBG 9661 1020 3456 78</li>
   * <li>BBAN: BNBG96611020345678</li>
   * <li>Bank identifier: BNBG</li>
   * <li>Branch identifier: 9661</li>
   * <li>Account type: 10</li>
   * <li>Account number: 20345678</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.bnb.bg/PaymentSystem/PSIBAN/index.htm?toLang=_EN">IBAN - bank account number</a>
   */
  BG(IsoCountry.BG, "4!a4!n2!n8!c", 0, 4, 4, 8, null, null, 10, 18),

  /**
   * BBAN structure for Bahrain as defined by the <a href="https://www.cbb.gov.bh/">Central Bank of Bahrain</a> (CBB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: BH67 BMAG 0000 1299 1234 56</li>
   * <li>BBAN: BMAG00001299123456</li>
   * <li>Bank identifier: BMAG</li>
   * <li>Account number: 00001299123456</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.cbb.gov.bh/iban/">IBAN Info</a>
   */
  BH(IsoCountry.BH, "4!a14!c", 0, 4, null, null, null, null, 4, 18),

  /**
   * BBAN structure for Burundi as defined by the <a href="https://brb.bi/">Banque de la République du Burundi</a>
   * (BRB).
   *
   * <p>
   * Note that the SWIFT expression seems to indicate the presence of a check digit at the end of the BBAN. As we have
   * not found any documentation clarifying this point we will consider for the time being that this is not the case (so
   * the last two characters belong to the account number).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: BI42 1000 0100 0100 0033 2045 181</li>
   * <li>BBAN: 10000100010000332045181</li>
   * <li>Bank identifier: 10000</li>
   * <li>Branch identifier: 10001</li>
   * <li>Account number: 0000332045181</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  BI(IsoCountry.BI, "5!n5!n11!n2!n", 0, 5, 5, 10, null, null, 10, 23),

  /**
   * BBAN structure for Brazil as defined by the <a href="https://www.bcb.gov.br/">Banco Central do Brasil</a> (Central
   * Bank of Brazil - BCB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: BR18 0036 0305 0000 1000 9795 493C 1</li>
   * <li>BBAN: 00360305000010009795493C1</li>
   * <li>Bank identifier: 00360305</li>
   * <li>Branch identifier: 00001</li>
   * <li>Account number: 0009795493</li>
   * <li>Account type: C</li>
   * <li>Owner account number: 1</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.bcb.gov.br/content/financialstability/paymentssystem_docs/IBAN-Guidelines_English.pdf">IBAN
   *      Implementation Guidelines for Brazil</a>
   */
  BR(IsoCountry.BR, "8!n5!n10!n1!a1!c", 0, 8, 8, 13, null, null, 13, 23),

  /**
   * BBAN structure for the Republic of Belarus as defined by the <a href="https://www.nbrb.by">National Bank of the
   * Republic of Belarus</a> (NBRB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: BY13 NBRB 3600 9000 0000 2Z00 AB00</li>
   * <li>BBAN: NBRB3600900000002Z00AB00</li>
   * <li>Bank identifier: NBRB</li>
   * <li>Balance account number: 3600</li>
   * <li>Account number: 900000002Z00AB00</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  BY(IsoCountry.BY, "4!c4!n16!c", 0, 4, null, null, null, null, 8, 24),

  /**
   * BBAN structure for Switzerland as defined by <a href="https://www.six-group.com">SIX Interbank Clearing</a>.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: CH93 0076 2011 6238 5295 7</li>
   * <li>BBAN: 00762011623852957</li>
   * <li>Bank identifier: 00762</li>
   * <li>Account number: 011623852957</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  CH(IsoCountry.CH, "5!n12!c", 0, 5, null, null, null, null, 5, 17),

  /**
   * BBAN structure for Costa Rica as defined by the <a href="https://www.bccr.fi.cr/">Banco Central de Costa Rica</a>
   * (BCCR).
   *
   * <p>
   * Note that data in IBAN registry (Release 92 – May 2022) is wrong regarding the BBAN structure (bank identifier
   * contains the reserved character).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: CR05 0152 0200 1026 2840 66</li>
   * <li>BBAN: 015202001026284066</li>
   * <li>Reserved: 0</li>
   * <li>Bank identifier: 152</li>
   * <li>Account number: 02001026284066</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.bccr.fi.cr/en/payments-system/general-information/iban">IBAN</a>
   */
  CR(IsoCountry.CR, "4!n14!n", 1, 4, null, null, null, null, 4, 18),

  /**
   * BBAN structure for Cyprus as defined by the <a href="https://www.centralbank.cy/">Kıbrıs Merkez Bankası</a>
   * (Central Bank of Cyprus).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: CY17 0020 0128 0000 0012 0052 7600</li>
   * <li>BBAN: 002001280000001200527600</li>
   * <li>Bank identifier: 002</li>
   * <li>Branch identifier: 00128</li>
   * <li>Account number: 0000001200527600</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href=
   *      "https://www.centralbank.cy/en/financial-market-infrastructures-payments/international-bank-account-number-iban">International
   *      Bank Account Number (IBAN)</a>
   */
  CY(IsoCountry.CY, "3!n5!n16!c", 0, 3, 3, 8, null, null, 8, 24),

  /**
   * BBAN structure for the Czech Republic as defined by the <a href="https://www.cnb.cz/">Česká národní banka</a>
   * (Czech National Bank - CNB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: CZ65 0800 0000 1920 0014 5399</li>
   * <li>BBAN: 08000000192000145399</li>
   * <li>Bank identifier: 0800</li>
   * <li>Account number: 0000192000145399</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.cnb.cz/en/payments/iban/iban-international-bank-account-number-basic-information/">IBAN –
   *      International Bank Account Number – basic information</a>
   */
  CZ(IsoCountry.CZ, "4!n6!n10!n", 0, 4, null, null, null, null, 4, 20),

  /**
   * BBAN structure for Germany as defined by the <a href="https://bankenverband.de">Bundesverband deutscher Banken</a>
   * (Association of German Banks).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: DE89 3704 0044 0532 0130 00</li>
   * <li>BBAN: 370400440532013000</li>
   * <li>Bank identifier: 37040044</li>
   * <li>Account number: 0532013000</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  DE(IsoCountry.DE, "8!n10!n", 0, 8, null, null, null, null, 8, 18),

  /**
   * BBAN structure for Djibouti as defined by the <a href="https://banque-centrale.dj/">Banque Centrale de Djibouti</a>.
   *
   * <p>
   * Note that the SWIFT expression seems to indicate the presence of a check digit at the end of the BBAN. As we have
   * not found any documentation clarifying this point we will consider for the time being that this is not the case (so
   * the last two characters belong to the account number).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: DJ21 0001 0000 0001 5400 0100 186</li>
   * <li>BBAN: 00010000000154000100186</li>
   * <li>Bank identifier: 00010</li>
   * <li>Branch identifier: 00000</li>
   * <li>Account number: 0154000100186</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  DJ(IsoCountry.DJ, "5!n5!n11!n2!n", 0, 5, 5, 10, null, null, 10, 23),

  /**
   * BBAN structure for Denmark as defined by <a href="https://finansdanmark.dk/">Finance Denmark</a>.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: DK50 0040 0440 1162 43</li>
   * <li>BBAN: 00400440116243</li>
   * <li>Bank identifier: 0040</li>
   * <li>Account number: 0440116243</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href=
   *      "https://financedenmark.dk/hard-figures/financial-institutions-branches-employees/payments/international-payments-iban/">International
   *      payments - IBAN</a>
   */
  DK(IsoCountry.DK, "4!n9!n1!n", 0, 4, null, null, null, null, 4, 14),

  /**
   * BBAN structure for the Dominican Republic as defined by the <a href="https://www.bancentral.gov.do">Banco Central
   * de la República Dominicana</a> (Central Bank of the Dominican Republic).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: DO28 BAGR 0000 0001 2124 5361 1324</li>
   * <li>BBAN: BAGR00000001212453611324</li>
   * <li>Bank identifier: BAGR</li>
   * <li>Account number: 00000001212453611324</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  DO(IsoCountry.DO, "4!c20!n", 0, 4, null, null, null, null, 4, 24),

  /**
   * BBAN structure for Estonia as defined by the <a href="https://www.pangaliit.ee">Eesti Pangaliit</a> (Estonian
   * Banking Association - EBA).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: EE38 2200 2210 2014 5685</li>
   * <li>BBAN: 2200221020145685</li>
   * <li>Bank identifier: 22</li>
   * <li>Account number: 00221020145685</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://pangaliit.ee/files/ibaninglise.pdf"></a>
   */
  EE(IsoCountry.EE, "2!n2!n11!n1!n", 0, 2, null, null, null, null, 2, 16),

  /**
   * BBAN structure for Egypt as defined by the <a href="https://www.cbe.org.eg">Central Bank of Egypt</a> (CBE).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: EG38 0019 0005 0000 0000 2631 8000 2</li>
   * <li>BBAN: 0019000500000000263180002</li>
   * <li>Bank identifier: 0019</li>
   * <li>Branch identifier: 0005</li>
   * <li>Account number: 00000000263180002</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  EG(IsoCountry.EG, "4!n4!n17!n", 0, 4, 4, 8, null, null, 8, 25),

  /**
   * BBAN structure for Spain as defined by the <a href="https://www.aebanca.es">Asociación Española de Banca</a> (AEB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: ES91 2100 0418 4502 0005 1332</li>
   * <li>BBAN: 21000418450200051332</li>
   * <li>Bank identifier: 2100</li>
   * <li>Branch identifier: 0418</li>
   * <li>National check digit: 45</li>
   * <li>Account number: 0200051332</li>
   * </ul>
   *
   * @see <a href="https://www.iban.es/estructura-del-iban.html">Estructura del IBAN en el España</a>
   */
  ES(IsoCountry.ES, "4!n4!n1!n1!n10!n", 0, 4, 4, 8, 8, 10, 10, 20),

  /**
   * BBAN structure for Finland as defined by the <a href="https://www.finanssiala.fi">Finanssiala ry</a> (Federation of
   * Finnish Financial Services - FA).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: FI21 1234 5600 0007 85</li>
   * <li>BBAN: 12345600000785</li>
   * <li>Bank identifier: 123</li>
   * <li>Account number: 45600000785</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.finanssiala.fi/wp-content/uploads/2021/03/IBAN-and-BIC-in-payments-1.pdf">International Bank
   *      Account Number (IBAN) and Bank Identifier Code (BIC) in payment</a>
   */
  FI(IsoCountry.FI, "3!n11!n", 0, 3, null, null, null, null, 3, 14, AX),

  /**
   * BBAN structure for Faroe Islands as defined by <a href="https://financedenmark.dk/">Finance Denmark</a>.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: FO62 6460 0001 6316 34</li>
   * <li>BBAN: 64600001631634</li>
   * <li>Bank identifier: 6460</li>
   * <li>Account number: 0001631634</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href=
   *      "https://financedenmark.dk/hard-figures/financial-institutions-branches-employees/payments/international-payments-iban/">International
   *      payments - IBAN</a>
   */
  FO(IsoCountry.FO, "4!n9!n1!n", 0, 4, null, null, null, null, 4, 14),

  /**
   * BBAN structure for France as defined by the <a href="https://www.cfonb.org/">Comité Français d'Organisation et de
   * Normalisation Bancaires</a> (CFONB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: FR14 2004 1010 0505 0001 3M02 606</li>
   * <li>BBAN: 20041010050500013M02606</li>
   * <li>Bank identifier: 20041</li>
   * <li>Branch identifier: 01005</li>
   * <li>Account number: 0500013M026</li>
   * <li>National check digit: 06</li>
   * </ul>
   *
   * @see <a href="https://www.cfonb.org/organisation-des-echanges/referentiel">Référentiel</a>
   */
  FR(IsoCountry.FR, "5!n5!n11!c2!n", 0, 5, 5, 10, 21, 23, 10, 21, GF, GP, MQ, RE, PF, TF, YT, NC, BL, MF, PM, WF),

  /**
   * BBAN structure for the United Kingdom as defined by <a href="https://www.ukpayments.org.uk">Payments UK Management
   * Ltd</a>.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: GB29 NWBK 6016 1331 9268 19</li>
   * <li>BBAN: NWBK60161331926819</li>
   * <li>Bank identifier: NWBK</li>
   * <li>Branch identifier: 601613</li>
   * <li>Account number: 31926819</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href=
   *      "https://www.wearepay.uk/wp-content/uploads/2018/10/Pay.UK-Standard-Format-of-the-IBAN-issued-in-the-UK-October18-1.pdf">Format
   *      of the IBAN issued in the UK</a>
   */
  GB(IsoCountry.GB, "4!a6!n8!n", 0, 4, 4, 10, null, null, 10, 18, IM, JE, GG),

  /**
   * BBAN structure for Georgia as defined by the <a href="https://www.nbg.gov.ge">National Bank of Georgia</a> (NBG).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: GE29 NB00 0000 0101 9049 17</li>
   * <li>BBAN: NB0000000101904917</li>
   * <li>Bank identifier: NB</li>
   * <li>Account number: 0000000101904917</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://nbg.gov.ge/en/page/iban-international-bank-account-number">IBAN - International Bank Account
   *      Number</a>
   */
  GE(IsoCountry.GE, "2!a16!n", 0, 2, null, null, null, null, 2, 18),

  /**
   * BBAN structure for Gibraltar as defined by the <a href="https://fsc.gi">Gibraltar Financial Services Commission</a>
   * (GFSC).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: GI75 NWBK 0000 0000 7099 453</li>
   * <li>BBAN: NWBK000000007099453</li>
   * <li>Bank identifier: NWBK</li>
   * <li>Account number: 000000007099453</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  GI(IsoCountry.GI, "4!a15!c", 0, 4, null, null, null, null, 4, 19),

  /**
   * BBAN structure for Greenland as defined by the <a href="https://financedenmark.dk/">Finance Denmark</a>.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: GL89 6471 0001 0002 06</li>
   * <li>BBAN: 64710001000206</li>
   * <li>Bank identifier: 6471</li>
   * <li>Account number: 0001000206</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href=
   *      "https://financedenmark.dk/hard-figures/financial-institutions-branches-employees/payments/international-payments-iban/">International
   *      payments - IBAN</a>
   */
  GL(IsoCountry.GL, "4!n9!n1!n", 0, 4, null, null, null, null, 4, 14),

  /**
   * BBAN structure for Greece as defined by the <a href="https://www.hba.gr">Hellenic Bank Association</a> (HBA).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: GR16 0110 1250 0000 0001 2300 695</li>
   * <li>BBAN: 01101250000000012300695</li>
   * <li>Bank identifier: 011</li>
   * <li>Branch identifier: 0125</li>
   * <li>Account number: 0000000012300695</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.hba.gr/UplFiles/iban/IBAN-en.pdf">International Bank Account Number (IBAN)</a>
   */
  GR(IsoCountry.GR, "3!n4!n16!c", 0, 3, 3, 7, null, null, 7, 23),

  /**
   * BBAN structure for Guatemala as defined by the <a href="https://www.banguat.gob.gt">Banco de Guatemala</a> (Bank of
   * Guatemala).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: GT82 TRAJ 0102 0000 0012 1002 9690</li>
   * <li>BBAN: TRAJ01020000001210029690</li>
   * <li>Bank identifier: TRAJ</li>
   * <li>Currency code: 01</li>
   * <li>Account type: 02</li>
   * <li>Account number: 0000001210029690</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  GT(IsoCountry.GT, "4!c20!c", 0, 4, null, null, null, null, 8, 24),

  /**
   * BBAN structure for Croatia as defined by the <a href="https://www.hnb.hr">Hrvatska narodna banka</a> (Croatian
   * National Bank - HNB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: HR12 1001 0051 8630 0016 0</li>
   * <li>BBAN: 10010051863000160</li>
   * <li>Bank identifier: 1001005</li>
   * <li>Account number: 1863000160</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  HR(IsoCountry.HR, "7!n10!n", 0, 7, null, null, null, null, 7, 17),

  /**
   * BBAN structure for Hungary as defined by the <a href="https://bankszovetseg.hu">Magyar Bankszövetség</a> (Hungarian Banking
   * Association).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: HU42 1177 3016 1111 1018 0000 0000</li>
   * <li>BBAN: 117730161111101800000000</li>
   * <li>Bank identifier: 117</li>
   * <li>Branch identifier: 7301</li>
   * <li>Account number: 6111110180000000</li>
   * <li>National check digit: 0</li>
   * </ul>
   */
  HU(IsoCountry.HU, "3!n4!n1!n15!n1!n", 0, 3, 3, 7, 23, 24, 7, 23),

  /**
   * BBAN structure for Ireland as defined by the <a href="https://www.bpfi.ie">Banking and Payments Federation
   * Ireland</a> (BPFI).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: IE29 AIBK 9311 5212 3456 78</li>
   * <li>BBAN: AIBK93115212345678</li>
   * <li>Bank identifier: AIBK</li>
   * <li>Branch identifier: 931152</li>
   * <li>Account number: 12345678</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  IE(IsoCountry.IE, "4!a6!n8!n", 0, 4, 4, 10, null, null, 10, 18),

  /**
   * BBAN structure for Israel as defined by the <a href="https://www.boi.org.il">Bank of Israel</a> (BOI).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: IL62 0108 0000 0009 9999 999</li>
   * <li>BBAN: 010800000099999999</li>
   * <li>Bank identifier: 010</li>
   * <li>Branch identifier: 800</li>
   * <li>Account number: 0000099999999</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.boi.org.il/he/ConsumerInformation/ToolsAndCalculators/Pages/Iban.aspx">IBAN</a>
   */
  IL(IsoCountry.IL, "3!n3!n13!n", 0, 3, 3, 6, null, null, 6, 19),

  /**
   * BBAN structure for Iraq as defined by the <a href="https://cbi.iq">Central Bank of Iraq</a> (CBI).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: IQ98 NBIQ 8501 2345 6789 012</li>
   * <li>BBAN: NBIQ850123456789012</li>
   * <li>Bank identifier: NBIQ</li>
   * <li>Branch identifier: 850</li>
   * <li>Account number: 123456789012</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  IQ(IsoCountry.IQ, "4!a3!n12!n", 0, 4, 4, 7, null, null, 7, 19),

  /**
   * BBAN structure for Iceland as defined by the <a href="https://www.sedlabanki.is/">Seðlabanki Íslands</a>
   * (Central Bank of Iceland - CB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: IS14 0159 2600 7654 5510 7303 39</li>
   * <li>BBAN: 0159260076545510730339</li>
   * <li>Bank identifier: 01</li>
   * <li>Branch identifier: 59</li>
   * <li>Account type: 26</li>
   * <li>Account number: 007654</li>
   * <li>National identification number: 5510730339</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  IS(IsoCountry.IS, "4!n2!n6!n10!n", 0, 2, 2, 4, null, null, 6, 12),

  /**
   * BBAN structure for Italy as defined by the <a href="https://www.abi.it">Associazione Bancaria Italiana</a> (ABI).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: IT60 X054 2811 1010 0000 0123 456</li>
   * <li>BBAN: X0542811101000000123456</li>
   * <li>National check digit: X</li>
   * <li>Bank identifier: 05428</li>
   * <li>Branch identifier: 11101</li>
   * <li>Account number: 000000123456</li>
   * </ul>
   *
   * @see <a href="https://www.codiceiban.it/">IBAN, ABI, CAB e coordinate bancarie</a>
   */
  IT(IsoCountry.IT, "1!a5!n5!n12!c", 1, 6, 6, 11, 0, 1, 11, 23),

  /**
   * BBAN structure for Jordan as defined by the <a href="https://www.cbj.gov.jo/">Central Bank of Jordan</a> (CBJ).
   *
   * <p>
   * Note that data in IBAN registry (Release 92 – May 2022) is wrong regarding the BBAN structure (the branch code is
   * missing).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: JO94 CBJO 0010 0000 0000 0131 0003 02</li>
   * <li>BBAN: CBJO0010000000000131000302</li>
   * <li>Bank identifier: CBJO</li>
   * <li>Branch identifier: 0010</li>
   * <li>Account number: 000000000131000302</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.cbj.gov.jo/Pages/viewpage.aspx?pageID=266">International Bank Account (IBAN) in Jordan</a>
   */
  JO(IsoCountry.JO, "4!a4!n18!c", 0, 4, 4, 8, null, null, 8, 26),

  /**
   * BBAN structure for Kuwait as defined by the <a href="https://www.cbk.gov.kw/">Central Bank of Kuwait</a> (CBK).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: KW81 CBKU 0000 0000 0000 1234 5601 01</li>
   * <li>BBAN: CBKU0000000000001234560101</li>
   * <li>Bank identifier: CBKU</li>
   * <li>Account number: 0000000000001234560101</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.cbk.gov.kw/en/images/iban-9721_v60_tcm10-9721.pdf">Information about International Bank Account
   *      Number (IBAN) Implementation in Kuwait</a>
   */
  KW(IsoCountry.KW, "4!a22!c", 0, 4, null, null, null, null, 4, 26),

  /**
   * BBAN structure for Kazakhstan as defined by the <a href="https://nationalbank.kz">Qazaqstan Ulttyq Banki</a>
   * (National Bank of Kazakhstan).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: KZ86 125K ZT50 0410 0100</li>
   * <li>BBAN: 125KZT5004100100</li>
   * <li>Bank identifier: 125</li>
   * <li>Account number: KZT5004100100</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  KZ(IsoCountry.KZ, "3!n13!c", 0, 3, null, null, null, null, 3, 16),

  /**
   * BBAN structure for Lebanon as defined by the <a href="https://bdl.gov.lb">Banque du Liban</a> (Bank of Lebanon -
   * BDL).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: LB62 0999 0000 0001 0019 0122 9114</li>
   * <li>BBAN: 099900000001001901229114</li>
   * <li>Bank identifier: 0999</li>
   * <li>Account number: 00000001001901229114</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.bdl.gov.lb/downloads/download/56/en">International Bank Account Number (IBAN)</a>
   */
  LB(IsoCountry.LB, "4!n20!c", 0, 4, null, null, null, null, 4, 24),

  /**
   * BBAN structure for Saint Lucia as defined by the <a href="https://slbs.dev/en/">Saint Lucia Bureau of Standards</a>
   * (SLBS).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: LC55 HEMM 0001 0001 0012 0012 0002 3015</li>
   * <li>BBAN: HEMM000100010012001200023015</li>
   * <li>Bank identifier: HEMM</li>
   * <li>Account number: 000100010012001200023015</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  LC(IsoCountry.LC, "4!a24!c", 0, 4, null, null, null, null, 4, 28),

  /**
   * BBAN structure for Liechtenstein as defined by the <a href="https://www.bankenverband.li">Liechtensteinischer
   * Bankenverband</a> (Liechtenstein Bankers Association).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: LI21 0881 0000 2324 013A A</li>
   * <li>BBAN: 088100002324013AA</li>
   * <li>Bank identifier: 08810</li>
   * <li>Account number: 0002324013AA</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.bankenverband.li/application/files/8015/4409/0448/Folder_IBAN_de.pdf">BAN – Die internationale
   *      bankkontonummer</a>
   */
  LI(IsoCountry.LI, "5!n12!c", 0, 5, null, null, null, null, 5, 17),

  /**
   * BBAN structure for Lithuania as defined by the <a href="https://www.lb.lt">Lietuvos Bankas</a> (Bank of Lithuania).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: LT12 1000 0111 0100 1000</li>
   * <li>BBAN: 1000011101001000</li>
   * <li>Bank identifier: 10000</li>
   * <li>Account number: 11101001000</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.lb.lt/en/iban-and-financial-institution-codes">IBAN and financial institution codes</a>
   */
  LT(IsoCountry.LT, "5!n11!n", 0, 5, null, null, null, null, 5, 16),

  /**
   * BBAN structure for Luxembourg as defined by the <a href="https://www.abbl.lu">Association des Banques et Banquiers
   * Luxembourg</a> (Luxembourg Bankers’ Association - ABBL).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: LU28 0019 4006 4475 0000</li>
   * <li>BBAN: 0019400644750000</li>
   * <li>Bank identifier: 001</li>
   * <li>Account number: 9400644750000</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.abbl.lu/en/professionals/toolbox/payment-standards/iban-and-bic-codes">Luxembourg register of
   *      IBAN/BIC codes</a>
   */
  LU(IsoCountry.LU, "3!n13!c", 0, 3, null, null, null, null, 3, 16),

  /**
   * BBAN structure for Latvia as defined by the <a href="https://www.bank.lv">Latvijas Banka</a> (Bank of Latvia).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: LV80 BANK 0000 4351 9500 1</li>
   * <li>BBAN: BANK0000435195001</li>
   * <li>Bank identifier: BANK</li>
   * <li>Account number: 0000435195001</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.bank.lv/en/tasks/payment-systems/identification-of-bic-by-iban">On identification of BIC by
   *      IBAN</a>
   */
  LV(IsoCountry.LV, "4!a13!c", 0, 4, null, null, null, null, 4, 17),

  /**
   * BBAN structure for Libya as defined by the <a href="https://cbl.gov.ly">Central Bank of Libya</a> (CBL).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: LY83 0020 4800 0020 1001 2036 1</li>
   * <li>BBAN: 002048000020100120361</li>
   * <li>Bank identifier: 002</li>
   * <li>Branch identifier: 048</li>
   * <li>Account number: 000020100120361</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  LY(IsoCountry.LY, "3!n3!n15!n", 0, 3, 3, 6, null, null, 6, 21),

  /**
   * BBAN structure for Monaco as defined by the <a href="https://www.amaf.mc">Association Monégasque des Activités
   * Financières</a> (AMAF).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: MC58 1122 2000 0101 2345 6789 030</li>
   * <li>BBAN: 11222000010123456789030</li>
   * <li>Bank identifier: 11222</li>
   * <li>Branch identifier: 00001</li>
   * <li>Account number: 01234567890</li>
   * <li>National check digit: 30</li>
   * </ul>
   *
   * @see <a href="https://www.cfonb.org/organisation-des-echanges/referentiel">Référentiel</a>
   */
  MC(IsoCountry.MC, "5!n5!n11!c2!n", 0, 5, 5, 10, 21, 23, 10, 21),

  /**
   * BBAN structure for Moldova as defined by the <a href="https://www.bnm.md">Banca Naţională a Moldovei</a> (National
   * Bank of Moldova - BNM).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: MD24 AG00 0225 1000 1310 4168</li>
   * <li>BBAN: AG000225100013104168</li>
   * <li>Bank identifier: AG</li>
   * <li>Account number: 000225100013104168</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * <a href="https://www.bnm.md/en/content/iban">IBAN</a>
   */
  MD(IsoCountry.MD, "2!c18!c", 0, 2, null, null, null, null, 2, 20),

  /**
   * BBAN structure for Montenegro as defined by the Association of Montenegrin Banks.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: ME25 5050 0001 2345 6789 51</li>
   * <li>BBAN: 505000012345678951</li>
   * <li>Bank identifier: 505</li>
   * <li>Account number: 0000123456789</li>
   * <li>National check digit: 51</li>
   * <li>Branch identifier: N/A</li>
   * </ul>
   */
  ME(IsoCountry.ME, "3!n13!n2!n", 0, 3, null, null, 16, 18, 3, 16),

  /**
   * BBAN structure for Macedonia as defined by the <a href="https://www.nbrm.mk/">Narodna Banka na Republika Severna
   * Makedonija</a> (National Bank of the Republic of North Macedonia - NBRSM).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: MK07 2501 2000 0058 984</li>
   * <li>BBAN: 250120000058984</li>
   * <li>Bank identifier: 250</li>
   * <li>Account number: 1200000589</li>
   * <li>National check digit: 84</li>
   * <li>Branch identifier: N/A</li>
   * </ul>
   */
  MK(IsoCountry.MK, "3!n10!c2!n", 0, 3, null, null, 13, 15, 3, 13),

  /**
   * BBAN structure for Mauritania as defined by the <a href="https://www.bcm.mr/">Banque Centrale de Mauritanie</a>
   * (Central Bank of Mauritania - BCM).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: MR13 0002 0001 0100 0012 3456 753</li>
   * <li>BBAN: 00020001010000123456753</li>
   * <li>Bank identifier: 00020</li>
   * <li>Branch identifier: 00101</li>
   * <li>Account number: 00001234567</li>
   * <li>National check digit: 53</li>
   * </ul>
   */
  MR(IsoCountry.MR, "5!n5!n11!n2!n", 0, 5, 5, 10, 21, 23, 10, 21),

  /**
   * BBAN structure for Malta as defined by the <a href="https://www.maltabankers.org">Malta Bankers’ Association</a>
   * (MBA).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: MT84 MALT 0110 0001 2345 MTLC AST0 01S</li>
   * <li>BBAN: MALT011000012345MTLCAST001S</li>
   * <li>Bank identifier: MALT</li>
   * <li>Branch identifier: 01100</li>
   * <li>Account number: 0012345MTLCAST001S</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href=
   *      "https://www.maltabankers.org/wp-content/uploads/2019/11/International-Bank-Account-Number-IBAN-brochure.doc">International
   *      Bank Account Number</a>
   */
  MT(IsoCountry.MT, "4!a5!n18!c", 0, 4, 4, 9, null, null, 9, 27),

  /**
   * BBAN structure for Mauritius as defined by the <a href="https://www.bom.mu/">Bank of Mauritius</a> (BOM).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: MU17 BOMM 0101 1010 3030 0200 000M UR</li>
   * <li>BBAN: BOMM0101101030300200000MUR</li>
   * <li>Bank identifier: BOMM01</li>
   * <li>Branch identifier: 01</li>
   * <li>Account number: 101030300200</li>
   * <li>Reserved: 000</li>
   * <li>Account currency: MUR</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  MU(IsoCountry.MU, "4!a2!n2!n12!n3!n3!a", 0, 6, 6, 8, null, null, 8, 20),

  /**
   * BBAN structure for the Netherlands as defined by the <a href="https://www.betaalvereniging.nl">Betaalvereniging
   * Nederland</a> (Dutch Payment Association).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: NL91 ABNA 0417 1643 00</li>
   * <li>BBAN: ABNA0417164300</li>
   * <li>Bank identifier: ABNA</li>
   * <li>Account number: 0417164300</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.betaalvereniging.nl/en/payment-products-services/iban/">IBAN</a>
   */
  NL(IsoCountry.NL, "4!a10!n", 0, 4, null, null, null, null, 4, 14),

  /**
   * BBAN structure for Norway as defined by the <a href="https://www.dnb.no">DNB ASA</a> ((formerly DnB NOR ASA).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: NO93 8601 1117 947</li>
   * <li>BBAN: 86011117947</li>
   * <li>Bank identifier: 8601</li>
   * <li>Account number: 111794</li>
   * <li>National check digit: 7</li>
   * <li>Branch identifier: N/A</li>
   * </ul>
   */
  NO(IsoCountry.NO, "4!n6!n1!n", 0, 4, null, null, 10, 11, 4, 10),

  /**
   * BBAN structure for Pakistan as defined by the <a href="https://www.sbp.org.pk/">State Bank of Pakistan</a> (SBP).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: PK36 SCBL 0000 0011 2345 6702</li>
   * <li>BBAN: SCBL0000001123456702</li>
   * <li>Bank identifier: SCBL</li>
   * <li>Account number: 0000001123456702</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.sbp.org.pk/psd/2012/IBAN-Guidelines-CL02-2012.pdf">Guidelines: IBAN Implementation in
   *      Pakistan</a>
   */
  PK(IsoCountry.PK, "4!a16!c", 0, 4, null, null, null, null, 4, 20),

  /**
   * BBAN structure for Poland as defined by the <a href="https://www.nbp.pl/">Narodowy Bank Polski</a> (National Bank
   * of Poland - NBP).
   *
   * <p>
   * Note that data in IBAN registry (Release 92 – May 2022) is wrong regarding the BBAN structure (wrong bank
   * identifier).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: PL61 1090 1014 0000 0712 1981 2874</li>
   * <li>BBAN: 109010140000071219812874</li>
   * <li>Bank identifier: 109</li>
   * <li>Branch identifier: 0101</li>
   * <li>National check digit: 4</li>
   * <li>Account number: 0000071219812874</li>
   * </ul>
   *
   * @see <a href="https://www.nbp.pl/homen.aspx?f=/iban_bic.html">IBAN - BIC</a>
   */
  PL(IsoCountry.PL, "8!n16!n", 0, 3, 3, 7, 7, 8, 8, 24),

  /**
   * BBAN structure for the State of Palestine as defined by the <a href="https://www.pma.ps">Palestine Monetary
   * Authority</a> (PMA).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: PS92 PALS 0000 0000 0400 1234 5670 2</li>
   * <li>BBAN: PALS000000000400123456702</li>
   * <li>Bank identifier: PALS</li>
   * <li>Account number: 000000000400123456702</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.pma.ps/en/English-IBAN">English IBAN</a>
   */
  PS(IsoCountry.PS, "4!a21!c", 0, 4, null, null, null, null, 4, 25),

  /**
   * BBAN structure for Portugal as defined by <a href="https://www.sibs.com/">SIBS</a>.
   *
   * <p>
   * Note that data in IBAN registry (Release 92 – May 2022) is wrong regarding the BBAN structure (missing branch
   * identifier).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: PT50 0002 0123 1234 5678 9015 4</li>
   * <li>BBAN: 000201231234567890154</li>
   * <li>Bank identifier: 0002</li>
   * <li>Branch identifier: 0123</li>
   * <li>Account number: 12345678901</li>
   * <li>National check digit: 54</li>
   * </ul>
   *
   * @see <a href=
   *      "https://www.bportugal.pt/sites/default/files/anexos/documentos-relacionados/international_bank_account_number_en_dpgdr_vr_20180226.pdf">IBAN
   *      | Specifications and validation procedures</a>
   */
  PT(IsoCountry.PT, "4!n4!n11!n2!n", 0, 4, 4, 8, 19, 21, 8, 19),

  /**
   * BBAN structure for Qatar as defined by the <a href="https://www.qcb.gov.qa">Qatar Central Bank</a> (QCB).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: QA58 DOHB 0000 1234 5678 90AB CDEF G</li>
   * <li>BBAN: DOHB00001234567890ABCDEFG</li>
   * <li>Bank identifier: DOHB</li>
   * <li>Account number: 00001234567890ABCDEFG</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.cbq.qa/EN/Advice-and-information/Pages/IBAN-FAQ.aspx">FAQ - IBAN</a>
   */
  QA(IsoCountry.QA, "4!a21!c", 0, 4, null, null, null, null, 4, 25),

  /**
   * BBAN structure for Romania as defined by the <a href="https://www.bnr.ro/">Banca Națională a României</a> (National
   * Bank of Romania - BNR).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: RO49 AAAA 1B31 0075 9384 0000</li>
   * <li>BBAN: AAAA1B31007593840000</li>
   * <li>Bank identifier: AAAA</li>
   * <li>Account number: 1B31007593840000</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.bnr.ro/files/d/Legislatie/EN/Reg_IBAN.pdf">Regulation regarding the usage of the IBAN codes in
   *      Romania</a>
   */
  RO(IsoCountry.RO, "4!a16!c", 0, 4, null, null, null, null, 4, 20),

  /**
   * BBAN structure for Serbia as defined by the <a href="https://nbs.rs">Narodna banka Srbije</a> (National bank of
   * Serbia - NBS).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: RS35 2600 0560 1001 6113 79</li>
   * <li>BBAN: 260005601001611379</li>
   * <li>Bank identifier: 260</li>
   * <li>Account number: 0056010016113</li>
   * <li>National check digit: 79</li>
   * <li>Branch identifier: N/A</li>
   * </ul>
   */
  RS(IsoCountry.RS, "3!n13!n2!n", 0, 3, null, null, 16, 18, 3, 16),

  /**
   * BBAN structure for Russia as defined by the <a href="https://cbr.ru/">Central Bank of the Russian Federation</a>
   * (CBR).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: RU17 0445 2522 5408 1781 0538 0913 1041 9</li>
   * <li>BBAN: 04452522540817810538091310419</li>
   * <li>Bank identifier: 044525225</li>
   * <li>Branch identifier: 40817</li>
   * <li>Account number: 810538091310419</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  RU(IsoCountry.RU, "9!n5!n15!c", 0, 9, 9, 14, null, null, 14, 29),

  /**
   * BBAN structure for Saudi Arabia as defined by the <a href="https://sama.gov.sa">Saudi Arabian Monetary
   * Authority</a> (SAMA).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: SA03 8000 0000 6080 1016 7519</li>
   * <li>BBAN: 80000000608010167519</li>
   * <li>Bank identifier: 80</li>
   * <li>Account number: 000000608010167519</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  SA(IsoCountry.SA, "2!n18!c", 0, 2, null, null, null, null, 2, 20),

  /**
   * BBAN structure for Seychelles as defined by the <a href="https://www.cbs.sc/">Central Bank of Seychelles</a> (CBS).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: SC18 SSCB 1101 0000 0000 0000 1497 USD</li>
   * <li>BBAN: SSCB11010000000000001497USD</li>
   * <li>Bank identifier: SSCB11</li>
   * <li>Branch identifier: 01</li>
   * <li>Account number: 0000000000001497</li>
   * <li>Account currency: USD</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  SC(IsoCountry.SC, "4!a2!n2!n16!n3!a", 0, 6, 6, 8, null, null, 8, 24),

  /**
   * BBAN structure for Sudan as defined by the <a href="https://cbos.gov.sd/">Central Bank of Sudan</a> (CBOS).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: SD21 2901 0501 2340 01</li>
   * <li>BBAN: 29010501234001</li>
   * <li>Bank identifier: 29</li>
   * <li>Account number: 010501234001</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  SD(IsoCountry.SD, "2!n12!n", 0, 2, null, null, null, null, 2, 14),

  /**
   * BBAN structure for Sweden as defined by the <a href="https://www.swedishbankers.se">Svenska Bankföreningen</a>
   * (Swedish Bankers’ Association).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: SE45 5000 0000 0583 9825 7466</li>
   * <li>BBAN: 50000000058398257466</li>
   * <li>Bank identifier: 500</li>
   * <li>Account number: 0000005839825746</li>
   * <li>National check digit: 6</li>
   * <li>Branch identifier: N/A</li>
   * </ul>
   *
   * <a href="https://www.bankinfrastruktur.se/framtidens-betalningsinfrastruktur/iban-och-svenskt-nationellt-kontonummer">IBAN
   * och svenskt nationellt kontonummer</a>
   */
  SE(IsoCountry.SE, "3!n16!n1!n", 0, 3, null, null, 19, 20, 3, 19),

  /**
   * BBAN structure for Slovenia as defined by the <a href="https://www.bsi.si">Banka Slovenije</a> (Bank of Slovenia).
   *
   * <p>
   * Note that data in IBAN registry (Release 92 – May 2022) is wrong regarding the BBAN structure (missing branch
   * identifier).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: SI56 2633 0001 2039 086</li>
   * <li>BBAN: 263300012039086</li>
   * <li>Bank identifier: 26</li>
   * <li>Branch identifier: 330</li>
   * <li>Account number: 00120390</li>
   * <li>National check digit: 86</li>
   * </ul>
   */
  SI(IsoCountry.SI, "5!n8!n2!n", 0, 2, 2, 5, 13, 15, 5, 13),

  /**
   * BBAN structure for Slovakia as defined by the <a href="https://www.nbs.sk">Národná banka Slovenska</a> (National
   * Bank of Slovakia - NBS).
   *
   * <p>
   * Note that Account number prefix is merged in the account number.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: SK31 1200 0000 1987 4263 7541</li>
   * <li>BBAN: 12000000198742637541</li>
   * <li>Bank identifier: 1200</li>
   * <li>Account number: 0000198742637541</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://nbs.sk/en/payments/general-information/iban/">IBAN</a>
   */
  SK(IsoCountry.SK, "4!n6!n10!n", 0, 4, null, null, null, null, 4, 20),

  /**
   * BBAN structure for San Marino as defined by the <a href="https://www.bcsm.sm/">Banca Centrale della Repubblica di
   * San Marino</a> (Central Bank of the Republic of San Marino - BCSM).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: SM86 U032 2509 8000 0000 0270 100</li>
   * <li>BBAN: U0322509800000000270100</li>
   * <li>National check digit: U</li>
   * <li>Bank identifier: 03225</li>
   * <li>Branch identifier: 09800</li>
   * <li>Account number: 000000270100</li>
   * </ul>
   */
  SM(IsoCountry.SM, "1!a5!n5!n12!c", 1, 6, 6, 11, 0, 1, 11, 23),

  /**
   * BBAN structure for São Tomé and Príncipe as defined by the <a href="https://bcstp.st/">Banco Central de Sao Tome e
   * Principe</a> (Central Bank of São Tomé and Príncipe - BCSTP).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: ST23 0001 0001 0051 8453 1014 6</li>
   * <li>BBAN: 000100010051845310146</li>
   * <li>Bank identifier: 0001</li>
   * <li>Branch identifier: 0001</li>
   * <li>Account number: 0051845310146</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  ST(IsoCountry.ST, "8!n11!n2!n", 0, 4, 4, 8, null, null, 8, 21),

  /**
   * BBAN structure for El Salvador as defined by the <a href="https://www.bcr.gob.sv/">Banco Central de Reserva de El
   * Salvador</a> (Central Reserve Bank of El Salvador).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: SV62 CENR 0000 0000 0000 0070 0025</li>
   * <li>BBAN: CENR00000000000000700025</li>
   * <li>Bank identifier: CENR</li>
   * <li>Account number: 00000000000000700025</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  SV(IsoCountry.SV, "4!a20!n", 0, 4, null, null, null, null, 4, 24),

  /**
   * BBAN structure for Timor-Leste as defined by the <a href="https://www.bancocentral.tl">Banco Central de
   * Timor-Leste</a> (East Timor Central Bank - BCTL).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: TL38 0080 0123 4567 8910 157</li>
   * <li>BBAN: 0080012345678910157</li>
   * <li>Bank identifier: 008</li>
   * <li>Account number: 00123456789101</li>
   * <li>National check digit: 57</li>
   * <li>Branch identifier: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.bancocentral.tl/en/go/timor-leste-iban">Timor-Leste IBAN</a>
   */
  TL(IsoCountry.TL, "3!n14!n2!n", 0, 3, null, null, 17, 19, 3, 17),

  /**
   * BBAN structure for Tunisia as defined by the <a href="https://www.apbt.org.tn/">Association Professionnelle
   * Tunisienne des Banques et des Etablissements Financiers</a> (Tunisia’s Professional Association for Banks and
   * Financial Institutions - APTBEF).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: TN59 1000 6035 1835 9847 8831</li>
   * <li>BBAN: 10006035183598478831</li>
   * <li>Bank identifier: 10</li>
   * <li>Branch identifier: 006</li>
   * <li>Account number: 0351835984788</li>
   * <li>National check digit: 31</li>
   * </ul>
   */
  TN(IsoCountry.TN, "2!n3!n13!n2!n", 0, 2, 2, 5, 18, 20, 5, 18),

  /**
   * BBAN structure for Türkiye as defined by the <a href="https://www.tcmb.gov.tr/">Türkiye Cumhuriyet Merkez
   * Bankası</a> (Central Bank of the Republic of Turkey - CBRT).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: TR33 0006 1005 1978 6457 8413 26</li>
   * <li>BBAN: 0006100519786457841326</li>
   * <li>Bank identifier: 00061</li>
   * <li>Reserved: 0</li>
   * <li>Account number: 0519786457841326</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   *
   * @see <a href="https://www.tcmb.gov.tr/wps/wcm/connect/EN/TCMB+EN/Bottom+Menu/IBAN/Information+Booklet">Information
   *      Booklet</a>
   */
  TR(IsoCountry.TR, "5!n1!n16!c", 0, 5, null, null, null, null, 6, 22),

  /**
   * BBAN structure for Ukraine as defined by the <a href="https://www.ukrswift.org">Association UkrSWIFT</a>.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: UA21 3223 1300 0002 6007 2335 6600 1</li>
   * <li>BBAN: 3223130000026007233566001</li>
   * <li>Bank identifier: 322313</li>
   * <li>Account number: 0000026007233566001</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  UA(IsoCountry.UA, "6!n19!c", 0, 6, null, null, null, null, 6, 25),

  /**
   * BBAN structure for the Vatican City State as defined by the <a href="https://www.aif.va">Autorita di Informazione
   * Finanziaria</a> (AIF - Financial Information Authority).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: VA59 001 1230 0001 2345 678</li>
   * <li>BBAN: 001123000012345678</li>
   * <li>Bank identifier: 001</li>
   * <li>Account number: 123000012345678</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  VA(IsoCountry.VA, "3!n15!n", 0, 3, null, null, null, null, 3, 18),

  /**
   * BBAN structure for the british Virgin Islands as defined by the <a href="https://vg.vpbank.com">VP Bank House</a>.
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: VG96 VPVG 0000 0123 4567 8901</li>
   * <li>BBAN: VPVG0000012345678901</li>
   * <li>Bank identifier: VPVG</li>
   * <li>Account number: 0000012345678901</li>
   * <li>Branch identifier: N/A</li>
   * <li>National check digit: N/A</li>
   * </ul>
   */
  VG(IsoCountry.VG, "4!a16!n", 0, 4, null, null, null, null, 4, 20),

  /**
   * BBAN structure for Kosovo as defined by the <a href="https://www.bqk-kos.org/">Banka Qendrore e Kosovës</a> (Central Bank
   * of the Republic of Kosovo).
   *
   * <p>
   * Example:
   * <ul>
   * <li>IBAN: XK05 1212 0123 4567 8906</li>
   * <li>BBAN: 1212012345678906</li>
   * <li>Bank identifier: 12</li>
   * <li>Branch identifier: 12</li>
   * <li>Account number: 0123456789</li>
   * <li>National check digit: 06</li>
   * </ul>
   *
   * @see <a href=
   *      "https://www.bqk-kos.org/repository/docs/korniza_ligjore/shqip/Sistemi%20i%20Numrave%20Standard%20te%20Llogarive%20Bankare.pdf">Rregullore
   *      për Sistemin e Numrave Standardë të Llogarive Bankare</a>
   */
  XK(IsoCountry.XK, "4!n10!n2!n", 0, 2, 2, 4, 14, 16, 4, 14);

  private static final Map<IsoCountry, BbanStructure> byCountry = new EnumMap<>(IsoCountry.class);

  static {
    for (BbanStructure structure : values()) {
      byCountry.put(structure.getCountry(), structure);
      for (IsoCountry subDivision : structure.getSubdivisions()) {
        byCountry.put(subDivision, structure);
      }
    }
  }

  @SuppressWarnings("ImmutableEnumChecker") // IbanPattern is immutable
  private final IbanPattern bbanPattern;

  private final IsoCountry country;
  @SuppressWarnings("ImmutableEnumChecker") // initialized with Collections.unmodifiableSet(...).
  private final Set<IsoCountry> subdivisions;

  private final int bankIdentifierStartIndexInclusive;
  private final int bankIdentifierEndIndexExclusive;
  private final Integer branchIdentifierStartIndexInclusive; // optional
  private final Integer branchIdentifierEndIndexExclusive; // optional
  private final Integer nationalCheckDigitStartIndexInclusive; // optional
  private final Integer nationalCheckDigitEndIndexExclusive; // optional
  private final int accountNumberStartIndexInclusive;
  private final int accountNumberEndIndexExclusive;

  @SuppressWarnings("java:S107") // cannot be avoided
  BbanStructure(IsoCountry country, String bbanSwiftExpression,
      int bankIdentifierStartIndexInclusive, int bankIdentifierEndIndexExclusive,
      Integer branchIdentifierStartIndexInclusive, Integer branchIdentifierEndIndexExclusive,
      Integer nationalCheckDigitStartIndexInclusive, Integer nationalCheckDigitEndIndexExclusive,
      int accountNumberStartIndexInclusive, int accountNumberEndIndexExclusive,
      IsoCountry... subdivisions) {
    this.bbanPattern = IbanPattern.compile(bbanSwiftExpression);

    this.country = country;
    this.subdivisions = Collections.unmodifiableSet(subdivisions.length > 0
        ? EnumSet.copyOf(Arrays.asList(subdivisions))
        : EnumSet.noneOf(IsoCountry.class));

    this.bankIdentifierStartIndexInclusive = bankIdentifierStartIndexInclusive;
    this.bankIdentifierEndIndexExclusive = bankIdentifierEndIndexExclusive;
    this.branchIdentifierStartIndexInclusive = branchIdentifierStartIndexInclusive;
    this.branchIdentifierEndIndexExclusive = branchIdentifierEndIndexExclusive;
    this.nationalCheckDigitStartIndexInclusive = nationalCheckDigitStartIndexInclusive;
    this.nationalCheckDigitEndIndexExclusive = nationalCheckDigitEndIndexExclusive;
    this.accountNumberStartIndexInclusive = accountNumberStartIndexInclusive;
    this.accountNumberEndIndexExclusive = accountNumberEndIndexExclusive;
  }

  /**
   * Returns the appropriate BbanStructure given the country, or null if IBAN are not in use in this country.
   *
   * @param country a country
   * @return the given country BBAN definition, or null if IBAN are not in use in this country or if the argument is
   *         {@code null}.
   */
  public static Optional<BbanStructure> forCountry(IsoCountry country) {
    return Optional.ofNullable(byCountry.get(country));
  }

  /**
   * Returns this BBAN definition country.
   *
   * <p>
   * This method is for internal use only.
   *
   * @return a non-null {@link IsoCountry}
   */
  IsoCountry getCountry() {
    return country;
  }

  /**
   * Returns this BBAN definition subdivision countries.
   *
   * <p>
   * This method is for internal use only.
   *
   * @return a non-null Set of countries (can be empty)
   */
  Set<IsoCountry> getSubdivisions() {
    return subdivisions;
  }

  /**
   * Returns this BBAN bank identifier start index (inclusive).
   *
   * <p>
   * This method is for internal use only.
   *
   * @return an integer
   */
  int getBankIdentifierStartIndexInclusive() {
    return bankIdentifierStartIndexInclusive;
  }

  /**
   * Returns this BBAN bank identifier end index (exclusive).
   *
   * <p>
   * This method is for internal use only.
   *
   * @return an integer
   */
  int getBankIdentifierEndIndexExclusive() {
    return bankIdentifierEndIndexExclusive;
  }

  /**
   * Returns this BBAN branch identifier start index (inclusive).
   *
   * <p>
   * This method is for internal use only.
   *
   * @return a non-null optional integer
   */
  Optional<Integer> getBranchIdentifierStartIndexInclusive() {
    return Optional.ofNullable(branchIdentifierStartIndexInclusive);
  }

  /**
   * Returns this BBAN branch identifier end index (exclusive).
   *
   * <p>
   * This method is for internal use only.
   *
   * @return a non-null optional integer
   */
  Optional<Integer> getBranchIdentifierEndIndexExclusive() {
    return Optional.ofNullable(branchIdentifierEndIndexExclusive);
  }

  /**
   * Returns this BBAN national check digit start index (inclusive).
   *
   * <p>
   * This method is for internal use only.
   *
   * @return a non-null optional integer
   */
  Optional<Integer> getNationalCheckDigitStartIndexInclusive() {
    return Optional.ofNullable(nationalCheckDigitStartIndexInclusive);
  }

  /**
   * Returns this BBAN national check digit end index (exclusive).
   *
   * <p>
   * This method is for internal use only.
   *
   * @return a non-null optional integer
   */
  Optional<Integer> getNationalCheckDigitEndIndexExclusive() {
    return Optional.ofNullable(nationalCheckDigitEndIndexExclusive);
  }

  /**
   * Returns this BBAN account number start index (inclusive).
   *
   * <p>
   * This method is for internal use only.
   *
   * @return an integer
   */
  int getAccountNumberStartIndexInclusive() {
    return accountNumberStartIndexInclusive;
  }

  /**
   * Returns this BBAN account number end index (exclusive).
   *
   * <p>
   * This method is for internal use only.
   *
   * @return an integer
   */
  int getAccountNumberEndIndexExclusive() {
    return accountNumberEndIndexExclusive;
  }

  /**
   * Returns this structure BBAN pattern.
   *
   * <p>
   * This method is for internal use only.
   *
   * @return a non-null {@link IbanPattern}
   */
  IbanPattern getBbanPattern() {
    return bbanPattern;
  }

  /**
   * Test whether the given BBAN is valid.
   *
   * <p>
   * This method is for internal use only.
   *
   * @param bban a non-null string
   * @return {@code true} if the given BBAN is valid against this BBAN structure, {@code false} otherwise
   * @throws IllegalArgumentException if the given BBAN is {@code null}
   */
  boolean isBbanValid(String bban) {
    if (bban == null) {
      throw new IllegalArgumentException("the bban argument cannot be null");
    }

    return bbanPattern.matches(bban);
  }
}
