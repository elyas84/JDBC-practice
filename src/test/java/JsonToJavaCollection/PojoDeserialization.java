package JsonToJavaCollection;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PojoDeserialization {
    /*
    {
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
}
     */

    @Test
    public void oneSpartanWithPojo(){

        Response response = given().accept(ContentType.JSON).pathParam("id",15)
                .when().get("http://54.174.236.45:8000/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        // Json to my Custom Class
        //Dese json to pojo java object
        //Taking response and converting to SPAR object

        SPAR spar1 = response.body().as(SPAR.class);
        System.out.println(spar1.toString());
        System.out.println(spar1.getName());
        System.out.println(spar1.getGender());
        System.out.println(spar1.getPhone());
        System.out.println(spar1.getId());

        assertEquals(spar1.getId(), 15);
        assertEquals(spar1.getName(),"Meta");
        assertEquals(spar1.getGender(),"Female");
        assertEquals(spar1.getPhone(), new Long(1938695106L));




    }

}
