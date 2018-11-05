import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Case1 extends BaseRunner{
    @Test
    public void  exe(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://google.ru/");
        driver.findElement(By.name("q")).sendKeys("мобайл тинькофф");
        driver.findElements(By.xpath("//ul[@role='listbox']/li"));
        wait.ignoring(StaleElementReferenceException.class)
                .withMessage("Что-то пошло не так...")
                .pollingEvery(Duration.ofMillis(500))
                .until(d -> {
                    By listItems = By.xpath("//ul[@role='listbox']/li[@role='presentation' and .//*[@role='option']]");
                    List<WebElement> elements = driver.findElements(listItems);
                    for (WebElement el : elements) {
                        System.out.println(el.getText());
                        if (el.getText().equals("мобайл тинькофф тарифы")) el.click();
                        break;
                    }
                    return d.getTitle();
                });

        driver.findElement(By.cssSelector("a[href*='https://www.tinkoff.ru/mobile-operator/tariffs/']")).click();
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                System.out.println(d.getTitle());
                check = d.getTitle().equals("Тарифы Тинькофф Мобайл");
            }
            return check;
        });
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                check = d.getTitle().equals("мобайл тинькофф тарифы - Поиск в Google");
                System.out.println(d.getTitle());
                break;
            }
            return check;
        });
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        wait.until(d -> driver.getCurrentUrl().equals("https://www.tinkoff.ru/mobile-operator/tariffs/"));
    }
}
