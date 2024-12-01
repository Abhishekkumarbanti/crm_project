package com.crm.controller;

import com.crm.entity.Post;
import com.crm.payload.PostDto;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import com.crm.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("api/v1/posts")
    public class PostController {
        private PostRepository postRepository;
        private CommentRepository commentRep;
        private PostService postService;
        public PostController(PostRepository postRepository ,CommentRepository commentRep,PostService postService){
            this.postRepository=postRepository;
            this.commentRep=commentRep;
            this.postService = postService;
        }
//
//        @PostMapping("/addPost")
//        public String createPost(
//                @RequestBody Post post
//                ){
//            postRepository.save(post);
//            return null;
//
//
//        }
//        @DeleteMapping("/delete")
//    public void deletePost(){
//            postRepository.deleteById(1L);
//        }
    @PostMapping("/addPost")
    public ResponseEntity<PostDto>createPost(
            @RequestBody PostDto postDto
    ){
            PostDto postDto1 =postService.addpost(postDto);
            return new ResponseEntity<>(postDto1, HttpStatus.CREATED);

    }
    @DeleteMapping("/delete")
    public String deletepost(@RequestParam long id){
            postService.delete(id);
            return "delete";

    }
    @PutMapping("/update")
    public ResponseEntity<PostDto>updatePost(
            @RequestParam long id,
            @RequestBody PostDto dto
    ){
            PostDto postDto=postService.updatepost(id , dto);
            return new ResponseEntity<>(postDto , HttpStatus.OK);
    }
    @GetMapping("/getAllPost")
    public ResponseEntity<List<PostDto>>getPosts(){
            List<PostDto>postDto=postService.getPosts();
            return new ResponseEntity<>(postDto , HttpStatus.OK);
    }


}
