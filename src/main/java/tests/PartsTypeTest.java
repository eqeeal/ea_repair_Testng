package tests;



import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PartsTypePage;

import java.time.Duration;

@Epic("零件模块")
@Feature("零件类型测试")
public class PartsTypeTest extends BaseTest {

    @Test(description = "测试零件类型页面成功进入",groups = {"smoke","partsType"})
    @Parameters({"username","password"})
    @Story("测试零件类型页面")
    public void testPartsTypePageEnter(String username,String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);//先登录
        HomePage homePage = new HomePage(driver);//先点击左边的菜单
        homePage.clickPartsTypePD();//再点击零件类型
        homePage.clickPartsTypeSD();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("success enter parts type page");
    }

//    @Parameters("partsType")
    @Test(description = "测试零件类型页面搜索功能",groups = {"smoke","partsType"})
    @Story("测试零件类型搜索功能")
    @Parameters({"username","password","searchParts"})
    public void testSearchPartsType(String username,String password,String searchParts){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);//先登录
        HomePage homePage=new HomePage(driver);
        homePage.clickPartsTypePD();
        homePage.clickPartsTypeSD();//再点击零件类型,进入零件类型页面
        PartsTypePage partsTypePage = new PartsTypePage(driver);

        String aimParts=searchParts;
        String serchaResult=partsTypePage.serchPartsType(aimParts);

        Assert.assertEquals(serchaResult,aimParts,"验证零件类型搜索功能失败");
        System.out.println("零件类型验证结果:"+(aimParts.contains(serchaResult)?"成功":"失败"));
    }

    @Test(description = "测试零件类型页面添加功能",groups = {"smoke","partsType"})
    @Story("测试零件类型添加功能")
    @Parameters({"username", "password", "addpartsType"})
    public void testAddParstType(String username, String password, String addpartsType){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);//先登录
        HomePage homePage=new HomePage(driver);
        homePage.clickPartsTypePD();
        homePage.clickPartsTypeSD();//再点击零件类型,进入零件类型页面


        PartsTypePage partsTypePage = new PartsTypePage(driver);
        String aimParts=addpartsType;

        //1.点击提交按钮
        partsTypePage.addPartsType(aimParts);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //2.断言，判断添加的零件是否可以成功搜索到
//        partsTypePage.clearPartsTypeSerarchInput();//清空搜索框
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(partsTypePage.partsTypeSerarchInput));
        String partsResult=partsTypePage.serchPartsType(aimParts);
        Assert.assertEquals(partsResult,aimParts,"验证零件类型添加功能失败");
        System.out.println("零件类型验证结果:"+(aimParts.contains(partsResult)?"成功":"失败"));
    }

    //测试零件类型的更行功能
    @Test(description = "测试零件类型页面更新功能",groups = {"smoke","partsType"})
    @Story("测试零件类型更新功能")
    @Parameters({"username","password","updateparts_before","updateparts_after"})
    public void testUpdatePartsType(String username,String password,String updateparts_before,String updateparts_after){
        //1.登录
        LoginPage loginPage= new LoginPage(driver);
        loginPage.login(username,password);//先登录
        //2.进入零件类型操作页面
        HomePage homePage=new HomePage(driver);
        homePage.clickPartsTypePD();
        homePage.clickPartsTypeSD();//再点击零件类型,进入零件类型页面

        //3.搜索需要编辑的内容是否存在
        PartsTypePage partsTypePage = new PartsTypePage(driver);
        String result=partsTypePage.serchPartsType(updateparts_before);
        String update=updateparts_after;
        //4.存在则选中列表第一行，然后点击编辑按钮;不存在则列表第一行为空
        if (!result.isEmpty()){
            //选择第一行
            partsTypePage.selectFirstRow();
            //点击编辑按钮
            partsTypePage.clickEditBtn();
            //等待修改框的出现(我悄悄加了固定等待)
            WebElement input=partsTypePage.getUpdateInputContent();
            //先删再写
            input.clear();

            input.sendKeys(update);
            partsTypePage.clickUpdateSubmitBtn();
        } else {
//            result=partsTypePage.getnotFoundPartsTypeResult().getText();
            result="未找到该零件类型";
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //5.查询更改结果
        String searchUpdateResult=partsTypePage.serchPartsType(update);
        Assert.assertEquals(searchUpdateResult,update,"验证零件类型更新功能失败");
        System.out.println("零件类型验证结果:"+(update.contains(searchUpdateResult)?"成功":"失败"));

    }

    //测试删除功能
    @Test(description = "测试零件类型页面删除功能",groups = {"smoke","partsType"})
    @Parameters({"username","password","delete_parts"})
    @Story("测试零件类型删除功能")
    public void testDeletePartsType(String username,String password,String delete_parts){
        //1.登录
        LoginPage loginPage= new LoginPage(driver);
        loginPage.login(username,password);//先登录
        //2.进入零件类型操作页面
        HomePage homePage=new HomePage(driver);
        homePage.clickPartsTypePD();
        homePage.clickPartsTypeSD();//再点击零件类型,进入零件类型页面
        //3.搜索需要删除的内容是否存在
        PartsTypePage partsTypePage = new PartsTypePage(driver);
        String del_aim=partsTypePage.serchPartsType(delete_parts);
        if (!del_aim.isEmpty()){
            //选择第一行
            partsTypePage.selectFirstRow();
            //点击删除按钮
            partsTypePage.clickDeleteBtn();
            //点击确认按钮
            partsTypePage.comfirmDeleteBtn();
        } else {
            del_aim="没有查询到结果";
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("del_aim"+del_aim);
        //4.查询删除结果
        String searchDelResult=partsTypePage.serchPartsType(delete_parts);
        Assert.assertEquals(searchDelResult,"没有查询到结果","验证零件类型删除功能失败");
        System.out.println("零件类型验证结果:"+(searchDelResult.contains("没有查询到结果")?"成功":"失败"));
    }
}
