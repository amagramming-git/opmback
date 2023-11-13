package com.openmemo.opmback.entity.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openmemo.opmback.entity.common.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResponce extends ApiResponse {
    public CustomerResponce() {
        super();
    }
}
