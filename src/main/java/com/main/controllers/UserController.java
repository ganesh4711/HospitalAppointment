package com.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.main.entites.User;
import com.main.service.LogService;
import com.main.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @GetMapping("/all/users")   //admin
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}") //admin
    public User getUserById(@PathVariable String userId) {
    	try {
            Integer userIdValue = Integer.parseInt(userId);
           
            User user = userService.getUserById(userIdValue);
            if (user != null) {
                return user;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
            }
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User Id format!", e);
        }
    }

    @PostMapping("/user-create")
    public User createUser(@RequestBody User user) {
    	try {

            User createdUser = userService.createUser(user);
            logService.logUserCreation(createdUser.getUserId());
            return createdUser;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User data!", e.getCause());
		}
         
    }

    @DeleteMapping("user/delete/{userId}") //admin
    public ResponseEntity<Void> deactivateUser(@PathVariable Integer userId) {
        boolean deactivated = userService.deactivateUser(userId);
        if (deactivated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("user/delete") //user can delete his details
    public ResponseEntity<Void> deactivateUserr() { 
    
        boolean deactivated = userService.deactivateUser(); // here u_id take from authentication
        if (deactivated) {
            return ResponseEntity.noContent().build();
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
