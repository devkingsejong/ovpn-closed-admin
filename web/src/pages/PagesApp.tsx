import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import {Menu} from "antd";
import {Page1} from "./Page1";
import {AdminController} from "../business/admin/controller/AdminController";
import {useEffect} from "react";
import {Page2} from "./Page2";

const adminController = new AdminController();

export const PagesApp: React.FC = () => {
    const navigate = useNavigate();
    const location = useLocation();

    // ... (다른 코드 부분들)

    // 현재 경로를 기반으로 메뉴 키를 결정합니다.
    const determineMenuKey =  location.pathname;

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
                <Menu mode="horizontal" defaultSelectedKeys={[determineMenuKey]}>
                    <Menu.Item key="/pages" onClick={() => navigate('')}>
                        Main
                    </Menu.Item>
                    <Menu.Item key="/pages/page2" onClick={() => navigate('page2')}>
                        Manage Vpn User
                    </Menu.Item>
                    <Menu.Item key="/pages/page3" onClick={() => navigate('page3')}>
                        Manage Admin
                    </Menu.Item>
                </Menu>
            </div>

            <div className="content-area">
                <div style={{padding: 15}}>
                    <Routes>
                        <Route index path="" element={<Page1 />} />
                        <Route path="page2" element={<Page2 />} />
                    </Routes>
                </div>
            </div>
        </div>
    );
};
