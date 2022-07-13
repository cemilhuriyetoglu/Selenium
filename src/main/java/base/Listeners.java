package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners implements ITestListener {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    @Override
    public void onStart(ITestContext testContext) {
        //specify location of the report
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Report.html");

        htmlReporter.config().setDocumentTitle("Selenium Automation"); // Tile of report
        htmlReporter.config().setReportName("Currency Exchange Test"); // name of the report
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name", "Selenium Automation");
        extent.setSystemInfo("Host name", "----");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("user", "Tester");

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName()); // create new entry in the report

        test.log(Status.PASS, result.getName() + "  >>  PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName()); // create new entry in the report
        test.log(Status.FAIL, result.getName() + "  >>  FAILED"); // to add name in extent report
        test.log(Status.FAIL, result.getThrowable()); // to add error/exception in extent report

        //Take screenshot
        File screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.FILE);
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String name = result.getName();
        String filepath = ("test-output/ScreenShots/screenshot_" + name + "_failed" + date + ".png");
        try {
            FileUtils.copyFile(screenshot, new File(filepath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            test.addScreenCaptureFromPath(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // create new entry in the report
        test.log(Status.SKIP, result.getName() + "  >>  SKIPPED");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

}