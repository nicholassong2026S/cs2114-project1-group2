import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
    }

    @Test
    public void testDepositNormalCase() throws Exception {
        // deposit(50.0) on a new account. Balance becomes 50.0 and streak becomes 1[cite: 11].
        account.deposit(50.0);
        assertEquals(50.0, account.getBalance());
        assertEquals(1, account.getStreak());
    }

    @Test
    public void testDepositBadInput() {
        // deposit(-5.0). Throws InvalidAmountException. Balance remains unchanged[cite: 11].
        assertThrows(InvalidAmountException.class, () -> {
            account.deposit(-5.0);
        });
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testWithdrawNormalCase() throws Exception {
        // withdraw(30.0) on a balance of 150.0. Balance becomes 120.0 and streak resets to 0[cite: 11].
        Account loadedAccount = new Account(150.0, 5); 
        loadedAccount.withdraw(30.0);
        
        assertEquals(120.0, loadedAccount.getBalance());
        assertEquals(0, loadedAccount.getStreak());
    }

    @Test
    public void testWithdrawBadInput() {
        // withdraw(500.0) on a balance of 400.0. Throws InsufficientFundsException. Balance remains unchanged[cite: 11].
        Account loadedAccount = new Account(400.0, 2);
        
        assertThrows(InsufficientFundsException.class, () -> {
            loadedAccount.withdraw(500.0);
        });
        assertEquals(400.0, loadedAccount.getBalance());
    }

    @Test
    public void testGetBalanceNormalAndBad() throws Exception {
        // Called after deposit(20.0) on a new account. Returns 20.0[cite: 11].
        account.deposit(20.0);
        assertEquals(20.0, account.getBalance());
        
        // Called on a new Account. Returns 0.0 instead of null[cite: 11].
        Account newAccount = new Account();
        assertEquals(0.0, newAccount.getBalance());
    }

    @Test
    public void testGetStreak() throws Exception {
        // Two Deposits in a row. Returns 2[cite: 11].
        account.deposit(10.0);
        account.deposit(10.0);
        assertEquals(2, account.getStreak());
        
        // One deposit then one withdrawal returns 0[cite: 11].
        account.withdraw(5.0);
        assertEquals(0, account.getStreak());
    }

    @Test
    public void testApplyInterestBoost() {
        // Apply a 0.5% boost. getCurrentAnnualRate() returns baseRate + 0.005 until it expires.
        InterestBoost boost = new InterestBoost(0.005, Duration.ofDays(1)); // Duration needs to be defined based on implementation
        account.applyInterestBoost(boost);
        assertEquals(0.025, account.getCurrentAnnualRate()); // Assuming 0.02 base rate
        
        // Note: To test the expiration ("Call getCurrentAnnualRate() after the boost's duration has passed returns the baseRate only"), 
        // you would typically use a mock clock or temporal injection in JUnit[cite: 12].
    }

    @Test
    public void testPayInterest() {
        // One day at a 2% base rate on a $1000 balance increases the balance by $0.55[cite: 12].
        // Note: This requires injecting a simulated past date (e.g., 1 day ago) into the Account's lastInterestPaymentAt field.
        
        // Called twice, one after the other. Adds $0 because the elapsed time is too short[cite: 12].
        Account interestAccount = new Account(1000.0, 0);
        interestAccount.payInterest(); 
        double balanceAfterFirstCall = interestAccount.getBalance();
        
        interestAccount.payInterest();
        assertEquals(balanceAfterFirstCall, interestAccount.getBalance()); 
    }
}