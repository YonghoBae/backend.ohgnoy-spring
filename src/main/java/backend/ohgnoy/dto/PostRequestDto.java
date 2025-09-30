package backend.ohgnoy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 생성 요청 데이터 전달용 DTO
 * 클라이언트가 보내는 게시글 작성 데이터
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostRequestDto {

    // 게시글 제목 (필수)
    private String title;

    // 게시글 요약 (선택)
    private String excerpt;

    // 게시글 커버 이미지 URL (선택)
    private String coverImage;

    // 게시글 OG 이미지 URL (선택)
    private String ogImage;

    // 게시글 노출 여부 (선택, 기본값 true)
    private Boolean displayYn = true;

    // 작성자 ID (필수, User 엔티티의 userId)
    private Integer authorId;

    // 작성자 IP 주소 (선택)
    private String ipAddr;
}
