package Day6_Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class JsonToJavaCollections {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }

    @Test
    public void spartansJsonToMap() {
        Response response = given().accept(ContentType.JSON).pathParam("id", 15)
                .when().get("spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //Convert json result to java Map
        Map<String, Object> spartanMap = response.body().as(Map.class);
        System.out.println("spartanMap = " + spartanMap.toString());
        String name = (String) spartanMap.get("name");
        System.out.println("name = " + name);
        assertEquals(name, "Meta");

    }

    @Test
    public void allSpartansToList() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        List<Map<String, Object>> allSpartansMap = response.body().as(List.class);
        System.out.println("allSpartansMap = " + allSpartansMap.size());
        System.out.println("allSpartansMap = " + allSpartansMap.get(0));
        System.out.println("allSpartansMap = " + allSpartansMap.get(0).get("name"));

        String name = (String) allSpartansMap.get(21).get("name");

    }

    @Test
    public void regionJsonMap() {
        Response response = get("http://54.174.236.45:1000/ords/hr/regions");
        assertEquals(response.statusCode(), 200);

        Map<String, Object> regionMap = response.body().as(Map.class); // here is most important step to remember!
        System.out.println("regionMapCount = " + regionMap.get("count"));
        System.out.println("regionMapLimit = " + regionMap.get("limit"));
        System.out.println("regionMapItems = " + regionMap.get("items"));

        List<Map<String, Object>> itemsList = (List<Map<String, Object>>) regionMap.get("items");
        String region_name = (String) itemsList.get(3).get("region_name");
        System.out.println("region_name = " + region_name);
    }
}