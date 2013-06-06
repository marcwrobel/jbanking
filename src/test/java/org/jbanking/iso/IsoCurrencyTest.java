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
package org.jbanking.iso;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

/**
 * Tests for the {@link org.jbanking.iso.IsoCountry} class.
 *
 * @author Marc Wrobel
 */
public class IsoCurrencyTest {

    private static final String CURRENCY_FILE_URL = "http://www.currency-iso.org/dam/downloads/dl_iso_table_a1.xml";

    @Test
    public void fromAlphaCodeAllowsNull() {
        Assert.assertNull(IsoCurrency.fromAlphabeticCode(null));
    }

    @Test
    public void fromAlphaCodeAllowsUnknownOrInvalidCodes() {
        Assert.assertNull(IsoCurrency.fromAlphabeticCode("AA"));
    }

    @Test
    public void fromAlphaCodeIsNotCaseSensitive() {
        Assert.assertEquals(IsoCurrency.EURO, IsoCurrency.fromAlphabeticCode(IsoCurrency.EURO.getAlphabeticCode().toLowerCase()));
    }

    @Test
    public void fromAlphaCodeWorksWithExistingValues() {
        for (IsoCurrency currency : IsoCurrency.values()) {
            Assert.assertEquals(currency, IsoCurrency.fromAlphabeticCode(currency.getAlphabeticCode()));
        }
    }

    @Test
    public void fromNumericCodeAllowsNull() {
        Assert.assertEquals(IsoCurrency.NO_UNIVERSAL_CURRENCY, IsoCurrency.fromNumericCode(null));
    }

    @Test
    public void fromNumericCodeAllowsUnknownOrInvalidCodes() {
        Assert.assertNull(IsoCurrency.fromNumericCode(-1));
        Assert.assertNull(IsoCurrency.fromNumericCode(1000));
    }

    @Test
    public void fromNumericCodeWorksWithExistingValuesExceptForUicFranc() {
        for (IsoCurrency currency : IsoCurrency.values()) {
            if (currency != IsoCurrency.UIC_FRANC) {
                Assert.assertEquals(currency, IsoCurrency.fromNumericCode(currency.getNumericCode()));
            }
        }
    }

    @Test
    public void ensureEnumCompleteness() throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new URL(CURRENCY_FILE_URL));

        for (Iterator i = document.getRootElement().elementIterator("ISO_CURRENCY"); i.hasNext(); ) {
            Element element = (Element) i.next();

            IsoCurrency c1 = IsoCurrency.fromAlphabeticCode(element.elementText("ALPHABETIC_CODE"));
            IsoCurrency c2 = IsoCurrency.fromNumericCode(safeParseInt(element.elementText("NUMERIC_CODE")));

            Assert.assertNotNull(c1);
            Assert.assertNotNull(c2);

            if (c1 != IsoCurrency.UIC_FRANC) {
                Assert.assertEquals(c1, c2);
            }
        }
    }

    private Integer safeParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
