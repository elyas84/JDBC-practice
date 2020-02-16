package Day6_Gson;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class POJO_deserialize {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }


    @Test

    public void oneSpartanWithPOJO(){
        Response response = given().accept(ContentType.JSON).pathParam("id", 15)
                .when().get("spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //JSON to my Custom Class (POJO)
        //deserialize json to pojo to Spartan object
        //taking response and converting to Spartans object

        SPARTAN spartan = response.body().as(SPARTAN.class);

        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        assertEquals(spartan.getId(), 15);
        assertEquals(spartan.getName(),"Meta");
        assertEquals(spartan.getGender(),"Female");
        assertEquals(spartan.getPhone(),new Long(1938695106));

    }

    @Test
    public void regionsWithPojo(){

        Response response = get("http://54.174.236.45:1000/ords/hr/regions");
        assertEquals(response.statusCode(),200);
        Region regions = response.body().as(Region.class);

        List<Item> regionList =  regions.getItems();
        System.out.println(regionList.get(0).getRegionName());
        System.out.println("regionList.get(1).getRegionId() = " + regionList.get(1).getRegionId());

        System.out.println(regions.getItems().get(0).getRegionName());

        for (Item item : regionList) {
            System.out.println(item.getRegionName());
        }

    }

    @Test
    public void GsonExample(){

        //Creating Gson object
        Gson gson = new Gson();

        //Deserialize and serialize with gson object

        String myJson = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";
        //Converting Json to pojo( SPARTAN CLASS)
       SPARTAN spartan15 = gson.fromJson(myJson, SPARTAN.class);

        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());
        //-----------------------------------------SERIALIZATION---------------
        //Java object to Json

        SPARTAN spartanEU = new SPARTAN(1156, "Elyas", "Male",12345899L);
        String jsonSpartansEU = gson.toJson(spartanEU);
        System.out.println("jsonSpartansEU = " + jsonSpartansEU);


    }

}

