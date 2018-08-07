package createAccount;

import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.cap.dao.AccountDaoImpl;
import org.cap.dao.IAccountDao;
import org.cap.exception.InvalidCustomer;
import org.cap.exception.InvalidOpeningBalance;
import org.cap.model.Account;
import org.cap.model.Address;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdef {

	private Customer customer;
	private double openingBalance;
	
	private IAccountService accountService;
	private double amount_withdraw;
	private int accountNo;
	
	@Mock
	private IAccountDao accountDao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		customer= new Customer();
		openingBalance=500;
		
		accountService = new AccountServiceImpl(accountDao);
	}

	@Given("^customer details$")
	public void for_customer_details() throws Throwable {
		  customer.setFirstName("Khishore");
		  customer.setLastName("Kumar");
		  Address address=new Address();
		  address.setDoorNo("12");
		  address.setCity("chennai");
		  customer.setAddress(address);
	}

	@When("^Valid customer$")
	public void valid_customer() throws Throwable {
	 assertNotNull(customer);
	}

	@When("^valid open balance$")
	public void valid_open_balance() throws Throwable {
	  assertTrue(openingBalance>=500);
	}

	@Then("^create new account$")
	public void create_new_account() throws Throwable {
		
		Account account = new Account();
		account.setAccountNo(1);
		account.setCustomer(customer);
		account.setOpeningBalance(500);
		
		Mockito.when(accountDao.addAccount(account)).thenReturn(true);
		
		//Business logic
		Account account1=accountService.createAccount(customer,openingBalance);
		
		Mockito.verify(accountDao).addAccount(account1);
		assertNotNull(account1);
		assertEquals(openingBalance, account.getOpeningBalance(),0.0);
		assertEquals(account1.getAccountNo(), 1);
		
	}
	
	@Given("^Customer details$")
	public void customer_details() throws Throwable {
	   customer = null;
	}

	@When("^Invalid Customer$")
	public void invalid_Customer() throws Throwable {
	   assertNull(customer);
	}

	@Then("^throw 'Invalid Customer' error message$")
	public void throw_Invalid_Customer_error_message() throws Throwable {
	   try {
		   accountService.createAccount(customer, 3000);
	   }catch (InvalidCustomer e) {
		// TODO: handle exception
	   }
	}

	@Given("^customer details and opening balance$")
	public void customer_details_and_opening_balance() throws Throwable {
		  customer.setFirstName("Sahithya");
		  customer.setLastName("Poreddy");
		  Address address=new Address();
		  address.setDoorNo("402");
		  address.setCity("chennai");
		  customer.setAddress(address);
		  openingBalance=100;
	}

	@When("^Invalid opening balance$")
	public void invalid_opening_balance() throws Throwable {
		 assertTrue(openingBalance<500);
	}

	@Then("^throw 'Insufficient Balance' error message$")
	public void throw_Insufficient_Balance_error_message() throws Throwable {
		try {
			   accountService.createAccount(customer, openingBalance);
		   }catch (InvalidOpeningBalance e) {
			// TODO: handle exception
		   }
	}
	
	@Given("^Account number$")
	public void account_number() throws Throwable {
	    accountNo=1001;
	}

	@When("^Valid account Number$")
	public void valid_account_Number() throws Throwable {
	   assertTrue(accountNo>0);
	}

	@Then("^find account details$")
	public void find_account_details() throws Throwable {
		
		Account account1=new Account();
		account1.setAccountNo(1001);
		account1.setCustomer(customer);
		account1.setOpeningBalance(10000);
		Mockito.when(accountDao.findAccountById(1001)).thenReturn(account1);
	  Account account =accountService.findAccountById(accountNo);  
	  Mockito.verify(accountDao).findAccountById(1001);
	}
	
	@Given("^Accountnumber (\\d+) and amount (\\d+)$")
	public void accountnumber_and_amount(int accNo, int amount) throws Throwable {
	    this.accountNo=accNo;
	    this.amount_withdraw=amount;
	    
	}

	@When("^Find account and do withdraw$")
	public void find_account_and_do_withdraw() throws Throwable {
		
	    
	}

	@Then("^Update the accountdetails$")
	public void update_the_accountdetails() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}



}
