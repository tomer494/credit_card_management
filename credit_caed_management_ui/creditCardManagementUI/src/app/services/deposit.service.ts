import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, Subject, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { IDepositApiRequest, IDepositApiResponse } from '../data-model/deposit';
import { IApiResponse } from '../data-model/api';
import { DepositComponent } from '../deposit/deposit.component';

@Injectable({
  providedIn: 'root'
})
export class DepositService {

  url = "http://localhost:8080/api/deposit";
  error = new Subject<string>();
  depositResponse = new Subject<IDepositApiResponse>();

  constructor(private http: HttpClient) {}

  /*public deposit(params: IDepositApiRequest){
    this.http.post(this.url, params, {
      observe: 'response',
      responseType: 'json',
    }).subscribe((response: HttpResponse<any>) => {
      this.depositResponse = response.body;
    },
    error => {
      this.error.next(error.message);
    });
  }

  getDepositResponse() {
    return this.depositResponse;
  }*/

  public async deposit(params: IDepositApiRequest): Promise<{ actualResponse: IDepositApiResponse }> {
    const response = await this.post(params);
    return { actualResponse: (response as IDepositApiResponse) };
  }

  public post(body: IDepositApiRequest): Promise<IApiResponse> {
    
    return new Promise((resolve, reject) => {
      this.http.post(this.url, body, {
        observe: 'response',
        responseType: 'json',
      }).subscribe((response: HttpResponse<any>) => {
        console.log('body: '+JSON.stringify(body))
        this.depositResponse.next(response.body);
      },
      error => {
        this.error.next(error.message);
      });
    });
  }

}