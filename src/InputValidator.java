public class inputValidator {

    static double validateAmount(String input) throws EmptyInputException, NonNumericInputException, InvalidAmountException {
        if (input.isEmpty()) {
            throw new EmptyInputException("Input cannot be empty.");
        }

        double amount;
        try {
            amount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new NonNumericInputException("Input must be a numeric value.");
        }

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        return amount;
    }

    static int validateWholeNumber(String input) throws EmptyInputException, NonNumericInputException, InvalidAmountException {
        if (input.isEmpty()) {
            throw new EmptyInputException("Input cannot be empty.");
        }

        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NonNumericInputException("Input must be a whole number.");
        }

        if (number < 0) {
            throw new InvalidAmountException("Number must be non-negative.");
        }

        return number;
    }

}