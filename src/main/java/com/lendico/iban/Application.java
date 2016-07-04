package com.lendico.iban;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lendico.iban.service.impl.Austria;
import com.lendico.iban.service.impl.Germany;
import com.lendico.iban.service.impl.Netherlands;
import com.lendico.iban.util.Initialize;

/**
 * Entry class for the application. Gets user input and forwards it to respective class.
 * 
 * @author shahg
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Germany germany;

    @Autowired
    private Austria austria;

    @Autowired
    private Netherlands netherland;

    @Autowired
    private IBANBuilder ibanBuilder;

    /**
     * @param args {@link String}
     */
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    /* (non-Javadoc)
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     */
    @Override
    public void run(String... args){

        boolean exit = false;
        Iterator<Entry<String, String>> iterator = Initialize.countries.entrySet().iterator();

        logger.info("Choose country to generate IBAN.");

        while (iterator.hasNext()) {

            Entry<String, String> next = iterator.next();

            logger.info(next.getKey() + " : " + next.getValue());

        }

        Scanner reader = new Scanner(System.in);
        String next = reader.next();

        do {
            
            switch (next) {
                case "1":
                    ibanBuilder.build(germany);
                    
                    break;

                case "2":
                    ibanBuilder.build(austria);
                    break;

                case "3":
                    ibanBuilder.build(netherland);
                    break;

                case "4":
                    exit = true;
                    break;
                    
                default:
                    logger.info(next +" is not a valid option.");

            }
            
            if(!exit){
                next = reader.next();
            }
            
        } while (!exit);

        reader.close();

    }

}
