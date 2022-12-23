package com.rest.api.services.impl;

import com.rest.api.entities.Post;
import com.rest.api.errors.ResourceNotFoundException;
import com.rest.api.repositories.PostRepository;
import com.rest.api.services.PostService;
import com.rest.api.utils.reponse.PostResponseDTO;
import com.rest.api.utils.request.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rest.api.services.impl.CommentServiceImpl.mapperToCommentDTO;
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostResponseDTO> getAll() {
        return postRepository.findAll().stream().map(p -> mapperToPostDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponseDTO> findById(Long id) {
//        Post p = postRepository.findById(id).get();
//
//        if ( p == null){
//            throw new ResourceNotFoundException()
//        }
        postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post not found with id :" + id));
        return Optional.of(mapperToPostDTO(postRepository.findById(id).get()));
    }

    @Override
    public PostResponseDTO save(PostDTO dto) {
        Post p = new Post();
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());

        Post save = postRepository.save(p);
        return mapperToPostDTO(save);
    }

    @Override
    public String delete(Long id) {
        postRepository.deleteById(id);
        return "Post Deleted";
    }

    @Override
    public PostResponseDTO update(PostDTO dto, Long id) {
        Post p = postRepository.findById(id).get();
        p.setDescription(p.getDescription());
        p.setContent(p.getContent());
        p.setTitle(p.getTitle());
        return mapperToPostDTO(p);
    }

    private PostResponseDTO mapperToPostDTO(Post entity){
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setDescription(entity.getDescription());
        if (entity.getComments().size()>0){
            dto.setComments(entity.getComments().stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toSet()));
        }
        return dto;
    }
}
