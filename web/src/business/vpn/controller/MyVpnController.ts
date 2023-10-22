import {UUID} from "crypto";
import {MyVpnHttpUtil} from "../../../common/util/MyVpnHttpUtil";

const myVpnHttpUtil = new MyVpnHttpUtil();

export class MyVpnController {
    async getOvpnFile(): Promise<string> {
        const response = await myVpnHttpUtil.post<string, null>('/myvpn/download', null)
        return response.response!;
    }
}
