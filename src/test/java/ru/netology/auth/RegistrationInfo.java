package ru.netology.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationInfo {
    private String login;
    private String password;
    private String status;
}