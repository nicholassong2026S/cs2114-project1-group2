import student.TestCase;
import java.time.Duration;

public class InterestBoostTest extends TestCase {

    private InterestBoost boost;

    public void setUp() {
        boost = new InterestBoost(0.01, Duration.ofDays(10));
    }

    public void testConstructorAndGetters() {
        assertEquals(0.01, boost.getAdditionalRate(), 0.0001);
        assertEquals(Duration.ofDays(10), boost.getDuration());
    }
}
