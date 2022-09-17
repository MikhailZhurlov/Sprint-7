package ru.qa_scooter.praktikum_services;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class ListOrdersTest {
    CourierClient courierClient;
    private ListOrders listOrders;

    @Before
    public void Setup(){
        courierClient = new CourierClient();
    }
    
    @Test
    @DisplayName("Getting a list of orders")
    @Description("Service return 200 and orders not empty")
    public void listOrderTest(){
        ValidatableResponse response = CourierClient.listOrder();
        int statusCode = response.extract().statusCode();
        assertEquals(SC_OK, statusCode);
        ArrayList<String> orderBody = response.extract().path("orders");
        boolean isNotEmpty = orderBody!=null && !orderBody.isEmpty();
        assertTrue(isNotEmpty);

    }
}