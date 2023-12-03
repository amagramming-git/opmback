package com.openmemo.opmback.entity.post;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("customerid")
    private Integer customerid;
    @JsonProperty("content")
    private String content;
    @JsonProperty("createdAt")
    private Date createdAt;
    @JsonProperty("updatedAt")
    private Date updatedAt;
}
