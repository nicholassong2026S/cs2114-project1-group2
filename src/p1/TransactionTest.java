package p1;

import student.TestCase;

// -------------------------------------------------------------------------
/**
<<<<<<< HEAD
 * Tests the Transaction class.
 * This test class verifies that transactions are created correctly,
 * invalid transaction data is rejected, and transaction information
 * can be retrieved as expected.
=======
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
>>>>>>> parent of c60b1df (Overhaul)
 * 
 * @author galve
 * @version Sep 14, 2026
 */
public class TransactionTest extends TestCase {
    // ~ Fields ................................................................

    private Transaction validDeposit;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a valid deposit transaction for use in the test methods.
     */
    public void setUp() {
        validDeposit = new Transaction("DEPOSIT", 50.0, 150.0);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testValidTransactionCreation() {
        assertEquals("DEPOSIT", validDeposit.getType());
        assertEquals(50.0, validDeposit.getAmount(), 0.001);
        assertEquals(150.0, validDeposit.getResultingBalance(), 0.001);
        assertNotNull(validDeposit.getTimestamp());
    }


    // ----------------------------------------------------------
    /**
     * Tests the normal case of creating a valid transaction and
     * verifying the getter methods return expected values.
     */
    public void testValidTransaction() {
        assertEquals("DEPOSIT", validDeposit.getType());
        assertEquals(50.0, validDeposit.getAmount(), 0.01);
        assertEquals(150.0, validDeposit.getResultingBalance(), 0.01);
        assertNotNull(validDeposit.getTimestamp());
    }


    /**
     * Tests the bad input case of providing a negative transaction amount.
     */
    public void testNegativeAmountTransaction() {
        try {
            validDeposit = new Transaction("WITHDRAW", -50.0, 150.0);
            fail(
                "Expected an IllegalArgumentException to be thrown for "
                + "negative amounts.");
        }
        catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("negative"));
        }
    }


    /**
     * Tests providing an invalid string for the transaction type.
     */
    public void testInvalidTypeTransaction() {
        try {
            validDeposit = new Transaction("TRANSFER", 50.0, 150.0);
            fail(
                "Expected an IllegalArgumentException to be thrown for "
                + "invalid types.");
        }
        catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("DEPOSIT or WITHDRAW"));
        }
    }
}
