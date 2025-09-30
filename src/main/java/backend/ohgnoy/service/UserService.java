package backend.ohgnoy.service;

import backend.ohgnoy.dto.UserLoginRequestDto;
import backend.ohgnoy.dto.UserResponseDto;
import backend.ohgnoy.entity.User;
import backend.ohgnoy.exception.EmailAlreadyExistsException;
import backend.ohgnoy.exception.InvalidPasswordException;
import backend.ohgnoy.exception.UserNotFoundException;
import backend.ohgnoy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public UserResponseDto registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException();
        }

        if(userRepository.findByNickname(user.getNickname()).isPresent()){
            throw new IllegalArgumentException();
        }

        userRepository.save(user);

        return new UserResponseDto(user.getUserId(), user.getEmail(), user.getNickname());
    }

    public UserResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        //이메일로 사용자 조회( 존재하지 않으면 예외 발생)
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if(!user.getPassword().equals(userLoginRequestDto.getPassword())){
            throw new InvalidPasswordException();
        }

        return new UserResponseDto(user.getUserId(), user.getEmail(), user.getNickname());
    }
}
