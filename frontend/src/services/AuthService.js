import axios from "axios";

const AUTH_BASE_URL = "http://localhost:8080/api/v1/auth";

export const login = (username, password) => {
  return axios.post(`${AUTH_BASE_URL}/login`, { username, password });
};

export const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("username");
  localStorage.removeItem("role");
};

export const getCurrentUser = () => ({
  username: localStorage.getItem("username"),
  role: localStorage.getItem("role"),
});

export const getToken = () => localStorage.getItem("token");

export const isLoggedIn = () => !!getToken();

export const isManager = () => localStorage.getItem("role") === "MANAGER";
