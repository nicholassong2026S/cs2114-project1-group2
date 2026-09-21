package p1;

import static org.junit.Assert.*;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Tests the inputValidator
 * 
 * @author Luke Stabler
 * @version Sep 21, 2026
 */
public class InputValidatorTest
{

    // ----------------------------------------------------------
    /**
     * Tests ValidateAmount method in the InputValidator.
     * 
     * @throws Exception
     */
    @Test
    public void testValidateAmount()
        throws Exception
    {
        try
        {
            InputValidator.validateAmount("");
            fail("Expected EmptyInputException for empty string");
        }
        catch (EmptyInputException e)
        {
            assertEquals("Input cannot be empty.", e.getMessage());
        }

        try
        {
            InputValidator.validateAmount("abc");
            fail("Expected NonNumericInputException for abc");
        }
        catch (NonNumericInputException e)
        {
            assertEquals("Input must be a numeric value.", e.getMessage());
        }

        try
        {
            InputValidator.validateAmount("12abc");
            fail("Expected NonNumericInputException for 12abc");
        }
        catch (NonNumericInputException e)
        {
            assertEquals("Input must be a numeric value.", e.getMessage());
        }

        try
        {
            InputValidator.validateAmount("$50");
            fail("Expected NonNumericInputException for $50");
        }
        catch (NonNumericInputException e)
        {
            assertEquals("Input must be a numeric value.", e.getMessage());
        }

        try
        {
            InputValidator.validateAmount("   ");
            fail("Expected NonNumericInputException for spaces only");
        }
        catch (NonNumericInputException e)
        {
            assertEquals("Input must be a numeric value.", e.getMessage());
        }

        try
        {
            InputValidator.validateAmount("0");
            fail("Expected InvalidAmountException for 0");
        }
        catch (InvalidAmountException e)
        {
            assertEquals("Amount must be greater than zero.", e.getMessage());
        }

        try
        {
            InputValidator.validateAmount("0.0");
            fail("Expected InvalidAmountException for 0.0");
        }
        catch (InvalidAmountException e)
        {
            assertEquals("Amount must be greater than zero.", e.getMessage());
        }

        try
        {
            InputValidator.validateAmount("-5");
            fail("Expected InvalidAmountException for -5");
        }
        catch (InvalidAmountException e)
        {
            assertEquals("Amount must be greater than zero.", e.getMessage());
        }

        try
        {
            InputValidator.validateAmount("-0.01");
            fail("Expected InvalidAmountException for -0.01");
        }
        catch (InvalidAmountException e)
        {
            assertEquals("Amount must be greater than zero.", e.getMessage());
        }

        assertEquals(100.0, InputValidator.validateAmount("100"), 0.001);
        assertEquals(25.50, InputValidator.validateAmount("25.50"), 0.001);
        assertEquals(0.01, InputValidator.validateAmount("0.01"), 0.0001);
        assertEquals(25.0, InputValidator.validateAmount(" 25 "), 0.001);
    }


    // ----------------------------------------------------------
    /**
     * Tests validateWholeNumber method in the InputValidator.
     * 
     * @throws Exception
     */
    @Test
    public void testValidateWholeNumber()
        throws Exception
    {
        try
        {
            InputValidator.validateWholeNumber("");
            fail("Expected EmptyInputException for empty string");
        }
        catch (EmptyInputException e)
        {
            assertEquals("Input cannot be empty.", e.getMessage());
        }

        try
        {
            InputValidator.validateWholeNumber("abc");
            fail("Expected NonNumericInputException for abc");
        }
        catch (NonNumericInputException e)
        {
            assertEquals("Input must be a whole number.", e.getMessage());
        }

        try
        {
            InputValidator.validateWholeNumber("3.5");
            fail("Expected NonNumericInputException for decimal");
        }
        catch (NonNumericInputException e)
        {
            assertEquals("Input must be a whole number.", e.getMessage());
        }

        try
        {
            InputValidator.validateWholeNumber("99999999999");
            fail("Expected NonNumericInputException for int overflow");
        }
        catch (NonNumericInputException e)
        {
            assertEquals("Input must be a whole number.", e.getMessage());
        }

        try
        {
            InputValidator.validateWholeNumber(" 5");
            fail("Expected NonNumericInputException for leading space");
        }
        catch (NonNumericInputException e)
        {
            assertEquals("Input must be a whole number.", e.getMessage());
        }

        try
        {
            InputValidator.validateWholeNumber("-1");
            fail("Expected InvalidAmountException for -1");
        }
        catch (InvalidAmountException e)
        {
            assertEquals("Number must be non-negative.", e.getMessage());
        }

        try
        {
            InputValidator.validateWholeNumber("-100");
            fail("Expected InvalidAmountException for -100");
        }
        catch (InvalidAmountException e)
        {
            assertEquals("Number must be non-negative.", e.getMessage());
        }

        assertEquals(0, InputValidator.validateWholeNumber("0"));
        assertEquals(42, InputValidator.validateWholeNumber("42"));
        assertEquals(
            Integer.MAX_VALUE,
            InputValidator.validateWholeNumber("2147483647"));
    }

}
