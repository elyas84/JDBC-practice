package Day8_JsomSchema;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.given;

public class JsonSchemaValidation {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }

    @Test
    public void jsonSchemaValidationForSpartan(){

        given().accept(ContentType.JSON).pathParam("id", 25)
                .when().get("/spartans/{id}").then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));


    }



}
