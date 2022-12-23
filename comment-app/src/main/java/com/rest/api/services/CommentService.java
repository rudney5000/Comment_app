package com.rest.api.services;

import com.rest.api.utils.reponse.CommentResponseDTO;
import com.rest.api.utils.request.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentResponseDTO> getAll();

    Optional<CommentResponseDTO> findById(Long id);
    CommentResponseDTO save(CommentDTO dto);

    String delete(Long id);

    CommentResponseDTO update(CommentDTO dto, Long id);

//    String delete(Long id);
}
