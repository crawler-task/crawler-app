   
If you want to run application locally you have to clone branch https://github.com/crawler-task/crawler-app.git
The project has own maven that's why we can use  
 Windows  
 Create a jar file  
    - mvnw.cmd clean package   
 Run tests  
    -mvnw.cmd test  
 Unix  
Create a jar file  
    - ./mvnw clean package  
Run tests  
    - ./mvnw test 
 
For run application we should move to the root directory of crawler-app project then call  
java -jar target/crawler-app-0.0.1-SNAPSHOT.jar

After running application we can use a dynamic endpoints documentation (Swagger) 
http://localhost:8080/crawler-app/swagger-ui.html

E.g for interview task we can use parameters like.

maxNestedCrawling (optional) e.g 
- 0 search only main website without nested links
- maxNestedCrawling > 0 specific number of nested websites
- without maxNestedCrawling parameter crawl all website   

url 
- https://wiprodigital.com

Full curl example

`curl --location --request GET 'http://localhost:8080/crawler-app/websites?url=https://wiprodigital.com&maxNestedCrawling=0'`

or we can use a swagger

What could be done as a future extension.

- We should enhance a regex for searching file extensions
- After increase out project we should change approach for creation a test data
- Should create a correct object for a error response. Currently we return only BAD_REQUEST
- If this project will be crucial we can create a integration test but currently I created Unit tests
- Currently we recognise a internal domain using of url parameter. Maybe in the future we want to pass two different urls
1 - main website 2 - internal pattern


