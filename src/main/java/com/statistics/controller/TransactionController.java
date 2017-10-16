package com.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.statistics.model.Transaction;
import com.statistics.service.TransactionService;

/**
* REST API for consuming transaction requests
*
* @author arijit nandi
*/

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private static final int SECONDS = 60;
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction){
		
		if ((System.currentTimeMillis() - transaction.getTimestamp()) / 1000 < SECONDS) {
			transactionService.addTransaction(transaction);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		
	}

}
