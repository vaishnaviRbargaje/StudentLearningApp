import React from "react";

function About() {
  return (
    <>
      <div className="container mt-5 mb-2">
        <h3 className="text-center bg-secondary text-white">About</h3>
        <hr></hr>
        <p>
          Our E-Learning Platform is an innovative online education system
          designed to provide high-quality learning experiences for students,
          professionals, and lifelong learners. It bridges the gap between
          traditional classroom learning and digital education, offering a
          flexible, affordable, and interactive way to acquire new skills.
        </p>

        <div className="row mt-2 gx-2">
          <div className="col-lg-4 col-mg-6 col-sm-12 mb-4 ">
            <div className="card shadow-sm">
              <img
                src="/image/image1.jpeg"
                className="card-img-top"
                alt="..."
                style={{ height: "200px", objectFit: "cover" }}
              />
              <div className="card-body">
                <h5 className="card-title">🌍 What We Offer?</h5>
                <h3
                  style={{
                    fontWeight: "lighter",
                    color: "lightgray",
                    fontSize: "20px",
                  }}
                >
                  We provide a diverse range of courses in multiple domains,
                  including:
                </h3>
                <hr></hr>
                <p className="card-text">
                  ✅ Programming & Development (Java, Python, React, Spring
                  Boot, etc.)
                  <hr></hr>✅ UI/UX Design (Figma, Adobe XD, Design Thinking)
                  <hr></hr>✅ Personal Development (Communication Skills, Public
                  Speaking)
                </p>
              </div>
            </div>
          </div>

          <div className="col-lg-4 col-md-6 col-sm-12 mb-4 ">
            <div className="card shadow-sm">
              <img
                src="/image/image2.jpeg"
                className="card-img-top"
                alt="..."
                style={{ height: "200px", objectFit: "cover" }}
              />
              <div className="card-body">
                <h5 className="card-title">🚀 Key Features</h5>
                <h3
                  style={{
                    fontWeight: "lighter",
                    color: "lightgray",
                    fontSize: "20px",
                  }}
                >
                  We provide a diverse range of courses in multiple domains,
                  including:
                </h3>
                <hr></hr>
                <p className="card-text">
                  🔹 Interactive Learning – Video lectures, quizzes, and
                  hands-on projects
                  <hr></hr> 
                  🔹 Expert Instructors – Learn from industry
                  professionals 
                  <hr></hr>
                  🔹 Certification – Get recognized certification .. 
                 
                 
                </p>
              </div>
            </div>
          </div>

          <div className="col-lg-4 col-md-6 col-sm-12 mb-4 ">
            <div className="card shadow-sm">
              <img
                src="/image/image3.jpeg"
                className="card-img-top"
                alt="..."
                style={{ height: "200px", objectFit: "cover" }}
              />
              <div className="card-body">
                <h5 className="card-title">📚 Learning Experience</h5>
                <h3
                  style={{
                    fontWeight: "lighter",
                    color: "lightgray",
                    fontSize: "20px",
                  }}
                >
                Our courses are designed with a structured learning path, including:
                </h3>
                <hr></hr>
                <p className="card-text">
                ✔ Video Tutorials – High-quality pre-recorded sessions
                <hr></hr>
✔ Assignments & Quizzes – Test your knowledge with assessments
<hr></hr>
✔ Hands-on Projects – Real-world projects....

                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default About;
