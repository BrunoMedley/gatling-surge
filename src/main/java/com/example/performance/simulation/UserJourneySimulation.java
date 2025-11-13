package com.example.performance.simulation;

import com.example.performance.config.SimulationConfig;
import com.example.performance.http.HttpProtocolFactory;
import com.example.performance.scenario.UserJourneyScenario;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.injectOpen;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;

/**
 * Main Gatling simulation class that orchestrates the performance test.
 * This class defines the load injection pattern and ties together the scenario,
 * HTTP protocol configuration, and test data feeders.
 */
public class UserJourneySimulation extends Simulation {

    // Configuration loaded from system properties or defaults
    private final SimulationConfig config = new SimulationConfig();
    
    // CSV feeder that cycles through user data (circular = loops back to start when exhausted)
    private final FeederBuilder<Object> userFeeder = csv("data/users.csv").circular();

    {
        // Build the scenario with the user journey steps
        ScenarioBuilder scn = scenario("User Journey")
            .exec(UserJourneyScenario.defaultJourney(userFeeder));

        // Configure the load injection pattern:
        // - Start with 1 user per second
        // - Ramp up to configured users per second
        // - Over the configured ramp duration
        setUp(
            scn.injectOpen(
                rampUsersPerSec(1).to(config.getUsersPerSecond()).during(config.getRampDuration())
            )
        ).protocols(HttpProtocolFactory.defaultHttpProtocol(config));
    }
}

