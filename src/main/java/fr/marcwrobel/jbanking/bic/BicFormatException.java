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
package fr.marcwrobel.jbanking.bic;

/**
 * Thrown to indicate that an attempt has been made to convert a string to a {@link Bic}, but that the string does not have the appropriate format.
 *
 * @author Marc Wrobel
 * @see Bic#Bic(String)
 * @since 1.0
 */
public final class BicFormatException extends RuntimeException {

    private final String inputString;

    /**
     * Constructs a <code>BicFormatException</code> with the string that caused the error and the given detail message.
     *
     * @param input   a string
     * @param message a string
     */
    private BicFormatException(String input, String message) {
        super(message);
        this.inputString = input;
    }

    static BicFormatException forNotProperlyFormattedInput(String input) {
        return new BicFormatException(input, String.format("'%s' format is not appropriate for a BIC", input));
    }

    static BicFormatException forUnknownCountryCode(String input) {
        return new BicFormatException(input, String.format("'%s' country code is unknown", input));
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
