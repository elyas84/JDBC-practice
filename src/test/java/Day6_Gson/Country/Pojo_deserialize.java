package Day6_Gson.Country;


import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.testng.Assert.assertEquals;

public class Pojo_deserialize {
    public void setUpClass(){
        RestAssured.baseURI = ConfigurationReader.get("hrapi.uri");

    }
    @Test
    public void test1(){

        Response response = get("http://54.174.236.45:1000/ords/hr/countries");
        assertEquals(response.statusCode(),200);

        Countries countriesInfo = response.body().as(Countries.class);
        List<Item> CountryList = countriesInfo.getItems();
        System.out.println("CountryList = " + CountryList.size());
        System.out.println(countriesInfo.getItems().get(2).getCountryName()+" | " +" | "+ countriesInfo.getItems().get(2).getCountryId());

    }

    @Test
    public void test2(){
        Response response = get("http://54.174.236.45:1000/ords/hr/");
        assertEquals(response.statusCode(),200);
        
    }
}
