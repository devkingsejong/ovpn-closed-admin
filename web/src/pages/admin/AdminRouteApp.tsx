import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import LoginApp from "./LoginApp";
import {PagesApp} from "./PagesApp";


export const AdminRouteApp: React.FC = () => {

    return (
        <Routes>
            <Route path="login" element={<LoginApp />} />
            <Route path="pages/*" element={<PagesApp />} />
        </Routes>
    );
};
