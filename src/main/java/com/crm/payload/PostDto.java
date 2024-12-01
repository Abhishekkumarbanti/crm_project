package com.crm.payload;

import com.crm.entity.Comment;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter

public class PostDto {


    private Long id;
    @NotBlank
    @Size(min=3,message="At list 3 Chars Required")
    private String name;

    @NotBlank
    @Size(min=5,message="At list 5 Chars Required")
    private String description;

    private Set<Comment> comments;


}
