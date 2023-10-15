import {Route, Routes, useNavigate} from "react-router-dom";
import {Menu, Space, Table} from "antd";
import {Page1} from "./Page1";
import {AdminController} from "../business/admin/controller/AdminController";
import {useEffect, useState} from "react";
import {VpnUserController} from "../business/account/controller/VpnUserController";
import {VpnUserDto} from "../business/account/dto/LoginPayload";

const vpnUserController = new VpnUserController();

export const Page2: React.FC = () => {

    const [vpnUserList, setVpnUserList] = useState<Array<VpnUserDto>>([]);
    const columns = [
        {
            title: 'uid',
            dataIndex: 'uid',
            key: 'uid',
        },
        {
            title: 'nickname',
            dataIndex: 'nickname',
            key: 'nickname',
        },
        {
            title: 'email',
            dataIndex: 'email',
            key: 'email',
        },
        {
            title: 'createdAt',
            dataIndex: 'createdAt',
            key: 'createdAt',
        },
        {
            title: 'status',
            dataIndex: 'status',
            key: 'status',
        },
        {
            title: 'Action',
            key: 'action',
            render: (_: unknown, record: VpnUserDto) => (
                <Space size="middle">
                    <a>Download .ovpn</a>
                </Space>
            ),
        },
    ];


    useEffect(() => {
        const checkTokenAndRedirectIfError = async () => {
            let vpnUserList = await vpnUserController.list();
            setVpnUserList(vpnUserList);
        };

        // Invoke the async function
        checkTokenAndRedirectIfError();
    }, []); // Empty dependency array means this useEffect runs once when component mounts.

    return (
        <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
            <Table dataSource={vpnUserList} columns={columns} rowKey={record => record.uid.toString()} />
        </div>
    );
};
