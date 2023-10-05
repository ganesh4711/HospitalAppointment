package com.main.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.main.RequestDto.UserDto;
import com.main.entites.User;
import com.main.customExceptions.BussinessException;
import com.main.repos.UserRepository;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;

    private LogService logService;

    private ModelMapper modelmapper;



    public UserDto convertEntityToDto(User user) {

        return modelmapper.map(user, UserDto.class);
    }
    public UserDto convertToDto(User user){
        return UserDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .password(user.getPassword())
                .phNo(user.getPhNo())
                .email(user.getEmail())
                .build();
    }

    private User convertToUser(UserDto userDto){
        return User.builder()
                .id(userDto.getUserId())
                .name(userDto.getName())
                .address(userDto.getAddress())
                .password(userDto.getPassword())
                .phNo(userDto.getPhNo())
                .email(userDto.getEmail())
                .build();
    }


    /**
     * @return user info
     */
    public UserDto getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail=authentication.getName();
        User user = userRepository.findByEmail(mail).get();

        return convertToDto(user);
    }

    /**
     * @return group of user
     */
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    /**
     * @param offSet   page
     * @param pagesize of the page
     * @return page
     */
    public Page<UserDto> findAllUserWithPagination(int offSet, int pagesize) {

        Page<User> userPage = userRepository.findAll(PageRequest.of(offSet, pagesize));

        // Check if the offset exceeds the total number of pages
        if (offSet >= userPage.getTotalPages()) {
            // Return an empty page or handle it as appropriate
            return Page.empty();
        }
        List<UserDto> userDtoList = userPage.getContent().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtoList, userPage.getPageable(), userPage.getTotalElements());
    }

    /**
     * @param offSet   page
     * @param pagesize-size of the page
     * @param field    to be sorted
     * @return page
     */
    public Page<UserDto> findAllUserWithPaginationSorting(int offSet, int pagesize, String field) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(offSet, pagesize, Sort.by(field)));
        List<UserDto> userDtoList = userPage.getContent().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtoList, userPage.getPageable(), userPage.getTotalElements());
    }



    /**
     * @param userId id of user
     * @return user info
     */
    public UserDto getUserById(int userId) {
        return convertEntityToDto(userRepository.findById(userId).get());
    }

    /**
     * @param userdto registered user
     * @return user info
     */
    public UserDto createUser(UserDto userdto) {

        if (userdto != null) {
            User user =convertToUser(userdto);
            userRepository.save(user);
            logService.logUserCreation(user.getId());
            return userdto;
        } else
            throw new BussinessException(HttpStatus.NOT_ACCEPTABLE, "user cannot be null...");


    }

    /**
     * @return user status
     */
    public boolean deactivateUser() {                          //for user
        int userId = 20; //authenticated user
        User user = userRepository.findById(userId).get();
        if (user.getStatus()) {
            user.setStatus(false); // Set status to 0 (deactivated)
            userRepository.save(user);
           // logService.logUserDelete(userId);
            return true;
        } else {
            throw new BussinessException("not a valid user");
        }

    }

    /**
     * @param userId id of user
     * @return user status
     */
    public boolean deactivateUser(Integer userId) {
        User user = userRepository.findById(userId).get();
        user.setStatus(false); // Set status to 0 (deactivated)
        userRepository.save(user);
        logService.logUserDelete(userId, 1);  //dltBy obtained from authenticated admin
        return true;

    }


}
