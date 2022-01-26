# Cheers is a beer catalog demo

Cheers is a beer catalog where demo where you can search beers.

The application was created with Spring boot 2.5.8 and Java 8. 
Testing with junit and MockMvc.
There is a swagger available wich was the built with API FIRST principles. The persistance layer is an in memory h2 database [h2 console](http://localhost:8080/cheers/h2-console)

## Building and running

Build it and run with maven:

~~~ sh
$ mvn clean compile
$ mvn spring-boot:run
~~~

Run tests:

~~~ sh
$ mvn test
~~~

## Endpoints, Security and users

Swagger is available  [here](http://localhost:8080/cheers/static/swagger-ui.html)

Admin user/password: admin/admin

Available manufactures password: 
damm/damm
guinness/guinness
paulaner/paulaner
brewdog/brewdog


Endpoints available without security are search a beeer and get a beer details:

~~~ sh
$ curl -i http://localhost:8080/cheers/public/v1/catalogue/beers?page=0&size=20&sort=type,desc&search=neth
$ curl -i http://localhost:8080/cheers/public/v1/catalogue/beer/1
~~~


The other endpoints available in swagger require basic authentication with above mentiond admin and manufacturers users:

~~~ sh
$ curl -i --user admin:admin -X POST http://localhost:8080/cheers/public/v1/catalogue/beer   -H 'Content-Type: application/json'    -d '{"name":"Punk IPA","graduation":5.8,"type":"IPA","description":"description Punk IPA","manufacturerId":1}'
~~~

There is a postmann availble to test the enpoints.

## What is implemented

This is the list of the features implemented:

<table>
<thead><tr>
  <th>feature</th> <th>default</th> 
</tr></thead>
<tbody>
  <tr>
    <th>1) Create the data model and the CRUD endpoints for a beers API, HTTP RESTFUL 
compliant, without the need to persist any information yet.</th>
    <td>✔</td>
  </tr>
  <tr>
    <th>2) Add the required persistence/service layer to the defined API. It's time to implement the CRUD operations to manage the model set. The 
API should support sorting.</th>
    <td>✔</td>
  </tr>
  <tr>
    <th>Bonus Steps:  Let's make this secure:</th>
    <td>✔</td>
  </tr>
  <tr>
    <th>Bonus Steps: Add pagination to the collection endpoints</th>
    <td>✔</td>
  </tr>
  <tr>
    <th>Bonus Steps: Include the beer search functionality to the API: users should be able to search by any 
beer attribute, such as name, type, nationality, manufacturer…</th>
    <td>✔</td>
  </tr>
  <tr>
    <th>Bonus Steps: Fulfil missing information from other sources</th>
    <td>Pending</td>
    </td>
  </tr>
  <tr>
    <th>Bonus Steps:Include a picture for each beer and allow uploading a file for a beer</th>
    <td>Pending</td>
  </tr> 
</tbody>
</table>

