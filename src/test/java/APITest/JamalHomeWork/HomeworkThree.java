package APITest.JamalHomeWork;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class HomeworkThree {

    @BeforeClass
    public void setUp(){

        RestAssured.baseURI = ConfigurationReader.get("fakeNews_uri");
    }

    /*
    No params test
1. Send a get request without providing any parameters
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that name, surname, gender, region fields have value
     */
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).and()
                .get("");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
       response.prettyPrint();

    }
/*
Gender test
1. Create a request by providing query parameter: gender, male or female
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that value of gender field is same from step 1
 */


    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("gender","male").and()
                .get("");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertEquals(response.body().path("gender"),"male");
        response.body().prettyPrint();
    }

    /*
    2 params test
1. Create a request by providing query parameters: a valid region and gender
NOTE: Available region values are given in the documentation
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that value of gender field is same from step 1
4. Verify that value of region field is same from step 1
     */
    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("gender","female")
                .queryParam("region","Sweden" )
                .get("");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertEquals(response.body().path("gender"),"female");
        assertEquals(response.body().path("region"),"Sweden");
        response.body().prettyPrint();

    }

    /*
    Invalid gender test
1. Create a request by providing query parameter: invalid gender
2. Verify status code 400 and status line contains Bad Request
3. Verify that value of error field is Invalid gende
     */
    @Test
    public void test4(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","hen").and()
                .get("");
        assertEquals(response.statusCode(),400);
        String invalidValue= response.prettyPrint();
        assertTrue(invalidValue.contains("Invalid gender"));
    }

    /*
    Invalid region test
1. Create a request by providing query parameter: invalid region
2. Verify status code 400 and status line contains Bad Request
3. Verify that value of error field is Region or language not found

     */
    @Test
    public void test5(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("region","Gävle").and()
                .get("");
        System.out.println("response.statusLine() = " + response.statusLine());
        String invalidValue= response.prettyPrint();
        assertTrue(invalidValue.contains("Region or language not found"));
    }

    /*
    Amount and regions test
1. Create request by providing query parameters: a valid region and amount (must be bigger than 1)
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that all objects have different name+surname combination
     */
    @Test
    public void test6() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("region", "Sweden").and()
                .queryParam("amount", 2)
                .get("");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");
        List<String> stringList = response.path("name");
        List<String> stringList1 = response.path("surname");


    }
/*
3 params test
1. Create a request by providing query parameters: a valid region, gender and amount (must be bigger
than 1)
2. Verify status code 200, content type application/json; charset=utf-8
3. Verify that all objects the response have the same region and gender passed in step
 */

    @Test
    public void test7(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("region","Sweden").and()
                .queryParam("amount",2).and()
                .queryParam("gender","female")
                .get("");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        List<String> stringList = response.path("name");
        List<String> stringList1 =response.path("surname");
        List<String> stringList3 = response.path("gender");




    }

}
