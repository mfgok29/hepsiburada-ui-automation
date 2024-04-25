package hepsiburada.pages;

import hepsiburada.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static hepsiburada.helpers.HelperMethods.getRandomIntInRange;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "div[class$='SearchBoxOld']")
    public WebElement searchBox;

    @FindBy(xpath = "//*[@type='text']")
    public WebElement searchBoxInput;

    @FindBy(xpath = "//*[text()='ARA']")
    public WebElement searchButton;

    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement cookieAcceptButton;

    @FindBy(id = "myAccount")
    public WebElement myAccountArea;

    @FindBy(id = "login")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@title='Hepsiburada']")
    public WebElement hepsiburadaButton;

    @FindBy(xpath = "//li[starts-with(@class,'sf-MenuItems')]")
    public List<WebElement> categories;


    public WebElement getRandomCategories(Actions actions,String categoryName) {


        if (!categoryName.equals("Elektronik")) {

            List<WebElement> randomCategories = Driver.get().findElements(
                    By.xpath("//li[contains(@class, 'sf-MenuItems')]//span[text()='"+categoryName
                            +"']/ancestor::li[contains(@class, 'sf-MenuItems')]//a[contains(@class, 'sf-ChildMenuItems')]"));
            int randomCategoriesIndex = getRandomIntInRange(1, randomCategories.size());
            return randomCategories.get(randomCategoriesIndex);
        }
        else {

            String subCategory = "(//ul[starts-with(@class,'sf-MenuItems')]/li[1]//div/ul[1]/li/a)";

            int randomSubCategoryNum = getRandomIntInRange(1, 9);

            String subCategoryXPath = subCategory + "[" + randomSubCategoryNum + "]/../../..";

            WebElement subCategoryElement = Driver.get().findElement(By.xpath(subCategoryXPath));

            actions.moveToElement(subCategoryElement).build().perform();

            String subTitle = "(//*[@class='sf-MenuItems-MXhuaBP08HFYcFicDzFl']/li[1]//div[@class='sf-ChildMenuItems-OMxRJDk0466HYQpTli0P'])" +
                    "[" + randomSubCategoryNum + "]//a[starts-with(@class,'sf-ChildMenuItems')]";

            List<WebElement> subTitles = Driver.get().findElements(By.xpath(subTitle));

            int subTitleSize = subTitles.size();

            int getRandomSubTitleSize = getRandomIntInRange(1, subTitleSize);

            String subTitleLocator = "(" + subTitle + ")" + "[" + getRandomSubTitleSize + "]";

            return Driver.get().findElement(By.xpath(subTitleLocator));

        }
    }


}
