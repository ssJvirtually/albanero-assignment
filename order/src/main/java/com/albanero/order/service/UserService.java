package com.albanero.order.service;

import com.albanero.order.entity.User;
import com.albanero.order.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {


    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
      return userRepository.save(user);
    }


    public User getUser(String name){
        return userRepository.findByName(name);
    }


    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public User getUserByEmailAndName(String email,String name) {
        return userRepository.findByEmailAndName(email,name);
    }
}
