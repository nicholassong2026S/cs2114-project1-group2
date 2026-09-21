package p1;

// -------------------------------------------------------------------------
/**
 * Checks inputs and catches exceptions in order to protect the data from
 * invalid values.
 * 
 * @author Luke Stabler
 * @version Sep 14, 2026
 */
public class InputValidator
{

    // ----------------------------------------------------------
    /**
     * Checks input to make sure its a valid input (Doubles)
     * 
     * @param input
     * @return parsed double
     * @throws EmptyInputException
     * @throws NonNumericInputException
     * @throws InvalidAmountException
     */
    static double validateAmount(String input)
        throws EmptyInputException,
        NonNumericInputException,
        InvalidAmountException
    {
        if (input.isEmpty())
        {
            throw new EmptyInputException("Input cannot be empty.");
        }

        double amount;
        try
        {
            amount = Double.parseDouble(input);
        }
        catch (NumberFormatException e)
        {
            throw new NonNumericInputException(
                "Input must be a numeric value.");
        }

        // Double.parseDouble accepts "NaN" and "Infinity", which are not real
        // dollar amounts (and NaN slips past the <= 0 check below).
        if (Double.isNaN(amount) || Double.isInfinite(amount))
        {
            throw new NonNumericInputException(
                "Input must be a numeric value.");
        }

        if (amount <= 0)
        {
            throw new InvalidAmountException(
                "Amount must be greater than zero.");
        }

        return amount;
    }


    // ----------------------------------------------------------
    /**
     * Checks input to make sure its a valid input (int)
     * 
     * @param input
     * @return parsed int
     * @throws EmptyInputException
     * @throws NonNumericInputException
     * @throws InvalidAmountException
     */
    static int validateWholeNumber(String input)
        throws EmptyInputException,
        NonNumericInputException,
        InvalidAmountException
    {
        if (input.isEmpty())
        {
            throw new EmptyInputException("Input cannot be empty.");
        }

        int number;
        try
        {
            number = Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            throw new NonNumericInputException("Input must be a whole number.");
        }

        if (number < 0)
        {
            throw new InvalidAmountException("Number must be non-negative.");
        }

        return number;
    }

}
