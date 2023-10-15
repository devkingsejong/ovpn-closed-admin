import {CommonErrorField} from "./CommonErrorField";

export interface CommonResponse<T> {
    success: boolean;
    response: T | null;
    error: CommonErrorField | null;
}
