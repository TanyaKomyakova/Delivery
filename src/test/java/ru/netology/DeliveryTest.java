package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {
    @Test
    void checkingCardBooking() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String dateMetting = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));;
        form.$(".calendar-input input").setValue(dateMetting);
        form.$("[data-test-id=name] input").setValue("Комякова Татьяна");
        form.$("[data-test-id=phone] input").setValue("+79631645544");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}
