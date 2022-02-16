import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Filter } from '../models/filter';
import { DictionaryResponse } from './../models/dictionary-response';
import { HttpService } from './http.service';

@Injectable({
  providedIn: 'root'
})
export class DictionaryService {

  constructor(private httpService: HttpService) { }

  getWordOfTheDay(): Observable<DictionaryResponse> {
    return this.httpService.get('/word-of-the-day')
  }

  searchWord(searchPhrase: string): Observable<DictionaryResponse> {
    return this.httpService.get('/words/' + searchPhrase);
  }

  getRandomWord(filter?: Filter): Observable<DictionaryResponse> {
    const httpParams = new HttpParams();
    if (filter !== undefined) {
      httpParams.set('wordLength', '' + filter.wordLength);
      httpParams.set('beginsWith', '' + filter.beginsWith);
      httpParams.set('endsWith', '' + filter.endsWith);
      httpParams.set('contains', '' + filter.contains);
      httpParams.set('notContains', '' + filter.notContains);
    }
    return this.httpService.get('/random-word', httpParams);
  }
}
