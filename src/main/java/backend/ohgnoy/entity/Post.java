package backend.ohgnoy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "post",
        indexes = {
                @Index(name = "author_index", columnList = "author_id")
        }
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false, updatable = false)
    private Integer postId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "excerpt", columnDefinition = "TEXT")
    private String excerpt;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "user_id")
    private User author;

    @Column(name = "og_image")
    private String ogImage;

    @Column(name = "view_cnt", nullable = false)
    private Integer viewCnt = 0;

    // ERD 기반 확장 필드 추가 (예시로 필요한 것들)
    @Column(name = "display_yn")
    private Boolean displayYn = true;

    @Column(name = "ip_addr", length = 20)
    private String ipAddr;

    @Column(name = "regist_date")
    private LocalDateTime registDate;

    @Column(name = "regist_uid")
    private Integer registUid;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @Column(name = "modify_uid")
    private Integer modifyUid;
}
