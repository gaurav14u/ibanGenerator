package com.lendico.iban;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.lendico.iban.service.EuropeanUnionCountry;

/**
 * Build Iban number based on country.
 * 
 * @author shahg
 */
@Component
@PropertySource("application.properties")
public class IBANBuilder {

    private static final Logger logger = LoggerFactory.getLogger(IBANBuilder.class);

    @Value("${ibanGenerator.exception.message}")
    private String exceptionMessage;

    @Value("${ibanGenerator.success.message}")
    private String successMessage;

    List<String> ibans = new ArrayList<>();

    /**
     * Build iban.
     * 
     * @param country {@link EuropeanUnionCountry}
     * @return {@link String}
     */
    public String build(EuropeanUnionCountry country) {
        try {

            // country code - DE, NE etc.
            String countryCode = country.countryCode();

            // bank code - Ideally this should be unique and based on bank and branch
            // but since we don't have this information, generate any random number.
            String bankCode = country.bankCode();

            // account number - Generate any random account number.
            String accountNumber = country.accountNumber();

            // check digit - Calculate check digits based on above information.
            String checkDigits = country.checkDigits(countryCode, bankCode, accountNumber);

            String iban = countryCode + checkDigits + bankCode + accountNumber;

            // check if iban already exists. If exists, then generate new one.
            synchronized (ibans) {
                if (ibans.contains(iban)) {
                    build(country);
                } else {
                    ibans.add(iban);
                }

            }

            logger.info(successMessage + iban);
            return iban;

        } catch (Exception e) {
            logger.error(exceptionMessage + "\n" + e.getMessage());
            return null;
        }
    }

}