import axios, {AxiosResponse} from "axios";

export class HttpUtil {
    private readonly baseUrl: string;

    constructor(baseUrl: string) {
        this.baseUrl = baseUrl;
    }

    public async get<T>(endpoint: string, headers: any): Promise<T> {
        try {
            const response: AxiosResponse<T> = await axios.get(`${this.baseUrl}${endpoint}`, {
                headers: headers
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    }

    public async post<T, U>(endpoint: string, data: U, headers: any): Promise<T> {
        try {
            const response: AxiosResponse<T> = await axios.post(`${this.baseUrl}${endpoint}`, data, {
                headers: headers
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    }
}
