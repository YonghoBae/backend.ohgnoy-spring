package backend.ohgnoy.controller;

import backend.ohgnoy.dto.ApiResponseDto;
import backend.ohgnoy.dto.UserLoginRequestDto;
import backend.ohgnoy.dto.UserResponseDto;
import backend.ohgnoy.entity.User;
import backend.ohgnoy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody User user){
        UserResponseDto registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

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
