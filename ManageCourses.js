import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import { AuthContext } from "../../context/AuthProvider";
import { useNavigate } from "react-router-dom";
import Navbar from "../common/NavBar";



const ManageCourse = () => {
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  const [courses, setCourses] = useState([]);
   const [searchTerm, setSearchTerm] = useState("");


    const [currentPage, setCurrentPage] = useState(1);
       const [totalPages, setTotalPages] = useState(1);
       const [CoursePerPage] = useState(3);
   
       const indexoffirstCourse = (currentPage - 1) * CoursePerPage; 
       const indexoflastCourse = indexoffirstCourse + CoursePerPage;
   
       const currentCourse = courses.slice(indexoffirstCourse, indexoflastCourse);
   
       useEffect(() => {
           setTotalPages(Math.ceil(courses.length / CoursePerPage));
       }, [courses, CoursePerPage]);


  const [newCourse, setNewCourse] = useState({
    courseName: "",
    description: "",
    price: "",
    duration: "",
  });
  const [image, setImage] = useState(null);
  const [editingCourse, setEditingCourse] = useState(null);
  const [showForm, setShowForm] = useState(false);

  useEffect(() => {
    if (!user) {
      console.error("User not found, redirecting...");
      navigate("/login");
    } else if (user.role !== "ADMIN") {
      console.error("Unauthorized access, redirecting...");
      navigate("/admin");
    }
  }, [user, navigate]);

  const fetchCourses = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/courses/viewAll"
      );
      setCourses(response.data);
    } catch (error) {
      console.error("Error fetching Courses:", error);
    }
  };

  useEffect(() => {
    fetchCourses();
  }, []);

  

  const handleUpdateCourse = async () => {
    try {
      if (
        !newCourse.courseName ||
        !newCourse.description ||
        !newCourse.price ||
        !newCourse.duration
      ) {
        alert("All filleds are required");
        return;
      }

      const formData = new FormData();
      formData.append("courseName", newCourse.courseName);
      formData.append("description", newCourse.description);
      formData.append("price", newCourse.price);
      formData.append("duration", newCourse.duration);

      if (image) {
        formData.append("imageFile", image);
      }
      await axios.put(
        `http://localhost:8080/api/courses/admin/update/${editingCourse.id}`,
        formData,
        { headers: { "Content-Type": "multipart/form-data" } }
      );
      alert("Course update Sucessfully");

      setEditingCourse(null);
      setNewCourse({
        courseName: "",
        description: "",
        price: "",
        duration: "",
      });
      setImage(null);
      setShowForm(false);
      fetchCourses();
    } catch (error) {
      console.log("Error Update Course", error);
    }
  };

  const handleEdit = (course) => {
    setNewCourse({
      courseName: course.courseName,
      description: course.description,
      price: course.price,
      duration: course.duration,
    });
    setEditingCourse(course);
    setShowForm(true);
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
    

    <Navbar
        searchTerm={searchTerm}
        setSearchTerm={setSearchTerm}
        handleSearch={handleSearch}
      ></Navbar>


      
      <div className="container mt-5 mb-2">
        <div className="container-fluid">
          <h1 className="display-5 mb-4 bg-secondary text-white text-center fw-bold font-monospace">
                 Edit Courses!
          </h1>
          <hr></hr>

        

          <div className="row">
            {courses.length > 0 ? (
              currentCourse.map((course, index) => (
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
                      <h5 className="card-title fw-bold text-primary text-center">
                        {course.courseName}
                      </h5>
                      <p
                        className="card-text flex-grow-1"
                        style={{
                          minHeight: "100px",
                          overflow: "hidden",
                          textOverflow: "ellipsis",
                        }}
                      >
                        {course.description}
                      </p>
                      <div className="mt-auto">
                        <p className="text-muted text-center">
                          <i className="bi bi-clock"></i> Duration:{" "}
                          {course.duration}
                        </p>
                        <div className="d-flex justify-content-between align-items-center">
                          <span className="badge bg-primary fs-6">
                            ₹{course.price}
                          </span>

                          <div className=" d-flex  ">
                            <button
                              onClick={() => handleEdit(course)}
                              className="btn btn-outline-primary btn-sm w-100"
                            >
                              Edit
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              ))
            ) : (
              <div className="col-12 text-center">
                <div className="alert alert-info fs-5">
                  No courses available at the moment.
                </div>
              </div>
            )}
          </div>




          <center><div>


<button
                    className="btn btn-outline-primary"
                    onClick={() => setCurrentPage((prev) => Math.max(prev - 1, 1))}
                    disabled={currentPage === 1}
                >
                    Prev
                </button>
                <span> Page {currentPage} of {totalPages}</span>
                <button
                    className="btn btn-outline-primary"
                    onClick={() => setCurrentPage((prev) => Math.min(prev + 1, totalPages))}
                    disabled={currentPage === totalPages}
                >
                    Next
                </button>



</div></center>

          {showForm && (
            <div
              className="modal show d-block mt-5"
              style={{ backgroundColor: "rgba(0,0,0,0.5)" }}
            >
              <div className="modal-dialog modal-lg">
                <div className="modal-content">
                  <div className="modal-header">
                    <h5 className="modal-title">
                      { "Update Course" }
                    </h5>
                    <button
                      type="button"
                      className="btn-close"
                      onClick={() => setShowForm(false)}
                    ></button>
                  </div>
                  <div className="modal-body">
                    <div className="mb-3">
                      <input
                        type="text"
                        className="form-control"
                        placeholder="Course Name"
                        value={newCourse.courseName}
                        onChange={(e) =>
                          setNewCourse({
                            ...newCourse,
                            courseName: e.target.value,
                          })
                        }
                      />
                    </div>
                    <div className="mb-3">
                      <textarea
                        className="form-control"
                        placeholder="Description"
                        value={newCourse.description}
                        onChange={(e) =>
                          setNewCourse({
                            ...newCourse,
                            description: e.target.value,
                          })
                        }
                        rows={3}
                      />
                    </div>
                    <div className="mb-3">
                      <input
                        type="text"
                        className="form-control"
                        placeholder="Price"
                        value={newCourse.price}
                        onChange={(e) =>
                          setNewCourse({ ...newCourse, price: e.target.value })
                        }
                      />
                    </div>
                    <div className="mb-3">
                      <input
                        type="text"
                        className="form-control"
                        placeholder="Duration"
                        value={newCourse.duration}
                        onChange={(e) =>
                          setNewCourse({
                            ...newCourse,
                            duration: e.target.value,
                          })
                        }
                      />
                    </div>
                    <div className="mb-3">
                      <label className="form-label">Course Image</label>
                      <input
                        type="file"
                        className="form-control"
                        onChange={(e) => setImage(e.target.files[0])}
                      />
                    </div>
                  </div>
                  <div className="modal-footer">
                    <button
                      className="btn btn-secondary"
                      onClick={() => setShowForm(false)}
                    >
                      Cancel
                    </button>

                    <button
                      className="btn btn-primary"
                      onClick={handleUpdateCourse}
                    >
                      {editingCourse ? "Update Course" : "Add Course"}
                    </button>
                  </div>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default ManageCourse;
