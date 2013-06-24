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

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests for the {@link IbanCheckDigit} class.
 *
 * @author Marc Wrobel
 */
public class IbanCheckDigitTest {

    // Generated using http://www.mobilefish.com/services/random_iban_generator/random_iban_generator.php
    private static final Set<String> VALID_IBANS = Sets.newHashSet(
            "AL42500951008263YMBNVJQPG592",
            "AD8068037409EYVLENCKM36B",
            "AT569679968878262587",
            "AZ41WCPC09WWE5Z357DC0H5M4V5O",
            "BH09BKGN31K7RRNKX6BX3Y",
            "BE11247435144852",
            "BA801060410664917763",
            "BR0544704519170100945405853ZK",
            "BG93OSML036868ABACW4OH",
            "CR1907973232651627907",
            "HR6126088131592490560",
            "CY9048920754P2G9RW3NVXCYT0Y0",
            "CZ5265310894121818381756",
            "DK0497590130583029",
            "DO12PWTC90284552983454956794",
            "EE627838993129304903",
            "FO1218128341341002",
            "FI9774860592510001",
            "FR839677472939PWV9Q74UHN824",
            "GE21OG6039692068085278",
            "DE18291071157514699238",
            "GI88MPPQMLHLXO1G2SRKE1L",
            "GR729341723RLAJNJ10WHEV5PCX",
            "GL1126041283912497",
            "GT72BP80YABMXGRD87KIDELRA4AJ",
            "HU92752873580649542475542408",
            "IS400736102173233535735816",
            "IE46KJLI82294657142673",
            "IL410929888876852849226",
            "IT81S4590379871K0AB4ZQT759R",
            "KZ04608LYDWRN9S1G4N6",
            "KW88VJRPQK04EN0VO7RNU8TJ1WAQ20",
            "LV87IZCRRDNM0TLK00DIJ",
            "LB910252NQPX7CXAIXRQG07J3M1A",
            "LI5737273EEWY16981U6R",
            "LT807046238142274725",
            "LU92632WB10W8CFS57D4",
            "MK25949DMJOPQACDU01",
            "MT10PJRS51597PX93G10K7PYT9CN2IG",
            "MR0775732988241953156481703",
            "MU38YMWQ6747283246010491292RXS",
            "MD026JK24D0RFGDJJPJQHKWN",
            "MC4272385506432J7GHL5DQUF20",
            "ME44698013825239609380",
            "NL18VLPN6958795806",
            "NO7792337227957",
            "PK12FXQFCLUC4U0D7Y649U1L",
            "PL10062120807058963164431234",
            "PS11KEAZTZGUUZTQ3F9OTMVIGILIX",
            "PT31834209000552381227836",
            "RO74JVFO6B4T5J79UJ2SX385",
            "SM74J0860464071EFIDJ0OTELUJ",
            "SA6034GYETER2Q3T4BC3ZXML",
            "RS25224008962961371620",
            "SK4988315651646165399678",
            "SI16831821650532179",
            "ES7804816688444683784937",
            "SE3367488724350721084494",
            "CH48837522XQSTYGPFKBT",
            "TN3214154456081981538080",
            "TR2439111QRT60VYQ0ZPQFTB4F",
            "AE297997987743698771526",
            "GB40CUFM27126929790073",
            "VG88DQDO8896564297833915",
            "YY62DRWQ354548673SC833V5AMLYPNNR78",
            "ZZ70JJXD3109729650459XALAO5L68UDTR1"
    );

    @Test(expected = IllegalArgumentException.class)
    public void nullIsNotValidForCalculation() {
        IbanCheckDigit.INSTANCE.calculate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullIsNotValidForValidation() {
        IbanCheckDigit.INSTANCE.validate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ibanSizeLowerThanFourIsNotValidForCalculation() {
        IbanCheckDigit.INSTANCE.calculate("123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ibanSizeLowerThanFourIsIsNotValidForValidation() {
        IbanCheckDigit.INSTANCE.validate("123");
    }

    @Test
    public void ibanComputation() {
        for (String iban : VALID_IBANS) {
            String countryCode = iban.substring(0, 2);
            String checkDigit = iban.substring(2, 4);
            String bban = iban.substring(4);
            assertEquals(String.format("%s not properly calculated", iban), checkDigit, IbanCheckDigit.INSTANCE.calculate(countryCode + "00" + bban));
        }
    }

    @Test
    public void ibanValidation() {
        for (String iban : VALID_IBANS) {
            assertTrue(String.format("%s should be valid", iban), IbanCheckDigit.INSTANCE.validate(iban));
        }
    }

    @Test
    public void invalidIbanValidation() {
        assertFalse(IbanCheckDigit.INSTANCE.validate("FR45123"));
    }

}
