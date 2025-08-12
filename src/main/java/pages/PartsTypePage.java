package pages;

import org.junit.jupiter.api.ClassOrderer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PartsTypePage extends BasePage{

//    @FindBy(xpath = "//*[@id=\"el-id-7804-6\"]")
//    @FindBy(className = "el-input__inner")
//    @FindBy(xpath = "//div[contains(@class, 'list_inp')]//input[contains(@class, 'el-input__inner') and @placeholder='类型']")
//    <input class="el-input__inner" type="text" autocomplete="off" tabindex="0" placeholder="类型" id="el-id-4747-26" spellcheck="false" data-ms-editor="true">
    @FindBy(xpath = "//input[contains(@class,'el-input__inner') and @placeholder='类型']")
    private WebElement partsTypeSerarchInput;//搜索零件类型框


    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[1]/div[1]/form/div[2]/button")
    private WebElement partsTypeSerarchBtn;//搜索零件类型按钮

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[1]/div[1]/div/button[1]")
    private WebElement partsTypeAddBtn;//添加零件类型按钮

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[1]/div[1]/div/button[2]")
    private WebElement partsTypeDetailBtn;//查看零件类型按钮

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[1]/div[1]/div/button[3]")
    private WebElement partsTypeEditBtn;//编辑零件类型按钮

    @FindBy(xpath = "//")
    private WebElement updateRowPartsType;//选择需编辑的行

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[1]/div[1]/div/button[4]")
    private WebElement partsTypeDelBtn;//删除零件类型按钮

    @FindBy(xpath = "/html/body/div[3]/div/div/div[3]/button[2]/span")
    private WebElement delPartsCmfirmBtn;//删除确认按钮

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[1]/div[2]/div[1]/div[3]/div/div[1]/div/table/tbody/tr/td[3]")
    private WebElement partsTypeResult;//查询/修改的文本框内容

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[2]/div/div/div/header/span")
    private WebElement partsTypeAddTip;//添加零件类型输入框

//    @FindBy(xpath = "//button[span[text()='提交']]")
//    @FindBy(xpath = "//span[text()='提交']/parent::button")
    //button[contains(@class,'formModel_confirm')]
//    @FindBy(xpath = "//button[contains(@class,'formModel_confirm')]")
    @FindBy(xpath = "//button[contains(@class,'formModel_confirm') and span[normalize-space(text())='提交']]")
    private WebElement partsTypeSubmitBtn;//添加零件类型提交按钮

//    @FindBy(xpath = "//*[@id=\"el-id-3061-74\"]")
//    @FindBy(xpath = "//input[@class='el-input__inner' and @placeholder='类型']")
    @FindBy(xpath = "//div[contains(@class, 'el-form-item__content')]//div[contains(@class, 'list_inp')]//input[contains(@class, 'el-input__inner') and @placeholder='类型']")
    private WebElement partsTypeInputSubmit;//添加零件提交输入框

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[2]/div/div/div/footer/span/button[1]")
    private WebElement partsCancelBtn;//取消按钮
//    @FindBy(xpath = "//td[contains(@class, 'el-table_1_column_3') and div/text()='%s']")
    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[1]/div[2]/div[1]/div[3]/div/div[1]/div/table/tbody/tr[1]/td[3]")
    private WebElement partsTypeFirstRow;//列表第一行3列

//    @FindBy(xpath = "//input[contains(@class, 'el-input__inner') and @placeholder='类型']")
    @FindBy(xpath = "/html/body/div[1]/section/div/main/div[3]/div[2]/div/div/div/div/form/div/div/div/div/div/div/input")
    private WebElement updateInputContent;//修改输入框和添加输入框的元素结构高度相似，唯一区别是定位前是否有值，所以修改框可以用@value != '' 或则 string-length(@value) > 0

//    @FindBy(xpath = "span[contains(@class,'el-table__empty-text')and text()='暂无数据']")
//    @FindBy(xpath = "/html/body/div[1]/section/div/main/div[3]/div[1]/div[2]/div[1]/div[3]/div/div[1]/div/div/span")
//    private WebElement notFoundPartsTypeResult;

    @FindBy(xpath = "//*[@id=\"app\"]/section/div/main/div[3]/div[2]/div/div/div/footer/span/button[2]/span")
    private WebElement sumbitUpdateBtn;
    //从BasePage继承的构造方法
    public PartsTypePage(WebDriver webDriver) {
        super(webDriver);
    }

    //输入零件类型内容
    public void serachPartsTypeInput(String partsType){
        partsTypeSerarchInput.clear();
        partsTypeSerarchInput.sendKeys(partsType);
    }

    public void clickPartsTypeSerarchBtn(){//点击搜索按钮
        partsTypeSerarchBtn.click();
    }

    public void clickPartsTypeSubmitBtn(){//点击提交按钮
        partsTypeSubmitBtn.click();
    }

    //搜索零件类型功能
//    public String serchPartsType(String partsType){
//
//
//        wait.until(ExpectedConditions.visibilityOf(partsTypeSerarchInput));
//        System.out.println("搜索零件类型为："+partsType);
//        serachPartsTypeInput(partsType);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        clickPartsTypeSerarchBtn();
//        try {
//            Thread.sleep(1000);
//            if (!partsTypeResult.isDisplayed()){
//                return partsTypeResult.getText();
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
////        wait.until(ExpectedConditions.visibilityOf(partsTypeResult));//显示等待查询结果
//        return "没有查询到结果";
//    }

    public String serchPartsType(String partsType) {
        wait.until(ExpectedConditions.visibilityOf(partsTypeSerarchInput));
        System.out.println("搜索零件类型为：" + partsType);
        serachPartsTypeInput(partsType);//输入搜索内容

        // 使用显式等待代替 Thread.sleep(1000)
        wait.until(webDriver -> {
            try {
                Thread.sleep(100); // 微等待减少CPU消耗
                return true;
            } catch (InterruptedException e) {
                return false;
            }
        });

        clickPartsTypeSerarchBtn();//点击搜索按钮

        // 再次使用显式等待
        wait.until(webDriver -> {
            try {
                Thread.sleep(100);
                return true;
            } catch (InterruptedException e) {
                return false;
            }
        });

        // 安全处理元素状态
        try {
            // 首先检查元素是否存在于DOM中
            if (webDriver.findElements(By.id(partsTypeResult.getAttribute("id"))).size() > 0) {//如果元素存在
                // 然后检查元素是否可见
                if (partsTypeResult.isDisplayed()) {//如果元素存在且可见
                    return partsTypeResult.getText();
                } else {
                    // 元素存在但不可见的情况
                    return "元素存在但不可见";
                }
            }
        } catch (Exception e) {
            // 处理其他可能的异常
            System.out.println("搜索异常: " + e.getMessage());
        }

        // 如果以上条件都不满足，返回未找到的结果
        return "没有查询到结果";
    }


    //清除搜索框内容功能
    public void clearPartsTypeSerarchInput(){
        partsTypeSerarchInput.clear();
    }

    //添加零件类型功能
    public void addPartsType(String partsType){
        partsTypeAddBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(partsTypeInputSubmit));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        partsTypeInputSubmit.sendKeys(partsType);//输入到搜索框去了，定位错误

//        wait.until(ExpectedConditions.visibilityOf(partsTypeSubmitBtn));
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        clickPartsTypeSubmitBtn();//点击提交
    }

    /**
public void addPartsType(String partsType) {
    // 点击添加按钮
    partsTypeAddBtn.click();
    System.out.println("点击了添加按钮");

    // 等待弹窗和表单完全加载（关键：先等表单就绪）
    By dialogLocator = By.cssSelector("div[role='dialog'][aria-label='新增零件类型']");
    By formLocator = By.cssSelector("form.el-form.formModel_form"); // 表单的定位器
    wait.until(ExpectedConditions.visibilityOfElementLocated(dialogLocator));
    wait.until(ExpectedConditions.visibilityOfElementLocated(formLocator));
    System.out.println("弹窗和表单已完全加载");

    // 等待提示出现
    wait.until(ExpectedConditions.elementToBeClickable(partsTypeAddTip));
    System.out.println("添加提示已出现");

    try {
        // 定位输入框
        By inputLocator = By.xpath("//input[contains(@class, 'el-input__inner') and @placeholder='类型']");
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(inputLocator));
        System.out.println("找到输入框，ID为: " + inputElement.getAttribute("id"));

        // 1. 确保输入框在视口内，避免被表单滚动遮挡
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                inputElement
        );
        Thread.sleep(500); // 等待滚动完成

        // 2. 彻底绕过点击：用JS直接聚焦输入框（无需点击，避免被form拦截）
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].focus();", inputElement);
        System.out.println("用JS聚焦输入框，跳过点击");
        Thread.sleep(500);

        // 3. 清空并输入内容（直接通过JS操作，不依赖点击）
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value = '';", inputElement); // 清空
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].value = arguments[1];",
                inputElement,
                partsType
        );
        System.out.println("用JS设置输入值为: " + partsType);

        // 4. 触发输入事件（关键：通知前端框架值已变化）
        ((JavascriptExecutor) webDriver).executeScript("" +
                        "var event = new Event('input', { bubbles: true });" +
                        "arguments[0].dispatchEvent(event);" +
                        "var changeEvent = new Event('change', { bubbles: true });" +
                        "arguments[0].dispatchEvent(changeEvent);",
                inputElement
        );
        Thread.sleep(500);

        // 验证输入结果
        String actualValue = inputElement.getAttribute("value");
        System.out.println("输入框实际值: " + actualValue);
        if (!partsType.equals(actualValue)) {
            throw new RuntimeException("输入失败，预期: " + partsType + "，实际: " + actualValue);
        }

        // 点击提交按钮（同样用JS避免可能的遮挡）
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(partsTypeSubmitBtn));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", submitBtn);
        System.out.println("用JS点击提交按钮");

        // 等待弹窗关闭
        try {
            WebDriverWait longWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            longWait.until(ExpectedConditions.invisibilityOfElementLocated(dialogLocator));
            System.out.println("弹窗已关闭");
        } catch (TimeoutException e) {
            System.out.println("弹窗关闭超时，可能已提交成功");
        }

    } catch (Exception e) {
        System.out.println("操作失败: " + e.getMessage());
        throw new RuntimeException(e);
    }
}
**/

    //选中第一行数据
    public void selectFirstRow(){
        partsTypeFirstRow.click();
    }

    //点击编辑按钮
    public void clickEditBtn(){
        partsTypeEditBtn.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //获取修改输入框元素
    public WebElement getUpdateInputContent(){
        return updateInputContent;
    }
    //提交修改结果
    public void clickUpdateSubmitBtn(){
        sumbitUpdateBtn.click();
    }

    //获取搜索失败的元素提示
    public WebElement getnotFoundPartsTypeResult(){
//        return notFoundPartsTypeResult;
        return null;
    }


    //点击删除按钮,进入删除页面
    public void clickDeleteBtn(){
        partsTypeDelBtn.click();
    }

    //确认删除页面
    public void comfirmDeleteBtn(){
        delPartsCmfirmBtn.click();
    }

}
