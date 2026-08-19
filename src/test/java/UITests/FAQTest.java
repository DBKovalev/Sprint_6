package UITests;

import PageObjects.MainPage;
import org.openqa.selenium.WebDriver;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Assertions;

public class FAQTest {
    @RegisterExtension
    private final DriverExtension extension = new DriverExtension();

    /** Набор данных для параметризованного теста */
    public static Object[][] setTestData() {
        return new Object[][] {
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." }, //тут обнаружил опечатку, и поправил ожидмаемый текст, чтоб он ее находил
        };
    }

    @ParameterizedTest
    @MethodSource("setTestData")
    public void checkQuestionsAndAnswersInFAQAccordion(int numberOfAccordionItem, String expectedHeaderText, String expectedItemText) {
        WebDriver driver = extension.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAcceptCookieButton(); //принимаем куки
        mainPage.clickAccordionHeader(numberOfAccordionItem); //кликаем на вопрос
        mainPage.waitAccordionAnswerToBeVisible(numberOfAccordionItem); //ждем раскрытия ответа
        String actualHeaderText = mainPage.getAccordionHeaderText(numberOfAccordionItem);
        String actualItemText = mainPage.getAccordionItemText(numberOfAccordionItem);
        Assertions.assertEquals(expectedHeaderText, actualHeaderText, "Не совпали ожидаемый и фактический тексты вопроса");
        Assertions.assertEquals(expectedItemText, actualItemText, "Не совпали ожидаемый и фактический тексты ответа");
    }
}
