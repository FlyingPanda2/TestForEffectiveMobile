package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты авторизации на Sauce Demo")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успешный логин: standard_user / secret_sauce")
    void testSuccessfulLogin() {
        new LoginPage()
                .login("standard_user", "secret_sauce");

        assertTrue(new ProductsPage().isLoaded(), "Страница товаров должна открыться");
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    void testLoginWithWrongPassword() {
        LoginPage loginPage = new LoginPage()
                .login("standard_user", "wrong_password");

        assertTrue(loginPage.isErrorMessageVisible(), "Должно отображаться сообщение об ошибке");
        assertTrue(loginPage.getErrorMessageText().contains("do not match"),
                "Сообщение должно указывать на неверные учётные данные");
    }

    @Test
    @DisplayName("Логин заблокированного пользователя (locked_out_user)")
    void testLockedOutUser() {
        LoginPage loginPage = new LoginPage()
                .login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorMessageVisible(), "Должна быть ошибка");
        assertTrue(loginPage.getErrorMessageText().contains("locked out"),
                "Сообщение должно говорить о блокировке");
    }

    @Test
    @DisplayName("Логин с пустыми полями")
    void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage()
                .login("", "");

        assertTrue(loginPage.isErrorMessageVisible(), "Ошибка обязательна");
        assertTrue(loginPage.getErrorMessageText().contains("Username is required"),
                "Должно требовать имя пользователя");
    }

    @Test
    @DisplayName("Логин с пользователем performance_glitch_user")
    @Description("Проверяет, что даже при задержках страница загружается корректно")
    void testPerformanceGlitchUser() {
        new LoginPage()
                .login("performance_glitch_user", "secret_sauce");

        assertTrue(new ProductsPage().isLoaded(), "Страница должна загрузиться несмотря на задержки");
    }
}