package ru.netology.auth;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private static String locale = "ru";

    public static RegistrationInfo generateActiveUserInfo () {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();

        return new RegistrationInfo(login, password, "active");
    }

    public static RegistrationInfo generateBlockedUserInfo () {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();

        return new RegistrationInfo(login, password, "blocked");
    }
}
