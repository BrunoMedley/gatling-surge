# Performance Testing with Gatling (Java)

Welcome to the performance engineering workspace. This project uses [Gatling](https://gatling.io/) with the Java DSL to define and execute repeatable load, stress, and soak scenarios.

## Getting Started

1. Ensure you have Java 17+ and Maven 3.8+ installed.
2. Review `pom.xml` for plugin versions and adjust organization-specific coordinates.
3. Run a smoke test locally:
   ```bash
   ./scripts/run-local.sh
   ```

## Project Layout

```text
config/                     # Gatling/generic configuration overrides
docs/                       # SLA baselines, report analysis guides
scripts/                    # Execution helpers for local and CI pipelines
src/main/java/              # Java sources (config, feeders, scenarios)
src/test/resources/data/    # CSV/JSON feeders consumed by simulations
src/test/resources/bodies/  # HTTP request payload templates
target/gatling/             # Generated reports (ignored in VCS)
```

## Key Commands

- `mvn gatling:test` – run the selected simulation (config via `gatling.simulationClass`)
- `mvn clean verify` – full build; bundles test + performance profiles
- `./scripts/run-ci.sh` – opinionated CI wrapper with headless options

## Next Steps

- Tailor feeder data under `src/test/resources/data`.
- Extend `HttpProtocolFactory` with headers, base URLs per environment.
- Model nuanced journeys in `scenario` and expose runnable `simulation` classes.
- Capture SLA targets in `docs/results-guide.md` to track regression thresholds.

Happy profiling!

