package APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class HRApiTestWithPath {

    @BeforeMethod
    public void setUpMethod(){
        RestAssured.baseURI= ConfigurationReader.get("hrapi.uri");
    }

    @Test
    public void getCountriesWithPath(){
        Response response = given().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);

        System.out.println("========================================");
        //count values
        System.out.println(response.path("count").toString());


         /*
            items.country_id[0] --> AR
            items.country_name[1]   -->Brazil
            items.links[2].href --> "http://54.161.128.36:1000/ords/hr/countries/CA"
            items.country_name --> list with all countries names
            */

         String firstCountry_id = response.path("items.country_id[0]");
         String secondCountryName = response.path("items.country_name[1]");
        System.out.println("firstCountry_id = " + firstCountry_id);
        System.out.println("secondCountryName = " + secondCountryName);

        Object link2 = response.path("items.links[2].href");
        System.out.println("link2 = " + link2);

        List<Object> nameOfCountries = response.path("items.country_name");
        System.out.println("name of countries = " + nameOfCountries);

       List<Object> allRegionsId = response.path("items.region_id");
        for (Object regionId : allRegionsId){
            assertEquals(regionId,2);
        }


    }

    @Test

    public void countriesWithQueryParam2(){
        Response response = given().accept(ContentType.JSON).and()
                .queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));

        //assert that all job_id s are IT_PROG
        List<String> jobIDs = response.path("items.job_id");

        for (String jobID : jobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals(jobID,"IT_PROG");
        }


    }

}
