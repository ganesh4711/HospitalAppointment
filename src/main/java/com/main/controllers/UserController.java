package com.main.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.main.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @GetMapping   //user
    public UserDto getUser() {
        return userService.getUserDetails();
    }
    
    @GetMapping("/all")   //admin
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}") //admin
    public ApiResponse<UserDto> getUserById(@PathVariable String userId) {
    	
            Integer userIdValue = Integer.parseInt(userId);
        Map<String,Object> data=new HashMap<>();
        data.put("user",userService.getUserById(userIdValue));
            return new ApiResponse<>(HttpStatus.OK,"SUCCESS",data);
     
    }

    @PostMapping("/signup")
    public ApiResponse<UserDto> createUser(@RequestBody @Valid UserDto userDto) {

    	    UserDto createdUser = userService.createUser(userDto);
              Map<String,Object> data=new HashMap<>();
             data.put("user",createdUser);

            return new ApiResponse<>(HttpStatus.CREATED,"CREATED",data);
		
         
    }

    @DeleteMapping("/{userId}") //admin
    public ResponseEntity<Void> deactivateUser(@PathVariable Integer userId) {
        boolean deactivated = userService.deactivateUser(userId);
        if (deactivated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
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
