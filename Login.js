import React, { useState, useContext } from "react";
import {  useNavigate } from "react-router-dom";
import { AuthContext } from "../context/AuthProvider";
import axios from "axios";
import { FaUser, FaLock } from "react-icons/fa";

const Login = () => {
  const [credentials, setCredentials] = useState({ email: "", password: "" });
  const { setUser } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/users/login", credentials,
        {headers:{"Content-Type":"application/json"}}
      );
      const userData = response.data;
      console.log("Login Response:", userData); 
   
      if (typeof userData !== "object") {
        alert("Invalid JSON format received!");
        return;
      }

     setUser(userData);
     localStorage.setItem("user",JSON.stringify(userData));

     if(userData.role?.toUpperCase()==="ADMIN")
     {
      navigate("/admin")
     }else{
      navigate("/user/dashboard")
     }



    } catch (error) {
        console.error("Login failed", error.response?.data || error.message);
        alert("Login failed: " + (error.response?.data || error.message));
    }
  };

  return (
    <div className="container mt-2 mb-8">
      <div className="row d-flex align-items-center justify-content-center vh-100">
        <div className="col-md-6 col-lg-4">
          <div className="card shadow">
            <div className="card-body p-5">
              <h2 className="text-center mb-4">Welcome Back</h2>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <div className="input-group">
                    <span className="input-group-text">
                      <FaUser />
                    </span>
                    <input
                      type="email"
                      name="email"
                      placeholder="Email"
                      className="form-control"
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>
                <div className="mb-4">
                  <div className="input-group">
                    <span className="input-group-text">
                      <FaLock />
                    </span>
                    <input
                      type="password"
                      name="password"
                      placeholder="Password"
                      className="form-control"
                      onChange={handleChange}
                      required
                    />
                  </div>
                </div>
                <button 
                  type="submit" 
                  className="btn btn-primary w-100 py-2 mb-3"
                >
                  Login
                </button>
                <div className="text-center">
                  <small className="text-muted">
                    Don't have an account? <a href="/register" className="text-decoration-none">Sign up</a>
                  </small>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
