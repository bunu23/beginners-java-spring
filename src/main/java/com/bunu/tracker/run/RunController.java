package com.bunu.tracker.run;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepo runRepo;

    public RunController(RunRepo runRepo){
        this.runRepo=runRepo;

    }

@GetMapping
List<Run> findAll()
{
    return runRepo.findAll();
}


@GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
    Optional<Run> run=runRepo.findById(id);
    if(run.isEmpty()){
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return run.get();
}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create( @RequestBody Run run) {
        runRepo.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable Integer id) {
        runRepo.update(run,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepo.delete(id);
    }


}