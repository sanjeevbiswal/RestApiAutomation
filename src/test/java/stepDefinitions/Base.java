package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public static String getPropertiesData(String value) throws IOException {
        File file=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\app.properties");
        System.out.println(System.getProperty("user.dir")+"\\src\\main\\resources\\app.properties");
        FileInputStream fis=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(fis);
        return prop.getProperty(value);
    }

    @Before
    public void initializeTest(){
        System.out.println("Test Initiated");
    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()) {
            System.out.println(scenario.getName()  +"    Failed");
        }else {

                System.out.println(scenario.getName()  +"     Passed");

        }
    }
}
