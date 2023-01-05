package TestCases;

import PageClasses.HomePage;
import PageClasses.LogInPage;
import PageClasses.PaymentPage;
import PageClasses.ProductPage;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.checkerframework.checker.units.qual.C;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

//Running the tests in order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SanityTest {

    //Creating instances of classes
    private static ExtentReports extent;
    private static ExtentTest test;
    public  static WebDriver driver;

    LogInPage login = new LogInPage(driver);
    HomePage homePage = new HomePage(driver);
    ProductPage product = new ProductPage(driver);
    PaymentPage payment = new PaymentPage(driver);

   // Creating a variable that will contain the expected title of the  page
    String extTitle;
    // Creating a variable that will contain the actual title of the  page
    String actTitle = "not found";
    //Creating a variable that will contain a unique name for the screenshots based on currentTimeMillis
    String currentTime = String.valueOf(System.currentTimeMillis());
    //Creating a variable that will contain a location to save the screenshots
    String imagePath = Constants.imagePath;
    //Creating a variable that will contain the test name to use in the reports
    String testName;

    @BeforeClass
    public static void   beforeClass() throws Exception {

     ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Constants.extentPath);
        ExtentReports reports = new ExtentReports();
// attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("sanity test for next");
//test.createNode("beforeTest");
        test.assignCategory("before");
        boolean driverEstablish = false;
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("-incognito");
            options.addArguments("--disable-extensions");
              options.addArguments("--profile-directory=Default");
              options.addArguments("--disable-plugins-discovery");
           options.addArguments("--start-maximized");
           System.setProperty("webdriver.chrome.driver","c:/selenium/chromedriver.exe");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get("https://www.next.co.il/en/homeware");
            driverEstablish = true;

        } catch (Exception e) {
            e.printStackTrace();
            fail("Cant connect chromeDriver");
            test.log(Status.FATAL, "Driver Connection Failed! " + e.getMessage());
            driverEstablish = false;
        } finally {
            if (driverEstablish) {
                test.log(Status.PASS, "Driver established successfully");

            }
       }
    }

    //The test checks that you can log in into the account
 //   @Ignore
    @Test
    public  void A_login() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        //Put the test name in the testName variable in order to display the test name in the report
   testName = "log in to account";
        test.log(Status.INFO,testName);
           LogInPage.myAccount();
        Thread.sleep(2000);
        LogInPage.enteruname(General.getData("user"));
        LogInPage.enterPass(General.getData("password"));
      //  Thread.sleep(3000);
       // LogInPage.singIn();
        Thread.sleep(3000);
        currentTime = String.valueOf(System.currentTimeMillis());
        imagePath = imagePath + currentTime;
     test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(General.takeScreenShot(imagePath)).build());
        extTitle = Constants.loginTitle;
         actTitle = driver.getTitle();
        try {
            Assert.assertEquals("it's not a valid page",extTitle, actTitle);

        } catch (Exception e){test.log(Status.FAIL, testName+" the page was not found " + e.getMessage());}
        finally {
            test.log(Status.PASS, testName+ actTitle);
        }
       HomePage.navigateToHomePage();
    }
  // @Ignore
    //The test checks that you reach the correct page when you click on a link on the left side of the home page
     @Test
    public void B_homePageLeftLink  () throws InterruptedException, IOException {
     //Put the test name in the testName variable in order to display the test name in the report
     testName = "homePageLeftLink";
        test.log(Status.INFO,testName);
        HomePage.kitchen();
       Thread.sleep(4000);
        currentTime = String.valueOf(System.currentTimeMillis());
        imagePath = imagePath + currentTime;
        test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(General.takeScreenShot(imagePath)).build());
        extTitle = Constants.homePageLeftLinkTitle;
        actTitle = driver.getTitle();
         try {
            Assert.assertEquals("it's not a valid page",extTitle,actTitle);

        } catch (Exception e){test.log(Status.FAIL, "the page was not found " + e.getMessage());}
        finally {
        test.log(Status.PASS, "the page is "+actTitle);
      }
        HomePage.navigateToHomePage();
              }
   //@Ignore
   //The test checks that you reach the correct page when you click on a link in the center of the home page
     @Test
    public void C_homePageCenterLink  () throws InterruptedException, IOException {
         //Put the test name in the testName variable in order to display the test name in the report
        testName = "homePageCenterLink";
        test.log(Status.INFO,testName);
        HomePage.bathroom();
        Thread.sleep(2000);
        currentTime = String.valueOf(System.currentTimeMillis());
        imagePath = imagePath + currentTime;
        test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(General.takeScreenShot(imagePath)).build());
        extTitle = Constants.homePageCenterLinkTitle;
        actTitle = driver.getTitle();
        Thread.sleep(4000);
        if (actTitle.equals(extTitle))
             test.log(Status.PASS, testName+"the link was open page: "+actTitle);
        else
            test.log(Status.FAIL, testName+"the link was not open"+actTitle);
        Thread.sleep(2000);
        HomePage.navigateToHomePage();
    }
  // @Ignore
    //The test checks that you reach the correct page when you click on a link in the top banner
    @Test
    public void D_homePageBannerLink  () throws InterruptedException, IOException {
        //Put the test name in the testName variable in order to display the test name in the report
        testName = "homePageBannerLink";
        test.log(Status.INFO,testName);
         HomePage.baby();
        Thread.sleep(4000);
        currentTime = String.valueOf(System.currentTimeMillis());
        imagePath = imagePath + currentTime;
        test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(General.takeScreenShot(imagePath)).build());
        extTitle = Constants.homePageBannerLinkTitle;
        actTitle = driver.getTitle();
          try {
            Assert.assertEquals("it's not a valid page",extTitle,actTitle);

        } catch (Exception e){test.log(Status.FAIL, "the page was not found " + e.getMessage());}
        finally {
            test.log(Status.PASS,"the page was found: "+actTitle);
        }  HomePage.navigateToHomePage();
                }

    //The test checks that the website language can be changed From English to Hebrew
    @Test
    public void h_changeLanguage  () throws InterruptedException, IOException {
        //Put the test name in the testName variable in order to display the test name in the report
        testName = "changeLanguage";
        test.log(Status.INFO,testName);
        HomePage.changeLanguage();
        HomePage.chooseLanguage();
        HomePage.shopNow();
        Thread.sleep(5000);
        extTitle = Constants.changeLanguageTitle;
        actTitle = driver.getTitle();
        currentTime = String.valueOf(System.currentTimeMillis());
        imagePath = imagePath + currentTime;
        test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(General.takeScreenShot(imagePath)).build());
        try {
            Assert.assertEquals("it's not a valid page",extTitle,actTitle);

        } catch (Exception e){test.log(Status.FAIL, "the page was not found " + e.getMessage());}
        finally {
            test.log(Status.PASS,"the page was found: "+actTitle);
        }
    }

    //The test checks that the website language can be changed From Hebrew to English
    @Test
    public void h_changeLanguageBack  () throws InterruptedException, IOException {
        //Put the test name in the testName variable in order to display the test name in the report
        testName = "changeLanguageBack";
        test.log(Status.INFO,testName);
        HomePage.changeLanguage();
        HomePage.chooseLanguageBack();
        HomePage.shopNowE();
        Thread.sleep(4000);
        currentTime = String.valueOf(System.currentTimeMillis());
        imagePath = imagePath + currentTime;
        test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(General.takeScreenShot(imagePath)).build());
        extTitle = Constants.changeLanguageTitleBack;
        actTitle = driver.getTitle();
        try {
            Assert.assertEquals("it's not a valid page",extTitle,actTitle);

        } catch (Exception e){test.log(Status.FAIL, "the page was not found " + e.getMessage());}
        finally {
            test.log(Status.PASS,"the page was found: "+actTitle);
        }
    }

    //The test verifies that a product can be found in the search panel
  //@Ignore
    @Test
    public void i_findProduct  () throws InterruptedException, IOException {
        //Put the test name in the testName variable in order to display the test name in the report
        testName = "findProduct";
        test.log(Status.INFO,testName);
        HomePage.clickSearch();
        Thread.sleep(2000);
        HomePage.search(Constants.searchProduct);
        HomePage.iconSearch();
        Thread.sleep(4000);
        currentTime = String.valueOf(System.currentTimeMillis());
        imagePath = imagePath + currentTime;
        test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(General.takeScreenShot(imagePath)).build());
        extTitle = Constants.findTitle;
        actTitle = driver.getTitle();
        try {
            Assert.assertEquals("it's not a valid page",extTitle,actTitle);

        } catch (Exception e){test.log(Status.FAIL, "the page was not found " + e.getMessage());}
        finally {
            test.log(Status.PASS,"the page was found: "+actTitle);
        }
    }

    //The test verifies that you can choose a color and size on the product page
   // @Ignore
    @Test
    public void k_productPage  () throws InterruptedException, IOException {
        //Put the test name in the testName variable in order to display the test name in the report
        testName = "productPage";
        test.log(Status.INFO,testName);
     ProductPage.navigateToProductPage();
        Thread.sleep(7000);
        //select color
        ProductPage.chooseColour();
        //select size
        ProductPage.chooseSize();
        ProductPage.addToBag();
        ProductPage.editBag();
        Thread.sleep(3000);
ProductPage.addProduct();
        Thread.sleep(3000);
ProductPage.choos2();
        Thread.sleep(3000);
          currentTime = String.valueOf(System.currentTimeMillis());
        imagePath = imagePath + currentTime;
        test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(General.takeScreenShot(imagePath)).build());
        extTitle = Constants.productPageTitle;
        actTitle = driver.getTitle();
        try {
            Assert.assertEquals("it's not a valid page",extTitle,actTitle);

        } catch (Exception e){test.log(Status.FAIL, "the page was not found " + e.getMessage());}
        finally {
            test.log(Status.PASS,"the page was found: "+actTitle);
        }
    }

    @Test
   @Ignore
    public void l_payment  () throws InterruptedException, IOException, ParserConfigurationException, SAXException {
        //Put the test name in the testName variable in order to display the test name in the report
        testName = "payment";
        test.log(Status.INFO,testName);
         ProductPage.checkout();
         Thread.sleep(1000);
        // choose a payment option
        PaymentPage.paymentOption();
        Thread.sleep(4000);
        // enter card number
        PaymentPage.enterCardNumber(General.getData("CardNumber"));
        //enter month
        PaymentPage.enterMonth(General.getData("mm"));
        //enter year
        PaymentPage.enterYear(General.getData("yy"));
        //enter cvv
        PaymentPage.cvv(General.getData("cvv"));
        //click on play
        PaymentPage.payNow();
        Thread.sleep(4000);
        currentTime = String.valueOf(System.currentTimeMillis());
        imagePath = imagePath + currentTime;
        test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(General.takeScreenShot(imagePath)).build());
        extTitle = Constants.paymentMessage;
        actTitle = PaymentPage.message();
        try {
            Assert.assertEquals("it's not a valid page",extTitle,actTitle);

        } catch (Exception e){test.log(Status.FAIL, "the page was not found " + e.getMessage());}
        finally {
            test.log(Status.PASS,"the page was found: "+actTitle);
        }
    }

    @AfterClass
    public static void afterClass() throws Exception {
        Thread.sleep(2000);
        driver.quit();
        test.log(Status.INFO, "end test");
        extent.flush();

    }
}

