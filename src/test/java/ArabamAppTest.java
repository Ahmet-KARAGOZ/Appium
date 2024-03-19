import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
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
    public void arabamTest(){
        // uygulamanin basarili bir sekilde yuklendigi dogrulanir

        /*
        driver.activateApp("com.dogan.arabam"); //1. YOL kimlik üzerinden aktif hala getirilir. Uygulamanın açılır sayfasından başlar.

         */
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));
        // uygulaminin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementByAccessibilityId("İlan Ver").isDisplayed());

        // alt menuden ilan ara butonuna tiklanir
        // kategori olarak otomobil secilir
        // arac olarak Volkswagen secilir
        // arac markasi olarak passat secilir
        // 1.4 TSI BlueMotion secilir
        // Paket secimi yapilir
        // Ucuzdan pahaliya siralama yaparak filtreleme yapilir
        // Gelen en ucuz aracin 500.000 tl den buyuk oldugu dogrulanir
    }
}
