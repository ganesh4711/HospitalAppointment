package com.main.hospital;

import com.main.RequestDto.UserDto;
import com.main.entites.User;
import com.main.repos.UserRepository;
import com.main.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelmapper;

    @InjectMocks
    private UserService service;
     User user1 = User.builder()
                .id(1).email("ganesh@img.com").phNo("88800551789").address("vijayawada").name("ganesh").build();
       User user2 = User.builder()
                .id(2).email("sai@img.com").phNo("741474123").address("hyd").name("sai").build();

    @Test
    void testGetAllUsers() {
        // Mock data

        List<User> mockUserList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(mockUserList);

//        when(modelmapper.map(Mockito.any(User.class), Mockito.eq(UserDto.class)))
//                .thenAnswer(method -> {
//                    User inputUser = method.getArgument(0);
//                    return service.convertToDto(inputUser);
//                });

        List<UserDto> result = service.getAllUsers();
        System.out.println(result);
        assertEquals(2, result.size());
        assertEquals(user2.getAddress(),result.get(1).getAddress());

        Mockito.verify(userRepository, Mockito.times(1)).findAll();

    }


    @Test
    void testGetUserDetails() {
        // Mock authentication and user data
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("ganesh@img.com");

        // Mock user data in the repository

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user1));



        // Call the method you want to test
        UserDto result = service.getUserDetails();

        assertNotNull(result);
        assertEquals("ganesh", result.getName());

        // Verify that the necessary methods were called
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(Mockito.anyString());
    }
}

