package com.cashtransfer2.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by VP on 02.05.2017.
 */
@Entity
@Table(name = "bank_account", schema = "ejb3-transaction2")
@NamedQueries({
        @NamedQuery(name = "BankAccount.findAll", query = "select b from BankAccount b"),
        @NamedQuery(name = "BankAccount.findByNumber", query = "select b from BankAccount b where b.accountNumber = :accountNumber")
})
public class BankAccount {
    private String accountNumber;
    private String inn;
    private String bik;
    private String bankName;
    private String userInfo;
    private String userAddress;
    private Double sum;
    private Collection<Transaction> transactions1;
    private Collection<Transaction> transactions2;

    @Id
    @Column(name = "accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Basic
    @Column(name = "INN")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Basic
    @Column(name = "BIK")
    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    @Basic
    @Column(name = "bankName")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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
    @Column(name = "userAddress")
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "sum")
    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        BankAccount that = (BankAccount) o;
//
//        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null)
//            return false;
//        if (inn != null ? !inn.equals(that.inn) : that.inn != null) return false;
//        if (bik != null ? !bik.equals(that.bik) : that.bik != null) return false;
//        if (bankName != null ? !bankName.equals(that.bankName) : that.bankName != null) return false;
//        if (userInfo != null ? !userInfo.equals(that.userInfo) : that.userInfo != null) return false;
//        if (userAddress != null ? !userAddress.equals(that.userAddress) : that.userAddress != null) return false;
//        if (sum != null ? !sum.equals(that.sum) : that.sum != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = accountNumber != null ? accountNumber.hashCode() : 0;
//        result = 31 * result + (inn != null ? inn.hashCode() : 0);
//        result = 31 * result + (bik != null ? bik.hashCode() : 0);
//        result = 31 * result + (bankName != null ? bankName.hashCode() : 0);
//        result = 31 * result + (userInfo != null ? userInfo.hashCode() : 0);
//        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
//        result = 31 * result + (sum != null ? sum.hashCode() : 0);
//        return result;
//    }

    @OneToMany(mappedBy = "senderAcc",fetch = FetchType.EAGER)
    public Collection<Transaction> getTransactions1() {
        return transactions1;
    }

    public void setTransactions1(Collection<Transaction> transactionsByAccountNumber) {
        this.transactions1 = transactionsByAccountNumber;
    }

    @OneToMany(mappedBy = "reciverAcc",fetch = FetchType.EAGER)
    public Collection<Transaction> getTransactions2() {
        return transactions2;
    }

    public void setTransactions2(Collection<Transaction> transactionsByAccountNumber_0) {
        this.transactions2 = transactionsByAccountNumber_0;
    }
}
