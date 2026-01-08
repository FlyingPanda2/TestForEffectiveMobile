package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $("[data-test='error']");

    @Step("Открыть страницу логина")
    public LoginPage open() {
        Selenide.open("https://www.saucedemo.com/");
        return this;
    }

    @Step("Ввести логин: {username}")
    public LoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    @Step("Ввести пароль: {password}")
    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать кнопку Login")
    public LoginPage clickLogin() {
        loginButton.click();
        return this;
    }

    @Step("Выполнить вход: {username} / {password}")
    public LoginPage login(String username, String password) {
        return open()
                .setUsername(username)
                .setPassword(password)
                .clickLogin();
    }

    @Step("Проверить, что отображается ошибка")
    public boolean isErrorMessageVisible() {
        return errorMessage.is(visible);
    }

    @Step("Получить текст ошибки")
    public String getErrorMessageText() {
        errorMessage.shouldBe(visible);
        return errorMessage.getText();
    }
}