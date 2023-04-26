package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSuit {
    static String expectedReferAFriendProductMsg = "Your massage has not  been sent.";
    static String expectedMassageForVoting = "User can not vote.";
    static String expectedRegistrationCompleteMsg = "Registration is not working.";
    static String expectedEmailMassage ="All customers can use email a friend feature.";
    static String expectedProductName = "Your Product Name is not match.";
    static String expectedTwoProductName ="You have two items to compare.";
    static String expectedErrorMassage = "Your vote was successfully.";
    protected  static  WebDriver driver;
    public static void clickElement(By by){
        driver.findElement(by).click();
    }
    public static void typeText(By by,String text){
        driver.findElement(by).sendKeys(text);
    }
    public static String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }
    public static long timestamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }
    @AfterMethod
   public static void closeBrowser(){
        //close browser
      driver.close();}

    @BeforeMethod
    public static void openBrowser(){
        //open the browser
      driver = new ChromeDriver();
    //type url
       driver.get("https://demo.nopcommerce.com/");
    //for browser open 10 second
       driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
       //for window maximize
       driver.manage().window().maximize();}


    @Test
    public static void verifyRegisteredUserShouldAbleToReferAProductToAFriendSuccessfully(){
        //click on register link
      clickElement(By.className("ico-register"));
      //type first naME
        typeText(By.id("FirstName"),"raj");
        //type last name
        typeText(By.id("LastName"),"patel");
        //type email
        typeText(By.name("Email"),"shama12@gmail.com");
       //type password
        typeText(By.id("Password"),"Test1234");
        //type  conform password
        typeText(By.name("ConfirmPassword"),"Test1234");
        //click on register button
        clickElement(By.name("register-button"));
        //log in link
        clickElement(By.className("ico-login"));
        //type email
        typeText(By.xpath("//div[@class=\"form-fields\"]/div[1]/input"),"shama12@gmail.com");
        //type password
        typeText(By.xpath("//div[@class=\"form-fields\"]/div[2]/input"),"Test1234");
        //click on login
        clickElement(By.xpath("//div[@class=\"returning-wrapper fieldset\"]/form/div[3]/button"));
        //click on digital downloads
        clickElement(By.xpath("//img[@alt='Picture for category Digital downloads']"));
        //click on london grammar add to cart
        clickElement(By.xpath("//div[@class=\"item-grid\"]/div[1]/div/div[2]/div[3]/div[2]/button[1]"));
        //click on email a friend
        clickElement(By.xpath("//div[@class='email-a-friend']"));
        //type  friend's email
        typeText(By.className("friend-email"),"rajpatel"+timestamp()+"@gmail.com");
        //type your email
       // typeText(By.xpath("//input[@placeholder=\"Enter your email address.\"]");
        //click send email
        clickElement(By.name("send-email"));
        //product name
         String actualMassage = getTextFromElement(By.xpath("//a[@class='product']"));
        System.out.println("Product name: "+actualMassage);
        //get your massage has been sent
        String actualMsg = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
        System.out.println("massage is:"+actualMsg);
        Assert.assertEquals(actualMsg,expectedReferAFriendProductMsg,"your massage has not been send");
    }
    @Test
    public static void verifyRegisteredUsersShouldAbleToVoteSuccessfully(){
        //click on register link
        clickElement(By.className("ico-register"));
        //type first name
        typeText(By.id("FirstName"),"raj");
        //type lastname
        typeText(By.id("LastName"),"patel");
        //type email
        typeText(By.name("Email"),"shyama12@gmail.com");
        // type password
        typeText(By.id("Password"),"Test1234");
        //type conform password
        typeText(By.name("ConfirmPassword"),"Test1234");
        //click on register button
        clickElement(By.name("register-button"));
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        //log in link
        clickElement(By.className("ico-login"));
        //type email
        typeText(By.xpath("//div[@class=\"form-fields\"]/div[1]/input"),"shyama12@gmail.com");
        //type password
        typeText(By.xpath("//div[@class=\"form-fields\"]/div[2]/input"),"Test1234");
        //click on login
        clickElement(By.xpath("//div[@class=\"returning-wrapper fieldset\"]/form/div[3]/button"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='pollanswers-2']")));
        //click on radio button good
        clickElement(By.xpath("//input[@id='pollanswers-2']"));
        //Click on vote
        clickElement(By.id("vote-poll-1"));
        //get vote result
         String actualMassage =getTextFromElement(By.className("poll-total-votes"));
        System.out.println("actual massage for vote:"+actualMassage);
        Assert.assertEquals(actualMassage,expectedMassageForVoting,"User can not vote");
    }
    @Test
    public static void verifyToUserShouldBeAbleToRegisterSuccessfully(){
        //click on register link
        clickElement(By.className("ico-register"));
        //type first name
        typeText(By.id("FirstName"),"raj");
        //type lastname
        typeText(By.id("LastName"),"patel");
        //type email
        typeText(By.name("Email"),"rajp56+"+timestamp()+"@gmail.com");
        // type password
        typeText(By.id("Password"),"Test1234");
        //type conform password
        typeText(By.name("ConfirmPassword"),"Test1234");
        //click on register button
        clickElement(By.name("register-button"));
        // get the massage
        String actualMassage = getTextFromElement(By.xpath("//div[@class='result']"));
        // print for massage
        System.out.println(" massage is :"+actualMassage);
        //for match 2 result
        Assert.assertEquals(actualMassage,expectedRegistrationCompleteMsg,"Registration is not working");

    }
    @Test
    public static void verifyNonRegisteredShouldNotAbleToEmailAFriend() {
        //Click on add to cart button
        clickElement(By.xpath("//div[@class=\"item-grid\"]/div[2]/div/div[2]/div[3]/div[2]/button[1]"));
        //Click on Email a friend
        clickElement(By.xpath("//div[@class='email-a-friend']"));
        //Type a friend email address
        typeText(By.className("friend-email"), "rj@gmail.com");
        //Type a your email eddress
        typeText(By.className("your-email"), "bh23@gmail.com");
        //Click on send email
        clickElement(By.name("send-email"));
        //get error massage
        String actualMassage = getTextFromElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]/ul/li"));
        System.out.println("Error Massage :" + actualMassage);
        Assert.assertEquals(actualMassage,expectedEmailMassage,"All customers can use email a friend feature");

    }
    @Test
    public static void verifyUserShouldBeAbleToSeeProductInShoppingCartSuccessfully(){
        //click on electronic
        clickElement(By.xpath("//div[@class=\"master-wrapper-page\"]/div[2]/ul[1]/li[2]/a[@href=\"/electronics\"]"));
        //click on camera & photo
        clickElement(By.xpath("//img[@alt ='Picture for category Camera & photo']"));
        //get name of product
        String productname = getTextFromElement(By.xpath("(//h2[@class=\"product-title\"])[3]"));
        //print name of product
        System.out.println("Product name:"+productname);
        // click on add to cart button
        clickElement(By.xpath("//div[@class=\"item-grid\"]/div[3]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        //click on shopping cart link
        clickElement(By.xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/cart\"]"));
        //get product name
        String actualProductName = getTextFromElement(By.className("product-name"));
        //print product name
        System.out.println("product name:"+actualProductName);
        Assert.assertEquals(actualProductName,expectedProductName,"Your Product Name is not match");
    }
    @Test
    public static void verifyUserShouldBeAbleToCompareTwoProductSuccessfully(){
        //Click on First product Htc 18
        clickElement(By.xpath("//div[@class=\"product-grid home-page-product-grid\"]/div[2]/div[3]/div/div[2]/div[3]/div[2]/button[2]"));
        //close popup  green window
        clickElement(By.xpath("//span[@class= \"close\"]"));
        //Click on virtual Card
        clickElement(By.xpath("//div[@class=\"product-grid home-page-product-grid\"]/div[2]/div[4]/div/div[2]/div[3]/div[2]/button[2]"));
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        //close popup  green window
        clickElement(By.xpath("//span[@class= \"close\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"bar-notification\"]/div/p/a")));
        //Click on popup window green notification
        clickElement(By.xpath("//div[@id=\"bar-notification\"]/div/p/a"));
        // get first  product name
       String productName = getTextFromElement(By.xpath("//table[@class=\"compare-products-table\"]/tbody/tr[3]/td[2]/a"));
        System.out.println("Frist product name "+productName);
        //get product second name
        String productname = getTextFromElement(By.xpath("//table[@class=\"compare-products-table\"]/tbody/tr[3]/td[3]/a"));
        System.out.println("Second product name "+productname);
        // click on clear list
        clickElement(By.className("clear-list"));
        //get massage
        String actualmassage = getTextFromElement(By.className("no-data"));
        System.out.println(" massage "+actualmassage);
        Assert.assertEquals(actualmassage,expectedTwoProductName,"You have two items to compare.");
    }
    @Test
    public static void verifyNOnRegisteredUserShouldNotBeAbleToVote(){
        //click on good redio button
        clickElement(By.id("pollanswers-2"));
        //Click on vote
        clickElement(By.id("vote-poll-1"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"poll-vote-error\"]")));
        //get error massage
        String actualMassage = getTextFromElement(By.xpath("//div[@class=\"poll-vote-error\"]"));
        System.out.println("Error Massage:"+actualMassage);
        Assert.assertEquals(actualMassage,expectedErrorMassage,"Your vote was successfully.");

    }


}



