import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from './../../../environments/environment';
import { MessagingService } from './messaging.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  private url = `${environment.apiEndpoint}${environment.apiBasePath}`;

  constructor(private httpClient: HttpClient,
    private messagingService: MessagingService) { }

  get(path: string, params: HttpParams = new HttpParams()): Observable<any> {

    return this.httpClient
      .get(`${this.url}${path}`, { params })
      .pipe(c => this.responseLogger(path, params, c))
      .pipe(catchError(error => throwError(error.error)));
  }

  private responseLogger(url: string, params: HttpParams, response: Observable<any>): Observable<any> {
    response.subscribe(r => {
      const resp = JSON.stringify(r);
      this.messagingService.push('HttpService: ' + `${this.url}` + url + params + ': ' + this.truncateResponse(resp));
    });
    return response;
  }

  private truncateResponse(resp: string): string {
    const truncated = resp.substring(0, Math.min(resp.length, 100));
    return (resp.length > 100) ? truncated + '... (truncated ' + resp.length + ' bytes)' : resp;
  }
}
