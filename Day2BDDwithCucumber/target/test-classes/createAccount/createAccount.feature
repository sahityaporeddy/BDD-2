@account
Feature: Create New Account
				 Create New Account for the valid customer details

	Scenario: For valid customer create new account
    Given customer details
    When Valid customer
    And valid open balance
    Then create new account 
    
	Scenario: For Invalid customer
						For invalid customer details throw error message
		Given Customer details
		When Invalid Customer
		Then throw 'Invalid Customer' error message
		
	Scenario: For Invalid Opening Balance
		Given customer details and opening balance
		When Invalid opening balance
		Then throw 'Insufficient Balance' error message
	
Scenario: Find account details
Find account details for the given account number
Given Account number
When Valid account Number
Then find account details

Scenario: Withdraw Amount from Account 
Find account details and withdraw amount
Given Accountnumber 1001 and amount 1000
When Find account and do withdraw
Then Update the accountdetails


		