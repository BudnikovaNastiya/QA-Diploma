package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateGenerator {

    LocalDate today = LocalDate.now();
    DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
    DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");

    protected static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    protected static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    protected static String getInvalidCardNumber() {
        return "4444 4444 4444 444";
    }

    protected static String getInvalidCardNumber43() {
        return "4444 4444 4444 4443";
    }

    protected static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    protected static String getInvalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    protected static String getInvalidOwner123() {
        return "123";
    }

    protected static String getInvalidOwnerLink() {
        return "https://github.com/";
    }


    protected static String getInvalidOwnerSpecialCharacters() {
        return "!@#";
    }

    protected static String getValidCvc() {
        return "111";
    }

    protected static String getInvalidCvc000() {
        return "000";
    }

    protected static String getInvalidCvc11() {
        return "11";
    }

    protected static String getInvalidCvc1() {
        return "1";
    }

    protected Year getCurrentYear() {
        LocalDate currentYear = LocalDate.now();
        return new Year(yearFormatter.format(currentYear));
    }

    protected Year getValidExpirationDate() {
        LocalDate newYear = today.plusYears(1);
        return new Year(yearFormatter.format(newYear));
    }

    protected Year getExpiredYear() {
        LocalDate newYear = today.minusYears(1);
        return new Year(yearFormatter.format(newYear));
    }

    protected Year getInvalidExpirationDate() {
        LocalDate newYear = today.plusYears(6);
        return new Year(yearFormatter.format(newYear));
    }

    protected Year getInvalidYear0() {
        return new Year("0");
    }

    protected Year getInvalidYear00() {
        return new Year("00");
    }

    protected Month getValidMonth() {
        LocalDate newMonth = today.plusMonths(1);
        return new Month(monthFormatter.format(newMonth));
    }

    protected Month getExpiredMonth() {
        LocalDate newMonth = today.minusMonths(1);
        return new Month(monthFormatter.format(newMonth));
    }

    protected Month getInvalidMonth13() {
        return new Month("13");
    }

    protected Month getInvalidMonth0() {
        return new Month("0");
    }

    protected Month getInvalidMonth00() {
        return new Month("00");
    }

    @Value
    protected static class Year {
        String year;
    }

    @Value
    protected static class Month {
        String month;
    }
}
