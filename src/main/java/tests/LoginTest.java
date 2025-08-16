package tests;

import base.BaseTest;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelDataProvider;

import java.sql.Time;
import java.time.Duration;
import java.util.Map;

//└── 用户认证 (Epic)
//    └── 登录功能 (Feature)
//        ├── 正常登录场景 (Story)
//        │   └── [测试登录成功] (TestCase)
//        └── 异常登录场景 (Story)
//            └── [测试登录失败] (TestCase)
@Epic("用户认证")
@Feature("登录功能")
public class LoginTest extends BaseTest {


    @Test(description = "测试登录成功")
    @Story("正常登录场景")
    @Severity(SeverityLevel.CRITICAL)//用于标记测试用例的重要程度
    public void testSuccessfulLogin() {
        System.out.println("==== 测试方法开始 ====");
        LoginPage loginPage= new LoginPage(driver);
        loginPage.login("admin","admin");
        try {
            Thread.sleep(1000);//10s
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        HomePage homePage= new HomePage(driver);
        System.out.println(homePage.getLoginResult());
        Assert.isTrue(homePage.getLoginResult(),"登录成功验证失败");
        System.out.println("登录成功验证结果："+(homePage.getLoginResult()?"成功":"失败"));
    }



    @Test(description = "测试登录失败")
    @Story("异常登录场景")
    @Severity(SeverityLevel.MINOR)//用于标记测试用例的重要程度
    public void testWrongLogin() {
        System.out.println("==== 测试方法开始 ====");
        LoginPage loginPage= new LoginPage(driver);
        WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(5));

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        loginPage.login("2024","1234");
        webDriverWait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        boolean isErrorDisplayed = loginPage.getErrorMessage().equals("账号或密码不正确");
        // 3. 断言验证
        Assert.isTrue(isErrorDisplayed,"登录错误弹窗验证失败");
        // 4. 添加日志
        System.out.println("login wrong msg result: " + (isErrorDisplayed ? "成功" : "失败"));
    }


    @Test(description = "账号密码错误提示框显示正确提示词")
    @Story("异常登录场景")
    @Severity(SeverityLevel.NORMAL)//用于标记测试用例的重要程度
    public void testLoginError() {
        System.out.println("==== 测试方法开始 ====");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("202411", "1234");

        // 显式等待错误信息出现（最长10秒）
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));//显示等待，等待10s，如果10s内元素出现，则继续执行，如果10s内元素未出现，则抛出异常
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));

        String errorText = loginPage.getErrorMessage();
        System.out.println("实际错误信息: " + errorText);

        // 灵活断言（检查包含关键文本）
        boolean isErrorDisplayed = errorText.contains("账号或密码不正确");
        Assert.isTrue(isErrorDisplayed, "登录错误弹窗验证失败，实际信息: " + errorText);

        System.out.println("错误弹窗验证结果: " + (isErrorDisplayed ? "成功" : "失败"));
    }




    // Excel数据提供者
    @DataProvider(name = "loginData")
    public Object[][] provideLoginData() {
        String filePath = "src/main/resources/datadriven/testdata.xlsx";
        return ExcelDataProvider.getData(filePath, "LoginTest");
    }

    @Test(dataProvider = "loginData", description = "登录功能数据驱动测试")
    @Story("登录场景")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginFunctionality(Map<String, String> testData) {
        String testCaseID = testData.get("TestCaseID");
        String description = testData.get("Description");
        String username = testData.get("Username");
        String password = testData.get("Password");
        boolean expectedResult = Boolean.parseBoolean(testData.get("ExpectedResult"));

        // 添加Allure参数展示
        Allure.parameter("测试用例ID", testCaseID);
        Allure.parameter("描述", description);
        Allure.parameter("用户名", username);
        Allure.parameter("密码", password);
        Allure.parameter("预期结果", String.valueOf(expectedResult));

        System.out.println("==== 执行测试: " + testCaseID + " - " + description + " ====");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // 使用显式等待替代Thread.sleep
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean actualResult = false;
        System.out.println("期待的结果"+expectedResult);
        // 根据预期结果动态等待
        if (expectedResult) {
            HomePage homePage = new HomePage(driver);
            // 成功登录的等待条件
            wait.until(ExpectedConditions.visibilityOf(homePage.getWelcomeElement()));
            actualResult = homePage.getLoginResult() == expectedResult;
        } else {
            // 登录失败的等待条件
            wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));
            actualResult = false; // 登录失败提示框出现时，证明失败了，实际结果为false
        }

        // 断言增强：包含测试用例信息
//        Assert.assertEquals(actualResult, expectedResult,
//                "测试用例失败: " + testCaseID + " - " + description +
//                        "\n预期结果: " + expectedResult + ", 实际结果: " + actualResult);
        Assert.isTrue(actualResult==expectedResult,"测试用例失败: " + testCaseID + " - " + description +
                "\n预期结果: " + expectedResult + ", 实际结果: " + actualResult);
        }



}
