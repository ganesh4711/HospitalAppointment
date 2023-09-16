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
    public ResponseEntity<ApiResponse<UserDto>> getUser() {
        return
                new ResponseEntity<>(new ApiResponse<>(userService.getUserDetails()),HttpStatus.OK);
    }

    /**
     * @return all users
     */
    @GetMapping("/all")   //admin
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        return  new ResponseEntity<>(new ApiResponse<>(userService.getAllUsers()),HttpStatus.OK);
    }

    /**
     * @param offset   page start from 1
     * @param pagesize of the page
     * @return page of users
     */
    @GetMapping("/{offset}/{pagesize}")
    public ResponseEntity<ApiResponse<List<UserDto>>> retriveAllWithPagination(@PathVariable int offset, @PathVariable int pagesize) {
        if (offset < 1 || pagesize < 1) {
            throw new IllegalArgumentException();
        }

        Page<UserDto> allUserWithPagination = userService.findAllUserWithPagination((offset - 1), pagesize);
        if (offset <= allUserWithPagination.getTotalPages()) {

            Map<String, Object> meta = Map.of("pageNo", offset,
                    "pageSize", allUserWithPagination.getSize(),
                    "pageCount", allUserWithPagination.getNumberOfElements(),
                    "recordCount", allUserWithPagination.getTotalElements(),
                    "noOfPages", allUserWithPagination.getTotalPages());

            return  new ResponseEntity<>(new ApiResponse<>(allUserWithPagination.getContent()),HttpStatus.OK);

        } else
            return new ResponseEntity<>(new ApiResponse<>(null),HttpStatus.NO_CONTENT);
    }

    /**
     * @param offset   page
     * @param pagesize of the page
     * @param field    to be sorted
     * @return page of users
     */
    @GetMapping("/{offset}/{pagesize}/{field}")
    public ResponseEntity<ApiResponse<List<UserDto>>> retriveAllWithPaginationSorting(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {

        if (offset < 1 || pagesize < 1) {
            throw new IllegalArgumentException();
        }

        Page<UserDto> allUserWithPagination = userService.findAllUserWithPaginationSorting((offset - 1), pagesize, field);
        if (offset <= allUserWithPagination.getTotalPages()) {
            Map<String, Object> meta = Map.of("pageNo", offset,
                    "pageSize", allUserWithPagination.getSize(),
                    "pageCount", allUserWithPagination.getNumberOfElements(),
                    "recordCount", allUserWithPagination.getTotalElements(),
                    "noOfPages", allUserWithPagination.getTotalPages());

             return  new ResponseEntity<>(new ApiResponse<>(allUserWithPagination.getContent()),HttpStatus.OK);

        } else
            return new ResponseEntity<>(new ApiResponse<>(null),HttpStatus.NO_CONTENT);
    }

    /**
     * @param userId id of user
     * @return user info
     */
    @GetMapping("/{userId}") //admin
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable String userId) {

        Integer userIdValue = Integer.parseInt(userId);

        return new ResponseEntity<>(new ApiResponse<>(userService.getUserById(userIdValue)),HttpStatus.OK);

    }

    /**
     * @param userDto signup user
     * @return user info
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {

        return new ResponseEntity<>(new ApiResponse<>(userService.createUser(userDto)),HttpStatus.CREATED);


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
    public ResponseEntity<Void> deactivateUser() {

        boolean deactivated = userService.deactivateUser(); // here u_id take from authentication
        if (deactivated) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
