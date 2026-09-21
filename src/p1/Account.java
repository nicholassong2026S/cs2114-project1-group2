import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Holds current balance, streak count, interest rate, and any active interest
 * boost[cite: 1].
 * 
 * @author Group 2: Andersson, Luke, Nicholas, Ashutosh[cite: 1]
 * @version 2026.09.21
 */
public class Account {
    private double balance;
    private int streakCount;
    private double baseInterestRate = 0.02; 
    private double activeBoostRate;
    private LocalDateTime boostExpiresAt;
    private LocalDateTime lastInterestPaymentAt;

    /**
     * Constructs a new account with a balance of 0.0 and a streak of 0[cite: 5].
     */
    public Account() {
        this.balance = 0.0;
        this.streakCount = 0;
        this.lastInterestPaymentAt = LocalDateTime.now();
    }

    /**
     * Constructs an account from set values[cite: 5].
     * 
     * @param startingBalance The initial balance retrieved from the file.
     * @param startingStreak The initial streak count retrieved from the file.
     */
    public Account(double startingBalance, int startingStreak) {
        this.balance = startingBalance;
        this.streakCount = startingStreak;
        this.lastInterestPaymentAt = LocalDateTime.now();
    }

    /**
     * Adds amount to balance and increments streakCount by 1[cite: 5].
     * 
     * @param amount The amount of money to deposit.
     * @throws InvalidAmountException if the number input is not within the valid number range[cite: 5].
     */
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        this.balance += amount;
        this.streakCount += 1;
    }

    /**
     * Subtracts amount from balance and resets streakCount to 0[cite: 5].
     * 
     * @param amount The amount of money to withdraw.
     * @throws InvalidAmountException if the number input is not within the valid number range[cite: 5].
     * @throws InsufficientFundsException if the withdrawal amount is greater than the current balance[cite: 5].
     */
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > this.balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        this.balance -= amount;
        this.streakCount = 0;
    }

    /**
     * Returns current balance[cite: 5].
     * 
     * @return The current balance in dollars[cite: 3].
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Returns current streak count[cite: 5].
     * 
     * @return Consecutive deposits with no withdrawal in between[cite: 3].
     */
    public int getStreak() {
        return this.streakCount;
    }

    /**
     * Replaces current active boost with a new one[cite: 6].
     * 
     * @param boost The InterestBoost object containing the additional rate and duration.
     */
    public void applyInterestBoost(InterestBoost boost) {
        this.activeBoostRate = boost.getAdditionalRate();
        this.boostExpiresAt = LocalDateTime.now().plus(boost.getDuration()); 
    }

    /**
     * Clears activeBoostRate if expired and then returns baseInterestRate plus activeBoostRate[cite: 6].
     * 
     * @return The combined active annual interest rate.
     */
    public double getCurrentAnnualRate() {
        if (boostExpiresAt != null && LocalDateTime.now().isAfter(boostExpiresAt)) {
            this.activeBoostRate = 0.0;
            this.boostExpiresAt = null;
        }
        return this.baseInterestRate + this.activeBoostRate;
    }

    /**
     * Computes time since last interest payment and adds balance * getCurrentAnnualRate() * (elapsed days / 365)[cite: 6].
     * Sets lastInterestPaymentAt to the current time[cite: 6].
     */
    public void payInterest() {
        LocalDateTime now = LocalDateTime.now();
        long daysElapsed = ChronoUnit.DAYS.between(this.lastInterestPaymentAt, now);
        
        if (daysElapsed > 0) {
            double interestAccrued = this.balance * getCurrentAnnualRate() * (daysElapsed / 365.0);
            this.balance += interestAccrued;
            this.lastInterestPaymentAt = now;
        }
    }
}