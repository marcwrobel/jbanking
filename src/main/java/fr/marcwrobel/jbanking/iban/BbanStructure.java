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
package fr.marcwrobel.jbanking.iban;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.swift.SwiftPattern;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * Provides BBAN (also known as basic bank account number) structure for each ISO 13616-compliant national IBAN formats.
 *
 * <p>It is based on the document <i>IBAN REGISTRY Release 45</i> issued by SWIFT on May 2013.</p>
 *
 * @author Marc Wrobel
 * @see <a href="http://www.swift.com/dsp/resources/documents/IBAN_Registry.pdf">http://www.swift.com/dsp/resources/documents/IBAN_Registry.pdf</a>
 * @since 1.0
 */
public enum BbanStructure {

    ALBANIA(IsoCountry.ALBANIA, "8!n16!c"),
    ANDORRA(IsoCountry.ANDORRA, "4!n4!n12!c"),
    AUSTRIA(IsoCountry.AUSTRIA, "5!n11!n"),
    AZERBAIJAN(IsoCountry.AZERBAIJAN, "4!a20!c"),
    BAHRAIN(IsoCountry.BAHRAIN, "4!a14!c"),
    BELGIUM(IsoCountry.BELGIUM, "3!n7!n2!n"),
    BOSNIA_AND_HERZEGOVINA(IsoCountry.BOSNIA_AND_HERZEGOVINA, "3!n3!n8!n2!n"),
    BRAZIL(IsoCountry.BRAZIL, "8!n5!n10!n1!a1!c"),
    BULGARIA(IsoCountry.BULGARIA, "4!a4!n2!n8!c"),
    COSTA_RICA(IsoCountry.COSTA_RICA, "3!n14!n"),
    CROATIA(IsoCountry.CROATIA, "7!n10!n"),
    CYPRUS(IsoCountry.CYPRUS, "3!n5!n16!c"),
    CZECH_REPUBLIC(IsoCountry.CZECH_REPUBLIC, "4!n6!n10!n"),
    DENMARK(IsoCountry.DENMARK, "4!n9!n1!n"),
    FAROE_ISLANDS(IsoCountry.FAROE_ISLANDS, "4!n9!n1!n"),
    GREENLAND(IsoCountry.GREENLAND, "4!n9!n1!n"),
    DOMINICAN_REPUBLIC(IsoCountry.DOMINICAN_REPUBLIC, "4!c20!n"),
    ESTONIA(IsoCountry.ESTONIA, "2!n2!n11!n1!n"),
    FINLAND(IsoCountry.FINLAND, "6!n7!n1!n"),
    ALAND_ISLANDS(IsoCountry.ALAND_ISLANDS, "6!n7!n1!n"),
    FRANCE(IsoCountry.FRANCE, "5!n5!n11!c2!n",
            IsoCountry.FRENCH_GUIANA,
            IsoCountry.GUADELOUPE,
            IsoCountry.MARTINIQUE,
            IsoCountry.REUNION,
            IsoCountry.FRENCH_POLYNESIA,
            IsoCountry.FRENCH_SOUTHERN_TERRITORIES,
            IsoCountry.NEW_CALEDONIA,
            IsoCountry.SAINT_BARTHELEMY,
            IsoCountry.SAINT_MARTIN,
            IsoCountry.SAINT_PIERRE_AND_MIQUELON,
            IsoCountry.WALLIS_AND_FUTUNA
    ),
    GEORGIA(IsoCountry.GEORGIA, "2!a16!n"),
    GERMANY(IsoCountry.GERMANY, "8!n10!n"),
    GIBRALTAR(IsoCountry.GIBRALTAR, "4!a15!c"),
    GREECE(IsoCountry.GREECE, "3!n4!n16!c"),
    GUATEMALA(IsoCountry.GUATEMALA, "4!c20!c"),
    HUNGARY(IsoCountry.HUNGARY, "3!n4!n1!n15!n1!n"),
    ICELAND(IsoCountry.ICELAND, "4!n2!n6!n10!n"),
    IRELAND(IsoCountry.IRELAND, "4!a6!n8!n"),
    ISRAEL(IsoCountry.ISRAEL, "3!n3!n13!n"),
    ITALY(IsoCountry.ITALY, "1!a5!n5!n12!c"),
    JORDANIAN(IsoCountry.JORDAN, "4!a4!n18!c"),
    KAZAKHSTAN(IsoCountry.KAZAKHSTAN, "3!n13!c"),
    KUWAIT(IsoCountry.KUWAIT, "4!a22!c"),
    LATVIA(IsoCountry.LATVIA, "4!a13!c"),
    LEBANON(IsoCountry.LEBANON, "4!n20!c"),
    LIECHTENSTEIN(IsoCountry.LIECHTENSTEIN, "5!n12!c"),
    LITHUANIA(IsoCountry.LITHUANIA, "5!n11!n"),
    LUXEMBOURG(IsoCountry.LUXEMBOURG, "3!n13!c"),
    MACEDONIA(IsoCountry.MACEDONIA, "3!n10!c2!n"),
    MALTA(IsoCountry.MALTA, "4!a5!n18!c"),
    MAURITANIA(IsoCountry.MAURITANIA, "5!n5!n11!n2!n"),
    MAURITIUS(IsoCountry.MAURITIUS, "4!a2!n2!n12!n3!n3!a"),
    MOLDOVA(IsoCountry.MOLDOVA, "2!c18!c"),
    MONACO(IsoCountry.MONACO, "5!n5!n11!c2!n"),
    MONTENEGRO(IsoCountry.MONTENEGRO, "3!n13!n2!n"),
    NETHERLANDS(IsoCountry.NETHERLANDS, "4!a10!n"),
    NORWAY(IsoCountry.NORWAY, "4!n6!n1!n"),
    PAKISTAN(IsoCountry.PAKISTAN, "4!a16!c"),
    PALESTINE(IsoCountry.PALESTINE, "4!a21!c"),
    POLAND(IsoCountry.POLAND, "8!n16!n"),
    PORTUGAL(IsoCountry.PORTUGAL, "4!n4!n11!n2!n"),
    ROMANIA(IsoCountry.ROMANIA, "4!a16!c"),
    SAN_MARINO(IsoCountry.SAN_MARINO, "1!a5!n5!n12!c"),
    SAUDI_ARABIA(IsoCountry.SAUDI_ARABIA, "2!n18!c"),
    SERBIA(IsoCountry.SERBIA, "3!n13!n2!n"),
    SLOVAKIA(IsoCountry.SLOVAKIA, "4!n6!n10!n"),
    SLOVENIA(IsoCountry.SLOVENIA, "5!n8!n2!n"),
    SPAIN(IsoCountry.SPAIN, "4!n4!n1!n1!n10!n"),
    SWEDEN(IsoCountry.SWEDEN, "3!n16!n1!n"),
    SWITZERLAND(IsoCountry.SWITZERLAND, "5!n12!c"),
    TIMOR_LESTE(IsoCountry.TIMOR_LESTE, "3!n14!n2!n"),
    TUNISIA(IsoCountry.TUNISIA, "2!n3!n13!n2!n"),
    TURKEY(IsoCountry.TURKEY, "5!n1!c16!c"),
    UNITED_ARAB_EMIRATES(IsoCountry.UNITED_ARAB_EMIRATES, "3!n16!n"),
    UNITED_KINGDOM(IsoCountry.UNITED_KINGDOM, "4!a6!n8!n"),
    QATAR(IsoCountry.QATAR, "4!a21!c"),
    BRITISH_VIRGIN_ISLANDS(IsoCountry.BRITISH_VIRGIN_ISLANDS, "4!a16!n");

    private final IsoCountry country;
    private final SwiftPattern bbanPattern;
    private final Set<IsoCountry> subdivisions;

    private BbanStructure(IsoCountry country, String bbanSwiftExpression, IsoCountry... subdivisions) {
        this.country = country;
        this.bbanPattern = SwiftPattern.compile(bbanSwiftExpression);

        if (subdivisions.length == 0) {
            this.subdivisions = EnumSet.noneOf(IsoCountry.class);
        } else {
            this.subdivisions = EnumSet.copyOf(Arrays.asList(subdivisions));
        }
    }

    /**
     * Returns the appropriate BbanStructure given the country, or null if IBAN are not in use in this country.
     *
     * @param country A Country.
     * @return the given country BBAN definition, or null if IBAN are not in use in this country or if the argument is {@code null}.
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
     * Test whether or not the given BBAN is valid.
     *
     * @param bban A non null string.
     * @return {@code true} if the given BBAN is valid against this BBAN structure, {@code false} otherwise.
     * @throws IllegalArgumentException if the given BBAN is null
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
     * @return a non null country
     */
    public IsoCountry getCountry() {
        return country;
    }

    /**
     * Returns this BBAN definition pattern.
     *
     * @return a non null pattern
     */
    public SwiftPattern getBbanPattern() {
        return bbanPattern;
    }

    /**
     * Returns this BBAN definition subdivision countries.
     *
     * @return a non null Set of countries (may be empty).
     */
    public Set<IsoCountry> getSubdivisions() {
        return subdivisions;
    }
}
