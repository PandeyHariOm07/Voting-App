package com.advaith.voting.VotingApp;

import com.advaith.voting.VotingApp.Modal.Poll;
import com.advaith.voting.VotingApp.Modal.Vote;

import java.util.List;
import java.util.Optional;

public interface PollService {
    Poll createPoll(Poll poll);

    List<Poll> getAllPolls();

    Optional<Poll> getPollById(Long id);

    void vote(Vote vote);
}
