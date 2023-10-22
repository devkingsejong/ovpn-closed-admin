import {LoginPayload} from "../dto/LoginPayload";
import {LoginResponse} from "../dto/LoginResponse";
import {MyVpnHttpUtil} from "../../../common/util/MyVpnHttpUtil";

const myVpnHttpUtil = new MyVpnHttpUtil();

export class MyVpnController {
    async login(email: string, password: string): Promise<Boolean> {
        const payload: LoginPayload = {
            email,
            password
        };

        try {
            const response = await myVpnHttpUtil.post<LoginResponse, LoginPayload>('/vpnuser/login/auth', payload);

            if (!response.success) {
                return false;
            }

            sessionStorage.setItem("VpnAuthentication", response.response?.authenticationToken!);
            return true;
        } catch (error: any) {
            return false;
        }
    }

    async checkToken(): Promise<string> {
        const response = await myVpnHttpUtil.post<string, null>('/vpnuser/login/check', null);
        if (!response.success) {
            throw Error
        }
        return response.response!;
    }
}
