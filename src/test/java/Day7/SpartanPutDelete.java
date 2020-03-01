package Day7;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
public class SpartanPutDelete {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }

    @Test
    public void updatingSpartan(){

        Map<String, Object> putMap = new HashMap<>();
        putMap.put("name","Mikela");
        putMap.put("gender","Female");
        putMap.put("phone",45678912345L);
        given().pathParam("id",50).and().contentType(ContentType.JSON).contentType(ContentType.JSON).body(putMap).when().put("/spartans/{id}")
                .then().assertThat().statusCode(204);
    }

    @Test
    public void deleteSpartans(){
        Random random = new Random();
        int idToDelete = random.nextInt(150)+2;
        System.out.println("idToDelete = " + idToDelete);
        given().pathParam("id",idToDelete).when().delete("/spartans/{id}").then().assertThat().statusCode(204);
    }
}
