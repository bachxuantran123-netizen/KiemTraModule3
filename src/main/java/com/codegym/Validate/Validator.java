package com.codegym.Validate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class Validator {
    private static final String REGEX_MA_MAT_BANG = "^[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}$";

    public static boolean isValidMaMatBang(String code) {
        return code != null && Pattern.matches(REGEX_MA_MAT_BANG, code);
    }

    public static boolean isAtLeast6Months(String startStr, String endStr) {
        try {
            LocalDate start = LocalDate.parse(startStr);
            LocalDate end = LocalDate.parse(endStr);
            long monthsBetween = ChronoUnit.MONTHS.between(start, end);
            return monthsBetween >= 6;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEmpty(String str) { return str == null || str.trim().isEmpty(); }
    public static boolean isPositive(String str) {
        try { return Double.parseDouble(str) > 0; } catch (Exception e) { return false; }
    }
}