package com.cashtransfer2.service.Impl;

import com.cashtransfer2.entity.BankAccount;
import com.cashtransfer2.service.BankAccountService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by VP on 02.05.2017.
 */

@Stateless
public class BankAccountServiceImpl implements BankAccountService {
    @PersistenceContext(unitName = "XA-TRANSACTION2")
    EntityManager em;

    @Override
    public BankAccount save(BankAccount account) {
        if(account.getAccountNumber() == null){
            em.persist(account);
            return account;
        }
        account = em.merge(account);
        return account;
    }

    @Override
    public void delete(BankAccount account) {
        account = em.merge(account);
        em.remove(account);
    }

    @Override
    public List<BankAccount> findAll() {
        List<BankAccount> bankAccounts = em.createNamedQuery("BankAccount.findAll",BankAccount.class).getResultList();
        return bankAccounts;
    }

    @Override
    public BankAccount findByAccountNumber(String number) {
        TypedQuery<BankAccount> query = em.createNamedQuery("BankAccount.findByNumber",BankAccount.class);
        return  query.setParameter("accountNumber",number).getSingleResult();
    }
}
