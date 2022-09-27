package ru.qa_scooter.praktikum_services;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreatingOrderTest {
    private Order order;
    private CourierClient courierClient;
    private final List<String> color;

    public CreatingOrderTest(List<String> color) {
        this.color = color;
    }

    @Before
    public void setUp() {
        order = Order.getRandomOrder(color);
        courierClient = new CourierClient();
    }

        @Parameterized.Parameters
    public static Object[][] getColor(){
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of()},
        };
    }

    @Test
    @DisplayName("Create orders")
    @Description("Service return 201 Created when new order created")
    public void createOrderTest() {
        ValidatableResponse response = CourierClient.createOrder(order);
        response.statusCode(SC_CREATED).and().assertThat().body("track", notNullValue());
    }
}