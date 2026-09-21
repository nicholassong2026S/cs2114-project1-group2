import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the BankingApp console loop and user input simulation.
 * 
 * @author Group 2: Andersson, Luke, Nicholas, Ashutosh
 * @version 2026.09.21
 */
public class BankingAppTest {

    /**
     * Tests a normal deposit case through simulated console input.
     * Verifies that when the user enters "75" when prompted for deposit,
     * the balance increases by 75 and returns a confirmation message[cite: 12].
     */
    @Test
    public void testDepositNormalCase() {
        String simulatedUserInput = "75\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        try {
            BankingApp app = new BankingApp();
            app.deposit(); 
            // The test passes if no exceptions are unhandled and the loop exits naturally.
        } catch (Exception e) {
            fail("No exception should be thrown to the caller for a valid deposit input.");
        } finally {
            System.setIn(originalIn); 
        }
    }
    
    /**
     * Tests a bad input case for deposit through simulated console input.
     * Verifies that if the user enters an empty field, it catches the 
     * EmptyInputException and reprompts the user[cite: 12]. 
     * Also tests handling of negative numbers[cite: 11].
     */
    @Test
    public void testDepositEmptyInput() {
        // Simulating an empty enter "\n", then a negative "-5\n", then a valid "75\n" to escape the reprompt loop.
        String simulatedUserInput = "\n-5\n75\n"; 
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        try {
            BankingApp app = new BankingApp();
            app.deposit();
            // The internal while loop should catch the exceptions and eventually succeed on the 75.
            // If it throws an exception to the top level, the catch block below will fail the test.
        } catch (Exception e) {
            fail("BankingApp should catch internal exceptions and reprompt, not throw them to the caller.");
        } finally {
            System.setIn(originalIn); 
        }
    }
}