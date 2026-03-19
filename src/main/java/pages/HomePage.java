package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import java.time.Duration;

public class HomePage extends BasePage{

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[1]")

    private WebElement welcomeMessage;

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/div/div/div[1]/div/ul/li[4]/div")
    private WebElement menuPartsTypePrimaryDirectory;

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/div/div/div[1]/div/ul/li[4]/ul/li")
    private WebElement partsTypeSecondaryDirectory;

    public HomePage(WebDriver webDriver){
        super(webDriver);
    }

    //获取登录结果
    public boolean getLoginResult(){
        System.out.println(welcomeMessage.getText());
        System.out.println("test");
        if (welcomeMessage.getText().equals("欢迎使用 家电维修店的管理系统")){
            return true;
        }else return false;
    }

    public WebElement getWelcomeElement(){
        return welcomeMessage;
    }

    //点击左边零件类型一级目录
    public void clickPartsTypePD(){
        wait.until(ExpectedConditions.visibilityOf(menuPartsTypePrimaryDirectory));//等待一级目录元素出现
        menuPartsTypePrimaryDirectory.click();
    }

    //点击左边零件类型二级目录
    public void clickPartsTypeSD(){
        wait.until(ExpectedConditions.visibilityOf(partsTypeSecondaryDirectory));//等待二级目录元素出现
        partsTypeSecondaryDirectory.click();
    }


}
