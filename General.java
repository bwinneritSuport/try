package TestCases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static PageClasses.LogInPage.driver;

public class General {

    // method for ScreenShot
    public static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }

    // method for xml
    public static String getData (String keyName) throws ParserConfigurationException, IOException, SAXException, SAXException {
                File configXmlFile = new File("C:\\Users\\Zak\\IdeaProjects\\SelenumProject\\src\\test\\java\\TestCases\\config.xml");
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
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }

    // method for report
//public static ExtentReports extent;



    // method for title
    public static String title() {return driver.getTitle();}


}
