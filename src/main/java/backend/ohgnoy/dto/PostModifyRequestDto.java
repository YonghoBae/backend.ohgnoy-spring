package backend.ohgnoy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostModifyRequestDto {
    private String title;
    private String excerpt;
    private String coverImage;
    private String ogImage;
    private Boolean displayYn;
    private String ipAddr;
    private Integer modifyUid; // 보통은 로그인 사용자 ID
}
