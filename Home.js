import React from "react";


import Course from "../../pages/Course";


const Home = () => {
  return (
    <div className="container mt-5 mb-2">
   
      <div className="jumbotron text-center bg-primary text-white p-5 rounded">
        <h1 className="display-4 fw-bold">Welcome to Our Learning Platform</h1>
        <p className="lead">
          Enhance your skills with high-quality courses in various domains.
        </p>
        <a href="/course" className="btn btn-light btn-lg">
          Explore Courses
        </a>
      </div>

      <div className="text-center mt-5">
        <a href="/register" className="btn btn-primary btn-lg">
          Get Started Today!
        </a>
      </div>


      <div className="container-fluid px-5">
        <Course></Course>
      </div>

    </div>
    
  );
};

export default Home;
