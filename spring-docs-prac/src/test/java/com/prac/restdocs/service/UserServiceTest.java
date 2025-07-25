package com.prac.restdocs.service;

import com.prac.restdocs.domain.User;
import com.prac.restdocs.domain.UserRequestDto;
import com.prac.restdocs.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)  // JUnit 5에서 Mockito 확장 사용
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("모든 사용자를 조회한다")
    void getAllUsers() {
        // given
        User user1 = User.builder()
                .name("홍길동")
                .email("hong@example.com")
                .build();
        User user2 = User.builder()
                .name("김철수")
                .email("kim@example.com")
                .build();

        List<User> expectedUsers = Arrays.asList(user1, user2);
        given(userRepository.findAll()).willReturn(expectedUsers);

        // when
        List<User> actualUsers = userService.getAllUsers();

        // then
        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers.get(0).getName(), actualUsers.get(0).getName());
        assertEquals(expectedUsers.get(1).getName(), actualUsers.get(1).getName());
    }

    @Test
    @DisplayName("새로운 사용자를 생성한다")
    void createUser() {
        // given
        UserRequestDto requestDto = UserRequestDto.builder()
                .name("홍길동")
                .email("hong@example.com")
                .build();

        User savedUser = User.builder()
                .name("홍길동")
                .email("hong@example.com")
                .build();

        given(userRepository.save(any(User.class))).willReturn(savedUser);

        // when
        User createdUser = userService.createUser(requestDto);

        // then
        assertNotNull(createdUser);
        assertEquals(requestDto.getName(), createdUser.getName());
        assertEquals(requestDto.getEmail(), createdUser.getEmail());
    }
}