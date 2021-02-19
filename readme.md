Problem statement:

Create a java project with ant and a dependency manager (ivy or maven), use apache httpclient
and build a program that takes a url as a command line parameter.
It should fetch the url and print the headers and the content.
The headers should be printed to the console as a key value dictionary,
while the content should be written to a file taken as an additional parameter.
The http status code should become the exit code for your app, except for 200,
which should be mapped to 0(success) exit code.

Bonus: If the content is html, try and extract the title and print the title on the console.

How to Build and Run:

Pre-requisite:

1.) Java
2.) Maven
3.) Ant
Above three should be installed.

Below are the 2 ways to run the project

Way 1:

To Run the project from command line - Run below command from project folder

    ant run -Darg0="<URL>" -Darg1="<file.html>"
    
    for example : 
        ant run -Darg0="https://mirrors.estointernet.in/apache/ant/binaries/" -Darg1="file.html"

    Output for the above command will be print on console including the response headers and title (if content is html).
    And, If content type is text/html, then the content will be written to the given file.


Way 2:

STEP 1: To package the jar: Run "ant package" from project directory.

This will create a single (fat) jar (in dist folder of project path) which will include all the dependencies as well as this project itself.

STEP 2: To Run Built Jar - Go to Built Jar folder -> open terminal -> run below command

    java -jar <jar-name> "URL" "fileName.html"

