package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class paginaInicio {
    WebDriver driver;
    WebDriverWait wait;

    public paginaInicio(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void escribirPalabra(String palabra) {
        WebElement campoBusqueda = driver.findElement(By.name("search"));
        campoBusqueda.sendKeys(palabra);
    }

    public void hacerBusqueda() {
        WebElement botonBuscar = driver.findElement(By.cssSelector(".input-group-btn button"));
        botonBuscar.click();
    }
}

