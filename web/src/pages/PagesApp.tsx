import {Route, Routes, useNavigate} from "react-router-dom";
import {Menu} from "antd";
import {Page1} from "./Page1";
import {AdminController} from "../business/admin/controller/AdminController";
import {useEffect} from "react";

const adminController = new AdminController();

export const PagesApp: React.FC = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const checkTokenAndRedirectIfError = async () => {
            try {
                // Try to check the token using the adminController.
                await adminController.checkToken();
            } catch (error) {
                // If an error occurs (e.g., the token is invalid), redirect to /login.
                navigate('/login');
            }
        };

        // Invoke the async function
        checkTokenAndRedirectIfError();
    }, [navigate]); // Empty dependency array means this useEffect runs once when component mounts.

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
