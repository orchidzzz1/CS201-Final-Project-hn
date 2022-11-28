import { useEffect, useState } from "react";
import { useLocation, useNavigate } from 'react-router-dom';

const Dashboard = () => {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem('profile')))
  const authenticated = user.authenticated
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    setUser(JSON.parse(localStorage.getItem('profile')))
    console.log("auth state: " + authenticated)
    if (authenticated !== 1) {
      console.log("not auth")
      navigate('/')
    } 
  }, [location, authenticated, navigate]);


  
  return (
    <div>
      <p>Welcome to your Dashboard</p>
    </div>
  );
  
};
export default Dashboard;