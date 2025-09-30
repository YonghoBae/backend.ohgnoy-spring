package backend.ohgnoy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 모든 API 응답에 사용하는 표준 포맷 DTO 클래스
@Getter
@AllArgsConstructor
public class ApiResponseDto<T> {
    private final int code;    // 성공/실패 코드
    private final String message; // 설명 메시지
    private final T data;         // 반환 데이터 (제네릭 타입)
}
