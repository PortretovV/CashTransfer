import com.cashtransfer2.entity.BankAccount;
import com.cashtransfer2.entity.Transaction;
import com.cashtransfer2.service.BankAccountService;
import com.cashtransfer2.service.TransactionService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by VP on 02.05.2017.
 */

@ManagedBean(name = "bankTransaction")
@SessionScoped
public class BankTransactionBean {
    @EJB
    private BankAccountService bankService;
    @EJB
    private TransactionService transactionService;

    private List<BankAccount> bankAccountList;
    private BankAccount bankAccount;
    private Transaction transaction;
    private String recieveNumber;


    public List<BankAccount> getBankAccountList() {
        bankAccountList = bankService.findAll();
        return bankAccountList;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getRecieveNumber() {
        return recieveNumber;
    }

    public void setRecieveNumber(String recieveNumber) {
        this.recieveNumber = recieveNumber;
    }


    public String deleteRollback(Transaction transaction){
        transactionService.deleteRollback(transaction);
        return "bankTransaction";
    }

    public String createTransaction(BankAccount bankAccount){
        this.recieveNumber = "";
        this.bankAccount = bankAccount;
        this.transaction = new Transaction();
        transaction.setSenderAcc(bankAccount);
        return "createBankTransaction";
    }

    public String saveTransaction(){
        if(recieveNumber != null){
            BankAccount bankAccount = bankService.findByAccountNumber(recieveNumber);
            this.transaction.setReciverAcc(bankAccount);
            transactionService.cashTransfer(transaction);
        }
        return "bankTransaction";
    }

    public String saveWithExceptionTransaction(){
        if(recieveNumber != null){
            BankAccount bankAccount = bankService.findByAccountNumber(recieveNumber);
            this.transaction.setReciverAcc(bankAccount);
            transactionService.cashTransferWithException(transaction);
        }
        return "bankTransaction";
    }



}
