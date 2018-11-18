package pages;

import app.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Page {

    Logger logger = LoggerFactory.getLogger(Page.class);

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public boolean isLoadedByTitleContains(String substring) {
        wait.until(d -> d.getTitle().contains(substring));
        return true;
    }

    public void switchToWindow(String windowName){
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                System.out.println(d.getTitle());
                check = d.getTitle().equals(windowName);
            }
            return check;
        });
    }

    public void getPage(String url) {
        driver.navigate().to(url);
    }

    //универсальный xpath локатор, вернет все элементы, содержащие текст
    public List<WebElement> xpathSearcherByText(String searchText) {
        String xpath = String.format("//*[contains(text(),'%s')]", searchText);
        return driver.findElements(By.xpath(xpath));
    }
    public void closeCurrentTab(){
        driver.close();
        logger.info("Закрыта активная вкладка");
    }
    public void checkTitle(String value) {
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                check = d.getTitle().equals(value + " - Поиск в Google");
                logger.info(d.getTitle());
                break;
            }
            return check;
        });
    }
    public void checkUrl(String value){
        wait.until(d ->driver.getCurrentUrl().equals(value));
    }
    public void switchToMainTab(){
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }
}