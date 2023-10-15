import {OvpnClosedAdminHttpUtil} from "../../../common/util/OvpnClosedAdminHttpUtil";
import {UUID} from "crypto";

const ovpnHttpUtil = new OvpnClosedAdminHttpUtil();

export class VpnController {
    async getOvpnFile(uid: UUID): Promise<string> {
        const response = await ovpnHttpUtil.post<string, null>('/vpn/download/'+ uid.toString(), null)
        return response.response!;
    }
}
