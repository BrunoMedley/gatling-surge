#!/usr/bin/env bash

set -euo pipefail

SIMULATION_CLASS="${SIMULATION_CLASS:-com.example.performance.simulation.UserJourneySimulation}"
USERS_PER_SEC="${USERS_PER_SEC:-5}"
DURATION_SECONDS="${DURATION_SECONDS:-60}"

echo "Running CI Gatling simulation:"
echo "  Simulation: ${SIMULATION_CLASS}"
echo "  Users/sec : ${USERS_PER_SEC}"
echo "  Duration  : ${DURATION_SECONDS}s"

mvn -B gatling:test \
  -Dgatling.simulationClass="${SIMULATION_CLASS}" \
  -DUSERS_PER_SEC="${USERS_PER_SEC}" \
  -DDURATION_SECONDS="${DURATION_SECONDS}"

