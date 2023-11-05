package API;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Crocodiles {
    public static int crocodileId;

    Faker fake = new Faker();
    String userName = fake.name().username();
    String firstName = fake.name().firstName();
    String lastName = fake.name().lastName();
    String email = fake.internet().emailAddress();
    String password = fake.funnyName().name();

    @BeforeTest
    void setup() {
        RestAssured.baseURI = "https://test-api.k6.io/";
    }

    @Test
    public void getPublicCrocodiles() {
        given().header("Content-Type", "application/json")
                .when().get("/public/crocodiles/")
                .then().log().all().statusCode(200);
    }

    @Test
    public void getPublicCrocodileById() {
        Response response = given().header("Content-Type", "application/json")
                .when().get("/public/crocodiles/1/");
        response.then().log().all().assertThat().body("id", Matchers.isA(Integer.class))
                .body("name", Matchers.isA(String.class))
                .body("sex", Matchers.isA(String.class))
                .body("date_of_birth", Matchers.isA(String.class))
                .body("age", Matchers.isA(Integer.class))
                .statusCode(200);
    }

    @Test
    public void createNewUser() {
        given().log().all().header("Content-Type", "application/json").body(
                        "{\n" +
                                "     \"username\": \"" + userName + "\",\n" +
                                "    \"first_name\": \"" + firstName + "\",\n" +
                                "    \"last_name\": \"" + lastName + "\",\n" +
                                "    \"email\": \"" + email + "\",\n" +
                                "    \"password\": \"" + password + "\"\n" +
                                "}")
                .when().post("/user/register/")
                .then().log().all().assertThat().body("username", Matchers.isA(String.class))
                .body("first_name", Matchers.isA(String.class))
                .body("last_name", Matchers.isA(String.class))
                .body("email", Matchers.isA(String.class))
                .statusCode(201);
    }

    @Test
    public void cookieLogin() {
        given().header("Content-Type", "application/json").body("{\n" +
                        "    \"username\": \"Majcche\",\n" +
                        "    \"password\": \"Majcche\"\n" +
                        "}")
                .when().post("/auth/cookie/login/")
                .then().log().all().assertThat().body("id", Matchers.isA(Integer.class))
                .body("username", Matchers.isA(String.class))
                .body("first_name", Matchers.isA(String.class))
                .body("last_name", Matchers.isA(String.class))
                .body("email", Matchers.isA(String.class))
                .body("date_joined", Matchers.isA(String.class))
                .statusCode(200);
    }

    @Test
    public void cookieLogout() {
        given().header("Content-Type", "application/json")
                .when().post("/auth/cookie/logout/")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void basicLogin() {
        given().header("Content-Type", "application/json").body("{\n" +
                        "    \"username\": \"Majcche\",\n" +
                        "    \"password\": \"Majcche\"\n" +
                        "}")
                .when().post("/auth/basic/login/")
                .then().log().all().assertThat().body("id", Matchers.isA(Integer.class))
                .body("username", Matchers.isA(String.class))
                .body("first_name", Matchers.isA(String.class))
                .body("last_name", Matchers.isA(String.class))
                .body("email", Matchers.isA(String.class))
                .body("date_joined", Matchers.isA(String.class))
                .statusCode(200);
    }

    public String getToken() {
        Response response = given().header("Content-Type", "application/json").body("{\n" +
                        "    \"username\": \"Majcche\",\n" +
                        "    \"password\": \"Majcche\"\n" +
                        "}")
                .when().post("/auth/token/login/")
                .then().extract().response();
        return response.path("access").toString();

    }

    @Test
    public void listAllMyCrocodiles() {
        given().header("Content-Type", "application/json")
                .auth().oauth2(getToken())
                .when().get("/my/crocodiles/")
                .then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void createNewCrocodile() {
        String response = given().log().all().header("Content-Type", "application/json").body("{\n" +
                        "    \"name\": \"Novi Krokodil\",\n" +
                        "    \"sex\": \"M\",\n" +
                        "    \"date_of_birth\": \"2015-07-06\"\n" +
                        "}")
                .auth().oauth2(getToken())
                .when().post("/my/crocodiles/")
                .then().log().all().assertThat().body("name", isA(String.class))
                .body("sex", isA(String.class))
                .body("date_of_birth", isA(String.class))
                .extract().response().asString();

        JsonPath form = new JsonPath(response);
        crocodileId = form.getInt("id");
        System.out.println(crocodileId);

    }

    @Test
    public void getSingleCrocodile() {
        createNewCrocodile();
        given().header("Content-Type", "application/json")
                .auth().oauth2(getToken())
                .when().get("/my/crocodiles/" + crocodileId + "/")
                .then().log().all().assertThat().body("id", isA(Integer.class))
                .body("name", isA(String.class))
                .body("sex", isA(String.class))
                .body("date_of_birth", isA(String.class))
                .body("age", Matchers.isA(Integer.class))
                .statusCode(200);
    }

    @Test
    public void updateCrocodileById() {
        createNewCrocodile();
        given().log().all().header("Content-Type", "application/json").body("{\n" +
                        "    \"name\": \"Updated Novi Krokodil\",\n" +
                        "    \"sex\": \"M\",\n" +
                        "    \"date_of_birth\": \"2023-05-05\"\n" +
                        "}")
                .auth().oauth2(getToken())
                .when().put("/my/crocodiles/" + crocodileId + "/")
                .then().log().all().assertThat().body("id", Matchers.isA(Integer.class))
                .body("name", Matchers.isA(String.class))
                .body("sex", Matchers.isA(String.class))
                .body("date_of_birth", Matchers.isA(String.class))
                .body("age", Matchers.isA(Integer.class))
                .statusCode(200);
    }

    @Test
    public void updateSingleField() {
        createNewCrocodile();
        given().log().all().header("Content-Type", "application/json").body("{\n" +
                        " \"date_of_birth\": \"2019-11-05\"\n" +
                        "}\n")
                .auth().oauth2(getToken())
                .when().patch("/my/crocodiles/" + crocodileId + "/")
                .then().log().all().assertThat().body("id", Matchers.isA(Integer.class))
                .body("name", Matchers.isA(String.class))
                .body("sex", Matchers.isA(String.class))
                .body("date_of_birth", Matchers.isA(String.class))
                .body("age", Matchers.isA(Integer.class))
                .statusCode(200);
    }

    @Test
    public void deleteCrocodile() {
        createNewCrocodile();
        given().header("Content-Type", "application/json")
                .auth().oauth2(getToken())
                .when().delete("/my/crocodiles/" + crocodileId + "/")
                .then().assertThat().statusCode(204);
    }
}
