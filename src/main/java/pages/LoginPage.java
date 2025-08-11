package pages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class LoginPage extends BasePage{

    //使用findby定位元素位置
    @FindBy(xpath = "//*[@id=\"app\"]/div/div/form/div[2]/input")

    private WebElement usernameInput;

    @FindBy(xpath="//*[@id=\"app\"]/div/div/form/div[3]/input")
    private WebElement passwordInput;

    @FindBy(xpath="//*[@id=\"app\"]/div/div/form/div[4]/button")
    private WebElement clicklogin_btn;


//    @FindBy(className = "css = \".el-message.el-message--error\"")
//    @FindBy(xpath = "//*[@id=\"message_88\"]")
//    @FindBy(xpath = "//*[starts-with(@id, 'message_')]")
    @FindBy(css = "div.el-message[role='alert']")
    private WebElement errorMessageElement;//该死的dom元素id是动态的，所以不能直接定位，使用starts-with方法

    public LoginPage(WebDriver webDriver){
        super(webDriver);
    }

    //使用findby定位元素位置，并输入内容
    public void inputusername(String username){
        usernameInput.sendKeys(username);
    }

    public void inputpassword(String password){
        passwordInput.sendKeys(password);
    }

    public void click_btn(){
        clicklogin_btn.click();
    }

    //获取错误提示元素
    public WebElement getErrorMessageElement(){

        return errorMessageElement;
    }
    //获取错误提示信息内容
    public String getErrorMessage(){

        return errorMessageElement.getText();
    }
    //组合操作，完成登录流程
    public void login(String username,String password){
        inputusername(username);
        inputpassword(password);
        click_btn();
    }

}
