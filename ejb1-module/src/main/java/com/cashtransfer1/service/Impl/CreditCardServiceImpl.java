package com.cashtransfer1.service.Impl;

import com.cashtransfer1.entity.CreditCard;
import com.cashtransfer1.service.CreditCardService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by VP on 02.05.2017.
 */
@Stateless
public class CreditCardServiceImpl implements CreditCardService {

    @PersistenceContext(unitName = "TRANSACTION1")
    EntityManager em;

    @Override
    public CreditCard save(CreditCard creditCard) {
        if(creditCard.getCardNumber() == null){
            em.persist(creditCard);
            return creditCard;
        }
        creditCard = em.merge(creditCard);
        return creditCard;
    }

    @Override
    public void delete(CreditCard creditCard) {
        creditCard = em.merge(creditCard);
        em.remove(creditCard);
    }

    @Override
    public List<CreditCard> findAll() {
        List<CreditCard> cards = em.createNamedQuery("CreditCard.findAll",CreditCard.class).getResultList();
        return cards;
    }
}
