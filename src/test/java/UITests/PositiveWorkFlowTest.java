package UITests;

import PageObjects.MainPage;
import PageObjects.OrderFormPage;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositiveWorkFlowTest {
    @RegisterExtension
    private final DriverExtension extension = new DriverExtension();

    /** Набор данных для параметризованного теста */
    public static Object[][] setTestData() {
        return new Object[][] {
                {"headerOrderButton","Круглов", "Анатолий", "Ленина 1", "ВДНХ", "89111111111", "30.11.2026", 0, 0, ""},
                {"bodyOrderButton","Шарова", "Варвара", "Ленина 2", "Павелецкая", "89222222222", "2026.12.01", 6, 1, "Комментарий"}
        };
    }

    @ParameterizedTest
    @MethodSource("setTestData")
    public void testPositiveOrderFlow(String location, String userName, String userSurname, String deliveryAddress, String metroStationName, String userPhone, String deliveryDate,int rentTimeIndex, int colorIndex, String comment) {
        WebDriver driver = extension.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAcceptCookieButton(); //принимаем куки
        mainPage.clickDifferentOrderButton(location); //кликаем одну из кнопок Заказать в зависимости от аргумента

        OrderFormPage orderFormPage = new OrderFormPage(driver);
        orderFormPage.waitFormPageVisibility(); //ждем отображения формы
        orderFormPage.setName(userName); //вводим имя
        orderFormPage.setSurname(userSurname); //вводим фамилию
        orderFormPage.setDeliveryAddress(deliveryAddress); //вводим адрес доставки
        orderFormPage.clickOnMetroStation(metroStationName); //выбираем станцию метро
        orderFormPage.setPhone(userPhone); //вводим телефон
        orderFormPage.clickOnNextButton(); //кликаем на кнопку Дальше

        orderFormPage.waitFormPageVisibility(); //ждем отображения формы
        orderFormPage.setDeliveryDate(deliveryDate); // вводим дату доставки
        orderFormPage.setRentTime(rentTimeIndex); //выбираем время аренды
        orderFormPage.setColor(colorIndex); //выбираем цвет
        orderFormPage.setComment(comment); //вводим комментарий
        orderFormPage.clickOnOrderButton(); //кликаем на кнопку Заказать

        orderFormPage.clickOnConfirmOrderButton(); //кликаем на кнопку подтверждения заказа

        assertTrue(orderFormPage.checkOrderResultText(), "На странице не обнаружено текста об успешном создании");
    }
}
