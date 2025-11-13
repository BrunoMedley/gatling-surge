package com.example.performance.config;

import java.time.Duration;

/**
 * Configuration class that loads simulation parameters from system properties.
 * Allows easy customization of test parameters without code changes.
 * 
 * Usage: Set system properties via -D flag or environment variables
 * Example: mvn gatling:test -DBASE_URL=http://localhost:8080 -DUSERS_PER_SEC=10
 */
public class SimulationConfig {

    private final String baseUrl;
    private final int usersPerSecond;
    private final Duration rampDuration;

    /**
     * Initializes configuration from system properties with sensible defaults.
     * System properties can be set via -D flags or environment variables.
     */
    public SimulationConfig() {
        // Base URL of the application under test (default: example.com)
        this.baseUrl = System.getProperty("BASE_URL", "https://api.example.com");
        
        // Target users per second to ramp up to (default: 5)
        this.usersPerSecond = Integer.parseInt(System.getProperty("USERS_PER_SEC", "5"));
        
        // Duration to ramp from 1 user/sec to target users/sec (default: 30 seconds)
        this.rampDuration = Duration.ofSeconds(Long.parseLong(System.getProperty("RAMP_SECONDS", "30")));
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getUsersPerSecond() {
        return usersPerSecond;
    }

    public Duration getRampDuration() {
        return rampDuration;
    }
}

