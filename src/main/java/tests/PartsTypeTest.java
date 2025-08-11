package tests;



import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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
        System.out.println("partsResult:"+partsResult);
        Assert.assertEquals(partsResult,aimParts,"验证零件类型添加功能失败");
        System.out.println("零件类型验证结果:"+(aimParts.contains(partsResult)?"成功":"失败"));
    }
}
