package com.example.goalBackend.goal;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class GoalService {

    private GoalFirebaseRepository goalFirebaseRepository = new GoalFirebaseRepository();



    private final static String COMPLETED = "Completed";
    private final static String FAIL = "Fail";

    Iterable<Goal> getAll() throws ExecutionException, InterruptedException {
        return goalFirebaseRepository.getGoalsByUserId("1");
    }

    Goal addGoal(Goal goal) throws ExecutionException, InterruptedException {
        return goalFirebaseRepository.addGoal(goal);
    }

    Goal completeGoal(String id) throws Exception {
        Goal goal = getGoal(id);
        goal.setStatus(COMPLETED);
        goal.setFinishDate(getCurrentDate());
        return goalFirebaseRepository.updateGoal(goal);
    }

    Goal failGoal(String id) throws Exception {
        Goal goal = getGoal(id);
        goal.setStatus(FAIL);
        goal.setFinishDate(getCurrentDate());
        return goalFirebaseRepository.updateGoal(goal);
    }

    Goal moveGoal(String id, String newTargetDate) throws Exception {
        Goal goal = getGoal(id);
        goal.setTargetDate(newTargetDate);
        return goalFirebaseRepository.updateGoal(goal);
    }

    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private Goal getGoal(String id) throws Exception {
        return goalFirebaseRepository.getGoal(id);
    }
}
