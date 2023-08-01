package com.example.goalBackend.goal;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Goal {

    @DocumentId
    private String id;

    private String description;
    private String targetDate;
    private String finishDate;
    private String status;
    private String userId;
}
