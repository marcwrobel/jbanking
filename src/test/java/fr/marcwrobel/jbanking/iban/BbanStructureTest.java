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
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for the {@link BbanStructure} enum.
 *
 * @author Marc Wrobel
 */
public class BbanStructureTest {

    @Test
    public void nullReturnsNull() {
        assertNull(BbanStructure.forCountry(null));
    }

    @Test
    public void unsupportedCountryReturnsNull() {
        assertNull(BbanStructure.forCountry(IsoCountry.UNITED_STATES));
    }

    @Test
    public void supportedCountryReturnsCorrespondingBbanDefinition() {
        for (BbanStructure structure : BbanStructure.values()) {
            assertEquals(structure, BbanStructure.forCountry(structure.getCountry()));
        }
    }

    @Test
    public void supportedCountrySubdivisionReturnsCorrespondingBbanDefinition() {
        for (BbanStructure structure : BbanStructure.values()) {
            for (IsoCountry country : structure.getSubdivisions()) {
                assertEquals(structure, BbanStructure.forCountry(country));
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void isBbanValidCannotBeCalledWithNull() {
        BbanStructure.ALBANIA.isBbanValid(null);
    }

}
