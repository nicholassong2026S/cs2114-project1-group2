
/**
 * Contains Streak Coin balance. Earns Streak Coins after a saving streak and
 * converts Streak Coins into an interest boost.
 */
public class StreakRewards {
    private int streakCoinBalance;

    public StreakRewards() {
        streakCoinBalance = 0;
    }


    /**
     * Adds StreakCoins to balance based on currentStreak.
     * 
     * @param currentStreak
     */
    public void earnStreakCoins(int currentStreak) {

    }


    /**
     * returns current streakCoinBalance
     * 
     * @return
     */
    public int getStreakCoinBalance() {
        return streakCoinBalance;
    }


    public InterestBoost redeemStreakCoins(int streakCoinsToSpend) {

        if (streakCoinsToSpend <= 0) {
            // The number input is not within the valid number range.
            Exception InvalidAmountException = new Exception();
            throw InvalidAmountException;
        }
        if (streakCoinsToSpend > streakCoinBalance) {
            // The user does not have enough StreakCoins to redeem the desired
            // reward.

            Exception InsufficientStreakCoinException = new Exception();
            throw InsufficientStreakCoinException;
        }

        int x = streakCoinBalance - streakCoinsToSpend;
        return new InterestBoost(x, null);
    }
}
