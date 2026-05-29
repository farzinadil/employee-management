import React from "react";
import { useNavigate } from "react-router-dom";
import { getCurrentUser, isLoggedIn, logout } from "../services/AuthService";

const HeaderComponent = () => {
  const navigate = useNavigate();
  const { username, role } = getCurrentUser();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <header>
      <nav className="navbar navbar-expand-md navbar-dark bg-dark">
        <div className="d-flex w-100 align-items-center">
          <a href="/employees" className="navbar-brand ms-3">
            Employee Management Application
          </a>
          {isLoggedIn() && (
            <div className="ms-auto me-3 d-flex align-items-center gap-3">
              <span className="text-white">
                {username}
                <span
                  className={`badge ms-2 ${
                    role === "MANAGER" ? "bg-warning text-dark" : "bg-secondary"
                  }`}
                >
                  {role}
                </span>
              </span>
              <button
                className="btn btn-outline-light btn-sm"
                onClick={handleLogout}
              >
                Logout
              </button>
            </div>
          )}
        </div>
      </nav>
    </header>
  );
};

export default HeaderComponent;
