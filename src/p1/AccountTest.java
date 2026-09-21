package p1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Account methods to ensure proper balance tracking, 
 * streak logic, and interest calculations.
 * 
 * @author Group 2: Andersson, Luke, Nicholas, Ashutosh
 * @version 2026.09.21
 */
public class AccountTest {

    private Account account;

    /**
     * Sets up a fresh account before each test.
     */
    @BeforeEach
    public void setUp() {
        account = new Account();
    }

    /**
     * Tests a normal deposit case.
     * Verifies that deposit(50.0) on a new account makes the balance 50.0 
     * and the streak becomes 1[cite: 11].
     */
    @Test
    public void testDepositNormalCase() throws Exception {
        account.deposit(50.0);
        assertEquals(50.0, account.getBalance());
        assertEquals(1, account.getStreak());
    }

    /**
     * Tests a bad input deposit case using a try-catch block.
     * Verifies that deposit(-5.0) throws InvalidAmountException and 
     * the balance remains unchanged[cite: 11].
     */
    @Test
    public void testDepositBadInput() {
        try {
            account.deposit(-5.0);
            fail("Expected InvalidAmountException to be thrown for negative deposit.");
        } catch (InvalidAmountException e) {
            // Test passes if this exception is caught
        }
        
        // Verifies the balance remains unchanged[cite: 11]
        assertEquals(0.0, account.getBalance());
    }

    /**
     * Tests a normal withdrawal case.
     * Verifies that withdraw(30.0) on a balance of 150.0 makes the balance 120.0 
     * and streak resets to 0[cite: 11].
     */
    @Test
    public void testWithdrawNormalCase() throws Exception {
        Account loadedAccount = new Account(150.0, 5); 
        loadedAccount.withdraw(30.0);
        
        assertEquals(120.0, loadedAccount.getBalance());
        assertEquals(0, loadedAccount.getStreak());
    }

    /**
     * Tests a bad input withdrawal case for insufficient funds using a try-catch block.
     * Verifies that withdraw(500.0) on a balance of 400.0 throws 
     * InsufficientFundsException and the balance remains unchanged[cite: 11].
     */
    @Test
    public void testWithdrawBadInput() throws InvalidAmountException {
        Account loadedAccount = new Account(400.0, 2);
        
        try {
            loadedAccount.withdraw(500.0);
            fail("Expected InsufficientFundsException to be thrown for overdrawing.");
        } catch (InsufficientFundsException e) {
            // Test passes if this exception is caught
        }
        
        // Verifies the balance remains unchanged[cite: 11]
        assertEquals(400.0, loadedAccount.getBalance());
    }

    /**
     * Tests getting the balance.
     * Verifies that called after deposit(20.0) on a new account returns 20.0, 
     * and called on a new Account returns 0.0 instead of null[cite: 11].
     */
    @Test
    public void testGetBalanceNormalAndBad() throws Exception {
        account.deposit(20.0);
        assertEquals(20.0, account.getBalance());
        
        Account newAccount = new Account();
        assertEquals(0.0, newAccount.getBalance());
    }

    /**
     * Tests getting the streak count.
     * Verifies two deposits in a row returns 2, and one deposit then 
     * one withdrawal returns 0[cite: 11].
     */
    @Test
    public void testGetStreak() throws Exception {
        account.deposit(10.0);
        account.deposit(10.0);
        assertEquals(2, account.getStreak());
        
        account.withdraw(5.0);
        assertEquals(0, account.getStreak());
    }
}