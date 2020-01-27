package com.example.goalBackend.goal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @PostMapping(path="/updateGoal/{id}", produces = {"application/json"})
    public ResponseEntity<GoalEntity> updateGoal(@RequestBody GoalEntity goalEntity, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(goalService.updateGoal(goalEntity, id),HttpStatus.OK);
    }
}
