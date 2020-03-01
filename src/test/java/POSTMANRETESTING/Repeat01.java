package POSTMANRETESTING;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class Repeat01 {
    String spartansURL = "http://54.174.236.45:8000/api/spartans";
    String hrURL = "http://54.174.236.45:1000/ords/hr";
    @Test
    public void test1(){

        Response response = RestAssured.get(spartansURL);
        System.out.println(response.statusCode());
        response.body().prettyPrint();

    }

    @Test
    public void test2(){
        Response response = RestAssured.get(spartansURL);
        Assert.assertEquals(response.statusCode(), 200);
        //When we verifying the Response body, and we gonna use [response.body().asString()]
        Assert.assertTrue(response.body().asString().contains("Orion"));
    }

    @Test
    public void test3(){
        Response response = RestAssured.get(spartansURL);
        System.out.println(response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Orion"));

    }

    @Test
    public void test4(){

        // This is an other way to implement the test Execution.
     Response response = given().accept(ContentType.JSON).get(spartansURL);
     Assert.assertEquals(response.statusCode(), 200);
     Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
     Assert.assertTrue(response.body().asString().contains("Orion"));

    }

    @Test
    public void test5(){
        Response response = given().accept(ContentType.XML).get(spartansURL);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/xml;charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Orion"));

    }

    @Test
    public void test6(){
        //HAMCREST CHAINING
                 given().accept(ContentType.JSON).when().get(spartansURL)
               .then().statusCode(200).and().contentType("application/json;charset=UTF-8");
    }

    @Test
    public void test7(){

         Response response =given().accept(ContentType.JSON).when().get(spartansURL+"/3");
         Assert.assertEquals(response.statusCode(), 200);
         Assert.assertEquals(response.contentType(), "application/json;charset=UTF-8");
         Assert.assertTrue(response.body().asString().contains("Fidole"));

    }

    @Test
    public void test8(){
        Response response = given().accept(ContentType.JSON).get(hrURL+"/regions");
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        response.body().prettyPrint();

        Assert.assertEquals(response.statusCode(),200 );
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertTrue(response.body().asString().contains("Americas"));
        Assert.assertTrue(response.body().asString().contains("Europe"));

    }



}
