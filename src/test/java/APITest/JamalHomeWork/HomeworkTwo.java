package APITest.JamalHomeWork;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class HomeworkTwo {
    @BeforeClass
    public void setUpClass() {
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");

    }
    @Test
    public void test1(){

        /*
        SPARTAN API
Q1:
Given accept type is json
And path param id is 20
When user sends a get request to "/spartans/{id}"
Then status code is 200
And content-type is "application/json;char"
And response header contains Date
And Transfer-Encoding is chunked
And response payload values match the following:
id is 20,
name is "Lothario",
gender is "Male",
phone is 7551551687
===================================================
        */


        given().accept(ContentType.JSON).and().pathParam("id",20)
                .get("/spartans/{id}").then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8");

        Response response =  given().accept(ContentType.JSON).and().pathParam("id",20)
                .get("/spartans/{id}");
        assertTrue(response.headers().hasHeaderWithName("Date"));
        System.out.println("response = " + response.getHeader("Transfer-Encoding"));
        System.out.println("response = " + response.body().prettyPrint());

    }
    @Test
    public void test2(){

        /*
        Q2:
Given accept type is json
And query param gender = Female
And query param nameContains = r
When user sends a get request to "/spartans/search"
Then status code is 200
And content-type is "application/json;char"
And all genders are Female
And all names contains r
And size is 20
And totalPages is 1
And sorted is false
         */

      given().accept(ContentType.JSON).and().get("/spartans/search?gender=Female&nameContains=r")
              .then().assertThat().statusCode(200).and().contentType("application/json;charset=UTF-8");

      Response response =  given().accept(ContentType.JSON).and().get("/spartans/search?gender=Female&nameContains=r");
        List<String> nameList= response.path("content.name");
        System.out.println("nameList = " + nameList);
        assertTrue(nameList.contains("R"));
        int sizNumber = response.body().path("size", "20");
        assertEquals(sizNumber,20);
        String totalPage = response.path("totalPages", "1");
        assertEquals(totalPage, 1);




    }
}
