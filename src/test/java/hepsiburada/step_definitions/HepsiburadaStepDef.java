package hepsiburada.step_definitions;

import hepsiburada.pages.*;
import hepsiburada.utilities.BrowserUtils;
import hepsiburada.utilities.ConfigurationReader;
import hepsiburada.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.time.Duration;
import java.util.ArrayList;


import static hepsiburada.helpers.HelperMethods.getRandomIndex;
import static hepsiburada.helpers.HelperMethods.getRandomIntInRange;

public class HepsiburadaStepDef {

    HomePage homePage = new HomePage();

    LoginPage loginPage = new LoginPage();

    ProductPage productPage = new ProductPage();

    ProductDetailPage productDetailPage = new ProductDetailPage();

    CartPage cartPage = new CartPage();

    ArrayList<String> products = new ArrayList<>();

    WebDriver driver = Driver.get();

    Actions actions = new Actions(driver);

    String expectedPrice;


    @Given("user go to website")
    public void userGoToWebsite(){
        String url = ConfigurationReader.get("hepsiburada_url");

        BrowserUtils.waitForClickablility(homePage.cookieAcceptButton,Duration.ofSeconds(15)).click();

        Assert.assertEquals(url, driver.getCurrentUrl());

        BrowserUtils.waitFor(3);

    }

    @And("user click to login button")
    public void userClickToLoginButton() {

        homePage.myAccountArea.click();

        homePage.loginButton.click();

    }

    @And("user login to the site")
    public void userLoginToTheSite() {
        Assert.assertEquals(driver.getTitle(), "Üye Giriş Sayfası & Üye Ol - Hepsiburada");

        loginPage.mailInput.click();
        loginPage.mailInput.sendKeys(ConfigurationReader.get("mail"));

        loginPage.passwordInput.click();
        loginPage.passwordInput.sendKeys(ConfigurationReader.get("password"));

        loginPage.loginButton.click();


        BrowserUtils.waitForClickablility(homePage.hepsiburadaButton, Duration.ofSeconds(5));

        Assert.assertEquals(driver.getTitle(), "Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com");

    }

    @And("user search {string} and search button")
    public void userSearchAndSearchButton(String productName) {
        homePage.searchBox.click();

        homePage.searchBoxInput.sendKeys(productName);

        homePage.searchButton.click();

        Assert.assertTrue(productPage.searchResultSummary.getText().contains(productName));

    }

    @And("user selects product from list and goes to product page")
    public void userSelectsProductFromListAndGoesToProductPage() {

        for (int i = 0; i < productPage.products.size(); i++) {

            products.add(productPage.products.get(i).getAttribute("href"));
        }

        int index = getRandomIntInRange(0, products.size()-1);

        driver.get(products.get(index));

        Assert.assertEquals(driver.getCurrentUrl(), products.get(index));

    }

    @Then("user likes the product on the product detail page")
    public void userLikesTheProductOnTheProductDetailPage() {

        actions.moveToElement(productDetailPage.productLikeButton).build().perform();

        if (!productDetailPage.productLikeButton.getText().equals("Beğendin")) {

            productDetailPage.productLikeButton.click();

            BrowserUtils.waitForVisibility(productDetailPage.toastMessage, Duration.ofSeconds(3));

            Assert.assertEquals(productDetailPage.toastMessage.getText(), "Ürün listenize eklendi.");


        } else {

            System.out.println("Ürün zaten Beğenilmiş");

        }

    }



    @And("selects a random category and clicks on the subcategory under the category")
    public void selectsARandomCategoryAndClicksOnTheSubcategoryUnderTheCategory() {

        int categoriesIndex = getRandomIndex(homePage.categories.size());

        WebElement categoryName = homePage.categories.get(categoriesIndex);

        BrowserUtils.waitForClickablility(categoryName, Duration.ofSeconds(15));

        actions.moveToElement(categoryName).build().perform();

        WebElement element = homePage.getRandomCategories(actions, categoryName.getText());

        String expectedUrl = element.getAttribute("href");

        element.click();

        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);

    }

    @And("user checks the price of the product on the product detail page")
    public void userChecksThePriceOfTheProductOnTheProductDetailPage() {

        try {
            expectedPrice = productDetailPage.newPrice.getText();
        } catch (Exception e) {
            expectedPrice = productDetailPage.price.getText();
        }

    }

    @And("user adds the product to the cart")
    public void userAddsTheProductToTheCart() {
        productDetailPage.addToCartButton.click();

    }


    @Then("user goes to the cart and checks the price in the cart with the price on the product detail page")
    public void userGoesToTheCartAndChecksThePriceInTheCartWithThePriceOnTheProductDetailPage() {

        BrowserUtils.waitForClickablility(productDetailPage.goToCart, Duration.ofSeconds(15)).click();

        String actualPrice = cartPage.cartPrice.getText();

        Assert.assertEquals(actualPrice, expectedPrice);

        cartPage.cartDeleteButton.click();

        cartPage.confirmDeleteButton.click();

        Assert.assertEquals(cartPage.cartEmptyMessage.getText(), "Sepetin şu an boş");

        System.out.println("Sepet boş olduğu kontrol edildi");

    }
}

