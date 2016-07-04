package com.lendico.iban.service.impl;

import org.springframework.stereotype.Component;

import com.lendico.iban.service.EuropeanUnionCountry;
import com.lendico.iban.util.Utility;

/**
 * Country specific logic for iban.
 * 
 * @author shahg
 *
 */
@Component
public class Netherlands implements EuropeanUnionCountry{

    private static final String NETHERLAND_CODE = "NL";
    private static final int ACCOUNT_NUMBER_DIGITS = 10;
    private static final int BANK_CODE_DIGITS = 4;
    
    /* (non-Javadoc)
     * @see com.lendico.iban.service.EuropeanUnionCountry#bankCode()
     */
    @Override
    public String bankCode() {
        return Utility.getRandomAlphabetsInRange(BANK_CODE_DIGITS);
    }

    /* (non-Javadoc)
     * @see com.lendico.iban.service.EuropeanUnionCountry#accountNumber()
     */
    @Override
    public String accountNumber() {
        return Utility.getRandomNumberInRange(ACCOUNT_NUMBER_DIGITS);
    }

    /* (non-Javadoc)
     * @see com.lendico.iban.service.EuropeanUnionCountry#countryCode()
     */
    @Override
    public String countryCode() {
        return NETHERLAND_CODE;
    }

}
