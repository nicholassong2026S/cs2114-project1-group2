package p1;

// -------------------------------------------------------------------------
/**
 * Creates an EmptyInputException for when a user inputs an empty field instead
 * of a valid value.
 * 
 * @author Luke Stabler
 * @version Sep 14, 2026
 */
public class EmptyInputException
    extends Exception
{
    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------
    /**
     * Create a new EmptyInputException object.
     * 
     * @param message
     */
    public EmptyInputException(String message)
    {
        super(message);
    }
}
