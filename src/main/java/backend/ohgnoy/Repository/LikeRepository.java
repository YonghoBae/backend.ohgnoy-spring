package backend.ohgnoy.Repository;

import backend.ohgnoy.Entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Integer> {
}
