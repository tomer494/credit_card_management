import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DepositComponent } from './deposit/deposit.component';

const routes: Routes = [
  {path: '', redirectTo: '/deposit', pathMatch: 'full'},
  { path: 'deposit', component: DepositComponent/*, children: [
    { path: ':user_id/:card_name', component: UserComponent }
  ]*/ },
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
