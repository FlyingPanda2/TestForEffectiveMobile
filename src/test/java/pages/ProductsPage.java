package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class ProductsPage {
    private final SelenideElement productsTitle = $(".title");

    @Step("Проверить, что страница товаров загружена")
    public boolean isLoaded() {
        return productsTitle.shouldBe(visible).is(visible);
    }
}