package p1;

import java.util.ArrayList;
import java.util.List;

// -------------------------------------------------------------------------
/**
 * Stores and manages a chronological list of transaction records.
 * This class provides methods to add new transactions to the history
 * and retrieve them safely for display.
 * 
 * @author galve
 * @version Sep 20, 2026
 */
public class TransactionHistory {
    // ~ Fields ................................................................

    private List<Transaction> transactions;

    // ~ Constructors ..........................................................

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param t
     */
    public void addTransaction(Transaction t) {
        if (t == null) {
            throw new IllegalArgumentException(
                "Cannot add a null transaction.");
        }
        transactions.add(t);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void printHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        }
        else {
            for (Transaction t : transactions) {
                System.out.println(t.toString());
            }
        }
    }
}
