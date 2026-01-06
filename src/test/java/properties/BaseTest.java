package properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    @BeforeEach
    public void setUp(){}

    @AfterEach
    public void tearDown(){
        closeWebDriver();
    }
}
