package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class CardDeliveryTest {

    public String getDate(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldSendValidForm() {
        int daysToAdd = 5;
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        $("[data-test-id=date] input").setValue(getDate(daysToAdd));
        $("[data-test-id=name] input").setValue("Денисов Денис");
        $("[data-test-id=phone] input").setValue("+79993337755");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + getDate(daysToAdd)));


    }
}
