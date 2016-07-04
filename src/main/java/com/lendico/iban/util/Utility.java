package com.lendico.iban.util;

import java.util.Random;


/**
 * Utility class to generate iban.
 * 
 * @author shahg
 *
 */
public class Utility {

    /**
     * Generate random digits of given length.
     * 
     * @param numberOfDigits {@link Integer}
     * @return {@link String}
     */
    public static String getRandomNumberInRange(int numberOfDigits) {

        StringBuilder builder = new StringBuilder();
        
        
        int[] c = new int[numberOfDigits];
        
        Random r = new Random();
        
        for(int i = 0 ; i < c.length ; i ++){
            builder.append(r.nextInt(9));            
        }
        
        return builder.toString();
    }

    /**
     * Generate random alphabets of given length.
     * 
     * @param numberOfCharacters {@link Integer}
     * @return {@link String}
     */
    public static String getRandomAlphabetsInRange(int numberOfCharacters) {

        char[] c = new char[numberOfCharacters];
        Random r = new Random();
        
        for(int i = 0 ; i < c.length ; i ++){
            c[i] = (char)(r.nextInt(26) + 'A');
        }
        
        return new String(c);
    }

    /**
     * Replace alphabet with digits.
     * 
     * @param alphabets {@link String}
     * @return {@link String}
     */
    public static String alphabetToDigits(String alphabets) {

        StringBuilder stringBuilder = new StringBuilder();
        
        for(int i = 0 ; i < alphabets.length(); i ++){
            Character charAt = alphabets.charAt(i);
            if(Character.isLetter(charAt)){
                stringBuilder.append(Initialize.AlphabetToDigits.get(charAt));
            }else{
                stringBuilder.append(charAt);
            }
        }
        
        return new String(stringBuilder);
    }

}
