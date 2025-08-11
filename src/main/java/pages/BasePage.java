package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;

    public BasePage(WebDriver webDriver){
        this.webDriver=webDriver;
        this.wait=new WebDriverWait(webDriver, Duration.ofSeconds(15));
        // BasePage.java
        PageFactory.initElements(webDriver, this);
    }

    // 添加通用等待方法
    protected void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    // 在 BasePage 或专门的工具类中添加

    public boolean isErrorPopupDisplayed(String expectedText) {
        // 定位弹窗的选择器
        By popupLocator = By.cssSelector(".el-message.el-message--error");

        try {
            // 设置短暂的显式等待（1秒内检查）
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(1000));
            wait.pollingEvery(Duration.ofMillis(50)); // 每50毫秒检查一次

            // 等待弹窗出现并验证文本
            wait.until(d -> {
                try {
                    WebElement popup = d.findElement(popupLocator);
                    return popup.isDisplayed() &&
                            popup.getText().contains(expectedText);
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            });
            return true;
        } catch (TimeoutException e) {
            // 备选方案：检查页面源码中是否包含错误文本
            return webDriver.getPageSource().contains(expectedText);
        }
    }

}
