package com.crm.service;

import com.crm.entity.Employee;
import com.crm.entity.Post;
import com.crm.payload.PostDto;
import com.crm.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;

    }

    public PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setName(post.getName());
        postDto.setDescription(post.getDescription());
        postDto.setComments(post.getComments());
        return postDto;

    }

    public Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setName(postDto.getName());
        post.setDescription(postDto.getDescription());
        post.setComments(postDto.getComments());
        return post;


    }

    public PostDto addpost(PostDto postDto) {
        Post post = mapToEntity(postDto);

        Post postData = postRepository.save(post);
        PostDto posDto = mapToDto(postData);
        return postDto;


    }

    public void delete(long id) {
        postRepository.deleteById(id);

    }

    public PostDto updatepost(long id, PostDto dto) {
        Post post = mapToEntity(dto);
        post.setId(id);
        Post updatepost=postRepository.save(post);
        PostDto postDto=mapToDto(updatepost);
        return postDto;

    }
   public List<PostDto> getPosts(){
        List<Post> post=postRepository.findAll();
        List<PostDto> dto=post.stream().map(e-> mapToDto(e)).collect(Collectors.toList());
        return dto;
   }


}
