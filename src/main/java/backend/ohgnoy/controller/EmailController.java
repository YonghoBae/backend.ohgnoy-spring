package backend.ohgnoy.controller;

import backend.ohgnoy.dto.ApiResponseDto;
import backend.ohgnoy.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "Email", description = "이메일 인증 API")
@RestController
@RequestMapping("/users/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @Operation(summary = "인증 이메일 발송")
    @PostMapping("")
    public ResponseEntity<ApiResponseDto<Integer>> sendEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        int authNumber = emailService.sendAuthEmail(email);

        ApiResponseDto<Integer> response = new ApiResponseDto<>(
                2000,
                "sendMail",
                authNumber
        );

        return ResponseEntity.ok(response);
    }
}
