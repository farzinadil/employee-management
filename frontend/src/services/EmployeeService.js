import axios from "axios";
import { getToken } from "./AuthService";

const EMPLOYEE_BASE_REST_API_URL = "http://localhost:8080/api/v1/employees";

const authHeaders = () => ({
  headers: { Authorization: `Bearer ${getToken()}` },
});

export const listEmployees = () =>
  axios.get(EMPLOYEE_BASE_REST_API_URL, authHeaders());

export const createEmployee = (employee) =>
  axios.post(EMPLOYEE_BASE_REST_API_URL, employee, authHeaders());

export const getEmployeeById = (employeeId) =>
  axios.get(`${EMPLOYEE_BASE_REST_API_URL}/${employeeId}`, authHeaders());

export const updateEmployee = (employeeId, employee) =>
  axios.put(`${EMPLOYEE_BASE_REST_API_URL}/${employeeId}`, employee, authHeaders());

export const deleteEmployee = (employeeId) =>
  axios.delete(`${EMPLOYEE_BASE_REST_API_URL}/${employeeId}`, authHeaders());
