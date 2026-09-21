package p1;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Holds current balance, streak count, interest rate, and any active interest
 * boost.
 * 
 * @author Group 2: Andersson, Luke, Nicholas, Ashutosh
 * @version 2026.09.21
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private double balance;
    private int streakCount;
    private double baseInterestRate = 0.02;
    private double activeBoostRate;
    private LocalDateTime boostExpiresAt;
    private LocalDateTime lastInterestPaymentAt;

    /**
     * Constructs a new account with a balance of 0.0 and a streak of 0.
     */
    public Account() {
        this.balance = 0.0;
        this.streakCount = 0;
        this.lastInterestPaymentAt = LocalDateTime.now();
    }


    /**
     * Constructs an account from set values.
     * 
     * @param startingBalance
     *            The initial balance retrieved from the file.
     * @param startingStreak
     *            The initial streak count retrieved from the file.
     */
    public Account(double startingBalance, int startingStreak) {
        this.balance = startingBalance;
        this.streakCount = startingStreak;
        this.lastInterestPaymentAt = LocalDateTime.now();
    }


    /**
     * Adds amount to balance and increments streakCount by 1.
     * 
    /**
     * Adds amount to balance and increments streakCount by 1.
     *
     * @param amount
     *            The amount of money to deposit.
     * @throws InvalidAmountException
     *             if the number input is not within the valid number
     *             range.
     */
    public void deposit(double amount) throws InvalidAmountException {
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new InvalidAmountException(
                "Deposit amount must be positive.");
        }
        this.balance += amount;
        this.streakCount += 1;
    }


    /**
     * Subtracts amount from balance and resets streakCount to 0.
     * 
    /**
     * Subtracts amount from balance and resets streakCount to 0.
     *
     * @param amount
     *            The amount of money to withdraw.
     * @throws InvalidAmountException
     *             if the number input is not within the valid number
     *             range.
     * @throws InsufficientFundsException
     *             if the withdrawal amount is greater than the current
     *             balance.
     */
    public void withdraw(double amount)
        throws InvalidAmountException,
        InsufficientFundsException {
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new InvalidAmountException(
                "Withdrawal amount must be positive.");
        }
        if (amount > this.balance) {
            throw new InsufficientFundsException(
                "Insufficient funds for withdrawal.");
        }
        this.balance -= amount;
        this.streakCount = 0;
    }


    /**
     * Returns current balance.
     * 
     * @return The current balance in dollars.
     */
    public double getBalance() {
        return this.balance;
    }


    /**
     * Returns current streak count.
     * 
     * @return Consecutive deposits with no withdrawal in between.
     */
    public int getStreak() {
        return this.streakCount;
    }


    /**
     * Replaces current active boost with a new one.
     * 
     * @param boost
     *            The InterestBoost object containing the additional rate and
     *            duration.
     */
    public void applyInterestBoost(InterestBoost boost) {
        this.activeBoostRate = boost.getAdditionalRate();
        this.boostExpiresAt = LocalDateTime.now().plus(boost.getDuration());
    }


    /**
     * Clears activeBoostRate if expired and then returns baseInterestRate plus
     * activeBoostRate.
     * 
     * @return The combined active annual interest rate.
     */
    public double getCurrentAnnualRate() {
        if (boostExpiresAt != null && LocalDateTime.now().isAfter(
            boostExpiresAt)) {
            this.activeBoostRate = 0.0;
            this.boostExpiresAt = null;
        }
        return this.baseInterestRate + this.activeBoostRate;
    }


    /**
     * Computes time since last interest payment and adds balance *
     * getCurrentAnnualRate() * (elapsed days / 365).
     * Sets lastInterestPaymentAt to the current time.
     */
    public void payInterest() {
        LocalDateTime now = LocalDateTime.now();
        long daysElapsed = ChronoUnit.DAYS.between(this.lastInterestPaymentAt,
            now);

        if (daysElapsed > 0) {
            double interestAccrued = this.balance * getCurrentAnnualRate()
                * (daysElapsed / 365.0);
            this.balance += interestAccrued;
            this.lastInterestPaymentAt = now;
        }
    }
}
