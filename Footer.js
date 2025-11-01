import React from "react";
import { FaFacebook, FaTwitter, FaInstagram } from "react-icons/fa";

const Footer = () => {
  return (
    <footer className="bg-dark text-light py-2 mt-4" style={{ fontSize: '14px' }}>
      <div className="container d-flex flex-column flex-md-row justify-content-between align-items-center">
        
        <div className="mb-2 mb-md-0 text-center text-md-start">
          &copy; 2025 E-learning
        </div>

        <div className="text-center">
          <a href="#" className="text-light me-2"><FaFacebook /></a>
          <a href="#" className="text-light me-2"><FaTwitter /></a>
          <a href="#" className="text-light"><FaInstagram /></a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
