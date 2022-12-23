package com.rest.api.utils.reponse;

import com.rest.api.entities.Comment;
import lombok.Data;

import java.util.Set;
@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String content;

    private Set<CommentResponseDTO> comments;
}
