package com.openmemo.opmback.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openmemo.opmback.entity.post.PostDto;
import com.openmemo.opmback.entity.post.insert.PostInsertRequest;
import com.openmemo.opmback.entity.post.update.PostUpdateRequest;
import com.openmemo.opmback.mapper.post.Post;
import com.openmemo.opmback.mapper.post.PostExample;
import com.openmemo.opmback.mapper.post.PostMapper;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public List<PostDto> selectMine(Integer customerId) {
        PostExample postExample = new PostExample();
        postExample.setOrderByClause("id");
        postExample.createCriteria().andCustomeridEqualTo(customerId);
        List<Post> postList = postMapper.selectByExample(postExample);
        List<PostDto> postDtoList = setPostDtoList(postList);
        return postDtoList;
    }

    public PostDto select(Integer id) {
        PostExample postExample = new PostExample();
        postExample.createCriteria().andIdEqualTo(id);
        List<Post> postList = postMapper.selectByExample(postExample);
        List<PostDto> dtoList = setPostDtoList(postList);
        return dtoList.get(0);
    }

    public int insert(PostInsertRequest req, Integer customerId) {
        Post post = new Post();
        post.setCustomerid(customerId);
        post.setContent(req.getContent());
        int insertCount = postMapper.insertCustom(post);
        return insertCount;
    }

    public int update(int customerid, int id, PostUpdateRequest req) {
        Post post = new Post();
        post.setId(id);
        post.setCustomerid(customerid);
        post.setContent(req.getContent());
        int updateCount = postMapper.updateByPrimaryKeyCustom(post);
        return updateCount;
    }

    public int delete(int customerId,int id) {
        int deleteCount = postMapper.deleteByPrimaryKey(id);
        return deleteCount;
    }

    private List<PostDto> setPostDtoList(List<Post> postList) {
        List<PostDto> postDtoList = new ArrayList<PostDto>();
        for (Post post : postList) {
            PostDto dto = setPostDto(post);
            postDtoList.add(dto);
        }
        return postDtoList;
    }

    private PostDto setPostDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setCustomerid(post.getCustomerid());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }
}
