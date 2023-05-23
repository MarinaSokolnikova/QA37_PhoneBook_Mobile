package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;

    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOptions;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    AndroidElement logoutButton;

    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusButton;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement> contactList;

    @FindBy(id = "android:id/button1")
    AndroidElement yesButton;

    @FindBy(xpath = "//*[@text='No Contacts. Add One more!']")
    AndroidElement contactListEmpty;

    int countBefore;
    int countAfter;

    public ContactListScreen deleteFirstContact(){
        isShouldHave(activityTextView, "Contact list", 10);
        countBefore = contactList.size();
        AndroidElement first = contactList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX()+rectangle.getWidth()/8;
        int yFrom = rectangle.getY()+rectangle.getHeight()/2;
        int xTo = rectangle.getX()+rectangle.getWidth()/8*7;
        int yTo = yFrom;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom, yFrom))
                .moveTo(PointOption.point(xTo, yTo))
                .release()
                .perform();
        should(yesButton, 8);
        yesButton.click();

        shouldLessThan(contactList, countBefore);
        countAfter = contactList.size();
        return this;
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isActivityTitleDisplayed(String text) {
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthenticationScreen logout(){
        if(activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AddNewContactScreen openContactForm(){
        pause(3000);
      if (activityTextView.getText().equals("Contact list")) {
          plusButton.click();
      }
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName){
        isShouldHave(activityTextView, "Contact list", 5);
        boolean isPresent=false;
        for (AndroidElement el: contactNameList){
            if (el.getText().equals(name+" "+lastName)){
                isPresent=true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

    public ContactListScreen isListSizeLessTheOne(){

        Assert.assertEquals(countBefore-countAfter, 1);
        return this;
    }


    public int sizeContactList(){
        return contactList.size();
    }

    public ContactListScreen deleteAllContacts(){
        pause(1000);
        while(contactList.size()>0){
            deleteFirstContact();
        }
        return this;
    }

    public boolean isContactListEmpty(){
        isShouldHave(activityTextView, "Contact list", 8);
        return isElementDisplayed(contactListEmpty);
    }


}
