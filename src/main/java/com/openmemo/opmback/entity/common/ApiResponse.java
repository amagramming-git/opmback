package com.openmemo.opmback.entity.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    // 処理結果
    @JsonProperty("result")
    protected String result;

    // メッセージ
    @JsonProperty("messages")
    protected List<MessageBody> messages;
}
