NWC Checker was console application for checking solution on programming competitions. The new extension to the project is creation java server applicaiton that will be able to perform puniversity competitions in algorithms.

Deployment instructions
Requirements:
1. Java v1.7 or higher
2. Apache Tomcat that supports Java 1.7(Tomcat 7 or higher)
3. MySQL database
(if you're cloning repository)
4. Maven (3.0 or higher)

Instructions:
1. Clone repository.
2. Using Maven, create a .war file:
  in folder ~/nwcserver/ run following command: mvn make-install
3. Create MySQL schema for server using following command:
  CREATE DATABASE nwserver CHARACTER SET utf8 COLLATE utf8_general_ci;

  grant access privileges to user root with password root on this database:
  
  CREATE USER 'root' IDENTIFIED BY 'root';
  GRANT ALL ON root.* TO 'nwserver'@'%' IDENTIFIED BY 'root';
  GRANT ALL ON root.* TO 'nwserver'@'localhost' IDENTIFIED BY 'root';
  FLUSH PRIVILEGES;
  
4. Deploy the project on your Tomcat server.
