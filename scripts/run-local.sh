#!/usr/bin/env bash

set -euo pipefail

export MAVEN_OPTS="${MAVEN_OPTS:-} -Xms512m -Xmx2g"

SIMULATION_CLASS="${1:-com.example.performance.simulation.UserJourneySimulation}"

echo "Running Gatling simulation: ${SIMULATION_CLASS}"
mvn gatling:test -Dgatling.simulationClass="${SIMULATION_CLASS}"

