package com.advaith.voting.VotingApp.Impl;

import com.advaith.voting.VotingApp.Modal.OptionVote;
import com.advaith.voting.VotingApp.Modal.Poll;
import com.advaith.voting.VotingApp.Modal.Vote;
import com.advaith.voting.VotingApp.PollRepository;
import com.advaith.voting.VotingApp.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {
    private final PollRepository pollRepository;

    public PollServiceImpl(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @Override
    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    @Override
    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    @Override
    public void vote(Vote vote) {

        //Get Poll From DB
        Poll poll = pollRepository.findById(vote.getPollId()).orElseThrow(()-> new RuntimeException("Poll Not Found"));

        //Get All Option
        List<OptionVote> options =poll.getOptions();

        //If Index from vote is not valid, throw error
        if(vote.getOptionIndex() <0 || vote.getOptionIndex()>=options.size()){
            throw new IllegalArgumentException("Invalid Option Index");
        }
        //Get Selected Option
        OptionVote selectedOption = options.get(vote.getOptionIndex());

        //Increment vote for selected Option
        selectedOption.setVoteCount(selectedOption.getVoteCount()+1);

        //Save Incremented Option into the DB
        pollRepository.save(poll);
    }


}
