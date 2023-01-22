import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
// קריאה מתוך קובץ XML
public class Lesson14 {
    private static WebDriver driver;
@BeforeClass
    public static void beforeClass(){
    System.setProperty("webdriver.chrome.driver", "C:\\DRIVER_NEW\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
}
@Test
public void test()throws IOException, SAXException, ParserConfigurationException
{
    driver.get(getData("URL"));
    System.out.println(getData("Author"));
    try {
        Thread.sleep(8000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
    // קריאה מקובץ חיצוני לאתר ביטוח לאומי
@Test public void BL_login()throws IOException, SAXException, ParserConfigurationException
{
      driver.navigate().to("https://ps.btl.gov.il/#/login");

    String word=getData("word");
    try {
        Thread.sleep(8000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    String userName=getData("username");
    try {
        Thread.sleep(8000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
   String pass=getData("Pass");

   System.out.println(word+userName+pass);
    driver.findElement(By.xpath("//*[@id=\"vm_userZehut\"]")).sendKeys(word);
    driver.findElement(By.xpath("//*[@id=\"vm_userName\"]")).sendKeys(userName);
    driver.findElement(By.xpath("//*[@id=\"vm_password\"]")).sendKeys(pass);
}


@AfterClass
public static void afterClass() throws InterruptedException {
    Thread.sleep(5000);
    driver.quit();
}

    private static String getData (String keyName) throws ParserConfigurationException, IOException, SAXException {
        File configXmlFile = new File("C:\\Java Projects\\config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(1).getTextContent();
    }
}
