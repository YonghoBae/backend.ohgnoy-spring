package backend.ohgnoy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "\"user\"",
        indexes = {
                @Index(name = "email_unique", columnList = "email", unique = true)
        }
)
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id", nullable = false, updatable = false)
        private Integer userId;

        @Column(name = "nickname")
        private String nickname;

        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

        @Column(name = "summary")
        private String summary;

        @Column(name = "reg_date", nullable = false)
        private LocalDateTime regDate;

        @Column(name = "edit_date")
        private LocalDateTime editDate;

        // ERD 기반 추가 필드들
        @Column(name = "telephone")
        private String telephone;

        @Column(name = "photo")
        private String photo;

        @Column(name = "last_ip")
        private String lastIp;

        @Column(name = "last_login_date")
        private LocalDateTime lastLoginDate;

        @Column(name = "emp_type_code_id")
        private Integer empTypeCodeId;

        @Column(name = "use_state_code_id")
        private Integer useStateCodeId;

        @Column(name = "delete_date")
        private LocalDateTime deleteDate;
}
