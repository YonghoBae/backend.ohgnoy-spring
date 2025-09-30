package backend.ohgnoy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequestDto {
    //댓글 내용은 비어있으면 안 되므로, 나중에 @NotBlank 와 같은 유효성 검증 어노테이션 추가
    private String content;

    //댓글을 작성한 사용자의 ID

}
