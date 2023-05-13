package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Auth;
import org.openqa.selenium.support.FindBy;

public class AuthenticationScreen extends BaseScreen{
    public AuthenticationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    AndroidElement emailEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    AndroidElement passwordEditText;

    @FindBy(xpath = "//*[@text='LOGIN']")
    AndroidElement loginButton;

    public AuthenticationScreen fillEmail(String email){
        should(emailEditText, 10);
        type(emailEditText, email);
        return this;
    }



    public AuthenticationScreen fillPassword(String password){
        type(passwordEditText, password);
        return this;
    }

    public ContactListScreen submitLogin(){
        loginButton.click();
        return new ContactListScreen(driver);
    }

    public AuthenticationScreen fillLoginRegistrationForm(Auth auth) {
        should(emailEditText, 10);
        type(emailEditText, auth.getEmail());
        type(passwordEditText, auth.getPassword());
        loginButton.click();
        return this;
    }
}
