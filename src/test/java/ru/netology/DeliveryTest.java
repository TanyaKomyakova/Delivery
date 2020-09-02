package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Registration.generateClient;
import static ru.netology.DataGenerator.Registration.generateDate;

public class DeliveryTest {
    @Test
    void checkingCardBooking1() {
        Client client = generateClient();
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue(client.getCity());
        form.$(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$(".calendar-input input").setValue(generateDate(8));
        form.$("[data-test-id=name] input").setValue(client.getName());
        form.$("[data-test-id=phone] input").setValue(client.getPhone());
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000).shouldHave(exactText("Успешно! Встреча успешно запланирована на 10.09.2020"));
    }

    @Test
    void checkingCardBooking2() {
        Client client = generateClient();
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue("Пенза");
        form.$(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$(".calendar-input input").setValue(generateDate(8));
        form.$("[data-test-id=name] input").setValue("Котейконька Татьяна");
        form.$("[data-test-id=phone] input").setValue("+79636662222");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000).shouldHave(exactText("Успешно! Встреча успешно запланирована на 10.09.2020"));
    }

    @Test
    void checkingCardBooking3() {
        Client client = generateClient();
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue("Пенза");
        form.$(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$(".calendar-input input").setValue(generateDate(9));
        form.$("[data-test-id=name] input").setValue("Котейконька Татьяна");
        form.$("[data-test-id=phone] input").setValue("+79636662222");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        form.$("button").waitUntil(visible, 15000).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000).shouldHave(exactText("Успешно!Встреча успешно запланирована на 11.09.2020"));


    }


}
