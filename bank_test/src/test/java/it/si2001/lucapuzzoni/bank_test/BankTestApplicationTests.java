package it.si2001.lucapuzzoni.bank_test;

import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import it.si2001.lucapuzzoni.bank_test.controller.BankTestController;
import it.si2001.lucapuzzoni.bank_test.enitity.MoneyTransfer;

@SpringBootTest
class BankTestApplicationTests {
	private final Long accountID = 14537780L;
	private final Gson gson = new Gson();

	@Autowired BankTestController bankTestController;

	@Test void contextLoads(){}

	@Test void a_getSale(){
		ResponseEntity<?> response = bankTestController.getSale(this.accountID);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assert(response.getBody().toString().contains("status=OK"));
		assert(response.getBody().toString().contains("error=[]"));
	}

	@Test void b_getTransactions(){
		ResponseEntity<?> response = bankTestController.getTransactions(this.accountID, "2019-01-01", "2019-12-01");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assert(response.getBody().toString().contains("accountingDate=2019-"));
		assert(response.getBody().toString().contains("valueDate=2019-"));
	}

	@Test void c_perform(){
		String json = "{'creditor':{'name':'JohnDoe','account':{'accountCode':'IT23A0336844430152923804660','bicCode':'SELBIT2BXXX'},"+
		"'address':{'address':null,'city':null,'countryCode':null}},'executionDate':'2019-04-01','uri':'REMITTANCE_INFORMATION','description'"+
		":'Paymentinvoice75/2017','amount':800,'currency':'EUR','isUrgent':false,'isInstant':false,'feeType':'SHA','feeAccountId':'45685475',"+
		"'taxRelief':{'taxReliefId':'L449','isCondoUpgrade':false,'creditorFiscalCode':'56258745832','beneficiaryType':'NATURAL_PERSON',"+
		"'naturalPersonBeneficiary':{'fiscalCode1':'MRLFNC81L04A859L','fiscalCode2':null,'fiscalCode3':null,'fiscalCode4':null,'fiscalCode5':null}"+
		",'legalPersonBeneficiary':{'fiscalCode':null,'legalRepresentativeFiscalCode':null}}}";
		MoneyTransfer paylaod = gson.fromJson(json, MoneyTransfer.class);
		
		ResponseEntity<?> response = bankTestController.performMoneyTransfer(this.accountID, paylaod);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assert(response.getBody().toString().contains("status=KO"));
	}
}