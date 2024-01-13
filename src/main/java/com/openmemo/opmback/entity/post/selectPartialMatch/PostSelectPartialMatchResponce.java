package com.openmemo.opmback.entity.post.selectPartialMatch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openmemo.opmback.entity.post.PostResponce;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSelectPartialMatchResponce extends PostResponce {
    @JsonProperty("body")
    private PostSelectPartialMatchResponceBody body;
}
