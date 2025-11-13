package com.example.performance.scenario;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.feed;
import static io.gatling.javaapi.core.CoreDsl.pause;
import static io.gatling.javaapi.http.HttpDsl.ElFileBody;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

/**
 * Defines the user journey scenario - the sequence of HTTP requests
 * that simulate a typical user interaction with the application.
 */
public final class UserJourneyScenario {

    private UserJourneyScenario() {
        // Utility class - prevents instantiation
    }

    /**
     * Builds the default user journey scenario chain.
     * 
     * @param feeder The data feeder providing test data (e.g., user IDs, emails)
     * @return A ChainBuilder representing the complete user journey
     */
    public static ChainBuilder defaultJourney(FeederBuilder<Object> feeder) {
        return feed(feeder)  // Load test data from feeder into session
            // Step 1: Health check endpoint
            .exec(http("Health Check")
                .get("/health")
                .check(status().is(200)))  // Verify successful response
            .pause(1)  // Wait 1 second (simulates user thinking time)
            
            // Step 2: Create a new user
            .exec(http("Create User")
                .post("/users")
                .body(ElFileBody("bodies/create-user.json"))  // Load JSON body from file
                .check(status().is(201)))  // Verify user was created
            .pause(2)  // Wait 2 seconds
            
            // Step 3: Retrieve the created user
            .exec(http("Get User")
                .get("/users/${id}")  // ${id} is resolved from session/feeder data
                .check(status().is(200)));  // Verify successful retrieval
    }
}

