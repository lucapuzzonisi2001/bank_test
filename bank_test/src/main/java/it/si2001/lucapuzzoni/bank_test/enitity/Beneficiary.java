package it.si2001.lucapuzzoni.bank_test.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Luca Puzzoni
 * Classe per modellare un Beneficiario
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Beneficiary {
    private String fiscalCode1;
    private String fiscalCode2;
    private String fiscalCode3;
    private String fiscalCode4;
    private String fiscalCode5;    
}
