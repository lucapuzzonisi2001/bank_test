package it.si2001.lucapuzzoni.bank_test.enitity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Luca Puzzoni
 * Classe per la modellazione di una transazione
 */
@Entity @Table(name = "transactions")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Transaction {
    @Id private Long transactionId;

    @Column(name="operationId") private String operationId;
    @Column(name="accountingDate") private String accountingDate;
    @Column(name="valueDate") private String valueDate;
    @Column(name="amount") private Double amount;
    @Column(name="currency") private String currency;
    @Column(name="description") private String description;
}
