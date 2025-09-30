package backend.ohgnoy.repository;

import backend.ohgnoy.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Integer> {
}
