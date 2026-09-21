package p1;

import java.time.Duration;
public class StreakRewards {

    // Data field
    private int streakCoinBalance;   // Streak Coins the user has earned. Starts at 0.

    // Constructor
    public StreakRewards() {
        this.streakCoinBalance = 0;
    }

    // Adds StreakCoins to balance based on currentStreak.
    // Milestones every 5 deposits on the currentStreak.
    // From test plan:
    //  - earnStreakCoins(3) on fresh balance -> streakCoinBalance becomes 5
    //  - earnStreakCoins(5) on fresh balance -> streakCoinBalance becomes 25 (5 base + 20 milestone)
    public void earnStreakCoins(int currentStreak) {
        if (currentStreak <= 0) {
            return; // no coins for non-positive streaks
        }

        int coinsEarned = 5; // base reward
        if (currentStreak % 5 == 0) {
            coinsEarned += 20; // milestone bonus every 5 deposits
        }

        streakCoinBalance += coinsEarned;
    }

    // Returns current StreakCoin balance.
    public int getStreakCoinBalance() {
        return streakCoinBalance;
    }

    // Redeems StreakCoins and returns a new InterestBoost.
    // Throws:
    //  - InvalidAmountException if streakCoinsToSpend <= 0
    //  - InsufficientStreakCoinsException if streakCoinsToSpend > streakCoinBalance
    public InterestBoost redeemStreakCoins(int streakCoinsToSpend)
            throws InvalidAmountException, InsufficientStreakCoinsException {

        if (streakCoinsToSpend <= 0) {
            throw new InvalidAmountException("Streak coins to spend must be greater than 0.");
        }

        if (streakCoinsToSpend > streakCoinBalance) {
            throw new InsufficientStreakCoinsException("Not enough Streak Coins to redeem this reward.");
        }

        // Subtract spent coins
        streakCoinBalance -= streakCoinsToSpend;

        // Map coins spent to boost rate and duration.
        // You can tweak these values to match your group’s design.
        double additionalRate;
        Duration duration;

        if (streakCoinsToSpend >= 100) {
            additionalRate = 0.02;              // +2% annual
            duration = Duration.ofDays(30);     // 30 days
        } else if (streakCoinsToSpend >= 50) {
            additionalRate = 0.01;              // +1% annual
            duration = Duration.ofDays(21);     // 21 days
        } else if (streakCoinsToSpend >= 25) {
            additionalRate = 0.005;             // +0.5% annual
            duration = Duration.ofDays(14);     // 14 days
        } else {
            additionalRate = 0.0025;            // +0.25% annual
            duration = Duration.ofDays(7);      // 7 days
        }

        return new InterestBoost(additionalRate, duration);
    }
}
