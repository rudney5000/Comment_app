package com.rest.api.services.impl;

import com.rest.api.entities.Comment;
import com.rest.api.entities.Post;
import com.rest.api.errors.ResourceNotFoundException;
import com.rest.api.repositories.CommentRepository;
import com.rest.api.repositories.PostRepository;
import com.rest.api.services.CommentService;
import com.rest.api.utils.reponse.CommentResponseDTO;
import com.rest.api.utils.request.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Override
    public List<CommentResponseDTO> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        commentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Comment not Found"));
        Comment comment = commentRepository.findById(id).get();
        return Optional.of(mapperToCommentDTO(comment));
    }

    @Override
    public CommentResponseDTO save(CommentDTO dto) {
        Post p = postRepository.findById(dto.getPostId())
                .orElseThrow(()-> new ResourceNotFoundException("Post not Found"));
        Comment comment = new Comment();
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
//        comment.setPost(dto.getPost());
        Comment savedComment = commentRepository.save(comment);
        return mapperToCommentDTO(savedComment);
    }

    @Override
    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Comment deleted";
    }
    @Override
    public CommentResponseDTO update(CommentDTO dto, Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not Found"));
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
//        comment.setId(dto.getId());
//        dto.setPost(comment.getPost());
        return mapperToCommentDTO(commentRepository.save(comment));
    }

    public static CommentResponseDTO mapperToCommentDTO(Comment comment){
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        dto.setPost(comment.getPost());
        return dto;
    }

}
