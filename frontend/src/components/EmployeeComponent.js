import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  createEmployee,
  getEmployeeById,
  updateEmployee,
} from "../services/EmployeeService";

const EmployeeComponent = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");

  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      getEmployeeById(id)
        .then((response) => {
          setFirstName(response.data.firstName);
          setLastName(response.data.lastName);
          setEmail(response.data.email);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [id]);

  const saveOrUpdateEmployee = (event) => {
    event.preventDefault();

    const employee = {
      firstName,
      lastName,
      email,
    };

    if (id) {
      updateEmployee(id, employee)
        .then(() => {
          navigate("/employees");
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      createEmployee(employee)
        .then(() => {
          navigate("/employees");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const pageTitle = () => {
    if (id) {
      return <h2 className="text-center">Update Employee</h2>;
    }

    return <h2 className="text-center">Add Employee</h2>;
  };

  return (
    <div>
      <br />
      <br />

      <div className="container">
        <div className="row">
          <div className="card col-md-6 offset-md-3">
            {pageTitle()}

            <div className="card-body">
              <form>
                <div className="form-group mb-2">
                  <label className="form-label">First Name:</label>
                  <input
                    type="text"
                    placeholder="Enter first name"
                    name="firstName"
                    className="form-control"
                    value={firstName}
                    onChange={(event) => setFirstName(event.target.value)}
                  />
                </div>

                <div className="form-group mb-2">
                  <label className="form-label">Last Name:</label>
                  <input
                    type="text"
                    placeholder="Enter last name"
                    name="lastName"
                    className="form-control"
                    value={lastName}
                    onChange={(event) => setLastName(event.target.value)}
                  />
                </div>

                <div className="form-group mb-2">
                  <label className="form-label">Email Id:</label>
                  <input
                    type="email"
                    placeholder="Enter email"
                    name="email"
                    className="form-control"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                  />
                </div>

                <button
                  className="btn btn-success"
                  onClick={saveOrUpdateEmployee}
                >
                  Submit
                </button>

                <button
                  className="btn btn-danger"
                  onClick={() => navigate("/employees")}
                  style={{ marginLeft: "10px" }}
                  type="button"
                >
                  Cancel
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EmployeeComponent;