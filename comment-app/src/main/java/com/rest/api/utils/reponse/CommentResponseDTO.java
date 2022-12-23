package com.rest.api.utils.reponse;

import com.rest.api.entities.Post;
import lombok.Data;

@Data

public class CommentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String body;

    private Post post;
}
