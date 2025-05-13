package backend.ohgnoy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter//원래는 엔티티를 만들 때 Setter 메서드를 사용하지 않음 -> 자유롭게 DB 데이터를 변경하는 것은 안전하지 않기 때문에
@Entity
@Table(
        name = "\"user\"",
        indexes = {
                @Index(name = "email_unique",columnList = "email", unique = true)
        }
)
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //속성값이 자동 1씩 증가
        @Column(name = "user_id", nullable =false, updatable = false)
        private Integer userId;

        private String nickName;

        private String email;

        private String password;

        private String summary;

        @Column(name = "reg_date", nullable = false)
        private LocalDateTime regDate;

        private LocalDateTime editDate;
}
