package JsonToJavaCollection;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ConvertingGsonTOJava {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }

    @Test
    public void SpartanJsonToMap(){
        Response response = given().accept(ContentType.JSON).pathParam("id",20)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        /*

            "id": 20,
            "name": "Lothario",
            "gender": "Male",
            "phone": 7551551687
}
         */
        // To converting Json to Map(java)--> and use as(Map.class) <--- this is a most!!!!
        Map<String, Object> spartanMap = response.body().as(Map.class);// --> here we are using Gson to convert Json to Java
        System.out.println("spartanMap = " + spartanMap);
        String name = String.valueOf(spartanMap.get("name"));
        System.out.println("name = " + name);
        int ID = (int) spartanMap.get("id");
        System.out.println("ID = " + ID);
        String gender = (String) spartanMap.get("gender");
        System.out.println("gender = " + gender);
        long phoneNumber = (long) spartanMap.get("phone");
        System.out.println("phoneNumber = " + phoneNumber);
        assertEquals(name,"Lothario");
        assertEquals(ID, 20);
        assertEquals(gender, "Male");
        assertEquals(phoneNumber, 7551551687L);

    }

    @Test
    public void allSpartanToTheList(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        List<Map<String,Object>> allSpartans = response.body().as((List.class));
        System.out.println("allSpartans = " + allSpartans);
        System.out.println("allSpartans.size() = " + allSpartans.size());
        //To get a special map then we gonna use index!
        System.out.println("allSpartans.get(2) = " + allSpartans.get(2));

        String name = (String) allSpartans.get(2).get("name");
        assertEquals(name, "Nels");
        String gender = (String) allSpartans.get(2).get("gender");
        assertEquals(gender, "Male");

    }

    @Test
    public void regionJsonToMap(){
        Response response = get("http://54.174.236.45:1000/ords/hr/regions");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json");
        response.body().prettyPrint();
        Map<String, Object>regionMap = response.body().as(Map.class);
        System.out.println("=================================================");
        System.out.println("regionMap.get(\"count\") = " + regionMap.get("count"));
        System.out.println("regionMap.get(\"items\") = " + regionMap.get("items"));

        // We are on the Map --> then we can get the map value by entering index and name of value!
        List<Map<String, Object>> listOfMap = (List<Map<String, Object>>) regionMap.get("items");
        System.out.println("listOfMap.get(0).get(\"region_id\") = " + listOfMap.get(0).get("region_id"));

    }

}
