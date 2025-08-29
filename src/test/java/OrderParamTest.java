import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OrderParamTest {

    private WebDriver driver;
    private MainPageSamokat page;

    @BeforeEach
    public void setup() {
        //  FirefoxOptions options = new FirefoxOptions();
        // driver = new FirefoxDriver(options);

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @ParameterizedTest
    @CsvSource({
            "top, Сергей, Иванов, Иваново, Черкизовская, 89203675234, 7, 1, 1, Даров",
            "bottom, Анна, Петрова, Москва, Сокольники, 89161234567, 5, 2, 2, Без звонка"
    })
    public void orderParameterized(String whichButton, String name, String surname,
                                   String location, String station, String phoneNumber,int dateIndex, int dateRangeIndex, int colorIndex, String comment) {
        MainPageSamokat objMainPageSamokat = new MainPageSamokat(driver);

        objMainPageSamokat.clickOrderButton(whichButton);
        objMainPageSamokat.fillName(name);
        objMainPageSamokat.fillSurname(surname);
        objMainPageSamokat.fillLocation(location);
        objMainPageSamokat.chooseMetro(station);
        objMainPageSamokat.fillPhoneNumber(phoneNumber);
        objMainPageSamokat.closeCookieWindow();
        objMainPageSamokat.clickButtonNext();
        objMainPageSamokat.chooseDate(dateIndex);
        objMainPageSamokat.chooseDateRange(dateRangeIndex);
        objMainPageSamokat.chooseColor(colorIndex);
        objMainPageSamokat.commentForDeliveryMan(comment);
        objMainPageSamokat.pressOrderButton();
        objMainPageSamokat.pressConfirmOrderButton();
        System.out.println(objMainPageSamokat.returnOrderConfirmed());

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}

