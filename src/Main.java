import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "drvs/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("http://www.rshb.ru/");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        WebElement internetBankButton = driver.findElement(By.id("ibank"));
        internetBankButton.click();

        if (driver.getTitle().equals("Интернет-банк РСХБ")) {
            System.out.println("Открыта страница авторизации");

            new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='ВХОД В ИНТЕРНЕТ-БАНК']")));

            WebElement loginInput = driver.findElement(By.id("textfield"));
            loginInput.sendKeys("тест");

            WebElement passInput = driver.findElement(By.id("passwordfield"));
            passInput.sendKeys("тест");

            String loginValue =  loginInput.getAttribute("value");
            String passValue =  passInput.getAttribute("value");

            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

            if (loginValue.equals("тест") & passValue.equals("тест")) {

                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

                WebElement loginBtn = driver.findElement(By.xpath("//*[contains(@class, 'ib-button-text')]"));
                loginBtn.click();

                System.out.println("Кнопка нажата");

                new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"t-error\"]/ul/li")));

                WebElement errorLog = driver.findElement(By.xpath("//div[@class=\"t-error\"]/ul/li"));
                if (errorLog.isDisplayed()) {
                    System.out.println("Сообщение отображено -Логин или пароль введены неверно.- ");
                }
            }
            else
                System.out.println("Логин и пароль не совпадают с проверочными");
        }

        driver.quit();
    }

}
