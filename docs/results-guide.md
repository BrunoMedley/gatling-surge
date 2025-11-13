# Reading Gatling Reports

## Key Sections

- **Requests** – latency percentiles and success ratios per request.
- **Groups** – aggregates of chained requests for user journeys.
- **Active Users** – concurrency profile over time; cross-check with injection steps.
- **Response Time Distribution** – spot multimodal latency spreads.

## SLAs & Baselines

- Document target thresholds per scenario (e.g., P95 < 500 ms, error rate < 1%).
- Store baseline summaries with the generated report (e.g., `target/gatling/<run>/summary.json`).
- Track regressions by comparing recent percentile trends to baselines.

## Tips

- Overlay CI runs to spot performance drifts early.
- Annotate commit hashes and environment details when archiving reports.
- Combine Gatling metrics with system telemetry (APM, logs) for root cause analysis.

