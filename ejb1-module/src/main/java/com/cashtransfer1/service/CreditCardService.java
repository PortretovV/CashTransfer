package com.cashtransfer1.service;

import com.cashtransfer1.entity.CreditCard;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by VP on 02.05.2017.
 */

@Local
public interface CreditCardService {
    CreditCard save(CreditCard creditCard);
    void delete(CreditCard creditCard);
    List<CreditCard> findAll();
}
