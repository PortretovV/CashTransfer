import com.cashtransfer1.entity.CreditCard;
import com.cashtransfer1.entity.Transaction;
import com.cashtransfer1.service.CreditCardService;
import com.cashtransfer1.service.TransactionService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by VP on 02.05.2017.
 */

@ManagedBean(name = "cardTransaction")
@SessionScoped
public class CardTransactionBean {

    @EJB
    CreditCardService cardService;
    @EJB
    TransactionService transactionService;

    private List<CreditCard> creditCardList;
    private CreditCard creditCard;
    private Transaction transaction;
    private String recieveNumber;

    public List<CreditCard> getCreditCardList() {
        creditCardList = cardService.findAll();
        return creditCardList;
    }


    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
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
        transactionService.deleteWithEJBException(transaction);
        return "cardTransaction";
    }

    public String createTransaction(CreditCard card){
        this.recieveNumber = "";
        this.creditCard = card;
        this.transaction = new Transaction();
        transaction.setSenderCard(card);
        return "createCardTransaction";
    }

    public String saveTransaction(){
        if(recieveNumber != null){
            CreditCard creditCard = cardService.findByCardNumber(recieveNumber);
            this.transaction.setReciverCard(creditCard);
            transactionService.cashTransfer(transaction);
        }
        return "cardTransaction";
    }

    public String saveTransactionWithRollback(){
        if(recieveNumber != null){
            CreditCard creditCard = cardService.findByCardNumber(recieveNumber);
            this.transaction.setReciverCard(creditCard);
            transactionService.cashTransferWithRollback(transaction);
        }
        return "cardTransaction";
    }



}
