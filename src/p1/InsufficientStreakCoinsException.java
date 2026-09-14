package p1;

// -------------------------------------------------------------------------
/**
 * Creates an InsufficientStreakCoinsException for when a user tries to redeem
 * more StreakCoins than they have
 * 
 * @author Luke Stabler
 * @version Sep 14, 2026
 */
public class InsufficientStreakCoinsException
    extends Exception
{
    // ----------------------------------------------------------
    /**
     * Create a new InsufficientStreakCoinsException object.
     * 
     * @param message
     */
    public InsufficientStreakCoinsException(String message)
    {
        super(message);
    }
}
