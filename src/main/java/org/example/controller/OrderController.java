package org.example.controller;

import io.opentelemetry.api.trace.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.service.OrderService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/generate-order-id")
    public ResponseEntity<?> generateOrderId() {
        // Get current span to add attributes
        Span span = Span.current();
        span.setAttribute("controller.operation", "generate-order-id");

        logger.info("Received request to generate order ID");

        // Generate the order ID
        long orderId = orderService.generateOrderId();

        // Create a response with additional information
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", orderId);
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "SUCCESS");

        logger.info("Returning generated order ID: {}", orderId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
