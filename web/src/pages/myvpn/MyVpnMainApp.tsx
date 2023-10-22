import React, {useEffect, useState} from 'react';
import {Button, Form, Input, message} from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import {useNavigate} from "react-router-dom";
import {MyVpnAccountController} from "../../business/myvpn/controller/MyVpnAccountController";
import {MyVpnController} from "../../business/vpn/controller/MyVpnController";

const myVpnController = new MyVpnController();
const MyVpnMainApp: React.FC = () => {

    const downloadOvpn = async () => {
        try {
            let ovpnFile = await myVpnController.getOvpnFile();

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

    return (
        <div style={{ maxWidth: '300px', margin: '0 auto', paddingTop: '100px' }}>
            <h1 style={{ fontSize: '24px', textAlign: 'center', marginBottom: '40px' }}>
                MY VPN
            </h1>

            <a onClick={async () => downloadOvpn()}>Download .ovpn</a>

        </div>
    );
};

export default MyVpnMainApp;
