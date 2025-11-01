import React, { useContext, useEffect, useState } from 'react'
import { AuthContext } from '../../context/AuthProvider'
import axios from 'axios'

function MyCourse() {

    const {user}=useContext(AuthContext)
    const[course,setMyCourse]=useState([])


    useEffect(()=>
    {
        try {
            

            if(user)
            {
                axios.get(`http://localhost:8080/api/enrollments/user/${user.id}`).then((response)=>
                {
                          setMyCourse(response.data)
                })
            }

        } catch (error) {
            console.error(error)
        }
    },[user])

    


  return (
    
    <div className='container mt-5 mb-4'>
        <h3 className='mb-4'>
            My Enrolled Courses</h3>
        {course.length === 0 ? (
            <p> You havent enrolled in nay courses yet. </p>

        ):(

            <div className="row">
              {
              course.map((course, index) => (
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
            }
          </div>
      )}
    </div>
       
   
  );
};

export default MyCourse


