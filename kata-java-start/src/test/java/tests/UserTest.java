package tests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void checkEmail() throws UnirestException {
        HttpResponse<String> jsonResponse
                = Unirest.get("https://reqres.in/api/users/5")
                .queryString("email", "charles.morris@reqres.in").asString();

        assertNotNull(jsonResponse.getBody());
        assertEquals(200, jsonResponse.getStatus());
        assertTrue(jsonResponse.getBody().contains("\"email\":\"charles.morris@reqres.in\""));
    }

    @Test
    public void patchUser() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse
                = Unirest.patch("https://reqres.in/api/users/3").header("Content-Type", "application/json")
                .body("{\"name\":\"Jesse\", \"job\":\"student\"}").asJson();

        String responseBody = jsonResponse.getBody().toString();

        assertEquals(200, jsonResponse.getStatus());
        assertTrue(responseBody.contains("\"name\":\"Jesse\""));
        assertTrue(responseBody.contains("\"job\":\"student\""));
    }

}
