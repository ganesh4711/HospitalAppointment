package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.main.entites.User;
import com.main.globalExcp.BussinessException;
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
             throw new BussinessException("User Id must be a positive number!");
         }
         
        return userRepository.findById(userId).get();
    }

    public User createUser(User user) {
        // Perform validation and registration logic
    	
    	  if (user.getName().isEmpty() || user.getName().length()==0) {
          	throw new BussinessException("name should not be empty...");
          }
      
        return userRepository.save(user);       
        
    }
    public boolean deactivateUser() {                          //for user
    	int uid=20; //authenticated user
        User user = userRepository.findById( uid ).get(); 
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
        User user = userRepository.findById(userId).get();
            user.setStatus(false); // Set status to 0 (deactivated)
            userRepository.save(user);
            logService.logUserDelete(userId, dltBy);  //dltBy obtained from authenticated admin
            return true;
       
    }

	public User getUserDetails() {
		int id=8;  //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
		return userRepository.findById(id).get();
	}
}
