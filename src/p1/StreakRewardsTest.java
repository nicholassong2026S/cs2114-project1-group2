package p1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StreakRewardsTest {

    private StreakRewards rewards;

    @Before

    public void setUp() {
        rewards = new StreakRewards();
    }

    // -------------------------
    // Constructor Test
    // -------------------------
    @Test
    public void testConstructor() {
        assertEquals(0, rewards.getStreakCoinBalance());
    }

    // -------------------------
    // earnStreakCoins Tests
    // -------------------------
    @Test
    public void testEarnStreakCoinsNormal() {
        rewards.earnStreakCoins(3);   // base reward only
        assertEquals(5, rewards.getStreakCoinBalance());
    }

    @Test

    public void testEarnStreakCoinsMilestone() {
        rewards.earnStreakCoins(5);   // base + milestone
        assertEquals(25, rewards.getStreakCoinBalance());
    }

    @Test

    public void testEarnStreakCoinsMultipleCalls() {
        rewards.earnStreakCoins(3);   // +5
        rewards.earnStreakCoins(5);   // +25
        assertEquals(30, rewards.getStreakCoinBalance());
    }

    @Test

    public void testEarnStreakCoinsZeroOrNegative() {
        rewards.earnStreakCoins(0);
        assertEquals(0, rewards.getStreakCoinBalance());

        rewards.earnStreakCoins(-10);
        assertEquals(0, rewards.getStreakCoinBalance());
    }

    // -------------------------
    // redeemStreakCoins Tests
    // -------------------------
    @Test
    public void testRedeemStreakCoinsNormal() throws Exception {
        rewards.earnStreakCoins(5);   // +25 coins
        rewards.earnStreakCoins(5);   // +25 coins → total 50

        InterestBoost boost = rewards.redeemStreakCoins(25);

        assertEquals(25, rewards.getStreakCoinBalance());  // 50 - 25
        assertNotNull(boost);
    }

    @Test

    public void testRedeemStreakCoinsInvalidAmount() {
        Exception thrown = null;

        try {
            rewards.redeemStreakCoins(0);
        }
        catch (InvalidAmountException e) {
            thrown = e;
        }
        catch (Exception e) {
            // wrong exception
        }

        assertNotNull(thrown);
    }

    @Test

    public void testRedeemStreakCoinsInsufficient() {
        Exception thrown = null;

        rewards.earnStreakCoins(3); // +5 coins

        try {
            rewards.redeemStreakCoins(10); // too many
        }
        catch (InsufficientStreakCoinsException e) {
            thrown = e;
        }
        catch (Exception e) {
            // wrong exception
        }

        assertNotNull(thrown);
        assertEquals(5, rewards.getStreakCoinBalance()); // unchanged
    }
}
