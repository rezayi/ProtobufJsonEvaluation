package com.mrezaei.test.proto;

import java.security.InvalidParameterException;

public class MainRunner {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1)
            throw new InvalidParameterException("Invalid arguments");
        var testHTML = args.length == 2 && args[1].equals("html");
        Thread.sleep(5000);
        if (testHTML) {
            final var count = 1_000;
            switch (args[0]) {
                case "json" -> new JsonRunner(count).testHTML();
                case "proto" -> new ProtoRunner(count).testHTML();
                default -> throw new InvalidParameterException("Invalid type");
            }
        } else {
            final var count = 1_000_000;
            for (int i = 0; i <= 10; i++) {
                System.out.println("\nType #" + i);
                switch (args[0]) {
                    case "json" -> new JsonRunner(count).testNormalObject(i);
                    case "proto" -> new ProtoRunner(count).testNormalObject(i);
                    default -> throw new InvalidParameterException("Invalid type");
                }
            }
        }
    }
}
