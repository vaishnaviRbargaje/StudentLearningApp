import axios from "axios";
import React, { useEffect, useState } from "react";



function Enrollments() {

    const[enrollments,setEnrollments]=useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [enrollmetPerPage] = useState(4);

    const indexoffirstEnrollment = (currentPage - 1) * enrollmetPerPage; 
    const indexoflastEnrollment = indexoffirstEnrollment + enrollmetPerPage;

    const currentEnrollments = enrollments.slice(indexoffirstEnrollment, indexoflastEnrollment);

    useEffect(() => {
        setTotalPages(Math.ceil(enrollments.length / enrollmetPerPage));
    }, [enrollments, enrollmetPerPage]);


    useEffect(()=>
    {
        fetchEnrollments();
    },[])

    const fetchEnrollments=async()=>
    {
        try {
            await axios.get("http://localhost:8080/api/enrollments/admin/viewAllEnroll").then((response)=>
            {
                setEnrollments(response.data)

            })
        
        } catch (error) {

            console.error("Error Fetching Enrollments","error")

            
        }
    }

    const CancelEnrollment=async(id)=>
    {
        try {
            await axios.delete(`http://localhost:8080/api/enrollments/admin/cancel/${id}`)

            alert("Enrollment Cancelled Sucessfully")
            fetchEnrollments();
        } catch (error) {

            console.error("Error cancelling enrollment:",error)
            
        };

    };

  return (

    <div>
    <div className="container mt-5 mb-4">
        <h2 className="text-center mb-4 text-bold">All Enrollments</h2>
        <div className="table-responsive">
        <table className="table table-striped table-bordered table-hover text-center">
            <thead className="table-dark">
                <tr>
                    <th>ID</th>
                    <th>User Name</th>
                    <th>Course Name</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    enrollments.length>0?(
                        currentEnrollments.map((e)=>
                            <tr>
                                <td>{e.id}</td>
                                <td>{e.username}</td>
                                <td>{e.courseName}</td>
                                <td>{e.status}</td>
                                <td>
                                <button className="btn btn-danger" onClick={()=>CancelEnrollment(e.id)}>
                                    Cancel
                                    
                                </button>
                                </td>
                               
                            </tr>
                       ))
                    :(
                        <tr>
                            <td colSpan={"5"} className="text-center">

                                No Enrollments Found..

                            </td>
                        </tr>
                    )}
                
            </tbody>

        

        </table>

        
      
    </div>
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

    </div>



  );
                }

export default Enrollments;
