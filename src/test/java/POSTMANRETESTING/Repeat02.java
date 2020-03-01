package POSTMANRETESTING;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Repeat02 {

    @BeforeClass
    public void setUp(){

        baseURI= ConfigurationReader.get("spartanapi.uri");
        baseURI = ConfigurationReader.get("hrapi.uri");
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.TEXT).when().get("/api/hello");
        // Verifying response
        assertEquals(response.statusCode(), 200);
        // Verifying Content.Type
        assertEquals(response.contentType(),"text/plain;charset=UTF-8");
        //Verifying we have header name called Date / for verifying the existence of the header in the Body " table"
        //First headers().HasHeaderWithName --> to Verifying the existence.
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals(response.getHeader("Content-Length"), "17"); // This is for looking for the Existence of any value.
        assertEquals(response.body().asString(), "Hello from Sparta");

    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).pathParam("id", 5)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
       assertTrue(response.body().asString().contains("Blythe"));

    }

    @Test
    public void test3(){
     Response response =  given().accept(ContentType.JSON).and().pathParam("id", 500).when().get("/api/spartans/{id}");
     assertEquals(response.contentType(),"application/json;charset=UTF-8");
     assertEquals(response.statusCode(), 404);
     assertTrue(response.body().asString().contains("Spartan Not Found"));


    }

    @Test
    public void test4(){
        /* When we create a MAP then we don't need to put every query one by one!
            Because Map can keep our value, wherever we need to use!
         */

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender","Male");
        queryMap.put("nameContains","e");


        Response response = given().accept(ContentType.JSON).and().and()
                .queryParams(queryMap)
              /*
               .queryParam("gender", "Male")
               .queryParam("nameContains", "e")

               */
                .when().get("/api/spartans/search");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Hershel"));

    }

    @Test
    public void test5(){
        // Navigate Json--> JAVAScript Object Notation

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("/api/spartans/{id}");
                assertEquals(response.statusCode(), 200);
                assertEquals(response.contentType(), "application/json;charset=UTF-8");

        System.out.println(response.body().path("id").toString()); // to.String--> it returns something different!
        System.out.println(response.body().path("name").toString());
        System.out.println(response.body().path("gender").toString());
        System.out.println(response.body().path("phone").toString());
        // save them

        int id= response.body().path("id");
        String name = response.body().path("name");
        String gender = response.body().path("gender");
        long phone = response.body().path("phone");

        //Assertion

        assertEquals(id, 10);
        assertEquals(name, "Lorenza");
        assertEquals(gender, "Female");
        assertEquals(phone, 3312820936L);


    }

    @Test
    public void test6(){

        Response response = given().accept(ContentType.JSON).and()
                .queryParam("q", "{\"region_id\":\"2\"}").when()
                .get("/countries");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("United States of America"));
    }

    @Test
    public void test7(){

        Response response = given().accept(ContentType.JSON).and()
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}").when()
                .get("/employees");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        System.out.println(response.header("date"));
        System.out.println(response.header("Content-Type"));
        System.out.println(response.header("ETag"));
        System.out.println(response.header("Transfer-Encoding"));
        /*
        When I wrote Header --> it gives me the corespondent value to them;
         */


    }

}
