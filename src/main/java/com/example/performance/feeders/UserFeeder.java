package com.example.performance.feeders;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.Session;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;

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
     * Each virtual user gets a unique userId and email.
     *
     * @return A FeederBuilder that generates random userId and email
     */
    public static FeederBuilder<Object> randomUsers() {
        Supplier<Map<String, Object>> generator = () -> Map.of(
            "userId", UUID.randomUUID().toString(),
            "email", "user+" + UUID.randomUUID() + "@example.com"
        );

        // Create a large list from the generator and use .random() to avoid feeder exhaustion
        return listFeeder(Stream.generate(generator).limit(10000).toList()).random();
    }

    /**
     * Adds a correlation ID to the session for request tracing.
     *
     * @param session The current Gatling session
     * @return Session with correlationId added
     */
    public static Session withCorrelationId(Session session) {
        return session.set("correlationId", UUID.randomUUID().toString());
    }
}