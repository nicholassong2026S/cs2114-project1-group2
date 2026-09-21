package p1;

// -------------------------------------------------------------------------
/**
 * Creates an InvalidAmountException for when a user inputs an invalid value
 * 
 * @author Luke Stabler
 * @version Sep 14, 2026
 */
public class InvalidAmountException
    extends Exception
{
    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------
    /**
     * Create a new InvalidAmountException object.
     * 
     * @param message
     */
    public InvalidAmountException(String message)
    {
        super(message);
    }
}
