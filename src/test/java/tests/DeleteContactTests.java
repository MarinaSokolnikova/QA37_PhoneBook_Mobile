package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DeleteContactTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("ssa@gmail.com").password("Ssa12345$").build())
                .submitLogin();
    }

    @Test
    public void deleteFirstContact(){
        new ContactListScreen(driver).deleteFirstContact()
                .isListSizeLessTheOne();
    }

    @Test
    public void deleteAllContacts(){
        new ContactListScreen(driver)
                .deleteAllContacts()
                .isContactListEmpty();

    }

}
