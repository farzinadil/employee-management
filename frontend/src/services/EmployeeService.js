import axios from "axios";

// Base URL for the Spring Boot backend employee API.
// React will call these endpoints through Axios.
const EMPLOYEE_BASE_REST_API_URL = "http://localhost:8080/api/v1/employees";

// GET all employees.
export const listEmployees = () => {
  return axios.get(EMPLOYEE_BASE_REST_API_URL);
};

// POST create new employee.
export const createEmployee = (employee) => {
  return axios.post(EMPLOYEE_BASE_REST_API_URL, employee);
};

// GET employee by ID.
export const getEmployeeById = (employeeId) => {
  return axios.get(`${EMPLOYEE_BASE_REST_API_URL}/${employeeId}`);
};

// PUT update existing employee.
export const updateEmployee = (employeeId, employee) => {
  return axios.put(`${EMPLOYEE_BASE_REST_API_URL}/${employeeId}`, employee);
};

// DELETE employee by ID.
export const deleteEmployee = (employeeId) => {
  return axios.delete(`${EMPLOYEE_BASE_REST_API_URL}/${employeeId}`);
}
