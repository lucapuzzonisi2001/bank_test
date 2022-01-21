package it.si2001.lucapuzzoni.bank_test.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Luca Puzzoni
 * Classe per rappresentare un creditore
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Creditor {
    private String name;
    private Account account;
    private Address address;
}