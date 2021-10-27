package ru.netology.page;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement heading = $$("h3").find(text("Кредит по данным карты"));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvcField = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement notificationOK = $(".notification_status_ok");
    private SelenideElement notificationError = $(".notification_status_error");
    private SelenideElement cardNumberFieldWarning = $("fieldset > div:nth-child(1) > span > span > span.input__sub");
    private SelenideElement monthFieldWarning = $("div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement yearFieldWarning = $("div:nth-child(2) > span > span:nth-child(2) > span > span > span.input__sub");
    private SelenideElement ownerFieldWarning = $("div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement cvcFieldWarning = $("div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__sub");

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public void enterCardInfo(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getOwner());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void notificationOkIsVisible() {
        notificationOK.waitUntil(visible, 12000);
    }

    public void notificationErrorIsVisible() {
        notificationError.waitUntil(visible, 12000);
    }

    public void checkInvalidCardNumber() {
        cardNumberFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void checkInvalidMonth() {
        monthFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void checkInvalidYear() {
        yearFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void checkInvalidOwner() {
        ownerFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void checkInvalidCvc() {
        cvcFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void checkEmptyCardNumberFieldMessage() {
        cardNumberFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void checkEmptyMonthFieldMessage() {
        monthFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void checkEmptyYearFieldMessage() {
        yearFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void checkEmptyOwnerFieldMessage() {
        ownerFieldWarning.shouldHave(text("Поле обязательно для заполнения"));
    }

    public void checkEmptyCvcFieldMessage() {
        cvcFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void checkExpiredYearMessage() {
        yearFieldWarning.shouldHave(text("Истёк срок действия карты"));
    }

    public void checkExpiredMonthMessage() {
        monthFieldWarning.shouldHave(text("Неверно указан срок действия карты"));
    }

    public void checkInvalidExpirationDate() {
        yearFieldWarning.shouldHave(text("Неверно указан срок действия карты"));
    }
}
