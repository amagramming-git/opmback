package com.openmemo.opmback.entity.customer.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openmemo.opmback.entity.customer.CustomerRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerGetRequest extends CustomerRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
}
