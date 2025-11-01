import React, { useContext } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./Component/common/NavBar";
import Home from "./Component/static/Home";

import Contact from "./Component/static/Contact";
import Footer from "./Component/common/Footer";
import Course from "./pages/Course";
import Login from "./pages/Login";
import AdminRoute from "./routes/AdminRoute";

import RegisterForm from "./pages/Register";
import UserDashboard from "./pages/UserDashboard";
import { AuthContext } from "./context/AuthProvider";
import AdminDashboard from "./pages/AdminDashboard";
import About from "./Component/static/About";
import Enrollments from "./Component/admin/Enrollments";
import ManageCourse from "./Component/admin/ManageCourses";

import AdminContactList from "./Component/admin/AdminContactList";
import Cart from "./pages/Cart";
import MyCourse from "./Component/user/MyCourse";
import TestEditor from "./Component/admin/TestEditor";
import TestList from "./Component/admin/TestList";
import TestResults from "./Component/admin/TestResult";
import UserTest from "./Component/user/UserTest";






function App() {
  const{user}=useContext(AuthContext)
 


  return (
    <div className="app-wrapper">
      <Navbar />
  
      <main className=" flex-grow-1 mt-4 ">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="about" element={<About></About>}/>
          <Route path="/course" element={<Course />} />
        
          <Route path="/contact" element={<Contact />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<RegisterForm />} />
         


          <Route
            path="/user/dashboard"
            element={user ? <UserDashboard /> : <Navigate to="/login" replace />}


          />
          <Route path="/user/cart" element={<Cart></Cart>}></Route>
          <Route path="/user/mycourse" element={<MyCourse></MyCourse>}></Route>
          <Route path="/user/test" element={<UserTest></UserTest>}></Route>

           {/* only admin can acess  & admin role only give by the database */}

           <Route path="/admin" element={<AdminRoute/>}>
           <Route path="/admin/enrollments" element={<Enrollments></Enrollments>}/>
           <Route path="/admin/update" element={<ManageCourse></ManageCourse>}></Route>
          <Route path="/admin/add/update" element={<TestEditor></TestEditor>}></Route>
          <Route path="/admin/alltest" element={<TestList></TestList>}></Route>
          <Route path="/admin/result" element={<TestResults></TestResults>}></Route>
           <Route path="message" element={<AdminContactList></AdminContactList>}></Route>
           <Route index element={<AdminDashboard/>}/>
           
           </Route>
           <Route path="*" element={<Navigate to={"/"}/>}></Route>

          
           
      
        </Routes>
      </main>
      <Footer />
    </div>
  );
}

export default App;
