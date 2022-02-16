import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessagingService {
  private static messages = new Map<string, string>();

  constructor() { }

  push(msg: string): void {
    MessagingService.messages.set(new Date().toISOString(), msg);
  }

  getMessages(): Observable<Map<string, string>> {
    return of(MessagingService.messages);
  }
}
