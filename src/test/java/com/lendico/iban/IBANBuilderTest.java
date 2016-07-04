package com.lendico.iban;

import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lendico.iban.service.impl.Austria;
import com.lendico.iban.service.impl.Germany;
import com.lendico.iban.service.impl.Netherlands;
import com.lendico.iban.util.Utility;
public class IBANBuilderTest {
    
    Germany germany;
    Netherlands netherlands;
    Austria austria;
    IBANBuilder iBANBuilder;

    @Before
    public void setUp() throws Exception {
        germany = new Germany();
        netherlands = new Netherlands();
        austria = new Austria();
        iBANBuilder = new IBANBuilder();
        
    }

    @After
    public void cleanUp() throws Exception {
        germany = null;
        netherlands = null;
        austria = null;
        iBANBuilder = null;
    }
    
    /**
     * Test if iban for Germany is valid.
     */
    @Test
    public void testForGermanyIban(){
        String iban = iBANBuilder.build(germany);
        org.junit.Assert.assertEquals(22, iban.length());

        int count = countAlphabets(iban);
        
        org.junit.Assert.assertEquals(2, count);

        BigInteger reminder = verifyCheckDigits(iban);

        org.junit.Assert.assertEquals(1, Integer.parseInt(reminder.toString()));
        
        org.junit.Assert.assertTrue(iban.startsWith("DE"));
    }

    /**
     * Test if iban for Austria is valid.
     */
    @Test
    public void testForAustriaIban(){
        String iban = iBANBuilder.build(austria);
        
        org.junit.Assert.assertEquals(20, iban.length());

        int count = countAlphabets(iban);
        
        org.junit.Assert.assertEquals(2, count);
        
        BigInteger reminder = verifyCheckDigits(iban);

        org.junit.Assert.assertEquals(1, Integer.parseInt(reminder.toString()));
        
        org.junit.Assert.assertTrue(iban.startsWith("AT"));

    }

    /**
     * Test if iban for Netherlands is valid.
     */
    @Test
    public void testForNetherlands(){
        String iban = iBANBuilder.build(netherlands);
        org.junit.Assert.assertEquals(18, iban.length());

        int count = countAlphabets(iban);
        
        org.junit.Assert.assertEquals(6, count);

        BigInteger reminder = verifyCheckDigits(iban);

        org.junit.Assert.assertEquals(1, Integer.parseInt(reminder.toString()));

        org.junit.Assert.assertTrue(iban.startsWith("NL"));

    }

    /**
     * Test if iban for Germany is unique.
     */
    @Test
    public void testUniquenessOfGermanyIban(){
        String iban = iBANBuilder.build(germany);
        String iban1 = iBANBuilder.build(germany);

        org.junit.Assert.assertNotEquals(iban, iban1);
    }

    /**
     * Test if iban for Austria is unique.
     */
    @Test
    public void testUniquenessOfAustriaIban(){
        String iban = iBANBuilder.build(austria);
        String iban1 = iBANBuilder.build(austria);

        org.junit.Assert.assertNotEquals(iban, iban1);
    }

    /**
     * Test if iban for Netherlands is unique.
     */
    @Test
    public void testUniquenessOfNetherlandsIban(){
        String iban = iBANBuilder.build(netherlands);
        String iban1 = iBANBuilder.build(netherlands);

        org.junit.Assert.assertNotEquals(iban, iban1);
    }

    /**
     * @param iban {@link String}
     * @return {@link BigInteger}
     */
    private BigInteger verifyCheckDigits(String iban) {
        String first = iban.substring(0, 4);
        String last = iban.substring(4, iban.length());

        String manipulatedIban = last + first;
        
        String alphabetToDigits = Utility.alphabetToDigits(manipulatedIban);
        
        BigInteger ibanAsBigInteger = new BigInteger(alphabetToDigits);
        
        BigInteger reminder = ibanAsBigInteger.mod(new BigInteger("97"));
        return reminder;
    }


    /**
     * @param iban {@link String}
     * @return {@link Integer}
     */
    private int countAlphabets(String iban) {
        int count = 0;
        char[] charArray = iban.toCharArray();
        for(int i = 0; i < charArray.length; i ++){
            if(Character.isAlphabetic(charArray[i])){
                count++;
            }
        }
        return count;
    }
    
    
}
