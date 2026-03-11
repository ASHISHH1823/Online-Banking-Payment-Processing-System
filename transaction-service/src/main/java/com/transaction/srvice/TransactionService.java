package com.transaction.srvice;

import java.util.Optional;

import com.transaction.dto.TransferRequest;
import com.transaction.entity.Transaction;

public interface TransactionService {

	String transfer(TransferRequest transferRequest);

}
