import {Route, Routes, useNavigate} from "react-router-dom";
import {Menu} from "antd";
import {Page1} from "./Page1";

export const PagesApp: React.FC = () => {
    const navigate = useNavigate();

    return (
        <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
            <div className="title-area">
                <h1>OVPN-CLOSED-ADMIN</h1>
                <Menu mode="horizontal" defaultSelectedKeys={['1']}>
                    <Menu.Item key="1" onClick={() => navigate('')}>
                        Main
                    </Menu.Item>
                    <Menu.Item key="2" onClick={() => navigate('page2')}>
                        Manage Vpn User
                    </Menu.Item>
                    <Menu.Item key="3" onClick={() => navigate('page2')}>
                        Manage Admin
                    </Menu.Item>
                </Menu>
            </div>

            <div className="content-area">
                <div style={{padding: 15}}>
                    <Routes>
                        <Route index element={<Page1 />} />
                    </Routes>
                </div>
            </div>
        </div>
    );
};
