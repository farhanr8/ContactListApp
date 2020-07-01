# Contact List Application (Spring 2020)
by Farhan Rahman

This project involves the creation of a database host application that interfaces with a backend SQL database. The data stored and displayed are a contact's name, address, phone, and date. 

## Demo
{% include youtubePlayer.html id=ofnksEBOlqM %}

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

## Prerequisites

The two main software needed are the following:
- Java SDK
- MySQL

## Installing

Java SDK:
This application is a Java Swing program and so follow the steps in the link below -
https://docs.oracle.com/javase/tutorial/uiswing/start/compile.html

MySQL:
Similarly, follow the steps provided from oracle's website:
https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/


## Running

First, we need to setup the database with the initial data provided in csv file. Steps are shown below:

1) Launch MySQL with the ability to get data from a local file. Below is the command I used.

>> mysql --local-infile -u root -p

2) Import the contacts data using the provided sql script. 
This example assumes that MySQL was launched from the same directory that the create-company script resides. 
Otherwise, you may have to include an absolute path location.

>> source create-contact-MySQL.sql

Now you should be able to see CONTACT as a database when you run 'SHOW DATABASES' in mysql.
Next, to launch the application an executable jar file has been provided that includes the necessary libraries.
The app uses the login information to MySQL provided in app.properties. So the steps to run the GUI is:

1) Edit app.properties to include your username and password to MySQL.
2) Launch ContactList.jar

Alternatively, all the java source files and libraries have been included in the submission. 
You could compile them (or import to an IDE) and run the application. 



## Authors

Farhan Rahman
sxr190032@utdallas.edu