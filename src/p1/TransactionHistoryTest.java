package p1;

import student.TestCase;

import java.util.List;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
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
     * 
     */
    public void setUp() {
        history = new TransactionHistory();
        t1 = new Transaction("DEPOSIT", 100.0, 100.0);
        t2 = new Transaction("WITHDRAW", 20.0, 80.0);
        t3 = new Transaction("DEPOSIT", 50.0, 130.0);
    }


    // ----------------------------------------------------------
    /**
     * 
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
     * 
     */
    public void testEmptyHistory() {
        List<Transaction> list = history.getAllTransactions();
        assertNotNull("List should be empty, not null", list);
        assertTrue(list.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * 
     */
    public void testAddNullTransaction() {
        Exception thrown = null;
        try {
            history.addTransaction(null);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(
            "Expected an IllegalArgumentException for null transaction",
            thrown);
        assertTrue(thrown.getMessage().contains("null"));
    }
}
