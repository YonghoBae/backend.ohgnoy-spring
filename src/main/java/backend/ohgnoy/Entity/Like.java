package backend.ohgnoy.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "like",
        indexes = {
                @Index(
                        name = "user_like_index",
                        columnList = "user_id, post_id, comment_id",
                        unique = true
                )
        }
)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false, updatable = false)
    private Integer likeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id")
    private Comment comment;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate = LocalDateTime.now();
}

