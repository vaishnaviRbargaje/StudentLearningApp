import React, { useState, useEffect } from "react";
import axios from "axios";
import Navbar from "../Component/common/NavBar";






const Course = () => {
  const [courses, setCourses] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const[currentPage,setCurrentPage]=useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [CoursePerPages]=useState(3);

  

  const indexoflaststudents= currentPage * CoursePerPages;
  const indexoffirststudents=indexoflaststudents - CoursePerPages;

  const currentCourses=courses.slice(
    indexoffirststudents,
    indexoflaststudents
  )

  useEffect(()=>
  {
    setTotalPages(Math.ceil(courses.length/CoursePerPages))
  },[courses,CoursePerPages])


  useEffect(() => {
    fetchCourses();
  }, []);

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

  return (
  <>
      
      <Navbar searchTerm={searchTerm} setSearchTerm={setSearchTerm} handleSearch={handleSearch}></Navbar>
   <div className="container mt-5 mb-2">
   <div class="bg-secondary shadow-lg text-white text-center rounded py-2 overflow-hidden position-relative" style={{height:"50px"}}>
    <h2  className="fw-bold font-monospace position-absolute"
          style={{
            whiteSpace: 'nowrap',
            animation: 'slideLeft 12s linear infinite'
          }}>
        <span className="pb-2">🚀 AVAILABLE COURSES • LEARN ANYTIME • GROW ANYWHERE 🌍</span>

    </h2>
    </div>
    
    <hr></hr>
  
    <div className="row">
      {courses.length > 0 ? (
        currentCourses.map((course, index) => (
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
                    <button className="btn btn-outline-primary btn-sm">More Info</button>
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

    <center><div>
    <button className="btn btn-outline-primary" onClick={()=> setCurrentPage((prev)=>Math.max(prev -1 , 1))} 
      disabled={currentPage===1}
      > 
prev
    </button>
    <span > {""} Page   {currentPage}   Of   {totalPages}</span>

    <button className="btn btn-outline-primary" onClick={()=> setCurrentPage((prev)=>Math.min(prev +1, totalPages))} 
      disabled={currentPage===totalPages}
      >
       next
    </button>


  </div></center>
  
  </>
   
  );
};

export default Course;