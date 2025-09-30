// 패키지 선언: 사용자 정보를 관리하는 엔티티 클래스가 위치하는 패키지
package backend.ohgnoy.entity;

// JPA 어노테이션을 사용하기 위한 임포트
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

// Lombok 어노테이션: 모든 필드에 대한 Getter/Setter 자동 생성
@Getter
@Setter

// JPA 엔티티 선언: 해당 클래스가 DB 테이블과 매핑됨
@Entity

// 테이블 설정: 테이블명을 "user"로 지정하며 email 컬럼에 유니크 인덱스 설정
@Table(
        name = "\"user\"",
        indexes = {
                @Index(name = "email_unique", columnList = "email", unique = true)
        }
)
public class User {

        // 사용자 고유 ID (자동 증가, Primary Key)
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id", nullable = false, updatable = false)
        private Integer userId;

        // 사용자 닉네임 (별명)
        @Column(name = "nickname")
        private String nickname;

        // 사용자 이메일 (로그인 및 식별 용도, 유니크 인덱스 설정됨)
        @Column(name = "email")
        private String email;

        // 사용자 비밀번호 (암호화 저장 권장)
        @Column(name = "password")
        private String password;

        // 사용자 한 줄 소개 (자기소개나 상태 메시지)
        @Column(name = "summary")
        private String summary;

        // 회원 가입 일시 (등록 일자)
        @Column(name = "reg_date", nullable = false)
        private LocalDateTime regDate;

        // 사용자 정보 마지막 수정 일시
        @Column(name = "edit_date")
        private LocalDateTime editDate;

        // 사용자 전화번호
        @Column(name = "telephone")
        private String telephone;

        // 사용자 프로필 사진 URL
        @Column(name = "photo")
        private String photo;

        // 마지막 로그인 시 사용한 IP 주소
        @Column(name = "last_ip")
        private String lastIp;

        // 마지막 로그인 일시
        @Column(name = "last_login_date")
        private LocalDateTime lastLoginDate;

        // 사용자 고용 유형 코드 (예: 정규직, 계약직 등 코드 값으로 관리)
        @Column(name = "emp_type_code_id")
        private Integer empTypeCodeId;

        // 사용자 계정 사용 상태 코드 (예: 활성, 정지, 삭제 등 상태 코드 값으로 관리)
        @Column(name = "use_state_code_id")
        private Integer useStateCodeId;

        // 사용자 계정 삭제 처리 일시 (실제 삭제가 아닌 삭제 상태 관리용)
        @Column(name = "delete_date")
        private LocalDateTime deleteDate;
}
