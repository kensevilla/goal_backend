package com.example.goalBackend.user;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final UserFirebaseRepository userFirebaseRepository = new UserFirebaseRepository();

    public String createUser(User user) throws ExecutionException, InterruptedException {
        if(userFirebaseRepository.getUserByUsername(user.getUsername()) != null) {
            return null;
        }
        return userFirebaseRepository.createUser(user);
    }

    public String getUser(User user) throws ExecutionException, InterruptedException {
        return userFirebaseRepository.getUser(user);
    }
}
