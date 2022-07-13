package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {

    public static void openBrowser() {
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Driver.get().get(ConfigurationReader.get("baseUrl"));
    }

    public static void closeBrowser() {
        Driver.closeDriver();
    }


    public static void checkWebElementCount(By by) {
        try {
            if (Driver.get().findElements(by).size() > 1) {
                Assert.fail("There are more than one WebElement of " + by.toString() + " . There has to be one.");

            } else if (Driver.get().findElements(by).size() == 0) {
                Assert.fail("Element not found: " + by);

            }
        } catch (InvalidSelectorException e) {
            Assert.fail(e.getMessage());
        }
    }

    public static void waitAndClick(By by) {
        checkWebElementCount(by);
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Driver.get().findElement(by).click();
    }

    public static void clearInputTextArea(By by) {
        Driver.get().findElement(by).clear();
    }

    public static void waitAndSendKeys(By by, String input) {
        checkWebElementCount(by);
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        By webElem = by;
        // Wait until element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.presenceOfElementLocated(webElem));
        Driver.get().findElement(webElem).sendKeys(input);
    }


    public static void hover(By by) {

        WebElement element = Driver.get().findElement(by);
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);

    }

    public static void mouseOverAndClick(By by) {

        Actions action = new Actions(Driver.get());
        WebElement we = Driver.get().findElement(by);
        action.moveToElement(we).moveToElement(Driver.get().findElement(by)).build().perform();
        action.click().build().perform();
    }

    public static String getText(By by) {
        checkWebElementCount(by);
        String text = "";
        // Wait until element is visible
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        text = Driver.get().findElement(by).getText();

        return text;
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }


    public static void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue(Driver.get().findElement(by).isDisplayed(), "Element not visible: " + by);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + by);

        }
    }

    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse(Driver.get().findElement(by).isDisplayed(), "Element should not be visible: " + by);
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
    }

    public static void doubleClick(WebElement element) {
        new Actions(Driver.get()).doubleClick(element).build().perform();
    }

    public static void jsClick(By by) {
        WebElement element = Driver.get().findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor) Driver.get();
        executor.executeScript("arguments[0].click();", element);

    }

    public static void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void switchTab(int tabNumber) {

        ArrayList<String> newTb = new ArrayList<String>(Driver.get().getWindowHandles());
        Driver.get().switchTo().window(newTb.get(tabNumber));

    }

    public static String getTabTitle() {

        String tabTitle = Driver.get().getTitle();
        return tabTitle;
    }


}