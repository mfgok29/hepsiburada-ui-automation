package hepsiburada.pages;

import hepsiburada.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    public ProductPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//*[starts-with(@class, 'productListContent')]//a")
    public List<WebElement> products;

    @FindBy(xpath = "//*[starts-with(@class,'searchResultSummaryBar')]//h1")
    public WebElement searchResultSummary;

}
