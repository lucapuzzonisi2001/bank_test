package it.si2001.lucapuzzoni.bank_test.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import it.si2001.lucapuzzoni.bank_test.enitity.MoneyTransfer;
import it.si2001.lucapuzzoni.bank_test.enitity.Transaction;
import it.si2001.lucapuzzoni.bank_test.repository.TransactionRepository;
import it.si2001.lucapuzzoni.bank_test.service.BankTestService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import it.si2001.lucapuzzoni.bank_test.utils.RestTemplateUtil;

/**
 * @author Luca Puzzoni
 * Implementazione interfaccia di servizio per la gestione delle chiamate API
 */
@Service
public class BankTestServiceImpl implements BankTestService{
    Logger logger = LoggerFactory.getLogger(BankTestServiceImpl.class);
    
    @Value("${banktest.baseUrl}") private String baseUrl;
    @Value("${banktest.saleRead}") private String saleRead;
    @Value("${banktest.transactionsRead}") private String transactionsRead;
    @Value("${banktest.moneyTransfer}") private String moneyTransfer;
    
    @Autowired private RestTemplateUtil restTemplateUtil;
    @Autowired private TransactionRepository transactionRepository;
    private final RestTemplate rt = new RestTemplate();

    /**
     * Metodo per la lettura del saldo
     * @return Una Map con il risultato della chiamata API
     */
    @Override
    public Map getSaleRead(Long accountID) throws RestClientException {
        String url = String.format("%s%d%s", this.baseUrl, accountID, this.saleRead);
        logger.info(String.format("Request to: %s", url));
        Map response = rt.exchange(url, HttpMethod.GET, restTemplateUtil.getHttpEntity(), Map.class).getBody();
        logger.info(String.format("Response: %s", response));

        return response;
    }

    /**
     * Metodo per la lettura delle transazioni
     * @return Una Map con il risultato della chiamata API
     */
    @Override
    public Map getTransactionsRead(Long accountID, String dateFrom, String dateTo) throws RestClientException {
        String url = String.format("%s%d%s?fromAccountingDate=%s&toAccountingDate=%s", this.baseUrl, accountID, this.transactionsRead, dateFrom, dateTo);
        logger.info(String.format("Request to: %s", url));

        LinkedHashMap r = rt.exchange(url, HttpMethod.GET, restTemplateUtil.getHttpEntity(), LinkedHashMap.class).getBody();
        ArrayList<Transaction> response = (ArrayList) ((LinkedHashMap) r.get("payload")).get("list");
        logger.info(String.format("Response: %s", response));

        Gson gson = new Gson();
        logger.info("Avvio processo di salvataggio...");
        for(int i=0; i < response.size(); i++){
            Transaction temp = gson.fromJson(gson.toJson(response.get(i)), Transaction.class);
            if(!this.transactionRepository.findById(temp.getTransactionId()).isPresent()){
                this.transactionRepository.save(temp);
            }
        }
        logger.info("Fine processo di salvataggio");

        return r;
    }

    /**
     * Metodo per la disposizione di un Bonifico
     * @return Una Map con il risultato della chiamata API
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    @Override
    public Map performMoneyTransfer(Long accountID, MoneyTransfer paylaod) throws JsonMappingException, JsonProcessingException, RestClientException {
        try{
            String url = String.format("%s%d%s", this.baseUrl, accountID, this.moneyTransfer);
            logger.info(String.format("Request to: %s", url));
            Map response = rt.exchange(url, HttpMethod.POST, restTemplateUtil.getHttpEntityToPost(paylaod), Map.class).getBody();
            logger.info(String.format("Response: %s", response));
    
            return response;
        } catch (HttpServerErrorException e){
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(e.getResponseBodyAsString().replace("\r\n", ""), Map.class);

            logger.info(String.format("Response: %s", map));
            return map;
        }
    }    
}