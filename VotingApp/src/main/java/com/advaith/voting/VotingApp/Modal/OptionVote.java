package com.advaith.voting.VotingApp.Modal;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class OptionVote {
    private String options;
    private Long voteCount = 0L;
}
