package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class BaseTest {
    protected WebDriver driver;



    @BeforeMethod
    public void setUp() {
        System.out.println("==== 初始化浏览器 ====");
        WebDriverManager.chromedriver().setup();
//        WebDriverManager。FirefoxDriver.setup();
//        WebDriverManager.edgedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8081"); // 替换为实际URL
        // 关键步骤：清除所有现有弹窗
        clearExistingAlerts();
    }

    @AfterMethod
    public void tearDown() {
        if (driver!=null){
            driver.quit();
        }
    }
//    测试前都把allure-results文件夹清空，确保每次的报告都是最新的
//    @BeforeAll
//    static void cleanAllureResults() throws IOException {
//        Path outputDir = Paths.get("target/allure-results");
//        System.out.println("==== 清空allure-results文件夹 ====");
//        if (Files.exists(outputDir)) {
//            Files.walk(outputDir)
//                    .sorted(Comparator.reverseOrder())
//                    .map(Path::toFile)
//                    .forEach(File::delete);
//        }
//    }

    // 失败时添加截图
    @AfterMethod
    public void onFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("失败截图", "image/png", String.valueOf(new ByteArrayInputStream(screenshot)));
        }
    }
    // 清除所有现有弹窗
    protected void clearExistingAlerts() {
        try {
            // 使用JavaScript移除所有弹窗元素
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll('.el-message').forEach(el => el.remove());"
            );

            // 额外处理原生alert
            try {
                driver.switchTo().alert().dismiss();
            } catch (NoAlertPresentException e) {
                // 忽略
            }

//            System.out.println("已清除所有现有弹窗");
        } catch (Exception e) {
            System.out.println("清除弹窗时出错: " + e.getMessage());
        }
    }
}
