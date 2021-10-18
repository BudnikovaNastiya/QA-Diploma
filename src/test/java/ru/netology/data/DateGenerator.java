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

    protected static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    protected static String getInvalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    protected static String getValidCvc() {
        return "111";
    }

    protected static String getInvalidCvc() {
        return "11";
    }

    @Value
    protected static class Year {
        String year;
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
        LocalDate newYear = today.plusYears(10);
        return new Year(yearFormatter.format(newYear));
    }

    protected Year getInvalidYear() {
        return new Year("00");
    }

    @Value
    protected static class Month {
        String month;
    }

    protected Month getValidMonth() {
        LocalDate newMonth = today.plusMonths(1);
        return new Month(monthFormatter.format(newMonth));
    }

    protected Month getExpiredMonth() {
        LocalDate newMonth = today.minusMonths(1);
        return new Month(monthFormatter.format(newMonth));
    }

    protected Month getInvalidMonth() {
        return new Month("13");
    }

}
