package com.main.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.main.RequestDto.UserDto;
import com.main.entites.User;
import com.main.globalExcp.BussinessException;
import com.main.repos.UserRepository;

@Service   
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogService logService;
    @Autowired
	private ModelMapper modelmapper;
	
	public UserDto convertEntityToDto(User user ) {
	
		return modelmapper.map(user, UserDto.class);
	}
	public User convertDtoToEntity(UserDto userdto ) {
		
		return modelmapper.map(userdto, User.class);
	}
    
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
        		.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public UserDto getUserDetails() {
    	int userId=244;     //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
       User user=userRepository.findById(userId).get();

        return convertEntityToDto(user);
    }

    public UserDto getUserById(int userId){
        return convertEntityToDto(userRepository.findById(userId).get());
    }

    public UserDto createUser(UserDto userdto) {

    	  if (userdto!=null) {
    		  User user=convertDtoToEntity(userdto);
    		 userRepository.save(user);
              logService.logUserCreation(user.getUserId());
    		 return userdto;
            }
    	  else 
    		  throw new BussinessException(HttpStatus.NOT_ACCEPTABLE,"user cannot be null...");
               
        
    }
    public boolean deactivateUser() {                          //for user
    	int userId=20; //authenticated user
        User user = userRepository.findById( userId ).get();
        if (user != null) {
            user.setStatus(false); // Set status to 0 (deactivated)
            userRepository.save(user);
            logService.logUserDelete(userId);
            return true;
        } else {
            return false;
        }
    }

    public boolean deactivateUser(Integer userId) { 
        User user = userRepository.findById(userId).get();
            user.setStatus(false); // Set status to 0 (deactivated)
            userRepository.save(user);
            logService.logUserDelete(userId,1);  //dltBy obtained from authenticated admin
            return true;
       
    }


}
