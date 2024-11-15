package com.mrezaei.test.proto.models;

import com.mrezaei.test.proto.proto_models.HtmlContainer;

public record HtmlObjectContainer(
        String content
) {
    public static HtmlObjectContainer fromProto(HtmlContainer proto) {
        return new HtmlObjectContainer(
                proto.getContent()
        );
    }

    public HtmlContainer toProto() {
        return HtmlContainer.newBuilder()
                .setContent(content)
                .build();
    }
}
