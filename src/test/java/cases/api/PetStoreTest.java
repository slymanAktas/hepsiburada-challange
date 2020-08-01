package cases.api;

import cases.BaseServiceTest;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static config.ConfigKeyword.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetStoreTest extends BaseServiceTest {

    @BeforeClass
    public static void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void shouldCreateUser() {
        ValidatableResponse response = petStoreService.createUser(
                ID,
                USERNAME,
                FIRSTNNAME,
                LASTNAME,
                EMAIL,
                PASSWORD,
                PHONE,
                USER_STATUS
        );
        assertThat("Failed to create user...", response.extract().path("code"), is(equalTo(200)));

    }

    @Test
    public void shouldListUser() {
        ValidatableResponse response = petStoreService.getUser(USERNAME);
        assertThat("Failed to list user...", response.extract().path("firstName"), is(equalTo(FIRSTNNAME)));

    }

    @Test
    public void shouldUpdateUser() {
        ValidatableResponse response = petStoreService.updateUser(
                ID,
                UPDATED_USERNAME,
                USERNAME,
                FIRSTNNAME,
                LASTNAME,
                EMAIL,
                PASSWORD,
                PHONE,
                USER_STATUS
        );
        assertThat("Failed to update user...", response.extract().path("code"), is(equalTo(200)));
    }

    @Test
    public void shouldDeleteUser() {
        ValidatableResponse response = petStoreService.deleteUser(UPDATED_USERNAME);
        assertThat("Failed to list user...", response.extract().path("message"), is(equalTo(FIRSTNNAME)));

    }
}
