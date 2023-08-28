package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.main.entites.User;
import com.main.repos.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogService logService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer userId) {
    	 if (userId <= 0) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Id must be a positive number!");
         }
         
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
        // Perform validation and registration logic
    	
      if (user.getEmail().contains(".com")) {
    	  if (userRepository.findByEmail(user.getEmail()).isPresent()) {
          	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mail already in use...");
          }
      }
        return userRepository.save(user);       
        
    }
    public boolean deactivateUser() {                          //for user
    	int uid=userRepository.getUserIdByMail("bb1@example.com");
        User user = userRepository.findById(uid).orElse(null);
        if (user != null) {
            user.setStatus(false); // Set status to 0 (deactivated)
            userRepository.save(user);
            logService.logUserDelete(uid);
            return true;
        } else {
            return false;
        }
    }

    public boolean deactivateUser(Integer userId) { 
    	int dltBy=userRepository.getUserIdByMail("bb1@example.com");  // for admin user id
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setStatus(false); // Set status to 0 (deactivated)
            userRepository.save(user);
            logService.logUserDelete(userId, dltBy);
            return true;
        } else {
            return false;
        }
    }
}
