package org.example.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderDetails {
    private String id;
    private LocalDateTime createdAt;
    private String status;

    // 构造函数
    public OrderDetails() {
        this.createdAt = LocalDateTime.now();
        this.status = "CREATED";
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", status='" + status + '\'' +
                '}';
    }
}
