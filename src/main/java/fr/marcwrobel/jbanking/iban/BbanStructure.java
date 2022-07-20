package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.IsoCountry.AX;
import static fr.marcwrobel.jbanking.IsoCountry.BL;
import static fr.marcwrobel.jbanking.IsoCountry.GF;
import static fr.marcwrobel.jbanking.IsoCountry.GG;
import static fr.marcwrobel.jbanking.IsoCountry.GP;
import static fr.marcwrobel.jbanking.IsoCountry.IM;
import static fr.marcwrobel.jbanking.IsoCountry.JE;
import static fr.marcwrobel.jbanking.IsoCountry.MF;
import static fr.marcwrobel.jbanking.IsoCountry.MQ;
import static fr.marcwrobel.jbanking.IsoCountry.NC;
import static fr.marcwrobel.jbanking.IsoCountry.PF;
import static fr.marcwrobel.jbanking.IsoCountry.PM;
import static fr.marcwrobel.jbanking.IsoCountry.RE;
import static fr.marcwrobel.jbanking.IsoCountry.TF;
import static fr.marcwrobel.jbanking.IsoCountry.WF;
import static fr.marcwrobel.jbanking.IsoCountry.YT;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.swift.SwiftPattern;
import java.util.*;

/**
 * Provides BBAN (also known as basic bank account number) structure for each ISO 13616-compliant national IBAN formats.
 *
 * <p>
 * It is based on the document <i>IBAN REGISTRY Release 92</i> issued by SWIFT in May 2022 and was last reviewed on
 * 2022-07-17.
 *
 * @see <a href="https://www.iso13616.org">https://www.iso13616.org</a>
 * @since 1.0
 */
@SuppressWarnings("java:S1192") // swift expressions cannot be constants (maintainability).
enum BbanStructure {
  /**
   * BBAN structure for Andorra as defined by the <a href="https://www.andorranbanking.ad/">Associació de Bancs Andorrans</a>
   * (ABA).
   *
   * <ul>
   * <li>BBAN example : 00012030200359100100
   * <li>IBAN example : AD12 0001 2030 2003 5910 0100
   * </ul>
   */
  AD(IsoCountry.AD, "4!n4!n12!c"),

  /**
   * BBAN structure for Albania as defined by the <a href="https://www.bankofalbania.org">Banka e Shqipërisë</a> (Bank of
   * Albania).
   *
   * <ul>
   * <li>BBAN example : 212110090000000235698741
   * <li>IBAN example : AL47 2121 1009 0000 0002 3569 8741
   * </ul>
   */
  AL(IsoCountry.AL, "8!n16!c"),

  /**
   * BBAN structure for Austria as defined by <a href="https://www.stuzza.at/">Stuzza</a>.
   *
   * <ul>
   * <li>BBAN example : 1904300234573201
   * <li>IBAN example : AT61 1904 3002 3457 3201
   * </ul>
   */
  AT(IsoCountry.AT, "5!n11!n"),

  /**
   * BBAN structure for Azerbaijan as defined by the <a href="http://www.cbar.az/">Azərbaycan Respublikasının Mərkəzi Bankı</a>
   * (CBA - Central Bank of the Republic of Azerbaijan).
   *
   * <ul>
   * <li>BBAN example : NABZ00000000137010001944
   * <li>IBAN example : AZ21 NABZ 0000 0000 1370 1000 1944
   * </ul>
   */
  AZ(IsoCountry.AZ, "4!a20!c"),

  /**
   * BBAN structure for Bahrain as defined by the <a href="https://www.cbb.gov.bh/">Central Bank of Bahrain</a> (CBB).
   *
   * <ul>
   * <li>BBAN example : BMAG00001299123456
   * <li>IBAN example : BH67 BMAG 0000 1299 1234 56
   * </ul>
   */
  BH(IsoCountry.BH, "4!a14!c"),

  /**
   * BBAN structure for Burundi as defined by the <a href="https://brb.bi/">Banque de la République du Burundi</a> (BRB).
   *
   * <ul>
   * <li>BBAN example : 10000100010000332045181
   * <li>IBAN example : BI42 1000 0100 0100 0033 2045 181
   * </ul>
   */
  BI(IsoCountry.BI, "5!n5!n11!n2!n"),

  /**
   * BBAN structure for the Republic of Belarus as defined by the <a href="http://www.nbrb.by">National Bank of the Republic of
   * Belarus</a>.
   *
   * <ul>
   * <li>BBAN example : NBRB 3600 0000 0000 0Z00 AB00
   * <li>IBAN example : BY13 NBRB 3600 9000 0000 2Z00 AB00
   * </ul>
   */
  BY(IsoCountry.BY, "4!c4!n16!c"),

  /**
   * BBAN structure for Belgium as defined by the <a href="https://www.febelfin.be">Fédération belge du secteur financier</a>
   * (Febelfin).
   *
   * <ul>
   * <li>BBAN example : 539007547034
   * <li>IBAN example : BE68 5390 0754 7034
   * </ul>
   */
  BE(IsoCountry.BE, "3!n7!n2!n"),

  /**
   * BBAN structure for Bosnia and Herzegovina as defined by the <a href="https://www.cbbh.ba/">Centralna banka Bosne I
   * Hercegovine</a> (Central Bank of Bosnia and Herzegovina).
   *
   * <ul>
   * <li>BBAN example : 1990440001200279
   * <li>IBAN example : BA39 1290 0794 0102 8494
   * </ul>
   */
  BA(IsoCountry.BA, "3!n3!n8!n2!n"),

  /**
   * BBAN structure for Brazil as defined by the <a href="https://www.bcb.gov.br/">Banco Central do Brasil</a> (Central Bank of
   * Brazil).
   *
   * <ul>
   * <li>BBAN example : 00360305000010009795493P1
   * <li>IBAN example : BR18 0036 0305 0000 1000 9795 493C 1
   * </ul>
   */
  BR(IsoCountry.BR, "8!n5!n10!n1!a1!c"),

  /**
   * BBAN structure for the british Virgin Islands as defined by the <a href="https://vg.vpbank.com">VP Bank House</a>.
   *
   * <ul>
   * <li>BBAN example : VPVG0000012345678901
   * <li>IBAN example : VG96 VPVG 0000 0123 4567 8901
   * </ul>
   */
  VG(IsoCountry.VG, "4!a16!n"),

  /**
   * BBAN structure for Bulgaria as defined by the <a href="http://www.bnb.bg">Balgarska narodna banka</a> (Bulgarian National
   * Bank).
   *
   * <ul>
   * <li>BBAN example : BNBG96611020345678
   * <li>IBAN example : BG80 BNBG 9661 1020 3456 78
   * </ul>
   */
  BG(IsoCountry.BG, "4!a4!n2!n8!c"),

  /**
   * BBAN structure for Costa Rica as defined by the <a href="https://www.bccr.fi.cr/">Banco Central de Costa Rica</a> (Central
   * Bank of Costa Rica).
   *
   * <ul>
   * <li>BBAN example : 15202001026284066
   * <li>IBAN example : CR05 0152 0200 1026 2840 66
   * </ul>
   */
  CR(IsoCountry.CR, "4!n14!n"),

  /**
   * BBAN structure for Croatia as defined by the <a href="https://www.hnb.hr">Hrvatska narodna banka</a> (Croatian National
   * Bank).
   *
   * <ul>
   * <li>BBAN example : 10010051863000160
   * <li>IBAN example : HR12 1001 0051 8630 0016 0
   * </ul>
   */
  HR(IsoCountry.HR, "7!n10!n"),

  /**
   * BBAN structure for Cyprus as defined by the <a href="https://www.centralbank.cy/">Kıbrıs Merkez Bankası</a> (Central Bank
   * of Cyprus).
   *
   * <ul>
   * <li>BBAN example : 002001280000001200527600
   * <li>IBAN example : CY17 0020 0128 0000 0012 0052 7600
   * </ul>
   */
  CY(IsoCountry.CY, "3!n5!n16!c"),

  /**
   * BBAN structure for the Czech Republic as defined by the <a href="http://www.cnb.cz">Česká národní banka</a> (Czech National
   * Bank).
   *
   * <ul>
   * <li>BBAN example : 08000000192000145399
   * <li>IBAN example : CZ65 0800 0000 1920 0014 5399
   * </ul>
   */
  CZ(IsoCountry.CZ, "4!n6!n10!n"),

  /**
   * BBAN structure for Djibouti as defined by the <a href="https://banque-centrale.dj/">Banque Centrale de Djibouti</a>.
   *
   * <ul>
   * <li>BBAN example : 00010000000154000100186
   * <li>IBAN example : DJ21 0001 0000 0001 5400 0100 186
   * </ul>
   */
  DJ(IsoCountry.DJ, "5!n5!n11!n2!n"),

  /**
   * BBAN structure for Denmark as defined by <a href="https://finansdanmark.dk/">Finance Denmark</a>.
   *
   * <ul>
   * <li>BBAN example : 00400440116243
   * <li>IBAN example : DK50 0040 0440 1162 43
   * </ul>
   */
  DK(IsoCountry.DK, "4!n9!n1!n"),

  /**
   * BBAN structure for El Salvador as defined by the <a href="https://www.bcr.gob.sv/">Banco Central de Reserva de El
   * Salvador</a> (Central Reserve Bank of El Salvador).
   *
   * <ul>
   * <li>BBAN example : CENR00000000000000700025
   * <li>IBAN example : SV62 CENR 0000 0000 0000 0070 0025
   * </ul>
   */
  SV(IsoCountry.SV, "4!a20!n"),

  /**
   * BBAN structure for Faroe Islands as defined by <a href="http://financedenmark.dk">Finance Denmark</a>.
   *
   * <ul>
   * <li>BBAN example : 64600001631634
   * <li>IBAN example : FO62 6460 0001 6316 34
   * </ul>
   */
  FO(IsoCountry.FO, "4!n9!n1!n"),

  /**
   * BBAN structure for Greenland as defined by the <a href="http://financedenmark.dk/">Finance Denmark</a>.
   *
   * <ul>
   * <li>BBAN example : 64710001000206
   * <li>IBAN example : GL89 6471 0001 0002 06
   * </ul>
   */
  GL(IsoCountry.GL, "4!n9!n1!n"),

  /**
   * BBAN structure for the Dominican Republic as defined by the <a href="https://www.bancentral.gov.do">Banco Central de la
   * República Dominicana</a> (Central Bank of the Dominican Republic).
   *
   * <ul>
   * <li>BBAN example : BAGR00000001212453611324
   * <li>IBAN example : DO28 BAGR 0000 0001 2124 5361 1324
   * </ul>
   */
  DO(IsoCountry.DO, "4!c20!n"),

  /**
   * BBAN structure for Estonia as defined by the <a href="https://www.pangaliit.ee">Eesti Pangaliit</a> (Estonian Banking
   * Association - EBA).
   *
   * <ul>
   * <li>BBAN example : 2200221020145685
   * <li>IBAN example : EE38 2200 2210 2014 5685
   * </ul>
   */
  EE(IsoCountry.EE, "2!n2!n11!n1!n"),

  /**
   * BBAN structure for Egypt as defined by the <a href="https://www.cbe.org.eg">Central Bank of Egypt</a> (CBE).
   *
   * <ul>
   * <li>BBAN example : 0019000500000000263180002
   * <li>IBAN example : EG38 0019 0005 0000 0000 2631 8000 2
   * </ul>
   */
  EG(IsoCountry.EG, "4!n4!n17!n"),

  /**
   * BBAN structure for Finland as defined by the <a href="https://www.finanssiala.fi">Finanssiala ry</a> (Federation of Finnish
   * Financial Services - FA).
   *
   * <ul>
   * <li>BBAN example : N/A
   * <li>IBAN example : FI21 1234 5600 0007 85
   * </ul>
   */
  FI(IsoCountry.FI, "3!n11!n", AX),

  /**
   * BBAN structure for France as defined by the <a href="https://www.cfonb.org/">Comité Français d'Organisation et de
   * Normalisation Bancaires</a> (CFONB).
   *
   * <ul>
   * <li>BBAN example : 20041010050500013M02606
   * <li>IBAN example : FR14 2004 1010 0505 0001 3M02 606
   * </ul>
   */
  FR(IsoCountry.FR, "5!n5!n11!c2!n", GF, GP, MQ, RE, PF, TF, YT, NC, BL, MF, PM, WF),

  /**
   * BBAN structure for Georgia as defined by the <a href="https://www.nbg.gov.ge">National Bank of Georgia</a>.
   *
   * <ul>
   * <li>BBAN example : NB0000000101904917
   * <li>IBAN example : GE29 NB00 0000 0101 9049 17
   * </ul>
   */
  GE(IsoCountry.GE, "2!a16!n"),

  /**
   * BBAN structure for Germany as defined by the <a href="https://bankenverband.de">Bundesverband deutscher Banken</a>
   * (Association of German Banks).
   *
   * <ul>
   * <li>BBAN example : 370400440532013000
   * <li>IBAN example : DE89 3704 0044 0532 0130 00
   * </ul>
   */
  DE(IsoCountry.DE, "8!n10!n"),

  /**
   * BBAN structure for Gibraltar as defined by the <a href="https://fsc.gi">Gibraltar Financial Services Commission</a>.
   *
   * <ul>
   * <li>BBAN example : NWBK000000007099453
   * <li>IBAN example : GI75 NWBK 0000 0000 7099 453
   * </ul>
   */
  GI(IsoCountry.GI, "4!a15!c"),

  /**
   * BBAN structure for Greece as defined by the <a href="https://www.hba.gr">Hellenic Bank Association</a> (HBA).
   *
   * <ul>
   * <li>BBAN example : 01101250000000012300695
   * <li>IBAN example : GR16 0110 1250 0000 0001 2300 695
   * </ul>
   */
  GR(IsoCountry.GR, "3!n4!n16!c"),

  /**
   * BBAN structure for Guatemala as defined by the <a href="http://www.banguat.gob.gt">Banco de Guatemala</a> (Bank of
   * Guatemala).
   *
   * <ul>
   * <li>BBAN example : TRAJ01020000001210029690
   * <li>IBAN example : GT82 TRAJ 0102 0000 0012 1002 9690
   * </ul>
   */
  GT(IsoCountry.GT, "4!c20!c"),

  /**
   * BBAN structure for Hungary as defined by the <a href="http://bankszovetseg.hu">Magyar Bankszövetség</a> (Hungarian Banking
   * Association).
   *
   * <ul>
   * <li>BBAN example : 117730161111101800000000
   * <li>IBAN example : HU42 1177 3016 1111 1018 0000 0000
   * </ul>
   */
  HU(IsoCountry.HU, "3!n4!n1!n15!n1!n"),

  /**
   * BBAN structure for Iceland as defined by the <a href="https://www.rb.is">Reiknistofa Bankanna</a> (RB).
   *
   * <ul>
   * <li>BBAN example : 0159260076545510730339
   * <li>IBAN example : IS14 0159 2600 7654 5510 7303 39
   * </ul>
   */
  IS(IsoCountry.IS, "4!n2!n6!n10!n"),

  /**
   * BBAN structure for Ireland as defined by the <a href="https://www.bpfi.ie">Banking and Payments Federation Ireland</a>.
   *
   * <ul>
   * <li>BBAN example : AIBK93115212345678
   * <li>IBAN example : IE29 AIBK 9311 5212 3456 78
   * </ul>
   */
  IE(IsoCountry.IE, "4!a6!n8!n"),

  /**
   * BBAN structure for Israel as defined by the <a href="http://www.boi.org.il">Bank of Israel</a>.
   *
   * <ul>
   * <li>BBAN example : 010800000099999999
   * <li>IBAN example : IL62 0108 0000 0009 9999 999
   * </ul>
   */
  IL(IsoCountry.IL, "3!n3!n13!n"),

  /**
   * BBAN structure for Iraq as defined by the <a href="https://cbi.iq">Central Bank of Iraq</a> (CBI).
   *
   * <ul>
   * <li>BBAN example : NBIQ850123456789012
   * <li>IBAN example : IQ98 NBIQ 8501 2345 6789 012
   * </ul>
   */
  IQ(IsoCountry.IQ, "4!a3!n12!n"),

  /**
   * BBAN structure for Italy as defined by the <a href="https://www.abi.it">Associazione Bancaria Italiana</a>.
   *
   * <ul>
   * <li>BBAN example : X0542811101000000123456
   * <li>IBAN example : IT60 X054 2811 1010 0000 0123 456
   * </ul>
   */
  IT(IsoCountry.IT, "1!a5!n5!n12!c"),

  /**
   * BBAN structure for Jordan as defined by the <a href="http://www.cbj.gov.jo">Central Bank of Jordan</a>.
   *
   * <ul>
   * <li>BBAN example : CBJO0010000000000131000302
   * <li>IBAN example : JO94 CBJO 0010 0000 0000 0131 0003 02
   * </ul>
   */
  JO(IsoCountry.JO, "4!a4!n18!c"),

  /**
   * BBAN structure for Kazakhstan as defined by the <a href="https://nationalbank.kz">Qazaqstan Ulttyq Banki</a> (National Bank
   * of Kazakhstan).
   *
   * <ul>
   * <li>BBAN example : 125KZT5004100100
   * <li>IBAN example : KZ86 125K ZT50 0410 0100
   * </ul>
   */
  KZ(IsoCountry.KZ, "3!n13!c"),

  /**
   * BBAN structure for Kosovo as defined by the <a href="https://www.bqk-kos.org/">Banka Qendrore e Kosovës</a> (Central Bank
   * of the Republic of Kosovo).
   *
   * <ul>
   * <li>BBAN example : 1212012345678906
   * <li>IBAN example : XK05 1212 0123 4567 8906
   * </ul>
   */
  XK(IsoCountry.XK, "4!n10!n2!n"),

  /**
   * BBAN structure for Kuwait as defined by the <a href="https://www.cbk.gov.kw/">Central Bank of Kuwait</a> (CBK).
   *
   * <ul>
   * <li>BBAN example : CBKU0000000000001234560101
   * <li>IBAN example : KW81 CBKU 0000 0000 0000 1234 5601 01
   * </ul>
   */
  KW(IsoCountry.KW, "4!a22!c"),

  /**
   * BBAN structure for Latvia as defined by the <a href="https://www.bank.lv">Latvijas Banka</a> (Bank of Latvia).
   *
   * <ul>
   * <li>BBAN example : BANK0000435195001
   * <li>IBAN example : LV80 BANK 0000 4351 9500 1
   * </ul>
   */
  LV(IsoCountry.LV, "4!a13!c"),

  /**
   * BBAN structure for Lebanon as defined by the <a href="https://bdl.gov.lb">Banque du Liban</a> (BDL - Bank of Lebanon).
   *
   * <ul>
   * <li>BBAN example : 0999 0000 0001 0019 0122 9114
   * <li>IBAN example : LB62 0999 0000 0001 0019 0122 9114
   * </ul>
   */
  LB(IsoCountry.LB, "4!n20!c"),

  /**
   * BBAN structure for Liechtenstein as defined by the <a href="https://www.bankenverband.li">Liechtensteinischer
   * Bankenverband</a> (Liechtenstein Bankers Association).
   *
   * <ul>
   * <li>BBAN example : 088100002324013AA
   * <li>IBAN example : LI21 0881 0000 2324 013A A
   * </ul>
   */
  LI(IsoCountry.LI, "5!n12!c"),

  /**
   * BBAN structure for Lithuania as defined by the <a href="https://www.lb.lt">Lietuvos Bankas</a> (Bank of Lithuania).
   *
   * <ul>
   * <li>BBAN example : 1000011101001000
   * <li>IBAN example : LT12 1000 0111 0100 1000
   * </ul>
   */
  LT(IsoCountry.LT, "5!n11!n"),

  /**
   * BBAN structure for Luxembourg as defined by the <a href="https://www.abbl.lu">Association des Banques et Banquiers
   * Luxembourg</a> (ABBL - Luxembourg Bankers’ Association).
   *
   * <ul>
   * <li>BBAN example : 0019400644750000
   * <li>IBAN example : LU28 0019 4006 4475 0000
   * </ul>
   */
  LU(IsoCountry.LU, "3!n13!c"),

  /**
   * BBAN structure for Libya as defined by the <a href="https://cbl.gov.ly">Central Bank of Libya</a> (CBL).
   *
   * <ul>
   * <li>BBAN example : 002048000020100120361
   * <li>IBAN example : LY83 0020 4800 0020 1001 2036 1
   * </ul>
   */
  LY(IsoCountry.LY, "3!n3!n15!n"),

  /**
   * BBAN structure for Macedonia as defined by the <a href="http://www.nbrm.mk">Narodna Banka na Republika Severna
   * Makedonija</a> (NBRSM - National Bank of the Republic of North Macedonia).
   *
   * <ul>
   * <li>BBAN example : 250120000058984
   * <li>IBAN example : MK07 2501 2000 0058 984
   * </ul>
   */
  MK(IsoCountry.MK, "3!n10!c2!n"),

  /**
   * BBAN structure for Malta as defined by the <a href="https://www.maltabankers.org">Malta Bankers’ Association</a> (MBA).
   *
   * <ul>
   * <li>BBAN example : MALT011000012345MTLCAST001S
   * <li>IBAN example : MT84 MALT 0110 0001 2345 MTLC AST0 01S
   * </ul>
   */
  MT(IsoCountry.MT, "4!a5!n18!c"),

  /**
   * BBAN structure for Mauritania as defined by the <a href="https://www.bcm.mr/">Banque Centrale de Mauritanie</a> (BCM -
   * Central Bank of Mauritania).
   *
   * <ul>
   * <li>BBAN example : 00020001010000123456753
   * <li>IBAN example : MR13 0002 0001 0100 0012 3456 753
   * </ul>
   */
  MR(IsoCountry.MR, "5!n5!n11!n2!n"),

  /**
   * BBAN structure for Mauritius as defined by the <a href="https://www.bom.mu/">Bank of Mauritius</a>.
   *
   * <ul>
   * <li>BBAN example : BOMM0101101030300200000MUR
   * <li>IBAN example : MU17 BOMM 0101 1010 3030 0200 000M UR
   * </ul>
   */
  MU(IsoCountry.MU, "4!a2!n2!n12!n3!n3!a"),

  /**
   * BBAN structure for Moldova as defined by the <a href="https://www.bnm.md">Banca Naţională a Moldovei</a> (National Bank of
   * Moldova).
   *
   * <ul>
   * <li>BBAN example : AG000225100013104168
   * <li>IBAN example : MD24 AG00 0225 1000 1310 4168
   * </ul>
   */
  MD(IsoCountry.MD, "2!c18!c"),

  /**
   * BBAN structure for Monaco as defined by the <a href="https://www.amaf.mc">Association Monégasque des Activités
   * Financières</a> (AMAF).
   *
   * <ul>
   * <li>BBAN example : 11222 00001 01234567890 30
   * <li>IBAN example : MC58 1122 2000 0101 2345 6789 030
   * </ul>
   */
  MC(IsoCountry.MC, "5!n5!n11!c2!n"),

  /**
   * BBAN structure for Montenegro as defined by the <a href="http://t-com.me/">Association of Montenegrin Banks</a>.
   *
   * <ul>
   * <li>BBAN example : 505000012345678951
   * <li>IBAN example : ME25 5050 0001 2345 6789 51
   * </ul>
   */
  ME(IsoCountry.ME, "3!n13!n2!n"),

  /**
   * BBAN structure for the Netherlands as defined by the <a href="https://www.betaalvereniging.nl">Betaalvereniging
   * Nederland</a> (Dutch Payment Association).
   *
   * <ul>
   * <li>BBAN example : ABNA0417164300
   * <li>IBAN example : NL91 ABNA 0417 1643 00
   * </ul>
   */
  NL(IsoCountry.NL, "4!a10!n"),

  /**
   * BBAN structure for Norway as defined by the <a href="https://www.dnb.no">DNB ASA</a> ((formerly DnB NOR ASA).
   *
   * <ul>
   * <li>BBAN example : 86011117947
   * <li>IBAN example : NO93 8601 1117 947
   * </ul>
   */
  NO(IsoCountry.NO, "4!n6!n1!n"),

  /**
   * BBAN structure for Pakistan as defined by the <a href="http://www.sbp.org.pk">State Bank of Pakistan (SBP)</a>.
   *
   * <ul>
   * <li>BBAN example : SCBL0000001123456702
   * <li>IBAN example : PK36 SCBL 0000 0011 2345 6702
   * </ul>
   */
  PK(IsoCountry.PK, "4!a16!c"),

  /**
   * BBAN structure for the State of Palestine as defined by the <a href="https://www.pma.ps">Palestine Monetary Authority</a>
   * (PMA).
   *
   * <ul>
   * <li>BBAN example : PALS000000000400123456702
   * <li>IBAN example : PS92 PALS 0000 0000 0400 1234 5670 2
   * </ul>
   */
  PS(IsoCountry.PS, "4!a21!c"),

  /**
   * BBAN structure for Poland as defined by the <a href="http://www.nbp.pl">Narodowy Bank Polski</a> (NBP - National Bank of
   * Poland).
   *
   * <ul>
   * <li>BBAN example : 109010140000071219812874
   * <li>IBAN example : PL61 1090 1014 0000 0712 1981 2874
   * </ul>
   */
  PL(IsoCountry.PL, "8!n16!n"),

  /**
   * BBAN structure for Portugal as defined by <a href="https://www.sibs.com/">SIBS</a>.
   *
   * <ul>
   * <li>BBAN example : 000201231234567890154
   * <li>IBAN example : PT50 0002 0123 1234 5678 9015 4
   * </ul>
   */
  PT(IsoCountry.PT, "4!n4!n11!n2!n"),

  /**
   * BBAN structure for Qatar as defined by the <a href="http://www.qcb.gov.qa">Qatar Central Bank</a>.
   *
   * <ul>
   * <li>BBAN example : DOHB00001234567890ABCDEFG
   * <li>IBAN example : QA58 DOHB 0000 1234 5678 90AB CDEF G
   * </ul>
   */
  QA(IsoCountry.QA, "4!a21!c"),

  /**
   * BBAN structure for Romania as defined by the <a href="https://www.bnr.ro/">Banca Națională a României</a> (BNR - National
   * Bank of Romania).
   *
   * <ul>
   * <li>BBAN example : AAAA1B31007593840000
   * <li>IBAN example : RO49 AAAA 1B31 0075 9384 0000
   * </ul>
   */
  RO(IsoCountry.RO, "4!a16!c"),

  /**
   * BBAN structure for Saint Lucia as defined by the <a href="https://www.slbs.org">Saint Lucia Bureau of Standards</a> (SLBS).
   *
   * <ul>
   * <li>BBAN example : HEMM000100010012001200023015
   * <li>IBAN example : LC55 HEMM 0001 0001 0012 0012 0002 3015
   * </ul>
   */
  LC(IsoCountry.LC, "4!a24!c"),

  /**
   * BBAN structure for San Marino as defined by the <a href="https://www.bcsm.sm/">Banca Centrale della Repubblica di San
   * Marino</a> (CBSM - Central Bank of the Republic of San Marino).
   *
   * <ul>
   * <li>BBAN example : U0322509800000000270100
   * <li>IBAN example : SM86 U032 2509 8000 0000 0270 100
   * </ul>
   */
  SM(IsoCountry.SM, "1!a5!n5!n12!c"),

  /**
   * BBAN structure for São Tomé and Príncipe as defined by the <a href="http://www.bcstp.st/">Banco Central de Sao Tome e
   * Principe</a> (BCSTP - Central Bank of São Tomé and Príncipe).
   *
   * <ul>
   * <li>BBAN example : 000100010051845310146
   * <li>IBAN example : ST23 0001 0001 0051 8453 1014 6
   * </ul>
   */
  ST(IsoCountry.ST, "8!n11!n2!n"),

  /**
   * BBAN structure for Saudi Arabia as defined by the <a href="http://www.sama.gov.sa">Saudi Arabian Monetary Authority</a>
   * (SAMA).
   *
   * <ul>
   * <li>BBAN example : 80000000608010167519
   * <li>IBAN example : SA03 8000 0000 6080 1016 7519
   * </ul>
   */
  SA(IsoCountry.SA, "2!n18!c"),

  /**
   * BBAN structure for Serbia as defined by the <a href="https://nbs.rs">Narodna banka Srbije</a> (National bank of Serbia).
   *
   * <ul>
   * <li>BBAN example : 260005601001611379
   * <li>IBAN example : RS35 2600 0560 1001 6113 79
   * </ul>
   */
  RS(IsoCountry.RS, "3!n13!n2!n"),

  /**
   * BBAN structure for Russia as defined by the <a href="https://cbr.ru/">Central Bank of the Russian Federation</a> (CBR).
   *
   * <ul>
   * <li>BBAN example : 044525225 40817 810 5 3809 1310419
   * <li>IBAN example : RU17 0445 2522 5408 1781 0538 0913 1041 9
   * </ul>
   */
  RU(IsoCountry.RU, "9!n5!n15!c"),

  /**
   * BBAN structure for Seychelles as defined by the <a href="http://www.cbs.sc/">Central Bank of Seychelles</a> (CBS).
   *
   * <ul>
   * <li>BBAN example : SSCB11010000000000001497USD
   * <li>IBAN example : SC18 SSCB 1101 0000 0000 0000 1497 USD
   * </ul>
   */
  SC(IsoCountry.SC, "4!a2!n2!n16!n3!a"),

  /**
   * BBAN structure for Sudan as defined by the <a href="http://www.cbs.sc/">Central Bank of Sudan</a> (CBOS).
   *
   * <ul>
   * <li>BBAN example : 29010501234001
   * <li>IBAN example : SD21 2901 0501 2340 01
   * </ul>
   */
  SD(IsoCountry.SD, "2!n12!n"),

  /**
   * BBAN structure for Slovakia as defined by the <a href="https://www.nbs.sk">Národná banka Slovenska</a> (NBS - National Bank
   * of Slovakia).
   *
   * <ul>
   * <li>BBAN example : 12000000198742637541
   * <li>IBAN example : SK31 1200 0000 1987 4263 7541
   * </ul>
   */
  SK(IsoCountry.SK, "4!n6!n10!n"),

  /**
   * BBAN structure for Slovenia as defined by the <a href="https://www.bsi.si">Banka Slovenije</a> (Bank of Slovenia).
   *
   * <ul>
   * <li>BBAN example : 263300012039086
   * <li>IBAN example : SI56 2633 0001 2039 086
   * </ul>
   */
  SI(IsoCountry.SI, "5!n8!n2!n"),

  /**
   * BBAN structure for Spain as defined by the <a href="https://www.aebanca.es">Asociación Española de Banca</a> (AEB).
   *
   * <ul>
   * <li>BBAN example : 21000418450200051332
   * <li>IBAN example : ES91 2100 0418 4502 0005 1332
   * </ul>
   */
  ES(IsoCountry.ES, "4!n4!n1!n1!n10!n"),

  /**
   * BBAN structure for Sweden as defined by the <a href="https://www.swedishbankers.se">Svenska Bankföreningen</a> (Swedish
   * Bankers’ Association).
   *
   * <ul>
   * <li>BBAN example : 50000000058398257466
   * <li>IBAN example : SE45 5000 0000 0583 9825 7466
   * </ul>
   */
  SE(IsoCountry.SE, "3!n16!n1!n"),

  /**
   * BBAN structure for Switzerland as defined by <a href="https://www.six-group.com">SIX Interbank Clearing</a>.
   *
   * <ul>
   * <li>BBAN example : 00762011623852957
   * <li>IBAN example : CH93 0076 2011 6238 5295 7
   * </ul>
   */
  CH(IsoCountry.CH, "5!n12!c"),

  /**
   * BBAN structure for Timor-Leste as defined by the <a href="https://www.bancocentral.tl">Banco Central de Timor-Leste</a>
   * (BCTL - East Timor Central Bank).
   *
   * <ul>
   * <li>BBAN example : 0080012345678910157
   * <li>IBAN example : TL38 0080 0123 4567 8910 157
   * </ul>
   */
  TL(IsoCountry.TL, "3!n14!n2!n"),

  /**
   * BBAN structure for Tunisia as defined by the <a href="https://apbt.org.tn/">Association Professionnelle Tunisienne des
   * Banques et des Etablissements Financiers</a> (APTBEF - Tunisia’s Professional Association for Banks and Financial
   * Institutions).
   *
   * <ul>
   * <li>BBAN example : 10006035183598478831
   * <li>IBAN example : TN59 1000 6035 1835 9847 8831
   * </ul>
   */
  TN(IsoCountry.TN, "2!n3!n13!n2!n"),

  /**
   * BBAN structure for Turkey as defined by the <a href="https://www.tcmb.gov.tr/">Türkiye Cumhuriyet Merkez Bankası</a> (CBRT
   * - Central Bank of the Republic of Turkey).
   *
   * <ul>
   * <li>BBAN example : 0006100519786457841326
   * <li>IBAN example : TR33 0006 1005 1978 6457 8413 26
   * </ul>
   */
  TR(IsoCountry.TR, "5!n1!n16!c"),

  /**
   * BBAN structure for Ukraine as defined by the <a href="https://www.ukrswift.org">Association UkrSWIFT</a>.
   *
   * <ul>
   * <li>BBAN example : 3223130000026007233566001
   * <li>IBAN example : UA21 3223 1300 0002 6007 2335 6600 1
   * </ul>
   */
  UA(IsoCountry.UA, "6!n19!c"),

  /**
   * BBAN structure for the United Arab Emirates as defined by the <a href="https://www.centralbank.ae">Central Bank of the
   * United Arab Emirates</a>.
   *
   * <ul>
   * <li>BBAN example : 0331234567890123456
   * <li>IBAN example : AE07 0331 2345 6789 0123 456
   * </ul>
   */
  AE(IsoCountry.AE, "3!n16!n"),

  /**
   * BBAN structure for the United Kingdom as defined by <a href="https://www.ukpayments.org.uk">Payments UK Management Ltd</a>.
   *
   * <ul>
   * <li>BBAN example : NWBK60161331926819
   * <li>IBAN example : GB29 NWBK 6016 1331 9268 19
   * </ul>
   */
  GB(IsoCountry.GB, "4!a6!n8!n", IM, JE, GG),

  /**
   * BBAN structure for the Vatican City State as defined by the <a href="https://www.aif.va">Autorita di Informazione
   * Finanziaria</a> (AIF - Financial Information Authority).
   *
   * <ul>
   * <li>BBAN example : 001123000012345678
   * <li>IBAN example : VA59 001 1230 0001 2345 678
   * </ul>
   */
  VA(IsoCountry.VA, "3!n15!n");

  private static final Map<IsoCountry, BbanStructure> byCountry = new EnumMap<>(IsoCountry.class);

  static {
    for (BbanStructure structure : values()) {
      byCountry.put(structure.getCountry(), structure);
      for (IsoCountry subDivision : structure.getSubdivisions()) {
        byCountry.put(subDivision, structure);
      }
    }
  }

  private final IsoCountry country;

  @SuppressWarnings("ImmutableEnumChecker") // SwiftPattern is immutable
  private final SwiftPattern bbanPattern;

  @SuppressWarnings("ImmutableEnumChecker") // initialized with Collections.unmodifiableSet(...).
  private final Set<IsoCountry> subdivisions;

  BbanStructure(IsoCountry country, String bbanSwiftExpression, IsoCountry... subdivisions) {
    this.country = country;
    this.bbanPattern = SwiftPattern.compile(bbanSwiftExpression);
    this.subdivisions = Collections.unmodifiableSet(subdivisions.length > 0
        ? EnumSet.copyOf(Arrays.asList(subdivisions))
        : EnumSet.noneOf(IsoCountry.class));
  }

  /**
   * Returns the appropriate BbanStructure given the country, or null if IBAN are not in use in this country.
   *
   * @param country A Country.
   * @return the given country BBAN definition, or null if IBAN are not in use in this country or if the argument is
   *         {@code null}.
   */
  public static Optional<BbanStructure> forCountry(IsoCountry country) {
    return Optional.ofNullable(byCountry.get(country));
  }

  /**
   * Test whether the given BBAN is valid.
   *
   * @param bban A non-null string.
   * @return {@code true} if the given BBAN is valid against this BBAN structure, {@code false} otherwise.
   * @throws IllegalArgumentException if the given BBAN is {@code null}.
   */
  public boolean isBbanValid(String bban) {
    if (bban == null) {
      throw new IllegalArgumentException("the bban argument cannot be null");
    }

    return bbanPattern.matcher(bban).matches();
  }

  /**
   * Returns this BBAN definition country.
   *
   * @return a non-null country.
   */
  public IsoCountry getCountry() {
    return country;
  }

  /**
   * Returns this BBAN definition pattern.
   *
   * @return a non-null pattern.
   */
  public SwiftPattern getBbanPattern() {
    return bbanPattern;
  }

  /**
   * Returns this BBAN definition subdivision countries.
   *
   * @return a non-null Set of countries (can be empty).
   */
  public Set<IsoCountry> getSubdivisions() {
    return subdivisions;
  }
}
