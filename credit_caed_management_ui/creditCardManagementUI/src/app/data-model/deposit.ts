import { IApiRequest, IApiResponse } from "./api";

export interface IDepositApiRequest extends IApiRequest {
    user_id: number;
    card_id: number;
    amount: number;
}

export interface IDepositApiResponse extends IApiResponse {
    balance: number;
}