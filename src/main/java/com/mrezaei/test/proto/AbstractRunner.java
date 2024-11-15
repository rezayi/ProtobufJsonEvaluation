package com.mrezaei.test.proto;


import com.mrezaei.test.proto.models.*;
import com.mrezaei.test.proto.proto_models.Order.OrderStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRunner<T> {
    private final int numberOfRecords;

    protected AbstractRunner(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public void testNormalObject(int type) {
        long totalTime = 0;
        long totalBytes = 0;
        for (int i = 0; i < numberOfRecords; i++) {
            var order = createOrder(i, type);
            var startTime = System.nanoTime();
            var msg = serializeOrderMessage(order);
            deserializeOrderMessage(msg);
            var endTime = System.nanoTime();
            var bytes = getBytes(msg);
            totalBytes += bytes.length;
            totalTime += endTime - startTime;
        }
        System.out.println("count=" + numberOfRecords);
        System.out.println("total Time=" + (totalTime / 1000000) + " (ms)");
        System.out.println("avg Time=" + (totalTime / numberOfRecords) + " (ns)");
        System.out.println("avg Bytes=" + totalBytes / numberOfRecords);
    }


    public Order createOrder(int index, int type) {
        int customerId = index / 5;
        var customer = new Customer(
                customerId,
                "User name#" + customerId,
                customerId + "_user@testmail.com",
                "091" + (100000000 + index)
        );
        var address = new Address(
                "Country name",
                "State #" + (index % 10),
                "City #" + (index % 100),
                "Street #" + (index % 1000),
                Integer.toString(index + 10000000)
        );
        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < type * 3; i++) {
            items.add(
                    new OrderItem(
                            index % 1000,
                            "item name #" + index,
                            5,
                            (10000000 + index) / 100.0
                    )
            );
        }
        StringBuilder description = new StringBuilder();
        description.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".repeat(Math.max(0, type * 5)));
        return new Order(
                index + 1,
                customer,
                items,
                address,
                OrderStatus.values()[index % (OrderStatus.values().length - 1)],
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                description.toString()
        );
    }

    public void testHTML() {
        try {
            String content = new String(
                    Thread.currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream("Amazon.html")
                            .readAllBytes()
            );
            long totalTime = 0;
            long totalBytes = 0;
            for (int i = 0; i < numberOfRecords; i++) {
                var htmlObject = new HtmlObjectContainer(
                        content
                );
                var startTime = System.nanoTime();
                var msg = serializeHTMLMessage(htmlObject);
                deserializeHTMLMessage(msg);
                var endTime = System.nanoTime();
                var bytes = getBytes(msg);
                totalBytes += bytes.length;
                totalTime += endTime - startTime;
            }
            System.out.println("count=" + numberOfRecords);
            System.out.println("total Time=" + (totalTime / 1000000) + " (ms)");
            System.out.println("avg Time=" + (totalTime / numberOfRecords) + " (ns)");
            System.out.println("avg Bytes=" + totalBytes / numberOfRecords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    protected abstract T serializeHTMLMessage(HtmlObjectContainer htmlObject);

    protected abstract void deserializeHTMLMessage(T msg);

    abstract T serializeOrderMessage(Order order);

    abstract void deserializeOrderMessage(T msg);

    protected abstract byte[] getBytes(T msg);
}
