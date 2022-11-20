To run this on your local machine, navigate to springbootREST/src/main/java/com/events/studentevents/StudenteventsApplication.java

This is the java file that starts the springboot application. It should start on port 8080.

The REST Controller is inside the controller folder, in a file called UserController.java, this is the code that calls appropriate functions depending on which URL the frontend accesses.

The Data Access Object interface describes the functions that need to be implemented (found at dao/UserDAO.java) and the implementation of these functions is given in UserDAOImp.java. 

The way this will work is: frontend goes to url/users => controller is called, handles GET request at /users and calles the appropriate function in DAO => DAO queries google cloud SQL instance.

https://csci-201-project-368421.uw.r.appspot.com/users is the google cloud version of this app, currently only the functionality to view all users is implemented

