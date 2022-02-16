import { Word } from './../../core/models/word';
import { MessagingService } from './../../core/services/messaging.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { DictionaryService } from 'src/app/core/services/dictionary.service';

@Component({
  selector: 'app-random-word',
  templateUrl: './random-word.component.html',
  styleUrls: ['./random-word.component.css']
})
export class RandomWordComponent implements OnInit {
  word!: Word;

  constructor(private route: ActivatedRoute, private router: Router,
    private dictionaryService: DictionaryService,
    private messagingService: MessagingService) { }

  ngOnInit(): void {
    this.dictionaryService.getRandomWord({wordLength: 5}).subscribe(resp => this.word = resp.words[0]);
  }

}
