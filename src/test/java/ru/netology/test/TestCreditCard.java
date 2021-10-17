package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.sql.DbHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreditCard {

    DashboardPage dashboardPage = new DashboardPage();

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        DbHelper.cleanDb();
    }

    @Test
    void shouldPayByApprovedCreditCard() {
        val paymentPage = dashboardPage.payByCreditCard();
        val approvedCardInformation = DataHelper.getApprovedCardInformation();
        paymentPage.enterCardInfo(approvedCardInformation);
        paymentPage.notificationOkIsVisible();
        val creditStatus = DbHelper.getCreditEntity();
        assertEquals("APPROVED", creditStatus);
    }

    @Test
    void shouldPayByDeclinedCreditCard() {
        val paymentPage = dashboardPage.payByCreditCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInformation();
        paymentPage.enterCardInfo(declinedCardInformation);
        paymentPage.notificationErrorIsVisible();
        val creditStatus = DbHelper.getCreditEntity();
        assertEquals("DECLINED", creditStatus);
    }

    @Test
    void shouldPayByCreditCardWithInvalidNumber() {
        val paymentPage = dashboardPage.payByCreditCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardInfo(invalidCardInformation);
        paymentPage.checkInvalidCardNumber();
    }

    @Test
    void shouldPayByCreditCardWithExpiredYear() {
        val paymentPage = dashboardPage.payByCreditCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInformation();
        paymentPage.enterCardInfo(expiredYearCardInformation);
        paymentPage.checkExpiredYearMessage();
    }

    @Test
    void shouldPayByCreditCardWithInvalidExpirationDate() {
        val paymentPage = dashboardPage.payByCreditCard();
        val invalidExpirationDate = DataHelper.getInvalidExpirationDateCardInformation();
        paymentPage.enterCardInfo(invalidExpirationDate);
        paymentPage.checkInvalidExpirationDate();
    }

    @Test
    void shouldPayByCreditCardWithExpiredMonth() {
        val paymentPage = dashboardPage.payByCreditCard();
        val expiredMonth = DataHelper.getExpiredMonthCardInformation();
        paymentPage.enterCardInfo(expiredMonth);
        paymentPage.checkExpiredMonthMessage();
    }

    @Test
    void shouldPayByCreditCardWithEmptyCardInformation() {
        val paymentPage = dashboardPage.payByCreditCard();
        val emptyCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardInfo(emptyCardInformation);
        paymentPage.checkEmptyCardNumberFieldMessage();
        paymentPage.checkEmptyMonthFieldMessage();
        paymentPage.checkEmptyYearFieldMessage();
        paymentPage.checkEmptyOwnerFieldMessage();
        paymentPage.checkEmptyCvcFieldMessage();
    }

    @Test
    void shouldPayByCreditCardWithInvalidOwner() {
        val paymentPage = dashboardPage.payByCreditCard();
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        paymentPage.enterCardInfo(invalidOwner);
        paymentPage.checkInvalidOwner();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidOtherFields() {
        val paymentPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidOtherFields = DataHelper.getValidCardNumberWithInvalidOtherFields();
        paymentPage.enterCardInfo(validCardNumberWithInvalidOtherFields);
        paymentPage.checkInvalidMonth();
        paymentPage.checkInvalidYear();
        paymentPage.checkInvalidOwner();
        paymentPage.checkInvalidCvc();
    }

}
