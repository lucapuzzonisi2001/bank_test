package it.si2001.lucapuzzoni.bank_test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Luca Puzzoni
 * Classe delle configurazioni custom
 */
@Component @ConfigurationProperties("banktest")
public class BankTestProperties {
    private String baseUrl;             //Property che indica l'end-point di base
    private String saleRead;            //Property che indica l'end-point per richiedere la lettura saldo    
    private String transationcsRead;    //Property che indica l'end-point per richiedere la lettura delle transazioni
    private String moneyTransfer;       //Property che indica l'API da interrogare per la lettura del saldo
    private String authSchema;          //Property che indica lo schema di autenticazione per le richieste
    private String apiKey;              //Property che indica la chiave API per le richieste
    private Long accountID;             //Property che indica l'ID dell'account del quale leggere i dati
}