package com.mrezaei.test.proto.models;

import com.mrezaei.test.proto.proto_models.Order;

public record Address(
        String country,
        String state,
        String city,
        String street,
        String postalCode
) {

    public Order.Address toProto() {
        return Order.Address.newBuilder()
                .setCountry(country)
                .setState(state)
                .setCity(city)
                .setStreet(street)
                .setPostalCode(postalCode)
                .build();
    }

    public static Address fromProto(Order.Address address) {
        return new Address(
                address.getCountry(),
                address.getState(),
                address.getCity(),
                address.getStreet(),
                address.getPostalCode()
        );
    }
}
