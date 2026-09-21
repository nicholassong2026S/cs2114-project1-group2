import student.TestCase;

public class StreakRewardsTest extends TestCase {

    private StreakRewards rewards;

    public void setUp() {
        rewards = new StreakRewards();
    }

    // -------------------------
    // Constructor Test
    // -------------------------
    public void testConstructor() {
        assertEquals(0, rewards.getStreakCoinBalance());
    }

    // -------------------------
    // earnStreakCoins Tests
    // -------------------------
    public void testEarnStreakCoinsNormal() {
        rewards.earnStreakCoins(3);   // base reward only
        assertEquals(5, rewards.getStreakCoinBalance());
    }

    public void testEarnStreakCoinsMilestone() {
        rewards.earnStreakCoins(5);   // base + milestone
        assertEquals(25, rewards.getStreakCoinBalance());
    }

    public void testEarnStreakCoinsMultipleCalls() {
        rewards.earnStreakCoins(3);   // +5
        rewards.earnStreakCoins(5);   // +25
        assertEquals(30, rewards.getStreakCoinBalance());
    }

    public void testEarnStreakCoinsZeroOrNegative() {
        rewards.earnStreakCoins(0);
        assertEquals(0, rewards.getStreakCoinBalance());

        rewards.earnStreakCoins(-10);
        assertEquals(0, rewards.getStreakCoinBalance());
    }

    // -------------------------
    // redeemStreakCoins Tests
    // -------------------------
    public void testRedeemStreakCoinsNormal() throws Exception {
        rewards.earnStreakCoins(5);   // +25 coins
        rewards.earnStreakCoins(5);   // +25 coins → total 50

        InterestBoost boost = rewards.redeemStreakCoins(25);

        assertEquals(25, rewards.getStreakCoinBalance());  // 50 - 25
        assertNotNull(boost);
    }

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
