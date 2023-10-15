import {LoginPayload} from "../dto/LoginPayload";
import {LoginResponse} from "../dto/LoginResponse";
import {OvpnClosedAdminHttpUtil} from "../../../common/util/OvpnClosedAdminHttpUtil";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;

const ovpnHttpUtil = new OvpnClosedAdminHttpUtil();

export class AdminController {
    async login(email: string, password: string): Promise<Boolean> {
        const payload: LoginPayload = {
            email,
            password
        };

        try {
            const response = await ovpnHttpUtil.post<LoginResponse, LoginPayload>('/admin/login/auth', payload);

            if (!response.success) {
                return false;
            }

            sessionStorage.setItem("authenticationToken", response.response?.authenticationToken!);
            return true;
        } catch (error: any) {
            return false;
        }
    }

    async checkToken(): Promise<string> {
        const response = await ovpnHttpUtil.post<string, null>('/admin/login/check', null);
        if (!response.success) {
            throw Error
        }
        return response.response!;
    }
}
