package com.lendico.iban.service;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.lendico.iban.util.Utility;

/**
 * Interface to facilitate process of generating iban.
 * @author shahg
 *
 */
@Component
public interface EuropeanUnionCountry {

    public static final String ZERO = "0";
    public static final int NINTYEIGHT = 98;
    public static final String NINTYSEVEN = "97";
    public static final String TEMP_CHECKDIGITS = "00";

    
    /**
     * Calculate check digits based on inputs. Common logic for all EU countries.
     * 
     * @param countryCode {@link String}
     * @param bankCode {@link String}
     * @param accountNumber {@link String}
     * @return {@link String}
     */
    default public String checkDigits(String countryCode, String bankCode, String accountNumber){

        String numberAsString = Utility.alphabetToDigits(bankCode + accountNumber + countryCode ) + TEMP_CHECKDIGITS;
        
        BigInteger dividend = new BigInteger(numberAsString);
        
        BigInteger divisor = new BigInteger(NINTYSEVEN);

        Integer result = dividend.mod(divisor).intValue();
        
        Integer checkDigit = NINTYEIGHT - result;
        
        String checkDigitAsString = null;
        
        if(checkDigit < 10){
            checkDigitAsString = ZERO + checkDigit.toString();
        }else{
            checkDigitAsString = checkDigit.toString();
        }
        
        return checkDigitAsString;
        
    }
    
    /**
     * This is taken from constant for now but there should be webservice call during start of application 
     * to get all EU country codes and store it in cache.
     * 
     * @return {@link String}
     */
    public String countryCode();
    
    /**
     * @return {@link String}
     */
    public String bankCode();

    /**
     * @return {@link String}
     */
    public String accountNumber();

}
