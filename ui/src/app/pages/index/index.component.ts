import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessagingService } from './../../core/services/messaging.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  word: string | undefined;
  formGroup: FormGroup;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private messagingService: MessagingService) {
    this.formGroup = new FormGroup({
      searchPhrase: new FormControl('')
    });
  }

  ngOnInit(): void {
  }

  search() {
    this.word = this.formGroup.value.searchPhrase;
    this.messagingService.push('HomePageComponent: search(' + this.word + ')');
    if (this.word !== undefined && this.word.trim() !== '') {
      this.router.navigate(['/search', this.word]);
    }
  }

}
