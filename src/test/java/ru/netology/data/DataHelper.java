package ru.netology.data;

import lombok.Value;

public class DataHelper {
    static DateGenerator dateGenerator = new DateGenerator();

    @Value
    public static class CardInfo {
        String cardNumber;
        String year;
        String month;
        String owner;
        String cvc;
    }

    public static CardInfo getApprovedCardInformation() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getDeclinedCardInformation() {
        return new CardInfo(DateGenerator.getDeclinedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getInvalidCardInformation() {
        return new CardInfo(DateGenerator.getInvalidCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getExpiredMonthCardInformation() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getCurrentYear().getYear(), dateGenerator.getExpiredMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getExpiredYearCardInformation() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getExpiredYear().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getInvalidExpirationDateCardInformation() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getInvalidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getEmptyCardInformation() {
        return new CardInfo(" ", " ", " ", " ", " ");
    }

    public static CardInfo getValidCardNumberWithInvalidOtherFields() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getInvalidYear().getYear(), dateGenerator.getInvalidMonth().getMonth(), DateGenerator.getInvalidOwner(), DateGenerator.getInvalidCvc());
    }

    public static CardInfo getInvalidOwnerCard() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(),
                dateGenerator.getValidExpirationDate().getYear(),
                dateGenerator.getValidMonth().getMonth(),
                DateGenerator.getInvalidOwner(),
                DateGenerator.getValidCvc());
    }

}

