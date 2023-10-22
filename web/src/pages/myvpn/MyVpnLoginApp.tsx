import React, {useEffect, useState} from 'react';
import {Button, Form, Input, message} from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import {useNavigate} from "react-router-dom";
import {MyVpnAccountController} from "../../business/myvpn/controller/MyVpnAccountController";

const myVpnController = new MyVpnAccountController();
const MyVpnLoginApp: React.FC = () => {
    const navigate = useNavigate();

    const [email, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    useEffect(() => {
        sessionStorage.removeItem("VpnAuthentication")
    }, []);

    const handleLogin = async () => {
        let loginResult = await myVpnController.login(email, password);
        if (!loginResult) {
            message.error('Login Failed. Please check your credentials.'); // Message displayed
        } else {
            navigate('/myvpn/main');
        }
    };

    return (
        <div style={{ maxWidth: '300px', margin: '0 auto', paddingTop: '100px' }}>
            <h1 style={{ fontSize: '24px', textAlign: 'center', marginBottom: '40px' }}>
                MY VPN
            </h1>
            <Form>
                <Form.Item>
                    <Input
                        prefix={<UserOutlined />}
                        placeholder="email"
                        value={email}
                        onChange={e => setUsername(e.target.value)}
                    />
                </Form.Item>
                <Form.Item>
                    <Input.Password
                        prefix={<LockOutlined />}
                        type="password"
                        placeholder="password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    />
                </Form.Item>
                <Form.Item>
                    <Button type="primary" block onClick={handleLogin}>
                        Sign in
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
};

export default MyVpnLoginApp;
