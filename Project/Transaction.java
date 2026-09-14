import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
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
     * @param type
     * @param amount
     * @param resultingBalance
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
     * Place a description of your method here.
     * 
     * @return
     */
    public String getType() {
        return type;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public double getAmount() {
        return amount;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public double getResultingBalance() {
        return resultingBalance;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s: $%.2f | Balance: $%.2f", timestamp.format(formatter),type, resultingBalance);
    }
}
