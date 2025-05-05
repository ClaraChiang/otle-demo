# OpenTelemetry Collector Setup

This project demonstrates how to set up and use the OpenTelemetry Collector with a Spring Boot application.

## Prerequisites

- Docker and Docker Compose
- Java 21
- Maven

## Setup Instructions

### 1. Start the OpenTelemetry Collector

```bash
docker-compose up -d
```

This will start the OpenTelemetry Collector on your local machine. The collector is configured to:
- Receive telemetry data via OTLP over HTTP (port 4318) and gRPC (port 4317)
- Process the data using batch processing
- Export the data to the console using the debug exporter (for testing purposes)
- Export traces to Zipkin and Jaeger for visualization

### 2. Verify the Collector is Running

```bash
docker ps
```

You should see the `otel-collector`, `zipkin`, and `jaeger` containers running.

### 3. Run Your Spring Boot Application

Your Spring Boot application is already configured to send telemetry data to the OpenTelemetry Collector via the configuration in `application.yml`.

```bash
mvn spring-boot:run
```

### 4. View the Telemetry Data

You can view the telemetry data in multiple ways:

- **Debug output**: View the raw telemetry data in the logs of the OpenTelemetry Collector:
  ```bash
  docker logs -f otel-collector
  ```

- **Zipkin UI**: Access the Zipkin UI at http://localhost:9411 to view distributed traces.

- **Jaeger UI**: Access the Jaeger UI at http://localhost:16686 to view distributed traces.

## Advanced Configuration

### Adding Prometheus for Metrics

Uncomment the Prometheus service in the `docker-compose.yml` file and the Prometheus exporter in the `otel-collector-config.yaml` file to enable metrics collection.

You'll also need to create a `prometheus.yml` file with the following content:

```yaml
global:
  scrape_interval: 15s
  evaluation_interval: 15s

scrape_configs:
  - job_name: 'otel-collector'
    scrape_interval: 5s
    static_configs:
      - targets: ['otel-collector:8889']
```

After starting the services, you can access the Prometheus UI at http://localhost:9090.

## Troubleshooting

If you're not seeing any telemetry data:

1. Check that your application is running and generating telemetry data
2. Verify that the OpenTelemetry Collector and other services are running (`docker ps`)
3. Check the logs of the OpenTelemetry Collector for any errors (`docker logs otel-collector`)
4. Ensure that your application is configured to send data to the correct endpoint (http://localhost:4318 for HTTP or localhost:4317 for gRPC)

## Comparison of Tracing Systems

### Zipkin
- Simple, lightweight distributed tracing system
- Good for basic tracing needs
- Easy to set up and use
- Access the UI at http://localhost:9411

### Jaeger
- More feature-rich distributed tracing system
- Better for complex systems with high trace volumes
- Provides more advanced querying and visualization
- Access the UI at http://localhost:16686
