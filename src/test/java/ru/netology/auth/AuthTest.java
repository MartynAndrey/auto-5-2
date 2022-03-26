package ru.netology.auth;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    @BeforeAll
    static void setUpAll() {
        RegistrationManager.RegistrationInfoSetUp("tim1", "pass0", "active");
        RegistrationManager.RegistrationInfoSetUp("tim1", "pass1", "active");
        RegistrationManager.RegistrationInfoSetUp("tim2", "pass2", "blocked");
    }

    @Test
    void shouldLoginInvalid() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue("tim0");
        form.$("[data-test-id='password'] input").setValue("pass0");
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginValidPasswordInvalidStatusBlocked() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue("tim2");
        form.$("[data-test-id='password'] input").setValue("pass0");
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginValidPasswordInvalidStatusActive() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue("tim1");
        form.$("[data-test-id='password'] input").setValue("pass0");
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginValidPasswordValidStatusBlocked() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue("tim2");
        form.$("[data-test-id='password'] input").setValue("pass2");
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldLoginValidPasswordValidStatusActive() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue("tim1");
        form.$("[data-test-id='password'] input").setValue("pass1");
        form.$("[data-test-id='action-login']").click();
        $("h2.heading").shouldHave(Condition.text("Личный кабинет"));
    }
}
