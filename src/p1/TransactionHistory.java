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

    /**
     * Create a new, empty TransactionHistory.
     */
    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Adds a transaction to the end of the history.
     * 
     * @param t the transaction to add
     * @throws IllegalArgumentException if t is null
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
     * Returns a copy of the history in chronological order.
     * 
     * @return a new list of all transactions
     */
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }


    // ----------------------------------------------------------
    /**
     * Prints every transaction, one per line, or a message if there are none.
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
