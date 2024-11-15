package com.mrezaei.test.proto.models;

import com.mrezaei.test.proto.proto_models.Order;

public record Customer(
        int customerId,
        String name,
        String email,
        String phone
) {

    public Order.Customer toProto() {
        return Order.Customer.newBuilder()
                .setCustomerId(customerId)
                .setName(name)
                .setEmail(email)
                .setPhone(phone)
                .build();
    }

    public static Customer fromProto(Order.Customer customer) {
        return new Customer(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
