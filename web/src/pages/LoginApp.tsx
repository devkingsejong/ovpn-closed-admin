import React, { useState } from 'react';
import { Button, Form, Input } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';

const LoginApp: React.FC = () => {
    const [email, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    const handleLogin = () => {
        console.log("Logging in with:", email, password);
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
