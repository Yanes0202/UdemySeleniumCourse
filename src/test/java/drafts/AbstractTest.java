package drafts;

import adrian.com.managers.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Dimension;

public abstract class AbstractTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Test Started");
        Driver.getDriver();
        Driver.getDriver().manage().window().setSize(new Dimension(1920,1080));
    }

    @AfterAll
    static void afterAll() {
        Driver.quitDriver();
        System.out.println("Test finished");
    }
}
