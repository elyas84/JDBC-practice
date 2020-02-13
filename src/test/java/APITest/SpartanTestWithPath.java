package APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class SpartanTestWithPath {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");

    }
    /*
    Given accept type is json
    And path param id is 10
    When user sends a get request to "/spartans/{id}"
    Then status code is 200
    And content-type is "application/json;char"
    And response payload values match the following:
        id is 10,
        name is "Lorenza",
        gender is "Female",
        phone is 3312820936
 */

    @Test
    public void getSpartanWithPath(){
        //request
        Response response = given().accept(ContentType.JSON).and()
                .pathParam("id", 10)
                .when().get("/spartans/{id}");

        //verify content type and status code
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //printing response json body

        System.out.println(response.body().path("id").toString());
        System.out.println("name "+ response.path("name").toString());
        System.out.println("gender:"+response.path("gender").toString());
        System.out.println("PhoneNumber: "+response.body().path("phone").toString());

        //save them
        int id = response.path("id");
        String firstName = response.body().path("name");
        String spartanGender = response.path("gender");
        long phoneNumber= response.path("phone");

        //do assertion
        assertEquals(id,10);
        assertEquals(firstName,"Lorenza");
        assertEquals(spartanGender,"Female");
        assertEquals(phoneNumber,3312820936L);

    }

    @Test
    public void getAllSpartansWithPath(){

        Response response = get("/spartans");
        assertEquals(response.statusCode(),200);

        // Print the first id
        int firstId = response.path("id[3]");
        System.out.println("firstId = " + firstId);

        // Print first name from the all spartans

        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        long phone = response.path("phone[0]");
        System.out.println("phone = " + phone);

        // -1 gives us the LAST NAME IN THE WHOLE LIST

        String lastName = response.path("name[-1]");
        System.out.println("lastName = " + lastName);

        // get all first name and print out from the table
        List<String> allFirstName = response.path("name");
        System.out.println("allFirstName = " + allFirstName.size());

        // get the All Ids
        List<Integer> allIdNumber = response.path("id");
        System.out.println("allIdNumber = " + allIdNumber);


        // Object it will bring everything!!
        List<Object> allPhoneNumber = response.path("phone");
        for (Object number : allPhoneNumber){
            System.out.println("number = " + number);
        }

      /*
        This is how we print on one shot!!!!
        System.out.println("allPhoneNumber = " + allPhoneNumber);

       */





    }
}

