package com.vinsguru.tests;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.vinsguru.listener.TestListener;

@Listeners(TestListener.class)
public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    //@Parameters({"broswer"})
    public void setDriver(ITestContext ctx) throws Exception{
        if(Boolean.getBoolean("seleniumgridenabled")) {
        	this.driver = getRemoteDriver();
        }else {
        	this.driver = getLocaldriver();
        }
        ctx.setAttribute("driver", this.driver);
    }
    // Access token
    private WebDriver getLocaldriver() {
    	return new ChromeDriver();
    }
    
    private WebDriver getRemoteDriver() throws Exception {
    	Capabilities cap;
    	if(System.getProperty("browser").equals("chrome")) {
    		cap = new ChromeOptions();
    	}else {
    		cap = new FirefoxOptions();
    	}
    	return new RemoteWebDriver(new URL("http://192.168.1.9:4444/"),cap);
    	//return new RemoteWebDriver(new URL("http://selenium-grid:4444/wd/hub"),cap);
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

}
