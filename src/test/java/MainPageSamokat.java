import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPageSamokat {

    private final WebDriver driver;

    public MainPageSamokat(WebDriver driver){
        this.driver = driver;
    }

    //описание элементов экрана "Для кого самокат"

    //закрытие окна куки
    public void closeCookieWindow(){
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }

    //Клик на кнопку заказа с выбором на какую именно кнопку нажимать
    public void clickOrderButton(String whichButton) {
        if (whichButton.equals("top")) {
            driver.findElement(By.className("Button_Button__ra12g")).click();
        }
        else {
            WebElement element = driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button")).click();
        }
    }

    //ввод имени
    public void fillName(String name){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input")).sendKeys(name);
    }

    //ввод фамилии
    public void fillSurname(String surname){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input")).sendKeys(surname);
    }

    //ввод локации
    public void fillLocation(String location){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input")).sendKeys(location);
    }

    //выбор станции метро
    public void chooseMetro(String station){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div/input")).click();
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div/input")).sendKeys(station);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]")
                )
        );
        option.click();
    }

    //ввод номера телефона
    public void fillPhoneNumber(String phoneNumber){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input")).sendKeys(phoneNumber);
    }

    //клик на кнопку "Далее"
    public void clickButtonNext(){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[3]/button")).click();
    }

    //Описание элементов экрана "Про аренду"

    //Выбор даты
    public void chooseDate(int dateIndex){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input")).click();
        String xpath = String.format(".//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div[3]/div[%d]", dateIndex);
        driver.findElement(By.xpath(xpath)).click();
    }

    //выбор срока аренды
    public void chooseDateRange(int dateRangeIndex){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div[1]")).click();
        String xpath = String.format(".//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[%d]", dateRangeIndex);
        driver.findElement(By.xpath(xpath)).click();
    }


    //выбор цвета самоката
    public void chooseColor(int colorIndex){
        String xpath = String.format(".//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/label[%d]", colorIndex);
        driver.findElement(By.xpath(xpath)).click();
    }

    //комментарий для курьера
    public void commentForDeliveryMan(String comment){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input")).sendKeys(comment);
    }

    //нажатие на кнопку заказать
    public void pressOrderButton(){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[3]/button[2]")).click();
    }

    //нажатие на кнопку Да подтверждения заказа
    public void pressConfirmOrderButton(){
        driver.findElement(By.xpath(".//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]")).click();
    }

    //проверка получения окна о подтверждении заказа
    public String returnOrderConfirmed(){
        return driver.findElement(By.xpath("./html/body/div/div/div[2]/div[5]/div[1]")).getText();
    }





    //описание раздела вопросов-ответов


    //проскроллить до списка вопросов
    public void scrollToList(){
        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //кликнуть на элемент списка
    public void clickAccordionByIndex(int index) {
        String id = String.format("accordion__heading-%d", index);
        driver.findElement(By.id(id)).click();
    }

    //взять текст из элемента
    public String getAccordionTextByIndex(int index) {
        String xpath = String.format("//div[@id='accordion__panel-%d']/p", index);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    //обьединили шаги кликнуть на элемент списка и взять текст из элемента
    public String getTextFromAccordionElement(int index){
        clickAccordionByIndex(index);
        return getAccordionTextByIndex(index);
    }



}
