package backend.ohgnoy.Repository;

import backend.ohgnoy.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
