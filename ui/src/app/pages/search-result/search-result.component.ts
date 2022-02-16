import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Word } from './../../core/models/word';
import { DictionaryService } from './../../core/services/dictionary.service';
import { MessagingService } from './../../core/services/messaging.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {
  word!: string | any;
  response!: Word;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private dictionaryService: DictionaryService,
    private messagingService: MessagingService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.word = params.get('word');
      if (this.word === undefined || this.word === null) {
        this.messagingService.push('SearchResultComponent: Parameter undefined. Navigating to /home.');
        this.router.navigate(['/home']);
      }

      this.dictionaryService.searchWord(this.word).subscribe(r => {
        this.response = r.words[0];
      });
    });
  }
}
