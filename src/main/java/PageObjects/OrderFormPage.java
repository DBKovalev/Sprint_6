package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;


public class OrderFormPage {
    private final WebDriver driver;

    /** Конструктор класса OrderFormPage */
    public OrderFormPage(WebDriver driver){
        this.driver = driver;
    }

    /** Поля формы заказа */
    private final By orderForm = By.xpath(".//div[starts-with(@class, 'Order_Form')]");

    /** Поле ввода имени */
    private final By nameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder, 'Имя')]");

    /** Поле ввода фамилии */
    private final By surnameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder, 'Фамилия')]");

    /** Поле ввода адреса доставки */
    private final By deliveryAddressInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder, 'Адрес: куда привезти заказ')]");

    /** Селектор станций метро */
    private final By metroSelector = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder, 'Станция метро')]");

    /** Поле ввода телефона */
    private final By userPhoneInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder, 'Телефон: на него позвонит курьер')]");

    /** Кнопка далее в форме ввода данных */
    private final By nextButton = By.xpath(".//button[starts-with(@class, 'Button_Button') and text()='Далее']");

    /** Поле ввода даты доставки*/
    private final By deliveryDateInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder, 'Когда привезти самокат')]");

    /** Датапике даты доставки*/
    private final By deliveryDatePicker = By.xpath(".//div[starts-with(@class, 'Order_Form')]//div[@class= 'react-datepicker']");

    /** Поле раскрытия выбора срочности аренды */
    private final By rentTimeDropdown = By.xpath(".//div[starts-with(@class, 'Order_Form')]/div[starts-with(@class, 'Dropdown-root')]");

    /** Выпадающий список срочности аренды */
    private final By rentTimeDropdownItem = By.xpath(".//div[starts-with(@class, 'Order_Form')]//div[starts-with(@class, 'Dropdown-option')]");

    /** Чекбокс цветов самоката */
    private final By colorCheckboxes = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]//input[(@id='black') or (@id='grey')]");

    /** Поле ввода комментария для курьера */
    private final By commentInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder, 'Комментарий для курьера')]");

    /** Кнопка заказать в форме ввода данных */
    private final By orderButton = By.xpath(".//div[starts-with(@class, 'Order_Button')]/button[starts-with(@class, 'Button_Button') and text()='Заказать']");

    /** Кнопка подтверждения заказа в попапе подтверждения */
    private final By confirmOrderButton = By.xpath(".//div[starts-with(@class, 'Order_Buttons')]/button[starts-with(@class, 'Button_Button') and text()='Да']");

    /** Уведомление об успешном создании заказа */
    private final By orderCreatedText = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    /** Метод ожидания загрузки страницы формы */
    public void waitFormPageVisibility (){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderForm));
    }

    /** Метод ввода имени в поле */
    public void setName (String userName){
        driver.findElement(nameInput).sendKeys(userName);
    }

    /** Метод ввода имени в поле */
    public void setSurname (String userSurname){
        driver.findElement(surnameInput).sendKeys(userSurname);
    }

    /** Метод ввода адреса доставки в поле */
    public void setDeliveryAddress (String deliveryAddress){
        driver.findElement(deliveryAddressInput).sendKeys(deliveryAddress);
    }

    /** Метод клика на кнопку метро с соответствующим текстом */
    public void clickOnMetroStation(String metroStationName) {
        WebElement metroField = driver.findElement(metroSelector);
        metroField.click();
        By stationLocator = By.xpath("//*[contains(text(), '" + metroStationName + "')]/ancestor::button");
        WebElement station = new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(stationLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", station);
        station.click();
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.invisibilityOf(station));
    }

    /** Метод ввода телефона в поле */
    public void setPhone (String userPhone){
        driver.findElement(userPhoneInput).sendKeys(userPhone);
    }

    /** Метод клика на кнопку далее */
    public void clickOnNextButton() {
        driver.findElement(nextButton).click();
    }

    /** Метод ввода даты */
    public void setDeliveryDate(String deliveryDate) {
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(deliveryDateInput));
        WebElement dateInput = driver.findElement(deliveryDateInput);
        dateInput.sendKeys(deliveryDate);
        dateInput.sendKeys(Keys.ENTER);
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(deliveryDatePicker));
    }

    /** Метод выбора срока аренды в списке */
    public void setRentTime(int index){
        driver.findElement(rentTimeDropdown).click();
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(rentTimeDropdownItem));
        driver.findElements(rentTimeDropdownItem).get(index).click();
    }

    /** Метод выбора цвтеа */
    public void setColor(int index){
        driver.findElements(colorCheckboxes).get(index).click();
    }

    /** Метод ввода комментария */
    public void setComment(String comment){
        driver.findElement(commentInput).sendKeys(comment);
    }

    /** Метод клика на кнопку заказать */
    public void clickOnOrderButton(){
        driver.findElement(orderButton).click();
    }

    /** Метод клика на кнопку пожтверждения заказа */
    public void clickOnConfirmOrderButton(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        driver.findElement(confirmOrderButton).click();
    }

    /** Метод проверки наличия текста об успешном создании заказа в попапе */
    public boolean checkOrderResultText(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderCreatedText));
        String actualText = driver.findElement(orderCreatedText).getText().toLowerCase();
        return actualText.contains("заказ оформлен");

    }


}
