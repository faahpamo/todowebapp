# todowebapp
A Java based web application for ToDo activities. Through this web application, one can create, read and update their todos through a modern web browser. The application also implements AAA, which means every user has their own account and their todo list etc. are private to them.

## Technologies Used
Java  
Servlet, JSP  
Apache Tomcat  
MySQL  
HTML
Apache NetBeans IDE
Firefox

This is entirely a back-end project. So, front-end technologies like CSS, JavaScript are not used. The aim of the project is to effectively learn and showcase how different pieces of the Java Servlet API work together.

We shall develop the web application starting with requirements analysis. Then we shall move on to database design. Data is central to any web application. Almost all use cases deal with data. Once the web application's data model is ready, we shall then move on to design the architecture of the application. In this phase, we shall see how our application behaves to different HTTP actions. Because, all actions performed by the application's users are through HTTP. We shall think of all possible user actions and define them clearly. Next, we shall move on to designing the interfaces and classes.

## Requirements Analysis
For our application, we begin with defining what a todo is for us. A todo is a task that must be accomplished. We create list of such tasks to help us live a prdocutive life. We keep tracking the list as we get the tasks done one after the other. A todo item for us has below properties:
1. an unique identification number such as a positive number
2. details of the task that we want to accomplish
3. task created timestamp
4. task status

Initially, a task will have the status 'todo'. When we start working on it, we change it to 'in progress'. Once the task gets accomplished, we mark its status as 'done'.

Also, we don't want to specify a deadline for a task that we create. We just want a simple todo list.

We want our application to also support multiple users. And every user shall have their own private list. Thus, users cannot see others' todo list. A user shall be identified by their username, which is their valid email address for us. Users are given accounts on our application. Thus, an account has below properties:
1. an unique account identification number such as a positive integer
2. a distince username, which is an email address. Duplicate email addresses are not allowed.
3. user's first name. Duplicates are allowed.
4. user's last name. Duplicates are allowed.
5. password. Duplicates are allowed.
6. account status - enabled/disabled. Only enabled user accounts will be able to login the application.

We want an 'administrator' account to only manage accounts. The administrator account shall use the username 'admin'. The admin user can:
1. create a user account
2. help reset password in case someone forgets
3. can enable or disable a user account
4. not disable 'admin' account
5. change user details
6. not have access to any user's todo lists
7. not maintain a personal todo list

The last two items are worth observing. Usually, it is believed that a user with 'admin' rights has access to everybody's information. We don't want such. Also, we have already defined that 'admin' account for us is only to manage accounts. It is not for managing todo lists of users. 'admin' user account is not often used. It is meant only for special purposes. For our application, we expect one user account to also handle 'admin' account. So, it will be the same person who logs in using 'admin' credentials only when required. Because it is an existing user account using 'admin' account only to manage all accounts, we don't want a separate task list for admin account. That doesn't serve any purpose.

We want the todo lists to persist always. Which means, once a todo item is created successfully by a user, it can never be deleted. Similarly, we also don't want to delete a user account. In conclusion, we don't want to support 'delete' operations in our application. Thus, we only support CRU out of CRUD.

Because, we want our application to maintain private todo lists, we want the application to provide login and logout mechanisms. This is called 'authentication'. Every user, including 'admin', should first authenticate themselves. Upon successful authentication, a user will be redirected to their workspace. Because we are discussing two types of users (one admin and the other normal), we shall have two types of workspaces meant for them. An admin user shall be only working with user account management workspace. A normal user shall be only working with todo list management workspace. Both are exclusive. A normal user cannot see admin's workspace. And admin user cannot see normal user's workspace.

In addition to the above requirements, we want our application to store the details of users' login and logout timestamps.


## Data Model

## Application Architecture
We shall develop this application following the MVC 2 desgin pattern. Our application will be action based.

## Class Diagrams

## Data Layer

## Controllers

## Views
