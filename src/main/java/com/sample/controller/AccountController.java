package com.sample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.exceptions.AccountNotFoundException;
import com.sample.model.Account;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AccountController {
	List<Account> accounts;

	public AccountController() {
		accounts = new ArrayList<>();
		accounts.add(new Account(1, "Khiet1"));
		accounts.add(new Account(2, "Sacombank"));
		accounts.add(new Account(3, "Amaris"));
		accounts.add(new Account(4, "Khiet1"));
		accounts.add(new Account(5, "Khiet"));
		accounts.add(new Account(6, "Cau Hai"));
		accounts.add(new Account(7, "Hien Ho"));
		accounts.add(new Account(8, "TriTo"));
	}

	@GetMapping(value = "")
	public String index() throws AccountNotFoundException {
		return "Welcome to lab.";
	}

	
	@GetMapping(value = "/findById/{id}")
	public Account findById(@PathVariable("id") Integer id) throws AccountNotFoundException {
		log.info(String.format("Account.findByNumber(%s)", id));
		return accounts.stream().filter(it -> it.getId().intValue() == id.intValue()).findFirst()
				.orElseThrow(() -> new AccountNotFoundException("id: " + id));

	}

	@GetMapping(value = "/findByName/{name}")
	public List<Account> findByName(@PathVariable("name") String name) throws AccountNotFoundException {
		log.info(String.format("Account.findByName(%s)", name));
		return accounts.stream().filter(it -> it.getName().equals(name)).collect(Collectors.toList());
	}

	@PostMapping(value = "/create")
	public Account createNewAccount(@RequestBody Account account) {
		log.info("Account.createNewAccount()");
		if (account.getId() != null) {
			return null;
		}
		int size = accounts.size();
		account.setId(size + 1);
		accounts.add(account);
		return account;
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean deleteAccount(@PathVariable Integer id) {
		log.info(String.format("Account.deleteAccount(%s)", id));
		try {
			Account byId = findById(id);
			accounts.remove(byId);
		} catch (AccountNotFoundException exception) {
			return false;
		}
		return true;
	}

	@PutMapping(value = "/update/")
	public boolean updateAccount(@PathVariable Account account) {
		log.info("Account.deleteAccount()");
		try {
			Account byId = findById(account.getId());
			byId.setName(account.getName());
			accounts.add(byId);
		} catch (AccountNotFoundException e) {
			return false;
		}
		return true;
	}
}
