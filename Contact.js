import axios from "axios";
import React, { useState } from "react";



function Contact() {

  const[formData,setFormData]=useState({

    name:"",
    email:"",
    subject:"",
    message:""


  })

  const handleChange=(e)=>
  {
    setFormData({...formData,[e.target.name]: e.target.value})
  }

  const handlesubmit=async(e)=>
  
  { 

    e.preventDefault();

    if(!formData.name.trim()|| !formData.email.trim()||!formData.subject.trim()||!formData.message.trim())
    {
      alert("Please fill the all field before submitting.")
      return;
    }

  
     const dataWithTimestamp = {
      ...formData,
      dateTime: new Date().toISOString()
     };
    try {

      const response=await axios.post("http://localhost:8080/api/contact/user/post"  ,  dataWithTimestamp,{
        headers:{"content-type":"application/json"}
      })

      if (response.status===200) {
        alert("Message sent Sucessfully!");
        setFormData({name:"",email:"",subject:"",message:""})
        
      } else {
        alert("failed  to send Message")
        
      }
      
    } catch (error) {
      console.error("Error:",error)
      
    }
  }








  return (
    <>
      <div  className=" d-flex align-items-center justify-content-center vh-100">
        <div className="container-fluid py-10">
          <div className="col-md-6 mx-auto bg-light">
            <form className="border p-5 w-100  rounded shadow-sm   " onSubmit={handlesubmit}>
              <h2
                style={{
                  textAlign: "center",
                  textTransform: "uppercase",
                  fontWeight: "bold",
                  color: "lightgrey",
                }}
              >
                Contact Us
              </h2>
              <input
                type="text"
                placeholder="name"
                 className="form-control mb-3"
                 name="name"
          
                value={formData.name}
                onChange={handleChange}
              />
              <input
                type="email"
                placeholder="email"
                 className="form-control mb-3"
                 name="email"
            
                value={formData.email}
                onChange={handleChange}
              />
              <input
                type="text"
                placeholder="subject"
                 className="form-control mb-3"
                 name="subject"
              
                value={formData.subject}
                onChange={handleChange}
              />
              <textarea
                placeholder="message"
                name="message"
                className="form-control mb-3"
                rows={4}
                value={formData.message}
                onChange={handleChange}
              ></textarea>

              <button className="btn btn-dark px-4" type="submit">Send Message</button>
            </form>
          </div>
         
        </div>
        
      </div>
    
      

 
    </>
   
  );
}

export default Contact;
