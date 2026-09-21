package p1;

// -------------------------------------------------------------------------
/**
 * Creates a NonNumericInputException for when a user inputs a non-numeric
 * character like a letter or symbol.
 * 
 * @author Luke Stabler
 * @version Sep 14, 2026
 */
public class NonNumericInputException
    extends Exception
{
    // ----------------------------------------------------------
    /**
     * Create a new NonNumericInputException object.
     * 
     * @param message
     */
    public NonNumericInputException(String message)
    {
        super(message);
    }
}
