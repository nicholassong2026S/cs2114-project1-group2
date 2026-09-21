package p1;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import org.junit.Test;

/**
 * Test class for the BankingApp console loop and user input simulation.
 * 
 * @author Group 2: Andersson, Luke, Nicholas, Ashutosh
 * @version 2026.09.21
 */
public class BankingAppTest {

    /**
     * Feeds the given text to a new BankingApp as console input, runs the given
     * action on it, and returns everything the app printed.
     * 
     * @param simulatedUserInput the lines the "user" types
     * @param action what to do with the app
     * @return the captured console output
     */
    private String runApp(String simulatedUserInput, Consumer<BankingApp> action) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        System.setOut(new PrintStream(captured));
        try {
            BankingApp app = new BankingApp(); // must be built after setIn
            action.accept(app);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        return captured.toString();
    }

    /**
     * Feeds the given text to a new BankingApp as console input, runs
     * deposit(), and returns everything the app printed.
     * 
     * @param simulatedUserInput the lines the "user" types
     * @return the captured console output
     */
    private String runDeposit(String simulatedUserInput) {
        return runApp(simulatedUserInput, BankingApp::deposit);
    }

    /**
     * Tests a normal deposit case through simulated console input.
     * Verifies that when the user enters "75" when prompted for deposit,
     * the balance increases by 75 and returns a confirmation message.
     */
    @Test
    public void testDepositNormalCase() {
        String output = runDeposit("75\n");
        
        assertTrue(output.contains("Deposited $75.00 successfully."));
        assertTrue(output.contains("New Balance: $75.00"));
        assertTrue(output.contains("Streak: 1"));
    }
    
    /**
     * Tests a bad input case for deposit through simulated console input.
     * Verifies that if the user enters an empty field, it catches the 
     * EmptyInputException and reprompts the user. 
     * Also tests handling of negative numbers.
     */
    @Test
    public void testDepositEmptyInput() {
        // An empty enter, then a negative, then a valid amount to escape the reprompt loop.
        String output = runDeposit("\n-5\n75\n"); 
        
        assertTrue(output.contains("Input cannot be empty. Please try again."));
        assertTrue(output.contains("Amount must be greater than zero. Please try again."));
        assertTrue(output.contains("Deposited $75.00 successfully."));
    }

    /**
     * Tests that a fresh app reports an empty transaction history.
     */
    @Test
    public void testTransactionHistoryEmpty() {
        String output = runApp("", BankingApp::viewTransactionHistory);
        
        assertTrue(output.contains("No transactions found."));
    }

    /**
     * Tests that deposits and withdrawals show up in the transaction history,
     * in order, with the resulting balance, and that a rejected withdrawal
     * is not recorded.
     */
    @Test
    public void testTransactionHistoryRecordsDepositsAndWithdrawals() {
        // deposit 75, try to withdraw 500 (rejected), then withdraw 25
        String output = runApp("75\n500\n25\n", app -> {
            app.deposit();
            app.withdraw();
            app.viewTransactionHistory();
        });
        
        String history = output.substring(output.indexOf("--- Transaction History ---"));
        int deposit = history.indexOf("DEPOSIT: $75.00 | Balance: $75.00");
        int withdraw = history.indexOf("WITHDRAW: $25.00 | Balance: $50.00");
        assertTrue(deposit >= 0);
        assertTrue(withdraw > deposit);
        assertFalse(history.contains("500.00"));
    }
}
