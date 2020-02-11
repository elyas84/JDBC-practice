package APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HRApiTestes {

    String regionAllUrl ="http://54.174.236.45:1000/ords/hr/regions";
    String spartanUrl = "http://54.174.236.45:8000/api/spartans/3";

    @Test
    public void getAllRegionTest(){


        Response response = RestAssured.get(regionAllUrl);
        System.out.println("============== Starting coding ==============");

        System.out.println("Status code = " + response.statusCode());
        System.out.println("Content type = " + response.contentType());
        System.out.println("Pretty Print = " + response.body().prettyPrint());

        System.out.println("============== Verifying ==============");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Americas"));
        Assert.assertTrue(response.body().asString().contains("Europe"));

    }

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.XML).get(spartanUrl);

        System.out.println("response = " + response.statusCode());
        System.out.println("Wrong message = " + response.body().prettyPrint());

        Assert.assertEquals(response.statusCode(),406);

    }

    @Test
    public void test3(){

        Response response = RestAssured.given().accept(ContentType.JSON).get(regionAllUrl);
        System.out.println("response = " + response.statusCode());
        System.out.println("response = " + response.contentType());

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Europe"));

            /*
            import static io.restassured.RestAssured.*;
            import static org.testng.Assert.*;
             */

    }
}
