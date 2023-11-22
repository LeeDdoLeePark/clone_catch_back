package com.example.catch_clone.user.service;

import com.example.catch_clone.jwt.JwtUtil;
import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.dao.UserRepository;
import com.example.catch_clone.user.dto.UserDto;
import com.example.catch_clone.user.dto.UserLoginRequestDto;
import com.example.catch_clone.user.dto.UserRequestDto;
import com.example.catch_clone.user.entity.User;
import com.example.catch_clone.user.service.inter.UserService;
import com.example.catch_clone.util.enums.UserRoleEnum;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder; //비밀번호 암호화

    @Override
    @Transactional
    public StatusResponseDto signUp(UserRequestDto userRequestDto) {
        //pwd 암호화 한 후 저장
        if(userRepository.existsByUsername(userRequestDto.username())){
          throw new IllegalArgumentException("유효하지 않은 정보입니다");
        }

        String encodedPwd = passwordEncoder.encode(userRequestDto.password());

        User user = User.builder()
            .username(userRequestDto.username())
            .password(encodedPwd)
            .phoneNumber(userRequestDto.phoneNumber())
            .aboutMe(userRequestDto.aboutMe())
            .nickName(userRequestDto.nickName())
            .profileUrl(userRequestDto.profileUrl())
            .role(UserRoleEnum.CUSTOMER)
            .build();

        userRepository.save(user);

        return new StatusResponseDto(201,"Created");
    }

  @Override
  @Transactional
  public StatusResponseDto login(HttpServletResponse response, UserLoginRequestDto request) {
    String username = request.username();
    String password = request.password();

    // 사용자 확인
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다.")
    );

    // 비밀번호 확인
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalArgumentException("입력한 정보가 틀립니다.");
    }
    // token 발급
    String accessToken = jwtUtil.createAccessToken(user.getUsername(), user.getRole());
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);

    return new StatusResponseDto(200,"OK");
  }

    @Override
    public void updateProfile(Long userId) {

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserProfile(Long userId) {
        return null;
    }

}
