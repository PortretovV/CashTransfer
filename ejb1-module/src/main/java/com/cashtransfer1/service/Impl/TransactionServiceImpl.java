package com.cashtransfer1.service.Impl;

import com.cashtransfer1.entity.CreditCard;
import com.cashtransfer1.service.TransactionService;
import com.cashtransfer1.entity.Transaction;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by VP on 02.05.2017.
 */
@Stateless
public class TransactionServiceImpl implements TransactionService {

    @PersistenceContext(unitName = "TRANSACTION1")
    private EntityManager em;
    @Resource
    private SessionContext ctx;

    @Override
    public Transaction cashTransfer(Transaction transaction){
        CreditCard sender = transaction.getSenderCard();
        CreditCard receiver = transaction.getReciverCard();

        double newSenderSum = sender.getDeposit() - transaction.getSumTransaction();
        double newReceiverSum = receiver.getDeposit() + transaction.getSumTransaction();
        sender.setDeposit(newSenderSum);
        receiver.setDeposit(newReceiverSum);
        em.merge(sender);
        em.merge(receiver);

        transaction = em.merge(transaction);
        return transaction;
    }

//    @Override
//    public Transaction cashTransferWithException(Transaction transaction) throws EJBException{
//        CreditCard sender = transaction.getSenderCard();
//        CreditCard receiver = transaction.getReciverCard();
//
//        double newSenderSum = sender.getDeposit() - transaction.getSumTransaction();
//        double newReceiverSum = receiver.getDeposit() + transaction.getSumTransaction();
//
//        sender.setDeposit(newSenderSum);
//        receiver.setDeposit(newReceiverSum);
//
//        em.merge(sender);
//        em.merge(receiver);
//
//        transaction = em.merge(transaction);
//        if(transaction.getSumTransaction()> sender.getDeposit()){
//            throw new EJBException("Должен вызваться откат");
//        }
//        return transaction;
//    }

    @Override
    public Transaction save(Transaction transaction) {
        if(transaction.getIdTransaction() == null){
            em.persist(transaction);
            return transaction;
        }
        else if (transaction.getIdTransaction()>0){
            em.merge(transaction);
            return transaction;
        }
        return null;
    }

    @Override
    public Transaction saveWithRollback(Transaction transaction) {
        if(transaction.getIdTransaction() == null){
            if(!ctx.getRollbackOnly()){
                ctx.setRollbackOnly();
            }
            em.persist(transaction);
            return transaction;
        }
        else if (transaction.getIdTransaction()>0){
            if(!ctx.getRollbackOnly()){
                ctx.setRollbackOnly();
            }
            em.merge(transaction);
            return transaction;
        }
        return null;
    }

    @Override
    public void delete(Transaction transaction) {
        Transaction mergedTransaction = em.merge(transaction);
        em.remove(transaction);
    }


}
