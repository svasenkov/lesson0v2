package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;


public class AlfaBankTest {

    @Test
    void testDeposit(){
        open("http://alfabank.ru");

        $("body").shouldHave(text("Частным лицам"));
        $(byText("Вклады")).click();

        $("body").shouldHave(text("Вклады и инвестиции"));
        $$(byText("Депозиты")).find(visible).click();

        $(".product-cell__row_recharged:not(.disabled)").$(".product-cell__cell-header").sibling(0).preceding(0).shouldHave(text("Самый высокий доход"));

    }

    @Test
    void checkArchivalDepositeTest(){
        open("http://alfabank.ru");

        $(byText("Вклады")).click();
        $$(byText("Депозиты")).find(visible).click();
        $(byLinkText("Архивные депозиты")).click();

        $("body").shouldHave(text("Архивные депозиты"));

        $$(".product-cell__cell-wrapper").shouldHave(size(3));
    }

    @Test
    void insuranseSiblingTest(){
        open("http://alfabank.ru");

        $(byText("Вклады")).click();

        $(byClassName("selected")).sibling(4).click();

        $("body").shouldHave(text("Страхование вкладов"));

    }

    @Test
    void insuranPrecedingClosestTest(){
        open("http://alfabank.ru");

        $(byText("Вклады")).click();

        $(byTitle("Специальный счет по 44-ФЗ")).closest("li").preceding(0).click();

        $("body").shouldHave(text("Страхование вкладов"));
     }
}
