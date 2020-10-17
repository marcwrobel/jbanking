package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.IsoCountry.AD;
import static fr.marcwrobel.jbanking.IsoCountry.AE;
import static fr.marcwrobel.jbanking.IsoCountry.AL;
import static fr.marcwrobel.jbanking.IsoCountry.AT;
import static fr.marcwrobel.jbanking.IsoCountry.AX;
import static fr.marcwrobel.jbanking.IsoCountry.AZ;
import static fr.marcwrobel.jbanking.IsoCountry.BA;
import static fr.marcwrobel.jbanking.IsoCountry.BE;
import static fr.marcwrobel.jbanking.IsoCountry.BG;
import static fr.marcwrobel.jbanking.IsoCountry.BH;
import static fr.marcwrobel.jbanking.IsoCountry.BL;
import static fr.marcwrobel.jbanking.IsoCountry.BR;
import static fr.marcwrobel.jbanking.IsoCountry.BY;
import static fr.marcwrobel.jbanking.IsoCountry.CH;
import static fr.marcwrobel.jbanking.IsoCountry.CR;
import static fr.marcwrobel.jbanking.IsoCountry.CY;
import static fr.marcwrobel.jbanking.IsoCountry.CZ;
import static fr.marcwrobel.jbanking.IsoCountry.DE;
import static fr.marcwrobel.jbanking.IsoCountry.DK;
import static fr.marcwrobel.jbanking.IsoCountry.DO;
import static fr.marcwrobel.jbanking.IsoCountry.EE;
import static fr.marcwrobel.jbanking.IsoCountry.EG;
import static fr.marcwrobel.jbanking.IsoCountry.ES;
import static fr.marcwrobel.jbanking.IsoCountry.FI;
import static fr.marcwrobel.jbanking.IsoCountry.FO;
import static fr.marcwrobel.jbanking.IsoCountry.FR;
import static fr.marcwrobel.jbanking.IsoCountry.GB;
import static fr.marcwrobel.jbanking.IsoCountry.GE;
import static fr.marcwrobel.jbanking.IsoCountry.GF;
import static fr.marcwrobel.jbanking.IsoCountry.GG;
import static fr.marcwrobel.jbanking.IsoCountry.GI;
import static fr.marcwrobel.jbanking.IsoCountry.GL;
import static fr.marcwrobel.jbanking.IsoCountry.GP;
import static fr.marcwrobel.jbanking.IsoCountry.GR;
import static fr.marcwrobel.jbanking.IsoCountry.GT;
import static fr.marcwrobel.jbanking.IsoCountry.HR;
import static fr.marcwrobel.jbanking.IsoCountry.HU;
import static fr.marcwrobel.jbanking.IsoCountry.IE;
import static fr.marcwrobel.jbanking.IsoCountry.IL;
import static fr.marcwrobel.jbanking.IsoCountry.IM;
import static fr.marcwrobel.jbanking.IsoCountry.IQ;
import static fr.marcwrobel.jbanking.IsoCountry.IS;
import static fr.marcwrobel.jbanking.IsoCountry.IT;
import static fr.marcwrobel.jbanking.IsoCountry.JE;
import static fr.marcwrobel.jbanking.IsoCountry.JO;
import static fr.marcwrobel.jbanking.IsoCountry.KW;
import static fr.marcwrobel.jbanking.IsoCountry.KZ;
import static fr.marcwrobel.jbanking.IsoCountry.LB;
import static fr.marcwrobel.jbanking.IsoCountry.LC;
import static fr.marcwrobel.jbanking.IsoCountry.LI;
import static fr.marcwrobel.jbanking.IsoCountry.LT;
import static fr.marcwrobel.jbanking.IsoCountry.LU;
import static fr.marcwrobel.jbanking.IsoCountry.LV;
import static fr.marcwrobel.jbanking.IsoCountry.MC;
import static fr.marcwrobel.jbanking.IsoCountry.MD;
import static fr.marcwrobel.jbanking.IsoCountry.ME;
import static fr.marcwrobel.jbanking.IsoCountry.MF;
import static fr.marcwrobel.jbanking.IsoCountry.MK;
import static fr.marcwrobel.jbanking.IsoCountry.MQ;
import static fr.marcwrobel.jbanking.IsoCountry.MR;
import static fr.marcwrobel.jbanking.IsoCountry.MT;
import static fr.marcwrobel.jbanking.IsoCountry.MU;
import static fr.marcwrobel.jbanking.IsoCountry.NC;
import static fr.marcwrobel.jbanking.IsoCountry.NL;
import static fr.marcwrobel.jbanking.IsoCountry.NO;
import static fr.marcwrobel.jbanking.IsoCountry.PF;
import static fr.marcwrobel.jbanking.IsoCountry.PK;
import static fr.marcwrobel.jbanking.IsoCountry.PL;
import static fr.marcwrobel.jbanking.IsoCountry.PM;
import static fr.marcwrobel.jbanking.IsoCountry.PS;
import static fr.marcwrobel.jbanking.IsoCountry.PT;
import static fr.marcwrobel.jbanking.IsoCountry.QA;
import static fr.marcwrobel.jbanking.IsoCountry.RE;
import static fr.marcwrobel.jbanking.IsoCountry.RO;
import static fr.marcwrobel.jbanking.IsoCountry.RS;
import static fr.marcwrobel.jbanking.IsoCountry.SA;
import static fr.marcwrobel.jbanking.IsoCountry.SC;
import static fr.marcwrobel.jbanking.IsoCountry.SE;
import static fr.marcwrobel.jbanking.IsoCountry.SI;
import static fr.marcwrobel.jbanking.IsoCountry.SK;
import static fr.marcwrobel.jbanking.IsoCountry.SM;
import static fr.marcwrobel.jbanking.IsoCountry.ST;
import static fr.marcwrobel.jbanking.IsoCountry.SV;
import static fr.marcwrobel.jbanking.IsoCountry.TF;
import static fr.marcwrobel.jbanking.IsoCountry.TL;
import static fr.marcwrobel.jbanking.IsoCountry.TN;
import static fr.marcwrobel.jbanking.IsoCountry.TR;
import static fr.marcwrobel.jbanking.IsoCountry.UA;
import static fr.marcwrobel.jbanking.IsoCountry.VA;
import static fr.marcwrobel.jbanking.IsoCountry.VG;
import static fr.marcwrobel.jbanking.IsoCountry.WF;
import static fr.marcwrobel.jbanking.IsoCountry.XK;
import static fr.marcwrobel.jbanking.IsoCountry.YT;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.swift.SwiftPattern;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * Provides BBAN (also known as basic bank account number) structure for each ISO 13616-compliant
 * national IBAN formats.
 *
 * <p>It is based on the document <i>IBAN REGISTRY Release 87</i> issued by SWIFT in May 2020.
 *
 * @author Marc Wrobel
 * @author Matthias Kay
 * @see <a href="https://www.iso13616.org">https://www.iso13616.org</a>
 * @since 1.0
 */
@SuppressWarnings("java:S1192") // swift expressions cannot be constants (maintainability).
public enum BbanStructure {
  ALBANIA(AL, "8!n16!c"),
  ANDORRA(AD, "4!n4!n12!c"),
  AUSTRIA(AT, "5!n11!n"),
  AZERBAIJAN(AZ, "4!a20!c"),
  BAHRAIN(BH, "4!a14!c"),
  BELARUS(BY, "4!c4!n16!c"),
  BELGIUM(BE, "3!n7!n2!n"),
  BOSNIA_AND_HERZEGOVINA(BA, "3!n3!n8!n2!n"),
  BRAZIL(BR, "8!n5!n10!n1!a1!c"),
  BRITISH_VIRGIN_ISLANDS(VG, "4!a16!n"),
  BULGARIA(BG, "4!a4!n2!n8!c"),
  COSTA_RICA(CR, "4!n14!n"),
  CROATIA(HR, "7!n10!n"),
  CYPRUS(CY, "3!n5!n16!c"),
  CZECH_REPUBLIC(CZ, "4!n6!n10!n"),
  DENMARK(DK, "4!n9!n1!n"),
  EL_SALVADOR(SV, "4!a20!n"),
  FAROE_ISLANDS(FO, "4!n9!n1!n"),
  GREENLAND(GL, "4!n9!n1!n"),
  DOMINICAN_REPUBLIC(DO, "4!c20!n"),
  ESTONIA(EE, "2!n2!n11!n1!n"),
  EGYPT(EG, "4!n4!n17!n"),
  FINLAND(FI, "3!n11!n", AX),
  FRANCE(FR, "5!n5!n11!c2!n", GF, GP, MQ, RE, PF, TF, YT, NC, BL, MF, PM, WF),
  GEORGIA(GE, "2!a16!n"),
  GERMANY(DE, "8!n10!n"),
  GIBRALTAR(GI, "4!a15!c"),
  GREECE(GR, "3!n4!n16!c"),
  GUATEMALA(GT, "4!c20!c"),
  HUNGARY(HU, "3!n4!n1!n15!n1!n"),
  ICELAND(IS, "4!n2!n6!n10!n"),
  IRELAND(IE, "4!a6!n8!n"),
  ISRAEL(IL, "3!n3!n13!n"),
  IRAQ(IQ, "4!a3!n12!n"),
  ITALY(IT, "1!a5!n5!n12!c"),
  JORDAN(JO, "4!a4!n18!c"),
  KAZAKHSTAN(KZ, "3!n13!c"),
  KOSOVO(XK, "4!n10!n2!n"),
  KUWAIT(KW, "4!a22!c"),
  LATVIA(LV, "4!a13!c"),
  LEBANON(LB, "4!n20!c"),
  LIECHTENSTEIN(LI, "5!n12!c"),
  LITHUANIA(LT, "5!n11!n"),
  LUXEMBOURG(LU, "3!n13!c"),
  MACEDONIA(MK, "3!n10!c2!n"),
  MALTA(MT, "4!a5!n18!c"),
  MAURITANIA(MR, "5!n5!n11!n2!n"),
  MAURITIUS(MU, "4!a2!n2!n12!n3!n3!a"),
  MOLDOVA(MD, "2!c18!c"),
  MONACO(MC, "5!n5!n11!c2!n"),
  MONTENEGRO(ME, "3!n13!n2!n"),
  NETHERLANDS(NL, "4!a10!n"),
  NORWAY(NO, "4!n6!n1!n"),
  PAKISTAN(PK, "4!a16!c"),
  PALESTINE(PS, "4!a21!c"),
  POLAND(PL, "8!n16!n"),
  PORTUGAL(PT, "4!n4!n11!n2!n"),
  QATAR(QA, "4!a21!c"),
  ROMANIA(RO, "4!a16!c"),
  SAINT_LUCIA(LC, "4!a24!c"),
  SAN_MARINO(SM, "1!a5!n5!n12!c"),
  SAO_TOME_AND_PRINCIPE(ST, "8!n11!n2!n"),
  SAUDI_ARABIA(SA, "2!n18!c"),
  SERBIA(RS, "3!n13!n2!n"),
  SEYCHELLES(SC, "4!a2!n2!n16!n3!a"),
  SLOVAKIA(SK, "4!n6!n10!n"),
  SLOVENIA(SI, "5!n8!n2!n"),
  SPAIN(ES, "4!n4!n1!n1!n10!n"),
  SWEDEN(SE, "3!n16!n1!n"),
  SWITZERLAND(CH, "5!n12!c"),
  TIMOR_LESTE(TL, "3!n14!n2!n"),
  TUNISIA(TN, "2!n3!n13!n2!n"),
  TURKEY(TR, "5!n1!n16!c"),
  UKRAINE(UA, "6!n19!c"),
  UNITED_ARAB_EMIRATES(AE, "3!n16!n"),
  UNITED_KINGDOM(GB, "4!a6!n8!n", IM, JE, GG),
  VATICAN_CITY_STATE(VA, "3!n15!n");

  private final IsoCountry country;
  private final SwiftPattern bbanPattern;
  private final Set<IsoCountry> subdivisions;

  BbanStructure(IsoCountry country, String bbanSwiftExpression, IsoCountry... subdivisions) {
    this.country = country;
    this.bbanPattern = SwiftPattern.compile(bbanSwiftExpression);

    if (subdivisions.length == 0) {
      this.subdivisions = EnumSet.noneOf(IsoCountry.class);
    } else {
      this.subdivisions = EnumSet.copyOf(Arrays.asList(subdivisions));
    }
  }

  /**
   * Returns the appropriate BbanStructure given the country, or null if IBAN are not in use in this
   * country.
   *
   * @param country A Country.
   * @return the given country BBAN definition, or null if IBAN are not in use in this country or if
   *     the argument is {@code null}.
   */
  public static BbanStructure forCountry(IsoCountry country) {
    if (country == null) {
      return null;
    }

    for (BbanStructure structure : values()) {
      if (structure.country.equals(country)) {
        return structure;

      } else {
        for (IsoCountry subdivision : structure.subdivisions) {
          if (subdivision.equals(country)) {
            return structure;
          }
        }
      }
    }

    return null;
  }

  /**
   * Test whether the given BBAN is valid.
   *
   * @param bban A non null string.
   * @return {@code true} if the given BBAN is valid against this BBAN structure, {@code false}
   *     otherwise.
   * @throws IllegalArgumentException if the given BBAN is null.
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
   * @return a non null country.
   */
  public IsoCountry getCountry() {
    return country;
  }

  /**
   * Returns this BBAN definition pattern.
   *
   * @return a non null pattern.
   */
  public SwiftPattern getBbanPattern() {
    return bbanPattern;
  }

  /**
   * Returns this BBAN definition subdivision countries.
   *
   * @return a non null Set of countries (can be empty).
   */
  public Set<IsoCountry> getSubdivisions() {
    return subdivisions;
  }
}
