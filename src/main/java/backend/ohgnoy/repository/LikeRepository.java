package backend.ohgnoy.repository;

import backend.ohgnoy.entity.Like;
import backend.ohgnoy.entity.Post;
import backend.ohgnoy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Integer> {
    Optional<Like> findByPostAndUser(Post post, User user);
}
