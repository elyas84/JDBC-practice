package POSTMANRETESTING;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Repeat03 {
    @BeforeClass
    public void setUp(){

        baseURI= ConfigurationReader.get("spartanapi.uri");
        baseURI = ConfigurationReader.get("hrapi.uri");
    }

    @Test
    public void test1(){
        Response response = get("/api/spartans");
        assertEquals(response.statusCode(), 200);
        //This called G path--> using Arrays structure! by index number;
        int id = response.path("id[3]");
        String firstName = response.path("name[2]");
        System.out.println("firstName = " + firstName);
        long  phone = response.path("phone[5]");
        System.out.println("phone = " + phone);
        System.out.println("id = " + id);

        //Get all firstName and print out
        List<String> listOfName = response.path("name");
        System.out.println("listOfName = " + listOfName);
        List<Long> allPhone = response.path("phone");
        System.out.println("allPhone = " + allPhone);
        //====================================================
      List<Object> Phone =response.path("phone");
      for (Object telephones : Phone){
          System.out.println("telephones = " + telephones);
      }

    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":\"2\"}").when()
                .get("/countries");
        assertEquals(response.statusCode(), 200);
        System.out.println("count= " + response.path("count"));

        String countryId= response.body().path("items.country_id[0]").toString();
        String countryName =(response.body().path("items.country_name[2]").toString());
        List<String> allCountry = response.body().path("items.country_name");
        System.out.println("countryId = " + countryId);
        System.out.println("countryName = " + countryName);
        System.out.println("allCountry = " + allCountry);

        //Assert that all region id's are equal to 2
        List<Integer> regionIDs = response.path("items.region_id");
        for(int regionId: regionIDs){
            assertEquals(regionId, 2);
        }

    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("q","{\"job_id\":\"IT_PROG\"}").when().get("/employees");
        assertEquals(response.statusCode(), 200);
       List<String> jobIds = response.body().path("items.job_id");
        for (String AllJobIDS : jobIds){
            System.out.println("AllJobIDS = " + AllJobIDS);
            assertEquals(AllJobIDS, "IT_PROG");
        }

    }
    @Test
    public void verifyOneSpartanWithJsonPath(){

        String spartanURL = "http://54.174.236.45:8000";
        // Request part!!
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",11).and()
                .when().get(spartanURL+"/api/spartans/{id}");

        // Response part!!!
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.headers().hasHeaderWithName("Transfer-Encoding"));

        //BODY part
        int id = response.path("id");
        assertEquals(id, 11);
        String name = response.body().path("name");
        assertEquals(name,"Nona");

        // JsonPath

        JsonPath jsonPath = response.jsonPath();
        int jsonID = jsonPath.getInt("id");
        String firstName = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phoneJson = jsonPath.getLong("phone");

        System.out.println("jsonID = " + jsonID);
        System.out.println("firstName = " + firstName);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phoneJson);

        assertEquals(jsonID, 11);
        assertEquals(firstName, "Nona");
        assertEquals(gender, "Female");
        assertEquals(phoneJson, 7959094216L);

    }
    @Test
    public void CountriesWithJsonPath(){
        Response response = get("/countries");
        JsonPath jsonPath = response.jsonPath();

        String countryName = jsonPath.getString("items.country_name[1]");
        System.out.println("countryName = " + countryName);

        String countryID = jsonPath.getString("items.country_id[0]");
        System.out.println("countryID = " + countryID);

        List<String> allList = jsonPath.getList("items.country_id");
        System.out.println("allList = " + allList);
        System.out.println("allList.size() = " + allList.size());

    }
}
