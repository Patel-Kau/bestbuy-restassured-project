package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.util.Objects;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Created by Kaushik Patel
 */
public class ProductsExtractionTest {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }


    // 01. Extract the limit
    @Test
    public void test01() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    // 02. Extract the total
    @Test
    public void test02() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // 03. Extract the name of 5th product
    @Test
    public void test03() {
        String nameofproduct = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + nameofproduct);
        System.out.println("------------------End of Test---------------------------");
    }

    // 04. Extract the names of all the products
    @Test
    public void test04() {
        List<String> nameofAllProduct = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all the products is : " + nameofAllProduct);
        System.out.println("------------------End of Test---------------------------");
    }


    // 05. Extract the productId of all the products
    @Test
    public void test05() {
        List<Objects> idOfAllProduct = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The productId of all the products is : " + idOfAllProduct);
        System.out.println("------------------End of Test---------------------------");
    }

    // 06. Print the size of the data list
    @Test
    public void test06() {
        List<HashMap<String, ?>> sizeOfAllProduct = response.extract().path("data.");
        int listsize = sizeOfAllProduct.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The productId of all the products is : " + listsize);
        System.out.println("------------------End of Test---------------------------");
    }


    // 07. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test07() {
        List<HashMap<String, ?>> productvalue = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product where product name = Energizer - MAX Batteries AA (4-Pack) is : " + productvalue);
        System.out.println("------------------End of Test---------------------------");
    }


    // 08. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2- Pack)
    @Test
    public void test08() {
        List<String> productname = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product where product name = Energizer - N Cell E90 Batteries (2- Pack) is : " + productname);
        System.out.println("------------------End of Test---------------------------");
    }

    // 09. Get all the categories of 8th products
    @Test
    public void test09() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of 8th products) is : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10. Get categories of the store where product id = 150115
    @Test
    public void test10() {
        List<String> categories = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store where product id = 15011) is : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11. Get all the descriptions of all the products
    @Test
    public void test11() {
        List<String> allDescription = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products is : " + allDescription);
        System.out.println("------------------End of Test---------------------------");
    }
    // 12. Get id of all the all categories of all the products
    @Test
    public void test12() {
        List<String> allCatagories = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products is : " + allCatagories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 13. Find the product names Where type = HardGood
    @Test
    public void test13() {
        List<String> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }



    // 14. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test14() {
        List<Objects> numberOfCatategories = response.extract().path("data.findAll{it.name =='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        int totalCategories = numberOfCatategories.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) is : " + totalCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 15. Find the createdAt for all products whose price < 5.49
    @Test
    public void test15() {
        List<String> productName = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 16. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)”
    @Test
    public void test16() {
        List<String> productName = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)” is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 17. Find the manufacturer of all the products
    @Test
    public void test17() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of all the products is : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }
    // 18. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test18() {
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The image of products whose manufacturer is = Energizer is : " + image);
        System.out.println("------------------End of Test---------------------------");
    }

    // 19. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test19() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 is : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // 20. Find the uri of all the product
    @Test
    public void test20() {
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The uri of all the product is : " + url);
        System.out.println("------------------End of Test---------------------------");
    }

}
