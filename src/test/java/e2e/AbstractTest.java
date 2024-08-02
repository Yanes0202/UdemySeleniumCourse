package e2e;

import adrian.com.managers.Driver;
import org.junit.jupiter.api.BeforeAll;

import java.time.Duration;

public abstract class AbstractTest {

    @BeforeAll
    static void before() {
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
}
