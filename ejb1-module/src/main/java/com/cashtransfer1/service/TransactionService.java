package com.cashtransfer1.service;

import com.cashtransfer1.entity.Transaction;

import javax.ejb.Local;

/**
 * Created by VP on 02.05.2017.
 */

@Local
public interface TransactionService {
    Transaction save(Transaction transaction);
    Transaction saveWithRollback(Transaction transaction);
    void delete(Transaction transaction);
    Transaction cashTransfer(Transaction transaction);
//    Transaction cashTransferWithException(Transaction transaction);
}
