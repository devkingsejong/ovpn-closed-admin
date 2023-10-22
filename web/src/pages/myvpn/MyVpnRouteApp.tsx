import {Route, Routes} from "react-router-dom";
import MyVpnLoginApp from "./MyVpnLoginApp";
import MyVpnMainApp from "./MyVpnMainApp";


export const MyVpnRouteApp: React.FC = () => {

    return (
        <Routes>
            <Route path="login" element={<MyVpnLoginApp />} />
            <Route path="main" element={<MyVpnMainApp />} />
        </Routes>
    );
};
