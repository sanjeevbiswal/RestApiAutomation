package stepDefinitions;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import utils.Helpers;

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


}
