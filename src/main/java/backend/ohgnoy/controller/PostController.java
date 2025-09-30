package backend.ohgnoy.controller;

import backend.ohgnoy.dto.PostModifyRequestDto;
import backend.ohgnoy.entity.Post;
import backend.ohgnoy.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/list")
    public List<Post> listPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @PostMapping("/modify/{postId}")
    public Post modifyPost(@PathVariable Integer postId,@RequestBody PostModifyRequestDto postModifyRequestDto){ return postService.modifyPost(postId, postModifyRequestDto); }
}
