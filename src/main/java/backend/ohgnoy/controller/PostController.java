package backend.ohgnoy.controller;

import backend.ohgnoy.dto.PostModifyRequestDto;
import backend.ohgnoy.dto.PostRequestDto;
import backend.ohgnoy.entity.Post;
import backend.ohgnoy.entity.User;
import backend.ohgnoy.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Post", description = "게시글 관련 API")
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "게시글 목록 조회")
    @GetMapping("/list")
    public List<Post> listPosts() {
        return postService.getAllPosts();
    }

    @Operation(summary = "게시글 생성")
    // consumes를 통해 multipart/form-data를 받겠다고 명시한다
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Post createPost(@RequestPart("data") PostRequestDto postRequestDto,
                           @RequestPart(value = "coverImage", required = false) MultipartFile coverImage, @AuthenticationPrincipal User user) {
        return postService.createPost(postRequestDto, user, coverImage);
    }

    @Operation(summary = "게시글 수정")
    @PostMapping(value = "/modify/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Post modifyPost(@PathVariable Integer postId,
                           @RequestPart("data") PostModifyRequestDto postModifyRequestDto,
                           @RequestPart(value = "coverImage", required = false) MultipartFile coverImage) {
        return postService.modifyPost(postId, postModifyRequestDto, coverImage);
    }
}
