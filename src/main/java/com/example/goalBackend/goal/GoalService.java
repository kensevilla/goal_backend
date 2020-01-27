package com.example.goalBackend.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
    @Autowired
    GoalRepository goalRepository;

    public Iterable<GoalEntity> getAll(){
        return goalRepository.findAll();
    }

    public GoalEntity addGoal(GoalEntity goalEntity){
        return goalRepository.save(goalEntity);
    }

    public GoalEntity updateGoal(GoalEntity goalEntity, Long id) throws Exception {
        if (!goalRepository.existsById(id)) {
            throw new Exception("Goal id: " + id + " do not exist.");
        } else {
            goalEntity.setId(id);
            return goalRepository.save(goalEntity);
        }
    }
}
