package backend.ohgnoy.Entity;

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

    @Column(name = "coverImage")
    private String coverImage;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "user_id")
    private User author;

    @Column(name = "ogImage")
    private String ogImage;

    @Column(name = "view_cnt", nullable = false)
    private Integer viewCnt = 0;
}
