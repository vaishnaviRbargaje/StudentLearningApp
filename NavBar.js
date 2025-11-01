import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthProvider";
import AddCourse from "../admin/AddCourse";

const Navbar = ({ searchTerm, setSearchTerm, handleSearch }) => {
  const { user, logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const [showForm, setShowForm] = useState(false);
  const [showAdminDropDown, setShowAdminDropdown] = useState(false);
  const [showUserDropdown, setShowUserDropdown] = useState(false);

  const handleLogout = () => {
    logout();
    localStorage.removeItem("user");
    navigate("/login");
  };

  const handleEdit = () => {
    navigate("/admin/update");
  };

  const toggleDropdown = () => {
    setShowAdminDropdown(!showAdminDropDown);
  };

  const toggleUserDropdown = () => {
    setShowUserDropdown(!showUserDropdown);
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg bg-white fixed-top shadow-sm pb-3">
        <div className="container">
          {user?.role?.toUpperCase() === "ADMIN" ? (
            <span className="btn-outline-primary btn-sm">Admin Dashboard</span>
          ) : (
            <Link className="nav-link fw-bold" to="/home">
              Edu<span className="fw-normal">Nexus</span>
            </Link>
          )}

          <ul className="nav justify-content-end">
            {!user || user.role?.toUpperCase() !== "ADMIN" ? (
              <>
                <li className="nav-item">
                  <Link className="nav-link active" to="/home">
                    Home
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/course">
                    Courses
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/contact">
                    Contact
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/about">
                    About
                  </Link>
                </li>

                
              </>
            ) : null}

            

            {user ? (
              <>
                {user.role?.toUpperCase() === "ADMIN" && (
                  <li className="nav-item dropdown position-relative">
                    <button
                      className="btn p-0 border-0 me-3"
                      style={{
                        width: "40px",
                        height: "40px",
                        overflow: "hidden",
                        borderRadius: "50px",
                        transition: "transform 0.2s ease-in-out",
                      }}
                      onClick={toggleDropdown}
                      onMouseEnter={(e) =>
                        (e.currentTarget.style.transform = "scale(1.1)")
                      }
                      onMouseLeave={(e) =>
                        (e.currentTarget.style.transform = "scale(1)")
                      }
                    >
                      <img
                        src="/image/Admin.png"
                        alt="Admin"
                        className="rounded-circle"
                        style={{
                          width: "100%",
                          height: "100%",
                          objectFit: "cover",
                          pointerEvents: "none",
                        }}
                      />
                    </button>

                    {showAdminDropDown && (
                      <div
                        className="dropdown-menu show position-absolute animate__animated animate__fadeIn mt-2"
                        style={{ left: "-80px", zIndex: 999 }}
                      >
                        <Link className="dropdown-item" to="/admin">
                           Dashboard
                        </Link>
                        <button
                          className="dropdown-item"
                          onClick={() => setShowForm(true)}
                        >
                           Add Course
                        </button>
                        <button className="dropdown-item" onClick={handleEdit}>
                           Edit
                        </button>
                        <Link className="dropdown-item" to="/admin/enrollments">
                           View Enrollments
                        </Link>
                        <Link className="dropdown-item" to="/admin/message">
                           Contact Messages
                        </Link>
                        <Link className="dropdown-item" to="/course">
                           View Courses
                        </Link>
                        <Link className="dropdown-item" to="/admin/add/update">
                           Manage Tests
                        </Link>
                        <Link className="dropdown-item" to="/admin/alltest">
                          All Test
                        </Link>
                        <Link className="dropdown-item" to="/admin/result">
                          User Result
                        </Link>


                        <button
                          className="dropdown-item text-danger"
                          onClick={handleLogout}
                        >
                          🚪 Logout
                        </button>
                      </div>

                      
                    )}
                  </li>
                  
                )}

                {user.role?.toUpperCase() === "USERS" && (
                  <li className="nav-item dropdown position-relative">
                    <button
                      className="btn p-0 border-0 me-3"
                      style={{
                        width: "40px",
                        height: "40px",
                        overflow: "hidden",
                        borderRadius: "50px",
                        transition: "transform 0.2s ease-in-out",
                      }}
                      onClick={toggleUserDropdown}
                      onMouseEnter={(e) =>
                        (e.currentTarget.style.transform = "scale(1.1)")
                      }
                      onMouseLeave={(e) =>
                        (e.currentTarget.style.transform = "scale(1)")
                      }
                    >
                      <img
                        src="/image/User.png"
                        alt="User"
                        className="rounded-circle"
                        style={{
                          width: "100%",
                          height: "100%",
                          objectFit: "cover",
                          pointerEvents: "none",
                        }}
                      />
                    </button>

                    {showUserDropdown && (
                      <div
                        className="dropdown-menu show position-absolute animate__animated animate__fadeIn mt-2"
                        style={{ left: "-80px", zIndex: 999 }}
                      >
                        <Link className="dropdown-item" to="/user/dashboard">
                           Dashboard
                        </Link>
                        <Link className="dropdown-item" to="/user/cart">
                           My Cart
                        </Link>
                        <Link className="dropdown-item" to="/user/mycourse">
                           My Courses
                        </Link>
                        <Link className="dropdown-item" to="/user/test">
                                Online Quize
                        </Link>
                        <Link className="dropdown-item" to="/contact">
                           Contact Messages
                        </Link>
                        
                        <button
                          className="dropdown-item text-danger"
                          onClick={handleLogout}
                        >
                          🚪 Logout
                        </button>


                   
                      </div>
                    )}
                  </li>
                )}
                   <form
              className="d-flex"
              role="search"
              onSubmit={(e) => e.preventDefault()}
            >
              <input
                className="form-control me-2"
                type="search"
                placeholder="Search"
                aria-label="Search"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
              <button
                className="btn btn-outline-success"
                type="submit"
                onClick={handleSearch}
              >
                Search
              </button>
            </form>
              </>
            ) : (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/login">
                    Sign In
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/register">
                    Sign Up
                  </Link>
                </li>

                <form
              className="d-flex"
              role="search"
              onSubmit={(e) => e.preventDefault()}
            >
              <input
                className="form-control me-2"
                type="search"
                placeholder="Search"
                aria-label="Search"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
              <button
                className="btn btn-outline-success"
                type="submit"
                onClick={handleSearch}
              >
                Search
              </button>
            </form>
              </>
            )}

         
          </ul>
        </div>
      </nav>

      {showForm && <AddCourse setShowForm={setShowForm} />}
    </>
  );
};

export default Navbar;
