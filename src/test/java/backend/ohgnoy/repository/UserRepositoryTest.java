package backend.ohgnoy.repository;

import backend.ohgnoy.entity.User;
import backend.ohgnoy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userJpa(){
        Optional<User> ou = this.userRepository.findById(1);//User 타입의 값이 있을 수도 있고 없을 수도 있다는 걸 명시적으로 표현하는 클래스
        if(ou.isPresent()){
            User u = ou.get();
            assertEquals("ohgnoy",u.getNickname());

        }
    }
//    public void userJpa() {
//        // given
//        User user = new User();
//        user.setNickName("ohgnoy");
//        user.setEmail("ohgnoy@naver.com");
//        user.setPassword("1234");
//        user.setSummary("개발연습 중인 ohgnoy입니다.");
//        user.setRegDate(LocalDateTime.now());
//
//        // when
//        User savedUser = userRepository.save(user);
//
//        // then
//        assertThat(savedUser.getUserId()).isNotNull();
//        assertThat(savedUser.getNickName()).isEqualTo("ohgnoy");
//        assertThat(userRepository.existsByEmail("ohgnoy@naver.com")).isTrue();
//    }
}

//test 단게
//1. given
//2. when
//3. then