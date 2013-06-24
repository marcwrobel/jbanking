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

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generate {@link IsoCountry} and {@link IsoCurrency} enum values from the lists provided by the International Organization for Standardization.
 *
 * @author Marc Wrobel
 * @since 1.0
 */
public class EnumGenerator {

    private static final String COUNTRY_FILE_URL = "http://www.iso.org/iso/home/standards/country_codes/country_names_and_code_elements_txt.htm";
    private static final InputSupplier<? extends InputStream> COUNTRY_FILE_SUPPLIER = new InputSupplier<InputStream>() {
        @Override
        public InputStream getInput() throws IOException {
            return new URL(COUNTRY_FILE_URL).openStream();
        }
    };
    private static final Splitter COUNTRY_FILE_SPLITTER = Splitter.on(";").omitEmptyStrings().trimResults();

    private static final String CURRENCY_FILE_URL = "http://www.currency-iso.org/dam/downloads/dl_iso_table_a1.xml";

    private static final Map<String, String> REPLACEMENTS = ImmutableMap.<String, String>builder()
            .put("CONGO, THE DEMOCRATIC REPUBLIC OF THE", "THE DEMOCRATIC REPUBLIC OF THE CONGO")
            .put("KOREA, DEMOCRATIC PEOPLE’S REPUBLIC OF", "NORTH KOREA")
            .put("KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF", "NORTH KOREA")
            .put("KOREA, REPUBLIC OF", "SOUTH KOREA")
            .put("VIRGIN ISLANDS (BRITISH)", "BRITISH VIRGIN ISLANDS")
            .put("VIRGIN ISLANDS, BRITISH", "BRITISH VIRGIN ISLANDS")
            .put("VIRGIN ISLANDS (US)", "US VIRGIN ISLANDS")
            .put("VIRGIN ISLANDS, U.S.", "US VIRGIN ISLANDS")
            .put("HOLY SEE (VATICAN CITY STATE)", "VATICAN CITY STATE")
            .put("VATICAN CITY STATE (HOLY SEE)", "VATICAN CITY STATE")
            .put("LAO PEOPLE'S DEMOCRATIC REPUBLIC", "LAO PEOPLES DEMOCRATIC REPUBLIC")
            .put("LAO PEOPLE’S DEMOCRATIC REPUBLIC", "LAO PEOPLES DEMOCRATIC REPUBLIC")
            .put("US DOLLAR (NEXT DAY)", "US DOLLAR NEXT DAY")
            .put("US DOLLAR (SAME DAY)", "US DOLLAR SAME DAY")
            .put("Bond Markets Unit European Composite Unit (EURCO)", "EURCO")
            .put("Bond Markets Unit European Monetary Unit (E.M.U.-6)", "EMU_6")
            .put("Bond Markets Unit European Unit of Account 9 (E.U.A.-9)", "EUA_9")
            .put("Bond Markets Unit European Unit of Account 17 (E.U.A.-17)", "EUA_17")
            .put("The codes assigned for transactions where no currency is involved", "No_Currency")
            .put("Codes specifically reserved for testing purposes", "Testing_Code")
            .build();

    private final class CountryData {
        private final String code;
        private final String name;

        private CountryData(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String toString() {
            return String.format("%s(\"%s\"),", name, code);
        }
    }

    @Test
    public void countryEnumGenerator() throws IOException {
        InputSupplier<InputStreamReader> supplier = CharStreams.newReaderSupplier(COUNTRY_FILE_SUPPLIER, Charsets.UTF_8);

        Map<String, CountryData> countries = Maps.newTreeMap();

        for (String line : CharStreams.readLines(supplier)) {
            List<String> elements = Lists.newArrayList(COUNTRY_FILE_SPLITTER.split(line));
            if (elements.size() != 2) {
                continue;
            }

            String code = elements.get(1);
            String name = toEnumName(makeReplacements(elements.get(0)));

            if (code.length() != 2) {
                continue;
            }

            countries.put(name, new CountryData(code, name));
        }

        for (CountryData country : countries.values()) {
            System.out.println(country);
        }
    }

    private String makeReplacements(String input) {
        input = CharMatcher.WHITESPACE.trimFrom(input).toUpperCase();

        for (Map.Entry<String, String> entry : REPLACEMENTS.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(input)) {
                return entry.getValue();
            }
        }

        return input;
    }

    private String toEnumName(String s) {
        s = Normalizer.normalize(s.toUpperCase(), Normalizer.Form.NFD);
        s = s.replaceAll("[^\\p{ASCII}]", "");
        s = s.replaceAll(",.+", "");
        s = s.replaceAll("\\(.+\\)", "");
        s = s.replaceAll("[^A-Z0-9]", " ");
        s = s.replaceAll("\\s+$", "");
        s = s.replaceAll("\\s+", "_");
        return s;
    }

    private final class CurrencyData {
        private final String alphaCode;
        private final Integer numCode;
        private final String name;
        private final Integer minorUnit;
        private final Set<IsoCountry> countries = Sets.newHashSet();

        private CurrencyData(String alphaCode, String numCode, String currencyName, String minorUnit) {
            this.alphaCode = alphaCode;
            this.name = toEnumName(currencyName);

            try {
                this.numCode = numCode.isEmpty() ? null : numCode.equalsIgnoreCase("nil") ? null : Integer.parseInt(numCode);
            } catch (NumberFormatException e) {
                throw e;
            }

            Integer mu;
            try {
                mu = Integer.parseInt(minorUnit);
            } catch (NumberFormatException e) {
                mu = null;
            }
            this.minorUnit = mu;
        }

        public void addCountry(String entity) {
            String countryEnumName = toEnumName(entity);

            try {
                IsoCountry country = IsoCountry.valueOf(countryEnumName);
                countries.add(country);
            } catch (IllegalArgumentException e) {
                System.out.printf("Ignoring entity based on name %s%n", countryEnumName);
            }
        }

        public String toString() {
            String countriesString = "";
            if (countries.size() > 0) {
                for (IsoCountry country : countries) {
                    countriesString += ", " + country.name();
                }
            }
            return String.format("%s(\"%s\", %s, %s%s),", name, alphaCode, numCode, minorUnit, countriesString);
        }

    }

    @Test
    public void currencyEnumGenerator() throws MalformedURLException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new URL(CURRENCY_FILE_URL));

        Map<String, CurrencyData> currencies = Maps.newTreeMap();

        for (Iterator i = document.getRootElement().elementIterator("ISO_CURRENCY"); i.hasNext(); ) {
            Element element = (Element) i.next();

            String entity = makeReplacements(element.elementText("ENTITY"));
            String currencyName = makeReplacements(element.elementText("CURRENCY"));
            String alphaCode = element.elementText("ALPHABETIC_CODE");
            String numCode = element.elementText("NUMERIC_CODE");
            String minorUnit = element.elementText("MINOR_UNIT");

            CurrencyData currency = currencies.get(currencyName);
            if (currency == null) {
                currency = new CurrencyData(alphaCode, numCode, currencyName, minorUnit);
                currencies.put(currencyName, currency);
            }
            currency.addCountry(entity);
        }

        for (CurrencyData currency : currencies.values()) {
            System.out.println(currency);
        }
    }
}
