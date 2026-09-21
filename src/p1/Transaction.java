package p1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// -------------------------------------------------------------------------
/**
 * A record of a deposit or withdrawal transaction.
 * This class captures the type of transaction, the amount moved, the
 * resulting account balance, and the exact time it occurred.
 * 
 * @author galve
 * @version Sep 14, 2026
 */
public class Transaction {
    // ~ Fields ................................................................
    private String type;
    private double amount;
    private double resultingBalance;
    private LocalDateTime timestamp;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Transaction object with the specified type, amount,
     * and resulting account balance. The timestamp is set to the current
     * date and time.
     * 
     * @param type
     *            the type of transaction, either "DEPOSIT" or "WITHDRAW"
     * @param amount
     *            the amount of money involved in the transaction
     * @param resultingBalance
     *            the account balance after the transaction
     * @throws IllegalArgumentException
     *             if the amount is negative or the
     *             transaction type is invalidf
     */
    public Transaction(String type, double amount, double resultingBalance) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                "Transaction amount connot be negative.");
        }
        if (type == null || (!type.equalsIgnoreCase("DEPOSIT") && !type
            .equalsIgnoreCase("WITHDRAW"))) {
            throw new IllegalArgumentException(
                "Type must be DEPOSIT or WITHDRAW.");
        }
        this.type = type.toUpperCase();
        this.amount = amount;
        this.resultingBalance = resultingBalance;
        this.timestamp = LocalDateTime.now();
    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Returns the type of this transaction. *
     * 
     * @return the transaction type, either "DEPOSIT" or "WITHDRAW"
     */
    public String getType() {
        return type;
    }


    // ----------------------------------------------------------
    /**
     * Returns the amount involved in this transaction. *
     * 
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }


    // ----------------------------------------------------------
    /**
     * Returns the account balance after this transaction was completed.
     * 
     * @return the resulting account balance
     */
    public double getResultingBalance() {
        return resultingBalance;
    }


    // ----------------------------------------------------------
    /**
     * Returns the date and time when this transaction occurred.
     * 
     * @return the transaction timestamp.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    /**
     * Returns a formatted description of this transaction.
     * 
     * @return a string containing the timestamp, transaction type,
     *         amount, and resulting balance.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s: $%.2f | Balance: $%.2f", timestamp
            .format(formatter), type, amount, resultingBalance);
    }
}
