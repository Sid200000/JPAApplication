package com.spring.JPAApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        return userService.getAllUsers();
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registered Successfully");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        try {
            userService.deleteUser(user);
            return ResponseEntity.ok("Deleted Successfully");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            userService.updateUser(user);
            return ResponseEntity.ok("Successfully Updated");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("User does not Exists!");
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email){
        try{
            User user = userService.getUserByEmail(email);
            if(user == null) return ResponseEntity.ok("User with this email does not exist");
//            System.out.println(user.getId()+" "+user.getEmail()+" "+user.getUsername()!=null);
            return ResponseEntity.ok().body(user);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("No user with this email !!");
        }
    }

    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<?> deleteAllUsers(){
        try{
            if(userService.deleteAllUsers() == 0)
                return ResponseEntity.ok().body("Successfully deleted all users");
            return ResponseEntity.ok("Empty Database");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
