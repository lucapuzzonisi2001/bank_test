package it.si2001.lucapuzzoni.bank_test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import it.si2001.lucapuzzoni.bank_test.enitity.MoneyTransfer;
import it.si2001.lucapuzzoni.bank_test.service.BankTestService;

/**
 * @author Luca Puzzoni
 * Controller per l'esposizione delle API richieste
 */
@RestController @RequestMapping(value = "/api/banktest")
public class BankTestController {
    Logger logger = LoggerFactory.getLogger(BankTestController.class);

    @Autowired private BankTestService bankTestService;

    /**
     * Metodo per la lettura del Saldo
     * @return rende la risposta dalla chiamata all'API
     */
    @GetMapping(value = "/{id}/sale")
    public ResponseEntity<?> getSale(@PathVariable("id") Long accountID){
        logger.info(String.format("Richiesta di lettura saldo su account: %d", accountID));
        try{
            return new ResponseEntity<>(this.bankTestService.getSaleRead(accountID), HttpStatus.OK);
        } catch (RestClientException rce) {
            return new ResponseEntity<>("Errore nella chiamata remota..", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo per la lettura delle transazioni
     * @return rende la risposta dalla chiamata all'API
     */
    @GetMapping(value = "/{id}/transactions/{dateFrom}/{dateTo}")
    public ResponseEntity<?> getTransactions(@PathVariable("id") Long accountID, @PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo){
        logger.info(String.format("Richiesta di lettura transazioni su account: %d Da %s A %s", accountID, dateFrom, dateTo));
        try{
            return new ResponseEntity<>(this.bankTestService.getTransactionsRead(accountID, dateFrom, dateTo), HttpStatus.OK);
        } catch (RestClientException rce) {
            return new ResponseEntity<>("Errore nella chiamata remota..", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * Metodo per la disposizione di un Bonifico
     * @return rende la risposta dalla chiamata all'API
     */
    @PostMapping(value = "/{id}/money-transfers")
    public ResponseEntity<?> performMoneyTransfer(@PathVariable("id") Long accountID,  @RequestBody MoneyTransfer payload){
        try{
            logger.info(String.format("Richiesta di dispoziione bonifico su account: %d", accountID));
            return new ResponseEntity<>(this.bankTestService.performMoneyTransfer(accountID, payload), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Errore");
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}