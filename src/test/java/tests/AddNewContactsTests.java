package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("ssa@gmail.com").password("Ssa12345$").build())
                .submitLogin();
    }

    @Test
    public void createNewContactSuccess(){
        int i = new Random().nextInt(1000);
        Contact contact = Contact.builder()
                .name("Bart")
                .lastName("Flip"+i)
                .email("flip"+i+"@gmail.com")
                .phone("2345678979"+i)
                .address("NY")
                .description("my friend")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());


    }

    @Test
    public void createNewContactSuccessReq(){
        int i = new Random().nextInt(1000);
        Contact contact = Contact.builder()
                .name("Lisa")
                .lastName("Flip"+i)
                .email("flip"+i+"@gmail.com")
                .phone("2345678979"+i)
                .address("LA")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());
    }

    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Kris")
                .email("kris@gmail.com")
                .phone("12345678900")
                .address("NY")
                .description("Empty name")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageContainsText("Error");
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver).logout();
    }
}
