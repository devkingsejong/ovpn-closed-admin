import React, {useEffect, useState} from 'react';
import {Button, Form, Input, message} from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import {OvpnClosedAdminHttpUtil} from "../common/util/OvpnClosedAdminHttpUtil";
import {AdminController} from "../business/admin/controller/AdminController";
import {useNavigate} from "react-router-dom";

const adminController = new AdminController();
const LoginApp: React.FC = () => {
    const navigate = useNavigate();

    const [email, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    useEffect(() => {
        sessionStorage.removeItem("authenticationToken")
    }, []);

    const handleLogin = async () => {
        let loginResult = await adminController.login(email, password);
        if (!loginResult) {
            message.error('Login Failed. Please check your credentials.'); // Message displayed
        } else {
            navigate('/pages');
        }
    };

    return (
        <div style={{ maxWidth: '300px', margin: '0 auto', paddingTop: '100px' }}>
            <h1 style={{ fontSize: '24px', textAlign: 'center', marginBottom: '40px' }}>
                OVPN-CLOSED-ADMIN
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

export default LoginApp;
