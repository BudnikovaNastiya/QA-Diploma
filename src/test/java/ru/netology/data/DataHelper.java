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

    public static CardInfo getEmptyNumberCardInformation() {
        return new CardInfo(" ",  dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }
    public static CardInfo getEmptyYearCardInformation() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), " ", dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }
    public static CardInfo getEmptyMonthCardInformation() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), " ", DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }
    public static CardInfo getEmptyOwnerCardInformation() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(),dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), " ", DateGenerator.getValidCvc());
    }
    public static CardInfo getEmptyCvcCardInformation() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), " ");
    }

    public static CardInfo getValidCardNumberWithInvalidYear0() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getInvalidYear().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getValidCardNumberInvalidMonth13() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getInvalidMonth13().getMonth(), DateGenerator.getInvalidOwner(), DateGenerator.getValidCvc());
    }
    public static CardInfo getValidCardNumberInvalidMonth0() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getInvalidMonth0().getMonth(), DateGenerator.getInvalidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getValidCardNumberInvalidMonth00 () {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getInvalidMonth00().getMonth(), DateGenerator.getInvalidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getValidCardNumberWithInvalidOwner() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(),dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getInvalidOwner(), DateGenerator.getValidCvc());
    }

    public static CardInfo getValidCardNumberWithInvalidOwnerSpecialCharacters() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(),dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getInvalidOwnerSpecialCharacters(), DateGenerator.getValidCvc());
    }

    public static CardInfo getValidCardNumberWithInvalidOwner123() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(),dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getInvalidOwner123(), DateGenerator.getValidCvc());
    }

    public static CardInfo getValidCardNumberWithInvalidCvc000() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getInvalidCvc000());
    }
    public static CardInfo getValidCardNumberWithInvalidCvc1() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getInvalidCvc1());
    }

    public static CardInfo getValidCardNumberWithInvalidCvc11() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), DateGenerator.getValidOwner(), DateGenerator.getInvalidCvc11());
    }

    public static CardInfo getInvalidOwnerCard() {
        return new CardInfo(DateGenerator.getApprovedCardNumber(),
                dateGenerator.getValidExpirationDate().getYear(),
                dateGenerator.getValidMonth().getMonth(),
                DateGenerator.getInvalidOwner(),
                DateGenerator.getValidCvc());
    }


}

