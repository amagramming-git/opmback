package com.openmemo.opmback.entity.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openmemo.opmback.entity.common.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostResponce extends ApiResponse {
    public PostResponce() {
        super();
    }
}
