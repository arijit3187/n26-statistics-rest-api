package com.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statistics.common.APIError;
import com.statistics.exception.ValidationException;
import com.statistics.model.Transaction;

/**
 * Logic for processing Transactions
 * 
 * @author arijit nandi
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	StatisticsService statisticsService;

	@Override
	public void addTransaction(Transaction transaction) {
		//validate the transaction
		if(isValidated(transaction)){
		statisticsService.computeStatistics(transaction);
		}
		
	}
	
	public Boolean isValidated(Transaction transaction){
		if(transaction == null) throw new ValidationException(APIError.VALIDATION_EMPTY_REQUEST_BODY);
		
        if(transaction.getTimestamp() == null) throw new ValidationException(APIError.VALIDATION_MISSING_TIMESTAMP);
        
        if(transaction.getAmount() == null) throw new ValidationException(APIError.VALIDATION_MISSING_AMOUNT);
		
        return true;
		
	}
	

}
