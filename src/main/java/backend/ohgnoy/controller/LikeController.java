package backend.ohgnoy.controller;

import backend.ohgnoy.dto.ApiResponseDto;
import backend.ohgnoy.entity.User;
import backend.ohgnoy.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Like", description = "게시글 좋아요 API")
@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "게시글 좋아요 토글 (추가/취소)")
    @PostMapping("/{postId}")
    public ResponseEntity<ApiResponseDto<String>> toggleLike(
            @PathVariable Integer postId,
            @AuthenticationPrincipal User user) {

        // 좋아요 토글 로직 실행
        String resultMessage = likeService.toggleLike(postId, user);

        ApiResponseDto<String> response = new ApiResponseDto<>(
                2000,
                resultMessage,
                null
        );

        return ResponseEntity.ok(response);
    }
}