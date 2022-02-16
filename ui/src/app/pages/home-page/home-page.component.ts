import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessagingService } from './../../core/services/messaging.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
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
