import axios from 'axios';
import React, { useEffect, useState } from 'react'

function AdminContactList() {

    const[contacts,setContacts]=useState([]);
     const [currentPage, setCurrentPage] = useState(1);
        const [totalPages, setTotalPages] = useState(1);
        const [adminContactListPerPage] = useState(4);
    
        const indexoffirstcontact = (currentPage - 1) * adminContactListPerPage; 
        const indexoflastcontact = indexoffirstcontact + adminContactListPerPage;
    
        const currentMessage= contacts.slice(indexoffirstcontact, indexoflastcontact);
    
        useEffect(() => {
            setTotalPages(Math.ceil(contacts.length / adminContactListPerPage));
        }, [contacts, adminContactListPerPage]);
    


    useEffect(()=>
    {
        fetchContactMessage();
    },[])


    const fetchContactMessage=async()=>
    {
        try {
                   await axios.get("http://localhost:8080/api/contact/admin/get").then((response)=>
            {
                setContacts(response.data);
            })
        } catch (error) {
            
            alert("failed to fetch the message.")
        }
    }


  return (
    <div>
      
<div className='container mt-5'>
    <h2 className='text text-center text-primary mb-4'> User Contact Message </h2>
    <div className='table-responsive'>
        <table className='table table-bordered table-striped shadow'>
            <thead className='table-dark'>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Subject</th>
                    <th>Message</th>
                    <th>Date/Time</th>
                </tr>

            </thead>
            <tbody>
                {contacts.length>0?( 
                    currentMessage.map((contact,index)=>(
                        <tr key={contact.id}>

                            <td>{index +1}</td>
                            <td>{contact.name}</td>
                            <td>{contact.email}</td>
                            <td>{contact.subject}</td>
                            <td>{contact.message}</td>
                            <td>{new Date(contact.datetime).toLocaleDateString()}</td>

                        </tr>
                    ))
                ):(
                    <tr>
                      <td colSpan="6" className="text-center">No messages found.</td>
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
  )
}

export default AdminContactList
