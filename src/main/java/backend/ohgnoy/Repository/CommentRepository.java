package backend.ohgnoy.Repository;

import backend.ohgnoy.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
