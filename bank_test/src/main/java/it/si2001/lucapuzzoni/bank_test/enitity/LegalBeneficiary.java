package it.si2001.lucapuzzoni.bank_test.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Luca Puzzoni
 * Classe per rrappresentare un beneficiario per una persona legale
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class LegalBeneficiary {
    private String fiscalCode;
    private String legalRepresentativeFiscalCode;
}