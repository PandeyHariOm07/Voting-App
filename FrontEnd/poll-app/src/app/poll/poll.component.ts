import { Component, OnInit } from '@angular/core';
import { PollService } from '../poll.service';
import { Poll } from '../poll.models';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-poll',
  imports: [CommonModule, FormsModule],
  templateUrl: './poll.component.html',
  styleUrl: './poll.component.scss'
})
export class PollComponent implements OnInit{

  newPoll: Poll = {
    id: 0, question: '', options: [{
      options: '', voteCount:0
    },{
      options: '', voteCount:0
    }]
  }
  polls: Poll[] = [];
  constructor(private pollService: PollService){

  }
  ngOnInit(): void {
    this.loadPolls();
  }

  loadPolls(){
    this.pollService.getPoll().subscribe({
      next: (data) =>{
        this.polls = data;
      }
      ,
      error: (error) => {
        console.error("Error Fetching polls:", error);
      }
    })
  }

  createPolls(){
    this.pollService.createPoll(this.newPoll).subscribe({
      next:(createdPoll) => {
        this.polls.push(createdPoll);
        this.resetPoll();
      } ,
      error: (error)=>{
        console.error("Error Fetching Polls", error)
      }
    })
  }

  resetPoll(){
    this.newPoll = {
      id: 0, question: '', options: [
        {
        options: '', voteCount:0
      }
      ,{
        options: '', voteCount:0
      }
    ]
    };
  }

  trackByIndex(index: number){
    return index;
  }
}
