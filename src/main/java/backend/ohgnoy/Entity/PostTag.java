package backend.ohgnoy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post_tags")
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_tag_id")
    private Integer post_tag_id;

    @Column(name = "post_id")
    private Integer post_id;

    @Column(name = "tag_id")
    private Integer tag_id;
}
