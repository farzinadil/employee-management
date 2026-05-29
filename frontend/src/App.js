import "./App.css";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import ListEmployeeComponent from "./components/ListEmployeeComponent";
import EmployeeComponent from "./components/EmployeeComponent";
import LoginComponent from "./components/LoginComponent";
import { isLoggedIn } from "./services/AuthService";

const PrivateRoute = ({ element }) => {
  return isLoggedIn() ? element : <Navigate to="/login" />;
};

function App() {
  return (
    <BrowserRouter>
      <HeaderComponent />

      <Routes>
        <Route path="/login" element={<LoginComponent />} />
        <Route path="/" element={<PrivateRoute element={<ListEmployeeComponent />} />} />
        <Route path="/employees" element={<PrivateRoute element={<ListEmployeeComponent />} />} />
        <Route path="/add-employee" element={<PrivateRoute element={<EmployeeComponent />} />} />
        <Route path="/edit-employee/:id" element={<PrivateRoute element={<EmployeeComponent />} />} />
      </Routes>

      <FooterComponent />
    </BrowserRouter>
  );
}

export default App;
