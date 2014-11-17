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
package fr.marcwrobel.jbanking.creditor;

/**
 * Thrown to indicate that an attempt has been made to convert a string to
 * a {@link fr.marcwrobel.jbanking.creditor.CreditorIdentifier},
 * but that the string does not have the appropriate format.
 *
 * @author Charles Kayser
 * @see fr.marcwrobel.jbanking.creditor.CreditorIdentifier#CreditorIdentifier(String)
 */
public class CreditorIdentifierFormatException extends RuntimeException {

    private final String inputString;

    /**
     * Constructs a <code>CreditorIdentifierFormatException</code> with the string that caused the error and the given detail message.
     *
     * @param input   a string
     * @param message a string
     */
    public CreditorIdentifierFormatException(String input, String message) {
        super(message);
        this.inputString = input;
    }

    /**
     * Creates a {@code CreditorIdentifierFormatException} telling the given Creditor Identifier is not properly formatted.
     */
    public static CreditorIdentifierFormatException forNotProperlyFormattedInput(String input) {
        return new CreditorIdentifierFormatException(input, String.format("'%s' format is not appropriate for a CreditorId", input));
    }

    /**
     * Creates a {@code CreditorIdentifierFormatException} telling the given Creditor Identifier check digits
     * are incorrect.
     */
    static CreditorIdentifierFormatException forIncorrectCheckDigits(String input) {
        return new CreditorIdentifierFormatException(input, String.format("'%s' check digits are incorrect", input));
    }

    /**
     * Creates a {@code CreditorIdentifierFormatException} telling the given Creditor Identifier refers an unknown country.
     */
    static CreditorIdentifierFormatException forUnknownCountry(String input) {
        return new CreditorIdentifierFormatException(input, String.format("'%s' country code is not an ISO 3166-1-alpha-2 code", input));
    }

    /**
     * Returns the input String that caused this exception to be raised.
     *
     * @return a string
     */
    public String getInputString() {
        return inputString;
    }
}
