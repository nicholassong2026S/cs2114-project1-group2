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
<<<<<<< HEAD
    /**
     * Create a new, empty TransactionHistory object.
     */
     */
=======

>>>>>>> parent of c60b1df (Overhaul)
    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
<<<<<<< HEAD
    /**
     * Adds a transaction to the end of the history.
     *
     * @param t
     *            the transaction to add
     * @throws IllegalArgumentException
     *             if the transaction is null.
     */
=======
     * Place a description of your method here.
     * 
     * @param t
>>>>>>> parent of c60b1df (Overhaul)
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
<<<<<<< HEAD
    /**
     * Returns a copy of the history in chronological order.
     *
     * @return a list containing all recorded transactions.
     */
=======
     * Place a description of your method here.
     * 
     * @return
>>>>>>> parent of c60b1df (Overhaul)
     */
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }


    // ----------------------------------------------------------
    /**
<<<<<<< HEAD
    /**
     * Prints every transaction, one per line, or a message if there are none.
=======
     * Place a description of your method here.
>>>>>>> parent of c60b1df (Overhaul)
     */
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
