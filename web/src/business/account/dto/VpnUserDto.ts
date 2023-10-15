import {UUID} from "crypto";

export interface VpnUserDto {
    uid: UUID;
    nickcname: string;
    status: string;
    email: string;
    createdAt: Date;
    updatedAt: Date;
}
