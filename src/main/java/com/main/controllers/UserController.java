package com.main.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.main.ApiResponse;
import com.main.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.main.RequestDto.UserDto;
import com.main.service.LogService;
import com.main.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    /**
     * @return user
     */
    @GetMapping   //user
    public UserDto getUser() {
        return userService.getUserDetails();
    }

    /**
     * @return all users
     */
    @GetMapping("/all")   //admin
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * @param offset   page start from 1
     * @param pagesize of the page
     * @return page of users
     */
    @GetMapping("/{offset}/{pagesize}")
    public ApiResponse<UserDto> retriveAllWithPagination(@PathVariable int offset, @PathVariable int pagesize) {
        if (offset < 1 || pagesize < 1) {
            throw new IllegalArgumentException();
        }

        Page<UserDto> allUserWithPagination = userService.findAllUserWithPagination((offset - 1), pagesize);
        if (offset <= allUserWithPagination.getTotalPages()) {
            Map<String, Object> data = new HashMap<>();
            data.put("user", allUserWithPagination.getContent());
            Map<String, Object> meta = Map.of("pageNo", offset,
                    "pageSize", allUserWithPagination.getSize(),
                    "pageCount", allUserWithPagination.getNumberOfElements(),
                    "recordCount", allUserWithPagination.getTotalElements(),
                    "noOfPages", allUserWithPagination.getTotalPages());

            return new ApiResponse<>(HttpStatus.OK, "SUCCESS", data, meta);

        } else
            return new ApiResponse<>(HttpStatus.NO_CONTENT, "Empty page");
    }

    /**
     * @param offset   page
     * @param pagesize of the page
     * @param field    to be sorted
     * @return page of users
     */
    @GetMapping("/{offset}/{pagesize}/{field}")
    public ApiResponse<UserDto> retriveAllWithPaginationSorting(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {

        if (offset < 1 || pagesize < 1) {
            throw new IllegalArgumentException();
        }

        Page<UserDto> allUserWithPagination = userService.findAllUserWithPaginationSorting((offset - 1), pagesize, field);
        if (offset <= allUserWithPagination.getTotalPages()) {
            Map<String, Object> data = new HashMap<>();
            data.put("user", allUserWithPagination.getContent());
            Map<String, Object> meta = Map.of("pageNo", offset,
                    "pageSize", allUserWithPagination.getSize(),
                    "pageCount", allUserWithPagination.getNumberOfElements(),
                    "recordCount", allUserWithPagination.getTotalElements(),
                    "noOfPages", allUserWithPagination.getTotalPages());

            return new ApiResponse<>(HttpStatus.OK, "SUCCESS", data, meta);

        } else
            return new ApiResponse<>(HttpStatus.NO_CONTENT, "Empty page");
    }

    /**
     * @param userId id of user
     * @return user info
     */
    @GetMapping("/{userId}") //admin
    public ApiResponse<UserDto> getUserById(@PathVariable String userId) {

        Integer userIdValue = Integer.parseInt(userId);
        Map<String, Object> data = new HashMap<>();
        data.put("user", userService.getUserById(userIdValue));
        return new ApiResponse<>(HttpStatus.OK, "SUCCESS", data);

    }

    /**
     * @param userDto signup user
     * @return user info
     */
    @PostMapping("/signup")
    public ApiResponse<UserDto> createUser(@RequestBody @Valid UserDto userDto) {

        UserDto createdUser = userService.createUser(userDto);
        Map<String, Object> data = new HashMap<>();
        data.put("user", createdUser);

        return new ApiResponse<>(HttpStatus.CREATED, "CREATED", data);


    }

    /**
     * @param userId id of user
     * @return status
     */
    @DeleteMapping("/{userId}") //admin
    public ResponseEntity<Void> deactivateUser(@PathVariable Integer userId) {
        boolean deactivated = userService.deactivateUser(userId);
        if (deactivated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @return user status
     */
    @DeleteMapping            //user can delete his details
    public ResponseEntity<Void> deactivateUserr() {

        boolean deactivated = userService.deactivateUser(); // here u_id take from authentication
        if (deactivated) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
