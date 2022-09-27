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

    public static Courier getRandomCourierWithoutLogin(){
        return new Courier(
                RandomStringUtils.randomAlphanumeric(0),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10)
        );
    }

    public static Courier getRandomCourierWithoutPass(){
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(0),
                RandomStringUtils.randomAlphanumeric(10)
        );
    }

    public static Courier getNonExistentLogin(){
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10)
        );
    }

    public static Courier getDefault(){
        return new Courier("z7FhyHVRckjs", "6p7qKKjmLdk", "petushichk");
    }
}
