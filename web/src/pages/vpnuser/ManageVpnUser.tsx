import {Button, message, Space, Table} from "antd";
import {useEffect, useState} from "react";
import {VpnUserController} from "../../business/account/controller/VpnUserController";
import {VpnUserDto} from "../../business/account/dto/VpnUserDto";
import {UserAddModal} from "./modal/UserAddModal";

const vpnUserController = new VpnUserController();

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

        checkTokenAndRedirectIfError();
    }, []);

    const [isModalVisible, setIsModalVisible] = useState(false);

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
