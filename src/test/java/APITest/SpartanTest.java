package APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SpartanTest {
    String spartanAllUrl ="http://54.174.236.45:8000/api/spartans";

    @Test
    public void viewAllSpartans(){
       Response response= RestAssured.get(spartanAllUrl);

       //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());


        //pretty

        System.out.println(response.body().prettyPrint());

    }

    @Test
    public void viewAllSpartanTest2(){
           /* when user send GET request to /api/spartans end point
        Then status code must be 200
        And body should contains Orion
     */
           Response response = RestAssured.get(spartanAllUrl);

            //verify status code is 200
            Assert.assertEquals(response.statusCode(),200);
            //verify body includes Orion
            Assert.assertTrue(response.body().asString().contains("Orion"));

        }

        @Test
    public void viewAllSpartanTest3(){
        Response response = given().accept(ContentType.JSON).when().get(spartanAllUrl);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");

        }


        @Test
    public void test4(){
 /*
        Given accept type application/xml
        Then user send GET request to / api/ spartans end point
        Then status code should be 200
        And Response Content type must be xml
        And body should contains Orion
         */

 Response response = given().accept(ContentType.XML).when().get(spartanAllUrl);
            Assert.assertEquals(response.statusCode(),200);
            Assert.assertEquals(response.contentType(),"application/xml;charset=UTF-8");
            Assert.assertTrue(response.body().asString().contains("Orion"));




        }
        @Test
    public void test5(){

               Response response=  given().accept(ContentType.JSON).when().get(spartanAllUrl+"/3");

               Assert.assertEquals(response.statusCode(),200);;
            Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
            Assert.assertTrue(response.body().asString().contains("Fidole"));

        }

//TASK
    /*
        Create a new class HRApiTests
        create a @Test getALLRegionsTest
        send a get request to AllRegions API endpoint
        -print status code
        -print content type
        -pretty print response JSON
        verify that status code is 200
        verify that content type is "application/json"
        verify that json response body contains "Americas"
        verify that json response body contains "Europe"
        *try to use static imports for both RestAssured and testing
        *store response inside the Response type variable
        */
    /*
    /*
        Given the accept type XML
        When I send get request to /api/spartans/3
        Then status code must be 406
     */



}
