package it.si2001.lucapuzzoni.bank_test.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Luca Puzzoni
 * Classe per rappresentare un indirizzo
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Address {
    private String address;
    private String city;
    private String countryCode;
}
