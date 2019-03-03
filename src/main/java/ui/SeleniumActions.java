package ui;

import api.JsonHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;
import runner.FrameWorkLauncher;

import java.util.List;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class SeleniumActions {

    static WebDriver driver;


    public void openUrl(String url) {
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    public void selectFlightFrom(String abc) throws Exception {
        WebElement element = driver.findElement(By.name("FlightFrom"));
        Select select = new Select(element);
        List l = JsonHandler.foundValues;
        try {
            for (Object str : l) {
                select.selectByVisibleText(str.toString());
            }
        } catch (Exception e) {

        }
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        List list = driver.findElements(By.xpath("//form[text()='Data Saved Sucessfully']"));
        driver.close();
        if (list.size() == 0) {
            FrameWorkLauncher.result.add("selectFlightFrom,Fail");
        } else {
            FrameWorkLauncher.result.add("selectFlightFrom,Pass");
        }


    }
}