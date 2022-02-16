import { Component, OnInit } from '@angular/core';
import { Word } from '../../core/models/word';
import { DictionaryService } from '../../core/services/dictionary.service';

@Component({
  selector: 'app-wotd',
  templateUrl: './wotd.component.html',
  styleUrls: ['./wotd.component.css']
})
export class WotdComponent implements OnInit {
  wotd: Word = {
    id: 1, word: 'undefined', meaning: 'undefined',
    wordLength: 0
  };

  constructor(private dictionaryService: DictionaryService) { }

  ngOnInit(): void {
    this.dictionaryService.getWordOfTheDay().subscribe(response => {
      this.wotd = response.words[0]
    })
  }

}
