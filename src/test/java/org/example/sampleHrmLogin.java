package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class sampleHrmLogin {

    @Test
    public void testOrangeHrmLogin() {

        //Create a browser instance
        WebDriver webDriver = new ChromeDriver();

        // Maximize the browser window
        webDriver.manage().window().maximize();

        //Navigate to sauce demo site
        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        //Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        //Wait for the login title to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h5[class='oxd-text oxd-text--h5 orangehrm-login-title']")));

        //Type username
        webDriver.findElement(By.name("username")).sendKeys("Admin");

        //Type password
        webDriver.findElement(By.name("password")).sendKeys("admin123");

        //Click login
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();

        //Wait for the login title to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h6[class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")));

        //Verify if user can login to the system
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h6[class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")).getText(),
                "Dashboard");

        //Close the browser
        webDriver.quit();

    }
}
