package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class javascript_alter {
    public String captureToastMessage(WebDriver driver) {
        // 注入监听脚本
        LoginPage loginPage= new LoginPage(driver);
        ((JavascriptExecutor)driver).executeScript(
                "window.__lastToast = '';" +
                        "const originalShow = Toast.show || function(){};" +
                        "Toast.show = function(msg) {" +
                        "   window.__lastToast = msg;" +
                        "   originalShow.apply(this, arguments);" +
                        "};"
        );

        // 执行触发操作
        loginPage.login("invalid", "creds");

        // 获取捕获的消息
        return (String)((JavascriptExecutor)driver).executeScript(
                "return window.__lastToast || ''"
        );
    }
}
