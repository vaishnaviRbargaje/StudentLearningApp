import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const RegisterForm = () => {
  const [user, setUser] = useState({ username: "", email: "", password: "", confirmpassword: "" });
  const [error, setError] = useState({});
  const navigate = useNavigate();

  const validateInputs = (name, value) => {
    let errors = { ...error };

 
    if (name === "username") {
      if (!value.trim()) {
        errors.username = "Username is required";
      } else if (!/^[A-Z][a-z]{1,9}$/.test(value)) {
        errors.username = "Invalid username (First letter uppercase, 2-10 letters)";
      } else {
        delete errors.username;
      }
    }

    
    if (name === "email") {
      if (!value.trim()) {
        errors.email = "Email is required";
      } else if (!/\S+@\S+\.\S+/.test(value)) {
        errors.email = "Invalid Email format";
      } else {
        delete errors.email;
      }
    }


    if (name === "password") {
      if (!value) {
        errors.password = "Password is required";
      } else if (value.length < 6) {
        errors.password = "Password must be at least 6 characters";
      } else {
        delete errors.password;
      }
    }


    if (name === "confirmpassword") {
      if (!value) {
        errors.confirmpassword = "Confirm Password is required";
      } else if (value !== user.password) {
        errors.confirmpassword = "Passwords do not match";
      } else {
        delete errors.confirmpassword;
      }
    }

    setError(errors);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
    validateInputs(name, value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
 
    const validationErrors = Object.keys(error).length;
    if (validationErrors > 0) {
      alert("Please fix errors before submitting");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/api/users/register", user);
      alert(response.data);
      navigate("/home");
    } catch (error) {
      console.error("Registration failed", error.response?.data || error.message);
      alert("Registration failed: " + (error.response?.data || error.message));
    }
  };

  return (
    <div className="container mt-2 mb-8">
      <div className="row d-flex align-items-center justify-content-center vh-100">
        <div className="col-md-6">
          <div className="card shadow-lg p-4">
            <h2 className="text-center">Apply Now to start Upskilling
            </h2>
            <form onSubmit={handleSubmit}>
              {/* Username Field */}
              <div className="mb-3">
                <label className="form-label">Username</label>
                <input
                  type="text"
                  name="username"
                  className={`form-control ${error.username ? "is-invalid" : ""}`}
                  placeholder="Enter username"
                  value={user.username}
                  onChange={handleChange}
                  required
                />
                {error.username && <div className="invalid-feedback">{error.username}</div>}
              </div>

        
              <div className="mb-3">
                <label className="form-label">E-mail</label>
                <input
                  type="email"
                  name="email"
                  className={`form-control ${error.email ? "is-invalid" : ""}`}
                  placeholder="Enter Email"
                  value={user.email}
                  onChange={handleChange}
                  required
                />
                {error.email && <div className="invalid-feedback">{error.email}</div>}
              </div>

      
              <div className="mb-3">
                <label className="form-label">Password</label>
                <input
                  type="password"
                  name="password"
                  className={`form-control ${error.password ? "is-invalid" : ""}`}
                  placeholder="Enter password"
                  value={user.password}
                  onChange={handleChange}
                  required
                />
                {error.password && <div className="invalid-feedback">{error.password}</div>}
              </div>

         
              <div className="mb-3">
                <label className="form-label">Confirm Password</label>
                <input
                  type="password"
                  name="confirmpassword"
                  className={`form-control ${error.confirmpassword ? "is-invalid" : ""}`}
                  placeholder="Confirm password"
                  value={user.confirmpassword}
                  onChange={handleChange}
                  required
                />
                {error.confirmpassword && <div className="invalid-feedback">{error.confirmpassword}</div>}
              </div>

              <button type="submit" className="btn btn-primary w-100">Register</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegisterForm;
