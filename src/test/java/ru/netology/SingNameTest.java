package ru.netology;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class SingNameTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldCardDeliveryWithName() {
        String DateToEnter = DataGenerator.getRandomDate(2);
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(DataGenerator.Registration.generateData("ru").getCity());
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(DateToEnter);
        $("[name='name']").setValue(DataGenerator.Registration.generateData("ru").getName());
        $("[name='phone']").setValue(DataGenerator.Registration.generateData("ru").getPhone());
        $("[class=checkbox__box]").click();
        $(".form-field .button_view_extra").click();
        $(".notification_status_ok .notification__content").waitUntil(visible, 15000).shouldHave(text(DateToEnter));
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        DateToEnter = DataGenerator.getRandomDate(7);
        $("[data-test-id=date] input.input__control").setValue(DateToEnter);
        $(".form-field .button_view_extra").click();
        $(".notification_status_error .notification__content").waitUntil(visible, 15000).shouldHave(text("Перепланировать"));
        $(".notification_status_error .button_view_extra").click();
        $(".notification_status_ok .notification__content").waitUntil(visible, 15000).shouldHave(text(DateToEnter));
    }
}