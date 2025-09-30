package backend.ohgnoy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostModifyResponseDto {
    private Integer postId;
    private String title;
    private String excerpt;
    private String coverImage;
    private String ogImage;
    private Boolean displayYn;
    private String ipAddr;
    private Integer modifyUid;
}