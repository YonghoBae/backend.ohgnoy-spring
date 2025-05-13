package backend.ohgnoy.Repository;

import backend.ohgnoy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
    User findBySummary(String summary);
    User findBySummaryAndRegDate(String summary, LocalDateTime regDate);
}
