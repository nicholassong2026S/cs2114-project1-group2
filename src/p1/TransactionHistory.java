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
    // ----------------------------------------------------------
    /**
     * Create a new TransactionHistory object.
     */
    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Adds a transaction to the transaction history.
     *
     * @param t
     *            the transaction to add
     * @throws IllegalArgumentException
     *             if the transaction is null.
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
     * Returns a copy of all transactions in the history.
     * 
     * @return a list containing all recorded transactions.
     */
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }


    // ----------------------------------------------------------
    /**
     * Prints all transactions in the history.
     * If the history is empty, a message is printed instead.
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
