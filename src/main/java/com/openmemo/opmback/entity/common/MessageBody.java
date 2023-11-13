package com.openmemo.opmback.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageBody {
    @JsonProperty("item")
    private String item;
    @JsonProperty("message")
    private String message;
}
