import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Account {
    // Data and state[cite: 3]
    private double balance;
    private int streakCount;
    private double baseInterestRate = 0.02; // Assuming a 2% default base rate[cite: 12]
    private double activeBoostRate;
    private LocalDateTime boostExpiresAt;
    private LocalDateTime lastInterestPaymentAt;

    // Constructors[cite: 5]
    public Account() {
        this.balance = 0.0;
        this.streakCount = 0;
        this.lastInterestPaymentAt = LocalDateTime.now();
    }

    public Account(double startingBalance, int startingStreak) {
        this.balance = startingBalance;
        this.streakCount = startingStreak;
        this.lastInterestPaymentAt = LocalDateTime.now();
    }

    // Method Signatures[cite: 5, 6]
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        this.balance += amount;
        this.streakCount += 1;
    }

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

    public double getBalance() {
        return this.balance;
    }

    public int getStreak() {
        return this.streakCount;
    }

    public void applyInterestBoost(InterestBoost boost) {
        this.activeBoostRate = boost.getAdditionalRate();
        // Assuming InterestBoost duration is handled appropriately to calculate expiration
        this.boostExpiresAt = LocalDateTime.now().plus(boost.getDuration()); 
    }

    public double getCurrentAnnualRate() {
        if (boostExpiresAt != null && LocalDateTime.now().isAfter(boostExpiresAt)) {
            this.activeBoostRate = 0.0;
            this.boostExpiresAt = null;
        }
        return this.baseInterestRate + this.activeBoostRate;
    }

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