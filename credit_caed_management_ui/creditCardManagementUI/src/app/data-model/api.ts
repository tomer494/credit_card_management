export interface IApiRequest {
    authToken: string;
}

export interface IApiResponse {
    errorMessage: string;
    message: string;
    ok: boolean;
}