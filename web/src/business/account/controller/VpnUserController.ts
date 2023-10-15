import {OvpnClosedAdminHttpUtil} from "../../../common/util/OvpnClosedAdminHttpUtil";
import {VpnUserDto} from "../dto/VpnUserDto";
import {LoginPayload} from "../../admin/dto/LoginPayload";
import {NewVpnUserForm} from "../dto/NewVpnUserForm";

const ovpnHttpUtil = new OvpnClosedAdminHttpUtil();

export class VpnUserController {
    async list(): Promise<Array<VpnUserDto>> {
        const response = await ovpnHttpUtil.get<Array<VpnUserDto>>('/vpnuser/list');
        return response.response!;
    }

    async addNewVpnUser(nickname: string, email: string, purePassword: string): Promise<string> {
        const payload: NewVpnUserForm = {
            nickname,
            email,
            purePassword
        };
        const response = await ovpnHttpUtil.post<string, NewVpnUserForm>('/vpnuser/create', payload)
        return response.response!;
    }
}
