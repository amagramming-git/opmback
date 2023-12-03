package com.openmemo.opmback.entity.post.selectAll;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openmemo.opmback.entity.post.PostDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSelectAllResponseBody {
    @JsonProperty("post_list")
    private List<PostDto> postList;
}
