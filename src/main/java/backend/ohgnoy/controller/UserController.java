package backend.ohgnoy.controller;

import backend.ohgnoy.dto.ApiResponseDto;
import backend.ohgnoy.dto.UserLoginRequestDto;
import backend.ohgnoy.dto.UserRegisterRequestDto;
import backend.ohgnoy.dto.UserResponseDto;
import backend.ohgnoy.entity.User;
import backend.ohgnoy.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "사용자 관련 API")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "토큰으로 사용자 정보 조회")
    @GetMapping("/token")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> getUserInfoFromToken() {
        // Step 1에서 SecurityContextHolder에 저장했던 사용자 정보를 꺼내온다
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증 객체에서 주체(Principal)를 꺼내어 User 엔티티로 캐스팅한다.
        User user = (User) authentication.getPrincipal();

        UserResponseDto userResponse = new UserResponseDto(
                user.getUserId(),
                user.getEmail(),
                user.getNickname()
        );

        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>(
                2000,
                "Token verified successfully",
                userResponse
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegisterRequestDto dto){
        UserResponseDto registeredUser = userService.registerUser(dto);
        return ResponseEntity.ok(registeredUser);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<String>> loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto){
        String loggedInUser = userService.loginUser(userLoginRequestDto);

        ApiResponseDto<String> response = new ApiResponseDto<>(
                2000,
                "User logged in successfully",
                loggedInUser
        );

        return ResponseEntity.ok(response);
    }
}
