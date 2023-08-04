package com.example.goalBackend.user;

import com.example.goalBackend.goal.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path="/createUser", produces = {"application/json"})
    public ResponseEntity<String> createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping(path="/login", produces = {"application/json"})
    public ResponseEntity<String> login(@RequestBody User user) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(userService.getUser(user), HttpStatus.CREATED);
    }
}
