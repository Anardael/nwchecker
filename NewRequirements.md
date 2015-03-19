# Introduction #

We need to create web system for university to perform university level contests for computer science.

## High level requirements ##

  1. Administration: We should have several levels of users. One users should have access to the whole system and some only to problems created for their department. There should be possibility to create as many departments as possible
  1. Extensibility: It should be possible easily to extend system of using new compiler/language.
  1. Problem storing: We should be able to store all revisions of problem submitted by user.
  1. Security: It should be possible to track problem submission to not allow attacks on the system (automatically ban of the users)
  1. Problem checking mechanism could be located on different machine than web application, probably there could be several machines that are running users solutions.
  1. We should have both contest and not contest functionality. As well as we should have different contest types (ACM and classic where solution would be checked at the end of contest). After contest is finished we should allow problem to come into problem archive
  1. We should allow a kind of forum per problem functionality to allow users put theirs problems there
  1. There should be possibility to add theoretical information to problem with possibility to hide this information if necessary.
  1. Project should be localized. Two main languages are Ukrainian and English.

## System components ##

We will have following components at our system:
  1. Web UI written with using jquery.
  1. Server side that will use spring, spring MVC for web part, hibernate for database and some library for client server communication
  1. SQL database for storing information
  1. There will be set of clients that will know how to compile and run the program.
  1. There would be groovy scripting engine that will allow to write custom verification procedures.

## User interface ##

For UI http://getbootstrap.com is recommended. For all big text areas - WYSIWYG editor should be added(http://www.sitepoint.com/html5-wysiwyg/).

### Main page ###
Main page should contains the following items:
  * Site news. It should be paged to not show all news at time.
  * Main menu that should contain links to problem list page, contest list page, admin page link for admin users, create contest button for teacher user.
  * Registration link that will allow user to registered. If user is registered there should be link that allows to log in, open user data and do log out.
  * Language selection buttons.

### Contest list view ###
View should simply display the list of all contests in the system, view should also display when the contest will start and small description. For teacher users there should be possibility to add new contest or adding existing one.

Also this view should have the same controls as main view (main menu, registration, log in/lg out, user preferences)

### Contest view ###

This view should show all the data for the contest (as it is described in the model section). For teacher user that have access to this user there should be possibility to edit contest data.

Editing rules:
  * Teacher could edit any contest data if contest was not started.
  * If contest was started or finished the only value that could be changed is contest time and it could be only increased.
Also this view should have the same controls as main view (main menu, registration, log in/lg out, user preferences)

### Problem list view ###
It should display problem list that could be grouped according to user preferences by contest, complexity, year, etc. There should be possibility to open any problem.

Also this view should have the same controls as main view (main menu, registration, log in/lg out, user preferences)

### Problem view ###
For regular user it should show problem text, input and output file and limitation.

For teacher that have edit rights to this problem it should allow editing all these items. Also it should have possibility to select add groovy script for verification.

After editing the problem - system should automatically start rejudging of all solutions sent by users. And in case if judge result was changed - e-mail should be sent to users.

Also this view should have the same controls as main view (main menu, registration, log in/log out, user preferences). For this view it should also show forum link, link for the view of problem solving attempts, post solution.

### Administration view ###
Administration view should allow to see all statistics and perform filtering (see log table).

There should be possibility to ban user/IP.

There should be possibility to remove some user or grant him some access level.

There should be possibility to see slaves jobs health status and available compilers.

There should be possibility setting security settings like minimum interval between check ins, connection password, etc

Also this view should have the same controls as main view (main menu, registration, log in/log out, user preferences).

### Post solution view ###

This view should have possibility to put file name, or solution text and do submit. After submitting it should show verification status for this problem.

Also this view should have the same controls as main view (main menu, registration, log in/log out, user preferences).

## Model ##

There would be following main objects:

**Contest** would have following fields
  * Description - contains blob field of rich text that will contain description of contest
  * Short description - short description of the contest that would be printed in contest list view
  * Contest type - contain constant saying of what rules would be used for contest (ACM or classic for now)
  * Contest duration - contains contest duration in minutes
  * Contest start - datetime that indicates contest start date and time

**Problem** would have following fields:
  * Text -  text of the problem. Should be rich text(blob)
  * Input file - string indicating the name of input file
  * Output file - string indicating the name of output file
  * Memory limit - int indicating maximum memory limit in KB
  * Time limit - int indicating how much time program could run in milliseconds
  * Script for verification(comparing output file)
  * Set of input data - in separate table
  * Set of output data - in the same table as output data
  * Forum pages link
  * Link to theory clarification if needed

**User** would have following fields:
  * Username
  * Password
  * Display name
  * Some university user data(probably faculty or group)
  * User access level (simple user or teacher or admin or not loged user). User could have several access levels
  * Attempts to solve the problem - this should be done in separate table and should include solution source code
  * If the user is teacher - list of problems user have possibility to modify
  * Department for teacher users.
  * Ban Time - if exists 0 time while user will be inactive

**News** Would have following fields:
  * News text - rich text format
  * User link - User that added this news
  * Date time - Time when it was added

**Log** would have following fields:
  * Date time
  * Event type (user log in, log out, solution sent, problem added, problem edited, rejudging)
  * Event user ()
  * Event level (warning, error)
  * Request IP if applicable
  * Event description.

## Communication between application and checker ##

It should be real time stable communication via TCP/IP. We will have simple synchronous socket connection between client and server. Server will kill clients connection if it will not have response for checking request for more than some configurable time.

For serialization we could use https://developers.google.com/protocol-buffers/

After connection client will send following information to server:
  1. Connection password
  1. Available compilers

Server will send following data to client:
  1. Program source code
  1. Set of input files
  1. Time limitation
  1. Memory limitation
  1. Language to compile source code
  1. Name of input and output files

Client will response with an array(with a size that will be the same as a set of input files) with following structure:
  1. Output file
  1. Return code
  1. Memory took
  1. Time took
  1. Compile error description
In case if application will fails or not compiles return code will contain appropriate number and in case of compilation error - "compilation error description" field will contain error description.

## Build system ##
Project should be built using maven. No IDE should be needed to create war file.