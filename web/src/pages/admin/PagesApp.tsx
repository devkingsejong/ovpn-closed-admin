import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import {Menu} from "antd";
import {StatusPage} from "./subpages/status/StatusPage";
import {AdminController} from "../../business/admin/controller/AdminController";
import {useEffect} from "react";
import {ManageVpnUserPage} from "./subpages/vpnuser/ManageVpnUserPage";

const adminController = new AdminController();

export const PagesApp: React.FC = () => {
    const navigate = useNavigate();
    const location = useLocation();

    const determineMenuKey =  location.pathname;

    useEffect(() => {
        const checkTokenAndRedirectIfError = async () => {
            try {
                await adminController.checkToken();
            } catch (error) {
                navigate('/admin/login');
            }
        };

        checkTokenAndRedirectIfError();
    }, [navigate]);

    return (
        <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
            <div className="title-area">
                <h1>OVPN-CLOSED-ADMIN</h1>
                <Menu mode="horizontal" defaultSelectedKeys={[determineMenuKey]}>
                    <Menu.Item key="/admin/pages" onClick={() => navigate('')}>
                        Main
                    </Menu.Item>
                    <Menu.Item key="/admin/pages/manage-vpn-user" onClick={() => navigate('manage-vpn-user')}>
                        Manage Vpn User
                    </Menu.Item>
                    <Menu.Item key="/admin/pages/page3" onClick={() => navigate('page3')}>
                        Manage Admin
                    </Menu.Item>
                </Menu>
            </div>

            <div className="content-area">
                <div style={{padding: 15}}>
                    <Routes>
                        <Route index path="" element={<StatusPage />} />
                        <Route path="manage-vpn-user" element={<ManageVpnUserPage />} />
                    </Routes>
                </div>
            </div>
        </div>
    );
};
