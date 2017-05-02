package com.cashtransfer2.exception;

import javax.ejb.ApplicationException;
import javax.ejb.EJBException;

/**
 * Created by VP on 02.05.2017.
 */

@ApplicationException(rollback = true)
public class TransactionException extends EJBException {
    public TransactionException() { }
    public TransactionException(String message) {
        super(message);
    }
}
