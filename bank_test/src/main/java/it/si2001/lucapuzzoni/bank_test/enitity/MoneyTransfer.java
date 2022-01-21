package it.si2001.lucapuzzoni.bank_test.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Luca Puzzoni
 * Classe per modellare un movimento di denaro
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class MoneyTransfer {
    private Creditor creditor;
    private String executionDate;
    private String uri;
    private String description;
    private Double amount;
    private String currency;
    private boolean isUrgent;
    private boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private TaxRelief taxRelief;
}