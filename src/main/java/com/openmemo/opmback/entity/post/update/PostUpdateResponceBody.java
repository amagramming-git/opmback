package com.openmemo.opmback.entity.post.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostUpdateResponceBody {
    @JsonProperty("updateCount")
    private Integer updateCount;
}
