package com.nimaaj.ecommerce.util;

public class MaskUtil {

    private MaskUtil() {
    }

    public static String maskMobile(String input) {
        return input.replaceAll("\\d(?=\\d{4})", "*");
    }

    public static String maskEmail(String input) {
        return input.replaceAll("(?<=.{3}).(?=.*@)", "*");
    }
}