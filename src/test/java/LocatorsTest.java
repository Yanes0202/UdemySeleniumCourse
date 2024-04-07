import adrian.com.models.Locators;
import org.junit.jupiter.api.Test;

public class LocatorsTest extends AbstractTest {

    @Test
    void test() {
        Locators locators = new Locators();

        locators.logIn();
    }

}
