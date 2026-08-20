package UITests;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;


public class DriverExtension implements AfterEachCallback, BeforeEachCallback {
    private final DriverFactory factory = new DriverFactory();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        factory.setUpDriver();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        factory.tearDown();
    }

    public WebDriver getDriver() {
        return factory.getDriver();
    }


}
