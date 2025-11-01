import React, {  useContext, useEffect, useState } from "react";

import axios from "axios";
import { AuthContext } from "../context/AuthProvider";
import { useNavigate } from "react-router-dom";
import Navbar from "../Component/common/NavBar";



const UserDashboard=()=>
{
    const[courses,setCourses]=useState([]);
    const{user,loading}=useContext(AuthContext);
    const navigate=useNavigate();
      const [searchTerm, setSearchTerm] = useState("");
    

    useEffect(() => {

      if(!loading){
        if(!user){
            navigate("/login");
            return;

        }
        if(user.role?.toUpperCase()==="ADMIN")
        {
            navigate("/admin/dashboard");
        }

       
  fetchCourses();
      }
}, [user,navigate,loading]);

const fetchCourses = async () => {
  try {
    const response = await axios.get("http://localhost:8080/api/courses/viewAll");
    console.log("API Response:", response.data);
    setCourses(response.data);
  } catch (error) {
    console.error("Error fetching courses:", error.message); 
  }
};


const handleSearch = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/courses/search?courseName=${searchTerm}`
    );
    setCourses(response.data);
  } catch (error) {
    console.error("Error searching courses:", error);
  }
};

const handleEnroll = (course) => {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const exists = cart.find((item) => item.id === course.id);
  if (!exists) {
    cart.push(course);
    localStorage.setItem("cart", JSON.stringify(cart));
  }
  navigate("/user/cart");
};




return (
  <>
       <Navbar searchTerm={searchTerm} setSearchTerm={setSearchTerm} handleSearch={handleSearch}></Navbar>
   <div className="container mt-5 mb-4">
    <h2 className="text-center mb-4 fw-bold bg-secondary text-white">
      <span >AVAILABLE COURSES</span>
    </h2>
    <hr></hr>


  
    <div className="row">
      {courses.length > 0 ? (
        courses.map((course, index) => (
          <div key={index} className="col-lg-4 col-md-6 col-sm-12 mb-4">
            <div className="card h-100 shadow-lg d-flex flex-column">
              <img
                src={`http://localhost:8080/api/courses/image/${course.id}`}
                className="card-img-top"
                alt={course.courseName}
                onError={(e) => (e.target.style.display = "none")}
                style={{ height: "200px", objectFit: "cover" }}
              />
              <div className="card-body d-flex flex-column">
                <h5 className="card-title fw-bold text-primary text-center">{course.courseName}</h5>
                <p className="card-text flex-grow-1" style={{ minHeight: "100px", overflow: "hidden", textOverflow: "ellipsis" }}>
                  {course.description}
                </p>
                <div className="mt-auto">
                  <p className="text-muted text-center">
                    <i className="bi bi-clock"></i> Duration: {course.duration}
                  </p>
                  <div className="d-flex justify-content-between align-items-center">
                    <span className="badge bg-primary fs-6">₹{course.price}</span>
                    <button className="btn btn-outline-primary btn-sm " onClick={()=>handleEnroll(course)}>Enroll NOW</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        ))
      ) : (
        <div className="col-12 text-center">
          <div className="alert alert-info fs-5">No courses available at the moment.</div>
        </div>
      )}
    </div>
  </div>
  
  </>
  
   

)
}

export default UserDashboard;

