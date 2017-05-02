package com.cashtransfer2.service.Impl;

import com.cashtransfer2.entity.BankAccount;
import com.cashtransfer2.entity.Transaction;
import com.cashtransfer2.exception.TransactionException;
import com.cashtransfer2.service.TransactionService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by VP on 02.05.2017.
 */

@Stateless
public class TransactionServiceImpl implements TransactionService {

    @PersistenceContext(unitName = "XA-TRANSACTION2")
    EntityManager em;
    @Resource
    SessionContext ctx;

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

    /*
9.Провести эксперимент: закончить транзакцию откатом в сессионном фасаде для
второй базы данных, должно выполняться вне контекста транзакции, продемонстрировать
результат эксперимента, и убедиться, что обновления отменены.
     */
    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public void delete(Transaction transaction) {
        em.merge(transaction);
        if(transaction != null){
            ctx.setRollbackOnly();
        }
        em.remove(transaction);
    }

    @Override
    public Transaction cashTransfer(Transaction transaction) {
        BankAccount sender = transaction.getSenderAcc();
        BankAccount receiver = transaction.getReciverAcc();

        double newSenderSum = sender.getSum() - transaction.getSumTransaction();
        double newReceiverSum = receiver.getSum() + transaction.getSumTransaction();
        sender.setSum(newSenderSum);
        receiver.setSum(newReceiverSum);
        em.merge(sender);
        em.merge(receiver);

        transaction = em.merge(transaction);
        return transaction;
    }

    /*
8. Провести эксперимент: закончить транзакцию откатом, выбросив системное
исключение EJBException в сессионном фасаде для второй базы данных после
обновления источника данных, и убедиться, что обновления отменены.
     */
    @Override
    public Transaction cashTransferWithException(Transaction transaction) {
        BankAccount sender = transaction.getSenderAcc();
        BankAccount receiver = transaction.getReciverAcc();

        double newSenderSum = sender.getSum() - transaction.getSumTransaction();
        double newReceiverSum = receiver.getSum() + transaction.getSumTransaction();
        sender.setSum(newSenderSum);
        receiver.setSum(newReceiverSum);
        em.merge(sender);
        em.merge(receiver);

        transaction = em.merge(transaction);
        if(!ctx.getRollbackOnly()){
            throw new TransactionException("Изменения не должны вступить в силу");
        }

        return transaction;
    }


}
