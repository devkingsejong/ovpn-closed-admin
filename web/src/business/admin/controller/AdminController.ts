import {LoginPayload} from "../dto/LoginPayload";
import {LoginResponse} from "../dto/LoginResponse";
import {OvpnClosedAdminHttpUtil} from "../../../common/util/OvpnClosedAdminHttpUtil";

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
}
