package hepsiburada.pages;

import hepsiburada.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {

    public ProductDetailPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//*[starts-with(@class,'customerAccount-Like')][@role='presentation']")
    public WebElement productLikeButton;

    @FindBy(xpath = "//*[@class='hb-toast-text']")
    public WebElement toastMessage;

    @FindBy(id = "offering-price")
    public WebElement price;

    @FindBy(xpath = "//div[@class='green-band visible']//span[@class='newPrice']")
    public WebElement newPrice;

    @FindBy(id = "addToCart")
    public WebElement addToCartButton;

    @FindBy(xpath = "//*[text()='Sepete git']")
    public WebElement goToCart;

}
