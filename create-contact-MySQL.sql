-- Run this script directly in the MySQL server query window.
-- It will automatically create the database and all the database objects.

-- If the database "COMPANY" already exists, then delete it.
DROP DATABASE IF EXISTS CONTACTS;
-- Create the Database "COMPANY"
CREATE DATABASE CONTACTS;


-- Set the currently active database to be "CONTACTS"
USE CONTACTS;

DROP TABLE IF EXISTS CONTACT;
CREATE TABLE CONTACT (
  Contact_id      INT         NOT NULL  AUTO_INCREMENT,
  Fname           VARCHAR(15) NOT NULL, 
  Mname           VARCHAR(15), 
  Lname           VARCHAR(15) NOT NULL, 
  CONSTRAINT pk_Contact PRIMARY KEY (Contact_id)
);

DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS (
  Address_id      INT         NOT NULL  AUTO_INCREMENT,
  Contact_id      INT,
  Address_type    CHAR(4)     NOT NULL, -- Home, Work
  Address         VARCHAR(25),  
  City            VARCHAR(15),
  State           VARCHAR(15),
  Zip             VARCHAR(10),
  CONSTRAINT pk_Address         PRIMARY KEY (Address_id),
  CONSTRAINT fk_Address_Contact FOREIGN KEY (Contact_id) 
    references CONTACT(Contact_id)
    ON DELETE CASCADE
);

DROP TABLE IF EXISTS PHONE;
CREATE TABLE PHONE (
  Phone_id      INT         NOT NULL  AUTO_INCREMENT,
  Contact_id    INT,
  Phone_type    VARCHAR(10)    NOT NULL, 
  Area_code     CHAR(3),                     
  Number        VARCHAR(12),
  CONSTRAINT pk_Phone           PRIMARY KEY (Phone_id),
  CONSTRAINT fk_Phone_Contact   FOREIGN KEY (Contact_id) 
    references CONTACT(Contact_id)
    ON DELETE CASCADE
);

DROP TABLE IF EXISTS DATE;
CREATE TABLE DATE (
  Date_id      INT         NOT NULL  AUTO_INCREMENT,
  Contact_id   INT,
  Date_type    VARCHAR(15)     NOT NULL,
  Date         VARCHAR(10),
  CONSTRAINT pk_Date         PRIMARY KEY (Date_id),
  CONSTRAINT fk_Date_Contact FOREIGN KEY (Contact_id) 
    references CONTACT(Contact_id)
    ON DELETE CASCADE
);


LOAD DATA LOCAL INFILE '/Users/farhanr8/Desktop/Contacts.csv' 
INTO TABLE CONTACT FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS 
-- (Contact_id, Fname, Mname, Lname);
(@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9,@col10,@col11,@col12,@col13,@col14,@col15,@col16)
SET Contact_id = @col1, Fname = @col2, Mname = nullif(@col3, ''), Lname = @col4;

LOAD DATA LOCAL INFILE '/Users/farhanr8/Desktop/Contacts.csv' 
INTO TABLE ADDRESS FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS 
(@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9,@col10,@col11,@col12,@col13,@col14,@col15,@col16)
SET Contact_id = @col1, Address_type = 'Home', Address = nullif(@col7,''), City = nullif(@col8,''), State = nullif(@col9,''), Zip = nullif(@col10,'');

LOAD DATA LOCAL INFILE '/Users/farhanr8/Desktop/Contacts.csv' 
INTO TABLE ADDRESS FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS 
(@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9,@col10,@col11,@col12,@col13,@col14,@col15,@col16)
SET Contact_id = @col1, Address_type = 'Work', Address = nullif(@col12,''), City = nullif(@col13,''), State = nullif(@col14,''), Zip = nullif(@col15,'');

LOAD DATA LOCAL INFILE '/Users/farhanr8/Desktop/Contacts.csv' 
INTO TABLE PHONE FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS 
(@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9,@col10,@col11,@col12,@col13,@col14,@col15,@col16)
SET Contact_id = @col1, Phone_type = 'Home', Area_code = nullif(SUBSTRING(@col5, 1, 3),''), Number = nullif(@col5,'');

LOAD DATA LOCAL INFILE '/Users/farhanr8/Desktop/Contacts.csv' 
INTO TABLE PHONE FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS 
(@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9,@col10,@col11,@col12,@col13,@col14,@col15,@col16)
SET Contact_id = @col1, Phone_type = 'Cell', Area_code = nullif(SUBSTRING(@col6, 1, 3),''), Number = nullif(@col6,'');

LOAD DATA LOCAL INFILE '/Users/farhanr8/Desktop/Contacts.csv' 
INTO TABLE PHONE FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS 
(@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9,@col10,@col11,@col12,@col13,@col14,@col15,@col16)
SET Contact_id = @col1, Phone_type = 'Work', Area_code = nullif(SUBSTRING(@col11, 1, 3),''), Number = nullif(@col11,'');

LOAD DATA LOCAL INFILE '/Users/farhanr8/Desktop/Contacts.csv' 
INTO TABLE DATE FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS 
(@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9,@col10,@col11,@col12,@col13,@col14,@col15,@col16)
SET Contact_id = @col1, Date_type = 'Birth', Date = nullif(@col16,'');































