package com.mrezaei.test.proto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrezaei.test.proto.models.HtmlObjectContainer;
import com.mrezaei.test.proto.models.Order;

import java.nio.charset.StandardCharsets;

public class JsonRunner extends AbstractRunner<String> {
    private final ObjectMapper objectMapper;

    public JsonRunner(int numberOfRecords) {
        super(numberOfRecords);
        objectMapper = new ObjectMapper();
    }

    @Override
    String serializeOrderMessage(Order order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (Exception e) {
            throw new ObjectConvertException(e);
        }
    }

    @Override
    void deserializeOrderMessage(String msg) {
        try {
            objectMapper.readValue(msg, Order.class);
        } catch (Exception e) {
            throw new ObjectConvertException(e);
        }
    }

    @Override
    protected String serializeHTMLMessage(HtmlObjectContainer htmlObject) {
        try {
            return objectMapper.writeValueAsString(htmlObject);
        } catch (Exception e) {
            throw new ObjectConvertException(e);
        }
    }

    @Override
    protected void deserializeHTMLMessage(String msg) {
        try {
            objectMapper.readValue(msg, HtmlObjectContainer.class);
        } catch (Exception e) {
            throw new ObjectConvertException(e);
        }
    }

    @Override
    protected byte[] getBytes(String msg) {
        return msg.getBytes(StandardCharsets.UTF_8);
    }

}
