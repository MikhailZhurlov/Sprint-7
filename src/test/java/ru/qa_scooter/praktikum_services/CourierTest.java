package ru.qa_scooter.praktikum_services;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;
import static ru.qa_scooter.praktikum_services.ErrorMessage.BAD_MESSAGE;
import static ru.qa_scooter.praktikum_services.ErrorMessage.CONFLICT_MESSAGE;

public class CourierTest {
  Courier courier;
  CourierClient courierClient;
  private int courierId;

  @Before
  public void Setup(){
    courierClient = new CourierClient();
  }

  @After
  public void tearDown(){
    CourierClient.deleteId(courierId);
  }

  @Test
  @DisplayName("Creating a courier")
  @Description("Service return 201 Created when new courier created")
    public void createCourierTest(){
      courier = GenerationCourier.getRandomCourier();
      ValidatableResponse response = CourierClient.create(courier);
      int statusCode = response.extract().statusCode();
      assertEquals(SC_CREATED, statusCode);
      boolean isOk = response.extract().path("ok");
      assertTrue(isOk);
  }

  @Test
  @DisplayName("The courier cannot be registered")
  @Description("Service return 400 bad request,when client create courier with double login")
    public void createDoubleCourierTest(){
      courier = GenerationCourier.getDefault();
      ValidatableResponse response = CourierClient.create(courier);
      int statusCode = response.extract().statusCode();
      assertEquals(SC_CREATED, statusCode);
      boolean isOk = response.extract().path("ok");
      assertTrue(isOk);

       ValidatableResponse loginResponse = CourierClient.loginCourier(LoginCourier.from(courier));
       courierId = loginResponse.extract().path("id");
       assertNotNull(courierId);

      ValidatableResponse doubleResponse = CourierClient.create(courier);
      int DoubleStatusCode = doubleResponse.extract().statusCode();
      assertEquals(SC_CONFLICT, DoubleStatusCode);
      String isMessage = doubleResponse.extract().path("message");
      assertEquals(CONFLICT_MESSAGE, isMessage);
  }

  @Test
  @DisplayName("Create Courier without login")
  @Description("Service return 400 bad request,when Courier create without field login")
  public void createWithoutLogin(){
    courier = GenerationCourier.getWithoutLogin();
    ValidatableResponse response = CourierClient.create(courier);
    int statusCode = response.extract().statusCode();
    assertEquals(SC_BAD_REQUEST, statusCode);
    String isMessage = response.extract().path("message");
    assertEquals(BAD_MESSAGE, isMessage);
  }

  @Test
  @DisplayName("Create Courier without password")
  @Description("Service return 400 bad request,when Courier create without field password")
  public void createWithoutPassword(){
    courier = GenerationCourier.getWithoutPassword();
    ValidatableResponse response = CourierClient.create(courier);
    int statusCode = response.extract().statusCode();
    assertEquals(SC_BAD_REQUEST, statusCode);
    String isMessage = response.extract().path("message");
    assertEquals(BAD_MESSAGE, isMessage);
  }
}