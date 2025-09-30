package backend.ohgnoy.service;

import backend.ohgnoy.dto.PostModifyRequestDto;
import backend.ohgnoy.dto.PostResponseDto;
import backend.ohgnoy.entity.Post;
import backend.ohgnoy.exception.PostNotFoundException;
import backend.ohgnoy.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public Post modifyPost(Integer postId, PostModifyRequestDto postModifyRequestDto){
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        post.setTitle(postModifyRequestDto.getTitle());
        post.setExcerpt(postModifyRequestDto.getExcerpt());

        return post;
    }
}

