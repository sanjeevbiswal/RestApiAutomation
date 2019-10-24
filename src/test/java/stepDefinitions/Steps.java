package stepDefinitions;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import utils.Helpers;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Steps {

    Logger logger = LogManager.getLogger(Steps.class.getName());

    @Given("^I got response from rest api$")
    public void i_got_response_from_rest_api() throws Throwable {
        RestAssured.useRelaxedHTTPSValidation();
        Response response = RestAssured.given()
                .queryParam("appid", "b6907d289e10d714a6e88b30761fae22")
                .and().queryParam("id", "2172797").get(Base.getPropertiesData("weatherAPIURL")).then()
                .assertThat().statusCode(200).extract().response();
        logger.info(Helpers.getJsonData(response.asString(), "coord"));
        logger.info(Helpers.getJsonData(response.asString(), "main"));
        logger.info(Helpers.getJsonData(response.asString(), "sys.country"));
        logger.info(Helpers.getJsonData(response.asString(), "cod"));

    }

    @Given("^I get hourly data from hourly api$")
    public void i_get_hourly_data_from_hourly_api() throws Throwable {
        RestAssured.useRelaxedHTTPSValidation();
        Response response2 = RestAssured.given()
                .queryParam("appid", "b6907d289e10d714a6e88b30761fae22")
                .and().queryParam("id", "524901").get(Base.getPropertiesData("hourlyAPI")).then()
                .assertThat().statusCode(200).extract().response();
        logger.info(Helpers.getJsonData(response2.asString(), "cod"));
        logger.info(Helpers.getJsonData(response2.asString(), "message"));
        logger.info(Helpers.getJsonData(response2.asString(), "city.id"));
        logger.info(Helpers.getJsonData(response2.asString(), "city.name"));


    }

    @Given("^I got respnse from animal api$")
    public void i_got_respnse_from_animal_api() throws Throwable {
       RestAssured.useRelaxedHTTPSValidation();
        Response res = RestAssured.given().get("https://cat-fact.herokuapp.com/facts").then().extract().response();
        String users = Helpers.getJsonData(res.asString(), "all.type");
        String[] usrs = users.split(",");
        for(String val:usrs){
            logger.info(val);
        }
    }

    @Given("^I got holiday list for Country \"([^\"]*)\" and Year (\\d+)$")
    public void i_got_holiday_list_for_Country_and_Year(String country, int year) throws Throwable {
        logger.info("Testing holiday list for "+country+" and year:"+year);

        Response res = RestAssured.given().relaxedHTTPSValidation().
                and().queryParam("token", Base.getPropertiesData("holidayListKey")).log().all().
                when().get(Base.getPropertiesData("holidayListEndPoint")).then().statusCode(200).extract().response();

        String category=Helpers.getJsonData(res.asString(),"result.category");
        logger.info(category);
        String[] val = category.split(",");
        System.out.println(val.length);
        HashSet<String> hs=new HashSet<String>();
        for(String value:val){
            hs.add(value);
        }

        System.out.println(hs.size());

        HashMap<String,Integer> hm=new HashMap<>();
        for(String value:val){
            if (hm.containsKey(value)) {
                Integer count = hm.get(value);
                hm.put(value,count+1);
            }else{
                hm.put(value,1);
            }
        }
        logger.info(hm.toString());



    }


}
