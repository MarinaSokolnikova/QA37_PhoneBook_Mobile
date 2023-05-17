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
                .isErrorMessageContainsText("name=must not be blank");
    }

    @Test
    public void createContactWithEmptyLastName(){
        int i = new Random().nextInt(1000);

        Contact contact = Contact.builder()
                .name("Sara"+i)
                .lastName("")
                .email("sara@gmail.com")
                .phone("1234567888"+i)
                .address("Tel Aviv")
                .description("empty last name")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageContainsText("lastName=must not be blank");
    }

    @Test
    public void createContactWithEmptyEmail(){
        int i = new Random().nextInt(1000);

        Contact contact = Contact.builder()
                .name("Sara"+i)
                .lastName("Braun")
                .email("")
                .phone("1234567888"+i)
                .address("Tel Aviv")
                .description("empty email")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageContainsText("Error");

    }

    @Test
    public void createContactWithEmptyPhone(){
        int i = new Random().nextInt(1000);

        Contact contact = Contact.builder()
                .name("Sara"+i)
                .lastName("Braun")
                .email("sara@gmail.com")
                .phone("")
                .address("Tel Aviv")
                .description("empty phone")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageContainsText("phone=Phone number must contain only digits!");
    }

    @Test
    public void createContactWithEmptyAddress(){
        int i = new Random().nextInt(1000);

        Contact contact = Contact.builder()
                .name("Sara"+i)
                .lastName("Braun")
                .email("sara@gmail.com")
                .phone("1234567888"+i)
                .address("")
                .description("empty address")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageContainsText("address=must not be blank");
    }


    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver).logout();
    }
}
