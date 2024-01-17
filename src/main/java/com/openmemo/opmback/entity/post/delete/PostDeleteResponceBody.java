package com.openmemo.opmback.entity.post.delete;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDeleteResponceBody {
    @JsonProperty("deleteCount")
    private Integer deleteCount;
}
