package it.si2001.lucapuzzoni.bank_test.utils;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import it.si2001.lucapuzzoni.bank_test.enitity.MoneyTransfer;

/**
 * @author Luca Puzzoni
 * Classe Util per la creazione di HttpEntities
 */
@Component
public class RestTemplateUtil {
    @Value("${banktest.authSchema}") private String authSchema;
    @Value("${banktest.apiKey}") private String apiKey;
    
    /**
     * Metodo per la creazione di una HttpEntity
     * @return Rende la HttpEntity creata
     */
    public HttpEntity<String> getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Auth-Schema", this.authSchema);
        headers.set("apiKey", this.apiKey);
        headers.set("X-Time-Zone", "Europe/Rome");
        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        return entity;
    }
    
    /**
     * Metodo per la creazione di una HttpEntity con un body per una POST
     * @return Rende la HttpEntity creata
     */
    public HttpEntity<String> getHttpEntityToPost(MoneyTransfer body){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Auth-Schema", this.authSchema);
        headers.set("apiKey", this.apiKey);
        headers.set("X-Time-Zone", "Europe/Rome");

        Gson gson = new Gson();
        final HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(body), headers);

        return entity;
    }
}
