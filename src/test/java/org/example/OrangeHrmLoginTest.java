package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHrmLoginTest {
    WebDriver webDriver;
    WebDriverWait wait;

    @BeforeMethod
    public void beforMethod(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h5[class='oxd-text oxd-text--h5 orangehrm-login-title']")));
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }

    @Test
    public void testLoginWithValidCredentials() {
        webDriver.findElement(By.name("username")).sendKeys("Admin");
        webDriver.findElement(By.name("password")).sendKeys("admin123");
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h6[class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")));
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h6[class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")).getText(),
                "Dashboard");
    }

    @Test
    public void testLoginWithBlankUsernameAndBlankPassword() {
        webDriver.findElement(By.name("username")).clear();
        webDriver.findElement(By.name("password")).clear();
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(webDriver.findElements(By.cssSelector("span[class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).getFirst().getText(),
                "Required");
        Assert.assertEquals(webDriver.findElements(By.cssSelector("span[class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).getLast().getText(),
                "Required");
    }

    @Test
    public void testLoginWithValidUsernameAndBlankPassword() {
        webDriver.findElement(By.name("username")).sendKeys("Admin");
        webDriver.findElement(By.name("password")).clear();
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).getText(),
                "Required");
    }

    @Test
    public void testLoginWithBlankUsernameAndValidPassword() {
        webDriver.findElement(By.name("username")).clear();
        webDriver.findElement(By.name("password")).sendKeys("admin123");
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).getText(),
                "Required");
    }

    @Test
    public void testLoginWithInvalidUsername() {
        webDriver.findElement(By.name("username")).sendKeys("Admin1");
        webDriver.findElement(By.name("password")).sendKeys("admin123");
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class='oxd-text oxd-text--p oxd-alert-content-text']")));
        Assert.assertEquals(webDriver.findElement(By.cssSelector("p[class='oxd-text oxd-text--p oxd-alert-content-text']")).getText(),
                "Invalid credentials");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        webDriver.findElement(By.name("username")).sendKeys("Admin");
        webDriver.findElement(By.name("password")).sendKeys("admin1234");
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class='oxd-text oxd-text--p oxd-alert-content-text']")));
        Assert.assertEquals(webDriver.findElement(By.cssSelector("p[class='oxd-text oxd-text--p oxd-alert-content-text']")).getText(),
                "Invalid credentials");
    }
}
