package com.example;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class TestClass {

    @Test
    public void test1_getAllUsers(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        given().
                header("Content-Type", "application/json").
                get("users").
                then().
                statusCode(200).
                body("username", hasItems("Bret", "Antonette", "Samantha" )). // we can add more here
                body("email", hasItems("Nathan@yesenia.net"));  // we and add more
    }

    @Test
    public void test2_getUserById(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        given().
                header("Content-Type", "application/json").
                param("id", 1).
                get("users").
                then().
                statusCode(200).
                body("id[0]", equalTo(1)).
                body("username[0]", equalTo("Bret")).
                body("email[0]", equalTo("Sincere@april.biz"));
    }

    @Test
    public void test3_postRequest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        JSONObject jsonObject = new JSONObject();
        // for address
        jsonObject.put("id", 11);
        jsonObject.put("name", "Habib");
        jsonObject.put("username", "habibmridha");
        jsonObject.put("email", "xyz@co.com");

        JSONArray array=new JSONArray();
        JSONObject item=new JSONObject();
        item.put("street", "ABC Street");
        item.put("suite", "Apt. 10C");
        item.put("city","NYC");
        item.put("zipcode", "10000");
        array.add(item);
        jsonObject.put("address",array);

        JSONArray geo=new JSONArray();
        JSONObject geoItm=new JSONObject();
        geoItm.put("lat", "-37.100");
        geoItm.put("lng", "80.1234");
        geo.add(geoItm);
        item.put("geo",geo);
        // for company
        JSONArray company=new JSONArray();
        JSONObject companyItm=new JSONObject();
        companyItm.put("name","AZ com LLC");
        companyItm.put("catchPhrase","Multi national company");
        companyItm.put("bs","Multi company");
        company.add(companyItm);
        jsonObject.put("company",company);
        jsonObject.put("phone", "1-111-1234-1234");
        jsonObject.put("website", "mywebsite.com");


        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jsonObject.toJSONString()).

                when().
                post("users").
                then().
                statusCode(201);

    }
    @Test
    public void test4_putRequest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";


        JSONObject jsonObject = new JSONObject();
        // for address
        jsonObject.put("id", 2);
        jsonObject.put("name", "Habib");
        jsonObject.put("username", "habibgggmridha");
        jsonObject.put("email", "xyz@co.com");

        JSONArray array=new JSONArray();
        JSONObject item=new JSONObject();
        item.put("street", "ABCSD Street");
        item.put("suite", "Apt. 10C");
        item.put("city","NYC");
        item.put("zipcode", "10000");
        array.add(item);
        jsonObject.put("address",array);

        JSONArray geo=new JSONArray();
        JSONObject geoItm=new JSONObject();
        geoItm.put("lat", "-34.100");
        geoItm.put("lng", "80.1234");
        geo.add(geoItm);
        item.put("geo",geo);
        // for company
        JSONArray company=new JSONArray();
        JSONObject companyItm=new JSONObject();
        companyItm.put("name","AZ com LLC");
        companyItm.put("catchPhrase","Multi national company");
        companyItm.put("bs","Multi company");
        company.add(companyItm);
        jsonObject.put("company",company);
        jsonObject.put("phone", "1-111-1234-1234");
        jsonObject.put("website", "mywebsite.com");

        given().
                header("Content-Type", "application/json").
                param("id", 2).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jsonObject.toJSONString()).
                when().
                put("users").
                then().
                statusCode(201);
    }

    @Test
    public void test5_deleteRequest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        given().
                header("Content-Type", "application/json").
                param("id", 11).
                when().
                delete("users").
                then().
                statusCode(204);

    }

}



