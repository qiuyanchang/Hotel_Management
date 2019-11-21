package com.ischoolbar.programmer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.AccountDao;
import com.ischoolbar.programmer.entity.Account;
import com.ischoolbar.programmer.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {

	
	@Autowired
	private AccountDao accountDao;

	public int add(Account account) {
		// TODO Auto-generated method stub
		return accountDao.add(account);
	}

	public int edit(Account account) {
		// TODO Auto-generated method stub
		return accountDao.edit(account);
	}

	public int delete(Long id) {
		// TODO Auto-generated method stub
		return accountDao.delete(id);
	}

	public List<Account> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return accountDao.findList(queryMap);
	}

	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return accountDao.getTotal(queryMap);
	}

	public Account find(Long id) {
		// TODO Auto-generated method stub
		return accountDao.find(id);
	}

	public Account findByName(String name) {
		// TODO Auto-generated method stub
		return accountDao.findByName(name);
	}

	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDao.findAll();
	}
	
	

}
