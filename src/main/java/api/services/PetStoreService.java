package api.services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PetStoreService {

    public ValidatableResponse createUser(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        ValidatableResponse response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(getRequestBody(id, username, firstName, lastName, email, password, phone, userStatus))
                .when()
                .post("/")
                .then();
        response.extract().response().prettyPrint();
        return response;
    }

    public ValidatableResponse getUser(String username) {
        ValidatableResponse response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("/" + username + "")
                .then();
        response.extract().response().prettyPrint();
        return response;
    }

    public ValidatableResponse updateUser(int id, String updatedUserNAme, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        ValidatableResponse response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(getRequestBody(id, updatedUserNAme, firstName, lastName, email, password, phone, userStatus))
                .when()
                .put("/" + username + "")
                .then();
        response.extract().response().prettyPrint();
        return response;
    }

    public ValidatableResponse deleteUser(String username) {
        ValidatableResponse response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete("/" + username + "")
                .then();
        response.extract().response().prettyPrint();
        return response;
    }

    private static HashMap<String, Object> getRequestBody(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("username", username);
        requestParams.put("firstName", firstName);
        requestParams.put("lastName", lastName);
        requestParams.put("email", email);
        requestParams.put("password", password);
        requestParams.put("phone", phone);
        requestParams.put("userStatus", userStatus);
        return requestParams;
    }
}
