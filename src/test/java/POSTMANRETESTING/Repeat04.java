package POSTMANRETESTING;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.equalTo;

public class Repeat04 {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI= ConfigurationReader.get("cbtapi.uri");

    }
    @Test
    public void getAsStudentWithJsonPath(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("studentId",5710).when().get("/student/{studentId}");
        assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = response.jsonPath();
        String firstName = jsonPath.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);
        int batchNumber = jsonPath.getInt("students.batch[0]");
        System.out.println("batchNumber = " + batchNumber);
        String companyName = jsonPath.getString("students.company.companyName[0]");
        System.out.println("companyName = " + companyName);
        int zipCode = jsonPath.getInt("students.company.address.zipCode[0]");
        System.out.println("zipCode = " + zipCode);
        assertEquals(zipCode, 22102);

    }

    @Test
    public void validationWithHamCrest(){

                 given().accept(ContentType.JSON).and().pathParam("id",11)
                .when().get("http://54.174.236.45:8000/api/spartans/{id} ")
                .then().assertThat().statusCode(200).and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("id", equalTo(11),"name",equalTo("Nona"),"gender",equalTo("Female")
                 ,"phone",equalTo(7959094216L));

                 // When we use HamcrestMatherMethod, We should put Assertion inside the BODY and put equal TO()!!!;

    }
    @Test
    public void test2(){
        given().accept(ContentType.JSON).pathParam("name","Johnny")
                .when().get("http://api.cybertektraining.com/teacher/name/{name}").then()
                .assertThat().statusCode(200).assertThat().contentType("application/json;charset=UTF-8")
                .assertThat().body("teachers.firstName[0]",equalTo("Johnny"),"teachers.emailAddress[0]",equalTo("string@gmail.com"),
                "teachers.department[0]",equalTo("Math"),"teachers.salary[0]",equalTo(1000));
    }

    @Test
    public void test4(){
        given().accept(ContentType.JSON).pathParam("name","Computer")
                .when().get("http://api.cybertektraining.com/teacher/department/{name}").then()
                .assertThat().statusCode(200).assertThat().contentType("application/json;charset=UTF-8")
                .assertThat().body("teachers.firstName[0]", equalTo("John"),"teachers.password[0]",equalTo("12343"));
    }

    /*
    Teacher/department/{name}-->Computer
    Verify that statusCode, contentType
    verify that firstName is includes john, Mariya, Anvar
     */
    @Test
    public void test5(){
        given().accept(ContentType.JSON).pathParam("name","Computer")
                .when().get("http://api.cybertektraining.com/teacher/department/{name}").then()
                .assertThat().statusCode(200).assertThat().contentType("application/json;charset=UTF-8")
                .assertThat().body("teachers.firstName",hasItems("John","Mariya","Anvar"));

        /*
        WHEN WE USE HAMCREST WE HAVE TO USE INSIDE BODY( EQUAL TO());
         */
    }
}
