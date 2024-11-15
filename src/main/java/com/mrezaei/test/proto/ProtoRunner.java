package com.mrezaei.test.proto;

import com.mrezaei.test.proto.models.HtmlObjectContainer;
import com.mrezaei.test.proto.models.Order;

public class ProtoRunner extends AbstractRunner<byte[]> {
    public ProtoRunner(int numberOfRecords) {
        super(numberOfRecords);
    }

    void deserializeOrderMessage(byte[] msg) {
        try {
            com.mrezaei.test.proto.proto_models.Order.parseFrom(msg);
        } catch (Exception e) {
            throw new ObjectConvertException(e);
        }
    }

    @Override
    byte[] serializeOrderMessage(Order order) {
        return order.toProto().toByteArray();
    }

    @Override
    protected byte[] serializeHTMLMessage(HtmlObjectContainer htmlObject) {
        return htmlObject.toProto().toByteArray();
    }

    @Override
    protected void deserializeHTMLMessage(byte[] msg) {
        try {
            com.mrezaei.test.proto.proto_models.HtmlContainer.parseFrom(msg);
        } catch (Exception e) {
            throw new ObjectConvertException(e);
        }
    }

    protected byte[] getBytes(byte[] msg) {
        return msg;
    }
}
