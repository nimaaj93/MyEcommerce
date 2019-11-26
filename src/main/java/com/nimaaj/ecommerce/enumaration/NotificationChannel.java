package com.nimaaj.ecommerce.enumaration;

import com.nimaaj.ecommerce.domain.User;

import java.util.function.Function;

public enum NotificationChannel {
    SMS(User::getMobileNumber),
    EMAIL(User::getEmail),
    PUSH(User::getMobileNumber),
    ;

    private final Function<User, String> function;

    NotificationChannel(Function<User, String> function) {
        this.function = function;
    }

    public Function<User, String> getFunction() {
        return function;
    }
}