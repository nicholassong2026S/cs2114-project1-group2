package p1;

import student.TestCase;

import java.util.List;

// -------------------------------------------------------------------------
/**
<<<<<<< HEAD
 * Tests the TransactionHistory class.
 * These tests verify that transactions can be added and retrieved,
 * an empty history behaves correctly, and null transactions are rejected.
=======
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
>>>>>>> parent of c60b1df (Overhaul)
 * 
 * @author galve
 * @version Sep 14, 2026
 */
public class TransactionHistoryTest extends TestCase {
    // ~ Fields ................................................................

    private TransactionHistory history;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new transaction history and sample transactions
     * used by the test methods.
     */
    public void setUp() {
        history = new TransactionHistory();
        t1 = new Transaction("DEPOSIT", 100.0, 100.0);
        t2 = new Transaction("WITHDRAW", 20.0, 80.0);
        t3 = new Transaction("DEPOSIT", 50.0, 130.0);
    }


    // ----------------------------------------------------------
    /**
     * Tests that transactions can be added to the history and
     * retrieved in the order they were added.
     */
    public void testAddAndRetrieveTransactions() {
        history.addTransaction(t1);
        history.addTransaction(t2);
        history.addTransaction(t3);

        List<Transaction> list = history.getAllTransactions();
        assertEquals(3, list.size());
        assertEquals("DEPOSIT", list.get(0).getType());
        assertEquals(100.0, list.get(0).getAmount(), 0.001);
        assertEquals("WITHDRAW", list.get(1).getType());
        assertEquals("DEPOSIT", list.get(2).getType());
    }


    // ----------------------------------------------------------
    /**
     * Tests that a newly created transaction history is empty
     * and returns a non-null list of transactions.
     */
    public void testEmptyHistory() {
        List<Transaction> list = history.getAllTransactions();
        assertNotNull("List should be empty, not null", list);
        assertTrue(list.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests that adding a null transaction throws an
     * IllegalArgumentException.
     */
    public void testAddNullTransaction() {
        try {
            history.addTransaction(null);
            fail("Expected an IllegalArgumentException to be thrown for "
                + "null transactions.");
        }
        catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("null"));
        }
    }
}
