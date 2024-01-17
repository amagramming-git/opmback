package com.openmemo.opmback.entity.post.insert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostInsertResponceBody {
    @JsonProperty("insertCount")
    private Integer insertCount;
}
