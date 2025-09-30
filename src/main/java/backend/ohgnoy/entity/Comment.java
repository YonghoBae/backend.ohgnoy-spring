package backend.ohgnoy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "comment",
        indexes = {
                @Index(name = "post_comment_index", columnList = "post_id"),
                @Index(name = "author_comment_index", columnList = "author_id")
        }
)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, updatable = false)
    private Integer commentId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "user_id")
    private User author;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate = LocalDateTime.now();
}
