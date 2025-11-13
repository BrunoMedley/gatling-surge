package com.example.performance.http;

import com.example.performance.config.SimulationConfig;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

/**
 * Factory class for creating HTTP protocol configurations.
 * Centralizes HTTP settings like base URL, headers, and connection pooling.
 */
public final class HttpProtocolFactory {

    private HttpProtocolFactory() {
        // Utility class - prevents instantiation
    }

    /**
     * Creates a default HTTP protocol builder with common settings.
     * 
     * @param config The simulation configuration containing base URL and other settings
     * @return Configured HttpProtocolBuilder ready to use in simulations
     */
    public static HttpProtocolBuilder defaultHttpProtocol(SimulationConfig config) {
        return http
            .baseUrl(config.getBaseUrl())  // Base URL for all requests (can be overridden per request)
            .acceptHeader("application/json")  // Accept JSON responses
            .contentTypeHeader("application/json")  // Send JSON in request bodies
            .userAgentHeader("performance-tests/1.0");  // Identify requests as performance tests
    }
}

