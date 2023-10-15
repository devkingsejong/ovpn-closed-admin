import {HttpUtil} from "./HttpUtil";
import {CommonResponse} from "../dto/CommonResponse";

export class OvpnClosedAdminHttpUtil {
    private readonly httpUtil: HttpUtil;

    constructor() {
        this.httpUtil = new HttpUtil(
            'http://localhost:8888'
        );
    }

    public async get<T>(endpoint: string): Promise<CommonResponse<T>> {
        try {
            const data: CommonResponse<T> = await this.httpUtil.get<CommonResponse<T>>(endpoint, this.getAuthHeader());
            return data;
        } catch (error) {
            throw error;
        }
    }

    public async post<T, U>(endpoint: string, data: U): Promise<CommonResponse<T>> {
        try {
            const responseData: CommonResponse<T> = await this.httpUtil.post<CommonResponse<T>, U>(endpoint, data, this.getAuthHeader());
            return responseData;
        } catch (error) {
            throw error;
        }
    }

    private getAuthHeader(): any {
        const token = sessionStorage.getItem("authenticationToken");
        return token ? {'Authorization': `${token}`} : {}
    }
}
