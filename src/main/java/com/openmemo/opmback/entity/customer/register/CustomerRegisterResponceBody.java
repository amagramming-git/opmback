package com.openmemo.opmback.entity.customer.register;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRegisterResponceBody {
    @JsonProperty("registerCount")
    private Integer registerCount;
}
