package com.example.goalBackend.user;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @DocumentId
    private String id;

    private String username;
    private String password;
}