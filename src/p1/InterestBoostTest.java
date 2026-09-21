package p1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.Duration;

public class InterestBoostTest {

    private InterestBoost boost;

    @Before

    public void setUp() {
        boost = new InterestBoost(0.01, Duration.ofDays(10));
    }

    @Test

    public void testConstructorAndGetters() {
        assertEquals(0.01, boost.getAdditionalRate(), 0.0001);
        assertEquals(Duration.ofDays(10), boost.getDuration());
    }
}
