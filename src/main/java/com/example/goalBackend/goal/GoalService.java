package com.example.goalBackend.goal;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GoalService {

    private GoalRepository goalRepository;
    public GoalService(GoalRepository goalRepository){
        this.goalRepository = goalRepository;
    }


    private final static String COMPLETED = "Completed";
    private final static String FAIL = "Fail";

    Iterable<Goal> getAll(){
        return goalRepository.findAll();
    }

    Goal addGoal(Goal goal){
        return goalRepository.save(goal);
    }

    Goal completeGoal(String id) throws Exception {
        Goal goal = getGoal(id);
        goal.setStatus(COMPLETED);
        goal.setFinishDate(getCurrentDate());
        return goalRepository.save(goal);
    }

    Goal failGoal(String id) throws Exception {
        Goal goal = getGoal(id);
        goal.setStatus(FAIL);
        goal.setFinishDate(getCurrentDate());
        return goalRepository.save(goal);
    }

    Goal moveGoal(String id, String newTargetDate) throws Exception {
        Goal goal = getGoal(id);
        goal.setTargetDate(newTargetDate);
        return goalRepository.save(goal);
    }

    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private Goal getGoal(String id) throws Exception {
        if (!goalRepository.existsById(id)) {
            throw new Exception("Goal id: " + id + " do not exist.");
        }
        else {
            return goalRepository.findById(id).get();
        }
    }
}
