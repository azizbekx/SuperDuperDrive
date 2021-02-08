package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUserByUsername(username) == null;
    }

    public int createUser(User user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        //create user, hashedPassword keep in database
        return userMapper.insert(new User(
                null, user.getUsername(), encodedSalt,hashedPassword,user.getFirstname(),user.getLastname()));
    }

    public User getUserByUsername(String username){
        return userMapper.getUserByUsername(username);
    }

    public User getUserByUserId(Integer userId){
        return userMapper.getUserByUserId(userId);
    }

    public Integer getUserId(Authentication authentication){
        String username = (String) authentication.getName();
        User user =  userMapper.getUserByUsername(username);
        return user.getUserId();
    }

}
