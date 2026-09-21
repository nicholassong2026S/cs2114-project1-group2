package p1;
// -------------------------------------------------------------------------
/**
 * Creates an InsufficientFundsException for when a user tries to withdraw money
 * they do not have.
 * 
 * @author Luke Stabler
 * @version Sep 14, 2026
 */
public class InsufficientFundsException
    extends Exception
{
    // ----------------------------------------------------------
    /**
     * Create a new InsufficientFundsException object.
     * 
     * @param message
     */
    public InsufficientFundsException(String message)
    {
        super(message);
    }
}
