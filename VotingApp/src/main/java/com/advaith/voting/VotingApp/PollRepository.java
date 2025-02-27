package com.advaith.voting.VotingApp;

import com.advaith.voting.VotingApp.Modal.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll,Long> {
}
