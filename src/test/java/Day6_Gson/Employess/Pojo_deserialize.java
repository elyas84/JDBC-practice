package Day6_Gson.Employess;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import javax.security.auth.login.Configuration;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Pojo_deserialize {

    @BeforeClass

    public void setUp(){
        RestAssured.baseURI= ConfigurationReader.get("hrapi.uri");
    }

    @Test
    public void test1(){

        Response response = given().get("/employees");
        assertEquals(response.statusCode(), 200);

        EMPLOYEES employeesInfo = response.body().as(EMPLOYEES.class);
        System.out.println(employeesInfo.getItems().get(12).getFirstName() + "|" + employeesInfo.getItems().get(12).getLastName() + "|" + employeesInfo
                .getItems().get(12).getPhoneNumber() + "|" + employeesInfo.getItems().get(12).getHireDate() + "|" + employeesInfo.getItems().get(12)
                .getSalary());
    }
}
