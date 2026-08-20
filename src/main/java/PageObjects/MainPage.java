package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import static java.time.Duration.ofSeconds;

public class MainPage {
    private final WebDriver driver;

    /** Конструктор класса MainPage */
    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    /** Кнопка принятия куков */
    private final By acceptCookieButton = By.id("rcc-confirm-button");

    /** Вопросы в аккордионе */
    private final By accordionHeaders = By.cssSelector(".accordion__heading");

    /** Ответы в аккордионе */
    private final By accordionItems = By.xpath(".//div[@class='accordion__panel']/p");

    /** Кнопка Заказать в шапке сайта */
    private final By headerOrderButton = By.xpath(".//div[starts-with(@class, 'Header_Nav')]/button[starts-with(@class, 'Button_Button') and text() = 'Заказать']");

    /** Кнопка заказать в теле сайта */
    private final By bodyOrderButton = By.xpath(".//div[starts-with(@class, 'Home_RoadMap')]//button[starts-with(@class, 'Button_Button') and text() = 'Заказать']");

    /** Метод ожидания кликабельности кнопки принятия куков и ее нажатия */
    public void clickOnAcceptCookieButton() {
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(acceptCookieButton));
        driver.findElement(acceptCookieButton).click();
    }

    /** Метод прокрутки до вопроса, ожидания кликабельности и клика на него в аккордионе */
    public void clickAccordionHeader(int index) {
        WebElement accordionHeader = driver.findElements(accordionHeaders).get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", accordionHeader);
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(accordionHeader));
        accordionHeader.click();
    }

    /** Метод ожидания появления ответа на вопрос после клика на него */
    public void waitAccordionAnswerToBeVisible(int index) {
        By specificAnswerLocator = By.xpath("(//div[@class='accordion__panel']/p)[" + (index + 1) + "]");
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(specificAnswerLocator));
    }

    /** Метод получения текста вопроса в аккордионе */
    public String getAccordionHeaderText(int index) {
        return driver.findElements(accordionHeaders).get(index).getText();
    }

    /** Метод получения текста ответа на вопрос в аккордионе*/
    public String getAccordionItemText(int index) {
        return driver.findElements(accordionItems).get(index).getText();
    }

    /** Метод ожидания кликабельности кнопки Заказать в шапке и клика на нее */
    public void clickOnHeaderOrderButton() {
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(headerOrderButton));
        driver.findElement(headerOrderButton).click();
    }

    /** Метод прокрутки до кнопки Заказать в теле, ожидания кликабельности и клика на нее */
    public void clickOnBodyOrderButton() {
        WebElement webElementBodyOrderButton = driver.findElement(bodyOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", webElementBodyOrderButton);
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(webElementBodyOrderButton));
        webElementBodyOrderButton.click();
    }

    /** Метод клика на разные кнопки заказа в зависимости от аргумента параметрического теста
     * Можно, конечно, сделать два одинаковых параметрических теста с разницой лишь в кнопке Заказать, и тогда
     * прогонятся оба набора данных через обе кнопки, и так будет правильнее, т.к. тестирование будет проходить
     * на всех данных, даже если если вдруг одна из кнопок сломается, но пока сделал так для ускорения отладки
     */
    public void clickDifferentOrderButton(String location) {
        switch (location) {
            case "headerOrderButton":
                clickOnHeaderOrderButton();  // внутри уже есть ожидание и клик
                break;
            case "bodyOrderButton":
                clickOnBodyOrderButton();    // внутри уже есть прокрутка, ожидание и клик
                break;
            default:
                throw new IllegalArgumentException(
                        "Неверный аргумент location='" + location + "'. Допустимые значения: 'headerOrderButton', 'bodyOrderButton'"
                );
        }
    }
}
