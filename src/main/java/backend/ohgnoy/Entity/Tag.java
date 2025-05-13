package backend.ohgnoy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tag_id;  // 태그 고유 ID

    @Column(name = "tagname")
    private String tagname;  // 태그 이름 (예: 공지사항, 이벤트)

    @Column(name = "tagdesc")
    private String tagdesc;  // 태그 설명 (태그의 의미나 용도에 대한 설명)

    @Column(name = "registdate")
    private LocalDateTime registdate;  // 태그 등록 일시

    @Column(name = "registuid")
    private Integer registuid;  // 태그를 등록한 사용자 ID

    @Column(name = "modifydate")
    private LocalDateTime modifydate;  // 태그 수정 일시

    @Column(name = "modifyuid")
    private Integer modifyuid;  // 태그를 수정한 사용자 ID
}
