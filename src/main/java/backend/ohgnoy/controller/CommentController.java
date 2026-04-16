package backend.ohgnoy.controller;

import backend.ohgnoy.dto.ApiResponseDto;
import backend.ohgnoy.dto.CommentCreateRequestDto;
import backend.ohgnoy.entity.Comment;
import backend.ohgnoy.entity.User;
import backend.ohgnoy.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment", description = "댓글 관련 API")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "게시글의 댓글 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponseDto<Comment>> getComment(@PathVariable Integer postId) {
        // 기존 CommentService에 작성하신 getComment 메소드 활용
        Comment comment = commentService.getComment(postId);

        ApiResponseDto<Comment> response = new ApiResponseDto<>(
                2000,
                "Comment fetched successfully",
                comment
        );
        return ResponseEntity.ok(response);
    }

     @Operation(summary = "댓글 작성")
     @PostMapping("/create/{postId}")
     public ResponseEntity<ApiResponseDto<Comment>> createComment(
             @PathVariable Integer postId,
             @RequestBody CommentCreateRequestDto dto,
             @AuthenticationPrincipal User user) {

         Comment createdComment = commentService.createComment(postId, dto, user);

         ApiResponseDto<Comment> response = new ApiResponseDto<>(
                 2000,
                 "Comment created successfully",
                 createdComment
         );
         return ResponseEntity.ok(response);
     }
}