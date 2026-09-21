package p1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

// -------------------------------------------------------------------------
/**
 * Tests the TransactionHistory class.
 * 
 * @author galve
 * @version Sep 14, 2026
 */
public class TransactionHistoryTest {
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
    @Before
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
    @Test
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
    @Test
    public void testEmptyHistory() {
        List<Transaction> list = history.getAllTransactions();
        assertNotNull("List should be empty, not null", list);
        assertTrue(list.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * 
     */
    @Test
    public void testAddNullTransaction() {
        try {
            history.addTransaction(null);
            fail(
                "Expected an IllegalArgumentException to be thrown for "
                + "null transactions.");
        }
        catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("null"));
        }
    }
}
