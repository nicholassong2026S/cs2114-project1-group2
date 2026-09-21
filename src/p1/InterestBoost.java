package p1;

import java.time.Duration;

public class InterestBoost {

    // Data fields
    private final double additionalRate;   // Extra annual interest rate this boost gives.
    private final Duration duration;       // How long the boost lasts after application.

    // Constructor
    public InterestBoost(double additionalRate, Duration duration) {
        this.additionalRate = additionalRate;
        this.duration = duration;
    }

    // Getter methods
    public double getAdditionalRate() {
        return additionalRate;
    }

    public Duration getDuration() {
        return duration;
    }
}
