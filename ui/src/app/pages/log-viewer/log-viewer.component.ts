import { MessagingService } from './../../core/services/messaging.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-log-viewer',
  templateUrl: './log-viewer.component.html',
  styleUrls: ['./log-viewer.component.css']
})
export class LogViewerComponent implements OnInit {
  logs = new Map<string, string>();

  constructor(private messagingService: MessagingService) { }

  ngOnInit(): void {
    this.messagingService.getMessages().subscribe(resp => this.logs = resp);
  }

}
