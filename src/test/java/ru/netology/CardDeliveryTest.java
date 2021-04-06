package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    public String setDate() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, 5);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(currentDate.getTime());
    }
    @Test
    void shouldSendValidForm() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        $("[data-test-id=date] input").setValue(setDate());
        $("[data-test-id=name] input").setValue("Денисов Денис");
        $("[data-test-id=phone] input").setValue("+79993337755");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));


    }
}
