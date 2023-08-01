package com.example.goalBackend.goal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/goal")
public class GoalController {

    private GoalService goalService;
    public GoalController(GoalService goalService){
        this.goalService = goalService;
    }

    @GetMapping(path = "/getAll", produces = {"application/json"})
    public ResponseEntity<Iterable<Goal>> getAll() throws ExecutionException, InterruptedException {

        return new ResponseEntity<>(goalService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path="/addGoal", produces = {"application/json"})
    public ResponseEntity<Goal> addGoal(@RequestBody Goal goal) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(goalService.addGoal(goal),HttpStatus.CREATED);
    }

    @PostMapping(path="/completeGoal/{id}", produces = {"application/json"})
    public ResponseEntity<Goal> completeGoal(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(goalService.completeGoal(id),HttpStatus.OK);
    }

    @PostMapping(path="/failGoal/{id}", produces = {"application/json"})
    public ResponseEntity<Goal> failGoal(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(goalService.failGoal(id),HttpStatus.OK);
    }

    @PostMapping(path="/moveGoal/{id}", produces = {"application/json"})
    public ResponseEntity<Goal> moveGoal(@RequestBody Goal goal, @PathVariable String id) throws Exception {
        return new ResponseEntity<>(goalService.moveGoal(id, goal.getTargetDate()),HttpStatus.OK);
    }
}
