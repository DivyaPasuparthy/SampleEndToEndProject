# SampleEndToEndProject
Sample project built with Angular JS, Node.js, Express, Spring Batch and Spring Boot - REST API.

Spring Boot Application
- Performs CRUD Operations (GET/PUT/POST/DELETE) on MYSQL Server and maintains a user record.

Spring Batch Application
- Reads username and termination Date from TerminationFile.csv and calls Spring Boot Microservice (POST)
to update the user status to suspended.

UI Application
- Based on the username entered by the application user, UI with angular js makes call to node js , 
which inturn calls Spring Boot Microservice (GET) to get the user information and displayed on the screen.

Database
- Table is created in MYSQL server. All database scripts are also uploaded.

For UI Application
- Because of space constraint, have not uploaded node_modules related code.
Following steps are followed to build and run the application.
npm init //creates package.json

//to download relevant node modules
npm install express --save
npm install http --save
npm install request --save
npm install scope --save

//To use nodemon
npm install -g nodemon

//Created public folder and kept index.html and controllers/controller.js
//Created server.js file , which makes REST API call

//Start the server
nodemon server.js

//Application is built using Angular JS, Node.js and Express library.
