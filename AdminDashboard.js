import React, { useContext, useEffect, useState } from "react";
import { PieChart, Pie, Cell, Tooltip, Legend } from "recharts";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { AuthContext } from "../context/AuthProvider";

const AdminDashboard = () => {
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();
  const COLORS = ["#3cb371", "#ff0000"]; 
  const [data, setData] = useState([]);


  useEffect(() => {
    if (!user) {
      console.error("User not found, redirecting...");
      navigate("/login");
    } else if (user.role !== "ADMIN") {
      console.error("Unauthorized access, redirecting...");
      navigate("/admin");
    }
  }, [user, navigate]);


  useEffect(() => {
    axios
      .get("http://localhost:8080/api/enrollments/admin/enrollment/stats")
      .then((response) => {
        setData([
          {
            name: "Enrolled",
            value: response.data.enrolledCount,
          },
          {
            name: "Cancelled",
            value: response.data.cancelledCount,
          },
        ]);
      })
      .catch((err) => console.error(err));
  }, []);

  return (

  
    <div className="container mt-aotu mb-4 ">
      <div className="row d-flex align-items-center justify-content-center vh-100">
        <div className="col-md-4">
          <div className="card shadow rounded-4 text-center p-3">
            <h5 className="card-title">Enrollment Status</h5>
            <div className="d-flex justify-content-center">
              <PieChart width={300} height={300}>
                <Pie
                  dataKey="value"
                  data={data}
                  cx="50%"
                  cy="50%"
                  outerRadius={100}
                  fill="#8884d8"
                  label
                >
                  {data.map((entry, index) => (
                    <Cell key={entry.name} fill={COLORS[index % COLORS.length]} />
                  ))}
                </Pie>
                <Tooltip />
                <Legend />
              </PieChart>
            </div>
          </div>
        </div>

     
      </div>
    </div>
   
  );
};

export default AdminDashboard;
