package p1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Tests the Transaction class.
 * 
 * @author galve
 * @version Sep 14, 2026
 */
public class TransactionTest {
    // ~ Fields ................................................................

    private Transaction validDeposit;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * 
     */
    @Before
    public void setUp() {
        validDeposit = new Transaction("DEPOSIT", 50.0, 150.0);
    }


    // ----------------------------------------------------------
    /**
     * Tests that a valid transaction stores its fields.
     */
    @Test
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
    @Test
    public void testValidTransaction() {
        assertEquals("DEPOSIT", validDeposit.getType());
        assertEquals(50.0, validDeposit.getAmount(), 0.01);
        assertEquals(150.0, validDeposit.getResultingBalance(), 0.01);
        assertNotNull(validDeposit.getTimestamp());
    }


    /**
     * Tests the bad input case of providing a negative transaction amount.
     */
    @Test
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
    @Test
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
