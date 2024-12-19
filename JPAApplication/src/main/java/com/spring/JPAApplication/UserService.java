package com.spring.JPAApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    JPARepo jpaRepo;

    public List<User> getAllUsers(){
        return jpaRepo.findAll();
    }

    public User getUserByEmail(String email){
        return jpaRepo.findByEmail(email);
    }


    public boolean ifEmailPresent(String email){
        return jpaRepo.emailExists(email);
    }

    public User registerUser(User user){
        if(ifEmailPresent(user.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        return jpaRepo.save(user);
    }

    public void deleteUser(User user){
        if(!ifEmailPresent(user.getEmail())){
            throw new IllegalArgumentException("User does not exist");
        }
        jpaRepo.deleteUser(user.getEmail());
    }

    public void updateUser(User user){
        int rowsUpdated = jpaRepo.updateUser(user.getEmail(), user.getId());
        if(rowsUpdated == 0){
            throw new RuntimeException("User with this id does no exist");
        }
    }

    public int deleteAllUsers(){
        int rowsUpdated = jpaRepo.deleteAllUsers();
        return rowsUpdated;
    }
}
