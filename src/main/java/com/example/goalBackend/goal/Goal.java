package com.example.goalBackend.goal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "goal")
public class Goal {
    @Id
    private String id;

    private String description;
    private String targetDate;
    private String finishDate;
    private String status;
    private String userId;
}
