package com.mrezaei.test.proto.models;

import java.util.List;
import com.mrezaei.test.proto.proto_models.Order.OrderStatus;

public record Order(
        int orderId,
        Customer customer,
        List<OrderItem> items,
        Address address,
        OrderStatus status,
        long createdAt,
        long updatedAt,
        String description
){

    public com.mrezaei.test.proto.proto_models.Order toProto(){
        return com.mrezaei.test.proto.proto_models.Order.newBuilder()
                .setOrderId(orderId)
                .setCustomer(customer.toProto())
                .addAllItems(items.stream().map(OrderItem::toProto).toList())
                .setAddress(address.toProto())
                .setOrderStatus(status)
                .setCreatedAt(createdAt)
                .setUpdatedAt(updatedAt)
                .setDescription(description)
                .build();
    }

    public static Order fromProto(com.mrezaei.test.proto.proto_models.Order proto) {
        return new Order(
                proto.getOrderId(),
                Customer.fromProto(proto.getCustomer()),
                proto.getItemsList().stream().map(OrderItem::fromProto).toList(),
                Address.fromProto(proto.getAddress()),
                proto.getOrderStatus(),
                proto.getCreatedAt(),
                proto.getUpdatedAt(),
                proto.getDescription()
        );
    }

}