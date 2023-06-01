package com.bestbuy.crudtestpackages;

import com.bestbuy.utils.TestUtils;
import com.bestbuy.model.ProjectPojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created By Kaushik patel
 */
public class ProductsCRUDTest {
static ValidatableResponse response;
    static String name = "New Product" + TestUtils.getRandomValue();
    static String updateName = "New Product" + TestUtils.getRandomValue();
    static String type = "Hard Good" + TestUtils.getRandomValue();
    static String upc = "12" + TestUtils.getRandomValue();
    static double price = 99.99;
    static String description = "This is a placeholder request for creating a new product.";
    static String model = "NP" + TestUtils.getRandomValue();
    static int productId;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    @Test
    public void test001() {

        ProjectPojo projectPojo = new ProjectPojo();
        projectPojo.setName(name);
        projectPojo.setType(type);
        projectPojo.setUpc(upc);
        projectPojo.setPrice(price);
        projectPojo.setDescription(description);
        projectPojo.setModel(model);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(projectPojo)
                .post();
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        productId = jsonPath.getInt("id");
    }

    @Test
    public void test002() {
        System.out.println("================" + productId);
         response = given()
                .when()
                .get("/" + productId)
        .then().log().all().statusCode(200);
    }

    @Test
    public void test003() {
        System.out.println("================" + productId);
        ProjectPojo projectPojo = new ProjectPojo();
        projectPojo.setName(updateName);
        projectPojo.setType(type);
        projectPojo.setUpc(upc);
        projectPojo.setPrice(price);
        projectPojo.setDescription(description);
        projectPojo.setModel(model);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(projectPojo)
                .patch("/" + productId);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void test004() {
        System.out.println("================" + productId);
        given()
                .pathParam("id", productId)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(200);
    }
}
