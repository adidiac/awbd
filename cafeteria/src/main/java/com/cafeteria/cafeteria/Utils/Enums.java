package com.cafeteria.cafeteria.Utils;

public class Enums {
    public enum Role {
        ADMIN,
        USER
    }

    public enum OrderStatus {
        PENDING("PENDING"),
        ACCEPTED("ACCEPTED"),
        REJECTED ("REJECTED"),
        DELIVERED("DELIVERED");

        private String status;

        OrderStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}
