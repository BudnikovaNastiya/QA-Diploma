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

    DashboardPage dashboardPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        DbHelper.cleanDb();
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        dashboardPage = open("http://localhost:8080", DashboardPage.class);
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    void shouldPayByApprovedCreditCard() {
        val creditPage = dashboardPage.payByCreditCard();
        val approvedCardInformation = DataHelper.getApprovedCardInformation();
        creditPage.enterCardInfo(approvedCardInformation);
        creditPage.notificationOkIsVisible();
        val creditStatus = DbHelper.getCreditEntity();
        assertEquals("APPROVED", creditStatus);
    }

    @Test
    void shouldPayByDeclinedCreditCard() {
        val creditPage = dashboardPage.payByCreditCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInformation();
        creditPage.enterCardInfo(declinedCardInformation);
        creditPage.notificationErrorIsVisible();
        val creditStatus = DbHelper.getCreditEntity();
        assertEquals("DECLINED", creditStatus);
    }

    @Test
    void shouldPayByCreditCardWithInvalidNumber() {
        val creditPage = dashboardPage.payByCreditCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        creditPage.enterCardInfo(invalidCardInformation);
        creditPage.checkInvalidCardNumber();
    }

    @Test
    void shouldPayByCreditCardWithInvalidNumber43() {
        val creditPage = dashboardPage.payByCreditCard();
        val invalidCardInformation43 = DataHelper.getInvalidCardInformation43();
        creditPage.enterCardInfo(invalidCardInformation43);
        creditPage.checkInvalidCardNumber();
    }


    @Test
    void shouldPayByCreditCardWithExpiredYear() {
        val creditPage = dashboardPage.payByCreditCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInformation();
        creditPage.enterCardInfo(expiredYearCardInformation);
        creditPage.checkExpiredYearMessage();
    }

    @Test
    void shouldPayByCreditCardWithInvalidExpirationDate() {
        val creditPage = dashboardPage.payByCreditCard();
        val invalidExpirationDate = DataHelper.getInvalidExpirationDateCardInformation();
        creditPage.enterCardInfo(invalidExpirationDate);
        creditPage.checkInvalidExpirationDate();
    }

    @Test
    void shouldPayByCreditCardWithExpiredMonth() {
        val creditPage = dashboardPage.payByCreditCard();
        val expiredMonth = DataHelper.getExpiredMonthCardInformation();
        creditPage.enterCardInfo(expiredMonth);
        creditPage.checkExpiredMonthMessage();
    }

    @Test
    void shouldPayByCreditCardWithEmptyCardInformation() {
        val creditPage = dashboardPage.payByCreditCard();
        val emptyCardInformation = DataHelper.getEmptyCardInformation();
        creditPage.enterCardInfo(emptyCardInformation);
        creditPage.checkEmptyCardNumberFieldMessage();
        creditPage.checkEmptyMonthFieldMessage();
        creditPage.checkEmptyYearFieldMessage();
        creditPage.checkEmptyOwnerFieldMessage();
        creditPage.checkEmptyCvcFieldMessage();
    }

    @Test
    void shouldPayByCreditCarEmptyNumberCardInformation() {
        val creditPage = dashboardPage.payByCreditCard();
        val emptyNumberCardInformation = DataHelper.getEmptyNumberCardInformation();
        creditPage.enterCardInfo(emptyNumberCardInformation);
        creditPage.checkEmptyCardNumberFieldMessage();
    }

    @Test
    void shouldPayByCreditCarEmptyMonthCardInformation() {
        val creditPage = dashboardPage.payByCreditCard();
        val emptyMonthCardInformation = DataHelper.getEmptyMonthCardInformation();
        creditPage.enterCardInfo(emptyMonthCardInformation);
        creditPage.checkEmptyMonthFieldMessage();
    }

    @Test
    void shouldPayByCreditCarEmptyYearCardInformation() {
        val creditPage = dashboardPage.payByCreditCard();
        val emptyYearCardInformation = DataHelper.getEmptyYearCardInformation();
        creditPage.enterCardInfo(emptyYearCardInformation);
        creditPage.checkEmptyYearFieldMessage();
    }

    @Test
    void shouldPayByCreditCarEmptyOwnerCardInformation() {
        val creditPage = dashboardPage.payByCreditCard();
        val emptyOwnerCardInformation = DataHelper.getEmptyOwnerCardInformation();
        creditPage.enterCardInfo(emptyOwnerCardInformation);
        creditPage.checkEmptyOwnerFieldMessage();
    }

    @Test
    void shouldPayByCreditCarEmptyCvcCardInformation() {
        val creditPage = dashboardPage.payByCreditCard();
        val emptyCvcCardInformation = DataHelper.getEmptyCvcCardInformation();
        creditPage.enterCardInfo(emptyCvcCardInformation);
        creditPage.checkEmptyCvcFieldMessage();
    }

    @Test
    void shouldPayByCreditCardWithInvalidOwner() {
        val creditPage = dashboardPage.payByCreditCard();
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        creditPage.enterCardInfo(invalidOwner);
        creditPage.checkInvalidOwner();
    }

    @Test
    void shouldPayByCreditCardWithInvalidOwnerLink() {
        val creditPage = dashboardPage.payByCreditCard();
        val invalidOwnerLink = DataHelper.getInvalidOwnerCardLink();
        creditPage.enterCardInfo(invalidOwnerLink);
        creditPage.checkInvalidOwner();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidYear0() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidYear0 = DataHelper.getValidCardNumberWithInvalidYear0();
        creditPage.enterCardInfo(validCardNumberWithInvalidYear0);
        creditPage.checkInvalidYear();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidYear00() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidYear00 = DataHelper.getValidCardNumberWithInvalidYear00();
        creditPage.enterCardInfo(validCardNumberWithInvalidYear00);
        creditPage.checkInvalidYear();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidMonth13() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberInvalidMonth13 = DataHelper.getValidCardNumberInvalidMonth13();
        creditPage.enterCardInfo(validCardNumberInvalidMonth13);
        creditPage.checkInvalidMonth();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidMonth0() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberInvalidMonth0 = DataHelper.getValidCardNumberInvalidMonth0();
        creditPage.enterCardInfo(validCardNumberInvalidMonth0);
        creditPage.checkInvalidMonth();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidMonth00() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberInvalidMonth00 = DataHelper.getValidCardNumberInvalidMonth00();
        creditPage.enterCardInfo(validCardNumberInvalidMonth00);
        creditPage.checkInvalidMonth();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidOtherOwner() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidOwner = DataHelper.getValidCardNumberWithInvalidOwner();
        creditPage.enterCardInfo(validCardNumberWithInvalidOwner);
        creditPage.checkInvalidOwner();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidOtherOwner123() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidOwner123 = DataHelper.getValidCardNumberWithInvalidOwner123();
        creditPage.enterCardInfo(validCardNumberWithInvalidOwner123);
        creditPage.checkInvalidOwner();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidOtherOwnerSpecialCharacters() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidOwnerSpecialCharacters = DataHelper.getValidCardNumberWithInvalidOwnerSpecialCharacters();
        creditPage.enterCardInfo(validCardNumberWithInvalidOwnerSpecialCharacters);
        creditPage.checkInvalidOwner();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidCvc000() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidCvc000 = DataHelper.getValidCardNumberWithInvalidCvc000();
        creditPage.enterCardInfo(validCardNumberWithInvalidCvc000);
        creditPage.checkInvalidCvc();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidCvc1() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidCvc1 = DataHelper.getValidCardNumberWithInvalidCvc1();
        creditPage.enterCardInfo(validCardNumberWithInvalidCvc1);
        creditPage.checkInvalidCvc();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidCvc11() {
        val creditPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidCvc11 = DataHelper.getValidCardNumberWithInvalidCvc11();
        creditPage.enterCardInfo(validCardNumberWithInvalidCvc11);
        creditPage.checkInvalidCvc();
    }
}
