# INTERNET-SHOP project

The project implements the work of a simple online store. 
Built according to the N-Tier architecture, SOLID principles and uses the 
following software and technologies:

### Technologies:
Java, JDBC, Servlets, 
jsp, HTML, bootstrap, jstl.

### Software: 
Maven, Tomcat, 
MySQL (Workbench), git.

Configured filters, implemented authentication and authorization 
(according to the roles Admin, User). 
Password hashing is used when registering a user.

### Admin can:
* view the list of registered users and delete them from the database
* view all products
* add / delete products
* view all orders, delete them 

### User can:
* view available products and put them in the cart
* delete products from the cart 
* form orders 
* view the list of their orders and details

## Running the project on a local machine
* Configure Tomcat
* Insert your own MySQL username and login in dbProperties in the ConnectionUtil class
* Run the scripts in the src / main / resources / init_db.sql file via MySQL workbench

**Once you have launched the program and the home page has opened, complete the following steps** :

* First click the Login button and then click the registration button for the new user
* Then click the InjectData button to write the data to the database for the correct 
  operation of the store
* The user only has access to certain pages. To log in as an administrator, first click the LogOut button,
  then click the SingIn button and enter the name 'admin' and password '1'