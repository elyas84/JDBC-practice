package APITest.JamalHomeWork;


import com.sun.xml.xsom.impl.scd.Iterators;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeworkOne {
    @BeforeClass
    public void setUpClass() {
        RestAssured.baseURI = ConfigurationReader.get("hrapi.uri");

    }

    /*
                - Given accept type is Json
            - Path param value- US
            - When users sends request to /countries
            - Then status code is 200
            - And Content - Type is Json
            - And country_id is US
            - And Country_name is United States of America
            - And Region_id is 2
             ===================================================================
                 */
    @Test
    public void homeWorkTest1() {

        given().accept(ContentType.JSON)
                .pathParam("country_id", "US").
                when().get("/countries/{country_id}").
                then().assertThat()
                .statusCode(200).and().assertThat().contentType("application/json").assertThat().and().body("country_id", equalTo("US"))
                .assertThat().and().body("country_name", equalTo("United States of America")).and().assertThat().body("region_id", equalTo(2));
    }

    @Test
    public void homeWorkTest2() {
        /*
        Q2:
                - Given accept type is Json
                - Query param value - q={"department_id":80}
                - When users sends request to /employees
                - Then status code is 200
                - And Content - Type is Json
                - And all job_ids start with 'SA'
                - And all department_ids are 80
                - Count is 25
                 ===================================================================
         */


       given().accept(ContentType.JSON).queryParam("q","{\"department_id\":\"80\"}")
               .and().get("/employees/").then().assertThat().statusCode(200).and()
               .assertThat().contentType("application/json").assertThat()
               .assertThat().body("count",equalTo(25));

       // ======================================================

        Response response = given().accept(ContentType.JSON).queryParam("q","{\"department_id\":\"80\"}")
                .and().get("/employees/");
        List<String> jobIds = response.path("items.job_id");
        System.out.println("jobIds = " + jobIds);
        assertTrue(response.body().asString().contains("SA"));
        List<Integer> departmentID = response.path("items.department_id");
        assertTrue(departmentID.contains(80));

       }


       @Test
    public void homeWork1test3(){

        /*
        Q3:
                - Given accept type is Json
                -Query param value q= region_id 3
                - When users sends request to /countries
                - Then status code is 200
                - And all regions_id is 3
                - And count is 6
                - And hasMore is false
                - And Country_name are;
                Australia,China,India,Japan,Malaysia,Singapore

                ===================================================================
         */

        given().accept(ContentType.JSON).queryParam("q","{\"region_id\":3}")
                .and().get("/countries").then().assertThat().statusCode(200).and().assertThat().body("count",equalTo(6))
                .and().assertThat().body("hasMore",equalTo(false)).and().
                body("items.country_name",hasItems("Australia","China","India","Japan","Malaysia","Singapore"));


        Response response = given().accept(ContentType.JSON).queryParam("q","{\"region_id\":3}")
                .and().get("/countries");
        List<Integer> regionIDs = response.path("items.region_id");
        assertTrue(regionIDs.contains(3));


       }





}
