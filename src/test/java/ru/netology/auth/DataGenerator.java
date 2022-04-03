package ru.netology.auth;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private static String locale = "ru";

    public static RegistrationInfo generateUserWithStatus (String status) {
        Faker faker = new Faker(new Locale(locale));
        return new RegistrationInfo(faker.name().username(), faker.internet().password(), status);
    }
}
