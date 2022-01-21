package it.si2001.lucapuzzoni.bank_test.service;

import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.stereotype.Service;
import it.si2001.lucapuzzoni.bank_test.enitity.MoneyTransfer;

/**
 * @author Luca Puzzoni
 * Interfaccia di servizio per la gestione delle chiamate API
 */
@Service
public interface BankTestService {
    public Map getSaleRead(Long accountID);
    public Map getTransactionsRead(Long accountID, String dateFrom, String dateTo);
    public Map performMoneyTransfer(Long accountID, MoneyTransfer paylaod) throws JsonMappingException, JsonProcessingException ;
}