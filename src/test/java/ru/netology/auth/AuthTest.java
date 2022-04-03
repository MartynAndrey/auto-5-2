package ru.netology.auth;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthTest {
    private final String activeStatus = "active";
    private final String blockedStatus = "blocked";
    private RegistrationInfo userInvalidActive;
    private RegistrationInfo userInvalidBlocked;
    private RegistrationInfo userValidActive;
    private RegistrationInfo userValidBlocked;

    @BeforeAll
    public void setUpAll() {
        this.userInvalidActive = DataGenerator.generateUserWithStatus(activeStatus);
        this.userInvalidBlocked = DataGenerator.generateUserWithStatus(blockedStatus);
        this.userValidActive = DataGenerator.generateUserWithStatus(activeStatus);
        this.userValidBlocked = DataGenerator.generateUserWithStatus(blockedStatus);
        RegistrationManager.registrationInfoSetUp(this.userValidActive);
        RegistrationManager.registrationInfoSetUp(this.userValidBlocked);
    }

    @Test
    void shouldLoginInvalidPasswordInvalidBlocked() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue(this.userInvalidBlocked.getLogin());
        form.$("[data-test-id='password'] input").setValue(this.userInvalidBlocked.getPassword());
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginInvalidPasswordInvalidActive() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue(this.userInvalidActive.getLogin());
        form.$("[data-test-id='password'] input").setValue(this.userInvalidActive.getPassword());
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginInvalidPasswordValidBlocked() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue(this.userInvalidBlocked.getLogin());
        form.$("[data-test-id='password'] input").setValue(this.userValidBlocked.getPassword());
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginInvalidPasswordValidActive() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue(this.userInvalidActive.getLogin());
        form.$("[data-test-id='password'] input").setValue(this.userValidActive.getPassword());
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginValidPasswordInvalidStatusBlocked() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue(this.userValidBlocked.getLogin());
        form.$("[data-test-id='password'] input").setValue(this.userInvalidBlocked.getPassword());
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginValidPasswordInvalidStatusActive() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue(this.userValidActive.getLogin());
        form.$("[data-test-id='password'] input").setValue(this.userInvalidActive.getPassword());
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginValidPasswordValidStatusBlocked() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue(this.userValidBlocked.getLogin());
        form.$("[data-test-id='password'] input").setValue(this.userValidBlocked.getPassword());
        form.$("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldLoginValidPasswordValidStatusActive() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='login'] input").setValue(this.userValidActive.getLogin());
        form.$("[data-test-id='password'] input").setValue(this.userValidActive.getPassword());
        form.$("[data-test-id='action-login']").click();
        $("h2.heading").shouldHave(Condition.text("Личный кабинет"));
    }
}
