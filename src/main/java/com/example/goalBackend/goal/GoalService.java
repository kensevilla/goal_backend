package com.example.goalBackend.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GoalService {
    @Autowired
    GoalRepository goalRepository;

    private final static String COMPLETED = "Completed";
    private final static String FAIL = "Fail";

    Iterable<GoalEntity> getAll(){
        return goalRepository.findAll();
    }

    GoalEntity addGoal(GoalEntity goalEntity){
        return goalRepository.save(goalEntity);
    }

    GoalEntity completeGoal(Long id) throws Exception {
        GoalEntity goalEntity = getGoal(id);
        goalEntity.setStatus(COMPLETED);
        goalEntity.setFinishDate(getCurrentDate());
        return goalRepository.save(goalEntity);
    }

    GoalEntity failGoal(Long id) throws Exception {
        GoalEntity goalEntity = getGoal(id);
        goalEntity.setStatus(FAIL);
        return goalRepository.save(goalEntity);
    }

    GoalEntity moveGoal(Long id, String newTargetDate) throws Exception {
        GoalEntity goalEntity = getGoal(id);
        goalEntity.setTargetDate(newTargetDate);
        return goalRepository.save(goalEntity);
    }

    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private GoalEntity getGoal(Long id) throws Exception {
        if (!goalRepository.existsById(id)) {
            throw new Exception("Goal id: " + id + " do not exist.");
        }
        else {
            return goalRepository.findById(id).get();
        }
    }
}
