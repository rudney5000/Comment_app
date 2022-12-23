package com.rest.api.services;

import com.rest.api.utils.reponse.PostResponseDTO;
import com.rest.api.utils.request.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostResponseDTO> getAll();

    Optional<PostResponseDTO> findById(Long id);
    PostResponseDTO save(PostDTO dto);

    String delete(Long id);

    PostResponseDTO update(PostDTO dto, Long id);

//    String delete(Long id);
}
