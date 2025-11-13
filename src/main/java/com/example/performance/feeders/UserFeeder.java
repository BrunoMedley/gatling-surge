package com.example.performance.feeders;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.Session;

import java.util.Map;
import java.util.UUID;

import static io.gatling.javaapi.core.CoreDsl.IteratorFeederBuilder;

/**
 * Utility class for creating data feeders that provide test data to scenarios.
 * Feeders can be CSV-based, database-based, or programmatically generated.
 */
public final class UserFeeder {

    private UserFeeder() {
        // Utility class - prevents instantiation
    }

    /**
     * Creates a feeder that generates random user data on-the-fly.
     * Useful when you need unique data for each virtual user without a CSV file.
     * 
     * @return A FeederBuilder that generates random userId and email for each iteration
     */
    public static FeederBuilder<Object> randomUsers() {
        return IteratorFeederBuilder(() -> Map.of(
            "userId", UUID.randomUUID().toString(),  // Random UUID as user ID
            "email", "user+" + UUID.randomUUID() + "@example.com"  // Random email address
        ));
    }

    /**
     * Adds a correlation ID to the session for request tracing.
     * Useful for distributed tracing and debugging in logs.
     * 
     * @param session The current Gatling session
     * @return Session with correlationId added
     */
    public static Session withCorrelationId(Session session) {
        return session.set("correlationId", UUID.randomUUID().toString());
    }
}

