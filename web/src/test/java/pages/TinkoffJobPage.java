package pages;

import app.Application;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TinkoffJobPage extends Page {
    public TinkoffJobPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void checkErrorNameField(){
        //Заполняем форму максиально быстро, пытаясь игнорировать анимацию страницы
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)",driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).getText());
                    return true;
                });
    }
    public void checkErrorEmailField(){
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) > .FormField__field_1iwkM:nth-child(1) > .Error__errorMessage_q8BBY")).getText());
                    return true;
                });
    }


    public void checkErrorPhoneField(){
        //Заполняем форму максиально быстро, пытаясь игнорировать анимацию страницы
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) > .FormField__field_1iwkM:nth-child(2) > .Error__errorMessage_q8BBY")).getText());
                    return true;
                });
    }
    public void typeNameField(String value){
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.name("fio")).click();
                    driver.findElement(By.name("fio")).clear();
                    driver.findElement(By.name("fio")).sendKeys(value);
                    return true;
                });
    }
    public void typeEmailField(String value){
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.name("email")).click();
                    driver.findElement(By.name("email")).clear();
                    driver.findElement(By.name("email")).sendKeys(value);
                    return true;
                });
    }
    public void typePhoneField(String value){
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.name("phone")).click();
                    driver.findElement(By.name("phone")).clear();
                    driver.findElement(By.name("phone")).sendKeys(value);
                    return true;
                });
    }
    public void typeCityField(String value) {
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.name("city")).click();
                    driver.findElement(By.name("city")).clear();
                    driver.findElement(By.name("city")).sendKeys(value);
                    return true;
                });
    }
    public void typeVacancyField() {
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.cssSelector("div.SelectWrap__root_35mlc")).click();
                    return true;
                });
    }
    public void checkErrorEmptyNameField(){
        driver.findElement(By.xpath("//*[@name='fio']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@data-qa=\"fio\"]/../following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
    }
    public void checkErrorEmptyEmailField(){
        driver.findElement(By.xpath("//*[@name='email']")).click();
        driver.findElement(By.xpath("//*[@name='fio']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@data-qa=\"email\"]/following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
    }


    public void checkErrorEmptyPhoneField(){
        driver.findElement(By.xpath("//*[@name='phone']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath("//*[contains(text(),'Необходимо указать номер телефона')]")).getText());
    }
    public void checkErrorEmptyCityField(){
        driver.findElement(By.xpath("//*[@name='city']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@name=\"city\"]/../../../following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
    }
    public void checkEmptyVacancyField() {
        driver.findElement(By.xpath("//*[contains(text(),'Выберите вакансию')]")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@role=\"listbox\"]/../following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
    }
}
