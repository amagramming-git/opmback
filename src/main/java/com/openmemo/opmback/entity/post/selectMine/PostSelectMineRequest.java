package com.openmemo.opmback.entity.post.selectMine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openmemo.opmback.entity.post.PostRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSelectMineRequest extends PostRequest {

}
