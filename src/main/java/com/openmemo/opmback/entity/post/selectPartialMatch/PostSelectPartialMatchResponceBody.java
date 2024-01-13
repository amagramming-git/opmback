package com.openmemo.opmback.entity.post.selectPartialMatch;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openmemo.opmback.entity.post.PostDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSelectPartialMatchResponceBody {
    @JsonProperty("posts")
    private List<PostDto> posts;
}
