import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { IDepositApiRequest } from '../data-model/deposit';
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
  

  constructor(private depositService: DepositService) { 
    /*
    user_id = ?;
    card_id = ?;
    */
  }


  ngOnInit(): void {
  }

  onDeposit(depositData: IDepositApiRequest): void {
    depositData.authToken = "SOME_WIERD_STRING";
    depositData.user_id = this.user_id;
    depositData.card_id = this.card_id;
    this.depositService.deposit(depositData).then((result) => {
      if(result.actualResponse.ok) this.balance = result.actualResponse.balance;
      else this.error = result.actualResponse.errorMessage;
      this.message = result.actualResponse.message;
    });
  }

}
