package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;

    @FindBy(xpath = "")
    AndroidElement menuOptions;

    @FindBy(xpath = "")
    AndroidElement logoutButton;

    public boolean isActivityTitleDisplayed(String text) {
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthenticationScreen logout(){
        menuOptions.click();
        logoutButton.click();
        return new AuthenticationScreen(driver);
    }

}
