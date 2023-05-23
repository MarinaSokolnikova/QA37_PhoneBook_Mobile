package screens;

import config.AppiumConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class BaseScreen {

    AppiumDriver<AndroidElement> driver;

    public BaseScreen(AppiumDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void type(AndroidElement element, String text){
        if (text!=null){
            element.click();
            element.clear();
            element.sendKeys(text);
        }
        driver.hideKeyboard();
    }

    public boolean isShouldHave(AndroidElement element, String text, int time){
        return new WebDriverWait(driver, time).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void should(AndroidElement element, int time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
    }

    public void shouldLessThan(List<AndroidElement> list, int less){
        new WebDriverWait(driver, 5).until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("com.sheygam.contactapp:id/rowContainer"), less));
    }

    public void checkAlertText(String text){
        Alert alert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
    }

    public boolean isElementDisplayed(AndroidElement element){
        try {
            should(element, 8);
            return element.isDisplayed();
        }catch (IllegalAccessError e){
            return false;
        }
    }
}
