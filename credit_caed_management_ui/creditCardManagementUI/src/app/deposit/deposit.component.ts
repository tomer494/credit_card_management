import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { IDepositApiRequest, IDepositApiResponse } from '../data-model/deposit';
import { DepositService } from '../services/deposit.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.scss']
})
export class DepositComponent implements OnInit {

  user_id = 1;
  card_id = 1;
  error: any;
  balance: any;
  message: any;
  amount = 9;
  subscription: Subscription | undefined;
  

  constructor(private depositService: DepositService) { 
    /*
    user_id = ?;
    card_id = ?;
    */
  }


  ngOnInit(): void {
    this.subscription = this.depositService.depositResponse
      .subscribe(
        (data: IDepositApiResponse) => {
          console.log('result: '+JSON.stringify(data));
          if(data.ok) 
          this.balance = data.balance;
          else this.error = data.errorMessage;
          this.message = data.message;
          console.log('balanse: '+this.balance+', error: '+this.error+', message: '+this.message)
        }
      );
  }

  onDeposit(depositData: IDepositApiRequest): void {
    depositData.authToken = "SOME_WIERD_STRING";
    depositData.user_id = this.user_id;
    depositData.card_id = this.card_id;
    this.depositService.deposit(depositData);
  }

}
