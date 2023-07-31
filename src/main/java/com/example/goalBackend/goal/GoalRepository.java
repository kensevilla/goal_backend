package com.example.goalBackend.goal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends MongoRepository<Goal, String> {
}
