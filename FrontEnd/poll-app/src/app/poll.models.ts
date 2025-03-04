export interface Poll {
    id: number;
    question: string;
    options: OptionVote[];
}

export interface OptionVote{
    options: string;
    voteCount: number;
}
