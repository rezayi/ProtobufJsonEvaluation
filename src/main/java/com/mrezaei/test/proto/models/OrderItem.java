package com.mrezaei.test.proto.models;

import com.mrezaei.test.proto.proto_models.Order;

public record OrderItem(
        int itemId,
        String name,
        int quantity,
        double price
) {
    public Order.OrderItem toProto() {
        return Order.OrderItem.newBuilder()
                .setItemId(itemId)
                .setName(name)
                .setQuantity(quantity)
                .setPrice(price)
                .build();
    }

    public static OrderItem fromProto(Order.OrderItem orderItem) {
        return new OrderItem(
                orderItem.getItemId(),
                orderItem.getName(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
