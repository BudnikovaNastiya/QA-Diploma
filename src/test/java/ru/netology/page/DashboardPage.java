package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement paymentButton = $$("button").find(exactText("Купить"));
    private final SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));
    private final SelenideElement payCard = $$("h3[class]").find(exactText("Оплата по карте"));
    private final SelenideElement payCredit = $$("h3[class]").find(exactText("Кредит по данным карты"));

    public PaymentPage payByDebitCard() {
        paymentButton.click();
        payCard.waitUntil(enabled, 15000);
        return new PaymentPage();
    }

    public CreditPage payByCreditCard() {
        creditButton.click();
        payCredit.waitUntil(enabled, 15000);
        return new CreditPage();
    }

}
