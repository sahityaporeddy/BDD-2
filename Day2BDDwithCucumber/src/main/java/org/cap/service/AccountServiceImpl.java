package org.cap.service;

import org.cap.dao.AccountDaoImpl;
import org.cap.dao.IAccountDao;
import org.cap.exception.InvalidCustomer;
import org.cap.exception.InvalidOpeningBalance;
import org.cap.model.Account;
import org.cap.model.Customer;
import org.cap.util.AccountUtil;

public class AccountServiceImpl  implements IAccountService{
	
	private IAccountDao accountDao=new AccountDaoImpl();

	public AccountServiceImpl(IAccountDao accountDao2) {
		accountDao=accountDao2;
	}
	public AccountServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Account createAccount(Customer customer, double amount) throws InvalidCustomer, InvalidOpeningBalance {
		if(customer!=null) {
			if(amount>=500) {
				Account account = new Account();
				account.setCustomer(customer);
				account.setOpeningBalance(amount);
				account.setAccountNo(AccountUtil.generateAccountNo());
				
				boolean flag = accountDao.addAccount(account);
				if(flag)
					return account;
				else
					return null;
			}
			else {
				throw new InvalidOpeningBalance("Sorry! InvalidOpeningBalance!");
			}
		}
		else {
			throw new InvalidCustomer("Sorry! Customer refers NULL!");
		}
		//return null;
	}
	@Override
	public Account findAccountById(int accountNo) {
		// TODO Auto-generated method stub
		return accountDao.findAccountById(accountNo);
	}
	@Override
	public Account withdraw(int accountNo, double amount_withdraw) {
		// TODO Auto-generated method stub
		return null;
	}

}
