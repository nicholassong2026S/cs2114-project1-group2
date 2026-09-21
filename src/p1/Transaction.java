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
     * Create a new Transaction object.
     * 
     * @param type "DEPOSIT" or "WITHDRAW" (case-insensitive)
     * @param amount the amount moved (must not be negative)
     * @param resultingBalance the account balance after the transaction
     */
    public Transaction(String type, double amount, double resultingBalance) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                "Transaction amount cannot be negative.");
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
     * Gets the transaction type.
     * 
     * @return "DEPOSIT" or "WITHDRAW"
     */
    public String getType() {
        return type;
    }


    // ----------------------------------------------------------
    /**
     * Gets the amount moved in this transaction.
     * 
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }


    // ----------------------------------------------------------
    /**
     * Gets the account balance after this transaction.
     * 
     * @return the resulting balance
     */
    public double getResultingBalance() {
        return resultingBalance;
    }


    // ----------------------------------------------------------
    /**
     * Gets the time this transaction was created.
     * 
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    /**
     * Formats the transaction as one line: timestamp, type, amount, and
     * resulting balance.
     * 
     * @return the formatted transaction
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s: $%.2f | Balance: $%.2f", timestamp
            .format(formatter), type, amount, resultingBalance);
    }
}
