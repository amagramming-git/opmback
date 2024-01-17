package com.openmemo.opmback.entity.customer.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openmemo.opmback.entity.customer.CustomerResponce;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerGetResponce extends CustomerResponce {
    @JsonProperty("body")
    private CustomerGetResponceBody body;
}