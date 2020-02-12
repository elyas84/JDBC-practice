package APITest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;


public class HRApiTestWithJsonPath {

        @BeforeClass
        public void setUpClass(){
            baseURI = ConfigurationReader.get("hrapi.uri");
        }
    @Test
    public void countriesWithJsonPath(){
        //request
        Response response = get("/countries");

        //put response body to JsonPath object
        JsonPath jsonData = response.jsonPath();

        //read second country name -Australia
        String secondCountryName = jsonData.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<String> countryIds=jsonData.getList("items.country_id");
        System.out.println("countryIds.size = " + countryIds.size());
        System.out.println("countryIds = " + countryIds);

    }

    @Test
    public void findAllEmployeesExample(){
        //request
        Response response = get("/employees");

        //put response body to JsonPath object
        JsonPath jsonData = response.jsonPath();

        //get me all first_name of employees who is making more than 10000
        List<String> firstnames = jsonData.getList("items.findAll {it.salary <10000}.first_name");
        System.out.println("firstnames = " + firstnames);
    }


}


