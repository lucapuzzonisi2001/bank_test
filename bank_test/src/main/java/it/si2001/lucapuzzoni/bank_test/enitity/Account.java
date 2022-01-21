package it.si2001.lucapuzzoni.bank_test.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Luca Puzzoni
 * Classe per rappresentare un account
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Account {
    private String accountCode;
    private String bicCode;
}