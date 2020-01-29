package com.example.goalBackend.goal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/goal")
public class GoalController {

    private GoalService goalService;
    public GoalController(GoalService goalService){
        this.goalService = goalService;
    }

    @GetMapping(path = "/getAll", produces = {"application/json"})
    public ResponseEntity<Iterable<GoalEntity>> getAll() {

        return new ResponseEntity<>(goalService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path="/addGoal", produces = {"application/json"})
    public ResponseEntity<GoalEntity> addGoal(@RequestBody GoalEntity goalEntity){
        return new ResponseEntity<>(goalService.addGoal(goalEntity),HttpStatus.CREATED);
    }

    @PostMapping(path="/completeGoal/{id}", produces = {"application/json"})
    public ResponseEntity<GoalEntity> completeGoal(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(goalService.completeGoal(id),HttpStatus.OK);
    }

    @PostMapping(path="/failGoal/{id}", produces = {"application/json"})
    public ResponseEntity<GoalEntity> failGoal(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(goalService.failGoal(id),HttpStatus.OK);
    }

    @PostMapping(path="/moveGoal/{id}", produces = {"application/json"})
    public ResponseEntity<GoalEntity> moveGoal(@RequestBody GoalEntity goalEntity, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(goalService.moveGoal(id, goalEntity.getTargetDate()),HttpStatus.OK);
    }
}
