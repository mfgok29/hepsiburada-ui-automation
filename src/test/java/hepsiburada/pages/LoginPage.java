package hepsiburada.pages;

import hepsiburada.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  {

    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }


    @FindBy(id = "txtUserName")
    public WebElement mailInput;

    @FindBy(id = "txtPassword")
    public WebElement passwordInput;

    @FindBy(id = "btnLogin")
    public WebElement loginButton;


}
