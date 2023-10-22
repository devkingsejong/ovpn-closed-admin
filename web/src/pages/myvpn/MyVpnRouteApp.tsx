import {Route, Routes} from "react-router-dom";
import MyVpnLoginApp from "./MyVpnLoginApp";


export const MyVpnRouteApp: React.FC = () => {

    return (
        <Routes>
            <Route path="login" element={<MyVpnLoginApp />} />
        </Routes>
    );
};
