import {SystemStatusResponse} from "../dto/SystemStatusResponse";
import {OvpnClosedAdminHttpUtil} from "../util/OvpnClosedAdminHttpUtil";

const ovpnHttpUtil = new OvpnClosedAdminHttpUtil();

export class CommonController {
    async systemStatus(): Promise<SystemStatusResponse> {
        const response = await ovpnHttpUtil.get<SystemStatusResponse>('/status');
        return response.response!;
    }
}
