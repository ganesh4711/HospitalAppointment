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
    	int uid=2;     //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
        return convertEntityToDto(userRepository.findById(uid).get());
    }

    public UserDto getUserById(int uid){
        return convertEntityToDto(userRepository.findById(uid).get());
    }

    public UserDto createUser(UserDto userdto) {
        // Perform validation and registration logic
    	
    	  if (userdto.getName().isEmpty() || userdto.getName().length()==0) {
          	throw new BussinessException("name should not be empty...");
          }
    	  if (userdto!=null) {
    		  
    		 userRepository.save(convertDtoToEntity(userdto));
    		 return userdto;
            }
    	  else 
    		  throw new BussinessException(HttpStatus.NOT_ACCEPTABLE,"user cannot be null...");
               
        
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


}
