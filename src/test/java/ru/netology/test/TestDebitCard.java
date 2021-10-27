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

public class TestDebitCard {

    DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        dashboardPage = open("http://localhost:8080", DashboardPage.class);
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
    void shouldPayByApprovedDebitCard() {
        val paymentPage = dashboardPage.payByDebitCard();
        val approvedCardInformation = DataHelper.getApprovedCardInformation();
        paymentPage.enterCardInfo(approvedCardInformation);
        paymentPage.notificationOkIsVisible();
        val paymentStatus = DbHelper.getPaymentEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPayByDeclinedDebitCard() {
        val paymentPage = dashboardPage.payByDebitCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInformation();
        paymentPage.enterCardInfo(declinedCardInformation);
        paymentPage.notificationErrorIsVisible();
        val paymentStatus = DbHelper.getPaymentEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    void shouldPayByDebitCardWithInvalidNumber() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardInfo(invalidCardInformation);
        paymentPage.checkInvalidCardNumber();
    }

    @Test
    void shouldPayByDebitCardWithExpiredYear() {
        val paymentPage = dashboardPage.payByDebitCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInformation();
        paymentPage.enterCardInfo(expiredYearCardInformation);
        paymentPage.checkExpiredYearMessage();
    }

    @Test
    void shouldPayByDebitCardWithInvalidExpirationDate() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidExpirationDate = DataHelper.getInvalidExpirationDateCardInformation();
        paymentPage.enterCardInfo(invalidExpirationDate);
        paymentPage.checkInvalidExpirationDate();
    }

    @Test
    void shouldPayByDebitCardWithExpiredMonth() {
        val paymentPage = dashboardPage.payByDebitCard();
        val expiredMonth = DataHelper.getExpiredMonthCardInformation();
        paymentPage.enterCardInfo(expiredMonth);
        paymentPage.checkExpiredMonthMessage();
    }

    @Test
    void shouldPayByDebitCardWithEmptyCardInformation() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardInfo(emptyCardInformation);
        paymentPage.checkEmptyCardNumberFieldMessage();
        paymentPage.checkEmptyMonthFieldMessage();
        paymentPage.checkEmptyYearFieldMessage();
        paymentPage.checkEmptyOwnerFieldMessage();
        paymentPage.checkEmptyCvcFieldMessage();
    }

    @Test
    void shouldPayByDebitCarEmptyNumberCardInformation() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyNumberCardInformation = DataHelper.getEmptyNumberCardInformation();
        paymentPage.enterCardInfo(emptyNumberCardInformation);
        paymentPage.checkEmptyYearFieldMessage();
    }

    @Test
    void shouldPayByDebitCarEmptyMonthCardInformation() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyMonthCardInformation = DataHelper.getEmptyMonthCardInformation();
        paymentPage.enterCardInfo(emptyMonthCardInformation);
        paymentPage.checkEmptyMonthFieldMessage();
    }

    @Test
    void shouldPayByDebitCarEmptyYearCardInformation() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyYearCardInformation = DataHelper.getEmptyYearCardInformation();
        paymentPage.enterCardInfo(emptyYearCardInformation);
        paymentPage.checkEmptyYearFieldMessage();
    }

    @Test
    void shouldPayByDebitCarEmptyOwnerCardInformation() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyOwnerCardInformation = DataHelper.getEmptyOwnerCardInformation();
        paymentPage.enterCardInfo(emptyOwnerCardInformation);
        paymentPage.checkEmptyOwnerFieldMessage();
    }

    @Test
    void shouldPayByDebitCarEmptyCvcCardInformation() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyCvcCardInformation = DataHelper.getEmptyCvcCardInformation();
        paymentPage.enterCardInfo(emptyCvcCardInformation);
        paymentPage.checkEmptyCvcFieldMessage();
    }

    @Test
    void shouldPayByDebitCardWithInvalidOwner() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        paymentPage.enterCardInfo(invalidOwner);
        paymentPage.checkInvalidOwner();
    }

    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidYear0() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidYear0 = DataHelper.getValidCardNumberWithInvalidYear0();
        paymentPage .enterCardInfo(validCardNumberWithInvalidYear0);
        paymentPage .checkInvalidYear();
    }
    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidMonth13() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberInvalidMonth13 = DataHelper.getValidCardNumberInvalidMonth13();
        paymentPage .enterCardInfo(validCardNumberInvalidMonth13);
        paymentPage .checkInvalidMonth();
    }
    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidMonth0() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberInvalidMonth0 = DataHelper.getValidCardNumberInvalidMonth0();
        paymentPage .enterCardInfo(validCardNumberInvalidMonth0);
        paymentPage .checkInvalidMonth();
    }

    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidMonth00() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberInvalidMonth00 = DataHelper.getValidCardNumberInvalidMonth00();
        paymentPage .enterCardInfo(validCardNumberInvalidMonth00);
        paymentPage .checkInvalidMonth();
    }
    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidOtherOwner() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidOwner = DataHelper.getValidCardNumberWithInvalidOwner();
        paymentPage .enterCardInfo(validCardNumberWithInvalidOwner);
        paymentPage .checkInvalidOwner();
    }

    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidOtherOwner123() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidOwner123 = DataHelper.getValidCardNumberWithInvalidOwner123();
        paymentPage .enterCardInfo(validCardNumberWithInvalidOwner123);
        paymentPage .checkInvalidOwner();
    }

    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidOtherOwnerSpecialCharacters() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidOwnerSpecialCharacters = DataHelper.getValidCardNumberWithInvalidOwnerSpecialCharacters();
        paymentPage .enterCardInfo(validCardNumberWithInvalidOwnerSpecialCharacters);
        paymentPage .checkInvalidOwner();
    }

    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidCvc000() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidCvc000 = DataHelper.getValidCardNumberWithInvalidCvc000();
        paymentPage .enterCardInfo(validCardNumberWithInvalidCvc000);
        paymentPage .checkInvalidCvc();
    }

    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidCvc1() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidCvc1 = DataHelper.getValidCardNumberWithInvalidCvc1();
        paymentPage .enterCardInfo(validCardNumberWithInvalidCvc1);
        paymentPage .checkInvalidCvc();
    }

    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidCvc11() {
        val paymentPage  = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidCvc11 = DataHelper.getValidCardNumberWithInvalidCvc11();
        paymentPage .enterCardInfo(validCardNumberWithInvalidCvc11);
        paymentPage .checkInvalidCvc();
    }

}
