package com.springboot.blog.service;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDTO, long id);

    void deletePostById(long id);
}
