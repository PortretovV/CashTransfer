package com.cashtransfer2.entity;

import javax.persistence.*;

/**
 * Created by VP on 02.05.2017.
 */
@Entity
public class Transaction {
    private Integer idTransaction;
    private Double sumTransaction;
    private BankAccount senderAcc;
    private BankAccount reciverAcc;

    @Id
    @Column(name = "idTransaction")
    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    @Basic
    @Column(name = "sumTransaction")
    public Double getSumTransaction() {
        return sumTransaction;
    }

    public void setSumTransaction(Double sumTransaction) {
        this.sumTransaction = sumTransaction;
    }

    @ManyToOne
    @JoinColumn(name = "senderAcc", referencedColumnName = "accountNumber", nullable = false)
    public BankAccount getSenderAcc() {
        return senderAcc;
    }

    public void setSenderAcc(BankAccount bankAccountBySenderAcc) {
        this.senderAcc = bankAccountBySenderAcc;
    }

    @ManyToOne
    @JoinColumn(name = "reciverAcc", referencedColumnName = "accountNumber", nullable = false)
    public BankAccount getReciverAcc() {
        return reciverAcc;
    }

    public void setReciverAcc(BankAccount bankAccountByReciverAcc) {
        this.reciverAcc = bankAccountByReciverAcc;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (idTransaction != that.idTransaction) return false;
        if (sumTransaction != null ? !sumTransaction.equals(that.sumTransaction) : that.sumTransaction != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransaction;
        result = 31 * result + (sumTransaction != null ? sumTransaction.hashCode() : 0);
        return result;
    }

}
