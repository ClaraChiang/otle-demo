package org.example.service;

import io.opentelemetry.api.trace.Span;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Override
    public long generateOrderId() {
        // Get current span for adding attributes
        log.info("Generating new order ID");
        Span span = Span.current();

        // Generate a random order ID based on UUID
        String uuid = UUID.randomUUID().toString();
        long orderId = Math.abs(uuid.hashCode());

        // Add the order ID as an attribute to the current span for tracing
        span.setAttribute("order.id", orderId);

        log.info("Generated new order ID: {}", orderId);

        return orderId;
    }
}
