import {Button, message, Space, Table} from "antd";
import {useEffect, useState} from "react";
import {VpnUserController} from "../../business/account/controller/VpnUserController";
import {VpnUserDto} from "../../business/account/dto/VpnUserDto";
import {UserAddModal} from "./modal/UserAddModal";
import {VpnController} from "../../business/vpn/controller/VpnController";
import {UUID} from "crypto";

const vpnUserController = new VpnUserController();
const vpnController = new VpnController();

export const ManageVpnUser: React.FC = () => {

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
                    <a onClick={async () => downloadOvpn(record.uid)}>Download .ovpn</a>
                </Space>
            ),
        },
    ];

    useEffect(() => {
        const checkTokenAndRedirectIfError = async () => {
            let vpnUserList = await vpnUserController.list();
            setVpnUserList(vpnUserList);
        };

        checkTokenAndRedirectIfError();
    }, []);

    const [isModalVisible, setIsModalVisible] = useState(false);

    const downloadOvpn = async (uid: UUID) => {
        try {
            let ovpnFile = await vpnController.getOvpnFile(uid)

            const blob = new Blob([ovpnFile], { type: 'application/octet-stream' });
            const link = document.createElement('a');
            link.setAttribute('download', 'config.ovpn');
            link.href = window.URL.createObjectURL(blob);
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        } catch (e) {
            message.error('ovpn file download failed.')
        }
    }

    const showModal = () => {
        setIsModalVisible(true);
    };

    const handleAddUser = async (values: { nickname: string; email: string; purePassword: string }) => {
        try {
            await vpnUserController.addNewVpnUser(values.nickname, values.email, values.purePassword)
            let vpnUserList = await vpnUserController.list();
            setVpnUserList(vpnUserList);
            setIsModalVisible(false);
        } catch (e) {
            setIsModalVisible(false);
            message.error('Create New Vpn User Failed. Please check your inputs.');
        }

    };

    return (
        <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
            <h2>Manage Vpn Users</h2>
            <h4 style={{color: "#ccc"}}>In this page, you can add vpn users.</h4>

            <Button type="primary" onClick={showModal} style={{ marginBottom: 16, float: "right" }}>
                new vpn user
            </Button>
            <Table dataSource={vpnUserList} columns={columns} rowKey={record => record.uid.toString()} />
            {isModalVisible && (
                <UserAddModal
                    visible={isModalVisible}
                    onCancel={() => setIsModalVisible(false)}
                    onAddUser={handleAddUser}
                />
            )}
        </div>
    );
};
