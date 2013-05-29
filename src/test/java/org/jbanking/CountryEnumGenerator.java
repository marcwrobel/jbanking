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

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.Normalizer;
import java.util.List;

/**
 * Generate {@link Country} enum values from the list provided by the International Organization for Standardization.
 */
public class CountryEnumGenerator {

    private static final InputSupplier<? extends InputStream> ISO_FILE_SUPPLIER = new InputSupplier<InputStream>() {
        @Override
        public InputStream getInput() throws IOException {
            return new URL(ISO_FILE_URL).openStream();
        }
    };

    private static final Splitter ISO_FILE_SPLITTER = Splitter.on(";").omitEmptyStrings().trimResults();

    static final String ISO_FILE_URL = "http://www.iso.org/iso/home/standards/country_codes/country_names_and_code_elements_txt.htm";

    @Test
    public void generateEnumFromISOList() throws IOException {
        InputSupplier<InputStreamReader> readerSupplier = CharStreams.newReaderSupplier(ISO_FILE_SUPPLIER, Charsets.UTF_8);

        for (String line : CharStreams.readLines(readerSupplier)) {
            List<String> elements = Lists.newArrayList(ISO_FILE_SPLITTER.split(line.toUpperCase()));
            if (elements.size() != 2) {
                continue;
            }

            String code = elements.get(1);
            if (code.length() != 2) {
                continue;
            }

            String name;
            if (code.equals("CD")) {
                name = "THE_DEMOCRATIC_REPUBLIC_OF_THE_CONGO";
            } else if (code.equals("KP")) {
                name = "NORTH_KOREA";
            } else if (code.equals("KR")) {
                name = "SOUTH_KOREA";
            } else if (code.equals("VG")) {
                name = "BRITISH_VIRGIN_ISLANDS";
            } else if (code.equals("VI")) {
                name = "US_VIRGIN_ISLANDS";
            } else {
                name = toJavaIdentifier(elements.get(0));
            }

            System.out.println(String.format("%s(\"%s\"),", name, code));
        }
    }

    /**
     * Clean unwanted characters and strip text between parenthesis or after a comma from the provided country name in order to obtain a suitable java identifier.
     */
    private String toJavaIdentifier(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[^\\p{ASCII}]", "");
        s = s.replaceAll(",.+", "");
        s = s.replaceAll("\\(.+\\)", "");
        s = s.replaceAll("[^A-Z]", " ");
        s = s.replaceAll("\\s+$", "");
        s = s.replaceAll("\\s+", "_");
        return s;

    }

}
