import {OvpnClosedAdminHttpUtil} from "../../../common/util/OvpnClosedAdminHttpUtil";
import {VpnUserDto} from "../dto/LoginPayload";

const ovpnHttpUtil = new OvpnClosedAdminHttpUtil();

export class VpnUserController {
    async list(): Promise<Array<VpnUserDto>> {
        const response = await ovpnHttpUtil.get<Array<VpnUserDto>>('/vpnuser/list');
        return response.response!;
    }
}
