package com.lendico.iban.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * Helper class to get information when application is up.
 *  
 * @author shahg
 *
 */
public class Initialize {

    
    /**
     * Used to show options to user when application is run.
     */
    public static final Map<String, String> countries = new HashMap<>();

    /**
     * Helper collection while generating iban.
     */
    public static final Map<Character, String> AlphabetToDigits = new HashMap<>();

    static{
        countries.put("1", "GERMANY");
        countries.put("2", "AUSTRIA");
        countries.put("3", "NETHERLANDS");
        countries.put("4", "EXIT");

        AlphabetToDigits.put('A', "10");
        AlphabetToDigits.put('B', "11");
        AlphabetToDigits.put('C', "12");
        AlphabetToDigits.put('D', "13");
        AlphabetToDigits.put('E', "14");
        AlphabetToDigits.put('F', "15");
        AlphabetToDigits.put('G', "16");
        AlphabetToDigits.put('H', "17");
        AlphabetToDigits.put('I', "18");
        AlphabetToDigits.put('J', "19");
        AlphabetToDigits.put('K', "20");
        AlphabetToDigits.put('L', "21");
        AlphabetToDigits.put('M', "22");
        AlphabetToDigits.put('N', "23");
        AlphabetToDigits.put('O', "24");
        AlphabetToDigits.put('P', "25");
        AlphabetToDigits.put('Q', "26");
        AlphabetToDigits.put('R', "27");
        AlphabetToDigits.put('S', "28");
        AlphabetToDigits.put('T', "29");
        AlphabetToDigits.put('U', "30");
        AlphabetToDigits.put('V', "31");
        AlphabetToDigits.put('W', "32");
        AlphabetToDigits.put('X', "33");
        AlphabetToDigits.put('Y', "34");
        AlphabetToDigits.put('Z', "35");

    }
}
