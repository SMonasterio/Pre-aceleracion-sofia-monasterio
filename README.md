# Disney Application
Backend challenge developed for the pre-acceleration in Java for Alkemy.

<h3>Context</h3>
This application is meant for kids to explore the Disney world. 
<br />
To do this, I developed this appication where they can access and modify the characters in it, and understand in wich movies each character performed.

<h3>Goal</h3>
To develop an API that is able to interact with the characters and their movies, ready to any fronted to use it.

<br/>

<h3>Technologies envolved:</h3>
<ul>
<li>Spring Boot</li>
<li>REST API</li>
<li>Spring Security</li>
<li>MySql</li>
<li>Swagger</li>
</ul>

<h3>Documentation:</h3>
<a href="http://localhost:8080/swagger-ui/index.html">
(Link to Swagger documentation)
<p>Only available when the backend is running</p>

<h3>Installation</h3>
<h4>Previous technical requirements</h4>
<ul>
<li>Have an IDE like IntelliJ Idea instaled</li>
<li>Have MySql Workbench and Postman instaled (the web postman's version can also be used)</li>
<li>Clone this repository</li>
</ul>
<h4>Database installation</h4>
<ul>
<li>Open MySql Workbench with your password, and create a schema called "DisneyApp".(When the backend is executed, Hibernate will automatically create the tables with their relationships)</li>
</ul>
<h4>Backend</h4>
<ul>
<li>From IntelliJ, run the proyect.</li>
</ul>
<h4>Postman</h4>
<ul>
<li>From Postman import the collection that is given in this repository.(Don't forget that to do all the API calls you will have to use de TOKEN provided when yoy login. Authorization -> Type: BEARER ->Token: copy the token here)</li>
</ul>
