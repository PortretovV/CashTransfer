package com.cashtransfer1.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by VP on 02.05.2017.
 */
@Entity
@Table(name = "credit_card", schema = "ejb3-transaction1")
@NamedQueries({
        @NamedQuery(name = "CreditCard.findAll", query = "select c from CreditCard c"),
        @NamedQuery(name = "CreditCard.findByCardNumber", query = "select c from CreditCard c where c.cardNumber=:cardNumber")
})
public class CreditCard {
    private String cardNumber;
    private String userInfo;
    private String securityCode;
    private Date endDate;
    private Double deposit;
    private Collection<Transaction> transactions;
    private Collection<Transaction> transactions1;

    @Id
    @Column(name = "cardNumber")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Basic
    @Column(name = "userInfo")
    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    @Basic
    @Column(name = "securityCode")
    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Basic
    @Column(name = "endDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "deposit")
    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        CreditCard that = (CreditCard) o;
//
//        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
//        if (userInfo != null ? !userInfo.equals(that.userInfo) : that.userInfo != null) return false;
//        if (securityCode != null ? !securityCode.equals(that.securityCode) : that.securityCode != null) return false;
//        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
//        if (deposit != null ? !deposit.equals(that.deposit) : that.deposit != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = cardNumber != null ? cardNumber.hashCode() : 0;
//        result = 31 * result + (userInfo != null ? userInfo.hashCode() : 0);
//        result = 31 * result + (securityCode != null ? securityCode.hashCode() : 0);
//        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
//        result = 31 * result + (deposit != null ? deposit.hashCode() : 0);
//        return result;
//    }

    @OneToMany(mappedBy = "senderCard", fetch = FetchType.EAGER)
    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<Transaction> transactionsByCardNumber) {
        this.transactions = transactionsByCardNumber;
    }

    @OneToMany(mappedBy = "reciverCard", fetch = FetchType.EAGER)
    public Collection<Transaction> getTransactions1() {
        return transactions1;
    }

    public void setTransactions1(Collection<Transaction> transactionsByCardNumber_0) {
        this.transactions1 = transactionsByCardNumber_0;
    }
}
