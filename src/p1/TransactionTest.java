package p1;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 * 
 * @author galve
 * @version Sep 14, 2026
 */
public class TransactionTest extends TestCase {
    // ~ Fields ................................................................

    private Transaction depositTx;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * 
     */
    public void setUp() {
        depositTx = new Transaction("DEPOSIT", 50.0, 150.0);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testValidTransactionCreation() {
        assertEquals("DEPOSIT", depositTx.getType());
        assertEquals(50.0, depositTx.getAmount(), 0.001);
        assertEquals(150.0, depositTx.getResultingBalance(), 0.001);
        assertNotNull(depositTx.getTimestamp());
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testNegativeAmountTransaction() {
        Exception thrown = null;
        try {
            new Transaction("WITHDRAW", -50.0, 150.0);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(
            "Expected an IllegalArgumentException for negative amount", thrown);
        assertTrue(thrown.getMessage().contains("negative"));
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testInvalidTypeTransaction() {
        Exception thrown = null;
        try {
            new Transaction("TRANSFER", 50.0, 150.0);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull("Expected an IllegalArgumentException for invalid type",
            thrown);
        assertTrue(thrown.getMessage().contains("DEPOSIT or WITHDRAW"));
    }
}
