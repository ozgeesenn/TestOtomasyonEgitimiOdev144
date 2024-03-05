package TestOtomasyon.tests;

import TestOtomasyon.drivers.Driver;
import TestOtomasyon.utils.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.text.html.CSS;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class CSSHomework {

    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager= new PropertyManager();
    String url =propertyManager.getProperty("APP_URL");



    @BeforeMethod(alwaysRun = true)
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openDemoQaTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"DEMOQA");
    }

    @Test
    public void clickMeButtonQaTest(){

        //BUTTONS BUTONU TIKLANIR

        WebElement buttons = driver.findElement(new By.ByCssSelector(".collapse.element-list.show > .menu-list > li:nth-of-type(5) > .text"));
        buttons.click();

        WebElement clickMe = driver.findElement(new By.ByCssSelector("[class] [class='mt-4']:nth-child(4) .btn-primary"));
        //sadece bu selector tanımlamasi ile bu elementi bulabildim
        clickMe.click();

        //EKRANDAKİ TEXT MESAJI OKUNUR VE YAZDIRILIR

        WebElement textMessage = driver.findElement(new By.ByCssSelector("p#dynamicClickMessage"));
        System.out.println("Ekranda Gorulen Text Mesaji: "+ textMessage.getText());

    }

    @Test
    public void webTablesAdd(){

        //WEBTABLES SEKMESİ TIKLANILIR
        WebElement webTables = driver.findElement(new By.ByCssSelector(".show .btn-light:nth-of-type(4) .text"));
        webTables.click();

        //ADD BUTONUNA TIKLANILIR
        WebElement addButton = driver.findElement(new By.ByCssSelector("button#addNewRecordButton"));
        addButton.click();

        WebElement firstName= driver.findElement(new By.ByCssSelector("input#firstName"));
        WebElement lastName= driver.findElement(new By.ByCssSelector("input#lastName"));
        WebElement email= driver.findElement(new By.ByCssSelector("input#userEmail"));
        WebElement age= driver.findElement(new By.ByCssSelector("input#age"));
        WebElement salary= driver.findElement(new By.ByCssSelector("input#salary"));
        WebElement department= driver.findElement(new By.ByCssSelector("input#department"));
        WebElement btnSubmit= driver.findElement(new By.ByCssSelector("button#submit"));

        firstName.sendKeys("Özge");
        lastName.sendKeys("Şen Kaya");
        email.sendKeys("ozgeesenn1995@gmail.com");
        age.sendKeys("29");
        salary.sendKeys("234556");
        department.sendKeys("QA Team");
        btnSubmit.click();

        //DUZENLE BUTONUNA TIKLANILIR VE DUZENLEME YAPILIR
        findEntry("ozgeesenn1995@gmail.com");

        waitAndFillInputForElement( By.cssSelector("input#firstName"),"Ezgi");
        waitAndFillInputForElement( By.cssSelector("input#lastName"),"Kaya");
        driver.findElement(new By.ByCssSelector("button#submit")).click();

   }

    public void findEntry(String text) {

        List<WebElement> tableElements = driver.findElements(By.cssSelector(".rt-table .rt-tbody [role='row'] .rt-td:nth-of-type(4)"));


        for(int i=0; i< tableElements.size();i++){

            if (tableElements.get(i).getText().equalsIgnoreCase(text)){
                driver.findElement(By.cssSelector("#edit-record-" + (i + 1))).click();
                break;
            }
        }
    }

    protected void waitAndFillInputForElement(By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }


    @AfterMethod(alwaysRun = true)
    public void  after(){
        webDriver.quitDriver();
    }

}
