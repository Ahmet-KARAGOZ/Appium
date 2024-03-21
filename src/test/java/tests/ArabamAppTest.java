package tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ArabamAppTest {
    AndroidDriver<AndroidElement> driver; //android cihazlarin driveri
    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");

        capabilities.setCapability("appPackage", "com.dogan.arabam"); //hangi uygulamayı başlatacağımız bundle id ile giriyoruz. (NOT: Uygulama yüklü olmalı.)
        capabilities.setCapability("appActivity", "com.dogan.arabam.presentation.feature.home.HomeActivity"); //uygulamanın hangi sayfasında çalışacağımız.

        driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    public void arabamTest() throws InterruptedException {
        // uygulamanin basarili bir sekilde yuklendigi dogrulanir

        /*
        driver.activateApp("com.dogan.arabam"); //1. YOL kimlik üzerinden aktif hala getirilir. Uygulamanın açılır sayfasından başlar.

         */
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));
        /* uygulaminin basarili bir sekilde acildigi dogrulanir */
        Assert.assertTrue(driver.findElementByAccessibilityId("İlan Ver").isDisplayed());

        // alt menuden ilan ara butonuna tiklanir

        //driver.findElementByAccessibilityId("İlan Ara").click();
        driver.findElementByXPath("//*[@text='İlan Ara']").click();

        // kategori olarak otomobil secilir
        driver.findElementByXPath("//*[@text='Otomobil']").click();
        Thread.sleep(3000);

        // arac olarak Volkswagen secilir
        TouchAction action = new TouchAction<>(driver);
          action
                .press(PointOption.point(530, 1553))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(120)))
                .moveTo(PointOption.point(530, 336))
                .release()
                .perform();

          //driver.findElementByXPath("//*[@text='Volkswagen']").click();
            Thread.sleep(1000);
          action.press(PointOption.point(230, 1289))
                  .release()
                  .perform();

        // arac markasi olarak passat secilir

        driver.findElementByXPath("//*[@text='Passat']").click();
        // 1.4 TSI BlueMotion secilir
        driver.findElementByXPath("//*[@text='1.4 TSi BlueMotion']").click();
        // Paket secimi yapilir
        driver.findElementByXPath("//*[@text='Highline']").click();
        // Ucuzdan pahaliya siralama yaparak filtreleme yapilir
        driver.findElementById("com.dogan.arabam:id/textViewSorting").click();
        driver.findElementByXPath("//*[@text='Fiyat - Ucuzdan Pahalıya']").click();
        // Gelen en ucuz aracin 500.000 tl den buyuk oldugu dogrulanir
        String enUcuzAracFiyati = driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvPrice'])[1]").getText();
        System.out.println(enUcuzAracFiyati);

        enUcuzAracFiyati = enUcuzAracFiyati.replaceAll("\\D", "");
        System.out.println(enUcuzAracFiyati);

        Assert.assertTrue(Integer.parseInt(enUcuzAracFiyati)> 500000);
    }
}
