package it.si2001.lucapuzzoni.bank_test.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Luca Puzzoni
 * Oggetto per gli sgravi fiscali
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class TaxRelief {
    private String taxReliefId;
    private boolean isCondoUpgrade;
    private String creditorFiscalCode;
    private String beneficiaryType;
    private Beneficiary naturalPersonBeneficiary;
    private LegalBeneficiary legalPersonBeneficiary;
}
