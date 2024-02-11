package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Create Blog Post
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    //Get all posts
    @GetMapping
    public PostResponse getAllPosts(
                @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
                ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,
                                              @PathVariable(name = "id") long id){
        PostDTO postDTOResponse = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Entity Deleted Successfully", HttpStatus.OK);
    }
}
