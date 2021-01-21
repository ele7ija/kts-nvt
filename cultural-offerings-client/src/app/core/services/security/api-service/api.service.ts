import { HttpClient, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, filter, map } from 'rxjs/operators';
import { PageableRequest } from 'src/app/core/model/pageable-request';

export enum RequestMethod {
  Get = 'GET',
  Head = 'HEAD',
  Post = 'POST',
  Put = 'PUT',
  Delete = 'DELETE',
  Options = 'OPTIONS',
  Patch = 'PATCH'
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  headers = new HttpHeaders({
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) {
  }

  getByPage(path: string, pageableRequest: PageableRequest): Observable<any> {
    return this.get(
      `${path}?page=${pageableRequest.page}&size=${pageableRequest.size}&sort=${pageableRequest.sort},${pageableRequest.sortOrder}`);
  }

  get(path: string, customHeaders?: HttpHeaders): Observable<any> {
    return this.request(path, null, RequestMethod.Get, customHeaders);
  }

  post(path: string, body?: any, customHeaders?: HttpHeaders): Observable<any> {
    return this.request(path, body, RequestMethod.Post, customHeaders);
  }

  postFile(path: string, body: any): Observable<any> {
    return this.http.post(path, body);
  }

  put(path: string, body: any): Observable<any> {
    return this.request(path, body, RequestMethod.Put);
  }

  delete(path: string, body?: any): Observable<any> {
    return this.request(path, body, RequestMethod.Delete);
  }

  request(path: string, body: any, method = RequestMethod.Post, custemHeaders?: HttpHeaders): Observable<any> {
    const req = new HttpRequest(method, path, body, {
      headers: custemHeaders || this.headers,
    });

    return this.http.request(req)
      .pipe(filter(response => response instanceof HttpResponse))
      .pipe(map((response: HttpResponse<any>) => response.body))
      .pipe(catchError(error => this.checkError(error)));
  }


  // Display error if logged in, otherwise redirect to IDP
  private checkError(error: any): any {
    if (error && error.status === 401) {
      // this.redirectIfUnauth(error);
    } else {
      // this.displayError(error);
    }
    throw error;
  }

}
