package ru.qa_scooter.praktikum_services;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerationCourier {
    public static Courier getRandomCourier(){
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10)
        );
    }

    public static Courier getDefault(){
        return new Courier("mishka5455405", "5455405", "petushich");
    }

    public static Courier getWithoutLogin() {
        return new Courier("", "555405", "petka");
    }

    public static Courier getWithoutPassword() {
        return new Courier("mishka55405", "", "petka");
    }

    public static Courier getRight() {
        return new Courier ("lolik", "1234", "saskes");
    }

    public static Courier getNonExistentLogin(){
        return new Courier("lalok04", "cdsf","dsdf");
    }
}
