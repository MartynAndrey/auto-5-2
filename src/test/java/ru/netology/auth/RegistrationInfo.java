package ru.netology.auth;

public class RegistrationInfo {
    private String login;
    private String password;
    private String status;

    public RegistrationInfo(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }
}
