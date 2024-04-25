package hepsiburada.pages;

import hepsiburada.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    public CartPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "(//*[starts-with(@class,'product_price_')])[3]")
    public WebElement cartPrice;

    @FindBy(xpath = "//*[starts-with(@class,'basket_delete')]")
    public WebElement cartDeleteButton;

    @FindBy(xpath = "//*[text()='Tümünü sil']")
    public WebElement confirmDeleteButton;

    @FindBy(xpath = "//*[starts-with(@class,'empty_basket_container')]//h1")
    public WebElement cartEmptyMessage;
}
