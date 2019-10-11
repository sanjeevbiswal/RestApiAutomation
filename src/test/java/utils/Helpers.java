package utils;

import io.restassured.path.json.JsonPath;

public class Helpers {

    public static String getJsonData(String jsonValue,String value){

        JsonPath js=new JsonPath(jsonValue);
        return js.get(value).toString();
    }
}
