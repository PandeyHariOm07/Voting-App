package com.advaith.voting.VotingApp.controllers;

import com.advaith.voting.VotingApp.Modal.Poll;
import com.advaith.voting.VotingApp.Modal.Vote;
import com.advaith.voting.VotingApp.PollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll){
        return pollService.createPoll(poll);
    }

    @GetMapping
    public List<Poll> getAllPolls(){
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Poll>> getPoll(@PathVariable Long id){
        if(pollService.getPollById(id).isPresent())
            return new ResponseEntity<>(pollService.getPollById(id), HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/vote")
    public ResponseEntity<String> vote(@RequestBody Vote vote){
        pollService.vote(vote);
        return new ResponseEntity<>("Successfully Voted",HttpStatus.CREATED);
    }
}
